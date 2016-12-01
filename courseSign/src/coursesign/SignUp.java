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
public class SignUp {
    private SimpleStringProperty name;
    private SimpleStringProperty type;
    public SignUp(String name,String type) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
    }
    public String getName() {
        return name.get();
    }
    public String getType() {
        return type.get();
    }
    public void setName(String newName) {
        name.set(newName);
    }
    public void setType(String newType) {
        type.set(newType);
    }
}
