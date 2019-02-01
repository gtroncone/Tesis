/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.util.LinkedList;

/**
 *
 * @author gtroncone
 */
public class Camion {
    
    private String modelo;
    private int capacidad; // 0 = 15yd3, 1 = 25yd3
    private String id;
    private double precio;
    
    private LinkedList<Pieza> piezas;
    
    public Camion(String modelo, int capacidad, String id,
            LinkedList<Pieza> piezas, double precio) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.id = id;
        this.piezas = piezas;
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
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
    
}
