/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.util.Random;
import simulacion.AreaBarrido;
import simulacion.Distribucion;

/**
 *
 * @author gtroncone
 */
public class AcumulacionDesechoPeatonal extends Evento {
    
    private final AreaBarrido areaBarrido;
    private final Distribucion desechoPorPeaton;
    private final int personasEnEvento;
    
    public AcumulacionDesechoPeatonal(int tick, AreaBarrido areaBarrido,
            Distribucion desechoPorPeaton, int personasEnEvento) {
        super(tick);
        this.areaBarrido = areaBarrido;
        this.desechoPorPeaton = desechoPorPeaton;
        this.personasEnEvento = personasEnEvento;
    }

    @Override
    public void modificarEstado() {
        Random rand = new Random();
        for (int i = 0; i < areaBarrido.getCantidadBasura().length; i++) {
            if (desechoPorPeaton.getTipoDistribucion().equals("Discreta")) {
                areaBarrido.añadirBasuraArea(i, desechoPorPeaton.getDistribucionDiscreta()
                    .inverseCumulativeProbability(rand.nextDouble()) * personasEnEvento);
            } else if (desechoPorPeaton.getTipoDistribucion().equals("Continua")) {
                areaBarrido.añadirBasuraArea(i, desechoPorPeaton.getDistribucionReal()
                    .inverseCumulativeProbability(rand.nextDouble()) * personasEnEvento);
            }
        }
    }
}
