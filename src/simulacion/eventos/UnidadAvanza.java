/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import simulacion.Calle;
import simulacion.Camion;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class UnidadAvanza extends Evento {

    private final Camion camion;
    private final Calle calle;
    private final Ruta ruta;
    private final boolean cambioDeCalle;
    
    public UnidadAvanza(int tick, Camion camion, Ruta ruta, Calle calle, boolean cambioDeCalle) {
        super(tick);
        this.camion = camion;
        this.calle = calle;
        this.cambioDeCalle = cambioDeCalle;
        this.ruta = ruta;
    }

    @Override
    public void modificarEstado() {
        if (cambioDeCalle) {
            // Pasa a la siguiente calle
            int indexCalle = ruta.getCalles().indexOf(calle);
            Calle proximaCalle = ruta.getCalles().get(indexCalle + 1);
            proximaCalle.camionEntra(camion);
            calle.camionSale(camion);
        } else {
            calle.camionAvanzaASiguientePunto(camion);
        }
    }
    
}
