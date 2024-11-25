package app.polsl.motocat;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Catalog;
import model.Motorbike;
import view.GUI;
import controller.LogicManager;

/**
 * The main class of the Motorcycle Catalog Application.
 *
 * This class contains the main method, which serves as the entry point of the application.
 *
 * @version 2.1
 * @author Kamil Kotorc
 *
 */
public class App {
    
    /**
     * The main method serves as the entry point of the application.
     * It sets up the look and feel, initializes the catalog, adds sample motorbikes,
     * and launches the graphical user interface (GUI) with the necessary controllers.
     *
     * @param args command-line arguments (optional), the first argument is used as the catalog name.
     */
    public static void main(String[] args) {
        
        // Set the Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Check if catalog name is provided as a command-line argument
        // If not, question the user to enter a catalog name
        String loadedCatalogName;
        if (args.length > 0) {
            loadedCatalogName = args[0];
        } else {
            loadedCatalogName = JOptionPane.showInputDialog(
                null, 
                "Enter the catalog name:", 
                "Catalog Name", 
                JOptionPane.QUESTION_MESSAGE
            );

            if (loadedCatalogName == null || loadedCatalogName.trim().isEmpty()) {
                loadedCatalogName = "Default Catalog";
            }
        }
        
        // Initialize catalog with given name
        Catalog loadedCatalog = new Catalog(loadedCatalogName);
        
        // Adding sample motorbikes to the catalog
        List<Motorbike> loadedMotorbikes = new ArrayList<>();
        Motorbike chart = new Motorbike("Chart", 30000.00, 750, 21);
        loadedMotorbikes.add(chart);
        Motorbike kadet = new Motorbike("Kadet", 19500.00, 125, 7);
        loadedMotorbikes.add(kadet);
        Motorbike pony = new Motorbike("Pony", 15999.99, 50, 3);
        loadedMotorbikes.add(pony);

        // Setting the loaded motorbikes to the catalog
        loadedCatalog.setMotorbikeList(loadedMotorbikes);

        // Creating and displaying the form
        java.awt.EventQueue.invokeLater(() -> {

            GUI gui = new GUI();

            LogicManager controller = new LogicManager(gui,loadedCatalog);
            controller.updateModelList();
            
            gui.setVisible(true);
        });
        
    }
    // End of application
}

