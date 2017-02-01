/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Cursor;
/**
 *
 * @author Scott
 */
public class CourseSignAddCourseControl extends AbstractMetaData {
    List<RadioButton> catOptions;
    @FXML
    TextField textFieldCourseNum;
    @FXML
    TextField textFieldCourseName;
    @FXML
    TextField textFieldInstructor;
    @FXML
    TextField textFieldClassSize;
    @FXML
    ComboBox comboBoxDesignation;
    @FXML
    RadioButton radioButtonCFG;
    @FXML
    RadioButton radioButtonAL;
    @FXML
    RadioButton radioButtonCS;
    @FXML
    RadioButton radioButtonCA;
    @FXML
    RadioButton radioButtonSC;
    @FXML
    RadioButton radioButtonRTAL;
    @FXML
    RadioButton radioButtonUD;
    @FXML
    RadioButton radioButtonTFSG;
    @FXML
    RadioButton radioButtonDGFYN;
    @FXML
    Button buttonSubmit;
    @FXML
    Button buttonBack;
    @FXML
    Label labelErrorMessage;
    @FXML
    protected void initialize() throws Exception {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();        
	//group radio buttons for easy reading
        comboBoxDesignation.getItems().clear();
	catOptions = new ArrayList<RadioButton>();
	catOptions.add(radioButtonCFG);
	catOptions.add(radioButtonAL);
	catOptions.add(radioButtonCS);
	catOptions.add(radioButtonCA);
	catOptions.add(radioButtonSC);
	catOptions.add(radioButtonRTAL);
	catOptions.add(radioButtonUD);
	catOptions.add(radioButtonTFSG);
	catOptions.add(radioButtonDGFYN);
        query = "SELECT * FROM Designation";
        rs = st.executeQuery(query);
        while(rs.next()) {
            comboBoxDesignation.getItems().add(rs.getString("Name"));
        }	
    }
    public void submit() throws Exception {
	if(textFieldCourseNum.getText().equals("") || textFieldCourseName.getText().equals("") ||
		textFieldInstructor.getText().equals("") || comboBoxDesignation.getValue() == null ||
		textFieldClassSize.getText().equals("")) {
	    //show error message
	    System.out.println("Error: please ensure all required fields are filled in");
	    labelErrorMessage.setText("Error: please ensure all required fields are filled in");
	    labelErrorMessage.setVisible(true);
	} else if(textFieldCourseNum.getText().length() > 50) {
	    labelErrorMessage.setText("Error: Class number cannot be longer than 50 characters");
	    labelErrorMessage.setVisible(true);
	} else if(textFieldCourseName.getText().length() > 100) {
	    labelErrorMessage.setText("Error: Class name cannot be longer than 100 characters");
	    labelErrorMessage.setVisible(true);
	} else if(textFieldInstructor.getText().length() > 255) {
	    labelErrorMessage.setText("Error: Instructor name cannot be longer than 255 characters");
	    labelErrorMessage.setVisible(true);
	} else if(!isNumeric(textFieldClassSize.getText())) {
	    labelErrorMessage.setText("Error: class size must be a number");
	    labelErrorMessage.setVisible(true);
	}else {
	    query = "SELECT * FROM Course WHERE CourseName='" + textFieldCourseName.getText()+"'";
	    rs = st.executeQuery(query);
	    if(rs.next()) {
		//class exists, display error
	    } else {
	    //submit clas
	    query = "INSERT INTO Course VALUES('" + textFieldCourseNum.getText() +"','" + textFieldCourseName.getText() + "'," +
		    textFieldClassSize.getText() + ",'" + textFieldInstructor.getText()+"','" + comboBoxDesignation.getValue()+"')";
	    st.executeUpdate(query);
	    for(int i=0;i<catOptions.size();i++) {
		//cycle through selected radio buttons to create entries in
		//Course_Is_Category table
		if(catOptions.get(i).isSelected()) {
		    //create entry in Proj_Is_Category table
		    query = "INSERT INTO Course_Is_Category VALUES('" +
			    textFieldCourseName.getText()+"','"+
			    catOptions.get(i).getText()+"')";
		    st.executeUpdate(query);
		}
	    }
	    returnToPrev();		
	    }	    
	}
    }
    public void returnToPrev() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneChooseFunctionality.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  	
    }
    public static boolean isNumeric(String str)  {  
	try  {  
	    double d = Double.parseDouble(str);  
	}  
	catch(NumberFormatException nfe) {  
	    return false;  
	}  
	return true;  
    }
}
