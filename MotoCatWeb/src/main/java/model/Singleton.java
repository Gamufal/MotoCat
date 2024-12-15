package model;

import static model.MotorbikeType.CRUISER;
import static model.MotorbikeType.ENDURO;
import static model.MotorbikeType.SPORT;
import static model.MotorbikeType.STANDARD;

public class Singleton {
   
    private static Catalog instance;

    private Singleton() {        
    }
    
    public static Catalog getInstance() {

        if(instance == null) {
            instance = new Catalog("Singleton");
            try{            
                instance.addMotorbike( new Motorbike("Chart", 30000.00, 750, 21, SPORT));
                instance.addMotorbike( new Motorbike("Kadet", 19400.00, 125, 7 , STANDARD));
                instance.addMotorbike( new Motorbike("Pony", 11999.99, 50, 3, ENDURO));
                instance.addMotorbike( new Motorbike("Simson", 26600.00, 125, 12, CRUISER));
                instance.addMotorbike( new Motorbike("Ogar", 15555.50, 50, 5, STANDARD));
            } catch(AppException ex) {
            }
        }
        return instance;
    }   
}
