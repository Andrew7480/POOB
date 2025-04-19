package domain;

import java.util.ArrayList;

public class DMaxwell {
    private int h = 11;
    private int w = 20;
    private int r = 10;
    private int b = 10;
    private final int contR = 10;
    private final int contB = 10;
    private int o = 6;
    private final int[] azulDefaultDer = {22,31,76,128,144,175};
    private final int[] rojoDefaultDer = {27,34,63,168};
    private final int[] holesDefaultDer = {66,91,141};
    private final int[] azulDefaultIz = {25,96,102,162};
    private final int[] holesDefaultIz = {53,167,175};
    private final int[] rojoDefaultIz = {37,49,88,130,158,171};
    private  int[] azulDer;
    private  int[] rojoDer;
    private  int[] holesDer;
    private  int[] azulIz;
    private  int[] holesIz;
    private  int[] rojoIz;
    private int[][][] rest;
    private int[][] contIz;
    private int[][] contDer;

    public DMaxwell(){
        contDer = new int[][] {azulDefaultDer,rojoDefaultDer,holesDefaultDer};
        contIz = new int [][] {azulDefaultIz,rojoDefaultIz,holesDefaultIz};
        rest = new int[][][] {contDer,contIz};
        azulDer = azulDefaultDer.clone();
        rojoDer = rojoDefaultDer.clone();
        holesDer = holesDefaultDer.clone();
        azulIz = azulDefaultIz.clone();
        holesIz = holesDefaultIz.clone();
        rojoIz = rojoDefaultIz.clone();
    }
    
    public DMaxwell(int newH, int newW, int newR, int newB, int newO) throws DMaxwellException{
        //if ( (newH == null ) || (newW == null ) || (newR == null ) || (newB == null) || (newO == null)) throw new DMaxwellException(DMaxwellException.NULL_VALUES);
        if ( (newH <= 0 ) || (newW <= 0 ) || (newR < 0 ) || (newB < 0) || (newO <0)) throw new DMaxwellException(DMaxwellException.VALUES_ERROR);
        h = newH;
        w = newW;
        r = newR;
        b = newB;
        o = newO;
        
        contDer = new int[][] {azulDefaultDer,rojoDefaultDer,holesDefaultDer};
        contIz = new int [][] {azulDefaultIz,rojoDefaultIz,holesDefaultIz};
        rest = new int[][][] {contDer,contIz};
        azulDer = azulDefaultDer.clone();
        rojoDer = rojoDefaultDer.clone();
        holesDer = holesDefaultDer.clone();
        azulIz = azulDefaultIz.clone();
        holesIz = holesDefaultIz.clone();
        rojoIz = rojoDefaultIz.clone();
    }


    public void movement(char direccion) throws DMaxwellException{
        int[] espeAzulDer = azulDer.clone();
        int[] espeRojoDer = rojoDer.clone();
        int[] espeAzulIz = azulIz.clone();
        int[] espeRojoIz = rojoIz.clone();

        for (int i = 0; i < azulDer.length; i++) {
            int temporal = move(azulDer[i], direccion);
            if (verifyHoleDer(temporal)) espeAzulDer[i] = temporal;
            else {
                espeAzulDer[i] = -1;
                b --;
            }
        }
        for (int i = 0; i < rojoDer.length; i++) {
            int temporal = move(rojoDer[i], direccion);
            if (verifyHoleDer(temporal)) espeRojoDer[i] = temporal;
            else {
                espeRojoDer[i] = -1;
                r --;
            }
        }
        for (int i = 0; i < azulIz.length; i++) {
            int temporal = move(azulIz[i], direccion);
            if (verifyHoleIz(temporal)) espeAzulIz[i] = temporal;
            else {
                espeAzulIz[i] = -1;
                b --;
            }
        }
        for (int i = 0; i < rojoIz.length; i++) {
            int temporal =move(rojoIz[i], direccion);
            if (verifyHoleIz(temporal)) espeRojoIz[i] = temporal;
            else{
                espeRojoIz[i] = -1;
                r --;
            }
        }
        azulDer = removeInvalidPositions(espeAzulDer);
        rojoDer = removeInvalidPositions(espeRojoDer);
        azulIz = removeInvalidPositions(espeAzulIz);
        rojoIz = removeInvalidPositions(espeRojoIz);
        contDer = new int[][] {azulDer,rojoDer,holesDefaultDer};
        contIz = new int [][] {azulIz,rojoIz,holesDefaultIz};
        rest = new int[][][] {contDer,contIz};
    }

    private int  move(int num, char direccion) throws DMaxwellException{
        if (num < 0 || num >= h * w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
        
        if (direccion == 'u'){
            if (num < w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num - w;
        }
        if (direccion == 'd'){
            if (num >= (h - 1) * w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + w;
        }
        if (direccion == 'r'){
            if ((num + 1) % w == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + 1;
        }
        if (direccion == 'l'){
            if (num % w == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num - 1;
        }
        throw new DMaxwellException(DMaxwellException.INVALID_MOVE);
    }
    private boolean verifyHoleDer(int pos){
        for(int i:holesDer){
            if(pos == i){
                return false;
            }
        }
        return true;
    }
    private boolean verifyHoleIz(int pos){
        for(int i:holesIz){
            if(pos == i){
                return false;
            }
        }
        return true;
    }
    
    private int[] removeInvalidPositions(int[] array) {
        ArrayList<Integer> validPositions = new ArrayList<>();
        for (int i : array) {
            if (i != -1) {
                validPositions.add(i);
            }
        }
        int[] newArray = new int[validPositions.size()];
        for (int i = 0; i < validPositions.size(); i++) {
            newArray[i] = validPositions.get(i);
        }
        return newArray;
    }

    public int[] results(){
        int[] result = new int[4];
        result[3] = 100 - (((contR + contB)*100)/(r+b));

        int rojasBien = rojoDer.length;
        int azulesBien = azulIz.length;

        result[0] =  ((rojasBien * 100) / contR);
        result[1] = ((azulesBien * 100) / contB);

        result[2] = (((rojasBien + azulesBien) * 100) / (contR+contB));

        return result;
    }

    public int[][][] container(){
        return rest;
    }

}
