/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    
    public Render() {        
        try {
            metadata = new MetadataMapa();
            zoom = metadata.getMinZoom();
            pixelX = 0;
            pixelY = 0;
        } catch (NullPointerException e) {
            throw e;
        }
    }
    
    public BufferedImage getRender() {
        mapa = renderizarMapa();
        // mapa = añadirLoDemas();
        return mapa;
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
}