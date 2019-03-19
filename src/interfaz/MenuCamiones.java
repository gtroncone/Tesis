/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import simulacion.Camion;
import simulacion.Distribucion;
import simulacion.Pieza;

/**
 *
 * @author gtroncone
 */
public class MenuCamiones extends javax.swing.JFrame {

    private final UI interfaz;
    private LinkedList<Camion> listaCamiones;

    private LinkedList<Pieza> listaPiezas;
    
    private final double FACTOR_CONVERSION_TASA_FALLA = 1 / 1000;
    
    /**
     * Creates new form MenuCamiones
     * @param ui
     */    
    // Falla / metro <= falla / kilómetro
    public MenuCamiones(UI ui) {
        interfaz = ui;
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        dropSelCamion.removeAllItems();
        dropSelCamion.addItem("Nuevo Camión");
        
        dropCapacidadCamion.removeAllItems();
        dropCapacidadCamion.addItem("15 yd3");
        dropCapacidadCamion.addItem("25 yd3");
        
        listaCamiones = interfaz.getSimulacion().getCamiones();
        
        dropSelPieza.removeAllItems();
        dropSelPieza.addItem("Nueva pieza");
    }
    
    private void reiniciarMenu() {
        if (dropSelCamion.getItemCount() > 0) {
            dropSelCamion.setSelectedIndex(0);            
        }
        campoModeloCamion.setText("");
        dropCapacidadCamion.setSelectedIndex(0);
        campoIDCamion.setText("");
        campoCostoCamion.setText("");
        listaPiezas = null;
        dropSelPieza.removeAllItems();
        dropSelPieza.addItem("Nueva Pieza");
        reiniciarMenuPieza();
    }
    
    private void reiniciarMenuPieza() {
        if (dropSelPieza.getItemCount() > 0) {
            dropSelPieza.setSelectedIndex(0);
        }
        campoNomPieza.setText("");
        campoCostoPieza.setText("");
        campoDistTiempoVidaPieza.setText("");
        checkFallaCritica.setState(false);
    }
    
    public void setListaCamiones(LinkedList<Camion> listaCamiones) {
        this.listaCamiones = listaCamiones;
        dropSelCamion.removeAllItems();
        dropSelCamion.addItem("Nuevo Camión");
        for (int i = 0; i < listaCamiones.size(); i++) {
            dropSelCamion.addItem(listaCamiones.get(i).getId());
        }
    }
    
    private boolean camionEsValido() {
        if (campoModeloCamion.getText().length() <= 0) {
            UI.alerta("El campo de modelo del camión no puede estar vacío");
            return false;
        } else if (campoIDCamion.getText().length() <= 0) {
            UI.alerta("El campo de ID del camión no puede estar vacío");
            return false;
        } else if (!campoIDEsUnico()) {
            UI.alerta("El campo de ID del camión debe ser único");
            return false;
        }
        try {
            Double.parseDouble(campoCostoCamion.getText());
        } catch (NumberFormatException e) {
            UI.alerta("El campo de precio del camión no contiene un número válido");
            return false;
        }
        return true;
    }
    
    private boolean campoIDEsUnico() {
        if (listaCamiones != null) {
            for (int i = 0; i < listaCamiones.size(); i++) {
                if (listaCamiones.get(i).getId().equals(campoIDCamion.getText())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean piezaEsValida() {
        if (campoNomPieza.getText().length() <= 0) {
            UI.alerta("El campo de nombre de la pieza no puede estar vacío");
            return false;
        } else if (!Distribucion.esDistValida(campoDistTiempoVidaPieza.getText(), false)) {
            UI.alerta("La notación de distribución en el campo de tiempo de vida es incorrecta");
            return false;
        }
        try {
            Double.parseDouble(campoCostoPieza.getText());
        } catch (NumberFormatException e) {
            UI.alerta("El campo de costo de la pieza no contiene un número válido");
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        etiquetaSelCamion = new javax.swing.JLabel();
        dropSelCamion = new javax.swing.JComboBox<>();
        btnEliminarCamion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoModeloCamion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dropCapacidadCamion = new javax.swing.JComboBox<>();
        etiquetaIDCamion = new javax.swing.JLabel();
        campoIDCamion = new javax.swing.JTextField();
        btnNuevoCamion = new javax.swing.JButton();
        btnCerrarCamion = new javax.swing.JButton();
        separador = new javax.swing.JSeparator();
        etiquetaSelPieza = new javax.swing.JLabel();
        dropSelPieza = new javax.swing.JComboBox<>();
        btnEliminarPieza = new javax.swing.JButton();
        etiquetaNomPieza = new javax.swing.JLabel();
        campoNomPieza = new javax.swing.JTextField();
        etiquetaCostoPieza = new javax.swing.JLabel();
        campoCostoPieza = new javax.swing.JTextField();
        etiquetaDistTVidaPieza = new javax.swing.JLabel();
        campoDistTiempoVidaPieza = new javax.swing.JTextField();
        btnAnadirPieza = new javax.swing.JButton();
        etiquetaCostoCamion = new javax.swing.JLabel();
        campoCostoCamion = new javax.swing.JTextField();
        checkFallaCritica = new java.awt.Checkbox();
        etiquetaCostoCamion1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaSelCamion.setText("Seleccionar Camión");

        dropSelCamion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropSelCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropSelCamionActionPerformed(evt);
            }
        });

        btnEliminarCamion.setText("Eliminar");
        btnEliminarCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCamionActionPerformed(evt);
            }
        });

