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
public class EficienciaCamionesRecolectores extends Metrica {

    private double resultado;
    
    public EficienciaCamionesRecolectores(String nombre) {
        super(nombre);
    }
    
    @Override
    public void evaluar(ContextoSimulacion contexto) {
        LinkedList<Camion> camiones = contexto.getCamiones();
        
        double numToneladasTotales = 0;
        double sumaCapacidadesCamiones = 0;
        int numeroViajesADispFinal = 0;
        double metrica = 0;
        
        for (Camion camion : camiones) {
            numToneladasTotales += camion.getCargaTotalRecolectada();
            sumaCapacidadesCamiones += (camion.getCapacidadEnKg() / 1000);
            numeroViajesADispFinal += camion.getNumeroDeViajesATransferencia();
            
            metrica += (sumaCapacidadesCamiones * numeroViajesADispFinal);
        }
        numToneladasTotales /= 1000;
        resultado = (((numToneladasTotales * 100) / numSemanas(contexto.getNumTicks())) / metrica);
    }
    
    @Override
    public Double getResultado() {
        return this.resultado;
    }
    
    private double numSemanas(int numTicks) {
        int ticksPorSemana = 7 * 24 * 60;
        return ((double) numTicks / ticksPorSemana);
    }

    @Override
    public boolean isIterable() {
        return false;
    }
    
}
