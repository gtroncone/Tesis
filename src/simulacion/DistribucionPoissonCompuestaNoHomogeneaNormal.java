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

    private final double media;
    private final double desviacion;
    private final double lambda;
    
    private int diaI;
    private int horaI;
    
    private int diaF;
    private int horaF;
    
    private int primerTickGrilla;
    
    private final int NUMERO_ITERACIONES = 20;
    private final double TAMAÑO_STEP = 0.1;
    private final double LIMITE_SUPERIOR_SEGURIDAD = 50;
    private final double LIMITE_INFERIOR_MDET = 50;
    
    public DistribucionPoissonCompuestaNoHomogeneaNormal(double lambda, double media, double desviacion) {
        this.media = media;
        this.desviacion = desviacion;
        this.lambda = lambda;
    }
    
    // Regla del trapezoide (b-a)*(f(a)+f(b))/2
    
    private double calcularFuncionM(int tickInicial, int tickFinal,
            int[] arrayDiario, int[] arraySemanal, int diaInicial) {
        estimarDiaYHora(tickInicial, tickFinal, diaInicial);
        
        int numeroPuntos = (int) Math.ceil((double) (tickFinal - tickInicial) / 60) + 1;
        
        double pendienteInicial;
        
        if (this.horaI + 1 >= arrayDiario.length) {
            if (this.diaI + 1 >= arraySemanal.length) {
                pendienteInicial = this.lambda * ((arrayDiario[0] * arraySemanal[0])
                    - (arrayDiario[this.horaI] * arraySemanal[this.diaI]));
            } else {
                pendienteInicial = this.lambda * ((arrayDiario[0] * arraySemanal[this.diaI + 1])
                    - (arrayDiario[this.horaI] * arraySemanal[this.diaI])) / 60;
            }
        } else {
            pendienteInicial = this.lambda * arraySemanal[this.diaI] *
                ((arrayDiario[this.horaI + 1]) - (arrayDiario[this.horaI])) / 60;
        }

        double pendienteFinal;
        
        if (this.horaF + 1 >= arrayDiario.length) {
            if (this.diaF + 1 >= arraySemanal.length) {
                pendienteFinal = this.lambda * ((arrayDiario[0] * arraySemanal[0])
                    - (arrayDiario[this.horaF] * arraySemanal[this.diaF]));
            } else {
                pendienteFinal = this.lambda * ((arrayDiario[0] * arraySemanal[this.diaF + 1])
                    - (arrayDiario[this.horaF] * arraySemanal[this.diaF])) / 60;
            }
        } else {
            pendienteFinal = this.lambda * arraySemanal[this.diaF] *
                ((arrayDiario[this.horaF + 1]) - (arrayDiario[this.horaF])) / 60;
        }
                
        double yInicial = pendienteInicial * (tickInicial % 60) + (this.lambda * arrayDiario[this.horaI] * arraySemanal[this.diaI]);
        double yFinal = pendienteFinal * (tickFinal % 60) + (this.lambda * arrayDiario[this.horaF] * arraySemanal[this.diaF]);
               
        double integral = 0;
        
        double aux = tickInicial;
        int indexDiario = this.horaI;
        int indexSemanal = this.diaI;
        for (int i = 0; i < numeroPuntos - 1; i++) {
            if (tickFinal - aux <= 60) {
                if (i == 0) {
                    integral += ((tickFinal - aux) * (yInicial + yFinal)) / 2;
                } else {
                    double factorDiarioInicial = this.lambda * arrayDiario[indexDiario] * arraySemanal[indexSemanal];
                    integral += (tickFinal - aux) * (yFinal + factorDiarioInicial) / 2;
                }
                break;
            } else {
                indexDiario++;
                if (indexDiario >= arrayDiario.length) {
                    indexDiario = 0;
                    indexSemanal++;
                    if (indexSemanal >= arraySemanal.length) {
                        indexSemanal = 0;
                    }
                }
                
                if (i == 0) {
                    integral += ((this.primerTickGrilla - aux) * (yInicial
                        + this.lambda * arrayDiario[indexDiario] * arraySemanal[indexSemanal])) / 2;
                    aux = this.primerTickGrilla;
                } else {
                    int viejoIndexDiario = indexDiario - 1;
                    int viejoIndexSemanal = indexSemanal;
                    if (viejoIndexDiario < 0) {
                        viejoIndexDiario = 23;
                        viejoIndexSemanal--;
                        if (viejoIndexSemanal < 0) {
                            viejoIndexSemanal = 6;
                        }
                    }
                    integral += (60) * (this.lambda *
                        ((arrayDiario[indexDiario] * arraySemanal[indexSemanal])
                        + (arrayDiario[viejoIndexDiario] * arraySemanal[viejoIndexSemanal]))) / 2;
                    aux += 60;
                }
            }
        }
        return integral;
    }
    
    private void estimarDiaYHora(int tickInicial, int tickFinal, int diaInicial) {
        this.diaI = (int) Math.floor((((double) tickInicial / (24 * 60)) % 7) + diaInicial);
        this.diaF = (int) Math.floor((((double) tickFinal / (24 * 60)) % 7) + diaInicial);
              
        this.horaI = (int) Math.floor(((double) tickInicial / 60) % 24);
        this.horaF = (int) Math.floor(((double) tickFinal / 60) % 24);
        
        int numDias = (int) Math.floor((double) tickInicial / (24 * 60));
        int numHoras = (int) Math.floor(((double) tickInicial - (numDias * 24 * 60)) / 60);
        
        this.primerTickGrilla = 60 * ((numDias * 24) + numHoras + 1);
    }
    
    public double evaluarProbabilidadInversa(int tickInicial, int tickFinal,
        double probabilidad, int[] arrayDiario, int[] arraySemanal, int diaInicial) {
        double mDeT = calcularFuncionM(tickInicial, tickFinal,
            arrayDiario, arraySemanal, diaInicial);
        
        if (mDeT < LIMITE_INFERIOR_MDET) {
            return 0;
        }
        
        double factorTemporal = Math.exp(-mDeT);
               
        double probZ;
        double z = 0;
        do {
            probZ = 0;
            z += TAMAÑO_STEP;
            for (int n = 1; n < NUMERO_ITERACIONES; n++) {
                NormalDistribution N = new NormalDistribution(n * media, Math.sqrt(n) * desviacion);
                double potencia = Math.pow(mDeT, n);
                double coeficienteFactorTemporal = (potencia / factorial(n)) * factorTemporal;
                probZ += ((N.cumulativeProbability(z) - N.cumulativeProbability(0)) * coeficienteFactorTemporal);
            }
            probZ += factorTemporal;
        } while (probZ < probabilidad && z < LIMITE_SUPERIOR_SEGURIDAD);
        return z;
    }
    
    public long factorial(int n) {
        long factorial = 1;
        int contador = n;
        do {
            factorial *= contador;
            contador--;
        } while (contador > 1);
        return factorial;
    }
        
}
