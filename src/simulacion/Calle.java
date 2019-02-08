/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Color;
import java.util.LinkedList;

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
    
    private LinkedList<Camion> camiones;
    private LinkedList<Integer> mapaCamionesPuntos;

    public Calle(String nombre, Distribucion velocidad,
            int puntoInicial, int puntoFinal,
            Color color) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
        this.color = color;
        this.camiones = new LinkedList<>();
        this.mapaCamionesPuntos = new LinkedList<>();
    }
    
    public Calle(Calle calle) {
        this.nombre = calle.getNombre();
        this.velocidad = new Distribucion(calle.getVelocidad().getCampo());
        this.puntoInicial = calle.getPuntoInicial();
        this.puntoFinal = calle.getPuntoFinal();
        this.color = new Color(calle.getColor().getGreen());
        this.puntosAcum = new PuntosAcumulacion(calle.getPuntosAcum());
        this.camiones = new LinkedList<>();
        this.mapaCamionesPuntos = new LinkedList<>();
    }

    public LinkedList<Camion> getCamiones() {
        return camiones;
    }
    
    public void camionEntra(Camion camion) {
        this.camiones.add(camion);
        this.mapaCamionesPuntos.add(0);
    }
    
    public void camionSale(Camion camion) {
        this.mapaCamionesPuntos.remove(this.camiones.indexOf(camion));
        this.camiones.remove(camion);
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

    public LinkedList<Integer> getMapaCamionesPuntos() {
        return mapaCamionesPuntos;
    }
    
    public void camionAvanzaASiguientePunto(Camion camion) {
        int index = this.camiones.indexOf(camion);
        this.mapaCamionesPuntos.set(index, this.mapaCamionesPuntos.get(index) + 1);
    }
}
