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
public class DesechosTotalesRecolectados extends Metrica {

    private Double resultado;
    
    public DesechosTotalesRecolectados(String nombre) {
        super(nombre);
    }
    
    public DesechosTotalesRecolectados(String nombre, String unidades) {
        super(nombre, unidades);
    }
    
    // Añadir unidades en el archivo de salida
    @Override
    public void evaluar(ContextoSimulacion contexto) {
        LinkedList<Camion> camiones = contexto.getCamiones();
        
        double total = 0;
        
        for (Camion camion : camiones) {
            total += camion.getCargaTotalRecolectada();
        }
        this.resultado = total / 1000;
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
