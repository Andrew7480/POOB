package test;
import domain.*;
import java.awt.Color;
import java.beans.Transient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * The test class CTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CTest
{
    /**
     * Default constructor for test class CTest
     */
    public CTest()
    {
    }
    /*
        Pruebas de unidad laboratorio 06
     */
    @Test
    public void shouldNotSave(){
        City city = new City();
        File archivo = null;
        try{
            archivo = File.createTempFile("pruebaNotOpen",".randomtxt");
            File finalArchivo = archivo;
            finalArchivo.setReadOnly();
            assertThrows(CityException.class, () -> city.save(finalArchivo));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldNotOpen(){
        City city = new City();
        File archivo = null;
        try{
            archivo = File.createTempFile("pruebaNoAbre",".dat");
            File finalArchivo = archivo;
            finalArchivo.setReadable(false);
            assertThrows(CityException.class, () -> city.open(finalArchivo));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shoudlNotExport02(){
        City city = new City();
        File archivo = new File("C:\\hola.exe2?");
        assertThrows(CityException.class, () -> city.export02(archivo));
    }

    @Test
    public void shouldNotImport02(){
        City city = new City();
        File archivo = new File("C:\\hola.juan2?");
        assertThrows(CityException.class, () -> city.importar02(archivo));
    }

    @Test
    public void shoudlNotExport03(){
        City city = new City();
        File archivo = new File("C:\\hola.exe2?");
        assertThrows(CityException.class, () -> city.export03(archivo));
    }

    @Test
    public void shouldNotImport03(){
        City city = new City();
        File archivo = new File("C:\\hola.juan3?");
        assertThrows(CityException.class, () -> city.importar03(archivo));
    }



    @Test
    public void shouldExportAFile(){
        City city = new City();
        File archivo = null;
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        try{
            archivo = File.createTempFile("pruebaExportar",".txt");
            city.export01(archivo);
            assertTrue(archivo.exists());
        } catch (IOException e) {
            e.getMessage();
        } catch (CityException e) {
            e.getMessage();
        }
    }

    @Test
    public void shouldImportAFile(){
        City city = new City();
        File archivo = null;
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        try{
            archivo = File.createTempFile("pruebaExportar",".txt");
            city.export01(archivo);
            City city2 = city.importar01(archivo);
            assertFalse(city2.isEmpty(0,0));
            //Cuando nosotros importamos estamos reconociendo todo tipo de objetos definidos inicialmente
            //entonces solo colocamos un objeto donde no haya nada es decir null
        } catch (IOException e) {
            e.getMessage();
        } catch (CityException e) {
            e.getMessage();
        }
    }



    @Test
    public void shouldSaveANewCity(){
        City city = new City();
        File archivo = null;
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        try {
            archivo = File.createTempFile("prueba",".dat");
            city.save01(archivo);
            assertTrue(archivo.exists());
        }catch (CityException e){
            e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void shouldOpenAFileWithANewCity(){
        City city = new City();
        File archivo = null;
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        try {
            archivo = File.createTempFile("prueba",".dat");
            city.save01(archivo);
            City city2 = city.open01(archivo);
            assertFalse(city2.isEmpty(3,4));
            //Tras crear una ciudad y hacer cuatro tic tacs se observa que en la posicion
            // [3,4] hay un objeto entonces no puede estar vacio.
        }catch (CityException e){
            e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void shouldBeCreateAWalker(){
        City city = new City();
        Walker prueba = new Walker(city, 24, 24);
        assertFalse(city.isEmpty(24,24));
        assertTrue(prueba.isIndifferent());
    }
    @Test
    public void shouldWalkerMoveToTheNorth(){
        City city = new City();
        Walker prueba = new Walker(city, 20, 20);
        city.ticTac();
        //Tras un tic tac el walker debe dirigirse hacia el norte, es decir, en la posición 19, 20 debe encontrar no puede ser nulo.
        assertFalse(city.isEmpty(19,20));
        assertTrue(city.isEmpty(20,20));
    }
    @Test
    public void shouldWalkerBeHappy(){
        City city = new City();
        Walker messnerP = new Walker(city, 18, 16);
        city.ticTac();
        city.ticTac();
        city.ticTac();
        
        assertTrue(messnerP.isHappy());
        
    }
    @Test
    public void shouldCreateATrafficLight(){
        City city = new City();
        TrafficLight light = new TrafficLight(city,24,24);
        assertFalse(city.isEmpty(24,24));
    }
    @Test
    public void shouldChangeToTheRightColorTheTrafficLight(){
        City city = new City();
        TrafficLight light = new TrafficLight(city,24,24);
        assertTrue(light.getColor() == Color.red);
        city.ticTac();
        assertTrue(light.getColor() == Color.yellow);
        city.ticTac();
        assertTrue(light.getColor() == Color.green);
        city.ticTac();
        assertTrue(light.getColor() == Color.yellow);
    }
    @Test
    public void shouldCreateANewKindOfPerson(){
        City city = new City();
        Slider slide = new Slider(city,22,0);
        assertFalse(city.isEmpty(22,0));
    }
    @Test
    public void shouldNotCreateANewKindOfPerson(){
        City city = new City();
        Slider slide = new Slider(city,0,0);
        assertFalse(city.isEmpty(0,0));
    }
    @Test
    public void shouldMakeTheRightMovements(){ // Significativa
        City city = new City();
        Slider slide = new Slider(city, 22,2);
        Person prueba = new Person(city, 22,4);
        TrafficLight light = new TrafficLight(city,22,0);
        city.ticTac();
        city.ticTac();
        assertTrue(slide.isDissatisfied());
        city.ticTac();
        city.ticTac();
        city.ticTac();
        city.ticTac();
        assertTrue(slide.isDissatisfied());
    }
    @Test
    public void shouldNotCreateANewKindOfItem(){
        City city = new City();
        Road roadC = new Road(city,0,0);
        assertFalse(city.isEmpty(0,0));
    }
    @Test
    public void shouldCreateANewKindOfItem(){
        City city = new City();
        Road roadT = new Road(city,18,0);
        assertFalse(city.isEmpty(18,0));
        city.ticTac();
        assertFalse(city.isEmpty(18,1));
    }
    @Test
    public void shouldMakeTheRightFunction(){ // Significativa
        City city = new City();
        Road roadT = new Road(city,21,0);
        for (int i = 1; i < 25; i++){
            city.ticTac();
        }
        assertFalse(city.isEmpty(21,0) && city.isEmpty(21,24) && city.isEmpty(21, 16)); // Escogiendo posiciones de manera horizontal se evidencia que se esta construyendo la carretera
    }
    
    @Test
    public void shoulMoveIfITsDISSATISFIED(){ 
        City city = new City();
        Person persona = new Person(city,4,17);
        Schelling schelling = new Schelling(city,4,16);
        assertTrue(schelling.isDissatisfied());
        city.ticTac();
        assertTrue(city.isEmpty(4,16)); 
        assertFalse(city.isEmpty(4,15));
        assertTrue(schelling.isDissatisfied());
    }
    
    @Test
    public void shoulNotMoveIfItsHappy(){ 
        City city = new City();
        
        Schelling schelling2 = new Schelling(city,22,2);
        Person persona = new Person(city,21,2);
        Schelling schelling1 = new Schelling(city,22,1);
        
        assertTrue(schelling1.isHappy());
        city.ticTac();
        assertTrue(city.isEmpty(21,1)); 
        assertFalse(city.isEmpty(22,1));
        assertTrue(schelling1.isHappy());
    }
    
    @Test
    public void shoulBeINDIFFERENT(){ 
        City city = new City();
        Schelling schelling2 = new Schelling(city,24,22);
        assertTrue(schelling2.isIndifferent());
        
        Schelling schelling1 = new Schelling(city,20,22);
        Schelling schelling3 = new Schelling(city,20,23);
        
        assertTrue(schelling1.isIndifferent());
        assertTrue(schelling3.isIndifferent());
    
    }
    
    @Test
    public void shoulBeDISSATISFIED(){ 
        City city = new City();
        Person persona = new Person(city,20,23);
        Schelling schelling1 = new Schelling(city,20,22);
        
        assertTrue(schelling1.isDissatisfied());
    
    }
    
    @Test
    public void shoulBeIfItsHappy(){ 
        City city = new City();
        
        Schelling schelling2 = new Schelling(city,22,2);
        Person persona = new Person(city,21,2);
        Schelling schelling1 = new Schelling(city,22,1);
        
        assertTrue(schelling1.isHappy());
    }
    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
