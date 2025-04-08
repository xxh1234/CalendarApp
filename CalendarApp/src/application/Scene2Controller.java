package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.YearMonth;

public class Scene2Controller {
	

	@FXML
	Button eventButton;
	
	@FXML
	Label nameLabel;
	
	@FXML
	Label monthLabel;
	
	@FXML
	Label todayLabel;
	
	@FXML
	private GridPane calendarPane;
	
	//this updates the current month to +1
	@FXML
	private void nextMonthButton() {
	    currentYearMonth = currentYearMonth.plusMonths(1);
	    updateCalendar(currentYearMonth);
	}
	//this updates the current month to -1
	@FXML
	private void previousMonthButton() {
	    currentYearMonth = currentYearMonth.minusMonths(1);
	    updateCalendar(currentYearMonth);
	}
	
	//YearMonth object to display year and month+
	private YearMonth currentYearMonth;
	
	public void initialize() {
		
	    currentYearMonth = YearMonth.now(); 
	    updateCalendar(currentYearMonth); // Sets to current month & year when initialized 
	    displayTodayDate();
	    eventButton.setStyle("-fx-background-color: grey; -fx-text-fill: black;");
	    
	}
	
	// showCalendar display the dates in the calendar.
	private void updateCalendar(YearMonth yearMonth) {
	    
		
		calendarPane.getChildren().clear(); //clear pane each time.

	    LocalDate firstOfMonth = yearMonth.atDay(1); //find the first day of the month.
	    
	    LocalDate todayDate = LocalDate.now();//get to day's date
	    
	    int lengthOfMonth = yearMonth.lengthOfMonth();//get the number of days in that month
	    
	    int dayOfWeek = firstOfMonth.getDayOfWeek().getValue(); // Monday=1, Sunday=7

	    // Shift to Sunday=0
	    int column = dayOfWeek % 7;//to start at the correct day of the week
	    int row = 0;

	    for (int day = 1; day <= lengthOfMonth; day++) {
	    	
	    	// finalDay,year and month to compare with added event
	    	int currentDay = day;
	    	int year = yearMonth.getYear();
	    	int month = yearMonth.getMonthValue();
	    	
	    	LocalDate buttonDate = LocalDate.of(year, month, currentDay);
	    	
	        Button dayButton = new Button(String.valueOf(day));
	        
	        dayButton.setPrefSize(100, 60);

	        //add events here
	        
	        dayButton.setOnAction(new buttonHandlerClass(buttonDate));
	        
	        
	        System.out.println("today is = " + buttonDate.toString());
	        
	        
	        //change color to differ even and odd dates.
	        if(column%2 != 0) {
	        	dayButton.setStyle("-fx-background-color: #eae8e4; ");
	        }
	        else {
	        	dayButton.setStyle("-fx-background-color: #c9c7c1; ");
	        }
	        //highlights today's date.
	        if(year == todayDate.getYear() && month == todayDate.getMonthValue() && currentDay == todayDate.getDayOfMonth()) {
	        	dayButton.setStyle("-fx-background-color: #5F7EA4; ");
	        }


	        calendarPane.add(dayButton, column, row);
	        
	        

	        column++;
	        if (column > 6) {
	            column = 0;
	            row++;
	        }
	    }

	    // Update label to show selected month & year
	    monthLabel.setText(yearMonth.getMonth() + " " + yearMonth.getYear());
	}
	
	class buttonHandlerClass implements EventHandler<ActionEvent>{
		private LocalDate date;
		
		public buttonHandlerClass(LocalDate date) {
			this.date = date;
		}
		@Override
		public void handle(ActionEvent event) {
			System.out.println("button clicked" + date);
			
		}
		
	}
	
	//displayName isn't functioning 04/03.
	public void displayName(String username) {
		
		nameLabel.setText("Hello: "+ username );
	}
	
	public void displayTodayDate() {
		
		todayLabel.setText(LocalDate.now().toString());
		
	}
	public void addEvent(ActionEvent event) throws IOException {
		//root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		//Scene3Controller scene3Controller = loader.getController();
		//above code is to get data from the scene3 but its not needed here.
		
		//code below are for transition
		//creates a fxmlloader instance and load it with scene3.fxml
		System.out.println("Event button clicked!");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
		//loader returns the root node
		Parent root = loader.load();
		//cast the button event to node then get the current scene of the current event
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		//creates an new scene
		Scene scene = new Scene(root);
		//set the stage with new scene
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
	}
	

}
