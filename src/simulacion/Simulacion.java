/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.io.Serializable;
import java.util.LinkedList;

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
    
    public void iniciarSimulacion() {
        ContextoSimulacion[] instancias = new ContextoSimulacion[numRepeticiones];
        int numTicks = determinarNumTicks();
        for (int i = 0; i < instancias.length; i++) {
            instancias[i] = new ContextoSimulacion(numTicks);
        }
        cicloPrincipal(instancias);
    }
    
    private int determinarNumTicks() {
        if (horarioASimular[0].toLowerCase().equals("x") ||
                horarioASimular[1].toLowerCase().equals("x")) {
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
    
    public void cicloPrincipal(ContextoSimulacion[] contextos) {
        for (int i = 0; i < contextos.length; i++) {
            ContextoSimulacion contexto = contextos[i];
            for (int j = 0; j < contexto.getNumeroTicks(); j++) {
                contexto.incrementarTick();
                
            }
        }
    }

    public void ejecutar() {
        if (estadoEsValido()) {
            iniciarSimulacion();
        }
    }
    
    private boolean estadoEsValido() {
        return true;
    }
}
