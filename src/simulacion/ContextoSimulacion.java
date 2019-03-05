/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.util.Collections;
import java.util.LinkedList;
import simulacion.eventos.Evento;

/**
 *
 * @author gtroncone
 */
public class ContextoSimulacion {

    private final LinkedList<Ruta> rutas;
    private final LinkedList<Camion> camiones;
    
    private final LinkedList<Evento> eventosAcumulacion;
    private final LinkedList<Evento> eventosEntradaUnidades;
    private final LinkedList<Evento> eventosAvanceUnidades;
    private final LinkedList<Evento> eventosRecoleccion;
    private final LinkedList<Evento> eventosAcopiacion;
    
    private final LinkedList<LinkedList<Evento>> listaEventos;
    private final LinkedList<Evento> listaAuditoria;
    
    public ContextoSimulacion(LinkedList<Ruta> rutas, LinkedList<Camion> camiones) {
        this.rutas = new LinkedList<>();
        this.camiones = new LinkedList<>();        
        eventosAcumulacion = new LinkedList<>();
        eventosEntradaUnidades = new LinkedList<>();
        eventosAvanceUnidades = new LinkedList<>();
        eventosRecoleccion = new LinkedList<>();
        eventosAcopiacion = new LinkedList<>();
        
        listaEventos = new LinkedList<LinkedList<Evento>>();
        listaEventos.add(eventosAcumulacion);
        listaEventos.add(eventosEntradaUnidades);
        listaEventos.add(eventosAvanceUnidades);
        listaEventos.add(eventosRecoleccion);
        listaEventos.add(eventosAcopiacion);
        
        listaAuditoria = new LinkedList<Evento>();
        
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
    
    public void añadirEventoAvanceUnidades(Evento evento) {
        eventosAvanceUnidades.add(evento);
    }
    
    public void añadirEventoRecoleccion(Evento evento) {
        eventosRecoleccion.add(evento);
    }
    
    public void añadirEventoAcopiacion(Evento evento) {
        eventosAcopiacion.add(evento);
    }

    public LinkedList<Ruta> getRutas() {
        return rutas;
    }

    public LinkedList<Camion> getCamiones() {
        return camiones;
    }

    public void sortEventos() {
        for (int i = 0; i < listaEventos.size(); i++) {
            Collections.sort(listaEventos.get(i));
        }
    }

    public LinkedList<Evento> getListaAuditoria() {
        return listaAuditoria;
    }
    
    public void ejecutarEventos() {
        boolean aux = true;
        int minTick;
        while (aux) {
            minTick = Integer.MAX_VALUE;
            boolean estanVacias = true;
            for (int i = 0; i < listaEventos.size(); i++) {
                estanVacias &= listaEventos.get(i).isEmpty();
                if (!listaEventos.get(i).isEmpty()
                    && listaEventos.get(i).peek().getTick() < minTick) {
                    minTick = listaEventos.get(i).peek().getTick();
                }
            }
            for (int i = 0; i < listaEventos.size(); i++) {
                if (!listaEventos.get(i).isEmpty()) {
                    Evento e =  listaEventos.get(i).peek();
                    if (e.getTick() == minTick) {
                        e.modificarEstado();
                        listaEventos.get(i).removeFirst();
                        listaAuditoria.add(e);
                    }
                }
            }
            aux = !estanVacias;
        }
    }
}
