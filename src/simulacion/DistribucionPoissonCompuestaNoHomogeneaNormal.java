/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author gtroncone
 */
public class DistribucionPoissonCompuestaNoHomogeneaNormal {

    private NormalDistribution distNormal;
    private double lambda;
    
    public DistribucionPoissonCompuestaNoHomogeneaNormal(double lambda, double media, double desviacion) {
        distNormal = new NormalDistribution(media, desviacion);
        this.lambda = lambda;
    }

    double evaluar(double valor, int tick) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    double evaluarInversa(double probabilidad, int tick) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
