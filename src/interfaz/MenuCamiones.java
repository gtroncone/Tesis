/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javax.swing.JFrame;

/**
 *
 * @author gtroncone
 */
public class MenuCamiones extends javax.swing.JFrame {

    private final UI interfaz;
    
    /**
     * Creates new form MenuCamiones
     * @param ui
     */    
    public MenuCamiones(UI ui) {
        interfaz = ui;
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        separador = new javax.swing.JSeparator();
        etiquetaSelPieza = new javax.swing.JLabel();
        dropSelPieza = new javax.swing.JComboBox<>();
        btnEliminarPieza = new javax.swing.JButton();
        etiquetaNomPieza = new javax.swing.JLabel();
        campoNomPieza = new javax.swing.JTextField();
        etiquetaCostoPieza = new javax.swing.JLabel();
        campoCostoPieza = new javax.swing.JTextField();
        dropMonedaCostoPieza = new javax.swing.JComboBox<>();
        etiquetaDistTVidaPieza = new javax.swing.JLabel();
        campoDistTiempoVidaPieza = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        etiquetaCantPorCamion = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaSelCamion.setText("Seleccionar Camión");

        dropSelCamion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEliminarCamion.setText("Eliminar");

        jLabel1.setText("Modelo");

        jLabel2.setText("Capacidad");

        dropCapacidadCamion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaIDCamion.setText("ID de Camión");

        jButton1.setText("Crear");

        jButton2.setText("Cancelar");

        etiquetaSelPieza.setText("Seleccionar Pieza");

        dropSelPieza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEliminarPieza.setText("Eliminar");

        etiquetaNomPieza.setText("Nombre de la Pieza");

        etiquetaCostoPieza.setText("Costo de la Pieza");

        dropMonedaCostoPieza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaDistTVidaPieza.setText("Tiempo de Vida (Dist)");

        jButton3.setText("Cancelar");

        jButton4.setText("Crear");

        etiquetaCantPorCamion.setText("Cantidad por Camión");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                                    .addComponent(etiquetaIDCamion))
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dropSelCamion, 0, 128, Short.MAX_VALUE)
                                    .addComponent(campoModeloCamion)
                                    .addComponent(dropCapacidadCamion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoIDCamion))
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarCamion))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separador)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaSelPieza)
                                            .addComponent(etiquetaNomPieza)
                                            .addComponent(etiquetaCostoPieza)
                                            .addComponent(etiquetaDistTVidaPieza)
                                            .addComponent(etiquetaCantPorCamion))
                                        .addGap(49, 49, 49)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dropSelPieza, 0, 128, Short.MAX_VALUE)
                                            .addComponent(campoNomPieza)
                                            .addComponent(campoCostoPieza)
                                            .addComponent(campoDistTiempoVidaPieza)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminarPieza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dropMonedaCostoPieza, 0, 1, Short.MAX_VALUE))
                                .addGap(0, 12, Short.MAX_VALUE)))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(campoCostoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dropMonedaCostoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoDistTiempoVidaPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaDistTVidaPieza))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiquetaCantPorCamion)
                        .addContainerGap(61, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarCamion;
    private javax.swing.JButton btnEliminarPieza;
    private javax.swing.JTextField campoCostoPieza;
    private javax.swing.JTextField campoDistTiempoVidaPieza;
    private javax.swing.JTextField campoIDCamion;
    private javax.swing.JTextField campoModeloCamion;
    private javax.swing.JTextField campoNomPieza;
    private javax.swing.JComboBox<String> dropCapacidadCamion;
    private javax.swing.JComboBox<String> dropMonedaCostoPieza;
    private javax.swing.JComboBox<String> dropSelCamion;
    private javax.swing.JComboBox<String> dropSelPieza;
    private javax.swing.JLabel etiquetaCantPorCamion;
    private javax.swing.JLabel etiquetaCostoPieza;
    private javax.swing.JLabel etiquetaDistTVidaPieza;
    private javax.swing.JLabel etiquetaIDCamion;
    private javax.swing.JLabel etiquetaNomPieza;
    private javax.swing.JLabel etiquetaSelCamion;
    private javax.swing.JLabel etiquetaSelPieza;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator separador;
    // End of variables declaration//GEN-END:variables
}
