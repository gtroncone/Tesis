/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;

/**
 *
 * @author gtroncone
 */
public class Distribucion {
    
    private String campo;
    private RealDistribution distReal;
    private IntegerDistribution distEntera;
    
    public Distribucion(String campo) {
        this.campo = campo;
        parse(this.campo);
    }

    public String getCampo() {
        return campo;
    }
    
    static public boolean esDistValida(String campo) {
        String aux = campo.trim();
        aux = aux.replaceAll("\\s", "");
        aux = aux.replaceAll("\\)", "");
        String[] partes = aux.split("\\(");
        String[] parametros;
        if (partes.length > 1) {
            parametros = partes[1].split(",");
            double[] auxParametros = new double[parametros.length];
            try {
                for (int i = 0; i < auxParametros.length; i++) {
                    auxParametros[i] = Double.parseDouble(parametros[i]);
                }
            } catch (NumberFormatException e) {
                return false;
            }
            switch (partes[0].toLowerCase()) {
                case "const":
                    if (auxParametros.length == 2) {
                        try {
                            new UniformRealDistribution(auxParametros[0], auxParametros[1]);
                            return true;
                        } catch (NumberIsTooLargeException e) {
                            return false;
                        }
                    }
                    return false;
                case "tri":
                    if (auxParametros.length == 3) {
                        try {
                            new TriangularDistribution(auxParametros[0],
                                auxParametros[1], auxParametros[2]);
                            return true;
                        } catch (NumberIsTooLargeException e) {
                            return false;
                        }
                    }
                    return false;
                case "weib":
                    if (auxParametros.length == 2) {
                        try {
                            new WeibullDistribution(auxParametros[0], auxParametros[1]);
                            return true;
                        } catch (NotStrictlyPositiveException e) {
                            return false;
                        }
                    }
                    return false;
                case "n":
                    if (auxParametros.length == 2) {
                        try {
                            new NormalDistribution(auxParametros[0], auxParametros[1]);
                            return true;
                        } catch (NotStrictlyPositiveException e) {
                            return false;
                        }
                    }
                    return false;
                case "exp":
                    return auxParametros.length == 1;
                case "pois":
                    if (auxParametros.length == 1) {
                        try {
                            new PoissonDistribution(auxParametros[0]);
                            return true;
                        } catch (NotStrictlyPositiveException e) {
                            return false;
                        }
                    }
                    return false;
                case "mpcnh":
                    if (auxParametros.length == 2) {
                        try {
                            new NormalDistribution(auxParametros[0], auxParametros[1]);
                            return true;
                        } catch (NotStrictlyPositiveException e) {
                            return false;
                        }
                    }
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
    
    public void parse(String campo) {
        String aux = campo.trim();
        aux = aux.replaceAll("\\s", "");
        aux = aux.replaceAll("\\)", "");
        String[] partes = aux.split("\\(");
        String[] parametros;
        if (partes.length > 1) {
            parametros = partes[1].split(",");
            double[] auxParametros = new double[parametros.length];
            try {
                for (int i = 0; i < auxParametros.length; i++) {
                    auxParametros[i] = Double.parseDouble(parametros[i]);
                }
            } catch (NumberFormatException e) {
                return;
            }
            switch (partes[0].toLowerCase()) {
                case "const":
                    if (auxParametros.length == 2) {
                        distReal = new UniformRealDistribution(auxParametros[0], auxParametros[1]);
                    }
                    return;
                case "tri":
                    if (auxParametros.length == 3) {
                        distReal = new TriangularDistribution(auxParametros[0],
                                auxParametros[1], auxParametros[2]);
                    }
                    return;
                case "weib":
                    if (auxParametros.length == 2) {
                        distReal = new WeibullDistribution(auxParametros[0], auxParametros[1]);
                    }
                    return;
                case "n":
                    if (auxParametros.length == 2) {
                        distReal = new NormalDistribution(auxParametros[0], auxParametros[1]);
                    }
                    return;
                case "exp":
                    if (auxParametros.length == 1) {
                        distReal = new ExponentialDistribution(auxParametros[0]);
                    }
                    return;
                case "pois":
                    if (auxParametros.length == 1) {
                        distEntera = new PoissonDistribution(auxParametros[0]);
                    }
                    return;
                case "mpcnh":
                    if (auxParametros.length == 2) {
                        distReal = new DistribucionPoissonCompuestaNoHomogeneaNormal();
                    }
            }
        }
    }
    
    public RealDistribution getDistribucionReal() {
        return distReal;
    }
    
    public IntegerDistribution getDistribucionDiscreta() {
        return distEntera;
    }
    
    public String getTipoDistribucion() {
        if (distReal != null) {
            return "Continua";
        } else if (distEntera != null) {
            return "Discreta";
        }
        return "Ninguna";
    }
    
}
