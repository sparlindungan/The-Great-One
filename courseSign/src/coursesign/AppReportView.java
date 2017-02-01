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
public class AppReportView {
    SimpleStringProperty pName;
    SimpleStringProperty numApps;
    SimpleStringProperty accRate;
    SimpleStringProperty topMajors;
    public AppReportView(String pName,String numApps,String accRate, String topMajors) {
	this.pName = new SimpleStringProperty(pName);
	this.numApps = new SimpleStringProperty(numApps);
	this.accRate = new SimpleStringProperty(accRate);
	this.topMajors = new SimpleStringProperty(topMajors);
    }
    public String getPName() {
	return pName.get();
    }
    public String getNumApps() {
	return numApps.get();
    }
    public String getAccRate() {
	return accRate.get();
    }
    public String getTopMajors() {
	return topMajors.get();
    }
}
