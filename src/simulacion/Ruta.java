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
    private LinkedList<AreaBarrido> listaAreas;
    
    private int tipoDeRecoleccion; // 0 = esquina a esquina, 1 = puerta a puerta
    private int zoom;
    
    public Ruta(String nombre, Horario horario, LinkedList<Calle> calles,
            Distribucion flujoPeatonal, Distribucion desechosPorPeaton,
            LinkedList<Point> puntos, int zoom, int tipoDeRecoleccion) {
        this.nombre = nombre;
        this.horario = horario;
        this.calles = calles;
        this.flujoPeatonal = flujoPeatonal;
        this.desechosPorPeaton = desechosPorPeaton;
        this.puntos = puntos;
        this.zoom = zoom;
        this.tipoDeRecoleccion = tipoDeRecoleccion;
    }
    
    public Ruta(Ruta ruta) {
        this.nombre = ruta.getNombre();
        this.horario = new Horario(ruta.getHorario());
        
        this.calles = new LinkedList<>();
        
        for (int i = 0; i < ruta.getCalles().size(); i++) {
            this.calles.add(new Calle(ruta.getCalles().get(i)));
        }
        
        this.flujoPeatonal = new Distribucion(ruta.getFlujoPeatonal().getCampo());
        this.desechosPorPeaton = new Distribucion(ruta.getDesechosPorPeaton().getCampo());
        
        this.puntos = new LinkedList<>();
        
        for (int i = 0; i < ruta.getPuntos().size(); i++) {
            this.puntos.add(new Point(ruta.getPuntos().get(i)));
        }
        
        this.listaAreas = new LinkedList<>();
        
        for (int i = 0; i < ruta.getListaAreas().size(); i++) {
            this.listaAreas.add(new AreaBarrido(ruta.getListaAreas().get(i)));
        }
        
        this.tipoDeRecoleccion = ruta.getTipoDeRecoleccion();
        this.zoom = ruta.getZoom();
    }

    public int getTipoDeRecoleccion() {
        return tipoDeRecoleccion;
    }

    public void setTipoDeRecoleccion(int tipoDeRecoleccion) {
        this.tipoDeRecoleccion = tipoDeRecoleccion;
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
    
    public LinkedList<AreaBarrido> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(LinkedList<AreaBarrido> listaAreas) {
        this.listaAreas = listaAreas;
    }
    
    public void añadirArea(AreaBarrido area) {
        if (listaAreas == null) {
            listaAreas = new LinkedList<>();
        }
        listaAreas.add(area);
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
