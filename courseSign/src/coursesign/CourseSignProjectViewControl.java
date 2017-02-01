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
public class CourseSignProjectViewControl extends AbstractMetaData {
    /*private String uName;
    String url = "jdbc:mysql://localhost:3306/cs4400t81";
    String user = "root";
    String password = "Yo282gNE!";
    String query;*/
    private String projName;
    /*Connection conn;
    Statement st;
    ResultSet rs;*/
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
    Label labelCategory;
    @FXML
    Label labelErrorMessage;
    @FXML
    Button buttonReturnToMain;
    @FXML
    Button buttonApply;
    @FXML
    protected void initialize() throws Exception {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement();          
    }
    /*public void setUname(String name) {
        this.uName = name;
    }*/
    public void loadProject(String projName) throws Exception {
	this.projName = projName;
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
	query = "SELECT CatName FROM Proj_Is_Category WHERE ProjName='"+projName+"'";
	rs=st.executeQuery(query);
	labelCategory.setText("Category: ");
	while(rs.next()) {
	    labelCategory.setText(labelCategory.getText() + rs.getString("CatName")+", ");
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
    public void applyToProject() throws Exception {
        query = "SELECT * FROM Application WHERE Username='"+uName+"' AND ProjName='"+projName+"'";
	rs = st.executeQuery(query);
	if(!rs.next()) {
	    //Application doesn't exist, check if user meets requirements (if there are any)
	    query = "SELECT MajorName,GradYear FROM Student WHERE Username='" + uName + "'";
	    rs2 = st.executeQuery(query);
	    String major = "";
	    String year = "";
	    while(rs2.next()) {
	    major = rs2.getString("MajorName");
	    year = rs2.getString("GradYear");
	    }
	    if(major != null && year != null) {
	    query = "SELECT * FROM (SELECT CASE " +
"    WHEN NOT EXISTS( " +
"    Select * from Requirement R " +
"     where Pname='" + projName + "' AND EXISTS ( " +
"            SELECT NULL FROM( " +
"            SELECT 'Freshman' AS Year  " +
"            UNION ALL SELECT 'Sophomore'  " +
"            UNION ALL Select 'Junior'  " +
"            UNION ALL SELECT 'Senior' " +
"        ) Y WHERE R.Requirement LIKE CONCAT(Y.Year,'%')     " +
"    ) " +
"    ) THEN 'n/a' " +
"    WHEN EXISTS(select concat(GradYear,' students only') as Req  " +
"        from Student  " +
"        where Username='" + uName + "' AND  " +
"        concat(GradYear,' students only') in (SELECT Requirement as Req FROM Requirement WHERE Pname='" + projName + "') " +
"    ) THEN 'yes' " +
"    ELSE 'no' " +
"END AS yr,  " +
"CASE " +
"    WHEN NOT EXISTS( " +
"    select * from Requirement R  " +
"    WHERE pName='" + projName + "' AND EXISTS ( " +
"        SELECT NULL From Department  " +
"        Where R.Requirement LIKE CONCAT(DeptName, '%') " +
"    ) " +
"    ) THEN 'n/a' " +
"    WHEN EXISTS( " +
"        select concat(DeptName,' students only') as Req " +
"        from Major where Major.MajorName = ( " +
"            select MajorName from Student where Username='" + uName + "' " +
"            ) And concat(DeptName,' students only') in (SELECT Requirement FROM Requirement WHERE Pname='" + projName + "') " +
"    ) THEN 'yes' " +
"    ELSE 'no' " +
"END as dept, " +
"CASE  " +
"    WHEN NOT EXISTS( " +
"    select * from Requirement R  " +
"    WHERE pName='" + projName + "' AND EXISTS ( " +
"        SELECT NULL From Major  " +
"        Where R.Requirement LIKE CONCAT(MajorName, '%') " +
"    ) " +
") THEN 'n/a' " +
"    WHEN EXISTS( " +
"        Select concat(MajorName,' students only') as Req " +
"        from Student WHERE Student.Username='" + uName + "' " +
"        AND concat(MajorName,' students only') In (SELECT Requirement FROM Requirement WHERE Pname='" + projName + "') " +
"    ) THEN 'yes' " +
"    ELSE 'no' " +
"END as mjr) T WHERE " +
"((T.yr='n/a' OR T.yr='yes') AND (IF(T.mjr<>'no',IF(T.dept<>'no',true,false),IF(T.dept='yes',true,false)) OR IF(T.dept<>'no',IF(T.mjr<>'no',true,false),IF(T.mjr='yes',true,false))))";
	    rs=st.executeQuery(query);
	    if(rs.next()) {
		//not empty, user met requirements
	    query = "INSERT INTO Application VALUES ('"+uName+"','"+projName+"',CURDATE(),'pending')";
	    st.executeUpdate(query);
	    returnToMain();
	    } else {
		//empty, user did not meet requirements
		System.out.println("User did not meed requirements");
		labelErrorMessage.setText("Error: You do not meet the requirements for this project.");
		labelErrorMessage.setVisible(true);
	    }
	    } else {
		//user hasn't set degree and year
		labelErrorMessage.setText("Error: You must first set your degree and year under the Edit Profile menu before applying to a project");
		labelErrorMessage.setVisible(true);
	    }
	} else {
	    //application exists, notify user
	    System.out.println("You have already applied for this project");
	    labelErrorMessage.setText("Error: you have already applied for this project");
	    labelErrorMessage.setVisible(true);
	}
    }
}
