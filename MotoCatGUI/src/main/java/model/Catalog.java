package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a catalog of motorbikes for a specific brand.
 * The catalog contains information about the brand and a list of motorbikes.
 * 
 * @author Kamil Kotorc
 * @version 2.0
 */
public class Catalog {  
    
    /** The brand name of the motorbikes in the catalog. */
    private String brand;
    
    /** A list of motorbikes associated with the brand in this catalog. */
    private List<Motorbike> motorbikeList = new ArrayList<>();

    /**
     * Constructs a Catalog with the specified brand name.
     *
     * @param brand the name of the brand for this catalog
     */
    public Catalog(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the brand of the catalog.
     *
     * @return the brand name of the catalog
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand name of the catalog.
     *
     * @param brand the new brand name to set for the catalog
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the list of motorbikes in the catalog.
     *
     * @return a list of Motorbike objects in the catalog
     */
    public List<Motorbike> getMotorbikeList() {
        return motorbikeList;
    }

    /**
     * Sets the list of motorbikes for the catalog.
     *
     * @param motorbikes a list of Motorbike objects to set in the catalog
     */
    public void setMotorbikeList(List<Motorbike> motorbikes) {
        this.motorbikeList = motorbikes;
    }
    
    // version 3.0
    
    /**
     * Adds a new motorbike to the catalog.
     *
     * @param motorbike the motorbike to add to the list
     */
    public void AddMotorbike(Motorbike motorbike) {
        if (!motorbikeList.contains(motorbike)) {
            motorbikeList.add(motorbike);  
        }
    }
    
    /**
     * Removes a motorbike from the catalog.
     *
     * @param motorbike the motorbike to remove
     */
    public void RemoveMotorbike(Motorbike motorbike){
        if (motorbikeList.contains(motorbike)) {
        motorbikeList.remove(motorbike);
        }
    }
    
    
    
}