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
    private LinkedList<Camion> camionesAsignados;
    
    public Horario() {
        datos = new LinkedList<>();
        camionesAsignados = new LinkedList<>();
    }

    public LinkedList<int[]> getDatos() {
        return datos;
    }

    public LinkedList<Camion> getCamionesAsignados() {
        return camionesAsignados;
    }

    public void setCamionesAsignados(LinkedList<Camion> camionesAsignados) {
        this.camionesAsignados = camionesAsignados;
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
    
    public void asignarCamion(Camion camion) {
        camionesAsignados.add(camion);
    }
    
    public void eliminarCamion(int index) {
        camionesAsignados.remove(index);
    }
    
    public Camion getCamion(int index) {
        if (!camionesAsignados.isEmpty()) {
            return camionesAsignados.get(index);
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

    public void eliminarDato(int selectedIndex) {
        datos.remove(selectedIndex);
    }
}
