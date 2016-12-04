/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Scott
 */
public abstract class AbstractMetaData {
    protected String url = "jdbc:mysql://localhost:3306/cs4400t81";
    protected String user = "root";
    protected String password = "Yo282gNE!";
    /*protected String url = "jdbc:mysql://academicmysql.cc.gatech.edu/cs4400_Team_81";
    protected String user = "cs4400_Team_81";
    protected String password = "h8SLUKjt";*/
    protected String query;
    protected Connection conn;
    protected Statement st;
    protected ResultSet rs;
    protected String uName;
    protected String appName;
    public void setUname(String newUname) {
	this.uName = newUname;
    }
    protected void setAppName(String newAppName) {
	this.appName = newAppName;
    }
}
