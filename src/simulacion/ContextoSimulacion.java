/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.util.LinkedList;
import simulacion.eventos.Evento;

/**
 *
 * @author gtroncone
 */
public class ContextoSimulacion {

    private LinkedList<Ruta> rutas;
    private LinkedList<Camion> camiones;
    
    private LinkedList<Evento> eventosAcumulacion;
    private LinkedList<Evento> eventosEntradaUnidades;
    
    public ContextoSimulacion(LinkedList<Ruta> rutas, LinkedList<Camion> camiones) {
        this.rutas = new LinkedList<>();
        this.camiones = new LinkedList<>();        
        eventosAcumulacion = new LinkedList<>();
        eventosEntradaUnidades = new LinkedList<>();
        for (int i = 0; i < camiones.size(); i++) {
            this.camiones.add(new Camion(camiones.get(i)));
        }
        
        for (int i = 0; i < rutas.size(); i++) {
            this.rutas.add(new Ruta(rutas.get(i)));
            this.rutas.get(i).getHorario().reconciliarCamiones(rutas.get(i), this.camiones);
        }        
    }
    
    public void añadirEventoAcumulacion(Evento evento) {
        eventosAcumulacion.add(evento);
    }
    
    public void añadirEventoEntradaUnidad(Evento evento) {
        eventosEntradaUnidades.add(evento);
    }

    public LinkedList<Ruta> getRutas() {
        return rutas;
    }

    public LinkedList<Camion> getCamiones() {
        return camiones;
    }

    public LinkedList<Evento> getEventosAcumulacion() {
        return eventosAcumulacion;
    }
    
    
}
