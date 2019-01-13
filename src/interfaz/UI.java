/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the templatex| in the editor.
 */
package interfaz;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import logica.Render;

/**
 *
 * @author gtroncone
 */
public class UI extends javax.swing.JFrame {
    
    private final static double FACTOR_AUTOSCROLL = 0.95;
    private final static double ACELERACION_AUTOSCROLL = 0.01;
    
    private int ultimoX;
    private int ultimoY;
    private int deltaX;
    private int deltaY;
    
    private final Render render;
    private final JLabel mapa;
    private final MenuRuta menuRuta;
    private final MenuCamiones menuCamiones;
    private final MenuPuntosAcum menuPuntosAcum;
    private final MenuBarredores menuBarredores;
    
    private final ScheduledExecutorService autoScroll;
    private static ScheduledFuture<?> t;

    /**
     * Creates new form UI
     * @throws java.io.IOException
     */
    public UI() throws IOException {
        initComponents();
        this.setResizable(false);
        
        menuRuta = new MenuRuta(this);
        menuCamiones = new MenuCamiones(this);
        menuPuntosAcum = new MenuPuntosAcum(this);
        menuBarredores = new MenuBarredores(this);
        
        ultimoX = -1;
        ultimoY = -1;

        render = new Render();
        mapa = new JLabel(new ImageIcon(render.getRender()), JLabel.CENTER);
        mapa.setSize(new Dimension(render.getMetadata().getDimX(),
                render.getMetadata().getDimY()));
        contenedorMapa.add(mapa);

        BufferedImage imagen = ImageIO.read(new File("assets/ruta.png"));
        btnRutas.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/camion.png"));
        btnCamiones.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/ptoacum.png"));
        btnPtosAcum.setIcon(new ImageIcon(imagen));
        
        imagen = ImageIO.read(new File("assets/barredor.png"));
        btnBarrido.setIcon(new ImageIcon(imagen));

        autoScroll = Executors.newSingleThreadScheduledExecutor();
        btnZoomIn.setFocusPainted(false);
        btnZoomOut.setFocusPainted(false);
    }
    
    private void actualizarMapa() {
        mapa.setIcon(new ImageIcon(render.getRender()));
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
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        btnRutas = new javax.swing.JButton();
        btnCamiones = new javax.swing.JButton();
        btnPtosAcum = new javax.swing.JButton();
        btnBarrido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contenedorMapa.setBackground(new java.awt.Color(123, 82, 40));
        contenedorMapa.setPreferredSize(new java.awt.Dimension(1024, 696));
        contenedorMapa.setRequestFocusEnabled(false);
        contenedorMapa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                contenedorMapaMouseDragged(evt);
            }
        });
        contenedorMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                contenedorMapaMouseReleased(evt);
            }
        });

        btnZoomIn.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnZoomIn.setText("+");
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });

        btnZoomOut.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        btnZoomOut.setText("-");
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
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

        javax.swing.GroupLayout contenedorMapaLayout = new javax.swing.GroupLayout(contenedorMapa);
        contenedorMapa.setLayout(contenedorMapaLayout);
        contenedorMapaLayout.setHorizontalGroup(
            contenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorMapaLayout.createSequentialGroup()
                .addContainerGap(1184, Short.MAX_VALUE)
                .addGroup(contenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
            .addGroup(contenedorMapaLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(btnRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addComponent(btnCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addComponent(btnPtosAcum, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addComponent(btnBarrido, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contenedorMapaLayout.setVerticalGroup(
            contenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorMapaLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btnZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(332, 332, 332)
                .addGroup(contenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBarrido, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(btnPtosAcum, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(btnCamiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRutas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        t = autoScroll.scheduleAtFixedRate(new Runnable() {
            int n = 0;
            int variacion = 0;
            @Override
            public void run() {
                n++;
                if (deltaX == 0 && deltaY == 0) {
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
    }//GEN-LAST:event_contenedorMapaMouseReleased

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        render.zoomIn();
        actualizarMapa();
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        render.zoomOut();
        actualizarMapa();
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutasActionPerformed
        menuRuta.setVisible(true);
    }//GEN-LAST:event_btnRutasActionPerformed

    private void btnCamionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamionesActionPerformed
        menuCamiones.setVisible(true);
    }//GEN-LAST:event_btnCamionesActionPerformed

    private void btnPtosAcumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPtosAcumActionPerformed
        menuPuntosAcum.setVisible(true);
    }//GEN-LAST:event_btnPtosAcumActionPerformed

    private void btnBarridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarridoActionPerformed
        menuBarredores.setVisible(true);
    }//GEN-LAST:event_btnBarridoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarrido;
    private javax.swing.JButton btnCamiones;
    private javax.swing.JButton btnPtosAcum;
    private javax.swing.JButton btnRutas;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JPanel contenedorMapa;
    // End of variables declaration//GEN-END:variables
}
