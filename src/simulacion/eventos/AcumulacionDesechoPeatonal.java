/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.util.LinkedList;
import java.util.Random;
import simulacion.AreaBarrido;
import simulacion.Distribucion;

/**
 *
 * @author gtroncone
 */
public class AcumulacionDesechoPeatonal extends Evento {
    
    private final LinkedList<AreaBarrido> areasBarrido;
    private final Distribucion desechoPorPeaton;
    private final int personasEnEvento;
    
    public AcumulacionDesechoPeatonal(int tick, LinkedList<AreaBarrido> areasBarrido,
            Distribucion desechoPorPeaton, int personasEnEvento) {
        super(tick);
        this.areasBarrido = areasBarrido;
        this.desechoPorPeaton = desechoPorPeaton;
        this.personasEnEvento = personasEnEvento;
    }

    @Override
    // BUG: Hay que sacar el for de aquí, se debe ejecutar sólo una vez por área
    public void modificarEstado() {
        Random rand = new Random();
        for (int i = 0; i < areasBarrido.size(); i++) {
            AreaBarrido area = areasBarrido.get(i);
            for (int j = 0; j < area.getCantidadBasura().length; j++) {
                if (desechoPorPeaton.getTipoDistribucion().equals("Discreta")) {
                    area.añadirBasuraArea(j, desechoPorPeaton.getDistribucionDiscreta()
                        .inverseCumulativeProbability(rand.nextDouble()) * personasEnEvento);
                } else if (desechoPorPeaton.getTipoDistribucion().equals("Continua")) {
                    area.añadirBasuraArea(j, desechoPorPeaton.getDistribucionReal()
                        .inverseCumulativeProbability(rand.nextDouble()) * personasEnEvento);
                }
            }
        }
    }

    public LinkedList<AreaBarrido> getAreasBarrido() {
        return areasBarrido;
    }  
}
