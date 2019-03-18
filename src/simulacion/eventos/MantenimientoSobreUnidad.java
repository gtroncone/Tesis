/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.util.LinkedList;
import simulacion.Camion;
import simulacion.Pieza;

/**
 *
 * @author gtroncone
 */
public class MantenimientoSobreUnidad extends Evento {

    private final Camion camion;
    private final int tipoDeMantenimiento;
    
    private final double PROBABILIDAD_MANTENIMIENTO_ALT_PREVENTIVO = 0.1;
    private final double PROBABILIDAD_MANTENIMIENTO_PREVENTIVO = 0.5;
    
    public MantenimientoSobreUnidad(int tick, Camion camion, int tipoDeMantenimiento) {
        super(tick);
        this.camion = camion;
        this.tipoDeMantenimiento = tipoDeMantenimiento;
    }

    @Override
    public void modificarEstado() {
        //System.out.println("Se va a ejecutar un evento de mantenimiento sobre unidad en el tick " + tick);
        LinkedList<Pieza> piezas = camion.getPiezas();
        for (Pieza pieza : piezas) {
            double distanciaLimite;
            switch (tipoDeMantenimiento) {
                case 0:
                    distanciaLimite = pieza.getTiempoDeVida().evaluarDistribucionInversa(PROBABILIDAD_MANTENIMIENTO_ALT_PREVENTIVO);
                    break;
                case 1:
                    distanciaLimite = pieza.getTiempoDeVida().evaluarDistribucionInversa(PROBABILIDAD_MANTENIMIENTO_PREVENTIVO);
                    break;
                case 2:
                    distanciaLimite = Double.MAX_VALUE;
                    break;
                default:
                    distanciaLimite = Double.MAX_VALUE;
            }
            if (camion.getDistanciaRecorrida() > distanciaLimite || pieza.isEstaAveriada()) {
                pieza.cambiarPieza();
            }
        }
    }
    
}
