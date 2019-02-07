/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import simulacion.Camion;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class EntradaARuta extends Evento {
    
    private Camion camion;
    private Ruta ruta;

    public EntradaARuta(int tick, Camion camion, Ruta ruta) {
        super(tick);
        this.camion = camion;
        this.ruta = ruta;
    }

    @Override
    public void modificarEstado() {
        ruta.getCalles().getFirst().camionEntra(camion);
        camion.setActivo(true);
    }
    
}
