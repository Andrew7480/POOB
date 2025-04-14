package domain;

public class DMaxwell {
    private int h;
    private int w;
    private int r;
    private int b;
    private int o;

    
    public DMaxwell(int newH, int newW, int newR, int newB, int newO) throws DMaxwellException{
        //if ( (newH == null ) || (newW == null ) || (newR == null ) || (newB == null) || (newO == null)) throw new DMaxwellException(DMaxwellException.NULL_VALUES);
        if ( (newH <= 0 ) || (newW <= 0 ) || (newR < 0 ) || (newB < 0) || (newO <0)) throw new DMaxwellException(DMaxwellException.VALUES_ERROR);
        h = newH;
        w = newW;
        r = newR;
        b = newB;
        o = newO;
    }


    public void movement(String direccion){
        
    }

}
