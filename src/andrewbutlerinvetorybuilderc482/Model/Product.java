/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482.Model;
import java.util.List;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
/**
 *
 * @author andrb
 */
public class Product {
    //private ObservableList<Part> associatedParts = FXCollections.observableArrayList(new ArrayList<Part>());
    private ArrayList<Part> associatedParts = new ArrayList<Part>();
    private int id;
    private SimpleStringProperty name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part associatedPart){
        this.associatedParts.remove(associatedPart);
    }

    public ArrayList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
    
    
}
