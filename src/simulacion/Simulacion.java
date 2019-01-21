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
public class Simulacion {
    
    private LinkedList<Ruta> rutas;
    private LinkedList<Camion> camiones;
    
    public Simulacion() {
        rutas = new LinkedList<>();
        camiones = new LinkedList<>();
    }
    
    public void añadirRuta(Ruta ruta) {
        rutas.add(ruta);
    }
    
    public LinkedList<Ruta> getRutas() {
        return rutas;
    }
    
    public LinkedList<Camion> getCamiones() {
        return camiones;
    }
    
    public void añadirCamion(Camion camion) {
        camiones.add(camion);
    }
}
