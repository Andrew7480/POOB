package domain;  

public class Course extends Unit{
    
    private Integer credits;
    private Integer inPerson;
    
    public Course(String code, String name)throws Plan15Exception{
        super(code, name);
    }    
    
    public Course(String code, String name, int credits) throws Plan15Exception{
        super(code, name);
        this.credits=credits;
    }
    
    public Course(String code, String name, int credits, int inPerson) throws Plan15Exception{
        this(code, name, credits);
        this.inPerson=inPerson;
    }
       
    
    @Override
    public int credits() throws Plan15Exception{
       if (credits == null) throw new Plan15Exception(Plan15Exception.CREDITS_UNKNOWN);
       if (credits <=0) throw new Plan15Exception(Plan15Exception.CREDITS_ERROR);
       if ((inPerson!=null) && (inPerson> 3*credits)) throw new Plan15Exception(Plan15Exception.CREDITS_ERROR);
       return credits;
    }    
    
    
    @Override
    public int inPerson() throws Plan15Exception{
       if (inPerson == null) throw new Plan15Exception(Plan15Exception.IN_PERSON_UNKNOWN);
       if (inPerson<=0) throw new Plan15Exception(Plan15Exception.IN_PERSON_ERROR);
       if ((credits!=null) && (inPerson> 3*credits)) throw new Plan15Exception(Plan15Exception.CREDITS_ERROR);
       return inPerson;
    }  
    
    @Override
    public String data(){
        String theData= code+": "+name;
        try{
            theData= theData+". Creditos:" +credits()+"["+inPerson()+"+"+independent()+"]";
        } catch (Plan15Exception e){
        }
        return theData;
    }
    @Override
    public String data(String e){
        String theData= code+": "+name;
        try{
            int totalHoras =credits()*(inPerson()+independent());
            theData= theData+". Creditos:" +credits()+" Horas presenciales: " +inPerson()+" Horas independientes: "+independent() + "\r\n        Total horas: "+ totalHoras ;

        } catch (Plan15Exception i){
        }
        return theData;
    }
}
