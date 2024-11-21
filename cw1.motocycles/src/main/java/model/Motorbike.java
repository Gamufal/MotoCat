package model;

/**
 *
 * @author Kamil Kotorc
 * @version 1.0
 * 
 */

public class Motorbike {
    
    private String brand;
    private String name;
    private double price;
    private int displacement;
    private int power;

    public Motorbike(String brand, String name, double price, int displacement, int power) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.displacement = displacement;
        this.power = power;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    
}
