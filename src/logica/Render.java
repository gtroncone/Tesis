/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import simulacion.Calle;
import simulacion.Ruta;
import simulacion.Simulacion;

/**
 *
 * @author gtroncone
 */
public class Render {
    
    private final static double FACTOR_SENSIBILIDAD_MAPA = 0.7; // No puede ser igual o menor a 0
    
    private final MetadataMapa metadata;
    
    private BufferedImage[][] tilesCargados;
    private BufferedImage mapa;
    private int zoom;
    private int pixelX;
    private int pixelY;
    private LinkedList<Point> puntosDibujados;
    private Point leadingPoint;

    private final Simulacion simulacion;
    private final static int DIAMETRO_PUNTO = 12;
    private final static int RADIO_PUNTO = DIAMETRO_PUNTO / 2;
    private boolean modoDeDibujo = false;
    
    public Render(Simulacion simulacion) {        
        try {
            metadata = new MetadataMapa();
            this.simulacion = simulacion;
            zoom = metadata.getMinZoom();
            puntosDibujados = new LinkedList<>();
            pixelX = 0;
            pixelY = 0;
        } catch (NullPointerException e) {
            throw e;
        }
    }

    public int getZoom() {
        return zoom;
    }

    public void setPuntosDibujados(LinkedList<Point> puntosDibujados) {
        if (puntosDibujados == null) {
            this.puntosDibujados = new LinkedList<>();
        } else {
            this.puntosDibujados = puntosDibujados;            
        }
    }
    
    public void añadirPunto(Point punto) {
        punto.translate(pixelX, pixelY);
        puntosDibujados.add(punto);
    }
    
    public BufferedImage getRender() {
        mapa = renderizarMapa();
        dibujarRutas();
        if (modoDeDibujo) {
            dibujarRutaEnConstruccion();
        }
        return mapa;
    }

    public void setModoDeDibujo(boolean modoDeDibujo) {
        this.modoDeDibujo = modoDeDibujo;
    }
    
    private void dibujarRutas() {
        Graphics g2d = mapa.createGraphics();
        g2d.setColor(new Color(1f, 0f, 0f, 0.5f));
        LinkedList<Ruta> rutas = simulacion.getRutas();
        for (int i = 0; i < rutas.size(); i++) {
            Ruta ruta = rutas.get(i);
            if (ruta.getZoom() == zoom) {
                LinkedList<Point> puntos = ruta.getPuntos();
                for (int j = 0; j < puntos.size(); j++) {
                    Point punto = puntos.get(j);
                    Calle calle = getCalle(ruta, j);
                    if (calle != null) {
                        g2d.setColor(calle.getColor());                        
                    }
                    g2d.fillOval((int) punto.getX() - pixelX - RADIO_PUNTO,
                        (int) punto.getY() - pixelY - RADIO_PUNTO,
                        DIAMETRO_PUNTO, DIAMETRO_PUNTO);
                    if (j + 1 < puntos.size()) {
                        Point siguientePunto = puntos.get(j + 1);
                        g2d.drawLine((int) punto.getX() - pixelX, (int) punto.getY() - pixelY,
                            (int) siguientePunto.getX() - pixelX, (int) siguientePunto.getY() - pixelY);
                    }
                }
            }
        }
    }
    
    private Calle getCalle(Ruta ruta, int index) {
        LinkedList<Calle> calles = ruta.getCalles();
        for (int i = 0; i < calles.size(); i++) {
            Calle calle = calles.get(i);
            if (calle.getPuntoInicial() <= index &&
                    calle.getPuntoFinal() >= index) {
                return calle;
            }
        }
        return null;
    }
    
    private void dibujarRutaEnConstruccion() {
        Graphics g2d = mapa.createGraphics();
        g2d.setColor(new Color(1f, 0f, 0f, 0.5f));
        for (int i = 0; i < puntosDibujados.size(); i++) {
            Point punto = puntosDibujados.get(i);
            g2d.fillOval((int) punto.getX() - pixelX - RADIO_PUNTO,
                    (int) punto.getY() - pixelY - RADIO_PUNTO,
                    DIAMETRO_PUNTO, DIAMETRO_PUNTO);
            if (i + 1 < puntosDibujados.size()) {
                Point siguientePunto = puntosDibujados.get(i + 1);
                g2d.drawLine((int) punto.getX() - pixelX, (int) punto.getY() - pixelY,
                        (int) siguientePunto.getX() - pixelX, (int) siguientePunto.getY() - pixelY);
            }
        }
        if (!puntosDibujados.isEmpty()) {
            Point puntoFinal = puntosDibujados.getLast();
            g2d.drawLine((int) puntoFinal.getX() - pixelX, (int) puntoFinal.getY() - pixelY,
                    (int) leadingPoint.getX() - pixelX, (int) leadingPoint.getY() - pixelY);
        }
            g2d.fillOval((int) leadingPoint.getX() - pixelX - RADIO_PUNTO,
                    (int) leadingPoint.getY() - pixelY - RADIO_PUNTO,
                    DIAMETRO_PUNTO, DIAMETRO_PUNTO);
    }

    public LinkedList<Point> getPuntosDibujados() {
        return puntosDibujados;
    }
    
