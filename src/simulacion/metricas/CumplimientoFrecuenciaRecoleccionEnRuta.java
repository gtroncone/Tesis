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
public class CumplimientoFrecuenciaRecoleccionEnRuta extends Metrica {

    private LinkedList<Double> resultados;

    public CumplimientoFrecuenciaRecoleccionEnRuta(String nombre) {
        super(nombre);
    }
    
    public CumplimientoFrecuenciaRecoleccionEnRuta(String nombre, String unidades) {
        super(nombre, unidades);
    }
    
    @Override
    public void evaluar(ContextoSimulacion contexto) {
        resultados = new LinkedList<>();
        LinkedList<Camion> camiones = contexto.getCamiones();
        
        LinkedList<String> subtitulos = new LinkedList<>();
        
        for (Camion camion : camiones) {
            double numeroRutas = camion.getNumeroRutasIniciadas();
            if (numeroRutas == 0) {
                resultados.add(Double.NaN);
                subtitulos.add("Camión con modelo " + camion.getModelo() + " y con ID " + camion.getId()
                    + " no fue desplegado");
            } else {
                resultados.add((double) ((numeroRutas - camion.getNumeroRutasConFallas()) / numeroRutas));
                subtitulos.add("Camión con modelo " + camion.getModelo() + " y con ID " + camion.getId());                
            }
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
