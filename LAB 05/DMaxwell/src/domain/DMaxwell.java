package domain;
import java.util.ArrayList;
import java.util.Random;
/**
 * DMaxwell
 * 
 * @author  Tulio Riaño and Andrés Cardozo
 * @version 95%
 */
public class DMaxwell {
    private int h = 11;
    private int w = 41;
    private int r = 10;
    private int b = 10;
    private int o = 6;
    private int contR = 10;
    private int contB = 10;
    private final int posDemonDefault = 225;
    private final int[] paredDefault = {20,61,102,143,184,225,266,307,348,389,430};
    private final int[] blueDefault = {43,52,139,254,291,343,67,201,228,310}; // 226 -> 228
    private final int[] redDefault = {48,55,126,336,79,112,193,277,326,360};
    private final int[] defaultHoles = {116,129,175,288,356,364};
    private  int[] blues;
    private  int[] red;
    private  int[] holes;
    private int[] wall;
    private int posDemon;
    /**
     * Constructor of DMaxwell
     **/
    public DMaxwell(){
        blues = blueDefault.clone();
        red = redDefault.clone();
        holes = defaultHoles.clone();
        wall = paredDefault.clone();
        posDemon = posDemonDefault;
    }
    /**
     * Constructor of a new DMaxwell board
     * @param newH
     * @param newW
     * @param newR
     * @param newB
     * @param newO
     * @throws DMaxwellException
    * */
    public DMaxwell(int newH, int newW, int newR, int newB, int newO) throws DMaxwellException{ 
        //if ( (newH == null ) || (newW == null ) || (newR == null ) || (newB == null) || (newO == null)) throw new DMaxwellException(DMaxwellException.NULL_VALUES);
        if ( (newH <= 0 ) || (newW <= 0 ) || (newR < 0 ) || (newB < 0) || (newO <0)) throw new DMaxwellException(DMaxwellException.VALUES_ERROR);
        if ((newW > 60) || (newH) > 30) throw new DMaxwellException(DMaxwellException.INVALID_DIMENSIONS);
        h = newH;
        w = newW+1;
        r = newR;
        b = newB;
        o = newO;
        contR = r;
        contB = b;
        valuesOfTheWall(h,w);
        createNewItems();
        System.out.println(h+" "+w+" "+r+" "+b+" "+o);
        //imprimirMatriz(blues);
        //imprimirMatriz(red);
        //imprimirMatriz(holes);
        imprimirMatriz(wall);
        print("demon " + posDemon);
        
    }
    private void print(Object o){
        System.out.println(o);
    }

    /*
     * Create new items in the board
     */
    private void createNewItems(){
        Random random = new Random();
        ArrayList<Integer> bluesTemp = new ArrayList<>();
        ArrayList<Integer> redTemp = new ArrayList<>();
        ArrayList<Integer> holesTemp = new ArrayList<>();
        ArrayList<Integer> wall1 = convertirArregloAArrayList(wall);
        int restantes = b;
        while (restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (!bluesTemp.contains(numeroAleatorio) && !wall1.contains(numeroAleatorio) && numeroAleatorio != posDemon){
                bluesTemp.add(numeroAleatorio);
                restantes--;
            }
        }
        restantes = r;
        while (restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (!bluesTemp.contains(numeroAleatorio) && !redTemp.contains(numeroAleatorio) && !wall1.contains(numeroAleatorio) && numeroAleatorio != posDemon){
                redTemp.add(numeroAleatorio);
                restantes--;
            }
        }

        restantes = o;
        while (restantes > 0){
            int numeroAleatorio = random.nextInt(h*w);
            if (!bluesTemp.contains(numeroAleatorio) && !redTemp.contains(numeroAleatorio) && !holesTemp.contains(numeroAleatorio) && numeroAleatorio != posDemon){
                holesTemp.add(numeroAleatorio);
                restantes--;
            }
        }        
        blues= convertirArrayListAArreglo(bluesTemp);
        red = convertirArrayListAArreglo(redTemp);

        holes = new int[o];
        int i = 0;
        for (int num : holesTemp){
            holes[i]= num;
            i++;
        }

        
    }
    /*
     * Determine the values of the board
     * @param int h
     * @param int wi
     */
    private void valuesOfTheWall(int h, int wi){
        wi = (wi-1)/2;
        wall = new int[h];
        int variable = h*((2*wi) +1);
        int i = wi;
        int centroPar = (h/2) * (2 * w + 1) + wi;
        int contador = 0;
        while(i < variable){
            wall[contador] = i;
            
            if((int) variable/2 == i){
                posDemon = i;
            }
            else if (i == centroPar){
                posDemon = i;
            }
            i+=((2*wi)+1);
            contador ++;
        }
    }
    
