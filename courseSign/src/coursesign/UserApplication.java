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
public class UserApplication {
    private SimpleStringProperty date;
    private SimpleStringProperty name;
    private SimpleStringProperty status;
    public UserApplication(String date,String name, String status) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.status = new SimpleStringProperty(status);
    }
    public String getName() {
        return name.get();
    }
    public String getStatus() {
        return status.get();
    }
    public String getDate() {
        return date.get();
    }
    public void setName(String newName) {
        name.set(newName);
    }
    public void setStatus(String newStatus) {
        status.set(newStatus);
    }
    public void setDate(String newDate) {
        date.set(newDate);
    }
}
