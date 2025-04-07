package domain; 

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Plan15
 * @author POOB  
 * @version ECI 2025
 */

public class Plan15{
    private ArrayList<Unit> units;
    private TreeMap<String,Course> courses;

    /**
     * Create a Plan15
     */
    public Plan15()throws Plan15Exception{
        units = new ArrayList<Unit>();
        courses = new TreeMap<String,Course>();
        addSome();
    }

    private void addSome() throws Plan15Exception{
        String [][] courses = {{"PRI1", "Proyecto Integrador","9","3"},
                              {"DDYA", "Diseño de Datos y Algoritmos","4","4"},
                              {"MPIN", "Matematicas para Informatica","3","4"}};
        
        for (String [] c: courses){
            addCourse(c[0],c[1],c[2],c[3]);
        }
        String [][] Core = {{"FCC","Nucleo formacion por comun por campo", "50", "PRI1\nDDYA\nMPIN"}};
        for (String [] c: Core){
            addCore(c[0],c[1],c[2],c[3]);
        }
    }


    /**
     * Consult a unit
     */
    public Unit consult(String name){
        Unit c=null;
        for(int i=0;i<units.size() && c == null;i++){
            if (units.get(i).code().compareToIgnoreCase(name)==0) 
               c=units.get(i);
        }
        return c;
    }

    
    /**
     * Add a new course
    */
    public void addCourse(String code, String name, String credits, String inPerson)throws Plan15Exception{ 
        if(courses.get(code.toUpperCase()) != null) throw new Plan15Exception(Plan15Exception.NOMENCLATURE_COURSE_ALREADY_EXITS);
        if (credits.equals("")) throw new Plan15Exception(Plan15Exception.PERCENTAGE_NOT_FOUND);
        if (inPerson.equals("")) throw new Plan15Exception(Plan15Exception.IN_PERSON_UNKNOWN);
        if (!verifyInt(credits)) throw new Plan15Exception(Plan15Exception.INT_ERROR);
        if (!verifyInt(inPerson)) throw new Plan15Exception(Plan15Exception.INT_ERROR);
        Course nc=new Course(code,name,Integer.parseInt(credits),Integer.parseInt(inPerson));
        units.add(nc);
        courses.put(code.toUpperCase(),nc);
    }
    
    /**
     * Add a new core
    */
    public void addCore(String code, String name, String percentage, String theCourses)throws Plan15Exception{ 
        if (percentage.equals("")) throw new Plan15Exception(Plan15Exception.PERCENTAGE_NOT_FOUND);
        if (!verifyInt(percentage)) throw new Plan15Exception(Plan15Exception.INT_ERROR);
        for(Unit u :units ){
            if (u.code().equals(code)) throw new Plan15Exception(Plan15Exception.NOMENCLATURE_CORE_ALREADY_EXITS);
        } 
        Core c = new Core(code,name,Integer.parseInt(percentage));
        String [] aCourses= theCourses.split("\n");
        for (String b : aCourses){
            if (courses.get(b.toUpperCase())==null) throw new Plan15Exception(Plan15Exception.COURSE_NOT_FOUND);
            c.addCourse(courses.get(b.toUpperCase()));
        }
        units.add(c);
    }

    private boolean verifyInt(String cadena){
        return cadena.matches("-?\\d+");
    }

    /**
     * Consults the units that start with a prefix
     * @param  
     * @return 
     */
    public ArrayList<Unit> select(String prefix){
        ArrayList <Unit> answers=new ArrayList<Unit>();
        prefix=prefix.toUpperCase();
        for(int i=0;i<units.size();i++){
            if(units.get(i).code().toUpperCase().startsWith(prefix)){
                answers.add(units.get(i));
            }   
        }
        return answers;
    }
    
    /**
     * Consult selected units
     * @param selected
     * @return  
     */
    public String data(ArrayList<Unit> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(units.size()+ " unidades\n");
        for(Unit p : selected) {
            try{
                answer.append('>' + p.data());
                answer.append("\n");
            }catch(Plan15Exception e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
     /**
     * Return the data of units with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }

    /**
     * Return the data of courses
     * @param prefix
     * @return  
     */ 
    public String search(){
        return data(courses);
    }

        /**
     * Consult selected courses
     * @param selected
     * @return  
     */
    public String data(TreeMap<String,Course> courses){
        StringBuffer answer=new StringBuffer();
        answer.append(units.size()+ " unidades\n");
        for(Course p : courses.values()) {
            answer.append('>' + p.data(""));
            answer.append("\n");
        }    
        return answer.toString();
    }

    /**
     * Return the data of all units
     * @return  
     */    
    public String toString(){
        return data(units);
    }
    
    /**
     * Consult the number of units
     * @return 
     */
    public int numberUnits(){
        return units.size();
    }

}
