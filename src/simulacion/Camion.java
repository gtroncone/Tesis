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
public class Camion implements Serializable {
    
    private String modelo;
    private int capacidad; // 0 = 15yd3, 1 = 25yd3
    private String id;
    private double precio;
    private boolean activo = false;
    private boolean averiado = false;
    private final double TASA_COMPACTACION = 650;
    private final double TASA_LIBRAS_A_KG = 0.453592;
    private double carga = 0;
    private double distanciaRecorrida = 0;
    private int numeroAverias = 0;
    private int numeroDeViajesATransferencia = 0;
    private static int CAPACIDAD_EN_YARDAS_CUBICAS_CAMION_PEQUEÑO = 15;
    private static int CAPACIDAD_EN_YARDAS_CUBICAS_CAMION_GRANDE = 25;
    
    private int numeroRutasIniciadas = 0;
    private int numeroRutasConFallas = 0;
    private double cargaTotalRecolectada = 0;
    
    private int tiempoOperacion = 0;
    private int tickEntrada;
    
    private LinkedList<Pieza> piezas;
    
    public Camion(String modelo, int capacidad, String id,
            LinkedList<Pieza> piezas, double precio) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.id = id;
        this.piezas = piezas;
        if (piezas == null) {
            this.piezas = new LinkedList<>();
        }
        this.precio = precio;
    }
    
    public Camion(Camion camion) {
        this.modelo = camion.getModelo();
        this.capacidad = camion.getCapacidad();
        this.id = camion.getId();
        this.precio = camion.getPrecio();
        
        this.piezas = new LinkedList<>();
        if (camion.getPiezas() != null) {
            for (int i = 0; i < camion.getPiezas().size(); i++) {
                this.piezas.add(new Pieza(camion.getPiezas().get(i)));
            }
        }
    }

    public boolean isAveriado() {
        return averiado;
    }

    public void setAveriado(boolean averiado) {
        this.averiado = averiado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getCapacidad() {
        return this.capacidad;
    }

    public double getCapacidadEnKg() {
        if (this.capacidad == 0) {
            return (CAPACIDAD_EN_YARDAS_CUBICAS_CAMION_PEQUEÑO * TASA_COMPACTACION * TASA_LIBRAS_A_KG);
        } else {
            return (CAPACIDAD_EN_YARDAS_CUBICAS_CAMION_GRANDE * TASA_COMPACTACION * TASA_LIBRAS_A_KG);
        }
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(LinkedList<Pieza> piezas) {
        this.piezas = piezas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }
    
    public void añadirCarga(double carga) {
        this.carga += carga;
        this.cargaTotalRecolectada += carga;
    }
    
    public void disminuirCarga(double carga) {
        this.carga -= carga;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void añadirDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida += distanciaRecorrida;
        for (Pieza pieza : piezas) {
            pieza.añadirDistanciaRecorrida(distanciaRecorrida);
        }
    }
    
    public void incrementarNumeroDeAverias() {
        this.numeroAverias++;
    }

    public int getNumeroAverias() {
        return numeroAverias;
    }
    
    public void añadirViajeATransferencia() {
        this.numeroDeViajesATransferencia++;
    }

    public int getNumeroDeViajesATransferencia() {
        return numeroDeViajesATransferencia;
    }
    
    public void añadirFallaRuta() {
        this.numeroRutasConFallas++;
    }
    
    public void añadirEntradaRuta() {
        this.numeroRutasIniciadas++;
    }

    public int getNumeroRutasIniciadas() {
        return numeroRutasIniciadas;
    }

    public int getNumeroRutasConFallas() {
        return numeroRutasConFallas;
    }

    public double getCargaTotalRecolectada() {
        return cargaTotalRecolectada;
    }

    public void setTickEntrada(int tick) {
        this.tickEntrada = tick;
    }
    
    public void setTickSalida(int tick) {
        this.tiempoOperacion += (tick - this.tickEntrada);
    }

    public int getTiempoOperacion() {
        return tiempoOperacion;
    }
           
}
