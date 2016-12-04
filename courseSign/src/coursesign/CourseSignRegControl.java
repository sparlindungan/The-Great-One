/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import java.sql.*;
/**
 *
 * @author Scott
 */
public class CourseSignRegControl extends AbstractMetaData {
    /*String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    String query;*/
    @FXML
    Button buttonCreateReg;
    @FXML
    TextField textFieldRegUname;
    @FXML
    TextField textFieldRegEmail;
    @FXML
    PasswordField pwdFieldRegPwd;
    @FXML
    PasswordField pwdFieldRegPwdConfirm;
    @FXML
    Label labelErrorMessage;
    public void registerUser() throws Exception {
        //check to see if initial conditions are met
        if(textFieldRegUname.getText().equals("") || textFieldRegEmail.getText().equals("")
                || pwdFieldRegPwd.getText().equals("") || pwdFieldRegPwdConfirm.getText().equals("")) {
            //one or more fields are left blank
	    labelErrorMessage.setVisible(true);
	    labelErrorMessage.setText("Error: one or more required fields are empty");
            //System.out.println("one or more fields are empty");
        } else if(textFieldRegUname.getText().length() > 15) {
	    labelErrorMessage.setText("Error: Username cannot be longer than 15 character");
	    labelErrorMessage.setVisible(true);
	}else if(!textFieldRegEmail.getText().contains("@gatech.edu")) {
            //not a valid GT email
            //System.out.println("not a valid Georgia Tech e-mail address");
	    labelErrorMessage.setVisible(true);
	    labelErrorMessage.setText("Error: the e-mail entered is not a valid Georgia Tech e-mail address");
        } else if(textFieldRegEmail.getText().length() > 255) {
	    labelErrorMessage.setText("Error: E-mail cannot be longer than 255 characters");
	    labelErrorMessage.setVisible(true);
	} else if(!pwdFieldRegPwd.getText().equals(pwdFieldRegPwdConfirm.getText())) {
            //passwords don't match
	    labelErrorMessage.setVisible(true);
	    labelErrorMessage.setText("Error: the entered passwords do not match");
            //System.out.println("passwords do not match");
        } else if(pwdFieldRegPwd.getText().length() > 10 || pwdFieldRegPwdConfirm.getText().length() > 10) {
	    labelErrorMessage.setVisible(true);
	    labelErrorMessage.setText("Error: password cannot be longer than 10 characters");
	}else {
            //run query to see if user exists already
            conn = DriverManager.getConnection(url, user, password);
            query = "SELECT Username FROM cs4400t81.Student WHERE Email= '" + textFieldRegEmail.getText() + "'";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            if(rs.isBeforeFirst()) {
                //not empty, user already exists
		labelErrorMessage.setVisible(true);
		labelErrorMessage.setText("Error: this e-mail address is already in use");
                //System.out.println("e-mail is already in use");
            } else {
                //doesn't exist in User table, but need to check for username being used
                query = "SELECT Username FROM cs4400t81.Student WHERE Username='" + 
                        textFieldRegUname.getText() + "'";
                rs = st.executeQuery(query);
                if(rs.isBeforeFirst()) {
                    //not empty, username already exists
		    labelErrorMessage.setVisible(true);
		    labelErrorMessage.setText("Error: this user name already exists");
                    //System.out.println("Username already exists");
                } else {
                    //username and e-mail has not been used, insert user data into User and Student tables
                    query = "INSERT INTO Student(Username,Password,Email,User_Type) VALUES ('" + textFieldRegUname.getText() + "','" 
                        + pwdFieldRegPwd.getText() + "','" + textFieldRegEmail.getText() + "',1)";
                    st.executeUpdate(query);
                    /*query = "INSERT INTO Student(Username,Email) VALUES ('" + textFieldRegUname.getText() + "','"
                        + textFieldRegEmail.getText() + "')";
                    st.executeUpdate(query);*/
		    //return to main page
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneLogin.fxml"));
		    Parent root = fxmlLoader.load();
		    Stage stage = (Stage) pwdFieldRegPwd.getScene().getWindow();
		    Scene scene = new Scene(root);
		    stage.setScene(scene);
		    stage.show();
                }
            }
        }
    }
}
