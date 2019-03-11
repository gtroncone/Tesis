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
public class Pieza implements Serializable {
    
    private String nombre;
    private double costo;
    private Distribucion tiempoDeVida;
    private boolean ocasionaFallaCritica;
    private boolean estaAveriada = false;
    private int numeroDeAverias = 0;
    
    private int numeroCambios = 0;
    private double distanciaRecorrida = 0;
    
    public Pieza(String nombre, double costo, Distribucion tiempoDeVida,
            boolean ocasionaFallaCritica) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempoDeVida = tiempoDeVida;
        this.ocasionaFallaCritica = ocasionaFallaCritica;
    }
    
    public Pieza(Pieza pieza) {
        this.nombre = pieza.getNombre();
        this.costo = pieza.getCosto();
        this.tiempoDeVida = new Distribucion(pieza.getTiempoDeVida().getCampo());
        this.ocasionaFallaCritica = pieza.isOcasionaFallaCritica();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Distribucion getTiempoDeVida() {
        return tiempoDeVida;
    }

    public void setTiempoDeVida(Distribucion tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
    }

    public boolean isOcasionaFallaCritica() {
        return ocasionaFallaCritica;
    }

    public void setOcasionaFallaCritica(boolean ocasionaFallaCritica) {
        this.ocasionaFallaCritica = ocasionaFallaCritica;
    }
    
    public void cambiarPieza() {
        this.numeroCambios++;
        resetearDistanciaRecorrida();
    }
    
    public int getNumeroCambios() {
        return numeroCambios;
    }

    public boolean isEstaAveriada() {
        return estaAveriada;
    }

    public void setEstaAveriada(boolean estaAveriada) {
        this.estaAveriada = estaAveriada;
        this.numeroDeAverias++;
    }
               
    public void añadirDistanciaRecorrida(double distancia) {
        this.distanciaRecorrida += distancia;
    }
    
    public void resetearDistanciaRecorrida() {
        this.distanciaRecorrida = 0;
    }

    public int getNumeroDeAverias() {
        return numeroDeAverias;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
    
}
