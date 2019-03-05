/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import interfaz.UI;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import logica.MetadataMapa;
import simulacion.eventos.AcopioDesechoPeatonal;
import simulacion.eventos.AcumulacionDesechoPeatonal;
import simulacion.eventos.DepositoDesechoEnPuntoAcumulacion;
import simulacion.eventos.EntradaARuta;
import simulacion.eventos.RecoleccionConCamion;
import simulacion.eventos.SalidaRuta;
import simulacion.eventos.UnidadAvanza;
import simulacion.metricas.Metrica;

/**
 *
 * @author gtroncone
 */
public class Simulacion implements Serializable {

    private final LinkedList<Ruta> rutas;
    private final LinkedList<Camion> camiones;

    private int numRepeticiones = 5;
    private double salarioBarredores = 0;
    private double salarioEquipoRecoleccion = 0;
    private int numMecanicos = 0;
    private double salarioMecanicos = 0;
    private int tipoDeMantenimiento;
    private int numeroDeDias = 1;
    private int diaInicial = 1;
    
    private final int NUMERO_MINUTOS_RECOLECCION_PROMEDIO = 5;
    private final int INICIO_HORARIO_LABORAL = 8;
    private final int FINAL_HORARIO_LABORAL = 17;
    
    private final LinkedList<Metrica> listaMetricas;

    public Simulacion(LinkedList<Metrica> metricas) {
        this.rutas = new LinkedList<>();
        this.camiones = new LinkedList<>();
        this.listaMetricas = metricas;
    }

    public void añadirRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public LinkedList<Ruta> getRutas() {
        return rutas;
    }

    public LinkedList<Camion> getCamiones() {
        return this.camiones;
    }

    public void añadirCamion(Camion camion) {
        this.camiones.add(camion);
    }

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setNumRepeticiones(int numRepeticiones) {
        this.numRepeticiones = numRepeticiones;
    }

    public double getSalarioBarredores() {
        return salarioBarredores;
    }

    public void setSalarioBarredores(double salarioBarredores) {
        this.salarioBarredores = salarioBarredores;
    }

    public double getSalarioEquipoRecoleccion() {
        return salarioEquipoRecoleccion;
    }

    public void setSalarioEquipoRecoleccion(double salarioEquipoRecoleccion) {
        this.salarioEquipoRecoleccion = salarioEquipoRecoleccion;
    }

    public int getNumMecanicos() {
        return numMecanicos;
    }

    public void setNumMecanicos(int numMecanicos) {
        this.numMecanicos = numMecanicos;
    }

    public double getSalarioMecanicos() {
        return salarioMecanicos;
    }

    public void setSalarioMecanicos(double salarioMecanicos) {
        this.salarioMecanicos = salarioMecanicos;
    }

    public int getTipoDeMantenimiento() {
        return tipoDeMantenimiento;
    }

    public void setTipoDeMantenimiento(int tipoDeMantenimiento) {
        this.tipoDeMantenimiento = tipoDeMantenimiento;
    }

