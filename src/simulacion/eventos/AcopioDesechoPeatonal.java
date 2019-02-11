/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion.eventos;

import java.util.Random;
import simulacion.AreaBarrido;
import simulacion.Calle;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class AcopioDesechoPeatonal extends Evento {
    
    private Ruta ruta;
    private AreaBarrido area;
    private double cantidadBasuraEnArea;
    
    public AcopioDesechoPeatonal(int tick, Ruta ruta, AreaBarrido area,
        double cantidadBasuraEnArea) {
        super(tick);
        this.ruta = ruta;
        this.area = area;
        this.cantidadBasuraEnArea = cantidadBasuraEnArea;
    }

    @Override
    public void modificarEstado() {
        Random rand = new Random();
        int indexCalle = rand.nextInt(ruta.getCalles().size());
        Calle calle = ruta.getCalles().get(indexCalle);
        int indexPuntoAcum = rand.nextInt(calle.getPuntosAcum().getNumeroPuntos());
        if (area.getCapacidad() >= cantidadBasuraEnArea) {
            area.restarBasuraArea(cantidadBasuraEnArea);
            calle.getPuntosAcum().acumularCantidadBasuraPunto(indexPuntoAcum, cantidadBasuraEnArea);
        } else {
            double diferencia = cantidadBasuraEnArea - area.getCapacidad();
            area.restarBasuraArea(diferencia);
            calle.getPuntosAcum().acumularCantidadBasuraPunto(indexPuntoAcum, diferencia);
        }
    }
    
}
