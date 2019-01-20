/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.util.LinkedList;

/**
 *
 * @author gtroncone
 */
public class Horario {
    
    private final LinkedList<int[]> datos;
    
    public Horario() {
        datos = new LinkedList<>();
    }
    
    public void nuevoDato(int hora, int minuto, int frecuencia) {
        int[] entrada = new int[3];
        entrada[0] = hora;
        entrada[1] = minuto;
        entrada[2] = frecuencia;
        datos.add(entrada);
    }
    
    public int[] getDato(int index) {
        if (!datos.isEmpty()) {
            return datos.get(index);
        }
        return null;
    }
    
    public boolean editarDato(int index, int hora, int minuto, int frecuencia) {
        if (index < datos.size()) {
            int[] nuevaEntrada = new int[3];
            nuevaEntrada[0] = hora;
            nuevaEntrada[1] = minuto;
            nuevaEntrada[2] = frecuencia;
            datos.set(index, nuevaEntrada);
            return true;
        }
        return false;
    }
}