    public int getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(int numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public int getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(int diaInicial) {
        this.diaInicial = diaInicial;
    }

    private void iniciarSimulacion() {
        ContextoSimulacion[] instancias = new ContextoSimulacion[numRepeticiones];
        for (int i = 0; i < instancias.length; i++) {
            instancias[i] = new ContextoSimulacion(rutas, camiones);
        }
        cicloPrincipal(instancias);
    }

    private int determinarNumTicks() {
        return 60 * 24 * numeroDeDias;
    }

    private void cicloPrincipal(ContextoSimulacion[] contextos) {
        try {
            int numTicks = determinarNumTicks();
            for (ContextoSimulacion contexto : contextos) {
                listarEventosAcumulacionDesechos(contexto, numTicks);
                listarEventosAcumulacionDesechoPeatonal(contexto, numTicks);
                listarEventosEntradaARuta(contexto, numTicks);
                listarEventosAcopioDesechoPeatonal(contexto, numTicks);
                ordenarEventos(contexto);
                ejecutarEventos(contexto);
            }
            desplegarResultados(contextos);
        } catch (IOException ex) {
            UI.alerta("Error al guardar el archivo con los resultados");
        }
    }  
    
    private void listarEventosAcopioDesechoPeatonal(ContextoSimulacion contexto,
        int numTicks) {
        Random rand = new Random();
        for (int i = 0; i < contexto.getRutas().size(); i++) {
            Ruta ruta = contexto.getRutas().get(i);
            for (int j = 0; j < ruta.getListaAreas().size(); j++) {
                AreaBarrido area = ruta.getListaAreas().get(j);
                for (int k = 0; k < area.getNumeroBarredores(); k++) {
                    double velocidad;
                    velocidad = area.getVelocidadAcopio().evaluarDistribucionInversa(rand.nextDouble());
                    double capacidad = area.getCapacidad();
                    int ticksParaLlenado = (int) Math.floor(capacidad / velocidad);
                    int auxTicks = 0;
                    while (auxTicks + ticksParaLlenado < numTicks) {
                        auxTicks += ticksParaLlenado;
                        double cantidadBasuraEnArea = 0;
                        for (int w = 0; i < area.getCantidadBasura().length; w++) {
                            cantidadBasuraEnArea += area.getCantidadBasura()[w];
                        }
                        if (tickEstaEnHorarioLaboral(auxTicks)) {
                            contexto.añadirEventoAcopiacion(new AcopioDesechoPeatonal(auxTicks, ruta, area, cantidadBasuraEnArea));
                        }
                    }
                }
            }
        }
    }
    
    private boolean tickEstaEnHorarioLaboral(int tick) {
        int tickInicioHorario = INICIO_HORARIO_LABORAL * 60;
        int tickFinalHorario = FINAL_HORARIO_LABORAL * 60;
        int tickDia = 24 * 60;
        return ((tick % tickDia) >= tickInicioHorario && (tick % tickDia) <= tickFinalHorario);
    }

    private void listarEventosAcumulacionDesechoPeatonal(ContextoSimulacion contexto,
        int numTicks) {
        Random rand = new Random();
        for (int i = 0; i < contexto.getRutas().size(); i++) {
            Ruta ruta = contexto.getRutas().get(i);
            for (int j = 0; i < ruta.getListaAreas().size(); j++) {
                int auxTicks = 0;
                AreaBarrido area = ruta.getListaAreas().get(j);
                while (auxTicks < numTicks) {
                    double aleatorio = rand.nextDouble();
                    if (auxTicks + 1 < numTicks) {
                        auxTicks++;
                        contexto.añadirEventoAcumulacion(new AcumulacionDesechoPeatonal(auxTicks,
                            area, ruta.getDesechosPorPeaton(),
                            (int) Math.floor(ruta.getFlujoPeatonal().evaluarDistribucionInversa(aleatorio))));
                    }
                }
            }
        }
    }

    private void listarEventosAcumulacionDesechos(ContextoSimulacion contexto, int numTicks) {
        Random rand = new Random();
        for (int i = 0; i < contexto.getRutas().size(); i++) {
            Ruta ruta = contexto.getRutas().get(i);
            for (int j = 0; j < ruta.getCalles().size(); j++) {
                Calle calle = ruta.getCalles().get(j);
                if (calle.getPuntosAcum() != null) {    
                    for (int k = 0; k < calle.getPuntosAcum().getNumeroPuntos(); k++) {
                        PuntosAcumulacion puntos = calle.getPuntosAcum();
                        int auxTicks = 0;
                        while (auxTicks < numTicks) {
                            double aleatorio = rand.nextDouble();
                            double tiempoEvento;
                            tiempoEvento = puntos.getTasaAcumulacion().evaluarDistribucionInversa(aleatorio);
                            if (tiempoEvento + auxTicks < numTicks) {
                                auxTicks += (int) Math.floor(tiempoEvento);
                                contexto.añadirEventoAcumulacion(
                                    new DepositoDesechoEnPuntoAcumulacion(auxTicks, calle, k, diaInicial));
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void listarEventosEntradaARuta(ContextoSimulacion contexto, int numTicks) {
        for (int i = 0; i < contexto.getRutas().size(); i++) {
            Ruta ruta = contexto.getRutas().get(i);
            Horario horario = ruta.getHorario();
            
            LinkedList<Camion> camionesAsignados = horario.getCamionesAsignados();
            LinkedList<Integer> mapaCamionesAHorarios = horario.getMapaCamionHorarios();
            if (camionesAsignados != null) {
                for (int j = 0; j < camionesAsignados.size(); j++) {
                    Camion camion = camionesAsignados.get(j);
                    int[] datosHorario = horario.getDatos().get(mapaCamionesAHorarios.get(j));
                    int paridadDiaInicial = diaInicial % 2;
                    for (int dia = 0; dia < numeroDeDias; dia++) {
                        // -> Aquí deben incluirse los eventos de mantenimiento <-
                        if (datosHorario[2] == 0 || dia % 2 == paridadDiaInicial) {
                            int tick = 0;
                            tick += dia * 24 * 60;
                            tick += (datosHorario[0] * 60) + datosHorario[1];
                            contexto.añadirEventoEntradaUnidad(new EntradaARuta(tick, camion, ruta));
                            listarEventosAvanceUnidad(tick, camion, ruta, numTicks, contexto);
                        }
                    }
                }
            }
        }
    }
    
    private void listarEventosAvanceUnidad(int tickInicial, Camion camionAvanza,
            Ruta ruta, int numTicks, ContextoSimulacion contexto) {
        
        Random rand = new Random();
        int ticksAcum = tickInicial;
        
        // El camión debe estar en la calle inicial
        for (int i = 0; i < ruta.getCalles().size(); i++) {
            Calle calle = ruta.getCalles().get(i);
            int ticksParaAvanzar = 0;
            for (int j = 0; j <= calle.getPuntosAcum().getNumeroPuntos(); j++) {
                // -> Aquí deben manejarse los eventos de avería y de reparación <-
                double velocidad = 1;
                velocidad = calle.getVelocidad().evaluarDistribucionInversa(rand.nextDouble());
                
                double distancia = calcularDistanciaEntrePuntos(calle.getPuntoFinal(), calle.getPuntoFinal(), ruta);
                distancia /= (calle.getPuntosAcum().getNumeroPuntos() + 1);

                ticksParaAvanzar = (int) Math.floor(distancia / velocidad);

                // En este caso hay que avanzar a la siguiente calle
                if (ticksAcum + ticksParaAvanzar < numTicks) {
                    ticksAcum += ticksParaAvanzar;
                    if (j == calle.getPuntosAcum().getNumeroPuntos()) {
                        if (ruta.getCalles().getLast().equals(calle)) {
                            // En este caso se abandona la ruta y se va al centro de transferencia
                            contexto.añadirEventoAvanceUnidades(new SalidaRuta(ticksAcum, calle, camionAvanza, ruta));
                        } else {
                            // En este caso se va a la siguiente calle en la ruta
                            contexto.añadirEventoAvanceUnidades(new UnidadAvanza(ticksParaAvanzar,
                                camionAvanza, ruta, calle, true));
                        }
                    } else {
                        // En este caso hay que avanzar al siguiente punto de acumulación
                        contexto.añadirEventoAvanceUnidades(new UnidadAvanza(ticksParaAvanzar,
                            camionAvanza, ruta, calle, false));
                        if (ticksAcum + NUMERO_MINUTOS_RECOLECCION_PROMEDIO < numTicks) {
                            ticksAcum += NUMERO_MINUTOS_RECOLECCION_PROMEDIO;
                            RecoleccionConCamion recoleccion = new RecoleccionConCamion(ticksAcum,
                                calle, camionAvanza, ruta);
                            contexto.añadirEventoRecoleccion(recoleccion);
                            if (recoleccion.hayIdaATransferencia()) {
                                ticksAcum += 2 * MetadataMapa.getMinutosPromedioATransferencia(
                                    ruta.getPuntos().get(recoleccion.getPuntoInicial()), ruta.getZoom());
                            }
                        }
                    }
                }   
            }
        }
    }
    
    private void desplegarResultados(ContextoSimulacion[] contextos) throws IOException {
        JFileChooser j = new JFileChooser();
        int returnValue = j.showSaveDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = j.getSelectedFile().getAbsolutePath();
            String[] partes = filePath.split(Pattern.quote("/"));
               String[] aux = partes[partes.length - 1].split(Pattern.quote("."));
               String extension = aux[aux.length - 1];
               if (extension.isEmpty()) {
                   filePath += ".html";
               } else {
                    if (!extension.equals("html")) {
                        if (filePath.charAt(filePath.length() - 1) == '.') {
                            filePath += "html";
                        } else {
                            filePath += ".html";
                        }
                    }
               }
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)));
            
            String data = "";
            
            data += "<!DOCTYPE html>\n";
            data += "<html>\n";
            data += "<head>\n";
            data += "<title>Resultados</title>\n";
            data += "</head>\n";
            data += "<body>\n";
            data += "<ul>\n";
            data += "<li>Resultado 1</li>\n";
            data += "</ul>\n";
            data += "</body>\n";
            data += "</html>\n";
            
            String[] dataToWrite = data.split(Pattern.quote("\n"));
            for (String line : dataToWrite) {
                br.write(line);
            }
            br.close();
        }
    }
    
    public static double calcularDistanciaEntrePuntos(int puntoI, int puntoF, Ruta ruta) {
        Point puntoInicial = ruta.getPuntos().get(puntoI);
        Point puntoFinal = ruta.getPuntos().get(puntoF);
        return (puntoFinal.distance(puntoInicial) * MetadataMapa.getEscalas()[ruta.getZoom()]);
    }
    
    public static double calcularDistanciaEntrePuntoYTransferencia(int puntoI, Ruta ruta) {
        Point puntoInicial = ruta.getPuntos().get(puntoI);
        return MetadataMapa.getDistanciaATransferencia(puntoInicial, ruta.getZoom());
    }
    
    public void ejecutar() {
        if (estadoEsValido()) {
            iniciarSimulacion();
        }
    }
    
    private void ordenarEventos(ContextoSimulacion contexto) {
        contexto.sortEventos();
    }
    
    private void ejecutarEventos(ContextoSimulacion contexto) {
        contexto.ejecutarEventos();
    }

    private boolean estadoEsValido() {
        return true;
    }
}
