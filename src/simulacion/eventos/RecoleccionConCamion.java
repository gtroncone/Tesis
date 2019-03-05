/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import simulacion.Calle;
import simulacion.Camion;
import simulacion.ContextoSimulacion;
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
    private final int puntoInicial;
    private boolean idaATransferencia = false;
    
    public RecoleccionConCamion(int tick, Calle calle, Camion camion, Ruta ruta) {
        super(tick);
        this.calle = calle;
        this.camion = camion;
        
        int puntoActual = calle.getMapaCamionesPuntos().get(calle.getMapaCamionesPuntos().indexOf(camion));
        double cantidadBasuraPunto = calle.getPuntosAcum().getCantidadBasuraPunto(puntoActual);
        this.idaATransferencia = !(camion.getCarga() + cantidadBasuraPunto <= camion.getCapacidadEnKg());
        this.puntoInicial = calle.getMapaCamionesPuntos().get(calle.getMapaCamionesPuntos().indexOf(camion));
        this.ruta = ruta;
    }

    /**
     * Algoritmo:
     * Si se puede vaciar el punto de acumulación, se vacía.
     * De lo contrario, se disminuye lo que se puede, en cuyo caso:
     * 1. El camión sale de la ruta para ir al centro de transferencia
     * 2. El camión regresa empezando por el siguiente punto de acumulación,
     * una cierta cantidad de ticks después
    */
    @Override
    public void modificarEstado() {
        int puntoActual = calle.getMapaCamionesPuntos().get(calle.getMapaCamionesPuntos().indexOf(camion));
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
    
    public boolean hayIdaATransferencia() {
        return this.idaATransferencia;
    }
    
    public int getPuntoInicial() {
        return this.getPuntoInicial();
    }
    
}
