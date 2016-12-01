# The-Great-One
STRUCTURE:
scripts folder -> SQL scripts to create schema and propagate data in the cs4400t81 database.
To run these scripts, type the following command at a command line or terminal:
mysql -u [Username] -p[Password] < path/to/file.sql

FXML folder -> these are the FXML templates used in the overall program.  These files are to be put in the build/ folder in the netBeans project directory in order to properly run the program.  These were built using the JavaFX Scene Builder (http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html).  Each FXML file is tied to a controller in the project directory (naming format [FXML name]Control.java).  The class to use as the controller for the specified FXML file can be set in Scene Builder (set as coursesign.[Controller Class Name])

courseSign folder -> the NetBeans project folder.  Source files can be found inside of the src directory.  

The general code format for switching screens is as follows:
1. Create a method to be used for switching to specified screen
2. In JavaFX Scene Builder, set this method as the one to be run on the specified action (for button clicks, set it as the "on action" event)
3. Use this code template in the method:
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(["sceneName.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
4. If the page being switched to will require additional data (user name, application, etc.), add in a line to create a controller object.  Then run the method for setting the variable in the specified controller class.  Add this code after the fxmlLoader.load() line of code and before the getWindow() line of code
EXAMPLE: code piece for loading course view page:
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneClassView.fxml"));
        Parent root = fxmlLoader.load();
        CourseSignClassViewControl cscvc = (CourseSignClassViewControl) fxmlLoader.getController();
        Stage stage = (Stage) imageViewUser.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        cscvc.loadClass(className);
        stage.show();
