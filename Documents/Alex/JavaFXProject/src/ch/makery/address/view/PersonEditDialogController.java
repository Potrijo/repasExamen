package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.EmpDAO;
import ch.makery.address.model.EmpVO;
import ch.makery.address.model.Person;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField empnoField;
    @FXML
    private TextField enameField;
    @FXML
    private TextField jobField;
    @FXML
    private TextField mgrField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField commField;
    @FXML
    private TextField deptnoField;



    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        empnoField.setText(person.getFirstName());
        enameField.setText(person.getLastName());
        jobField.setText(person.getStreet());
        mgrField.setText(Integer.toString(person.getPostalCode()));
        salaryField.setText(person.getCity());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            int empno = Integer.parseInt(empnoField.getText());
            String ename = enameField.getText();
            String job = jobField.getText();
            String mgr = mgrField.getText();
            int salary = Integer.parseInt(salaryField.getText());
            String comm = commField.getText();
            int deptno = Integer.parseInt(deptnoField.getText());
            
        	EmpVO newEmployee = new EmpVO(empno,ename,job,mgr,salary,comm,deptno);
        	EmpDAO newConnection = new EmpDAO();
        	newConnection.insertEMP(newEmployee);
            empnoField.getText();
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (empnoField.getText() == null || empnoField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(empnoField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }
        
        
        if (salaryField.getText() == null || salaryField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(salaryField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }
        
        
        if (deptnoField.getText() == null || deptnoField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(deptnoField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}