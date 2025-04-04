package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class Main extends Application{
 
	public static void main(String[] args){
		
		//Users
		User CortlandB = new User("smoothcjb", "password1");
		User DiyaP = new User("diyap1234", "password2");
		User XinhaoX = new User("xxh", "");
		
	
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		Scene scene = new Scene(root);
		Image icon = new Image(getClass().getResource("app_Icon.png").toExternalForm());
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Schedulo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}