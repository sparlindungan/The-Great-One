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
public class PopProjectView {
    private SimpleStringProperty name;
    private SimpleStringProperty count;
    public PopProjectView(String name,String count) {
	this.name = new SimpleStringProperty(name);
	this.count = new SimpleStringProperty(count);
    }
    public String getName() {
	return name.get();
    }
    public String getCount() {
	return count.get();
    }
    public void setName(String newName) {
	name.set(newName);
    }
    public void setCount(String newCount) {
	count.set(newCount);
    }
}
