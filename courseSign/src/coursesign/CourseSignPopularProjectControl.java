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
public class CourseSignPopularProjectControl {
    String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    Connection conn;
    Statement st;
    ResultSet rs;
    String query;
    public ObservableList<PopProjectView> data;
    @FXML
    TableView tableViewPopularProject;
    @FXML
    Button buttonBack;
    @FXML
    protected void initialize() throws SQLException {
	try {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement(); 
	} catch(Exception e) {
	    
	}
        tableViewPopularProject.getItems().clear();
        tableViewPopularProject.getColumns().clear();
        TableColumn<PopProjectView, String> colCount = new TableColumn("# of Application");
        TableColumn<PopProjectView, String> colName = new TableColumn("Project");
        tableViewPopularProject.getColumns().addAll(colName,colCount);
	tableViewPopularProject.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        query = "SELECT ProjName,COUNT(ProjName) AS ProjCount FROM Application GROUP BY ProjName ORDER BY ProjCount DESC";
	rs = st.executeQuery(query);
        data = FXCollections.observableArrayList();
        PopProjectView projInstance;
        while(rs.next()) {
            projInstance = new PopProjectView(rs.getString("ProjName"),rs.getString("ProjCount"));
            data.add(projInstance);
        }
        colCount.setCellValueFactory(new PropertyValueFactory<PopProjectView,String>("count"));
        colName.setCellValueFactory(new PropertyValueFactory<PopProjectView,String>("name"));
        tableViewPopularProject.setItems(data);
    }
    public void returnToPrev() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneChooseFunctionality.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
}
