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
        //System.out.println("Se va a ejecutar un evento de avance de unidad en el tick " + tick);
        if (!camion.isAveriado()) {
            this.camion.setActivo(true);
            if (cambioDeCalle) {
                // Pasa a la siguiente calle
                int indexCalle = ruta.getCalles().indexOf(calle);
                Calle proximaCalle = ruta.getCalles().get(indexCalle + 1);
                proximaCalle.camionEntra(camion);
                calle.camionSale(camion);
            } else {
                calle.camionAvanzaASiguientePunto(camion);
            }
            int numPuntos = 0;
            if (calle.getPuntosAcum() != null) {
                numPuntos = calle.getPuntosAcum().getNumeroPuntos();
            }
            double distanciaCalle = Simulacion.calcularDistanciaEntrePuntos(
                calle.getPuntoInicial(), 
                calle.getPuntoFinal(), ruta);
            double distanciaRecorrida = distanciaCalle / (numPuntos + 1);
            camion.añadirDistanciaRecorrida(distanciaRecorrida);
        }
    }
    
}
