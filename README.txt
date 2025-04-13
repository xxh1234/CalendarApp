# Schedulo - A JavaFX Calendar App

Schedulo is a JavaFX event scheduler application aims to provide a practical tool for user allowing them to view, create, categorize, prioritize events.

## Features

- User login with password authentication.
- View month by month calendar with JavaFX gridpane.
- Date highlighting.
- Add, view and remove events with datepicker selection.
- User data persistence.
- Event display with JavaFX listview.
- Minimalistic UI design with JavaFX and Scene Builder.

## How To Download And Setup JavaFX Environment

1. Obtain the program files from our submitted ZIP file and unzip to your local directory.
2. Download the latest eclipse IDE on your computer [Eclipse IDE](https://eclipseide.org/)
3. Download the JavaFX SDK from the Gluon website [JavaFX SDK](https://gluonhq.com/products/javafx/) Make sure you remember the file directory you downloaded to
you will be using it later.
4. Now Import the project to your eclipse IDE. We will be setting up the environment in the next few steps.
5. Right-click on the project folder,  go to Build path -> Configure Build Path. In the Libraries tab, click Add Library... >JavaFX SDK
6. Click Next and then Browse to select the JavaFX SDK lib folder you downloaded. Click Finish.
7. In the imported project, right-click project -> properties -> Java Build Path. In the libraries tab: click "Modulepath" -> select"Add Library.." -> choose "User Library"
Choose "JavaFX" -> "Finish" ->"Apply" ->"Apply and close" At this step if you see errors before it should all be gone now.
8. Now we need to set up the run configuration: Right click on main.java -> run As -> run configurations. In VM arguments tab 
change VM arguments to --module-path [JavaFX directory in your local drive] --add-modules javafx.controls,javafx.graphics,javafx.fxml 




## How To Run
Click on run(a small green play button on the top bar) if you don't see any error and an app screen should popup displaying the login page of the app. If you see any errors
Please scroll down to see the troubleshooting page.
### Default user ###
- In the login page you can use one of our three default users "smoothcjb", "password1" "diyap1234", "password2","xxh", ""or you can add [User (your name) = new User("your username", "your password");] in the main file under //users
- Type in your credentials then click view calendar, an error will show up if the password/username is incorrect.
- Once you have successfully logged in you can enjoy the convenience of viewing our calendar, add and remove events at your own convenience!
- You can change the month by clicking on previous month or next month button. 
- You can select a date then put in the details of an event then click on "Click to add event!" your event will show up in the upcoming events list view.
- You can remove an existing event by selecting the event and click remove.


## Common Issue That Might Happen ##
- All the file has error (fix: Make sure that you import the correct library for your build path.) 
- JavaFx error (fix: Make sure in your run configuration you
change VM arguments to --module-path [JavaFX directory in your local drive] --add-modules javafx.controls,javafx.graphics,javafx.fxml )
- Java is outdated. If you see errors related to the java version make sure you update java JDK to the latest version.
- We have user reports that JavaFX couldn't run on certain CPU models. If you have issues with compatibility try the application with a different computer.
