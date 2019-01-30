/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import simulacion.Calle;
import simulacion.Distribucion;
import simulacion.Horario;
import simulacion.Ruta;

/**
 *
 * @author gtroncone
 */
public class MenuRuta extends javax.swing.JFrame {

    private final UI interfaz;
    private final MenuHorarios menuHorarios;
    private Ruta rutaSeleccionada;
    private LinkedList<Calle> listaCalles;
    private Horario horario;
    private int zoom;
    private LinkedList<Point> puntos;
    private LinkedList<Ruta> listaRutas;
    
    // La ruta tiene un punto de más (el inicial)
    private DefaultListModel modeloCalles;
    
    private Color estadoColorPicker;
    private final int[] rainbowColors;
    
    /**
     * Creates new form MenuRuta
     * @param ui
     * @throws java.io.IOException
     */    
    public MenuRuta(UI ui) throws IOException {
        interfaz = ui;
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        BufferedImage imagen = ImageIO.read(new File("assets/dibujar-ruta.png"));
        btnDibujarRuta.setIcon(new ImageIcon(imagen));
        menuHorarios = new MenuHorarios(this);
        
        modeloCalles = new DefaultListModel();
        listaCallesRuta.setModel(modeloCalles);
        
        listaCalles = new LinkedList<>();
        refrescarRutas();
                
        inicializarDropdowns();
        rainbowColors = new int[6];
        rainbowColors[0] = 255 * 1000000;
        rainbowColors[1] = 255 * 1000000 + 127 * 1000;
        rainbowColors[2] = 255 * 1000000 + 255 * 1000;
        rainbowColors[3] = 255 * 1000;
        rainbowColors[4] = 255;
        rainbowColors[5] = 139 * 1000000 + 255;
    }
    
    private void inicializarDropdowns() {
        dropSelRuta.removeAllItems();
        dropSelRuta.addItem("Nueva Ruta");
        LinkedList<Ruta> rutas = interfaz.getSimulacion().getRutas();
        for (int i = 0; i < rutas.size(); i++) {
            dropSelRuta.addItem(rutas.get(i).getNombre());
        }
        
        dropTipoRec.removeAllItems();
        dropTipoRec.addItem("Puerta a puerta");
        dropTipoRec.addItem("Esquina a esquina");
        
        dropPrimerPuntoCalle.removeAllItems();
        
        dropSegundoPuntoCalle.removeAllItems();
    }
    
    private void modoCreacion() {
        btnAceptar.setText("Crear");
        rutaSeleccionada = null;
        campoDistFlujoPeatonal.setText("");
        campoNombreRuta.setText("");
        menuHorarios.setHorario(null);
        modeloCalles.clear();
        campoNombreCalle.setText("");
        listaCalles = new LinkedList<>();
        campoDistFlujoPeatonal.setText("");
        campoDistDesPorPeaton.setText("");
        campoDistVelRecor.setText("");
        puntos = new LinkedList<>();
        dropTipoRec.setSelectedIndex(0);
    }
    
    private void modoEdicion(int index) {
        btnAceptar.setText("Editar");
        LinkedList<Ruta> listaR = interfaz.getSimulacion().getRutas();
        this.rutaSeleccionada = listaR.get(index - 1);
        campoNombreRuta.setText(rutaSeleccionada.getNombre());
        menuHorarios.setHorario(rutaSeleccionada.getHorario());
        listaCalles = this.rutaSeleccionada.getCalles();
        modeloCalles.clear();
        for (int i = 0; i < listaCalles.size(); i++) {
            modeloCalles.addElement(listaCalles.get(i).getNombre());
        }
        campoDistFlujoPeatonal.setText(rutaSeleccionada.getFlujoPeatonal().getCampo());
        campoDistDesPorPeaton.setText(rutaSeleccionada.getDesechosPorPeaton().getCampo());
        puntos = this.rutaSeleccionada.getPuntos();
        dropTipoRec.setSelectedIndex(rutaSeleccionada.getTipoDeRecoleccion());
    }
    
