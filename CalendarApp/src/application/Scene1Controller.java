package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene1Controller {
	
	@FXML
	TextField nameTextField;
	 
	@FXML
	private PasswordField passwordField;
	
	@FXML
    Label invalidLogin;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	static Stack <String> userStack = new Stack<>();
	
	public void login(ActionEvent event) throws IOException {
		//root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		
		String username = nameTextField.getText();
		String password = passwordField.getText();
		Boolean validUser = User.userMap.containsKey(username);
		Boolean validPassword = User.userMap.containsValue(password);
		userStack.push(username);
		
	  if(validUser && validPassword) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
		
		root = loader.load();
		
		//Scene2Controller scene2Controller = loader.getController();
			
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();			
	}
	  else {
  
		 invalidLogin.setText("Invalid username or password");
	} 
}	

 }