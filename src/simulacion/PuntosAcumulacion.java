/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author gtroncone
 */
public class PuntosAcumulacion implements Serializable {
    
    private final Color color;
    private Distribucion tasaAcumulacion;
    private Distribucion tasaGeneracion;
    private int[] factorHora;
    private int[] factorSemanal;
    private int numeroPuntos;
    private double[] cantidadBasura;
    private double totalGenerado = 0;
    
    public PuntosAcumulacion(Distribucion tasaAcumulacion, Distribucion tasaGeneracion,
            int[] factorHora, int[] factorSemanal, int numeroPuntos) {
        this.tasaAcumulacion = tasaAcumulacion;
        this.tasaGeneracion = tasaGeneracion;
        this.factorHora = factorHora;
        this.factorSemanal = factorSemanal;
        this.numeroPuntos = numeroPuntos;
        color = new Color(128 * 1000000 + 128);
        this.cantidadBasura = new double[this.numeroPuntos];
    }
    
    public PuntosAcumulacion(PuntosAcumulacion puntosAcumulacion) {
        this.color = new Color(puntosAcumulacion.getColor().getRGB());
        this.tasaAcumulacion = new Distribucion(puntosAcumulacion.getTasaAcumulacion().getCampo(),
            puntosAcumulacion.getTasaAcumulacion().getFactor());
        this.tasaGeneracion = new Distribucion(puntosAcumulacion.getTasaGeneracion().getCampo(),
            puntosAcumulacion.getTasaGeneracion().getFactor());
        
        this.factorHora = new int[puntosAcumulacion.getFactorHora().length];
        for (int i = 0; i < puntosAcumulacion.getFactorHora().length; i++) {
            this.factorHora[i] = puntosAcumulacion.getFactorHora()[i];
        }
        
        this.factorSemanal = new int[puntosAcumulacion.getFactorSemanal().length];
        for (int i = 0; i < puntosAcumulacion.getFactorSemanal().length; i++) {
            this.factorSemanal[i] = puntosAcumulacion.getFactorSemanal()[i];
        }
        
        this.numeroPuntos = puntosAcumulacion.getNumeroPuntos();
        this.cantidadBasura = new double[this.numeroPuntos];
    }

    public Color getColor() {
        return color;
    }

    public Distribucion getTasaAcumulacion() {
        return tasaAcumulacion;
    }

    public void setTasaAcumulacion(Distribucion tasaAcumulacion) {
        this.tasaAcumulacion = tasaAcumulacion;
    }

    public Distribucion getTasaGeneracion() {
        return tasaGeneracion;
    }

    public void setTasaGeneracion(Distribucion tasaGeneracion) {
        this.tasaGeneracion = tasaGeneracion;
    }

    public int[] getFactorHora() {
        return factorHora;
    }

    public void setFactorHora(int[] factorHora) {
        this.factorHora = factorHora;
    }

    public int[] getFactorSemanal() {
        return factorSemanal;
    }

    public void setFactorSemanal(int[] factorSemanal) {
        this.factorSemanal = factorSemanal;
    }

    public int getNumeroPuntos() {
        return numeroPuntos;
    }

    public void setNumeroPuntos(int numeroPuntos) {
        this.numeroPuntos = numeroPuntos;
    }

    public double[] getCantidadBasura() {
        return cantidadBasura;
    }
    
    public void acumularCantidadBasuraPunto(int index, double cantidad) {
        this.cantidadBasura[index] += cantidad;
        this.totalGenerado += cantidad;
    }
    
    public double getTotalGenerado() {
        return this.totalGenerado;
    }
    
    public double getCantidadBasuraPunto(int index) {
        return this.cantidadBasura[index];
    }
    
    public void disminuirCantidadBasuraPunto(int index, double cantidad) {
        this.cantidadBasura[index] -= cantidad;
    }
}