    private void editarRuta() {
        rutaSeleccionada.setNombre(campoNombreRuta.getText());
        rutaSeleccionada.setCalles(listaCalles);
        rutaSeleccionada.setHorario(horario);
        rutaSeleccionada.setZoom(zoom); // Editado durante el proceso de dibujo
        rutaSeleccionada.setDesechosPorPeaton(campoDistDesPorPeaton.getText());
        rutaSeleccionada.setFlujoPeatonal(campoDistFlujoPeatonal.getText());
        rutaSeleccionada.setPuntos(puntos); // Editado durante el proceso de dibujo
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    private void refrescarRutas() {
        listaRutas = interfaz.getSimulacion().getRutas();
        dropSelRuta.removeAllItems();
        dropSelRuta.addItem("Nueva Ruta");
        for (int i = 0; i < listaRutas.size(); i++) {
            dropSelRuta.addItem(listaRutas.get(i).getNombre());
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

        etiquetaHorario = new javax.swing.JLabel();
        btnEditarHorario = new javax.swing.JButton();
        etiquetaTipoRecol = new javax.swing.JLabel();
        dropTipoRec = new javax.swing.JComboBox<>();
        btnDibujarRuta = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        dropSelRuta = new javax.swing.JComboBox<>();
        btnEliminarRuta = new javax.swing.JButton();
        etiquetaSelRuta = new javax.swing.JLabel();
        etiquetaNomRuta = new javax.swing.JLabel();
        campoNombreRuta = new javax.swing.JTextField();
        etiquetaDistFlujoPeatonal = new javax.swing.JLabel();
        campoDistFlujoPeatonal = new javax.swing.JTextField();
        etiquetaDistDesPorPeaton = new javax.swing.JLabel();
        campoDistDesPorPeaton = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCallesRuta = new javax.swing.JList<>();
        etiquetaNombreCalle = new javax.swing.JLabel();
        campoNombreCalle = new javax.swing.JTextField();
        etiquetaDistVelRecor = new javax.swing.JLabel();
        campoDistVelRecor = new javax.swing.JTextField();
        btnEscogerColor = new javax.swing.JButton();
        btnEliminarCalle = new javax.swing.JButton();
        btnAnadirCalle = new javax.swing.JButton();
        etiquetaPuntosCalle = new javax.swing.JLabel();
        etiquetaPuntoInicial = new javax.swing.JLabel();
        dropPrimerPuntoCalle = new javax.swing.JComboBox<>();
        etiquetaPuntoFinal = new javax.swing.JLabel();
        dropSegundoPuntoCalle = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaHorario.setText("Horario");

        btnEditarHorario.setText("Editar Horario");
        btnEditarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHorarioActionPerformed(evt);
            }
        });

        etiquetaTipoRecol.setText("Tipo de Recolección");

