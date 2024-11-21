package app.motocycles;

import controller.ManageCatalog;
import controller.ManageMotorbike;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Catalog;
import model.Motorbike;
import view.ConsoleDisplay;

/**
 * The main class of the Motorcycle Catalog Application.
 * 
 * It initializes the application, manages user interactions, 
 * and simulates loading a motorcycle catalog.
 * 
 * This class contains the main method which serves as the entry point of the application.
 * 
 * @author Kamil Kotorc
 * @version 1.0
 */
public class Main {

    /**
     * The main method that runs the application.
     * Simulates the loading of the motorcycle catalog and allows user interaction with the catalog.
     *
     * @param args command line parameters (not used).
     */
    public static void main(String[] args) {
        
        // Simulating loading a catalog with existing vehicles 
        Catalog romet = new Catalog("Romet");
        List<Motorbike> loadedMotorbikes = new ArrayList<>();
        
        // Adding sample motorbikes to the catalog
        Motorbike chart = new Motorbike("Chart", 30000.00, 750, 21);
        loadedMotorbikes.add(chart);
        Motorbike kadet = new Motorbike("Kadet", 19500.00, 125, 7);
        loadedMotorbikes.add(kadet);
        Motorbike pony = new Motorbike("Pony", 15999.99, 50, 3);
        loadedMotorbikes.add(pony);

        // Setting the loaded motorbikes to the catalog
        romet.setMotorbikes(loadedMotorbikes);
        
        ManageCatalog catalogManager = new ManageCatalog();
        ManageMotorbike motorbikeManager = new ManageMotorbike();
        ConsoleDisplay console = new ConsoleDisplay();
        
        console.showInstructions();
        
        Scanner in = new Scanner(System.in);
       
        boolean appRunner = true;
        
        while(appRunner){
            
            boolean selectedMotorbikeRunner = false;
            Motorbike selectedMotorbike = null;
            
            console.showCatalogMenu();
            
            String input = in.nextLine();

            switch(input) {
                case "1" -> {
                    System.out.println(catalogManager.countMotorbikes(romet) + " results");
                    console.displayCatalog(romet);
                }
                case "2" -> {
                    System.out.print("Type model: ");
                    String inputModel = in.nextLine();
                    if(catalogManager.isModelInCatalog(romet, inputModel)){
                        selectedMotorbikeRunner = true;
                        selectedMotorbike = catalogManager.getMotorbikeByModel(romet, inputModel);
                        console.showSuccessMessage("Selected " + selectedMotorbike.getModel());
                    } else{
                        console.showErrorMessage("No given model in catalog");
                    }
                }
                case "3" -> {
                    System.out.print("Search by name: ");
                    String searchName = in.nextLine();
                    List<Motorbike> nameResult = catalogManager.findMotorbikesByName(romet, searchName);
                    System.out.println(nameResult.size() + " results");
                    console.displayList(nameResult);
                }
                case "4" -> {
                    try {
                        System.out.print("Maximum price: ");
                        double maxPrice = Double.parseDouble(in.nextLine());
                        System.out.print("Maximum displacement: ");
                        int maxDisplacement = Integer.parseInt(in.nextLine());
                        System.out.print("Minimum power: ");
                        int minPower = Integer.parseInt(in.nextLine());

                        List<Motorbike> parametersResult = catalogManager.findMotorbikesByParameters(romet, maxPrice, maxDisplacement, minPower);
                        System.out.println(parametersResult.size() + " results");
                        console.displayList(parametersResult);
                    } catch (NumberFormatException e) {
                        console.showErrorMessage("Invalid numeric value entered");
                    }
                }
                case "5" -> {
                    try {
                        Motorbike newMotorbike = console.getMotorbikeInput();
                        if (motorbikeManager.validateData(newMotorbike)) {
                            catalogManager.AddMotorbike(romet, newMotorbike);
                        } else {
                            console.showErrorMessage("Invalid data entered");
                        }
                    } catch (Exception e) {
                        console.showErrorMessage("An error occurred while adding motorbike");
                    }
                }
                case "6" -> catalogManager.clearCatalog(romet);
                case "7" -> appRunner = false;
                default -> System.out.println("Incorrect input, try again");
            }
             
            while(selectedMotorbikeRunner && selectedMotorbike != null){
                
                console.showMotorbikeMenu();
                
                input = in.nextLine();
                
                switch(input) {
                    case "1" -> console.displayMotorbike(selectedMotorbike);
                    case "2" -> {
                        catalogManager.RemoveMotorbike(romet, selectedMotorbike);
                        selectedMotorbikeRunner = false;
                    }
                    case "3" -> {
                        try {
                            System.out.print("Enter new displacement: ");
                            int newDisplacement = Integer.parseInt(in.nextLine());

                            System.out.print("Enter new power: ");
                            int newPower = Integer.parseInt(in.nextLine());

                            catalogManager.updateMotorbikeSpecification(romet, selectedMotorbike, newDisplacement, newPower);
                        } catch (NumberFormatException e) {
                            console.showErrorMessage("Invalid specification entered");
                        }
                    }
                    case "4" -> {
                        try {
                            System.out.print("Enter new price: ");
                            double newPrice = Double.parseDouble(in.nextLine());

                            catalogManager.updateMotorbikePrice(romet, selectedMotorbike, newPrice);
                        } catch (NumberFormatException e) {
                            console.showErrorMessage("Invalid price entered");
                        }
                    }
                    case "5" -> selectedMotorbikeRunner = false;
                    default -> System.out.println("Incorrect input, try again");
                }
            }
        }
        
        System.out.println("Application is closing");
    }
    // End of application
}
