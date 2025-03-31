package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Scene2Controller {
	
	@FXML
	Label nameLabel;
	
	/*
	 * displayName function gets username data from scene1 and display it at the right bottom coner in scene2
	 * which states hello (their username)
	 */
	public void displayName(String username) {
		
		nameLabel.setText("Hello: "+ username );
	}
	/*
	 * addEvent functon takes button press on the add event button and switch to scene3.
	 */
	public void addEvent(ActionEvent event) throws IOException {
		//root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		//Scene3Controller scene3Controller = loader.getController();
		//above code is to get data from the scene3 but its not needed here.
		
		//code below are for transition
		//creates a fxmlloader instance and load it with scene3.fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
		//loader returns the root node
		Parent root = loader.load();
		//cast the button event to node then get the current scene of the current event
		//then we get the 
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		//creates an new scene
		Scene scene = new Scene(root);
		//set the stage with new scene
		stage.setScene(scene);
		stage.show();
		
	}
	

}
