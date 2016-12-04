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
public class CourseSignEditProfileControl extends AbstractMetaData {
    /*String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    String query;
    Connection conn;
    Statement st;
    ResultSet rs;
    private String uName;*/
    @FXML
    ComboBox comboBoxEditProfileMajor;
    @FXML
    ComboBox comboBoxEditProfileYear;
    @FXML
    Label labelEditProfileDepartment;
    @FXML
    Button buttonReturnToMe;
    @FXML
    protected void initialize() throws Exception { 
        comboBoxEditProfileMajor.getItems().clear();
        comboBoxEditProfileYear.getItems().clear();
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();
        query = "SELECT MajorName FROM Major";
        rs = st.executeQuery(query);
        while(rs.next()) {
            comboBoxEditProfileMajor.getItems().add(rs.getString(1));
        }
        //setValue to reflect user's degree
        comboBoxEditProfileYear.getItems().addAll(
                    "Freshman",
                    "Sophomore",
                    "Junior",
                    "Senior"
        );
        rs.close();
        //setValue to reflect user's year
    }
    /*public void setUname(String name) {
        this.uName = name;
    }*/
    public void loadUserData() throws Exception {
        query = "SELECT MajorName,GradYear FROM Student WHERE Username='" + uName +"'";
        rs = st.executeQuery(query);
        System.out.println(rs.getMetaData().getColumnCount());
        System.out.println(rs.getRow());
        /*for(int i = 1; i<= rs.getMetaData().getColumnCount();i++) {
            System.out.println(rs.getMetaData().getColumnName(i));
        }*/
        if(rs.next()) {
            String majorName = rs.getString("MajorName");
            String gradYear = rs.getString("GradYear");
            if(majorName != null) {
                //value in Major field
                comboBoxEditProfileMajor.setValue(majorName);
            }
            if(gradYear != null) {
                comboBoxEditProfileYear.setValue(gradYear);
            }
            if(comboBoxEditProfileMajor.getValue() == null) {
                labelEditProfileDepartment.setText("");
            } else {
                query = "SELECT DeptName FROM cs4400t81.Major WHERE MajorName='"
                        + comboBoxEditProfileMajor.getValue() +"'";
                rs = st.executeQuery(query);
                if(rs.next()) {
                    labelEditProfileDepartment.setText(rs.getString("DeptName"));
                } else {
		    labelEditProfileDepartment.setText("");
		}
            }
        }
    }
    public void updateDepartment() throws Exception {
        query = "SELECT DeptName FROM cs4400t81.Major WHERE MajorName='"
                + comboBoxEditProfileMajor.getValue() + "'";
                rs = st.executeQuery(query);
                while(rs.next()) {
                    labelEditProfileDepartment.setText(rs.getString("DeptName"));
                }
    }
    public void saveAndReturn() throws Exception {
        query = "UPDATE cs4400t81.Student SET MajorName=" + (comboBoxEditProfileMajor.getValue() != null ? "'"+comboBoxEditProfileMajor.getValue()+"'" : "NULL") + ","
                + "GradYear=" + (comboBoxEditProfileYear.getValue() != null ? "'"+comboBoxEditProfileYear.getValue()+"'" : "NULL") + " WHERE Username='"
                + uName + "'";
        st.executeUpdate(query);
        returnToMe();
    }
    private void returnToMe() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneMe.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignMeControl csmc = (CourseSignMeControl) fxmlLoader.getController();
                Stage stage = (Stage) labelEditProfileDepartment.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                csmc.setUname(this.uName);
                stage.show();
    }
}
