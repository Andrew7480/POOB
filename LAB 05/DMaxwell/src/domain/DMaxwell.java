package domain;
import java.util.ArrayList;
import java.util.Random;

public class DMaxwell {
    private int h = 11;
    private int w = 41;
    private int r = 10;
    private int b = 10;
    private int contR = 10;
    private int contB = 10;
    private int o = 6;
    private final int[] pared = {20,61,102,143,184,225,266,307,348,389,430};
    private final int[][] blueDefault = {{43,52,139,254,291,343},{67,201,226,310}}; // 226 -> 228
    private final int[][] redDefault = {{48,55,126,336},{79,112,193,277,326,360}};
    private final int[] defaultHoles = {116,129,175,288,356,364};
    private int posDemon;
    private  int[][] blues;
    private  int[][] red;
    private  int[] holes;
    private int[] wall;

    public DMaxwell(){
        blues = blueDefault.clone();
        red = redDefault.clone();
        holes = defaultHoles.clone();
        posDemon = 225;
    }
    
    public DMaxwell(int newH, int newW, int newR, int newB, int newO) throws DMaxwellException{ 
        //if ( (newH == null ) || (newW == null ) || (newR == null ) || (newB == null) || (newO == null)) throw new DMaxwellException(DMaxwellException.NULL_VALUES);
        if ( (newH <= 0 ) || (newW <= 0 ) || (newR < 0 ) || (newB < 0) || (newO <0)) throw new DMaxwellException(DMaxwellException.VALUES_ERROR);
        h = newH;
        w = newW;
        r = newR;
        b = newB;
        o = newO;
        contR = r;
        contB = b;
        //valuesOfTheWall(h,w);
        createNewItems();
    }

    private void valuesOfTheWall(int h, int w){
        wall = new int[h];
        int variable = h*((2*w) +1);
        int i = w;
        int centroPar = (h/2) * (2 * w + 1) + w;
        int contador = 0;
        while(contador < h){
            wall[contador] = i;
            if((int) variable/2 == i){
                posDemon = i;
            }
            if (i == centroPar){
                posDemon = i;
            }
            i+=((2*w)+1);
        }
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
                contB--;
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
        while (restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (!bluesTemp.contains(numeroAleatorio)){
                bluesTemp.add(numeroAleatorio);
                restantes--;
            }
        }
        restantes = r;
        while (restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (!bluesTemp.contains(numeroAleatorio) && !redTemp.contains(numeroAleatorio)){
                redTemp.add(numeroAleatorio);
                restantes--;
            }
        }

        restantes = o;
        while (restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (!bluesTemp.contains(numeroAleatorio) && !redTemp.contains(numeroAleatorio) && !holesTemp.contains(numeroAleatorio)){
                holesTemp.add(numeroAleatorio);
                restantes--;
            }
        }
        ArrayList<ArrayList<Integer>> temporalB = new ArrayList<>();
        temporalB.add(new ArrayList<>());
        temporalB.add(new ArrayList<>());
        for (int num : bluesTemp){
            int numeroAleatorio = random.nextInt(2);
            if (numeroAleatorio == 0) {
                temporalB.get(0).add(num);
            }
            if (numeroAleatorio == 1){
                temporalB.get(1).add(num);
            }
        }
        blues = toMatrix(temporalB);
        ArrayList<ArrayList<Integer>> temporalR = new ArrayList<>();
        temporalR.add(new ArrayList<>());
        temporalR.add(new ArrayList<>());
        for (int num : redTemp){
            int numeroAleatorio = random.nextInt(2);
            if (numeroAleatorio == 0) {
                temporalR.get(0).add(num);
            }
            if (numeroAleatorio == 1){
                temporalR.get(1).add(num);
            }
        }
        red = toMatrix(temporalR);
        holes = new int[o];
        int i = 0;
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
            for (int i : pared){
                if (num+1 == i && i != posDemon) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            }
            if (col + 1 == w / 2) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            if ((num + 1) % w == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + 1;
        }
    
        if (direccion == 'l') {
            for (int i : pared){
                if (num-1 == i && i != posDemon) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            }
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
        int rojasB = red[0].length;
        int azulB = blues[1].length;
        result[0] =  ((azulB * 100) / contR);
        result[1] = ((rojasB * 100) / contB);
        result[2] = (((rojasB + azulB) * 100) / (contR+contB));
        result[3] = 100 - (((contR + contB)*100)/(r+b));
        return result;
    }

    public int[][] container(){ 
        int[] azules = new int[blues[0].length + blues[1].length];
        System.arraycopy(blues[0], 0, azules, 0, blues[0].length);
        System.arraycopy(blues[1], 0, azules, blues[0].length, blues[1].length);

        int[] rojas = new int[red[0].length + red[1].length];
        System.arraycopy(red[0], 0, rojas, 0, red[0].length);
        System.arraycopy(red[1], 0, rojas, red[0].length, red[1].length);

        return new int[][] { azules, rojas, holes };
    }


    public int[][] toMatrix(ArrayList<ArrayList<Integer>> list2D) {
        int[][] result = new int[list2D.size()][];
        for (int i = 0; i < list2D.size(); i++) {
            ArrayList<Integer> innerList = list2D.get(i);
            result[i] = new int[innerList.size()];
            for (int j = 0; j < innerList.size(); j++) {
                result[i][j] = innerList.get(j);
            }
        }
        return result;
    }

    public void imprimirMatriz(int[] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("Fila " + i + ": ");
        }
    }



}
