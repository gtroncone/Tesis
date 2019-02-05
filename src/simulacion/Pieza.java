/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

/**
 *
 * @author gtroncone
 */
public class Pieza {
    
    private String nombre;
    private double costo;
    private Distribucion tiempoDeVida;
    private int cantidadPorCamion;
    private boolean ocasionaFallaCritica;
    
    public Pieza(String nombre, double costo, Distribucion tiempoDeVida,
            int cantidadPorCamion, boolean ocasionaFallaCritica) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempoDeVida = tiempoDeVida;
        this.cantidadPorCamion = cantidadPorCamion;
        this.ocasionaFallaCritica = ocasionaFallaCritica;
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

    public int getCantidadPorCamion() {
        return cantidadPorCamion;
    }

    public void setCantidadPorCamion(int cantidadPorCamion) {
        this.cantidadPorCamion = cantidadPorCamion;
    }

    public boolean isOcasionaFallaCritica() {
        return ocasionaFallaCritica;
    }

    public void setOcasionaFallaCritica(boolean ocasionaFallaCritica) {
        this.ocasionaFallaCritica = ocasionaFallaCritica;
    }
    
}
