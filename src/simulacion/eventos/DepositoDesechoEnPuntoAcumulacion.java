/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.util.Random;
import simulacion.Calle;
import simulacion.Distribucion;

/**
 *
 * @author gtroncone
 */
public class DepositoDesechoEnPuntoAcumulacion extends Evento {
    
    private final Calle callePuntoAcumulacion;
    private final int numeroPuntoAcumulacion;
    private final int diaInicial;
    private final int[] factorHora;
    private final int[] factorSemanal;
    
    public DepositoDesechoEnPuntoAcumulacion(int tick, Calle calle, int k, int diaInicial,
        int[] factorHora, int[] factorSemanal) {
        super(tick);
        this.callePuntoAcumulacion = calle;
        this.numeroPuntoAcumulacion = k;
        this.diaInicial = diaInicial;
        this.factorHora = factorHora;
        this.factorSemanal = factorSemanal;
    }

    public Calle getCallePuntoAcumulacion() {
        return callePuntoAcumulacion;
    }

    public int getNumeroPuntoAcumulacion() {
        return numeroPuntoAcumulacion;
    }

    @Override
    public void modificarEstado() {
        Random rand = new Random();
        Distribucion dist = callePuntoAcumulacion.getPuntosAcum().getTasaGeneracion();
        if (dist.esDistribucionEspecial()) {
            
        } else {
            callePuntoAcumulacion.getPuntosAcum().acumularCantidadBasuraPunto(numeroPuntoAcumulacion,
                dist.evaluarDistribucionInversa(rand.nextDouble()));   
        }
    }
    
}
