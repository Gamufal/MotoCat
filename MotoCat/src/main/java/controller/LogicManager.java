package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.AppException;

import model.Catalog;
import model.Motorbike;
import view.GUI;

/**
 * The LogicManager class is responsible for handling the business logic of the motorbike catalog application.
 * It acts as the controller in the MVC architecture, managing the interactions between the view (GUI) and the model (Catalog, Motorbike).
 * It handles events such as adding, removing, selecting motorbikes, and clearing the catalog.
 * 
 * @author Kamil Kotorc
 * @version 3.0
 */
public final class LogicManager {
    
    private final GUI gui;
    private final Catalog catalog;
    
    /**
     * Constructor that initializes the LogicManager with the GUI and Catalog objects.
     * It sets up listeners for various UI actions such as adding, removing, and selecting motorbikes.
     *
     * @param gui the GUI instance that represents the view of the application.
     * @param catalog the Catalog instance that holds the list of motorbikes.
     */
    public LogicManager(GUI gui, Catalog catalog) {
        this.gui = gui;
        this.catalog = catalog;
        
        gui.setSelectedCatalog(catalog.getBrand());
   
        gui.setClearButtonActionListener(e -> clearModelList());
        handleMotorbikeSelection();
        
        handleRemoveMotorbike();
        handleAddMotorbike();
        handleEditMotorbike();
        
        attachMenuClearAction();
        attachMenuRemoveAction();
        attachMenuAddAction();
    }
    
    /**
     * Updates the motorbike list in the GUI based on the current motorbikes in the catalog.
     */
    public void updateModelList() {
        //String[] motorbikeNames;
        List<String> motorbikeNames = new ArrayList<>();
        List<Motorbike> motorbikes = catalog.getMotorbikeList();
        
        for (Motorbike motorbike : catalog.getMotorbikeList()) {
            motorbikeNames.add(motorbike.model());
        }
        
        gui.updateMotorbikeTable(motorbikes);
        gui.updateMotorbikeList(motorbikeNames);
    }
    
