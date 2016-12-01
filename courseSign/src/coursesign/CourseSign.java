/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseSign extends Application {
    Scene scene;
    Scene sceneReg;
    Scene curScene;
    Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        //JDBC initialization
                Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/cs4400t81";
        String user = "root";
        String password = "Yo282gNE!";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
            System.out.println("it works!");
        } catch (SQLException ex) {
        
            Logger lgr = Logger.getLogger(CourseSign.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            
            try {             
                if (rs != null) {
                    rs.close();
                }
                
                if (st != null) {
                    st.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(CourseSign.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        //retrieval test

        //everything else goes below here
        mainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("sceneLogin.fxml"));
        //Parent rootReg = FXMLLoader.load(getClass().getResource("sceneRegister.fxml"));
        scene = new Scene(root, 600, 400);
        //sceneReg = new Scene(rootReg, 600, 400);
        curScene = scene;
        mainStage.setTitle("CourseSign");
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
