/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

import java.io.Serializable;
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
public class Distribucion implements Serializable {
    
    private String campo;
    private double factor = -1;
    private RealDistribution distReal;
    private IntegerDistribution distEntera;
    private DistribucionPoissonCompuestaNoHomogeneaNormal distEspecial;
    private int[] arraySemanal;
    private int[] arrayDiario;
    
    public Distribucion(String campo) {
        this.campo = campo;
        parse(this.campo);
    }
    
    public Distribucion(String campo, double factor) {
        this.campo = campo;
        
        if (factor > 0) {
            this.factor = factor;

            String aux = campo.trim();

            aux = aux.replaceAll("\\s", "");
            aux = aux.replaceAll("\\)", "");
            String[] partes = aux.split("\\(");
            String[] parametros;
            parametros = partes[1].split(",");
            double [] auxParametros = new double[parametros.length];
            try {
                for (int i = 0; i < auxParametros.length; i++) {
                    auxParametros[i] = Double.parseDouble(parametros[i]);
                }

                String resultado = partes[0] + "(";
                for (int i = 0; i < auxParametros.length; i++) {
                    resultado += String.valueOf(auxParametros[i] * factor);
                    if (i != auxParametros.length - 1) {
                        resultado += ",";
                    }
                }
                resultado += ")";
                parse(resultado);
            } catch (NumberFormatException e) {

            }
        } else {
            parse(campo);
        }
    }
    
    public Distribucion(String campo, int[] arraySemanal, int[] arrayDiario) {
        this.campo = campo;
        this.arrayDiario = arrayDiario;
        this.arraySemanal = arraySemanal;
        parse(this.campo);
    }

    public String getCampo() {
        return campo;
    }
    
    public double getFactor() {
        return factor;
    }
    
    static public boolean esDistValida(String campo, boolean permitidaEspecial) {
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
                    if (permitidaEspecial && auxParametros.length == 3 && auxParametros[0] > 0) {
                        try {
                            new NormalDistribution(auxParametros[1], auxParametros[2]);
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
                    if (auxParametros.length == 3 && auxParametros[0] > 0) {
                        distEspecial = new DistribucionPoissonCompuestaNoHomogeneaNormal(
                            auxParametros[0], auxParametros[1], auxParametros[2]);
                    }
            }
        }
    }
    
    private RealDistribution getDistribucionReal() {
        return distReal;
    }
    
    private IntegerDistribution getDistribucionDiscreta() {
        return distEntera;
    }

    private DistribucionPoissonCompuestaNoHomogeneaNormal getDistEspecial() {
        return distEspecial;
    }

    public int[] getArraySemanal() {
        return arraySemanal;
    }

    public int[] getArrayDiario() {
        return arrayDiario;
    }
        
    public double evaluarDistribucionInversa(double probabilidad) {
        double aux;
        if (null == this.getTipoDistribucion()) {
            return 0;
        } else switch (this.getTipoDistribucion()) {
            case "Continua":
                aux = this.getDistribucionReal().inverseCumulativeProbability(probabilidad);
                return Math.max(aux, 0);
            case "Discreta":
                aux = (int) Math.floor(this.getDistribucionDiscreta().inverseCumulativeProbability(probabilidad));
                return Math.max(aux, 0);
            default:
                return 0;
        }
    }
    
    public double evaluarDistribucion(double valor) {
        double aux;
        if (null == this.getTipoDistribucion()) {
            return 0;
        } else switch (this.getTipoDistribucion()) {
            case "Continua":
                aux = this.getDistribucionReal().cumulativeProbability(valor);
                return Math.max(aux, 0);
            case "Discreta":
                aux = (int) Math.floor(this.getDistribucionDiscreta().cumulativeProbability((int) Math.floor(valor)));
                return Math.max(aux, 0);
            default:
                return 0;
        }
    }
    
    public double evaluarDistribucionInversaEspecial(int tickInicial, int tickFinal, double probabilidad, int diaInicial) {
        if (this.esDistEspecial()) {
            double aux = this.getDistEspecial().evaluarProbabilidadInversa(tickInicial, tickFinal, probabilidad, this.arrayDiario, this.arraySemanal, diaInicial);
            return Math.max(aux, 0);
        } else {
            return 0;
        }
    }
    
    public boolean esDistribucionEspecial() {
        return "Especial".equals(this.getTipoDistribucion());
    }
    
    private String getTipoDistribucion() {
        if (distReal != null) {
            return "Continua";
        } else if (distEntera != null) {
            return "Discreta";
        } else if (distEspecial != null) {
            return "Especial";
        }
        return "Ninguna";
    }
    
    public boolean esDistEspecial() {
        return this.distEspecial != null;
    }
    
    public static boolean seriaDistEspecial(String campo) {
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
                            return false;
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
                            return false;
                        } catch (NumberIsTooLargeException e) {
                            return false;
                        }
                    }
                    return false;
                case "weib":
                    if (auxParametros.length == 2) {
                        try {
                            new WeibullDistribution(auxParametros[0], auxParametros[1]);
                            return false;
                        } catch (NotStrictlyPositiveException e) {
                            return false;
                        }
                    }
                    return false;
                case "n":
                    if (auxParametros.length == 2) {
                        try {
                            new NormalDistribution(auxParametros[0], auxParametros[1]);
                            return false;
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
                            return false;
                        } catch (NotStrictlyPositiveException e) {
                            return false;
                        }
                    }
                    return false;
                case "mpcnh":
                    if (auxParametros.length == 3 && auxParametros[0] > 0) {
                        try {
                            new NormalDistribution(auxParametros[1], auxParametros[2]);
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
    
    public boolean hayConversion() {
        return this.factor == -1;
    }
    
}
