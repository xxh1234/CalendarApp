package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Main extends Application{
 
	public static void main(String[] args){
		
		//Users
		User CortlandB = new User("smoothcjb", "password1");
		User DiyaP = new User("diyap1234", "password2");
		User XinhaoX = new User("xxh1234", "password3");
	
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		Scene scene = new Scene(root);
		//String css = this.getClass().getResource("application.css").toExternalForm();
		//scene.getStylesheets().add(css);
		primaryStage.setTitle("The date app");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}