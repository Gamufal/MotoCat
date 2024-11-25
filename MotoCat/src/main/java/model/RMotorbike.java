package model;

/**
 * Represents a motorbike with specific attributes such as model, price, displacement, and power.
 * This class provides methods to access and modify these attributes.
 * 
 * @author Kamil Kotorc
 * @version 2.1
 */


public record RMotorbike(String model, double price, int displacement, int power) {
    
    public boolean validateData() {
        try{
            if(model == null){
                throw new AppException("Invalid input. Model name cannot be empty.");
            } else if (model.isEmpty()) {
                throw new AppException("Invalid input. Model name cannot be empty.");
            } else if (price < 0){
                throw new AppException("Invalid input. Price cannot be less than 0.");
            } else if (displacement < 1){
                throw new AppException("Invalid input. Displacement cannot be less than 0.");
            } else if (power < 1){
                throw new AppException("Invalid input. Power cannot be less than 0.");
            }
        }catch(AppException e){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return model + " " + price + " " + displacement + " " + power;
    }
    
    
}
