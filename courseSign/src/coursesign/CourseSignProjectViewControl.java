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
public class CourseSignProjectViewControl {
    private String uName;
    String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    String query;
    Connection conn;
    Statement st;
    ResultSet rs;
    ResultSet rs2;
    @FXML
    Label labelProjectTitle;
    @FXML
    Label labelAdvisor;
    @FXML
    Label labelDescription;
    @FXML
    Label labelDesignation;
    @FXML
    Label labelRequirements;
    @FXML
    Label labelCourseSize;
    @FXML
    Button buttonReturnToMain;
    @FXML
    Button buttonApply;
    @FXML
    protected void initialize() throws Exception {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();          
    }
    public void setUname(String name) {
        this.uName = name;
    }
    public void loadProject(String projName) throws Exception {
        query = "SELECT DISTINCT * FROM Project WHERE Name='"+projName+"'";
        rs = st.executeQuery(query);
        if(rs.next()) {
            labelProjectTitle.setText(rs.getString("Name"));
            labelAdvisor.setText("Advisor: " + rs.getString("AdvisorName") + " (" + rs.getString("AdvisorEmail") + ")");
            labelDescription.setText("Description: "+rs.getString("Description"));
            labelDesignation.setText("Designation: "+rs.getString("Designation"));
            labelCourseSize.setText("Estimated number of students: "+rs.getString("EstNumberStudents"));
            //requirements needs an inner loop
            labelRequirements.setText("Requirements: ");
            query = "SELECT Requirement FROM Requirement WHERE Pname='"+projName+"'";
            rs2=st.executeQuery(query);
            while(rs2.next()) {
                labelRequirements.setText(labelRequirements.getText() + rs2.getString("Requirement") +", ");
            }
        }
    }
    public void returnToMain() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneMainPage.fxml"));
        Parent root = fxmlLoader.load();
        CourseSignMainControl csmc = (CourseSignMainControl) fxmlLoader.getController();
        csmc.initialize(uName);
        Stage stage = (Stage) labelProjectTitle.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void applyToProject() {
        
    }
}
