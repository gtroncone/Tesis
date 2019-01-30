/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Color;

/**
 *
 * @author gtroncone
 */
public class PuntosAcumulacion {
    
    private final Color color;
    private Distribucion tasaAcumulacion;
    private Distribucion tasaGeneracion;
    private int[] factorHora;
    private int[] factorSemanal;
    private int numeroPuntos;
    
    public PuntosAcumulacion(Distribucion tasaAcumulacion, Distribucion tasaGeneracion,
            int[] factorHora, int[] factorSemanal, int numeroPuntos) {
        this.tasaAcumulacion = tasaAcumulacion;
        this.tasaGeneracion = tasaGeneracion;
        this.factorHora = factorHora;
        this.factorSemanal = factorSemanal;
        this.numeroPuntos = numeroPuntos;
        color = new Color(128 * 1000000 + 128);
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
}