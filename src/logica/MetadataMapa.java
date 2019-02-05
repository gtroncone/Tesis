/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author gtroncone
 */
public class MetadataMapa {

    private final ArrayList<int[]> zooms;
    private final double[] escalas;
    private final int maxZoom;
    private final int minZoom;
    private final int dimX;
    private final int dimY;
    private final int dimTile;
    private final int distanciaPromedioATransferencia;

    public MetadataMapa() throws NullPointerException {
        maxZoom = 19;
        minZoom = 16;
        dimX = 1280;
        dimY = 696;
        dimTile = 256;
        zooms = new ArrayList<>();
        int[] zoom16 = new int[4];
        int[] zoom17 = new int[4];
        int[] zoom18 = new int[4];
        int[] zoom19 = new int[4];

        /*
            Indice 0: representa la baseX
            Indice 1: representa la baseY
            Indice 2: representa numX
            Indice 3: representa numY
         */

        zoom16[0] = 20584;
        zoom16[1] = 30840;
        zoom16[2] = 8;
        zoom16[3] = 8;
        zooms.add(zoom16);

        zoom17[0] = 41168;
        zoom17[1] = 61680;
        zoom17[2] = 16;
        zoom17[3] = 16;
        zooms.add(zoom17);

        zoom18[0] = 82336;
        zoom18[1] = 123360;
        zoom18[2] = 32;
        zoom18[3] = 32;
        zooms.add(zoom18);
        
        zoom19[0] = 164672;
        zoom19[1] = 246720;
        zoom19[2] = 64;
        zoom19[3] = 64;
        zooms.add(zoom19);
        
        distanciaPromedioATransferencia = (14700 + 12500) / 2;
        
        escalas = new double[zooms.size()];
        for (int i = 0; i < escalas.length; i++) {
            escalas[i] = 156543.03392 * Math.cos(10.4806 * Math.PI / 180) / Math.pow(2, minZoom + i);
            // metersPerPx = 156543.03392 * Math.cos(latLng.lat() * Math.PI / 180) / Math.pow(2, zoom)
            // https://groups.google.com/forum/#!topic/google-maps-js-api-v3/hDRO4oHVSeM
        }

        if (!verificacionDeMetadatos()) {
            throw new NullPointerException();
        }
    }

    public int getDistanciaPromedioATransferencia() {
        return distanciaPromedioATransferencia;
    }

    private boolean verificacionDeMetadatos() {
        if (maxZoom < minZoom) {
            return false;
        } else {
            for (int i = 0; i < zooms.size() - 1; i++) {
                for (int j = 0; j < zooms.get(0).length; j++) {
                    if (2 * zooms.get(i)[j] != zooms.get(i + 1)[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public double[] getEscalas() {
        return escalas;
    }
    
    public double getEscala(int index) {
        return escalas[index];
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public int getMinZoom() {
        return minZoom;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }
    
    public int getDimTile() {
        return dimTile;
    }

    public int getDato(int zoom, String llave) {
        int indiceArray;
        int indiceZoom;

        switch (zoom) {
            case 16:
                indiceArray = 0;
                break;
            case 17:
                indiceArray = 1;
                break;
            case 18:
                indiceArray = 2;
                break;
            case 19:
                indiceArray = 3;
                break;
            default:
                return -1;
        }

        switch (llave) {
            case "baseX":
                indiceZoom = 0;
                break;
            case "baseY":
                indiceZoom = 1;
                break;
            case "numX":
                indiceZoom = 2;
                break;
            case "numY":
                indiceZoom = 3;
                break;
            default:
                return -1;
        }

        return zooms.get(indiceArray)[indiceZoom];
    }
}
