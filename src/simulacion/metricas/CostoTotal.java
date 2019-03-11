/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.metricas;

import java.util.LinkedList;
import simulacion.AreaBarrido;
import simulacion.Camion;
import simulacion.ContextoSimulacion;
import simulacion.Pieza;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class CostoTotal extends Metrica {

    private Double resultado;
    
    public CostoTotal(String nombre) {
        super(nombre);
    }

    @Override
    public void evaluar(ContextoSimulacion contexto) {
        double costo = 0;
        
        LinkedList<Camion> camiones = contexto.getCamiones();
        LinkedList<Ruta> rutas = contexto.getRutas();
        
        costo += (contexto.getNumMecanicos() * contexto.getSalarioMecanicos());
                
        for (Ruta ruta : rutas) {
            LinkedList<AreaBarrido> areas = ruta.getListaAreas();
            for (AreaBarrido area : areas) {
                costo += (area.getNumeroBarredores() * contexto.getSalarioBarredores());
            }
        }
        
        for (Camion camion : camiones) {
            costo += camion.getPrecio();
            costo += contexto.getSalarioEquipoRecoleccion();
            LinkedList<Pieza> piezas = camion.getPiezas();
            for (Pieza pieza : piezas) {
                costo += (pieza.getNumeroCambios() * pieza.getCosto());
                costo += (pieza.getCosto());
            }
        }
        
        this.resultado = costo;
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
