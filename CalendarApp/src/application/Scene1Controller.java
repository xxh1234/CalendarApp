package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene1Controller {
	
	@FXML
	TextField nameTextField;
	 
	private Stage stage;
	private Scene scene;
	private Parent root;
	/*
	 * login function gets the username in and pass it to the scene2controller Then switch to scene2
	 */
	public void login(ActionEvent event) throws IOException {
		//root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		String username = nameTextField.getText();
				
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
		root = loader.load();
		Scene2Controller scene2Controller = loader.getController();
		
		scene2Controller.displayName(username);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
		
}
