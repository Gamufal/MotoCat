package model;

/**
 * Represents a motorbike with specific attributes such as model, price, displacement, and power.
 * This class provides methods to access and modify these attributes.
 * 
 * @author Kamil Kotorc
 * @version 3.1
 */
public record Motorbike(String model, double price, int displacement, int power, MotorbikeType type) {
    
    /**
     * Validates the motorbike's fields to ensure they meet specified constraints.
     *
     * @return true if all fields are valid; false otherwise.
     */
    public boolean validateData() {
        try {
            if (model == null || model.isEmpty()) {
                throw new AppException("Invalid input. Model name cannot be empty.");
            } else if (price < 1) {
                throw new AppException("Invalid input. Price cannot be less than 1.");
            } else if (displacement < 1) {
                throw new AppException("Invalid input. Displacement cannot be less than 1.");
            } else if (power < 1) {
                throw new AppException("Invalid input. Power cannot be less than 1.");
            } else if (type == null) {
                throw new AppException("Invalid input. Type cannot be null.");
            }
        } catch (AppException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return model + " " + price + " " + displacement + " " + power + " " + type;
    }
    
}
