package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Scene3Controller {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField eventField;

    @FXML
    private Label statusLabel;
    
    @FXML
    private Button backToCalendar;
    
   
/*
 * handleAddEvent ask user to input a date with date picker and write a message for event detail. this should add an event to
 * the calendar view.
 * 
 * What to work on: right now this is only output an console print out. a proper data structure is needed to store the data.
 */
    public void handleAddEvent() {
        if (datePicker.getValue() == null || eventField.getText().isEmpty()) {
            statusLabel.setText("Please select a date and enter event details.");
        } else {
            String selectedDate = datePicker.getValue().toString();
            String event = eventField.getText();

            
            System.out.println("Event on " + selectedDate + ": " + event);

            statusLabel.setText("Event added for " + selectedDate + "!");
            eventField.clear();
        }

    }
    
    /*
     * function goBackToCalendar takes user back to the calendar view after a button is pressed
     */
    
    public void goBackToCalendar(ActionEvent event) throws IOException {
    	
    	//System.out.println("Back button clicked!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}