package tests;

import java.util.stream.Stream;
import model.Catalog;
import model.Motorbike;
import static model.MotorbikeType.STANDARD;
import model.AppException;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Kamil Kotorc
 * @version 3.0
 */
public class CatalogTest {

    private Catalog catalog;
    
    @BeforeEach
    public void setUp() {
        catalog = new Catalog("TestCatalog");
    }
    
    @Test
    public void testRemoveMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        catalog.addMotorbike(motorbike);
        
        try {
            catalog.removeMotorbike(motorbike);  
        } catch (AppException e) {
            fail("An exception should NOT be thrown");
        }
    }
    
    @Test
    public void testRemoveMotorbikeException() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
                
        try {
            catalog.removeMotorbike(motorbike);  
            fail("An exception should be thrown");
        } catch (AppException e) {   
        }
    }
    
    @Test
    public void testAddMotorbike() {
        int sizePlusOne = catalog.getMotorbikeList().size() + 1;
        
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        catalog.addMotorbike(motorbike);
                
        assertEquals(sizePlusOne, catalog.getMotorbikeList().size(), 0.01, "ArrayList size should be equal to size + 1");
    }
    
    @Test
    public void testClearCatalog() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        catalog.addMotorbike(motorbike);
        
        catalog.clearCatalog();
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "ArrayList size should be equal to 0");
        
    }
    
    @Test
    public void testIsCatalogEmpty() {
        
        assertTrue(catalog.isCatalogEmpty(), "Should return true");
        
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        catalog.addMotorbike(motorbike);
        
        assertTrue(!catalog.isCatalogEmpty(), "Should return true");
    }
    
    @ParameterizedTest
    @MethodSource("stringProvider")
    public void testUpdateMotorbikePrice(Double argument) {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        catalog.addMotorbike(motorbike);
        
        try{
            catalog.updateMotorbikePrice(motorbike, argument);
        } catch (AppException e) {
            fail("An exception should NOT be thrown");
        }

    }

//    static Stream<Double> stringProvider() {
//        return Stream.of(5.55,9.99);
//    }
//  
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//        
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
    
}