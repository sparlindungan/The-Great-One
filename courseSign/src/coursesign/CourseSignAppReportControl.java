/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
/**
 *
 * @author Scott
 */
public class CourseSignAppReportControl extends AbstractMetaData {
    public ObservableList<AppReportView> data;
    @FXML
    TableView tableViewAppReport;
    @FXML
    Button buttonReturnToPrev;
    @FXML
    protected void initialize() {
	try {
        conn = DriverManager.getConnection(url, user, password);
        st = conn.createStatement(); 
	} catch(Exception e) {    
	}
    }
    public void generateAppReport() throws Exception {
        tableViewAppReport.getItems().clear();
        tableViewAppReport.getColumns().clear();
        TableColumn<AppReportView, String> colName = new TableColumn("Project Name");
        TableColumn<AppReportView, String> colNumApps = new TableColumn("# of Applications");
        TableColumn<AppReportView, String> colAccRate = new TableColumn("Acceptance Rate");
        TableColumn<AppReportView, String> colTopMajors = new TableColumn("Top 3 Majors");	
        tableViewAppReport.getColumns().addAll(colName,colNumApps,colAccRate,colTopMajors);
	//tableViewAppReport.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	query = "SELECT ProjName,TotalApps,AccRate,TopThree FROM (SELECT * FROM (SELECT ProjName, Count(ProjName) As TotalApps,((Sum(case when Application.Status='accepted' then 1 else 0 end) / Count(ProjName))*100) as AccRate " +
"FROM Student NATURAL JOIN Application GROUP BY ProjName) tOne  " +
"LEFT JOIN (select SUBSTRING_INDEX(GROUP_CONCAT(DISTINCT MajorName ORDER BY degCount DESC SEPARATOR ','),',',3)  As TopThree,pName " +
" from (  " +
"Select MajorName,ProjName as pName, Count(ProjName) as Top, Count(majorName) as DegCount  " +
"from Student NATURAL JOIN Application Group by projName,MajorName ORDER BY TOP DESC)  " +
"AS Tops group by pName) tTwo ON tOne.ProjName=tTwo.pName) T ORDER BY T.AccRate DESC";
	rs = st.executeQuery(query);
        data = FXCollections.observableArrayList();
        AppReportView projInstance;
        while(rs.next()) {
            projInstance = new AppReportView(rs.getString("ProjName"),rs.getString("TotalApps"),rs.getString("AccRate"),rs.getString("TopThree"));
            data.add(projInstance);
        }
        colName.setCellValueFactory(new PropertyValueFactory<AppReportView,String>("pName"));
	colNumApps.setCellValueFactory(new PropertyValueFactory<AppReportView,String>("numApps"));
	colAccRate.setCellValueFactory(new PropertyValueFactory<AppReportView,String>("accRate"));
	colTopMajors.setCellValueFactory(new PropertyValueFactory<AppReportView,String>("topMajors"));
        tableViewAppReport.setItems(data);
    }
    public void returnToPrev() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sceneChooseFunctionality.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) buttonReturnToPrev.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
}
