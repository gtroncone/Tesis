/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

/**
 *
 * @author gtroncone
 */
public abstract class Evento {
    
    private final int tick;
    
    public Evento(int tick) {
        this.tick = tick;
    }

    public int getTick() {
        return tick;
    }
    
    public boolean isTick(int tick) {
        return this.tick == tick;
    }
    
    public abstract void modificarEstado();
}
