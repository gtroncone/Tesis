/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import simulacion.Simulacion;

/**
 *
 * @author gtroncone
 */
public class MenuConfiguracion extends javax.swing.JFrame {

    private final UI interfaz;
    
    /**
     * Creates new form MenuConfiguracion
     * @param interfaz
     * @throws java.io.IOException
     */
    public MenuConfiguracion(UI interfaz) throws IOException {
        this.interfaz = interfaz;
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        dropHoraInicial.removeAllItems();
        dropHoraFinal.removeAllItems();
        
        for (int i = 0; i < 24; i++) {
            String string = "";
            if (i < 10) {
                string += "0";
            }
            string += String.valueOf(i);
            string += ":00";
            dropHoraInicial.addItem(string);
            dropHoraFinal.addItem(string);
        }
        
        ((JLabel)dropNumRepeticiones.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        dropNumRepeticiones.removeAllItems();
        
        for (int i = 5; i <= 1000; i += 5) {
            dropNumRepeticiones.addItem(String.valueOf(i));
        }
        
        dropNumDias.removeAllItems();
        for (int i = 0; i < 30; i++) {
            dropNumDias.addItem(String.valueOf(i + 1));
        }
        
        dropDiaInicial.removeAllItems();
        dropDiaInicial.addItem("Lunes");
        dropDiaInicial.addItem("Martes");
        dropDiaInicial.addItem("Miércoles");
        dropDiaInicial.addItem("Jueves");
        dropDiaInicial.addItem("Viernes");
        dropDiaInicial.addItem("Sábado");
        dropDiaInicial.addItem("Domingo");
        
        dropTipoMantenimiento.removeAllItems();
        dropTipoMantenimiento.addItem("Alt. Preventivo");
        dropTipoMantenimiento.addItem("Preventivo");
        dropTipoMantenimiento.addItem("Reactivo");
    }
    
    private boolean configuracionEsValida() {
        if (dropHoraInicial.getItemAt(dropHoraInicial.getSelectedIndex())
                .equals(dropHoraFinal.getItemAt(dropHoraFinal.getSelectedIndex())) &&
                !checkCompleto.getState()) {
            alerta("Las horas inicial y final no pueden ser iguales.");
            return false;
        }
        
        try {
            Double.parseDouble(campoSalarioBarredores.getText());
        } catch (NumberFormatException e) {
            alerta("El campo de salario de barredores no contiene un número válido.");
            return false;
        }
        
        try {
            Double.parseDouble(campoEquipoRec.getText());
        } catch (NumberFormatException e) {
            alerta("El campo de salario del equipo de recolección"
                    + " no contiene un número válido.");
            return false;
        }
        
        try {
            Double.parseDouble(campoSalarioMecanicos.getText());
        } catch (NumberFormatException e) {
            alerta("El campo de salario de mecánicos"
                    + " no contiene un número válido.");
            return false;
        }
        
        return true;
    }
    
    private void alerta(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
    
    public void setConfiguracion() {
        Simulacion sim = interfaz.getSimulacion();
        String[] horarios = sim.getHorarioASimular();
        if (horarios[0].equals("x")) {
            checkCompleto.setState(true);
            dropHoraInicial.setSelectedItem("00:00");
            dropHoraInicial.setEnabled(false);
            dropHoraFinal.setSelectedItem("00:00");
            dropHoraFinal.setEnabled(false);
        } else {
            dropHoraInicial.setSelectedItem(horarios[0]);
            dropHoraFinal.setSelectedItem(horarios[1]);
        }
        
        int numRepeticiones = sim.getNumRepeticiones();
        if (numRepeticiones > 0 && numRepeticiones <= 1000 &&
                numRepeticiones % 5 == 0) {
            dropNumRepeticiones.setSelectedItem(String.valueOf(numRepeticiones));
        } else {
            dropNumRepeticiones.setSelectedItem("5");
        }
        
        if (sim.getSalarioBarredores() > 0) {
            campoSalarioBarredores.setText(String.valueOf(sim.getSalarioBarredores()));
        } else {
            campoSalarioBarredores.setText("0");
        }
        
        if (sim.getSalarioEquipoRecoleccion() > 0) {
            campoEquipoRec.setText(String.valueOf(sim.getSalarioEquipoRecoleccion()));
        } else {
            campoEquipoRec.setText("0");
        }
        
        if (sim.getNumMecanicos() > 0) {
            etiquetaDigNumMecanicos.setText(String.valueOf(sim.getNumMecanicos()));
        } else {
            etiquetaDigNumMecanicos.setText("0");
        }
        
        if (sim.getSalarioMecanicos() > 0) {
            campoSalarioMecanicos.setText(String.valueOf(sim.getSalarioMecanicos()));
        } else {
            campoSalarioMecanicos.setText("0");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        etiquetaIntervaloASim = new javax.swing.JLabel();
        dropHoraInicial = new javax.swing.JComboBox<>();
        etiquetaIntervaloA = new javax.swing.JLabel();
        dropHoraFinal = new javax.swing.JComboBox<>();
        etiquetaNumRepeticiones = new javax.swing.JLabel();
        etiquetaSalarioBarredores = new javax.swing.JLabel();
        campoSalarioBarredores = new javax.swing.JTextField();
        etiquetaSalarioEquipoRec = new javax.swing.JLabel();
        campoEquipoRec = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        etiquetaNumMecanicos = new javax.swing.JLabel();
        etiquetaDigNumMecanicos = new javax.swing.JLabel();
        btnNuevoMecanico = new javax.swing.JButton();
        btnRemoverMecanico = new javax.swing.JButton();
        etiquetaSalarioMecanicos = new javax.swing.JLabel();
        campoSalarioMecanicos = new javax.swing.JTextField();
        checkCompleto = new java.awt.Checkbox();
        dropNumRepeticiones = new javax.swing.JComboBox<>();
        etiquetaTipoMantenimiento = new javax.swing.JLabel();
        dropTipoMantenimiento = new javax.swing.JComboBox<>();
        etiquetaNumDias = new javax.swing.JLabel();
        dropNumDias = new javax.swing.JComboBox<>();
        etiquetaDiaInicial = new javax.swing.JLabel();
        dropDiaInicial = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaIntervaloASim.setText("Intervalo a Simular");

        dropHoraInicial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaIntervaloA.setText("a");

        dropHoraFinal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaNumRepeticiones.setText("Número de Repeticiones");

        etiquetaSalarioBarredores.setText("Salario de Barredores");

        etiquetaSalarioEquipoRec.setText("Salario de Equipo de Recolección");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        etiquetaNumMecanicos.setText("Número de Mecánicos");

        etiquetaDigNumMecanicos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDigNumMecanicos.setText("0");

        btnNuevoMecanico.setText("+");
        btnNuevoMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoMecanicoActionPerformed(evt);
            }
        });

        btnRemoverMecanico.setText("-");
        btnRemoverMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverMecanicoActionPerformed(evt);
            }
        });

        etiquetaSalarioMecanicos.setText("Salario de Mecánicos");

        checkCompleto.setLabel("Completo");
        checkCompleto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkCompletoMouseClicked(evt);
            }
        });

        dropNumRepeticiones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaTipoMantenimiento.setText("Tipo de Mantenimiento");

        dropTipoMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaNumDias.setText("Número de Días");

        dropNumDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaDiaInicial.setText("Día Inicial");

        dropDiaInicial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnCancelar)
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaNumMecanicos)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaNumRepeticiones)
                            .addComponent(etiquetaSalarioBarredores)
                            .addComponent(etiquetaSalarioEquipoRec)
                            .addComponent(etiquetaSalarioMecanicos)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaIntervaloASim)
                                .addGap(19, 19, 19)
                                .addComponent(checkCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(etiquetaTipoMantenimiento)
                            .addComponent(etiquetaNumDias)
                            .addComponent(etiquetaDiaInicial))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dropHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(etiquetaIntervaloA)
                                .addGap(18, 18, 18)
                                .addComponent(dropHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dropDiaInicial, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropNumDias, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropTipoMantenimiento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropNumRepeticiones, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoSalarioMecanicos, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(etiquetaDigNumMecanicos, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNuevoMecanico)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemoverMecanico))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(campoEquipoRec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                        .addComponent(campoSalarioBarredores, javax.swing.GroupLayout.Alignment.LEADING)))))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etiquetaIntervaloASim)
                        .addComponent(dropHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaIntervaloA)
                        .addComponent(dropHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNumRepeticiones)
                    .addComponent(dropNumRepeticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaNumDias)
                    .addComponent(dropNumDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaDiaInicial)
                    .addComponent(dropDiaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaSalarioBarredores)
                    .addComponent(campoSalarioBarredores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaSalarioEquipoRec)
                    .addComponent(campoEquipoRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNumMecanicos)
                    .addComponent(etiquetaDigNumMecanicos)
                    .addComponent(btnNuevoMecanico)
                    .addComponent(btnRemoverMecanico))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaSalarioMecanicos)
                    .addComponent(campoSalarioMecanicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTipoMantenimiento)
                    .addComponent(dropTipoMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (configuracionEsValida()) {
            Simulacion sim = interfaz.getSimulacion();
            String[] horarios = new String[2];
            if (checkCompleto.getState()) {
                horarios[0] = "x";
                horarios[1] = "x";
            } else {
                horarios[0] = dropHoraInicial.getItemAt(dropHoraInicial.getSelectedIndex());
                horarios[1] = dropHoraFinal.getItemAt(dropHoraInicial.getSelectedIndex());
            }
            sim.setHorarioASimular(horarios);
            sim.setNumRepeticiones(Integer.parseInt(
                    dropNumRepeticiones.getItemAt(dropNumRepeticiones.getSelectedIndex())));
            sim.setSalarioBarredores(Double.parseDouble(campoSalarioBarredores.getText()));
            sim.setSalarioEquipoRecoleccion(Double.parseDouble(campoEquipoRec.getText()));
            sim.setNumMecanicos(Integer.parseInt(etiquetaDigNumMecanicos.getText()));
            sim.setSalarioMecanicos(Double.parseDouble(campoSalarioMecanicos.getText()));
            sim.setTipoDeMantenimiento(dropTipoMantenimiento.getSelectedIndex());
            sim.setDiaInicial(dropDiaInicial.getSelectedIndex() + 1);
            sim.setNumeroDeDias(dropNumDias.getSelectedIndex() + 1);
            alerta("Configuración almacenada exitosamente.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //TODO: Reiniciar estado del componente
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoMecanicoActionPerformed
        etiquetaDigNumMecanicos.setText(String.valueOf(
                Integer.parseInt(etiquetaDigNumMecanicos.getText()) + 1));
    }//GEN-LAST:event_btnNuevoMecanicoActionPerformed

    private void btnRemoverMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverMecanicoActionPerformed
        if (Integer.parseInt(etiquetaDigNumMecanicos.getText()) > 0) {
            etiquetaDigNumMecanicos.setText(String.valueOf(
                    Integer.parseInt(etiquetaDigNumMecanicos.getText()) - 1));
        }
    }//GEN-LAST:event_btnRemoverMecanicoActionPerformed

    private void checkCompletoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkCompletoMouseClicked
        dropHoraInicial.setEnabled(!checkCompleto.getState());
        dropHoraFinal.setEnabled(!checkCompleto.getState());
    }//GEN-LAST:event_checkCompletoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevoMecanico;
    private javax.swing.JButton btnRemoverMecanico;
    private javax.swing.JTextField campoEquipoRec;
    private javax.swing.JTextField campoSalarioBarredores;
    private javax.swing.JTextField campoSalarioMecanicos;
    private java.awt.Checkbox checkCompleto;
    private javax.swing.JComboBox<String> dropDiaInicial;
    private javax.swing.JComboBox<String> dropHoraFinal;
    private javax.swing.JComboBox<String> dropHoraInicial;
    private javax.swing.JComboBox<String> dropNumDias;
    private javax.swing.JComboBox<String> dropNumRepeticiones;
    private javax.swing.JComboBox<String> dropTipoMantenimiento;
    private javax.swing.JLabel etiquetaDiaInicial;
    private javax.swing.JLabel etiquetaDigNumMecanicos;
    private javax.swing.JLabel etiquetaIntervaloA;
    private javax.swing.JLabel etiquetaIntervaloASim;
    private javax.swing.JLabel etiquetaNumDias;
    private javax.swing.JLabel etiquetaNumMecanicos;
    private javax.swing.JLabel etiquetaNumRepeticiones;
    private javax.swing.JLabel etiquetaSalarioBarredores;
    private javax.swing.JLabel etiquetaSalarioEquipoRec;
    private javax.swing.JLabel etiquetaSalarioMecanicos;
    private javax.swing.JLabel etiquetaTipoMantenimiento;
    // End of variables declaration//GEN-END:variables
}
