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
public class Calle {
    
    private String nombre;
    private Distribucion velocidad;
    private int puntoInicial;
    private int puntoFinal;
    private Color color;
    private PuntosAcumulacion puntosAcum;

    public Calle(String nombre, Distribucion velocidad,
            int puntoInicial, int puntoFinal,
            Color color) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
        this.color = color;
    }
    
    public Calle(Calle calle) {
        this.nombre = calle.getNombre();
        this.velocidad = new Distribucion(calle.getVelocidad().getCampo());
        this.puntoInicial = calle.getPuntoInicial();
        this.puntoFinal = calle.getPuntoFinal();
        this.color = new Color(calle.getColor().getGreen());
        this.puntosAcum = new PuntosAcumulacion(calle.getPuntosAcum());
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

    public int getPuntoInicial() {
        return puntoInicial;
    }

    public void setPuntoInicial(int puntoInicial) {
        this.puntoInicial = puntoInicial;
    }

    public int getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(int puntoFinal) {
        this.puntoFinal = puntoFinal;
    }
    
    public PuntosAcumulacion getPuntosAcum() {
        return puntosAcum;
    }
    
    public void setPuntosAcum(PuntosAcumulacion puntosAcum) {
        this.puntosAcum = puntosAcum;
    }
}
