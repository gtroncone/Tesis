/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author gtroncone
 */
public class Horario implements Serializable {
    
    private final LinkedList<int[]> datos;
    private LinkedList<Camion> camionesAsignados;
    private LinkedList<Integer> mapaCamionHorarios; //0: indice camion, 1: indice horario
    
    public Horario() {
        datos = new LinkedList<>();
        camionesAsignados = new LinkedList<>();
        mapaCamionHorarios = new LinkedList<>();
    }
    
    public Horario(Horario horario) {
        this.datos = new LinkedList<>();
        
        for (int i = 0; i < horario.getDatos().size(); i++) {
            int[] dato = new int[horario.getDatos().get(i).length];
            for (int j = 0; j < horario.getDatos().get(i).length; j++) {
                dato[j] = horario.getDatos().get(i)[j];
            }
            this.datos.add(dato);
        }
        this.mapaCamionHorarios = new LinkedList<>();
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
        entrada[2] = frecuencia; // 0: Diario, 1: Interdiario
        datos.add(entrada);
    }
    
    public int[] getDato(int index) {
        if (!datos.isEmpty()) {
            return datos.get(index);
        }
        return null;
    }
    
    public void asignarCamion(Camion camion, int indexHorario) {
        camionesAsignados.add(camion);
        mapaCamionHorarios.add(indexHorario);
    }
    
    public void eliminarCamion(int index) {
        camionesAsignados.remove(index);
        mapaCamionHorarios.remove(index);
    }
    
    public Camion getCamion(int index) {
        if (!camionesAsignados.isEmpty()) {
            return camionesAsignados.get(index);
        }
        return null;
    }

    public LinkedList<Integer> getMapaCamionHorarios() {
        return mapaCamionHorarios;
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
    
    public void reconciliarCamiones(Ruta ruta, LinkedList<Camion> nuevosCamiones) {
        LinkedList<Camion> viejosCamionesAsignados = ruta.getHorario().getCamionesAsignados();
        LinkedList<Integer> viejoMapaCamionesAHorario = ruta.getHorario().getMapaCamionHorarios();
        for (int i = 0; i < viejosCamionesAsignados.size(); i++) {
            encontrarCamion(viejosCamionesAsignados.get(i), i, nuevosCamiones,
                viejoMapaCamionesAHorario);
        }
    }
    
    private void encontrarCamion(Camion camion, int indiceCamion, LinkedList<Camion> listaCamiones,
            LinkedList<Integer> viejoMapaCamionesAHorario) {
        for (int i = 0; i < listaCamiones.size(); i++) {
            if (listaCamiones.get(i).getId().equals(camion.getId())) {
                this.mapaCamionHorarios.add(viejoMapaCamionesAHorario.get(indiceCamion));
                this.camionesAsignados.add(camion);
            }
        }
    }
}
