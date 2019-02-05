/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.util.Queue;

/**
 *
 * @author gtroncone
 */
public class ContextoSimulacion {
    
    private final int numeroTicks;
    private int tick;
    
    public ContextoSimulacion(int numeroTicks) {
        this.numeroTicks = numeroTicks;
    }

    public int getTick() {
        return tick;
    }
    
    public void incrementarTick() {
        this.tick++;
    }
    
    public int getNumeroTicks() {
        return numeroTicks;
    }
}
