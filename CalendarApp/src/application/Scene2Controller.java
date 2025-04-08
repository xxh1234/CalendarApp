package application;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class Scene2Controller implements Event {
	//
	static Map<String, Set<String>> eventMap = new HashMap<>();

	@FXML 
	private DatePicker datePicker;
	
	@FXML 
	private TextField eventField;
	
	@FXML
	private Label statusLabel;
	
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
	
	//displayName isn't functioning 04/03.
	public void displayName(String username) {
		
		nameLabel.setText("Hello: "+ username );
	}
	
	public void displayTodayDate() {
		
		todayLabel.setText(LocalDate.now().toString());
		
	}
//  @Override
    public void addEvent() {
    //  String eventDate = datePicker.getValue().toString();
      LocalDate date = datePicker.getValue();
      String eventDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
      String eventTitle = eventField.getText();
     // String username = Scene1Controller.userStack.peek();
      String event = (eventDate + ": " + eventTitle);
   //   addToSet(eventMap,username,event);
   //   statusLabel.setText(event);
      eventField.clear();
      //
    //  myEvents.add(event);
     // System.out.println(myEvents);
      System.out.println("Date: " + date);
     }   
   
 /*  @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	   
	// eventView.getItems().addAll(myEvents);
	// eventView.getItems().add(result);
	// System.out.println(eventList[0]);
	 }
	   
	/*   if (list[0] == null) {  
		   System.out.println("No events to display");
	   } */

	@Override
	public void removeEvent() {
		// TODO Auto-generated method stub
		
	}
		
	
 	public void addToSet(Map<String, Set<String>> map, String key, String element){
  		 
  		 if (map.containsKey(key)) {
  	            Set<String> set = map.get(key);
  	            set.add(element);
  	        } else { 
  	        	Set<String> set = new HashSet<>();
  	            set.add(element);
  	            eventMap.put(key, set);	 
       } 
   
  	}    
//	public void addEvent(ActionEvent event) throws IOException {
//		//root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
//		//Scene3Controller scene3Controller = loader.getController();
//		//above code is to get data from the scene3 but its not needed here.
//		
//		//code below are for transition
//		//creates a fxmlloader instance and load it with scene3.fxml
//		System.out.println("Event button clicked!");
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene3.fxml"));
//		//loader returns the root node
//		Parent root = loader.load();
//		//cast the button event to node then get the current scene of the current event
//		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		//creates an new scene
//		Scene scene = new Scene(root);
//		//set the stage with new scene
//		stage.setResizable(false);
//		stage.setScene(scene);
//		stage.show();
//		
//	}
	

}
