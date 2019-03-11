/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.metricas;

import java.util.LinkedList;
import simulacion.Camion;
import simulacion.ContextoSimulacion;

/**
 *
 * @author gtroncone
 */
public class KilometrosSinAveriaEnRuta extends Metrica { 

    private LinkedList<Double> resultados;
    
    public KilometrosSinAveriaEnRuta(String nombre) {
        super(nombre);
    }
    
    @Override
    public void evaluar(ContextoSimulacion contexto) {
        resultados = new LinkedList<>();
        LinkedList<Camion> camiones = contexto.getCamiones();
        
        LinkedList<String> subtitulos = new LinkedList<>();
        
        for (Camion camion : camiones) {
            resultados.add(camion.getDistanciaRecorrida() / camion.getNumeroAverias());
            subtitulos.add("Camión: " + camion.getModelo() + " " + camion.getId());
        }
        this.setSubtitulos(subtitulos);
    }
    
    @Override
    public LinkedList<Double> getResultado() {
        return this.resultados;
    }

    @Override
    public boolean isIterable() {
        return (this.resultados instanceof Iterable);
    }
}
