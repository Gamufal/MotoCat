package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kamil Kotorc
 * @version 1.0
 * 
 */

public class Catalog {  
    
    private String brand;
    private List<Motorbike> Motorbikes = new LinkedList<>();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Motorbike> getMotorbikes() {
        return Motorbikes;
    }

    public void setMotorbikes(List<Motorbike> Motorbikes) {
        this.Motorbikes = Motorbikes;
    }
    
}
