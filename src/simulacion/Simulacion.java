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

    public void ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
