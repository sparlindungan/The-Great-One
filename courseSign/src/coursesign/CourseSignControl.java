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
public class CourseSignControl extends AbstractMetaData {
    @FXML
    Button regLoginButton;
    @FXML
    Button loginButton;
    @FXML
    TextField uNameLoginField;
    @FXML
    PasswordField pwdLoginField;
    @FXML
    Label labelError;
    @FXML
    Label labelWelcome;
    @FXML
    protected void initialize() throws Exception {
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	conn = DriverManager.getConnection(url, user, password);
    }
    public void switchToReg() throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("sceneRegister.fxml"));
        Stage stage = (Stage) regLoginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void loginAttempt() throws Exception {
        if(uNameLoginField.getText().equals("") || pwdLoginField.getText().equals("")) {
            System.out.println("one or more required fields is blank");
	    labelError.setText("Error: one or more required fields are blank");
	    labelError.setVisible(true);
        } else if(uNameLoginField.getText().length() > 15){
	    labelError.setText("Error: Username cannot be longer than 15 characters");
	    labelError.setVisible(true);
	} else if(pwdLoginField.getText().length() > 10 ) {
	    labelError.setText("Error: Password cannot be longer than 10 characters");
	    labelError.setVisible(true);
	}else {
            String result = "";
            conn = DriverManager.getConnection(url, user, password);
            query = "SELECT User_Type FROM Student WHERE BINARY Username= '" + uNameLoginField.getText() + "' AND BINARY Password= '" + pwdLoginField.getText() + "'";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                result = rs.getString(1);
            }
            if(result.equals("")) {
                //no user found
                labelError.setText("Error: user not found.  Please enter correct username and password or create an account");
                labelError.setVisible(true);
            } else if(result.equals("1")) {
                //user found is student
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneMainPage.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignMainControl csmc = (CourseSignMainControl) fxmlLoader.getController();
                //csmc.initialize(uNameLoginField.getText());
		csmc.setUname(uNameLoginField.getText());
                Stage stage = (Stage) uNameLoginField.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                //user found is admin, take to "ChooseFunctionality" page
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneChooseFunctionality.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) uNameLoginField.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();                
            }
        }
    }
}
