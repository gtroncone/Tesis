/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gtroncone
 */
public class MetadataMapa {

    private static ArrayList<int[]> zooms;
    private static double[] escalas;
    private static Point[] puntoCTPorZoom;
    private static int maxZoom;
    private static int minZoom;
    private static int dimX;
    private static int dimY;
    private static int dimTile;
    private static final int VELOCIDAD_PROMEDIO_UNIDADES = 1000;
    
    public static void init() throws FileNotFoundException, IOException {
        File metadata = new File("./metadata.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(metadata));
        
        maxZoom = Integer.parseInt(br.readLine());
        minZoom = Integer.parseInt(br.readLine());
        dimX = Integer.parseInt(br.readLine());
        dimY = Integer.parseInt(br.readLine());
        dimTile = Integer.parseInt(br.readLine());
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
        
        for (int i = 0; i < zoom16.length; i++) {
            zoom16[i] = Integer.parseInt(br.readLine());
        }
        zooms.add(zoom16);
        
        for (int i = 0; i < zoom17.length; i++) {
            zoom17[i] = Integer.parseInt(br.readLine());
        }
        zooms.add(zoom17);
        
        for (int i = 0; i < zoom18.length; i++) {
            zoom18[i] = Integer.parseInt(br.readLine());
        }
        zooms.add(zoom18);

        for (int i = 0; i < zoom19.length; i++) {
            zoom19[i] = Integer.parseInt(br.readLine());
        }
        zooms.add(zoom19);
                
        escalas = new double[zooms.size()];
        for (int i = 0; i < escalas.length; i++) {
            escalas[i] = 156543.03392 * Math.cos(10.4806 * Math.PI / 180) / Math.pow(2, minZoom + i);
            // metersPerPx = 156543.03392 * Math.cos(latLng.lat() * Math.PI / 180) / Math.pow(2, zoom)
            // https://groups.google.com/forum/#!topic/google-maps-js-api-v3/hDRO4oHVSeM
        }
        
        puntoCTPorZoom = new Point[zooms.size()];
        for (int i = 0; i < puntoCTPorZoom.length; i++) {
            puntoCTPorZoom[i] = new Point((int) Math.floor(-60 / escalas[i]),
                (int) Math.floor(11190 / escalas[i]));
        }
        
        if (!verificacionDeMetadatos()) {
            throw new NullPointerException();
        }
    }
    
    public static double getDistanciaATransferencia(Point puntoI, int zoom) {
        return puntoCTPorZoom[zoom].distance(puntoI) * escalas[zoom];
    }

    public static int getMinutosPromedioATransferencia(Point puntoI, int zoom) {
        return ((int) Math.floor(getDistanciaATransferencia(puntoI, zoom) / VELOCIDAD_PROMEDIO_UNIDADES));
    }

    private static boolean verificacionDeMetadatos() {
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

    public static double[] getEscalas() {
        return escalas;
    }
    
    public static double getEscala(int index) {
        return escalas[index];
    }

    public static int getMaxZoom() {
        return maxZoom;
    }

    public static int getMinZoom() {
        return minZoom;
    }

    public static int getDimX() {
        return dimX;
    }

    public static int getDimY() {
        return dimY;
    }
    
    public static int getDimTile() {
        return dimTile;
    }

    public static int getDato(int zoom, String llave) {
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
