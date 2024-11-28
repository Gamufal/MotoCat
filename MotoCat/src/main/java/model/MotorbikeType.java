package model;

import java.util.Vector;

/**
 * Enum representing the different types of motorbikes.
 * Each constant corresponds to a specific category of motorbike.
 * 
 * @author Kamil Kotorc
 * @version 3.1
 */
public enum MotorbikeType {
    
    STANDARD, SPORT, CRUISER, TOURING, ENDURO;
    
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
    
    public static MotorbikeType fromString(String value) {
        return MotorbikeType.valueOf(value.toUpperCase());
    }
    
    /**
     * list
     * @return list
     */
    public static Vector<String> getFormattedValues() {
        Vector<String> formattedValues = new Vector<>();
        for (MotorbikeType type : MotorbikeType.values()) {
            String name = type.name();
            formattedValues.add(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
        }
        return formattedValues;
    }


}
