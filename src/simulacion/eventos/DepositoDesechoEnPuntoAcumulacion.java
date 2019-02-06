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
    
    public DepositoDesechoEnPuntoAcumulacion(int tick, Calle calle, int k, int diaInicial, String[] rangoHorario) {
        super(tick);
        this.callePuntoAcumulacion = calle;
        this.numeroPuntoAcumulacion = k;
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
        if (dist.getTipoDistribucion().equals("Discreta")) {
            callePuntoAcumulacion.getPuntosAcum().setCantidadBasuraPunto(numeroPuntoAcumulacion,
                dist.getDistribucionDiscreta().inverseCumulativeProbability(rand.nextDouble()));
        } else if (dist.getTipoDistribucion().equals("Continua")) {
            callePuntoAcumulacion.getPuntosAcum().setCantidadBasuraPunto(numeroPuntoAcumulacion,
                dist.getDistribucionReal().inverseCumulativeProbability(rand.nextDouble()));
        }
    }
    
}
