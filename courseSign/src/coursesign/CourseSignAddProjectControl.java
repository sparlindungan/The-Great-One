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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class CourseSignAddProjectControl extends AbstractMetaData {
    @FXML
    TextField textFieldTitle;
    @FXML
    TextField textFieldAdvisor;
    @FXML
    TextField textFieldAdvisorEmail;
    @FXML
    TextArea textAreaDescription;
    @FXML
    ComboBox comboBoxDesignation;
    @FXML
    TextField textFieldClassSize;
    @FXML
    ComboBox comboBoxMajor;
    @FXML
    ComboBox comboBoxYear;
    @FXML
    ComboBox comboBoxDepartment;
    @FXML
    Button buttonSubmit;
    @FXML
    Button buttonBack;
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
    Label labelErrorMessage;
    
    private List<RadioButton> catOptions;
    @FXML
    protected void initialize() throws Exception {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();        
	//group radio buttons for easy reading
        comboBoxDesignation.getItems().clear();
        comboBoxMajor.getItems().clear();
        comboBoxYear.getItems().clear();
	comboBoxDepartment.getItems().clear();
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
	//build designation combobox
        query = "SELECT * FROM Designation";
        rs = st.executeQuery(query);
        while(rs.next()) {
            comboBoxDesignation.getItems().add(rs.getString(1));
        }
	query = "SELECT MajorName FROM Major";
	rs=st.executeQuery(query);
	while(rs.next()) {
	    comboBoxMajor.getItems().add(rs.getString("MajorName"));
	}
	query = "SELECT DeptName FROM Department";
	rs=st.executeQuery(query);
	while(rs.next()) {
	    comboBoxDepartment.getItems().add(rs.getString("DeptName"));
	}
        comboBoxYear.getItems().addAll(
            "Freshman",
            "Sophomore",
            "Junior",
            "Senior"
        );
    }
    public void returnToPrev() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneChooseFunctionality.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  	
    }
    public void submit() throws Exception {
	if(textFieldTitle.getText().equals("") || textFieldAdvisor.getText().equals("") ||
		textFieldAdvisorEmail.getText().equals("") || textAreaDescription.getText().equals("") ||
		comboBoxDesignation.getValue() == null || textFieldClassSize.equals("")) {
	    //one or more required fields are empty
	    labelErrorMessage.setText("Error: please ensure all required fields are filled in");
	    labelErrorMessage.setVisible(true);
	} else if(textFieldTitle.getText().length() > 255) {
	    labelErrorMessage.setText("Error: project name cannot be greater than 255 characters");
	    labelErrorMessage.setVisible(true);
	}else if(textFieldAdvisor.getText().length() > 255) {
	    labelErrorMessage.setText("Error: Advisor name cannot be longer than 255 characters");
	    labelErrorMessage.setVisible(true);
	}else if(textFieldAdvisorEmail.getText().length() > 255) {
	    labelErrorMessage.setText("Error: Advisor e-mail cannot be longer than 255 characters");
	    labelErrorMessage.setVisible(true);
	}else if(textAreaDescription.getText().length() > 1000) {
	    labelErrorMessage.setText("Error: Description cannot be longer than 1000 characters");
	    labelErrorMessage.setVisible(true);
	}else if(!isNumeric(textFieldClassSize.getText())) {
	    labelErrorMessage.setText("Error: class size must be a number");
	}else {
	    //insert class
	    query = "SELECT * FROM Project WHERE Name='" + textFieldTitle.getText() + "'";
	    rs = st.executeQuery(query);
	    if(rs.next()) {
		//class exists, display user error
	    } else {
	    query = "INSERT INTO Project VALUES('" + textFieldTitle.getText()+"',"+textFieldClassSize.getText()+",'"+
		    textFieldAdvisor.getText()+"','"+textFieldAdvisorEmail.getText()+"','"+textAreaDescription.getText()+"','"
		    +comboBoxDesignation.getValue()+"')";
	    st.executeUpdate(query);
	    if(comboBoxMajor.getValue() != null) {
		//insert into requirement field
		query = "INSERT INTO Requirement VALUES('" + textFieldTitle.getText() + "','"+
			comboBoxMajor.getValue() + " students only')";
		st.executeUpdate(query);
	    }
	    if(comboBoxYear.getValue() != null) {
		//insert into requirement table
		query = "INSERT INTO Requirement VALUES('" + textFieldTitle.getText() + "','"+
			comboBoxYear.getValue() + " students only')";
		st.executeUpdate(query);
	    }
	    if(comboBoxDepartment.getValue() != null) {
		query = "INSERT INTO Requirement VALUES('" + textFieldTitle.getText() + "','"+
			comboBoxDepartment.getValue() + " students only')";
		st.executeUpdate(query);
	    }
	    for(int i=0;i<catOptions.size();i++) {
		//cycle through selected radio buttons to create entries in
		//Proj_Is_Category table
		if(catOptions.get(i).isSelected()) {
		    //create entry in Proj_Is_Category table
		    query = "INSERT INTO Proj_Is_Category VALUES('" +
			    textFieldTitle.getText()+"','"+
			    catOptions.get(i).getText()+"')";
		    st.executeUpdate(query);
		}
	    }
	    returnToPrev();		
	    }
	}
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
