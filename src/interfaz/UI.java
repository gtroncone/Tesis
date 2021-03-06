/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the templatex| in the editor.
 */
package interfaz;

import simulacion.Simulacion;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import logica.MetadataMapa;
import logica.Render;
import logica.Tesis;

/**
 *
 * @author gtroncone
 */
public class UI extends javax.swing.JFrame {
    
    private final static double FACTOR_AUTOSCROLL = 0.95;
    private final static double ACELERACION_AUTOSCROLL = 0.01;
    private String filepath = "";
    
    private int ultimoX;
    private int ultimoY;
    private int deltaX;
    private int deltaY;
        
    private boolean modoDibujo = false;
    
    private Render render;
    private final JLabel mapa;
    private MenuRuta menuRuta;
    private MenuCamiones menuCamiones;
    private MenuPuntosAcum menuPuntosAcum;
    private MenuBarredores menuBarredores;
    private MenuConfiguracion menuConfiguracion;
    private MenuAsignacionCamiones menuAsignacion;
    
    private Simulacion simulacion;
    private final Tesis tesis;
    
    private final ScheduledExecutorService autoScroll;
    private static ScheduledFuture<?> t;
    
    private boolean dragMutex = false;

    /**
     * Creates new form UI
     * @param simulacion
     * @throws java.io.IOException
     */
    
    public UI(Simulacion simulacion, Tesis tesis) throws IOException {
        initComponents();
        this.setResizable(false);
        
        this.simulacion = simulacion;
        this.tesis = tesis;
                
        menuRuta = new MenuRuta(this);
        menuCamiones = new MenuCamiones(this);
        menuPuntosAcum = new MenuPuntosAcum(this);
        menuBarredores = new MenuBarredores(this);
        menuConfiguracion = new MenuConfiguracion(this);
        menuAsignacion = new MenuAsignacionCamiones(this);
        
        ultimoX = -1;
        ultimoY = -1;

        render = new Render(simulacion);
        mapa = new JLabel(new ImageIcon(render.getRender()), JLabel.CENTER);
        mapa.setSize(new Dimension(MetadataMapa.getDimX(),
                MetadataMapa.getDimY()));
        contenedorMapa.add(mapa);

        BufferedImage imagen = ImageIO.read(new File("assets/ruta.png"));
        btnRutas.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/camion.png"));
        btnCamiones.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/ptoacum.png"));
        btnPtosAcum.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/barredor.png"));
        btnBarrido.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/configuracion.png"));
        btnConfiguracion.setIcon(new ImageIcon(imagen));

        imagen = ImageIO.read(new File("assets/asignaciones.png"));
        btnAsignarCamiones.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/play.png"));
        btnPlay.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/carga.png"));
        btnCargar.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/guardar.png"));
        btnGuardar.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/nuevo.png"));
        btnNuevo.setIcon(new ImageIcon(imagen));
        
        autoScroll = Executors.newSingleThreadScheduledExecutor();
        btnPlay.setFocusPainted(false);
        btnZoomOut.setFocusPainted(false);
    }
    
    public void setSimulacion(Simulacion simulacion) {
        this.simulacion = simulacion;
        this.render = new Render(simulacion);
        this.actualizarMapa();
        this.menuRuta.setListaRutas(simulacion.getRutas());
        this.menuConfiguracion.setConfiguracion();
        this.menuCamiones.setListaCamiones(simulacion.getCamiones());
    }

    public Simulacion getSimulacion() {
        return simulacion;
    }
    
    public void actualizarMapa() {
        mapa.setIcon(new ImageIcon(render.getRender()));
    }
    
    public int getZoom() {
        return render.getZoom();
    }
    
