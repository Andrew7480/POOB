package domain;
import java.util.ArrayList;
import java.util.Random;

public class DMaxwell {
    private int h = 11;
    private int w = 40+1;
    private int r = 10;
    private int b = 10;
    private int contR = 10;
    private int contB = 10;
    private int o = 6;
    private final int[][] blueDefault = {{43,52,139,254,291,343},{67,201,228,310}};
    private final int[][] redDefault = {{48,55,126,336},{79,112,193,277,326,360}};
    private final int[] defaultHoles = {116,129,175,288,356,364};
    private  int[][] blues;
    private  int[][] red;
    private  int[] holes;

    public DMaxwell(){
        blues = blueDefault.clone();
        red = redDefault.clone();
        holes = defaultHoles.clone();
    }
    
    public DMaxwell(int newH, int newW, int newR, int newB, int newO) throws DMaxwellException{ 
        //if ( (newH == null ) || (newW == null ) || (newR == null ) || (newB == null) || (newO == null)) throw new DMaxwellException(DMaxwellException.NULL_VALUES);
        if ( (newH <= 0 ) || (newW <= 0 ) || (newR < 0 ) || (newB < 0) || (newO <0)) throw new DMaxwellException(DMaxwellException.VALUES_ERROR);
        h = newH;
        w = newW + 1;
        r = newR;
        b = newB;
        o = newO;
        createNewItems();
    }


    public void movement(char direccion) throws DMaxwellException{
        int [] bluesTempoL = blues[0].clone();
        int [] bluesTempoR = blues[1].clone();
        int [] redTempoL = red[0].clone();
        int [] redTempoR = red[1].clone();

        for (int i = 0; i < bluesTempoL.length; i++) {
            int temporal = move(bluesTempoL[i], direccion);
            if (verifyHole(temporal)) bluesTempoL[i] = temporal;
            else {
                bluesTempoL[i] = -1;
                contB --;
            }
        }
        for (int i = 0; i < bluesTempoR.length; i++) {
            int temporal = move(bluesTempoR[i], direccion);
            if (verifyHole(temporal)) bluesTempoR[i] = temporal;
            else {
                bluesTempoR[i] = -1;
                contB --;
            }
        }
        for (int i = 0; i < redTempoL.length; i++) {
            int temporal = move(redTempoL[i], direccion);
            if (verifyHole(temporal)) redTempoL[i] = temporal;
            else {
                redTempoL[i] = -1;
                contR --;
            }
        }
        for (int i = 0; i < redTempoR.length; i++) {
            int temporal = move(redTempoR[i], direccion);
            if (verifyHole(temporal)) redTempoR[i] = temporal;
            else {
                redTempoR[i] = -1;
                contR --;
            }
        }
        blues[0] = removeInvalidPositions(bluesTempoL);
        blues[1] = removeInvalidPositions(bluesTempoR);
        red[0] = removeInvalidPositions(redTempoL);
        red[1] = removeInvalidPositions(redTempoR);
    }
    
    private boolean isLeft(int pos) {
        return pos % w < w / 2;
    }
    
    private boolean isRight(int pos) {
        return pos % w > w / 2;
    }

    private void createNewItems(){
        Random random = new Random();
        ArrayList<Integer> bluesTemp = new ArrayList<>();
        ArrayList<Integer> redTemp = new ArrayList<>();
        ArrayList<Integer> holesTemp = new ArrayList<>();

        int restantes = b;
        while ( restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (! bluesTemp.contains(numeroAleatorio)){
                bluesTemp.add(numeroAleatorio);
                restantes--;
            }
        }
        restantes = r;
        while ( restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (! bluesTemp.contains(numeroAleatorio) && ! redTemp.contains(numeroAleatorio)){
                redTemp.add(numeroAleatorio);
                restantes--;
            }
        }

        restantes = o;
        while ( restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (! bluesTemp.contains(numeroAleatorio) && ! redTemp.contains(numeroAleatorio) && ! holesTemp.contains(numeroAleatorio)){
                holesTemp.add(numeroAleatorio);
                restantes--;
            }
        }
        blues = new int[2][b];
        int i = 0;
        for (int num : bluesTemp){
            int numeroAleatorio = random.nextInt(2);
            if (numeroAleatorio == 0) {
                blues[0][i] = num;
                i++;
            }
            if (numeroAleatorio == 1){
                blues[1][i] = num;
                i++;
            }
        }
        red = new int[2][r];
        i = 0;
        for (int num : redTemp){
            int numeroAleatorio = random.nextInt(2);
            if (numeroAleatorio == 0) {
                red[0][i] = num;
                i++;
            }
            if (numeroAleatorio == 1){
                red[1][i] = num;
                i++;
            }
        }
        holes = new int[o];
        i = 0;
        for (int num : holesTemp){
            holes[i]= num;
            i++;
        }
    }


    private int move(int num, char direccion) throws DMaxwellException {
        if (num < 0 || num >= h * w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
        int col = num % w;
    
        if (direccion == 'u') {
            if (num < w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num - w;
        }
    
        if (direccion == 'd') {
            if (num >= (h - 1) * w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + w;
        }
    
        if (direccion == 'r') {
            if (col + 1 == w / 2) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            if ((num + 1) % w == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + 1;
        }
    
        if (direccion == 'l') {
            if (col == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num - 1;
        }
        throw new DMaxwellException(DMaxwellException.INVALID_MOVE);
    }

    private boolean verifyHole(int pos){
        for(int i : holes){
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
 
         int rojasBien = redDefault[0].length;
         int azulesBien = blueDefault[1].length;
 
         result[0] =  ((rojasBien * 100) / contR);
         result[1] = ((azulesBien * 100) / contB);
 
         result[2] = (((rojasBien + azulesBien) * 100) / (contR+contB));
 
         return result;
    }

    public int[][] container(){ 
        int[] azules = new int[blues[0].length + blues[1].length];
        System.arraycopy(blues[0], 0, azules, 0, blues[0].length);
        System.arraycopy(blues[1], 0, azules, blues[0].length, blues[1].length);

        int[] rojas = new int[red[0].length + red[1].length];
        System.arraycopy(red[0], 0, rojas, 0, red[0].length);
        System.arraycopy(red[1], 0, rojas, red[0].length, red[1].length);

        return new int[][] { azules, rojas, defaultHoles };
    }

}
