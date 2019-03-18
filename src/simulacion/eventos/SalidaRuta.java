/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import simulacion.Calle;
import simulacion.Camion;
import simulacion.Ruta;
import simulacion.Simulacion;

/**
 *
 * @author gtroncone
 */
public class SalidaRuta extends Evento {

    private final Calle calle;
    private final Camion camion;
    private final Ruta ruta;

    public SalidaRuta(int tick, Calle calle, Camion camion, Ruta ruta) {
        super(tick);
        this.calle = calle;
        this.camion = camion;
        this.ruta = ruta;
    }

    @Override
    public void modificarEstado() {
        //System.out.println("Se va a ejecutar un evento de salida de ruta en el tick " + tick);
        if (!camion.isAveriado()) {
            calle.camionSale(camion);
            camion.setActivo(false);
            camion.añadirViajeATransferencia();
            camion.añadirDistanciaRecorrida(
                Simulacion.calcularDistanciaEntrePuntoYTransferencia(calle.getPuntoFinal(), ruta));

            camion.setTickSalida(tick);
        }
    }

}
