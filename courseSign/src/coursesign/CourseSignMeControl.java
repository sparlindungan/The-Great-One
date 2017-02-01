/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.image.ImageView;
import java.sql.*;
import javafx.scene.Cursor;
/**
 *
 * @author Scott
 */
public class CourseSignMeControl {
    private String uName;
    @FXML
    Label labelEditProfile;
    @FXML
    Label labelMyApplication;
    @FXML
    protected void initialize() {
        
    }
    protected void setUname(String name) {
        this.uName = name;
    }
    public void setMouseClickable() {
        labelEditProfile.getScene().setCursor(Cursor.HAND);
    }
    public void setMouseNormal() {
        labelEditProfile.getScene().setCursor(Cursor.DEFAULT);
    }
    public void editProfile() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneEditProfile.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignEditProfileControl csepc = (CourseSignEditProfileControl) fxmlLoader.getController();
                csepc.setUname(uName);
                csepc.loadUserData();
                Stage stage = (Stage) labelEditProfile.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();           
    }
    public void viewMyApp() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneMyApp.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignMyAppController csmac = (CourseSignMyAppController) fxmlLoader.getController();
                csmac.setUname(uName);
                csmac.loadUserData();
                //initialize table
                Stage stage = (Stage) labelEditProfile.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();           
    }
    public void returnToMain() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneMainPage.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignMainControl csmc = (CourseSignMainControl) fxmlLoader.getController();
                csmc.initialize(uName);
                Stage stage = (Stage) labelEditProfile.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();        
    }
}
