/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import simulacion.AreaBarrido;
import simulacion.Distribucion;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class MenuBarredores extends javax.swing.JFrame {
    
    private final UI interfaz;
    private LinkedList<Ruta> rutas;
    private DefaultListModel modeloRutas;
    private DefaultListModel modeloAreas;
    
    private final double FACTOR_CONVERSION_VELOCIDAD_ACOPIO = 1 / 60;

    /**
     * Creates new form MenuBarredores
     * @param ui
     */
    // Kilogramos / minuto <= kilogramos / hora
    public MenuBarredores(UI ui) {
        interfaz = ui;
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
                
        modeloRutas = new DefaultListModel();
        listaRutas.setModel(modeloRutas);
        
        modeloAreas = new DefaultListModel();
        listaAreaBarrido.setModel(modeloAreas);
        
        rutas = interfaz.getSimulacion().getRutas();
    }

    public void setRutas(LinkedList<Ruta> rutas) {
        this.rutas = rutas;
        if (this.rutas != null) {
            modeloRutas.clear();
            for (int i = 0; i < this.rutas.size(); i++) {
                modeloRutas.addElement(this.rutas.get(i).getNombre());
            }
        }
        reiniciarMenu();
    }

    private void reiniciarMenu() {
        modeloAreas.clear();
        etiquetaDigNumBarredores.setText("0");
        etiquetaDigNumCuadras.setText("0");
        campoCapacidad.setText("");
        campoDistVelAcopio.setText("");
        listaRutas.clearSelection();
    }
    
    private void reiniciarSubMenu() {
        etiquetaDigNumBarredores.setText("0");
        etiquetaDigNumCuadras.setText("0");
        campoCapacidad.setText("");
        campoDistVelAcopio.setText("");
    }
    
    private boolean esValido() {
        try {
            Double.parseDouble(campoCapacidad.getText());
        } catch (NumberFormatException e) {
            UI.alerta("El campo capacidad no tiene un número válido");
            return false;
        }
        if (!Distribucion.esDistValida(campoDistVelAcopio.getText(), false)) {
            UI.alerta("La notación de distribución en el campo de velocidad de acopio es incorrecta");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        etiquetaRutas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaRutas = new javax.swing.JList<>();
        etiquetaAreaBarrido = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAreaBarrido = new javax.swing.JList<>();
        etiquetaNumBarredores = new javax.swing.JLabel();
        etiquetaNumCuadras = new javax.swing.JLabel();
        etiquetaDistVelAcopio = new javax.swing.JLabel();
        etiquetaDigNumBarredores = new javax.swing.JLabel();
        btnNuevoBarredor = new javax.swing.JButton();
        btnRemoverBarredor = new javax.swing.JButton();
        etiquetaDigNumCuadras = new javax.swing.JLabel();
        btnSumarCuadra = new javax.swing.JButton();
        btnRestarCuadra = new javax.swing.JButton();
        campoDistVelAcopio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        etiquetaCapacidad = new javax.swing.JLabel();
        campoCapacidad = new javax.swing.JTextField();
        btnCrearAreaBarrido = new javax.swing.JButton();
        btnEditarAreaBarrido = new javax.swing.JButton();
        btnEliminasAreaBarrido = new javax.swing.JButton();
        etiquetaKilogramo = new javax.swing.JLabel();
        btnCancelarAreaBarrido = new javax.swing.JButton();
        etiquetaKilogramo1 = new javax.swing.JLabel();

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaRutas.setText("Rutas");

        listaRutas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaRutasMouseClicked(evt);
            }
        });
        listaRutas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaRutasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaRutas);

        etiquetaAreaBarrido.setText("Área de Barrido");

        listaAreaBarrido.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaAreaBarrido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaAreaBarridoMouseClicked(evt);
            }
        });
        listaAreaBarrido.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaAreaBarridoValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaAreaBarrido);

        etiquetaNumBarredores.setText("Número de Barredores por Área");

        etiquetaNumCuadras.setText("Número de Cuadras");

        etiquetaDistVelAcopio.setText("Velocidad de Acopio (Dist.)");

        etiquetaDigNumBarredores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDigNumBarredores.setText("0");

        btnNuevoBarredor.setText("+");
        btnNuevoBarredor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoBarredorActionPerformed(evt);
            }
        });

        btnRemoverBarredor.setText("-");
        btnRemoverBarredor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverBarredorActionPerformed(evt);
            }
        });

        etiquetaDigNumCuadras.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDigNumCuadras.setText("0");

        btnSumarCuadra.setText("+");
        btnSumarCuadra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarCuadraActionPerformed(evt);
            }
        });

        btnRestarCuadra.setText("-");
        btnRestarCuadra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarCuadraActionPerformed(evt);
            }
        });

        etiquetaCapacidad.setText("Capacidad");

        btnCrearAreaBarrido.setText("Crear");
        btnCrearAreaBarrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAreaBarridoActionPerformed(evt);
            }
        });

        btnEditarAreaBarrido.setText("Editar");
        btnEditarAreaBarrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAreaBarridoActionPerformed(evt);
            }
        });

        btnEliminasAreaBarrido.setText("Eliminar");
        btnEliminasAreaBarrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminasAreaBarridoActionPerformed(evt);
            }
        });

        etiquetaKilogramo.setText("Kg");

        btnCancelarAreaBarrido.setText("Cancelar");
        btnCancelarAreaBarrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAreaBarridoActionPerformed(evt);
            }
        });

        etiquetaKilogramo1.setText("Kg/h");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaRutas)
                            .addComponent(etiquetaAreaBarrido))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(etiquetaNumCuadras, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaNumBarredores, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(etiquetaDigNumBarredores, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNuevoBarredor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemoverBarredor))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(etiquetaDigNumCuadras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSumarCuadra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRestarCuadra))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaDistVelAcopio)
                                    .addComponent(etiquetaCapacidad))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoCapacidad)
                                    .addComponent(campoDistVelAcopio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaKilogramo1)
                                    .addComponent(etiquetaKilogramo))
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCrearAreaBarrido, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEditarAreaBarrido, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEliminasAreaBarrido, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelarAreaBarrido)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaRutas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etiquetaAreaBarrido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNumBarredores)
                    .addComponent(etiquetaDigNumBarredores)
                    .addComponent(btnNuevoBarredor)
                    .addComponent(btnRemoverBarredor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNumCuadras)
                    .addComponent(etiquetaDigNumCuadras)
                    .addComponent(btnSumarCuadra)
                    .addComponent(btnRestarCuadra))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCapacidad)
                    .addComponent(campoCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaKilogramo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaDistVelAcopio)
                    .addComponent(campoDistVelAcopio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaKilogramo1))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearAreaBarrido)
                    .addComponent(btnEditarAreaBarrido)
                    .addComponent(btnEliminasAreaBarrido)
                    .addComponent(btnCancelarAreaBarrido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaRutasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaRutasValueChanged
        Ruta ruta = rutas.get(listaRutas.getSelectedIndex());
        modeloAreas.clear();
        if (ruta.getListaAreas() != null) {
            for (int i = 0; i < ruta.getListaAreas().size(); i++) {
                modeloAreas.addElement(this.rutas.get(
                    listaRutas.getSelectedIndex()).getListaAreas().get(i));
            }
        }
        reiniciarSubMenu();
    }//GEN-LAST:event_listaRutasValueChanged

    private void listaAreaBarridoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaAreaBarridoValueChanged
        if (listaAreaBarrido.getSelectedIndex() > -1) {
            AreaBarrido area = rutas.get(listaRutas.getSelectedIndex())
                .getListaAreas().get(listaAreaBarrido.getSelectedIndex());
            etiquetaDigNumBarredores.setText(String.valueOf(area.getNumeroBarredores()));
            etiquetaDigNumCuadras.setText(String.valueOf(area.getNumeroCuadras()));
            campoCapacidad.setText(String.valueOf(area.getCapacidad()));
            campoDistVelAcopio.setText(area.getVelocidadAcopio().getCampo());
        }
    }//GEN-LAST:event_listaAreaBarridoValueChanged

    private void listaAreaBarridoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAreaBarridoMouseClicked
        if (evt.getButton() == 3) {
            listaAreaBarrido.clearSelection();
            reiniciarSubMenu();
        }
    }//GEN-LAST:event_listaAreaBarridoMouseClicked

    private void listaRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaRutasMouseClicked
        if (evt.getButton() == 3) {
            listaRutas.clearSelection();
            reiniciarMenu();
        }
    }//GEN-LAST:event_listaRutasMouseClicked

    private void btnNuevoBarredorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoBarredorActionPerformed
        etiquetaDigNumBarredores.setText(String.valueOf(
                Integer.parseInt(etiquetaDigNumBarredores.getText()) + 1));
    }//GEN-LAST:event_btnNuevoBarredorActionPerformed

    private void btnRemoverBarredorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverBarredorActionPerformed
        if (Integer.parseInt(etiquetaDigNumBarredores.getText()) > 0) {
            etiquetaDigNumBarredores.setText(String.valueOf(
                Integer.parseInt(etiquetaDigNumBarredores.getText()) - 1));
        }
    }//GEN-LAST:event_btnRemoverBarredorActionPerformed

    private void btnSumarCuadraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarCuadraActionPerformed
        etiquetaDigNumCuadras.setText(String.valueOf(
            Integer.parseInt(etiquetaDigNumCuadras.getText()) + 1));
    }//GEN-LAST:event_btnSumarCuadraActionPerformed

    private void btnRestarCuadraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarCuadraActionPerformed
        if (Integer.parseInt(etiquetaDigNumCuadras.getText()) > 0) {
            etiquetaDigNumCuadras.setText(String.valueOf(
                Integer.parseInt(etiquetaDigNumCuadras.getText()) - 1));
        }
    }//GEN-LAST:event_btnRestarCuadraActionPerformed

    private void btnCrearAreaBarridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAreaBarridoActionPerformed
        if (esValido()) {
            if (listaRutas.getSelectedIndex() > -1) {
                AreaBarrido area = new AreaBarrido(
                    Integer.parseInt(etiquetaDigNumBarredores.getText()),
                    Integer.parseInt(etiquetaDigNumCuadras.getText()), 
                    Double.parseDouble(campoCapacidad.getText()), 
                    new Distribucion(campoDistVelAcopio.getText(),
                    FACTOR_CONVERSION_VELOCIDAD_ACOPIO));
                rutas.get(listaRutas.getSelectedIndex()).añadirArea(area);
                modeloAreas.addElement("Area " + modeloAreas.getSize());
                UI.alerta("Área de barrido añadida correctamente");
            }
        }
    }//GEN-LAST:event_btnCrearAreaBarridoActionPerformed

    private void btnEditarAreaBarridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAreaBarridoActionPerformed
        if (esValido()) {
            if (listaRutas.getSelectedIndex() > -1 &&
                    listaAreaBarrido.getSelectedIndex() > -1) {
                AreaBarrido area = rutas.get(listaRutas.getSelectedIndex()).getListaAreas().get(listaAreaBarrido.getSelectedIndex());
                area.setNumeroBarredores(Integer.parseInt(etiquetaDigNumBarredores.getText()));
                area.setNumeroCuadras(Integer.parseInt(etiquetaDigNumCuadras.getText()));
                area.setCapacidad(Double.parseDouble(campoCapacidad.getText()));
                area.setVelocidadAcopio(new Distribucion(campoDistVelAcopio.getText(),
                FACTOR_CONVERSION_VELOCIDAD_ACOPIO));
            }
            UI.alerta("Área de barrido editada correctamente");
        }
    }//GEN-LAST:event_btnEditarAreaBarridoActionPerformed

    private void btnEliminasAreaBarridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminasAreaBarridoActionPerformed
        if (listaRutas.getSelectedIndex() > -1 &&
                listaAreaBarrido.getSelectedIndex() > -1) {
            rutas.get(listaRutas.getSelectedIndex()).getListaAreas()
                    .remove(listaAreaBarrido.getSelectedIndex());
            modeloAreas.remove(listaAreaBarrido.getSelectedIndex());
            reiniciarSubMenu();
        }
    }//GEN-LAST:event_btnEliminasAreaBarridoActionPerformed

    private void btnCancelarAreaBarridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAreaBarridoActionPerformed
        //TODO: Reiniciar estado del componente
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCancelarAreaBarridoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarAreaBarrido;
    private javax.swing.JButton btnCrearAreaBarrido;
    private javax.swing.JButton btnEditarAreaBarrido;
    private javax.swing.JButton btnEliminasAreaBarrido;
    private javax.swing.JButton btnNuevoBarredor;
    private javax.swing.JButton btnRemoverBarredor;
    private javax.swing.JButton btnRestarCuadra;
    private javax.swing.JButton btnSumarCuadra;
    private javax.swing.JTextField campoCapacidad;
    private javax.swing.JTextField campoDistVelAcopio;
    private javax.swing.JLabel etiquetaAreaBarrido;
    private javax.swing.JLabel etiquetaCapacidad;
    private javax.swing.JLabel etiquetaDigNumBarredores;
    private javax.swing.JLabel etiquetaDigNumCuadras;
    private javax.swing.JLabel etiquetaDistVelAcopio;
    private javax.swing.JLabel etiquetaKilogramo;
    private javax.swing.JLabel etiquetaKilogramo1;
    private javax.swing.JLabel etiquetaNumBarredores;
    private javax.swing.JLabel etiquetaNumCuadras;
    private javax.swing.JLabel etiquetaRutas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaAreaBarrido;
    private javax.swing.JList<String> listaRutas;
    // End of variables declaration//GEN-END:variables
}
