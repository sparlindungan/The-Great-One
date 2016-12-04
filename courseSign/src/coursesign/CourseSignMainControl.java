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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
import java.util.ArrayList;
import java.util.List;
//import javafx.beans.value.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.Observable;
import javafx.scene.Cursor;
/**
 *
 * @author Scott
 */
public class CourseSignMainControl extends AbstractMetaData {
    List<RadioButton> catOptions;
    String majorFilter;
    String desigFilter;
    String yearFilter;
    public ObservableList<SignUp> data;
    @FXML
    ComboBox comboBoxDesignation;
    @FXML
    ComboBox comboBoxMajor;
    @FXML
    ComboBox comboBoxYear;
    @FXML
    TextField textFieldTitle;
    @FXML
    RadioButton radioButtonCourse;
    @FXML
    RadioButton radioButtonProject;
    @FXML
    RadioButton radioButtonBoth;
    @FXML
    TableView<SignUp> tableViewMain;
    @FXML
    Button buttonApplyFilter;
    @FXML
    Button buttonResetFilter;
    @FXML
    ImageView imageViewUser;
    @FXML
    RadioButton radioButtonCFG;
    @FXML
    RadioButton radioButtonAL;
    @FXML
    RadioButton radioButtonCS;
    @FXML
    RadioButton radioButtonCA;
    @FXML
    RadioButton radioButtonSC;
    @FXML
    RadioButton radioButtonRTAL;
    @FXML
    RadioButton radioButtonUD;
    @FXML
    RadioButton radioButtonTFSG;
    @FXML
    RadioButton radioButtonDGFYN;
    @FXML
    protected void initialize() throws Exception{
        //initialize Designation combobox
        comboBoxDesignation.getItems().clear();
        comboBoxMajor.getItems().clear();
        comboBoxYear.getItems().clear();
	catOptions = new ArrayList<RadioButton>();
	catOptions.add(radioButtonCFG);
	catOptions.add(radioButtonAL);
	catOptions.add(radioButtonCS);
	catOptions.add(radioButtonCA);
	catOptions.add(radioButtonSC);
	catOptions.add(radioButtonRTAL);
	catOptions.add(radioButtonUD);
	catOptions.add(radioButtonTFSG);
	catOptions.add(radioButtonDGFYN);
        final ToggleGroup courseProj = new ToggleGroup();
        radioButtonCourse.setToggleGroup(courseProj);
        radioButtonProject.setToggleGroup(courseProj);
        radioButtonBoth.setToggleGroup(courseProj);
            conn = DriverManager.getConnection(url, user, password);
            query = "SELECT * FROM Designation";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                comboBoxDesignation.getItems().add(rs.getString(1));
            }
            query = "SELECT MajorName FROM Major";
            rs = st.executeQuery(query);
            while(rs.next()) {
                comboBoxMajor.getItems().add(rs.getString(1));
            }
            comboBoxYear.getItems().addAll(
                    "Freshman",
                    "Sophomore",
                    "Junior",
                    "Senior"
            );
        //initialize the table
            query = "SELECT CourseName AS Name,'Course' As Type FROM Course UNION SELECT Project.Name AS Name,'Project' As Type FROM Project";
            buildTable(true);
            //System.out.println(this.uName);
    }
    protected void initialize(String uName) throws Exception{
        setUname(uName);
        initialize();
    }
    public void applyFilter() throws Exception {
        //query = "SELECT CourseName AS Name FROM Course" + (comboBoxDesignation.getValue().equals("") ? "":" WHERE Designation='" + comboBoxDesignation.getValue() + "'");
        buildFilter();
        buildTable(false);
    }
    private void buildTable(Boolean initBuild) throws Exception {
        tableViewMain.getItems().clear();
        tableViewMain.getColumns().clear();
        TableColumn<SignUp, String> colName = new TableColumn("Name");
        TableColumn<SignUp, String> colType = new TableColumn("Type");
        tableViewMain.getColumns().addAll(colName,colType);
	tableViewMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            rs = st.executeQuery(query);
            data = FXCollections.observableArrayList();
            SignUp signUpInstance;
            while(rs.next()) {
                signUpInstance = new SignUp(rs.getString(1),rs.getString(2));
                data.add(signUpInstance);
            }
            colName.setCellValueFactory(new PropertyValueFactory<SignUp,String>("name"));
            colType.setCellValueFactory(new PropertyValueFactory<SignUp,String>("type"));
            tableViewMain.setItems(data);
            if(initBuild) {
            tableViewMain.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (tableViewMain.getSelectionModel().getSelectedItem() != null) {
                System.out.println(newValue.getName());
                if(newValue.getType().equals("Course")) {
                    //open course view
                    //loadClassView(newValue.getName());
                try{
                    loadClassView(newValue.getName());
                } catch(Exception e) {
                
                }
            } else {
                    //open project application
                try{
                    loadProjectView(newValue.getName());
                } catch(Exception e) {
                    
                }
            }
            }
        });
            }
        /*tableViewMain.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
        @Override
        public void invalidated(Observable arg0) {

        }
        public void changed(ObservableValue<? extends SignUp> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("changed " + oldValue + "->" + newValue);
        }
        });*/

    }
    private void loadClassView(String className) throws Exception{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneClassView.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignClassViewControl cscvc = (CourseSignClassViewControl) fxmlLoader.getController();
                cscvc.loadClass(className);
		cscvc.setUname(uName);
		Stage stage = (Stage) imageViewUser.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    private void loadProjectView(String projName) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneProjectView.fxml"));
        Parent root = fxmlLoader.load();
        CourseSignProjectViewControl cspvc = (CourseSignProjectViewControl) fxmlLoader.getController();
        Stage stage = (Stage) imageViewUser.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        cspvc.setUname(uName);
        cspvc.loadProject(projName);
        stage.show();        
    }
    public void comboDesignationChange() {
        
    }
    public void comboMajorChange() {
        
    }
    public void comboYearChange() {
        
    }
    public void resetFilter() throws Exception {
        comboBoxDesignation.setValue(null);
        comboBoxMajor.setValue(null);
        comboBoxYear.setValue(null);
        query = "SELECT CourseName AS Name, 'Course' AS Type FROM Course UNION SELECT Project.Name as Name, 'Project' AS Type FROM Project";
        buildTable(false);
    }
    private void buildFilter() {
        int filterFlag = 0;
        filterFlag = (textFieldTitle.getText().equals("") ? filterFlag : filterFlag | 1);
        filterFlag = (comboBoxDesignation.getValue() == null ? filterFlag : filterFlag | 2);
        filterFlag = (comboBoxMajor.getValue() == null ? filterFlag : filterFlag | 4);
        filterFlag = (comboBoxYear.getValue() == null ? filterFlag : filterFlag | 8);
	filterFlag = (this.checkCategoryEmpty() ? filterFlag : filterFlag | 16);
        String projSegment = "";
        String courseSegment = "";
        //query = "SELECT CourseName AS Name,'Course' As Type FROM Course UNION SELECT Project.Name AS Name,'Project' As Type FROM Project";
        if(radioButtonCourse.isSelected() || radioButtonBoth.isSelected() || !(radioButtonProject.isSelected() || radioButtonCourse.isSelected())) {
            //add course to overall query
            courseSegment = "SELECT CourseName AS Name, 'Course' AS Type FROM Course";
            if((filterFlag & 1) != 0) {
                //set title
                courseSegment += " WHERE Course.CourseName LIKE '%" + textFieldTitle.getText() + "%'";
            }
            if((filterFlag & 2) != 0) {
                //Designation search
                if((filterFlag & 1) == 0) {
                    //if it's the first condition, add WHERE to it
                    courseSegment += " WHERE ";
                } else {
                    courseSegment += " AND ";
                }
                courseSegment += "Course.Designation='" + comboBoxDesignation.getValue() + "' ";
            }
	    if((filterFlag & 16) != 0) {
		//categort filter
		if((filterFlag & 15) == 0) {
		    courseSegment += " WHERE ";
		} else {
		    courseSegment += " AND ";
		}
		courseSegment += "EXISTS (SELECT * FROM Course_Is_Category WHERE " + 
		"CourseName=Course.CourseName AND (CatName=";
		int catCounter = 0;
		for(int i=0;i<catOptions.size();i++) {
		    if(catOptions.get(i).isSelected()) {
			courseSegment += "'"+catOptions.get(i).getText()+"' OR CatName=";
			//catCounter++;
		    }
		}
		courseSegment = courseSegment.substring(0,courseSegment.length() - 12);
		courseSegment += "))";
		//projSegment += ") HAVING COUNT(*)=" + catCounter+")";
	    }
        }
        if(radioButtonProject.isSelected() || radioButtonBoth.isSelected() || !(radioButtonProject.isSelected() || radioButtonCourse.isSelected())) {
	    projSegment = "SELECT Project.Name AS Name, 'Project' AS Type FROM Project";
            if((filterFlag & 1) != 0) {
                //set title
                projSegment += " WHERE Project.Name LIKE '%" + textFieldTitle.getText() + "%'";
            }
            if((filterFlag & 2) != 0) {
                //Designation search
                if((filterFlag & 1) == 0) {
                    //if it's the first condition, add WHERE to it
                    projSegment += " WHERE ";
                } else {
                    projSegment += " AND ";
                }
                projSegment += "Project.Designation='" + comboBoxDesignation.getValue() + "' ";
            }
	    if((filterFlag & 4) != 0) {
                if((filterFlag & 3) == 0) {
                    //if it's the first condition, add WHERE to it
                    projSegment += " WHERE ";
                } else {
                    projSegment += " AND ";
                }
		projSegment += "    Name IN ( " +
"        SELECT Pname FROM Requirement WHERE Requirement='"+comboBoxMajor.getValue() + " students only'" +
"        )";
		//Also do a department check
		projSegment += "OR " +
"Name IN (" +
"    SELECT PName FROM Requirement WHERE Requirement=Concat((" +
"    SELECT GROUP_CONCAT(DeptName) FROM Major WHERE Major.MajorName='"+comboBoxMajor.getValue() + "'),' students only'))";
	    }
	    if((filterFlag & 8) != 0) {
                if((filterFlag & 7) == 0) {
                    //if it's the first condition, add WHERE to it
                    projSegment += " WHERE ";
                } else {
                    projSegment += " AND ";
                }
		projSegment += "(Name IN ( " +
"SELECT Pname FROM Requirement WHERE Requirement='" + comboBoxYear.getValue() + " students only'))";
	    }
	    if((filterFlag & 16) != 0) {
		//categort filter
		if((filterFlag & 15) == 0) {
		    projSegment += " WHERE ";
		} else {
		    projSegment += " AND ";
		}
		projSegment += "EXISTS (SELECT * FROM Proj_Is_Category WHERE " + 
		"ProjName=Project.Name AND (CatName=";
		int catCounter = 0;
		for(int i=0;i<catOptions.size();i++) {
		    if(catOptions.get(i).isSelected()) {
			projSegment += "'"+catOptions.get(i).getText()+"' OR CatName=";
			//catCounter++;
		    }
		}
		projSegment = projSegment.substring(0,projSegment.length() - 12);
		projSegment += "))";
		//projSegment += ") HAVING COUNT(*)=" + catCounter+")";
	    }
        }
        if(radioButtonBoth.isSelected() || (!(radioButtonProject.isSelected() || radioButtonCourse.isSelected()) && (comboBoxMajor.getValue() == null && comboBoxYear.getValue() == null))) {
            query = courseSegment + " UNION " + projSegment;
        } else if(radioButtonCourse.isSelected() || (!(radioButtonProject.isSelected() || radioButtonCourse.isSelected()) && (comboBoxMajor.getValue() == null && comboBoxYear.getValue() == null))) {
            query = courseSegment;
        } else {
            query = projSegment;
        }
        /*sub-queries to add in:
        projects have requirements =>
        SELECT Pname AS Name FROM Requirement WHERE
        Requirement='comboBoxValue + students only'
        do this for major & department
        */
    }
    private Boolean checkCategoryEmpty() {
	    for(int i=0;i<catOptions.size();i++) {
		if(catOptions.get(i).isSelected()) {
		    return false;
		}
	    }
	    return true;
    }
    public void launchMeWindow() throws Exception{
        System.out.println(uName);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneMe.fxml"));
                Parent root = fxmlLoader.load();
                CourseSignMeControl csmc = (CourseSignMeControl) fxmlLoader.getController();
                Stage stage = (Stage) imageViewUser.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                csmc.setUname(this.uName);
                stage.show();
    }
    public void setCourseOnly() {
        
    }
    public void setProjOnly() {
        
    }
    public void setCourseAndProj() {
        
    }
    public void setMouseClickable() {
        imageViewUser.getScene().setCursor(Cursor.HAND);
    }
    public void setMouseNormal() {
        imageViewUser.getScene().setCursor(Cursor.DEFAULT);
    }
}
