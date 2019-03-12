/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import simulacion.Simulacion;
import interfaz.UI;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import simulacion.metricas.CostoTotal;
import simulacion.metricas.CumplimientoFrecuenciaRecoleccionEnRuta;
import simulacion.metricas.DesechosTotalesRecolectados;
import simulacion.metricas.EficaciaRecoleccion;
import simulacion.metricas.EficienciaCamionesRecolectores;
import simulacion.metricas.KilometrosSinAveriaEnRuta;
import simulacion.metricas.Metrica;
import simulacion.metricas.ToneladasPorTiempoRecoleccion;

/**
 *
 * @author gtroncone
 */
public class Tesis implements Serializable {

    /**
     * @param args the command line arguments
     */
    private UI interfaz;
    private Simulacion simulacion;
    private LinkedList<Metrica> metricas;
    
    public Tesis() {
        metricas = new LinkedList<>();
        metricas.add(new CostoTotal("Costo Total"));
        metricas.add(new DesechosTotalesRecolectados("Total de Desechos Recolectados"));
        metricas.add(new CumplimientoFrecuenciaRecoleccionEnRuta("Cumplimiento de Frecuencia de Recolección en Ruta"));
        metricas.add(new EficaciaRecoleccion("Eficacia de Recolección"));
        metricas.add(new EficienciaCamionesRecolectores("Eficiencia de Camiones Recolectores"));
        metricas.add(new KilometrosSinAveriaEnRuta("Kilómetros Sin Avería En Ruta"));
        metricas.add(new ToneladasPorTiempoRecoleccion("Toneladas por Tiempo de Recolección"));
        
        simulacion = new Simulacion(metricas);
        try {
            MetadataMapa.init();
        } catch (IOException ex) {
            Logger.getLogger(Tesis.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        try {
            interfaz = new UI(simulacion, this);
        } catch (IOException ex) {
            Logger.getLogger(Tesis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarNuevo(String filepath) throws IOException {
        this.simulacion = new Simulacion(metricas);
        this.interfaz.setSimulacion(this.simulacion);
        this.serializarTesis(filepath);
    }
    
    public void serializarTesis(String filepath) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(this.simulacion);
        objectOutputStream.close();
    }
    
    public void deSerializarTesis(String filepath) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filepath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        
        this.simulacion = (Simulacion) object;
        this.interfaz.setSimulacion(this.simulacion);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Tesis.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        Tesis tesis = new Tesis();
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            tesis.getInterfaz().setVisible(true);
            tesis.getInterfaz().setResizable(false);
        });
    }

    public UI getInterfaz() {
        return interfaz;
    }

}
