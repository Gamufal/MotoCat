package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a catalog of motorbikes for a specific brand.
 * The catalog contains information about the brand and a list of motorbikes.
 * 
 * @author Kamil Kotorc
 * @version 2.1
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
    
     /**
     * Retrieves a motorbike from the catalog by its model name.
     *
     * @param modelName the model name of the motorbike to retrieve
     * @return the motorbike if found; null otherwise
     */
    public Motorbike getMotorbikeByModel(String modelName) {
        for (Motorbike motorbike : motorbikeList) {
            if (motorbike.getModel().equals(modelName)) {
                return motorbike;
            }
        }
        return null; 
    }
    
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
    
    /**
     * Clears all motorbikes in motorbike list
     */
    public void clearCatalog(){
        if (!motorbikeList.isEmpty()) {
           motorbikeList.clear();
        }
    }
    
    /**
     * Checks if the specified catalog is empty.
     *
     * @return true if the catalog is empty; false otherwise
     */
    public boolean isCatalogEmpty() {
        return motorbikeList.isEmpty();
    }
    
    /**
     * Counts the total number of motorbikes in the specified catalog.
     *
     * @return the number of motorbikes in the catalog
     */
    public int countMotorbikes() {
        return motorbikeList.size();
    }
    
}