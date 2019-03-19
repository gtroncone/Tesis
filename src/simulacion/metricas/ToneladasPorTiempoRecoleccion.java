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
public class ToneladasPorTiempoRecoleccion extends Metrica {

    private Double resultado;
    
    public ToneladasPorTiempoRecoleccion(String nombre) {
        super(nombre);
    }
    
    public ToneladasPorTiempoRecoleccion(String nombre, String unidades) {
        super(nombre, unidades);
    }
    
    @Override
    public void evaluar(ContextoSimulacion contexto) {
        LinkedList<Camion> camiones = contexto.getCamiones();
        double cargaTotalRecolectada = 0;
        double tiempoTotalOperacion = 0;
                
        for (Camion camion : camiones) {
            cargaTotalRecolectada += camion.getCargaTotalRecolectada();
            tiempoTotalOperacion += camion.getTiempoOperacion();
        }
        this.resultado = ((cargaTotalRecolectada / 1000) / (tiempoTotalOperacion / 60));
    }

    @Override
    public Object getResultado() {
        return this.resultado;
    }

    @Override
    public boolean isIterable() {
        return false;
    }
    
}
