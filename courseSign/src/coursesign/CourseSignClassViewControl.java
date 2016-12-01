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
public class CourseSignClassViewControl {
    String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    String query;
    Connection conn;
    Statement st;
    ResultSet rs;
    @FXML
    Label labelClassNum;
    @FXML
    Label labelClassName;
    @FXML
    Label labelInstructorName;
    @FXML
    Label labelDesignation;
    @FXML
    Label labelCategory;
    @FXML
    Label labelClassSize;
    @FXML
    Button buttonClassBack;
    @FXML
    protected void initialize() throws Exception {
    conn = DriverManager.getConnection(url, user, password);
    st = conn.createStatement();   
    }
    public void loadClass(String classNum) throws Exception {
        query = "SELECT * FROM Course WHERE CourseName='" + classNum + "'";
        rs = st.executeQuery(query);
        while(rs.next()) {
            labelClassNum.setText(rs.getString("CourseNumber"));
            labelClassName.setText("Course Name: "+rs.getString("CourseName"));
            labelInstructorName.setText("Instructor: "+rs.getString("Instructor"));
            labelDesignation.setText("Designation: "+rs.getString("Designation"));
            labelClassSize.setText("Estimated Number of Students: "+rs.getString("EstNumberStudents"));
        }
    }
}
