package application;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;


public class Scene2Controller implements Event, Initializable, Serializable {
	
	private static final long serialVersionUID = 1L;

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
	
	@FXML
    private ListView<String> eventView;
	
	@FXML
	private Label alertLabel;
	
    
    //
    protected Map<String, Set<String>> eventMap = new HashMap<>();
    protected String username = Scene1Controller.userStack.peek();
    protected Set<String> eventSet = new LinkedHashSet<>(Arrays.asList());
    
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
	
	//displayName 
    public void displayName() {
		
		nameLabel.setText("Hello, " + username + "!");			
	} 
 
	public void displayTodayDate() {
		
		todayLabel.setText(LocalDate.now().toString());	
	}
	
    @Override
    public void addEvent() {
    	
      LocalDate date = datePicker.getValue();
      String eventDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
      String eventDetails = eventField.getText();
      String event = (date.toString() + " " + eventDetails);
      String viewEvent = (eventDate + ": " + eventDetails);
      addToSet(eventMap,username,event);
      eventView.getItems().add(event);
      datePicker.getEditor().clear();
      eventField.clear(); 
    }
    // Add events to Set
 	public void addToSet(Map<String, Set<String>> map, String key, String element){
 	
         ArrayList<String> eventList = new ArrayList<>(loadEvents);
         eventList.add(element);
         listToFile(eventList,eventFile);
 	     eventSet = new LinkedHashSet<>(eventList);  
 	     eventMap.put(key, eventSet);
 	     System.out.println(eventMap);   
 	}
 	//Copy ArrayList to serialized file
 	String eventFile = "eventlist.ser";
    LocalDateTime dateTime = LocalDateTime.now();
    String timestamp = dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
 	
 	public void listToFile (ArrayList<String> list, String fileName) {
 		
 	  try (FileOutputStream fileOut = new FileOutputStream(fileName);
              ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

             // Write the ArrayList to the file
             objectOut.writeObject(list);
             System.out.println(timestamp + ": ArrayList serialized and saved to " + fileName);
             
         } catch (IOException e) {
             e.printStackTrace();
         }
}
 	
 	//Retrieve ArrayList from serialized file	
 	@SuppressWarnings("unchecked")
	public ArrayList<String> listfromFile (String fileName) {
 		
 		ArrayList<String> retrievedEventList = null; 	
 		
 	 	  try (FileInputStream fileIn = new FileInputStream(fileName);
 	              ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

 	             // Read ArrayList from the file
 	 		  	 retrievedEventList = (ArrayList<String>) objectIn.readObject();
 	             System.out.println(timestamp + ": ArrayList retrieved from: " + fileName);            

 	         } catch (IOException | ClassNotFoundException e) {
 	             e.printStackTrace();
 	             
 	         }
 	 	  return retrievedEventList;
 	} 
    //Display events in ListView
    ArrayList<String> loadEvents = listfromFile(eventFile);
 	public void displayEvents(ArrayList<String> list) {
 		
 		for (String e : list) {
 			
 			eventView.getItems().add(e);
 		}	
 	}

 	//LocalDate todayDate = LocalDate.now();
 	public void checkCurrentDay(ArrayList<String> list) {
 		
 	LocalDate currentDate = LocalDate.now();
 	String targetDate = currentDate.toString();
 	 	
 	 int count = 0;
 	 for (String e : list) {
 	 String[] dates = e.split(" ");
     for (String date : dates) {
         if (date.equals(targetDate)) {
        	 count++;
        	 if(count == 1) {
        		 alertLabel.setText("You have " + count + " event today!");
        	 } else {
        		 alertLabel.setText("You have " + count + " events today!");
         }
     }
     }
  }
 	 }
 	
 	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
 		
 		currentYearMonth = YearMonth.now(); 
 	    updateCalendar(currentYearMonth); // Sets to current month & year when initialized 
 	    displayTodayDate(); 
	    displayName();
	    displayEvents(loadEvents);
	    checkCurrentDay(loadEvents);	    
 	}  

	@Override
	public void removeEvent() {
		int index = eventView.getSelectionModel().getSelectedIndex();
		eventView.getItems().remove(index);
		removeFromSet(eventMap,username,index);
	}
	public void removeFromSet(Map<String, Set<String>> map, String key, int index){
		
		 ArrayList<String> removeEventList = new ArrayList<>(loadEvents);
	     removeEventList.remove(index);
	     eventSet = new LinkedHashSet<>(removeEventList);  
	     listToFile(removeEventList,eventFile);
	     eventMap.put(key, eventSet);
	     System.out.println(eventMap);
	}   
} 
