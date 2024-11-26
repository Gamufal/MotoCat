package model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * Represents a catalog of motorbikes for a specific brand.
 * The catalog contains information about the brand and a list of motorbikes.
 * 
 * @author Kamil Kotorc
 * @version 3.0
 */

//Lombok version B
@AllArgsConstructor
@Data  //replaces @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. 
public class CatalogBook {  
    
    
    private String brand;
    private List<Motorbike> motorbikeList = new ArrayList<>();
    
    
    
     /**
     * Retrieves a motorbike from the catalog by its model name.
     *
     * @param modelName the model name of the motorbike to retrieve
     * @return the motorbike if found; null otherwise
     */
    public Motorbike getMotorbikeByModel(String modelName) {
        for (Motorbike motorbike : motorbikeList) {
            if (motorbike.model().equals(modelName)) {
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

