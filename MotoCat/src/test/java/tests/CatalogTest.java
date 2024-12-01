package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Catalog;
import model.Motorbike;
import static model.MotorbikeType.STANDARD;
import model.AppException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Kamil Kotorc
 * @version 4.0
 */
public class CatalogTest {

    private Catalog catalog;
    
    @BeforeEach
    public void setUp() {
        catalog = new Catalog("TestCatalog");
    }
    
    // testing class for motorbikeList being null
    
    @Test
    public void testNullArrayList() {
        List<Motorbike> emptyList = new ArrayList<>();
        Catalog testCatalog = new Catalog("test",null);
        
        assertFalse(testCatalog.getMotorbikeList() == null, "Catalog list should NOT be null");
        
        testCatalog.setMotorbikeList(null);
        
        assertFalse(testCatalog.getMotorbikeList() == null, "Catalog list should NOT be null");
        
        assertEquals(testCatalog.getMotorbikeList(), emptyList, "Catalog list should be empty ArrayList");
    }
    
    // testing removeMotorbike
    
    @Test
    public void testResultRemoveMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
        }
        try {
            catalog.removeMotorbike(motorbike);
        } catch (AppException ex) {
            fail("An exception should NOT be thrown");
        }
        
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should be equal to 0");
    }
    
    @Test
    public void testExceptionRemoveMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
                
        try {
            catalog.removeMotorbike(motorbike);  
            fail("An exception should be thrown");
        } catch (AppException e) {   
        }
    }
    
    // testing addMotorbike
    
    @Test
    public void testResultAddMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
            fail("An exception should NOT be thrown");
        }
                
        assertEquals(1, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should increase by 1");
    }
    
    @ParameterizedTest
    @MethodSource("motorbikeStream")
    public void testExceptionAddMotorbike(Motorbike motorbike) {
        if(motorbike == null){
            try {
                catalog.addMotorbike(motorbike);
                fail("An exception should be thrown for null");
            } catch (AppException ex) {
            }
        } else {
            try {
                catalog.addMotorbike(motorbike);
                catalog.addMotorbike(motorbike);
                fail("An exception should be thrown for duplicate");
            } catch (AppException ex) {
            }
        }
    }
    
    static Stream<Motorbike> motorbikeStream() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        return Stream.of(motorbike,null);
    }
    
    // testing editMotorbike
    
    @Test
    public void testResultEditMotorbike() {
        Motorbike oldMotorbike = new Motorbike("TestMotorbike1",1.11,2,3,STANDARD);
        Motorbike newMotorbike = new Motorbike("TestMotorbike2",2.22,4,6,STANDARD);
        
        try {
            catalog.addMotorbike(oldMotorbike);
            catalog.editMotorbike(oldMotorbike,newMotorbike);
        } catch (AppException ex) {
            fail("An exception should NOT be thrown");
        }
        
        assertFalse(catalog.getMotorbikeList().contains(oldMotorbike), "Catalog should NOT contain oldMotorbike");        
        assertTrue(catalog.getMotorbikeList().contains(newMotorbike), "Catalog should contain newMotorbike");
    }
    
    @Test
    public void testExceptionEditMotorbike() {
        Motorbike nullMotorbike = null;
        Motorbike validMotorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        
        try {
            catalog.editMotorbike(validMotorbike,validMotorbike);
            fail("An exception should be thrown for editing motorbike NOT listed in catalog");
        } catch (AppException ex) {
        }
        
        try {
            catalog.addMotorbike(validMotorbike);
            catalog.editMotorbike(validMotorbike, nullMotorbike);
            fail("An exception should be thrown for new motorbike being null");
        } catch (AppException ex) {
        }   
    }
    
    // testing clearCatalog
    
    @Test
    public void testClearCatalog() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
        }
        catalog.clearCatalog();
        
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should be equal to 0");
        
        catalog.clearCatalog();
        
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should be equal to 0");     
    }
    
    // testing isCatalogEmpty
    
    @Test
    public void testIsCatalogEmpty() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        
        assertTrue(catalog.isCatalogEmpty(), "Method should return true");
        
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
        }
        
        assertFalse(catalog.isCatalogEmpty(), "Method should return false");
    }
  
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
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
    
}