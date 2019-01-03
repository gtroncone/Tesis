/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis;

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
            pixelX = 300;
            pixelY = 250;
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
