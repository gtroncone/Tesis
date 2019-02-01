/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

/**
 *
 * @author gtroncone
 */
public class Distribucion {
    
    private String campo;
    
    public Distribucion(String campo) {
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
    
    static public boolean esDistValida(String campo) {
        //TODO: Validación
        return true;
    }
    
}
