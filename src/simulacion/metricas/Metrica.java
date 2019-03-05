/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.metricas;

import java.util.LinkedList;
import simulacion.eventos.Evento;

/**
 *
 * @author gtroncone
 */
public abstract class Metrica {
        
    public abstract void evaluar(LinkedList<Evento> listaAuditoria);
    
}
