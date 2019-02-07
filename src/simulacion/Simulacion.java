/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;
import simulacion.eventos.AcumulacionDesechoPeatonal;
import simulacion.eventos.DepositoDesechoEnPuntoAcumulacion;
import simulacion.eventos.EntradaARuta;

/**
 *
 * @author gtroncone
 */
public class Simulacion implements Serializable {

    private LinkedList<Ruta> rutas;
    private LinkedList<Camion> camiones;

    private String[] horarioASimular;
    private int numRepeticiones = 5;
    private double salarioBarredores = 0;
    private double salarioEquipoRecoleccion = 0;
    private int numMecanicos = 0;
    private double salarioMecanicos = 0;
    private int tipoDeMantenimiento;
    private int numeroDeDias = 1;
    private int diaInicial = 1;

    public Simulacion() {
        rutas = new LinkedList<>();
        camiones = new LinkedList<>();
        horarioASimular = new String[2];
        horarioASimular[0] = "x";
        horarioASimular[1] = "x";
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

    public String[] getHorarioASimular() {
        return horarioASimular;
    }

    public void setHorarioASimular(String[] horarioASimular) {
        this.horarioASimular = horarioASimular;
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
        if (horarioASimular[0].toLowerCase().equals("x")
                || horarioASimular[1].toLowerCase().equals("x")) {
            return 60 * 24 * numeroDeDias;
        }
        int horaInicial = Integer.parseInt(horarioASimular[0]);
        int horaFinal = Integer.parseInt(horarioASimular[1]);
        if (horaInicial < horaFinal) {
            return (horaFinal - horaInicial) * 60 * numeroDeDias;
        } else {
            return (24 - (horaInicial - horaFinal)) * 60 * numeroDeDias;
        }
    }

    private void cicloPrincipal(ContextoSimulacion[] contextos) {
        int numTicks = determinarNumTicks();
        for (ContextoSimulacion contexto : contextos) {
            listarEventosAcumulacionDesechos(contexto, numTicks);
            listarEventosAcumulacionDesechoPeatonal(contexto, numTicks);
            listarEventosEntradaARuta(contexto);
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
                                contexto.añadirEventoAcumulacion(new DepositoDesechoEnPuntoAcumulacion(auxTicks, calle, k, diaInicial, horarioASimular));
                            } else {
                                break;
                            }
                        } else if (puntos.getTasaAcumulacion().getTipoDistribucion().equals("Continua")) {
                            tiempoEvento = puntos.getTasaAcumulacion().getDistribucionReal().inverseCumulativeProbability(aleatorio);
                            if (Math.floor(tiempoEvento) + auxTicks < numTicks) {
                                auxTicks += (int) Math.floor(tiempoEvento);
                                contexto.añadirEventoAcumulacion(new DepositoDesechoEnPuntoAcumulacion(auxTicks, calle, k, diaInicial, horarioASimular));
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Si la simulación no incluye el horario de entrada de los camiones, no se simula la recolección
     * @param contexto 
     */
    private void listarEventosEntradaARuta(ContextoSimulacion contexto) {
        for (int i = 0; i < contexto.getRutas().size(); i++) {
            Ruta ruta = contexto.getRutas().get(i);
            Horario horario = ruta.getHorario();
            
            LinkedList<Camion> camionesAsignados = horario.getCamionesAsignados();
            LinkedList<Integer> mapaCamionesAHorarios = horario.getMapaCamionHorarios();
            for (int j = 0; j < camionesAsignados.size(); j++) {
                Camion camion = camionesAsignados.get(j);
                int[] datosHorario = horario.getDatos().get(mapaCamionesAHorarios.get(j));
                if (horarioEstaIncluido(datosHorario)) {
                    int paridadDiaInicial = diaInicial % 2;
                    for (int dia = 0; dia < numeroDeDias; dia++) {
                        if (datosHorario[2] == 0 || dia % 2 == paridadDiaInicial) {
                            int tick = 0;
                            if (horarioASimular[0].equals("x") || horarioASimular[1].equals("x")) {
                                tick += dia * 24 * 60;
                                tick += (datosHorario[0] * 60) + datosHorario[1];
                            } else {
                                int horaInicial = Integer.parseInt(horarioASimular[0]);
                                int horaFinal = Integer.parseInt(horarioASimular[1]);
                                tick += (dia * ((horaFinal - horaInicial) * 60));
                                tick += (datosHorario[0] - horaInicial) * 60;
                                tick += (datosHorario[1]);
                            }
                            contexto.añadirEventoEntradaUnidad(new EntradaARuta(tick, camion, ruta));
                        }
                    }
                }
            }
        }
    }
    
    public void ejecutar() {
        if (estadoEsValido()) {
            iniciarSimulacion();
        }
    }
    
    private boolean horarioEstaIncluido(int[] datosHorario) {
        if (horarioASimular[0].equals("x")
            || horarioASimular[1].toLowerCase().equals("x")) {
            return true;
        } else {
            String[] parsedHora = horarioASimular[0].split(":");
            int horaInicialSim = Integer.parseInt(parsedHora[0]);
            int horaFinalSim = Integer.parseInt(parsedHora[1]);
            return (datosHorario[0] >= horaInicialSim &&
                    datosHorario[0] < horaFinalSim);
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