    /**
     * Clears the motorbike list from the catalog and updates the GUI accordingly.
     * If the catalog is already empty, an error message is shown.
     */
    public void clearModelList() {
        if(!catalog.isCatalogEmpty()){
            catalog.clearCatalog();

            gui.updateMotorbikeList(new ArrayList<>());
            JOptionPane.showMessageDialog(gui, "Catalog has been cleared!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(gui, "Catalog is already empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Handles the selection of a motorbike from the motorbike list in the GUI.
     * Displays the details of the selected motorbike in the GUI.
     */
    public void handleMotorbikeSelection() {
        gui.setMotorbikeSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { 
                int selectedIndex = gui.getSelectedMotorbikeIndex();
                if (selectedIndex >= 0) {
                    Motorbike selectedMotorbike = catalog.getMotorbikeList().get(selectedIndex);
                    gui.updateSelectedMotorbikeDetails(
                            catalog.getBrand(),
                            selectedMotorbike.model(),
                            String.valueOf(selectedMotorbike.price()),
                            String.valueOf(selectedMotorbike.displacement()),
                            String.valueOf(selectedMotorbike.power())
                    );
                } else {
                    gui.clearSelectedMotorbikeDetails();
                }
            }
        });
    }
    
    /**
     * Handles the action of removing a selected motorbike from the catalog.
     * If no motorbike is selected, an error message is shown.
     */
    public void handleRemoveMotorbike() {
        gui.setRemoveButtonActionListener(e -> {
            try {
                int selectedIndex = gui.getSelectedMotorbikeIndex();
                if (selectedIndex < 0) {
                    throw new AppException("No motorbike selected!");
                }

                Motorbike motorbikeToRemove = catalog.getMotorbikeList().get(selectedIndex);
                catalog.removeMotorbike(motorbikeToRemove);
                updateModelList();
                gui.clearSelectedMotorbikeDetails();
                JOptionPane.showMessageDialog(gui, "Motorbike has been removed!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (AppException ex) {
            }
        });
    }
    
    /**
     * Handles the action of adding a new motorbike to the catalog.
     * If the input fields are invalid or empty, an error message is shown.
     */
    public void handleAddMotorbike() {
        gui.setAddButtonListener(e -> {
            
            Motorbike newMotorbike = gui.getMotorbikeFromInputFields();
                
            if (!newMotorbike.validateData()) {
                JOptionPane.showMessageDialog(gui, "Please enter correct values!", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            catalog.addMotorbike(newMotorbike);
            List<String> motorbikeNames = new ArrayList<>();
            
            for (Motorbike motorbike : catalog.getMotorbikeList()) {
                motorbikeNames.add(motorbike.model());
            }
            
            gui.updateMotorbikeList(motorbikeNames);
            gui.clearInputFields();
            JOptionPane.showMessageDialog(gui, "Motorbike added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    /**
     * Handles the action of editing a selected motorbike from the catalog.
     * If the input fields are invalid or empty, an error message is shown.
     */
    public void handleEditMotorbike() {
        gui.setEditButtonListener(e -> {
            
            int selectedIndex = gui.getSelectedMotorbikeIndex();
            if (selectedIndex < 0) {
                JOptionPane.showMessageDialog(gui, "Please select a motorbike to edit!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            Motorbike toEditMotorbike = catalog.getMotorbikeList().get(selectedIndex);
            
            JTextField modelField = new JTextField(toEditMotorbike.model());
            JTextField priceField = new JTextField(String.valueOf(toEditMotorbike.price()));
            JTextField displacementField = new JTextField(String.valueOf(toEditMotorbike.displacement()));
            JTextField powerField = new JTextField(String.valueOf(toEditMotorbike.power()));

            JPanel panel = new JPanel();
            panel.setLayout(new java.awt.GridLayout(4, 2, 5, 5));
            panel.add(new JLabel("Model:"));
            panel.add(modelField);
            panel.add(new JLabel("Price:"));
            panel.add(priceField);
            panel.add(new JLabel("Displacement:"));
            panel.add(displacementField);
            panel.add(new JLabel("Power:"));
            panel.add(powerField);

            int result = JOptionPane.showConfirmDialog(
                gui,
                panel,
                "Edit Motorbike",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String newModel = modelField.getText();
                    double newPrice = Double.parseDouble(priceField.getText());
                    int newDisplacement = Integer.parseInt(displacementField.getText());
                    int newPower = Integer.parseInt(powerField.getText());

                    Motorbike updatedMotorbike = new Motorbike(newModel, newPrice, newDisplacement, newPower);

                    catalog.editMotorbike(toEditMotorbike, updatedMotorbike);

                    updateModelList();
                    gui.clearSelectedMotorbikeDetails();
                    JOptionPane.showMessageDialog(gui, "Motorbike has been edited successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gui, "Invalid number format. Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    /**
     * Attaches the action listener for the "Clear" menu item.
     * Clears the entire catalog and updates the GUI.
     */
    public void attachMenuClearAction() {
        gui.setMenuClearActionListener(e -> {
            catalog.clearCatalog(); 
            gui.updateMotorbikeList(new ArrayList<>()); 
            gui.clearSelectedMotorbikeDetails();
            JOptionPane.showMessageDialog(gui, "Catalog has been cleared!", "Information", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    /**
     * Attaches the action listener for the "Remove" menu item.
     * Removes the selected motorbike from the catalog.
     */
    public void attachMenuRemoveAction() {
        gui.setMenuRemoveActionListener(e -> {
            int selectedIndex = gui.getSelectedMotorbikeIndex();
            if (selectedIndex >= 0) {
                Motorbike motorbikeToRemove = catalog.getMotorbikeList().get(selectedIndex);
                catalog.removeMotorbike(motorbikeToRemove);
                updateModelList();
                gui.clearSelectedMotorbikeDetails();
                JOptionPane.showMessageDialog(gui, "Motorbike has been removed!", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(gui, "No motorbike selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        });
    }
    
    /**
     * Attaches the action listener for the "Add" menu item.
     * Adds a new motorbike to the catalog based on user input.
     */
    public void attachMenuAddAction() {
        gui.setMenuAddActionListener(e -> {
            Motorbike newMotorbike = gui.getMotorbikeFromInputFields();
            if (newMotorbike != null) {
                catalog.addMotorbike(newMotorbike); 
                List<String> motorbikeNames = new ArrayList<>();
                for (Motorbike motorbike : catalog.getMotorbikeList()) {
                    motorbikeNames.add(motorbike.model());
                }
                gui.updateMotorbikeList(motorbikeNames);
                gui.clearInputFields();
                JOptionPane.showMessageDialog(gui, "Motorbike added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    
        
}
