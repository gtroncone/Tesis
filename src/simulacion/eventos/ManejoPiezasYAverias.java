/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.util.LinkedList;
import java.util.Random;
import simulacion.Calle;
import simulacion.Camion;
import simulacion.Pieza;
import simulacion.Simulacion;

/**
 *
 * @author gtroncone
 */
public class ManejoPiezasYAverias extends Evento {

    private final Camion camion;
    private final Calle calle;

    // Añadir mismo efecto de salida de ruta
    public ManejoPiezasYAverias(int tick, Camion camion, Calle calle) {
        super(tick);
        this.camion = camion;
        this.calle = calle;
    }

    @Override
    public void modificarEstado() {
        //System.out.println("Se va a ejecutar un evento de manejo de piezas y averías en el tick " + tick);
        Random rand = new Random();
        if (!camion.isAveriado()) {
            LinkedList<Pieza> piezas = camion.getPiezas();
            if (piezas != null && !piezas.isEmpty()) {
                for (Pieza pieza : piezas) {
                    if (pieza.getTiempoDeVida().evaluarDistribucion(pieza.getDistanciaRecorrida())
                            > rand.nextDouble()) {
                        pieza.setEstaAveriada(true);
                        if (pieza.isOcasionaFallaCritica()) {
                            camion.setAveriado(true);
                            camion.setActivo(false);
                            camion.añadirFallaRuta();
                            calle.camionSale(camion);
                            camion.setActivo(false);
                            camion.setTickSalida(tick);
                        }
                    }
                }
            }
        }
    }

}
