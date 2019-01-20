/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author gtroncone
 */
public class Ruta {
   
    private String nombre;
    private Horario horario;
    private LinkedList<Calle> calles;
    private Distribucion flujoPeatonal;
    private Distribucion desechosPorPeaton;
    private LinkedList<Point> puntos;
    private int zoom;
    
    public Ruta(String nombre, Horario horario, LinkedList<Calle> calles,
            Distribucion flujoPeatonal, Distribucion desechosPorPeaton,
            LinkedList<Point> puntos, int zoom) {
        this.nombre = nombre;
        this.horario = horario;
        this.calles = calles;
        this.flujoPeatonal = flujoPeatonal;
        this.desechosPorPeaton = desechosPorPeaton;
        this.puntos = puntos;
        this.zoom = zoom;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setCalles(LinkedList<Calle> calles) {
        this.calles = calles;
    }

    public void setFlujoPeatonal(String flujoPeatonal) {
        this.flujoPeatonal = new Distribucion(flujoPeatonal);
    }

    public void setDesechosPorPeaton(String desechosPorPeaton) {
        this.desechosPorPeaton = new Distribucion(desechosPorPeaton);
    }

    public void setPuntos(LinkedList<Point> puntos) {
        this.puntos = puntos;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getZoom() {
        return zoom;
    }

    public String getNombre() {
        return nombre;
    }

    public Horario getHorario() {
        return horario;
    }

    public LinkedList<Calle> getCalles() {
        return calles;
    }

    public Distribucion getFlujoPeatonal() {
        return flujoPeatonal;
    }

    public Distribucion getDesechosPorPeaton() {
        return desechosPorPeaton;
    }

    public LinkedList<Point> getPuntos() {
        return puntos;
    }
    
}
