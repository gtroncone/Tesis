/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.io.Serializable;

/**
 *
 * @author gtroncone
 */
public class AreaBarrido implements Serializable {
    
    private int numeroBarredores;
    private int numeroCuadras;
    private double capacidad;
    private Distribucion velocidadAcopio;
    private double[] cantidadBasura;
    
    public AreaBarrido(int numeroBarredores, int numeroCuadras,
            double capacidad, Distribucion velocidadAcopio) {
        this.numeroBarredores = numeroBarredores;
        this.numeroCuadras = numeroCuadras;
        this.capacidad = capacidad;
        this.velocidadAcopio = velocidadAcopio;
        this.cantidadBasura = new double[numeroCuadras];
    }
    
    public AreaBarrido(AreaBarrido areaBarrido) {
        this.numeroBarredores = areaBarrido.getNumeroBarredores();
        this.numeroCuadras = areaBarrido.getNumeroCuadras();
        this.capacidad = areaBarrido.getCapacidad();
        this.velocidadAcopio = new Distribucion(areaBarrido.getVelocidadAcopio().getCampo());
        this.cantidadBasura = new double[areaBarrido.getNumeroCuadras()];
    }

    public int getNumeroBarredores() {
        return numeroBarredores;
    }

    public void setNumeroBarredores(int numeroBarredores) {
        this.numeroBarredores = numeroBarredores;
    }

    public int getNumeroCuadras() {
        return numeroCuadras;
    }

    public void setNumeroCuadras(int numeroCuadras) {
        this.numeroCuadras = numeroCuadras;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public Distribucion getVelocidadAcopio() {
        return velocidadAcopio;
    }

    public void setVelocidadAcopio(Distribucion velocidadAcopio) {
        this.velocidadAcopio = velocidadAcopio;
    }

    public double[] getCantidadBasura() {
        return cantidadBasura;
    }
    
    public void añadirBasuraArea(int index, double basura) {
        this.cantidadBasura[index] += basura;
    }
    
    public void restarBasuraArea(double basura) {
        double aRestar = basura;
        for (int i = 0; i < cantidadBasura.length; i++) {
            if (cantidadBasura[i] <= aRestar)  {
                double aux = cantidadBasura[i];
                cantidadBasura[i] = 0;
                aRestar -= aux;
            } else {
                cantidadBasura[i] -= aRestar;
                aRestar = 0;
            }
        }
    }
}
