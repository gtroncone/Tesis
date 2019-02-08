/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import simulacion.Calle;
import simulacion.Camion;

/**
 *
 * @author gtroncone
 */
public class SalidaRuta extends Evento {

    private Calle calle;
    private Camion camion;
    
    public SalidaRuta(int tick, Calle calle, Camion camion) {
        super(tick);
        this.calle = calle;
        this.camion = camion;
    }

    @Override
    public void modificarEstado() {
        calle.camionSale(camion);
        camion.setActivo(false);
    }
    
}
