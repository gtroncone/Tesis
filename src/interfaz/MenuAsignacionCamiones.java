/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import simulacion.Camion;
import simulacion.Horario;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class MenuAsignacionCamiones extends javax.swing.JFrame {

    private final UI interfaz;
    private LinkedList<Camion> camiones;
    private LinkedList<Ruta> rutas;
    
    private DefaultListModel modeloRutas;
    private DefaultListModel modeloCamiones;
    private DefaultListModel modeloHorarios;
    private DefaultListModel modeloAsignados;
    
    /**
     * Creates new form MenuAsignacionCamiones
     * @param interfaz
     */
    public MenuAsignacionCamiones(UI interfaz) {
        this.interfaz = interfaz;
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        modeloRutas = new DefaultListModel();
        listaRutas.setModel(modeloRutas);
        
        modeloCamiones = new DefaultListModel();
        listaPiscinaCamiones.setModel(modeloCamiones);
        
        modeloHorarios = new DefaultListModel();
        listaHorarios.setModel(modeloHorarios);
        
        modeloAsignados = new DefaultListModel();
        listaCamionesAsignados.setModel(modeloAsignados);
    }

    public LinkedList<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(LinkedList<Camion> camiones) {
        this.camiones = camiones;
        if (this.camiones != null) {
            modeloCamiones.clear();
            for (int i = 0; i < this.camiones.size(); i++) {
                Camion camion = this.camiones.get(i);
                modeloCamiones.addElement("Modelo: " + camion.getModelo()
                    + " ID: " + camion.getId());
            }
        }
    }

    public LinkedList<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(LinkedList<Ruta> rutas) {
        this.rutas = rutas;
        if (this.rutas != null) {
            modeloRutas.clear();
            for (int i = 0; i < this.rutas.size(); i++) {
                modeloRutas.addElement(this.rutas.get(i).getNombre());
            }
        }
        reiniciarHorariosYAsignaciones();
    }
    
    private void reiniciarHorariosYAsignaciones() {
        modeloHorarios.clear();
        modeloAsignados.clear();
    }
    
    private boolean camionYaFueAsignado() {
        if (listaPiscinaCamiones.getSelectedIndex() > -1) {
            for (int i = 0; i < modeloAsignados.getSize(); i++) {
                if (modeloAsignados.getElementAt(i).equals(modeloCamiones
                        .getElementAt(listaPiscinaCamiones.getSelectedIndex()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaRutas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaRutas = new javax.swing.JList<>();
        etiquetaHorarios = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaHorarios = new javax.swing.JList<>();
        etiquetaCamiones = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaCamionesAsignados = new javax.swing.JList<>();
        etiquetaPiscinaCamiones = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaPiscinaCamiones = new javax.swing.JList<>();
        btnAsignarCamion = new javax.swing.JButton();
        btnRemoverCamion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaRutas.setText("Rutas");

        listaRutas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaRutas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaRutasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaRutas);

        etiquetaHorarios.setText("Horarios");

        listaHorarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaHorarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaHorariosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaHorarios);

        etiquetaCamiones.setText("Camiones Asignados");

        listaCamionesAsignados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaCamionesAsignados);

        etiquetaPiscinaCamiones.setText("Piscina de Camiones");

        listaPiscinaCamiones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaPiscinaCamiones);

        btnAsignarCamion.setText("↑");
        btnAsignarCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarCamionActionPerformed(evt);
            }
        });

        btnRemoverCamion.setText("↓");
        btnRemoverCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverCamionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(btnAsignarCamion)
                .addGap(66, 66, 66)
                .addComponent(btnRemoverCamion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiquetaPiscinaCamiones)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaRutas)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaCamiones)
                                .addGap(59, 59, 59))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaHorarios)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaRutas)
                            .addComponent(etiquetaHorarios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaCamiones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignarCamion)
                    .addComponent(btnRemoverCamion))
                .addGap(18, 18, 18)
                .addComponent(etiquetaPiscinaCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsignarCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarCamionActionPerformed
        if (!camionYaFueAsignado()) {
            if (listaPiscinaCamiones.getSelectedIndex() > -1 && listaHorarios.getSelectedIndex() > -1) {
                Horario horario = rutas.get(listaRutas.getSelectedIndex()).getHorario();
                horario.asignarCamion(camiones.get(listaPiscinaCamiones.getSelectedIndex()),
                    listaHorarios.getSelectedIndex());
                Camion camion = camiones.get(listaPiscinaCamiones.getSelectedIndex());
                modeloAsignados.addElement("Modelo: " + camion.getModelo()
                    + " ID: " + camion.getId());
                UI.alerta("Camión asignado exitosamente");
            }
        }
    }//GEN-LAST:event_btnAsignarCamionActionPerformed

    private void btnRemoverCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverCamionActionPerformed
        if (listaCamionesAsignados.getSelectedIndex() > -1) {
            Horario horario = rutas.get(listaRutas.getSelectedIndex()).getHorario();
            horario.eliminarCamion(listaCamionesAsignados.getSelectedIndex());
            modeloAsignados.remove(listaCamionesAsignados.getSelectedIndex());
        }
    }//GEN-LAST:event_btnRemoverCamionActionPerformed

    private void listaRutasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaRutasValueChanged
        if (listaRutas.getSelectedIndex() > -1) {
            Horario horario = rutas.get(listaRutas.getSelectedIndex()).getHorario();
            modeloHorarios.clear();
            modeloAsignados.clear();
            for (int i = 0; i < horario.getDatos().size(); i++) {
                int[] datos = horario.getDato(i);
                String cadena = "";
                if (datos[0] < 10) {
                    cadena += "0";
                }
                cadena += String.valueOf(datos[0]);
                cadena += ":";
                if (datos[1] < 10) {
                    cadena += "0";
                }
                cadena += String.valueOf(datos[1]);
                cadena += " Frecuencia: " + (datos[2] == 0 ? "Diaria" : "Interdiaria");
                modeloHorarios.addElement(cadena);
            }
        }
    }//GEN-LAST:event_listaRutasValueChanged

    private void listaHorariosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaHorariosValueChanged
        if (listaHorarios.getSelectedIndex() > -1) {
            LinkedList<Camion> camionesAux = rutas.get(listaRutas.getSelectedIndex())
                    .getHorario().getCamionesAsignados();
            modeloAsignados.clear();
            for (int i = 0; i < camionesAux.size(); i++) {
                Camion camion = camionesAux.get(i);
                modeloAsignados.addElement("Modelo: " + camion.getModelo()
                    + " ID: " + camion.getId());
            }
        }
    }//GEN-LAST:event_listaHorariosValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarCamion;
    private javax.swing.JButton btnRemoverCamion;
    private javax.swing.JLabel etiquetaCamiones;
    private javax.swing.JLabel etiquetaHorarios;
    private javax.swing.JLabel etiquetaPiscinaCamiones;
    private javax.swing.JLabel etiquetaRutas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listaCamionesAsignados;
    private javax.swing.JList<String> listaHorarios;
    private javax.swing.JList<String> listaPiscinaCamiones;
    private javax.swing.JList<String> listaRutas;
    // End of variables declaration//GEN-END:variables
}