    public static void alerta(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
    
    public void iniciarProcesoDeDibujo(LinkedList<Point> puntos) {
        modoDibujo = true;
        btnPlay.setEnabled(true);
        btnZoomIn.setEnabled(false);
        btnZoomOut.setEnabled(false);
        render.setPuntosDibujados(puntos);
        render.setModoDeDibujo(true);
        render.setLeadingPoint(new Point(0, 0));
        actualizarMapa();
    }
    
    private boolean hayOtrosMenusVisibles() {
        return (menuRuta.isVisible() || menuCamiones.isVisible() ||
                menuPuntosAcum.isVisible() || menuBarredores.isVisible() ||
                menuConfiguracion.isVisible() || menuAsignacion.isVisible() ||
                menuRuta.isMenuHorariosVisible());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorMapa = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnRutas = new javax.swing.JButton();
        btnCamiones = new javax.swing.JButton();
        btnPtosAcum = new javax.swing.JButton();
        btnBarrido = new javax.swing.JButton();
        btnAsignarCamiones = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnPlay = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        btnZoomIn = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contenedorMapa.setBackground(new java.awt.Color(123, 82, 40));
        contenedorMapa.setPreferredSize(new java.awt.Dimension(1024, 696));
        contenedorMapa.setRequestFocusEnabled(false);
        contenedorMapa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                contenedorMapaMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                contenedorMapaMouseDragged(evt);
            }
        });
        contenedorMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                contenedorMapaMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contenedorMapaMouseClicked(evt);
            }
        });

        btnRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutasActionPerformed(evt);
            }
        });

        btnCamiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamionesActionPerformed(evt);
            }
        });

        btnPtosAcum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPtosAcumActionPerformed(evt);
            }
        });

        btnBarrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarridoActionPerformed(evt);
            }
        });

        btnAsignarCamiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarCamionesActionPerformed(evt);
            }
        });

        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPtosAcum, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBarrido, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAsignarCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPtosAcum, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(btnCamiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRutas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBarrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsignarCamiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnPlay.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnZoomOut.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnZoomOut.setText("-");
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });

        btnZoomIn.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnZoomIn.setText("+");
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });

        btnCargar.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contenedorMapaLayout = new javax.swing.GroupLayout(contenedorMapa);
        contenedorMapa.setLayout(contenedorMapaLayout);
        contenedorMapaLayout.setHorizontalGroup(
            contenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorMapaLayout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorMapaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        contenedorMapaLayout.setVerticalGroup(
            contenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorMapaLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contenedorMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contenedorMapaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMapaMouseDragged
        if (ultimoX != -1 && ultimoY != -1) {
            deltaX = ultimoX - evt.getX();
            deltaY = ultimoY - evt.getY();
            render.actualizarPosicion(ultimoX - evt.getX(), ultimoY - evt.getY());
            actualizarMapa();
        }
        ultimoX = evt.getX();
        ultimoY = evt.getY();
    }//GEN-LAST:event_contenedorMapaMouseDragged

    private void contenedorMapaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMapaMouseReleased
        if (!dragMutex) {
            dragMutex = true;
            t = autoScroll.scheduleAtFixedRate(new Runnable() {
                int n = 0;
                int variacion = 0;
                @Override
                public void run() {
                    n++;
                    if (deltaX == 0 && deltaY == 0) {
                        dragMutex = false;
                        t.cancel(true);
                    }
                    render.actualizarPosicion((int)(FACTOR_AUTOSCROLL * deltaX), (int)(FACTOR_AUTOSCROLL * deltaY));
                    actualizarMapa();

                    if (deltaX > 0) {
                        variacion = deltaX - (int)(n * n * ACELERACION_AUTOSCROLL);
                    } else if (deltaX < 0) {
                        variacion = deltaX + (int)(n * n * ACELERACION_AUTOSCROLL);                    
                    }

                    deltaX = ((deltaX >= 0 && variacion <= 0) || (deltaX <= 0 && variacion >= 0) ? 0 : variacion);

                    if (deltaY > 0) {
                        variacion = deltaY - (int)(n * n * ACELERACION_AUTOSCROLL);                    
                    } else if (deltaY < 0) {
                        variacion = deltaY + (int)(n * n * ACELERACION_AUTOSCROLL);
                    }

                    deltaY = ((deltaY >= 0 && variacion <= 0) || (deltaY <= 0 && variacion >= 0) ? 0 : variacion);
                }
            }, 0, 5, TimeUnit.MILLISECONDS);
            ultimoX = -1;
            ultimoY = -1;
        }
    }//GEN-LAST:event_contenedorMapaMouseReleased

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        simulacion.ejecutar();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        render.zoomOut();
        actualizarMapa();
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutasActionPerformed
        if (!hayOtrosMenusVisibles()) {
            menuRuta.setVisible(true);   
        }
    }//GEN-LAST:event_btnRutasActionPerformed

    private void btnCamionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionesActionPerformed
        if (!hayOtrosMenusVisibles()) {
            menuCamiones.setVisible(true);
            menuCamiones.setListaCamiones(simulacion.getCamiones());
        }
    }//GEN-LAST:event_btnCamionesActionPerformed

    private void btnPtosAcumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPtosAcumActionPerformed
        if (!hayOtrosMenusVisibles()) {
            menuPuntosAcum.setVisible(true);
            menuPuntosAcum.setRutas(simulacion.getRutas());
        }
    }//GEN-LAST:event_btnPtosAcumActionPerformed

    private void btnBarridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarridoActionPerformed
        if (!hayOtrosMenusVisibles()) {
            menuBarredores.setVisible(true);
            menuBarredores.setRutas(simulacion.getRutas());
        }
    }//GEN-LAST:event_btnBarridoActionPerformed

    private void contenedorMapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMapaMouseClicked
        t.cancel(true);
        dragMutex = false;
        if (modoDibujo) {
            if (evt.getButton() == 1) {
                render.añadirPunto(new Point(evt.getX(), evt.getY()));
                actualizarMapa();
            } else if (evt.getButton() == 3) {
                modoDibujo = false;
                btnPlay.setEnabled(true);
                btnZoomIn.setEnabled(true);
                btnZoomOut.setEnabled(true);
                render.setModoDeDibujo(false);
                menuRuta.setPuntos(render.getPuntosDibujados());
                menuRuta.setZoom(render.getZoom());
                menuRuta.setVisible(true);
                actualizarMapa();
            }
        }
    }//GEN-LAST:event_contenedorMapaMouseClicked

    private void contenedorMapaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMapaMouseMoved
        if (modoDibujo) {
            render.setLeadingPoint(new Point(evt.getX(), evt.getY()));
            actualizarMapa();
        }
    }//GEN-LAST:event_contenedorMapaMouseMoved

    private void btnAsignarCamionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarCamionesActionPerformed
        if (!hayOtrosMenusVisibles()) {
            menuAsignacion.setVisible(true);
            menuAsignacion.setCamiones(simulacion.getCamiones());
            menuAsignacion.setRutas(simulacion.getRutas());
        }
    }//GEN-LAST:event_btnAsignarCamionesActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        if (!hayOtrosMenusVisibles()) {
            menuConfiguracion.setVisible(true);
            menuConfiguracion.setConfiguracion();
        }
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        render.zoomIn();
        actualizarMapa();
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        JFileChooser j = new JFileChooser();
        int returnValue = j.showOpenDialog(null);
        
        if (returnValue == j.APPROVE_OPTION) {
            this.filepath = j.getSelectedFile().getAbsolutePath();
            this.filepath = j.getSelectedFile().getAbsolutePath();
            String[] partes = this.filepath.split(Pattern.quote("/"));
            String[] aux = partes[partes.length - 1].split(Pattern.quote("."));
            String extension = aux[aux.length - 1];
            if (extension.equals("dsim")) {
                try {
                    tesis.deSerializarTesis(this.filepath);
                } catch (IOException ex) {
                    System.out.println(ex);
                    alerta("Error al cargar archivo");
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                    alerta("Error al cargar archivo");
                }
            } else {
                alerta("El archivo seleccionado es inválido");
            }
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (this.filepath.isEmpty()) {
           JFileChooser j = new JFileChooser();
           int returnValue = j.showSaveDialog(null);

           if (returnValue == j.APPROVE_OPTION) {
               this.filepath = j.getSelectedFile().getAbsolutePath();
               String[] partes = this.filepath.split(Pattern.quote("/"));
               String[] aux = partes[partes.length - 1].split(Pattern.quote("."));
               String extension = aux[aux.length - 1];
               if (extension.isEmpty()) {
                   this.filepath += ".dsim";
               } else {
                    if (!extension.equals("dsim")) {
                        if (this.filepath.charAt(this.filepath.length() - 1) == '.') {
                            this.filepath += "dsim";
                        } else {
                            this.filepath += ".dsim";
                        }
                    }
               }
                try {
                    tesis.serializarTesis(this.filepath);
                    alerta("Guardado correctamente");
                } catch (IOException ex) {
                    System.out.println(ex);
                    alerta("Error inesperado ocurrido al guardar. ¿Tiene los permisos requeridos?");
                }
           }
        } else {
            try {
                tesis.serializarTesis(this.filepath);
                alerta("Guardado correctamente");
            } catch (IOException ex) {
                System.out.println(ex);
                alerta("Error inesperado ocurrido al guardar. ¿Tiene los permisos requeridos?");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        JFileChooser j = new JFileChooser();
        int returnValue = j.showSaveDialog(null);

        if (returnValue == j.APPROVE_OPTION) {
            this.filepath = j.getSelectedFile().getAbsolutePath();
            String[] partes = this.filepath.split(Pattern.quote("/"));
            String[] aux = partes[partes.length - 1].split(Pattern.quote("."));
            String extension = aux[aux.length - 1];
            if (extension.isEmpty()) {
                this.filepath += ".dsim";
            } else {
                 if (!extension.equals("dsim")) {
                     if (this.filepath.charAt(this.filepath.length() - 1) == '.') {
                         this.filepath += "dsim";
                     } else {
                         this.filepath += ".dsim";
                     }
                 }
            }
        }
        try {
            tesis.guardarNuevo(this.filepath);
            alerta("Guardado correctamente");
        } catch (IOException ex) {
            System.out.println(ex);
            alerta("Error inesperado ocurrido al guardar. ¿Tiene los permisos requeridos?");
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarCamiones;
    private javax.swing.JButton btnBarrido;
    private javax.swing.JButton btnCamiones;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnPtosAcum;
    private javax.swing.JButton btnRutas;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JPanel contenedorMapa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
