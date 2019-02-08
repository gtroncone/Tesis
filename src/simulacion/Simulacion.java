/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;
import logica.MetadataMapa;
import simulacion.eventos.AcumulacionDesechoPeatonal;
import simulacion.eventos.DepositoDesechoEnPuntoAcumulacion;
import simulacion.eventos.EntradaARuta;
import simulacion.eventos.SalidaRuta;
import simulacion.eventos.UnidadAvanza;

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
    private final double DISTANCIA_PROMEDIO_A_TRANSFERENCIA;
    private final double[] escalas;

    public Simulacion() {
        rutas = new LinkedList<>();
        camiones = new LinkedList<>();
        MetadataMapa aux = new MetadataMapa();
        escalas = aux.getEscalas();
        DISTANCIA_PROMEDIO_A_TRANSFERENCIA = aux.getDistanciaPromedioATransferencia();
    }

    public void añadirRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public LinkedList<Ruta> getRutas() {
        return rutas;
    }

    public LinkedList<Camion> getCamiones() {
        return camiones;
    }

    public void añadirCamion(Camion camion) {
        camiones.add(camion);
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
        int numTicks = determinarNumTicks();
        for (ContextoSimulacion contexto : contextos) {
            listarEventosAcumulacionDesechos(contexto, numTicks);
            listarEventosAcumulacionDesechoPeatonal(contexto, numTicks);
            listarEventosEntradaARuta(contexto, numTicks);
            // listarEventosAveria(contexto, numTicks);
            //ejecutarEventos(contexto);
        }
    }

    private void listarEventosAcumulacionDesechoPeatonal(ContextoSimulacion contexto,
        int numTicks) {
        Random rand = new Random();
        for (int i = 0; i < contexto.getRutas().size(); i++) {
            Ruta ruta = contexto.getRutas().get(i);
            int auxTicks = 0;
            while (auxTicks < numTicks) {
                double aleatorio = rand.nextDouble();
                if (auxTicks + 1 < numTicks) {
                    auxTicks++;
                    if (ruta.getFlujoPeatonal().getTipoDistribucion().equals("Discreta")) {
                        contexto.añadirEventoAcumulacion(new AcumulacionDesechoPeatonal(auxTicks, 
                            ruta.getListaAreas(), ruta.getDesechosPorPeaton(),
                            ruta.getFlujoPeatonal().getDistribucionDiscreta().inverseCumulativeProbability(aleatorio)));
                    } else if (ruta.getFlujoPeatonal().getTipoDistribucion().equals("Continua")) {
                        contexto.añadirEventoAcumulacion(new AcumulacionDesechoPeatonal(auxTicks,
                            ruta.getListaAreas(), ruta.getDesechosPorPeaton(),
                            (int) Math.floor(ruta.getFlujoPeatonal().getDistribucionReal().inverseCumulativeProbability(aleatorio))));
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
                for (int k = 0; k < calle.getPuntosAcum().getNumeroPuntos(); k++) {
                    PuntosAcumulacion puntos = calle.getPuntosAcum();
                    int auxTicks = 0;
                    while (auxTicks < numTicks) {
                        double aleatorio = rand.nextDouble();
                        double tiempoEvento;
                        if (puntos.getTasaAcumulacion().getTipoDistribucion().equals("Discreta")) {
                            tiempoEvento = puntos.getTasaAcumulacion().getDistribucionDiscreta().inverseCumulativeProbability(aleatorio);
                            if (tiempoEvento + auxTicks < numTicks) {
                                auxTicks += (int) Math.floor(tiempoEvento);
                                contexto.añadirEventoAcumulacion(new DepositoDesechoEnPuntoAcumulacion(auxTicks, calle, k, diaInicial));
                            } else {
                                break;
                            }
                        } else if (puntos.getTasaAcumulacion().getTipoDistribucion().equals("Continua")) {
                            tiempoEvento = puntos.getTasaAcumulacion().getDistribucionReal().inverseCumulativeProbability(aleatorio);
                            if (Math.floor(tiempoEvento) + auxTicks < numTicks) {
                                auxTicks += (int) Math.floor(tiempoEvento);
                                contexto.añadirEventoAcumulacion(new DepositoDesechoEnPuntoAcumulacion(auxTicks, calle, k, diaInicial));
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
            for (int j = 0; j < camionesAsignados.size(); j++) {
                Camion camion = camionesAsignados.get(j);
                int[] datosHorario = horario.getDatos().get(mapaCamionesAHorarios.get(j));
                int paridadDiaInicial = diaInicial % 2;
                for (int dia = 0; dia < numeroDeDias; dia++) {
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
    
    private void listarEventosAvanceUnidad(int tickInicial, Camion camionAvanza,
            Ruta ruta, int numTicks, ContextoSimulacion contexto) {
        
        Random rand = new Random();
        int ticksAcum = tickInicial;
        
        // El camión debe estar en la calle inicial
        for (int i = 0; i < ruta.getCalles().size(); i++) {
            Calle calle = ruta.getCalles().get(i);
            int ticksParaAvanzar = 0;
            
            double velocidad = 1;
            if (calle.getVelocidad().getTipoDistribucion().equals("Discreta")) {
                velocidad = calle.getVelocidad().getDistribucionDiscreta().inverseCumulativeProbability(rand.nextDouble());
            } else if (calle.getVelocidad().getTipoDistribucion().equals("Continua")) {
                velocidad = calle.getVelocidad().getDistribucionReal().inverseCumulativeProbability(rand.nextDouble());
            }
            
            double distancia = calcularDistanciaEntrePuntos(calle.getPuntoFinal(), calle.getPuntoFinal(), ruta);
            distancia /= (calle.getPuntosAcum().getNumeroPuntos() + 1);

            ticksParaAvanzar = (int) Math.floor(distancia / velocidad);
            
            // En este caso hay que avanzar a la siguiente calle
            if (ticksAcum + ticksParaAvanzar < numTicks) {
                ticksAcum += ticksParaAvanzar;
                if (calle.getMapaCamionesPuntos().get(calle.getCamiones().indexOf(camionAvanza))
                        == calle.getPuntosAcum().getNumeroPuntos()) {
                    if (ruta.getCalles().getLast().equals(calle)) {
                        // En este caso se abandona la ruta y se va al centro de transferencia
                        contexto.añadirEventoAvanceUnidades(new SalidaRuta(ticksAcum, calle, camionAvanza));
                    } else {
                        // En este caso se va a la siguiente calle en la ruta
                        contexto.añadirEventoAvanceUnidades(new UnidadAvanza(ticksParaAvanzar,
                            camionAvanza, ruta, calle, true));
                    }
                } else {
                    // En este caso hay que avanzar al siguiente punto de acumulación
                    contexto.añadirEventoAvanceUnidades(new UnidadAvanza(ticksParaAvanzar,
                        camionAvanza, ruta, calle, false));
                    // -> Manejar aquí el listado de eventos de recolección de camión <-
                }
            }
        }
    }
    
    private double calcularDistanciaEntrePuntos(int puntoI, int puntoF, Ruta ruta) {
        Point puntoInicial = ruta.getPuntos().get(puntoI);
        Point puntoFinal = ruta.getPuntos().get(puntoF);
        return (puntoFinal.distance(puntoInicial) * escalas[ruta.getZoom()]);
    }
    
    public void ejecutar() {
        if (estadoEsValido()) {
            iniciarSimulacion();
        }
    }
    
    private void ejecutarEventos(ContextoSimulacion contexto) {
        /*LinkedList<Evento> eventosAcumulacion = contexto.getEventosAcumulacion();
        for (int i = 0; i < eventosAcumulacion.size(); i++) {
            Evento eventoAcumulacion = eventosAcumulacion.get(i);
            eventoAcumulacion.modificarEstado();
        }*/
    }

    private boolean estadoEsValido() {
        return true;
    }
}