    public MetadataMapa getMetadata() {
        return metadata;
    }
    
    public void actualizarPosicion(int deltaX, int deltaY) {
        if (pixelX + deltaX >= 0 &&
                pixelX + deltaX + metadata.getDimX() < metadata.getDato(zoom, "numX") * metadata.getDimTile()) {
            pixelX += (FACTOR_SENSIBILIDAD_MAPA * deltaX);
        }
        if (pixelY + deltaY >= 0 &&
                pixelY + deltaY + metadata.getDimY() < metadata.getDato(zoom, "numY") * metadata.getDimTile()) {
            pixelY += (FACTOR_SENSIBILIDAD_MAPA * deltaY);
        }
    }
    
    public void zoomIn() {
        if (zoom < metadata.getMaxZoom()) {
            zoom++;
            pixelX = 2 * (pixelX + 2 * metadata.getDimTile());
            pixelY = 2 * (pixelY + metadata.getDimTile());
            pixelX = pixelX > (metadata.getDato(zoom, "numX") * metadata.getDimTile() - metadata.getDimX()) ? (metadata.getDato(zoom, "numX") * metadata.getDimTile() - metadata.getDimX() - 1) : pixelX;
            pixelY = pixelY > (metadata.getDato(zoom, "numY") * metadata.getDimTile() - metadata.getDimY()) ? (metadata.getDato(zoom, "numY") * metadata.getDimTile() - metadata.getDimY() - 1) : pixelY;
        }
    }
    
    public void zoomOut() {
        if (zoom > metadata.getMinZoom()) {
            zoom--;
            pixelX = (pixelX / 2) - (2 * metadata.getDimTile());
            pixelY = (pixelY / 2) - (metadata.getDimTile());
            pixelX = pixelX < 0 ? 0 : pixelX;
            pixelY = pixelY < 0 ? 0 : pixelY;
            pixelX = pixelX > (metadata.getDato(zoom, "numX") * metadata.getDimTile() - metadata.getDimX()) ? (metadata.getDato(zoom, "numX") * metadata.getDimTile() - metadata.getDimX() - 1) : pixelX;
            pixelY = pixelY > (metadata.getDato(zoom, "numY") * metadata.getDimTile() - metadata.getDimY()) ? (metadata.getDato(zoom, "numY") * metadata.getDimTile() - metadata.getDimY() - 1) : pixelY;
        }
    }
    
    private BufferedImage renderizarMapa() {
        int coordInicialX = pixelX / metadata.getDimTile();
        int coordInicialY = pixelY / metadata.getDimTile();
        
        int coordFinalX = (pixelX + metadata.getDimX()) / metadata.getDimTile() + 
                (((pixelX + metadata.getDimX()) % metadata.getDimTile() == 0) ? -1 : 0);
        int coordFinalY = (pixelY + metadata.getDimY()) / metadata.getDimTile() +
                (((pixelX + metadata.getDimY()) % metadata.getDimTile()) == 0 ? -1 : 0);
                
        int numTilesX = coordFinalX - coordInicialX + 1;
        int numTilesY = coordFinalY - coordInicialY + 1;
        
        tilesCargados = new BufferedImage[numTilesX][numTilesY];
        try {
            for (int i = 0; i < tilesCargados.length; i++) {
                for (int j = 0; j < tilesCargados[0].length; j++) {
                    String filepath = generarFilepath(i, j, coordInicialX, coordInicialY);
                    BufferedImage imagen = ImageIO.read(new File(filepath));
                    tilesCargados[i][j] = imagen;
                }
            }
            BufferedImage unida = unirImagenes();
            return unida;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public void setLeadingPoint(Point leadingPoint) {
        this.leadingPoint = leadingPoint;
        this.leadingPoint.translate(pixelX, pixelY);
    }

    private String generarFilepath(int i, int j, int coordInicialX, int coordInicialY) {
        String resultado = "mapa/" + zoom;
        resultado += "/gm_" + (metadata.getDato(zoom, "baseX") + coordInicialX + i);
        resultado += "_" + (metadata.getDato(zoom, "baseY") + coordInicialY + + j);
        resultado += "_" + zoom +".png";
        return resultado;
    }
    
    private BufferedImage unirImagenes() {
        BufferedImage resultado = new BufferedImage(metadata.getDimTile() * tilesCargados.length, metadata.getDimY() * tilesCargados[0].length, BufferedImage.TYPE_INT_RGB);
        Graphics g = resultado.getGraphics();
        
        for (int x = 0; x < tilesCargados.length; x++) {
            for (int y = 0; y < tilesCargados[0].length; y++) {
                g.drawImage(tilesCargados[x][y], metadata.getDimTile() * x, metadata.getDimTile() * y, null);
            }
        }
                
        resultado = resultado.getSubimage(pixelX % 256, pixelY % 256, metadata.getDimX(), metadata.getDimY());
        
        return resultado;
    }

    public Point getPuntoCercano(int x, int y) {
        for (int i = 0; i < puntosDibujados.size(); i++) {
            if (puntosDibujados.get(i).distance(x, y) <= RADIO_PUNTO) {
                return puntosDibujados.get(i);
            }
        }
        return null;
    }
}