        dropTipoRec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDibujarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDibujarRutaActionPerformed(evt);
            }
        });

        btnAceptar.setText("Crear");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        dropSelRuta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropSelRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropSelRutaActionPerformed(evt);
            }
        });

        btnEliminarRuta.setText("Eliminar");
        btnEliminarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRutaActionPerformed(evt);
            }
        });

        etiquetaSelRuta.setText("Seleccionar Ruta");

        etiquetaNomRuta.setText("Nombre de Ruta");

        etiquetaDistFlujoPeatonal.setText("Flujo Peatonal (Dist)");

        etiquetaDistDesPorPeaton.setText("Desechos por Peatón (Dist)");

        listaCallesRuta.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaCallesRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaCallesRutaMouseClicked(evt);
            }
        });
        listaCallesRuta.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaCallesRutaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaCallesRuta);

        etiquetaNombreCalle.setText("Nombre de Calle");

        etiquetaDistVelRecor.setText("Velocidad de Recorrido (Dist)");

        btnEscogerColor.setText("Escoger Color");
        btnEscogerColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscogerColorActionPerformed(evt);
            }
        });

        btnEliminarCalle.setText("-");
        btnEliminarCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCalleActionPerformed(evt);
            }
        });

        btnAnadirCalle.setText("+");
        btnAnadirCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirCalleActionPerformed(evt);
            }
        });

        etiquetaPuntosCalle.setText("Puntos");

        etiquetaPuntoInicial.setText("De");

        dropPrimerPuntoCalle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaPuntoFinal.setText("A");

        dropSegundoPuntoCalle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaSelRuta)
                    .addComponent(etiquetaHorario)
                    .addComponent(etiquetaTipoRecol)
                    .addComponent(etiquetaNomRuta)
                    .addComponent(etiquetaDistFlujoPeatonal)
                    .addComponent(etiquetaDistDesPorPeaton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dropTipoRec, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoNombreRuta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dropSelRuta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarRuta))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoDistDesPorPeaton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addComponent(campoDistFlujoPeatonal, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnCancelar)
                .addGap(48, 48, 48)
                .addComponent(btnDibujarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnEscogerColor)
                .addGap(39, 39, 39)
                .addComponent(btnAnadirCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnEliminarCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaNombreCalle)
                            .addComponent(etiquetaDistVelRecor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNombreCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoDistVelRecor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaPuntosCalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaPuntoInicial)
                        .addGap(18, 18, 18)
                        .addComponent(dropPrimerPuntoCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiquetaPuntoFinal)
                        .addGap(18, 18, 18)
                        .addComponent(dropSegundoPuntoCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropSelRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarRuta)
                    .addComponent(etiquetaSelRuta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNomRuta)
                    .addComponent(campoNombreRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaHorario)
                    .addComponent(btnEditarHorario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTipoRecol)
                    .addComponent(dropTipoRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaDistFlujoPeatonal)
                    .addComponent(campoDistFlujoPeatonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaDistDesPorPeaton)
                    .addComponent(campoDistDesPorPeaton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnAceptar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDibujarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombreCalle)
                    .addComponent(campoNombreCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaDistVelRecor)
                    .addComponent(campoDistVelRecor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaPuntosCalle)
                    .addComponent(etiquetaPuntoInicial)
                    .addComponent(dropPrimerPuntoCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaPuntoFinal)
                    .addComponent(dropSegundoPuntoCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEscogerColor)
                    .addComponent(btnEliminarCalle)
                    .addComponent(btnAnadirCalle))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHorarioActionPerformed
        menuHorarios.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEditarHorarioActionPerformed

    private void dropSelRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropSelRutaActionPerformed
        int index = dropSelRuta.getSelectedIndex();
        if (index <= 0) {
            this.modoCreacion();
        } else {
            this.modoEdicion(index);
        }
    }//GEN-LAST:event_dropSelRutaActionPerformed

    private void btnEliminarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRutaActionPerformed
        int index = dropSelRuta.getSelectedIndex() - 1;
        if (index >= 0) {
            interfaz.getSimulacion().getRutas().remove(index);
            inicializarDropdowns();
            if (index <= 0) {
                this.modoCreacion();
            } else {
                this.modoEdicion(index);
            }
        }
    }//GEN-LAST:event_btnEliminarRutaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //TODO: Reiniciar estado del componente
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (dropSelRuta.getSelectedIndex() <= 0) {
            Ruta ruta = new Ruta(campoNombreRuta.getText(),
            horario, listaCalles, new Distribucion(campoDistFlujoPeatonal.getText()),
            new Distribucion(campoDistDesPorPeaton.getText()),
            puntos, interfaz.getZoom(), dropTipoRec.getSelectedIndex());
            interfaz.getSimulacion().añadirRuta(ruta);
            modoCreacion();
            refrescarRutas();
        } else {
            editarRuta();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnDibujarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDibujarRutaActionPerformed
        this.setVisible(false);
        if (dropSelRuta.getSelectedIndex() <= 0) {
            interfaz.iniciarProcesoDeDibujo(puntos);
        } else {
            
        }
    }//GEN-LAST:event_btnDibujarRutaActionPerformed

    private void btnEliminarCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCalleActionPerformed
        if (listaCallesRuta.getSelectedIndex() != -1) {
            listaCalles.remove(listaCallesRuta.getSelectedIndex());
            modeloCalles.remove(listaCallesRuta.getSelectedIndex());
            btnAnadirCalle.setText("+");
        }
    }//GEN-LAST:event_btnEliminarCalleActionPerformed

    private void btnAnadirCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirCalleActionPerformed
        if (listaCallesRuta.getSelectedIndex() == -1) {
            if (estadoColorPicker == null) {
                Random rand = new Random();
                estadoColorPicker = new Color(rainbowColors[rand.nextInt(6)]);
            }
            Calle calle = new Calle(campoNombreCalle.getText(), 
            new Distribucion(campoDistVelRecor.getText()),
            Integer.parseInt((String)dropPrimerPuntoCalle.getSelectedItem()),
            Integer.parseInt((String)dropSegundoPuntoCalle.getSelectedItem()),
            new Color(estadoColorPicker.getRGB()));
            listaCalles.add(calle);
            modeloCalles.addElement(campoNombreCalle.getText());
            estadoColorPicker = null;
        } else {
            Calle calle = listaCalles.get(listaCallesRuta.getSelectedIndex());
            modeloCalles.set(listaCallesRuta.getSelectedIndex(), campoNombreCalle.getText());
            calle.setNombre(campoNombreCalle.getText());
            calle.setVelocidad(new Distribucion(campoDistVelRecor.getText()));
            calle.setPuntoInicial(Integer.parseInt((String)dropPrimerPuntoCalle.getSelectedItem()));
            calle.setPuntoFinal(Integer.parseInt((String)dropSegundoPuntoCalle.getSelectedItem()));
        }
    }//GEN-LAST:event_btnAnadirCalleActionPerformed

    private void btnEscogerColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerColorActionPerformed
        estadoColorPicker = JColorChooser.showDialog(null, "Escoge un color", Color.GRAY);
    }//GEN-LAST:event_btnEscogerColorActionPerformed

    private void listaCallesRutaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaCallesRutaValueChanged
        if (listaCallesRuta.getSelectedIndex() != -1) {
            btnAnadirCalle.setText("Editar");
            Calle calle = listaCalles.get(listaCallesRuta.getSelectedIndex());
            estadoColorPicker = calle.getColor();
            campoNombreCalle.setText(calle.getNombre());
            campoDistVelRecor.setText(calle.getVelocidad().getCampo());
            for (int i = 0; i < puntos.size(); i++) {
                if (calle.getPuntoInicial() == i) {
                    dropPrimerPuntoCalle.setSelectedItem(Integer.toString(i));
                }
                if (calle.getPuntoFinal() == i) {
                    dropSegundoPuntoCalle.setSelectedItem(Integer.toString(i));
                }
            }
        } else {
            estadoColorPicker = null;
        }
    }//GEN-LAST:event_listaCallesRutaValueChanged

    private void listaCallesRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaCallesRutaMouseClicked
        if (evt.getButton() == 3) {
            btnAnadirCalle.setText("+");
            listaCallesRuta.clearSelection();
        }
    }//GEN-LAST:event_listaCallesRutaMouseClicked

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void setPuntos(LinkedList<Point> puntos) {
        this.puntos = puntos;
        if (puntos.size() >= 2) {
            dropPrimerPuntoCalle.removeAllItems();
            dropSegundoPuntoCalle.removeAllItems();
            for (int i = 0; i < puntos.size() - 1; i++) {
                dropPrimerPuntoCalle.addItem(Integer.toString(i));
                dropSegundoPuntoCalle.addItem(Integer.toString(i + 1));
            }   
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAnadirCalle;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDibujarRuta;
    private javax.swing.JButton btnEditarHorario;
    private javax.swing.JButton btnEliminarCalle;
    private javax.swing.JButton btnEliminarRuta;
    private javax.swing.JButton btnEscogerColor;
    private javax.swing.JTextField campoDistDesPorPeaton;
    private javax.swing.JTextField campoDistFlujoPeatonal;
    private javax.swing.JTextField campoDistVelRecor;
    private javax.swing.JTextField campoNombreCalle;
    private javax.swing.JTextField campoNombreRuta;
    private javax.swing.JComboBox<String> dropPrimerPuntoCalle;
    private javax.swing.JComboBox<String> dropSegundoPuntoCalle;
    private javax.swing.JComboBox<String> dropSelRuta;
    private javax.swing.JComboBox<String> dropTipoRec;
    private javax.swing.JLabel etiquetaDistDesPorPeaton;
    private javax.swing.JLabel etiquetaDistFlujoPeatonal;
    private javax.swing.JLabel etiquetaDistVelRecor;
    private javax.swing.JLabel etiquetaHorario;
    private javax.swing.JLabel etiquetaNomRuta;
    private javax.swing.JLabel etiquetaNombreCalle;
    private javax.swing.JLabel etiquetaPuntoFinal;
    private javax.swing.JLabel etiquetaPuntoInicial;
    private javax.swing.JLabel etiquetaPuntosCalle;
    private javax.swing.JLabel etiquetaSelRuta;
    private javax.swing.JLabel etiquetaTipoRecol;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> listaCallesRuta;
    // End of variables declaration//GEN-END:variables
}
