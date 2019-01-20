/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author gtroncone
 */
public class Calle {
    
    private String nombre;
    private Distribucion velocidad;
    private Point puntoInicial;
    private Point puntoFinal;
    private Color color;
    
    public Calle(String nombre, Distribucion velocidad,
            Point puntoInicial, Point puntoFinal,
            Color color) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Distribucion getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Distribucion velocidad) {
        this.velocidad = velocidad;
    }

    public Point getPuntoInicial() {
        return puntoInicial;
    }

    public void setPuntoInicial(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
    }

    public Point getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
    }
    
}
