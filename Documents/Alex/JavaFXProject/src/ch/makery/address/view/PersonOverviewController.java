package ch.makery.address.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

import ch.makery.address.MainApp;
import ch.makery.address.model.EmpDAO;
import ch.makery.address.model.EmpVO;

public class PersonOverviewController {
    @FXML
    private TableView<EmpVO> personTable;
    @FXML
    private TableColumn<EmpVO, String> firstNameColumn;
    @FXML
    private TableColumn<EmpVO, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    
    //private ObservableList<EmpVO> personData;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getEmpno())));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEname()));
        
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
    		(observable, oldValue, newValue) -> showPersonDetails(newValue));
    
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(EmpVO employee) {
        if (employee != null) {            
            firstNameLabel.setText(employee.getEname());
            lastNameLabel.setText(Integer.toString(employee.getEmpno()));
            streetLabel.setText(employee.getHiredate());
            postalCodeLabel.setText(employee.getJob());
            cityLabel.setText(Integer.toString(employee.getSalary()));
            birthdayLabel.setText(employee.getMgr());
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
    	EmpVO auxiliarEmpVO;
    	EmpDAO currEmpDAO = new EmpDAO();
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            auxiliarEmpVO = personTable.getItems().get(selectedIndex);
            currEmpDAO.deleteEMP(auxiliarEmpVO);
            reloadDB();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    
    @FXML
    private void handleNewPerson() {
    	EmpVO tempPerson = new EmpVO();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
        	reloadDB();
        }
    }
	
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        /*Person selectedPerson = personTable.getSelectionModel().getSelectedItem();*/
        EmpVO selectedPerson = personTable.getSelectionModel().getSelectedItem();
        
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
    
    private void reloadDB() {
    	EmpDAO currEmpDAO = new EmpDAO();
        ArrayList<EmpVO> empleatsArrayList;
    	empleatsArrayList = currEmpDAO.obtenirTotEMP();
        personTable.getItems().clear();
        
        for(int i =0 ; i<empleatsArrayList.size(); i++) {
        	personTable.getItems().add(empleatsArrayList.get(i));
        }
    }
}