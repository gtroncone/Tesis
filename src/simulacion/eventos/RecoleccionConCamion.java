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
public class RecoleccionConCamion extends Evento {

    private final Calle calle;
    private final Camion camion;
    private final Ruta ruta;
    
    public RecoleccionConCamion(int tick, Calle calle, Camion camion, Ruta ruta) {
        super(tick);
        this.calle = calle;
        this.camion = camion;
        
        this.ruta = ruta;
    }

    @Override
    public void modificarEstado() {
        //System.out.println("Se va a ejecutar un evento recolección con camión en el tick " + tick);
        if (!camion.isAveriado()) {
            int puntoActual = calle.getMapaCamionesPuntos().get(calle.getCamiones().indexOf(camion)) - 1;
            double cantidadBasuraPunto = calle.getPuntosAcum().getCantidadBasuraPunto(puntoActual);
            if (camion.getCarga() + cantidadBasuraPunto <= camion.getCapacidadEnKg()) {
                calle.getPuntosAcum().disminuirCantidadBasuraPunto(puntoActual, cantidadBasuraPunto);
                camion.añadirCarga(cantidadBasuraPunto);
            } else {
                double diferencia = camion.getCapacidadEnKg() - camion.getCarga();
                calle.getPuntosAcum().disminuirCantidadBasuraPunto(puntoActual, diferencia);
                camion.añadirCarga(diferencia);
                camion.setActivo(false);
                camion.añadirDistanciaRecorrida(
                    2 * Simulacion.calcularDistanciaEntrePuntoYTransferencia(puntoActual, ruta));
                camion.añadirViajeATransferencia();
            }
        }
    }
    
}
