/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.metricas;

import java.io.Serializable;
import java.util.LinkedList;
import simulacion.ContextoSimulacion;

/**
 *
 * @author gtroncone
 */
public abstract class Metrica implements Serializable {
    
    private String nombre;
    private LinkedList<String> subtitulos;
    private String unidades = "";
    
    public Metrica(String nombre) {
        this.nombre = nombre;
    }
    
    public Metrica(String nombre, String unidades) {
        this.nombre = nombre;
        this.unidades = unidades;
    }
    
    public abstract void evaluar(ContextoSimulacion contexto);
    
    public abstract Object getResultado();
    
    public abstract boolean isIterable();
    
    public String getNombre() {
        return this.nombre;
    }

    public LinkedList<String> getSubtitulos() {
        return subtitulos;
    }
    
    public String getUnidades() {
        return this.unidades;
    }
    
    public void setSubtitulos(LinkedList<String> subtitulos) {
        this.subtitulos = subtitulos;
    }
}