        jLabel1.setText("Modelo");

        jLabel2.setText("Capacidad");

        dropCapacidadCamion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaIDCamion.setText("ID de Camión");

        btnNuevoCamion.setText("Crear");
        btnNuevoCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCamionActionPerformed(evt);
            }
        });

        btnCerrarCamion.setText("Cerrar");
        btnCerrarCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCamionActionPerformed(evt);
            }
        });

        etiquetaSelPieza.setText("Seleccionar Pieza");

        dropSelPieza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropSelPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropSelPiezaActionPerformed(evt);
            }
        });

        btnEliminarPieza.setText("Eliminar");
        btnEliminarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPiezaActionPerformed(evt);
            }
        });

        etiquetaNomPieza.setText("Nombre de la Pieza");

        etiquetaCostoPieza.setText("Costo de la Pieza");

        etiquetaDistTVidaPieza.setText("Tasa de Falla (Dist)");

        btnAnadirPieza.setText("Crear");
        btnAnadirPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirPiezaActionPerformed(evt);
            }
        });

        etiquetaCostoCamion.setText("Costo del Camión");

        checkFallaCritica.setLabel("¿Falla Crítica?");

        etiquetaCostoCamion1.setText("1/km");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaSelCamion)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(etiquetaIDCamion)
                                    .addComponent(etiquetaCostoCamion))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dropSelCamion, 0, 128, Short.MAX_VALUE)
                                    .addComponent(campoModeloCamion)
                                    .addComponent(dropCapacidadCamion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoIDCamion)
                                    .addComponent(campoCostoCamion))
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarCamion))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaSelPieza)
                                            .addComponent(etiquetaNomPieza)
                                            .addComponent(etiquetaCostoPieza)
                                            .addComponent(etiquetaDistTVidaPieza))
                                        .addGap(49, 49, 49)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dropSelPieza, 0, 128, Short.MAX_VALUE)
                                            .addComponent(campoNomPieza)
                                            .addComponent(campoCostoPieza)
                                            .addComponent(campoDistTiempoVidaPieza))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnEliminarPieza)
                                            .addComponent(etiquetaCostoCamion1)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(btnAnadirPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(checkFallaCritica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separador, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNuevoCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(btnCerrarCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaSelCamion)
                    .addComponent(dropSelCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCamion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoModeloCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropCapacidadCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaIDCamion)
                    .addComponent(campoIDCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCostoCamion)
                    .addComponent(campoCostoCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoCamion)
                    .addComponent(btnCerrarCamion))
                .addGap(18, 18, 18)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaSelPieza)
                    .addComponent(dropSelPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarPieza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNomPieza)
                    .addComponent(campoNomPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCostoPieza)
                    .addComponent(campoCostoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDistTiempoVidaPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaDistTVidaPieza)
                    .addComponent(etiquetaCostoCamion1))
                .addGap(18, 18, 18)
                .addComponent(checkFallaCritica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnadirPieza)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCamionActionPerformed
        if (camionEsValido()) {
            if (dropSelCamion.getSelectedIndex() == 0) {
                Camion camion = new Camion(campoModeloCamion.getText(),
                    dropCapacidadCamion.getSelectedIndex(), campoIDCamion.getText(),
                listaPiezas, Double.parseDouble(campoCostoCamion.getText()));
                interfaz.getSimulacion().añadirCamion(camion);
                reiniciarMenu();
                campoModeloCamion.setText("");
                dropSelCamion.addItem(camion.getId());
                UI.alerta("Camión creado exitosamente");
            } else {
                Camion camion = listaCamiones.get(dropSelCamion.getSelectedIndex() - 1);
                camion.setModelo(campoModeloCamion.getText());
                camion.setCapacidad(dropCapacidadCamion.getSelectedIndex());
                camion.setId(campoIDCamion.getText());
                camion.setPrecio(Double.parseDouble(campoCostoCamion.getText()));
                UI.alerta("Camión editado exitosamente");
            }
        }
    }//GEN-LAST:event_btnNuevoCamionActionPerformed

    private void dropSelCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropSelCamionActionPerformed
        if (dropSelCamion.getSelectedIndex() > 0) {
            btnNuevoCamion.setText("Editar");
            Camion camion = listaCamiones.get(dropSelCamion.getSelectedIndex() - 1);
            campoModeloCamion.setText(camion.getModelo());
            campoIDCamion.setText(camion.getId());
            dropCapacidadCamion.setSelectedIndex(camion.getCapacidad());
            listaPiezas = camion.getPiezas();
            if (listaPiezas != null) {
                for (int i = 0; i < listaPiezas.size(); i++) {
                    dropSelPieza.addItem(listaPiezas.get(i).getNombre());
                }
            }
            campoCostoCamion.setText(String.valueOf(camion.getPrecio()));
        } else {
            btnNuevoCamion.setText("Crear");
            reiniciarMenu();
        }
    }//GEN-LAST:event_dropSelCamionActionPerformed

    private void btnAnadirPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirPiezaActionPerformed
        if (piezaEsValida()) {
            if (dropSelPieza.getSelectedIndex() == 0) {
                Pieza pieza = new Pieza(campoNomPieza.getText(),
                        Double.parseDouble(campoCostoPieza.getText()),
                        new Distribucion(campoDistTiempoVidaPieza.getText(), FACTOR_CONVERSION_TASA_FALLA),
                        checkFallaCritica.getState());
                if (listaPiezas == null) {
                    listaPiezas = new LinkedList<>();
                    listaCamiones.get(dropSelCamion.getSelectedIndex() - 1).setPiezas(listaPiezas);
                }
                listaPiezas.add(pieza);
                dropSelPieza.addItem(pieza.getNombre());
                reiniciarMenuPieza();
                UI.alerta("Pieza añadida exitosamente");
            } else {
                Pieza pieza = listaPiezas.get(dropSelPieza.getSelectedIndex() - 1);
                pieza.setNombre(campoNomPieza.getText());
                pieza.setCosto(Double.parseDouble(campoCostoPieza.getText()));
                pieza.setTiempoDeVida(new Distribucion(campoDistTiempoVidaPieza.getText(),
                FACTOR_CONVERSION_TASA_FALLA));
                pieza.setOcasionaFallaCritica(checkFallaCritica.getState());
                UI.alerta("Pieza editada exitosamente");
            }
        }
    }//GEN-LAST:event_btnAnadirPiezaActionPerformed

    private void dropSelPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropSelPiezaActionPerformed
        if (dropSelPieza.getSelectedIndex() > 0) {
            btnAnadirPieza.setText("Editar");
            Pieza pieza = listaPiezas.get(dropSelPieza.getSelectedIndex() - 1);
            campoNomPieza.setText(pieza.getNombre());
            campoCostoPieza.setText(String.valueOf(pieza.getCosto()));
            campoDistTiempoVidaPieza.setText(pieza.getTiempoDeVida().getCampo());
            checkFallaCritica.setState(pieza.isOcasionaFallaCritica());
        } else {
            btnAnadirPieza.setText("Crear");
            reiniciarMenuPieza();
        }
    }//GEN-LAST:event_dropSelPiezaActionPerformed

    private void btnEliminarCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCamionActionPerformed
        if (dropSelCamion.getSelectedIndex() > 0) {
            listaCamiones.remove(dropSelCamion.getSelectedIndex() - 1);
            dropSelCamion.removeItemAt(dropSelCamion.getSelectedIndex());
            btnNuevoCamion.setText("Crear");
            reiniciarMenu();
        }
    }//GEN-LAST:event_btnEliminarCamionActionPerformed

    private void btnEliminarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPiezaActionPerformed
        if (dropSelPieza.getSelectedIndex() > 0) {
            listaPiezas.remove(dropSelPieza.getSelectedIndex() - 1);
            dropSelPieza.removeItemAt(dropSelPieza.getSelectedIndex());
            btnAnadirPieza.setText("Crear");
            reiniciarMenuPieza();
        }
    }//GEN-LAST:event_btnEliminarPiezaActionPerformed

    private void btnCerrarCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCamionActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCerrarCamionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirPieza;
    private javax.swing.JButton btnCerrarCamion;
    private javax.swing.JButton btnEliminarCamion;
    private javax.swing.JButton btnEliminarPieza;
    private javax.swing.JButton btnNuevoCamion;
    private javax.swing.JTextField campoCostoCamion;
    private javax.swing.JTextField campoCostoPieza;
    private javax.swing.JTextField campoDistTiempoVidaPieza;
    private javax.swing.JTextField campoIDCamion;
    private javax.swing.JTextField campoModeloCamion;
    private javax.swing.JTextField campoNomPieza;
    private java.awt.Checkbox checkFallaCritica;
    private javax.swing.JComboBox<String> dropCapacidadCamion;
    private javax.swing.JComboBox<String> dropSelCamion;
    private javax.swing.JComboBox<String> dropSelPieza;
    private javax.swing.JLabel etiquetaCostoCamion;
    private javax.swing.JLabel etiquetaCostoCamion1;
    private javax.swing.JLabel etiquetaCostoPieza;
    private javax.swing.JLabel etiquetaDistTVidaPieza;
    private javax.swing.JLabel etiquetaIDCamion;
    private javax.swing.JLabel etiquetaNomPieza;
    private javax.swing.JLabel etiquetaSelCamion;
    private javax.swing.JLabel etiquetaSelPieza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator separador;
    // End of variables declaration//GEN-END:variables
}