    /***
     * Do the movement of the particle, depends of direction
     * @param direccion
     * @throws DMaxwellException
     */

    public void movement(char direccion) throws DMaxwellException{
        int [] bluesTempo = blues.clone();
        int [] redTempo = red.clone();

        for (int i = 0; i < bluesTempo.length; i++) {
            try {
                int temporal = move(bluesTempo[i], direccion);
                if (verifyHole(temporal)) bluesTempo[i] = temporal;
                else {
                    bluesTempo[i] = -1;
                    contB --;
                }
            } catch (DMaxwellException e) {
                
            }

        }
        for (int i = 0; i < redTempo.length; i++) {
            try{
                int temporal = move(redTempo[i], direccion);
                if (verifyHole(temporal)) redTempo[i] = temporal;
                else {
                    redTempo[i] = -1;
                    contR --;
                }
            }
            catch (DMaxwellException e) {
                
            }
        }
        blues = removeInvalidPositions(bluesTempo);
        red = removeInvalidPositions(redTempo);
    }
    private boolean verifyParticle(int[] blues1 , int[] red1,int pos){
        for(int i : blues1){
            if(pos == i){
                return false;
            }
        }
        for(int i : red1){
            if(pos == i){
                return false;
            }
        }
        return true;
    }

    /*
     * Determine if the particle is in the left chamber 
     * @param pos
     * @return if the particle is in the left chamber 
     */
    private boolean isLeft(int pos) {
        return pos % w < w / 2;
    }
    /*
     * Determine if the particle is in the right chamber 
     * @param pos
     * @return if the particle is in the right chamber 
     */
    private boolean isRight(int pos) {
        return pos % w > w / 2;
    }

    /*
     * Validates the movement in the direction 
     * @param num
     * @param direccion
     * @return the position after the move
     * @throws DMaxwellException
     */
    private int move(int num, char direccion) throws DMaxwellException {
        int col = num % w;
        
        if (num < 0 || num >= h * w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
        if (direccion == 'u') {
            for (int i : wall){
                if (i == num - w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            }
            if (num < w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num - w;
        }

        if (direccion == 'd') {
            for (int i : wall){
                if (i == num + w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            }
            if (num >= (h - 1) * w) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            //if (blues.containts(num+w) ) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + w;
        }
    
        if (direccion == 'r') {
            for (int i : wall){
                if (num+1 == i && i != posDemon) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            }
            if ((num + 1) % w == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num + 1;
        }
    
        if (direccion == 'l') {
            for (int i : wall){
                if (num-1 == i && i != posDemon) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            }
            if (col == 0) throw new DMaxwellException(DMaxwellException.INVALID_MOVEMENT);
            return num - 1;
        }
        throw new DMaxwellException(DMaxwellException.INVALID_MOVE);
    }
    /*
     * Verify if the particle is in a hole position 
     * @param pos
     * @return if the position is in the hole
     */
    private boolean verifyHole(int pos){
        for(int i : holes){
            if(pos == i){
                return false;
            }
        }
        return true;
    }


    /*
     * it eliminates invalid positions, that is, when the particle is already eaten by a hole
     * @param array
     * @return
     */
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
    /*
     * Calculate the results, i.e. the number of red particles on the left and blue particles on the right and their respective percentages or losses
     * @return
     */
    public int[] results(){
        /* 
        int[] result = new int[4];
        int rojasB = red[0].length;
        int azulB = blues[1].length;
        result[0] =  ((azulB * 100) / contR);
        result[1] = ((rojasB * 100) / contB);
        result[2] = (((rojasB + azulB) * 100) / (contR+contB));
        result[3] = 100 - (((contR + contB)*100)/(r+b));
        return result;
        */
        
        return new int[] {0,0,0,0,0};
    }
    /*
     * a matrix with the position of each particle including the wall values 
     * @return 
     */
    public int[][] container(){ 

        return new int[][] { blues, red, holes, wall};
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
    public static int[] convertirArrayListAArreglo(ArrayList<Integer> lista) {
        int[] arreglo = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            arreglo[i] = lista.get(i);
        }
        return arreglo;
    }

    public static ArrayList<Integer> convertirArregloAArrayList(int[] arreglo) {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int num : arreglo) {
            lista.add(num);
        }
        return lista;
    }

    public void imprimirMatriz(int[] matriz) {
        for(int num : matriz){
            System.out.println(num);
        }
    }
    public void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("Fila " + i + ": ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }



}
