/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Scott
 */
public class ApplicationAdminView extends UserApplication {
    SimpleStringProperty major;
    SimpleStringProperty year;
    SimpleStringProperty project;
    public ApplicationAdminView(String date, String name, String status, String major, String year,String project) {
	super(date,name,status);
	this.major = new SimpleStringProperty(major);
	this.year = new SimpleStringProperty(year);
	this.project = new SimpleStringProperty(project);
    }
    public String getMajor() {
	return major.get();
    }
    public String getYear() {
	return year.get();
    }
    public String getProject() {
	return project.get();
    }
    public void setMajor(String newMajor) {
	major.set(newMajor);
    }
    public void setYear(String newYear) {
	year.set(newYear);
    }
    public void setProject(String newProject) {
	project.set(newProject);
    }
}
