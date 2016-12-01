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
import javafx.beans.InvalidationListener;
import java.sql.*;
import javafx.beans.value.ObservableValue;
import javafx.beans.Observable;
import javafx.scene.Cursor;
/**
 *
 * @author Scott
 */
public class CourseSignMyAppController {
    private String uName;
    String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    Connection conn;
    Statement st;
    ResultSet rs;
    String query;
    public ObservableList<UserApplication> data;
    @FXML
    TableView tableViewApp;
    @FXML
    Button buttonReturnToMe;
    public void setUname(String name) {
        this.uName = name;
    }
    @FXML
    protected void Initialize() throws Exception {   
    }
    public void loadUserData() throws Exception {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();     
        tableViewApp.getItems().clear();
        tableViewApp.getColumns().clear();
        TableColumn<UserApplication, String> colDate = new TableColumn("Date");
        TableColumn<UserApplication, String> colName = new TableColumn("Name");
        TableColumn<UserApplication, String> colStatus = new TableColumn("Status");
        tableViewApp.getColumns().addAll(colDate,colName,colStatus);
        query = "SELECT AppDate,ProjName,Status FROM Application WHERE Username='" + uName +"'";
        rs = st.executeQuery(query);
        data = FXCollections.observableArrayList();
        UserApplication appInstance;
        while(rs.next()) {
            appInstance = new UserApplication(rs.getString("AppDate"),rs.getString("ProjName"),rs.getString("Status"));
            data.add(appInstance);
        }
        colDate.setCellValueFactory(new PropertyValueFactory<UserApplication,String>("date"));
        colName.setCellValueFactory(new PropertyValueFactory<UserApplication,String>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<UserApplication,String>("status"));
        tableViewApp.setItems(data);
    }
    public void returnToMe() {
        
    }
}
