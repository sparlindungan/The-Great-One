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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.*;
/**
 *
 * @author Scott
 */
public class CourseSignChooseFunctionalityControl {
    @FXML
    Label labelViewApplications;
    @FXML
    Label labelViewPopularProj;
    @FXML
    Label labelViewAppReport;
    @FXML
    Label labelAddProject;
    @FXML
    Label labelAddCourse;
    @FXML
    Button buttonLogOut;
    public void logOut() throws Exception {
        //Return to main screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneLogin.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	
    }
    public void viewAppReport() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneAppReport.fxml"));
        Parent root = fxmlLoader.load();
	CourseSignAppReportControl csarc = (CourseSignAppReportControl) fxmlLoader.getController();
	csarc.generateAppReport();
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    public void viewPopularProjects() throws Exception {
	//go to Popular Projects screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenePopularProject.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void viewApplications() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneViewApp.fxml"));
        Parent root = fxmlLoader.load();
	CourseSignViewAppControl csvac = (CourseSignViewAppControl) fxmlLoader.getController();
	csvac.buildTable(true);
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();	
    }
    public void addACourse() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneAddCourse.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();	
    }
    public void addAProject() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneAddProject.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();	
    }
}
