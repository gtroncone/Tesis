/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.awt.Point;
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
            Point puntoAuxI = new Point(ruta.getPuntos().get(calle.getPuntoInicial()));
            Point puntoAuxF = new Point(ruta.getPuntos().get(calle.getPuntoFinal()));
            int factor = calle.getPuntosAcum().getNumeroPuntos() + 1;
            int x = (int) Math.floor(puntoAuxF.getX() - puntoAuxI.getX()) / factor;
            int y = (int) Math.floor(puntoAuxF.getY() - puntoAuxI.getY()) / factor;
            Point puntoAux = new Point((int) Math.floor(puntoAuxI.getX()) + x, 
                (int) Math.floor(puntoAuxF.getY()) + y);
            camion.añadirDistanciaRecorrida(
                Simulacion.calcularDistanciaEntrePuntoYTransferencia(puntoAux, ruta));

            camion.setTickSalida(tick);
        }
    }

}
