/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.metricas;

import java.util.LinkedList;
import simulacion.Calle;
import simulacion.Camion;
import simulacion.ContextoSimulacion;
import simulacion.PuntosAcumulacion;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class EficaciaRecoleccion extends Metrica {

    private Double resultado;
    
    public EficaciaRecoleccion(String nombre) {
        super(nombre);
    }
    
    @Override
    public void evaluar(ContextoSimulacion contexto) {
        LinkedList<Camion> camiones = contexto.getCamiones();
        double totalRecolectado = 0;
                
        for (Camion camion : camiones) {
            totalRecolectado += camion.getCargaTotalRecolectada();
        }
        
        LinkedList<Ruta> rutas = contexto.getRutas();
        double totalGenerado = 0;
        
        for (Ruta ruta : rutas) {
            LinkedList<Calle> calles = ruta.getCalles();
            for (Calle calle : calles) {
                PuntosAcumulacion puntosAcum = calle.getPuntosAcum();
                totalGenerado += puntosAcum.getTotalGenerado();
            }
        }
        resultado = new Double(5);
        
        this.resultado = (totalRecolectado / totalGenerado);
        
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
