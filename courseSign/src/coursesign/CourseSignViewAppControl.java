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
public class CourseSignViewAppControl extends AbstractMetaData {
    public ObservableList<ApplicationAdminView> data;
    private String status;
    @FXML
    TableView<ApplicationAdminView> tableViewApps;
    @FXML
    Button buttonBack;
    @FXML
    Button rejectButton;
    @FXML
    Button acceptButton;
    @FXML
    protected void initialize() {
	try {
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
	} catch(Exception e) {
	    
	}
    }
    public void buildTable(Boolean addListeners) throws Exception {
        tableViewApps.getItems().clear();
        tableViewApps.getColumns().clear();
        TableColumn<ApplicationAdminView, String> colProj = new TableColumn("Project");
        TableColumn<ApplicationAdminView, String> colMajor = new TableColumn("Applicant Major");
        TableColumn<ApplicationAdminView, String> colYear = new TableColumn("Applicant Year");
        TableColumn<ApplicationAdminView, String> colStatus = new TableColumn("Status");
	tableViewApps.getColumns().addAll(colProj,colMajor,colYear,colStatus);
	tableViewApps.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	query = "SELECT AppDate,Username,Status,MajorName,GradYear,ProjName FROM Application a NATURAL JOIN Student s";
	rs = st.executeQuery(query);
            data = FXCollections.observableArrayList();
            ApplicationAdminView appInstance;
            while(rs.next()) {
                appInstance = new ApplicationAdminView(rs.getString("AppDate"),rs.getString("Username"),rs.getString("Status"),rs.getString("MajorName"),rs.getString("GradYear"),rs.getString("ProjName"));
                data.add(appInstance);
	    }
            colProj.setCellValueFactory(new PropertyValueFactory<ApplicationAdminView,String>("project"));
            colMajor.setCellValueFactory(new PropertyValueFactory<ApplicationAdminView,String>("major"));
            colYear.setCellValueFactory(new PropertyValueFactory<ApplicationAdminView,String>("year"));
            colStatus.setCellValueFactory(new PropertyValueFactory<ApplicationAdminView,String>("status"));
            tableViewApps.setItems(data);
	if(addListeners) {
	    tableViewApps.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
		if(tableViewApps.getSelectionModel().getSelectedItem() != null) {
		this.setUname(newValue.getName());
		this.setAppName(newValue.getProject());
		this.status = newValue.getStatus();
		System.out.println(newValue.getName());
		System.out.println(newValue.getProject());
		}
	    });
	}
    }
    public void acceptApplication() throws Exception {
	if(status.equals("pending")) {
	tableViewApps.getSelectionModel().clearSelection();
	query = "UPDATE Application SET Status='accepted' WHERE Username='"+this.uName+"' AND ProjName='" +
		this.appName + "'";
	st.executeUpdate(query);
	buildTable(false);
    }
    }
    public void rejectApplication() throws Exception {
	if(status.equals("pending")) {
	tableViewApps.getSelectionModel().clearSelection();
	query = "UPDATE Application SET Status='rejected' WHERE Username='"+this.uName+"' AND ProjName='" +
		this.appName + "'";
	st.executeUpdate(query);
	buildTable(false);
	}
    }
    public void returnToPrev() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneChooseFunctionality.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) tableViewApps.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
