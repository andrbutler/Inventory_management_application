/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482.Model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andrb
 */
public class OutsourcedPart extends Part{
    private SimpleStringProperty companyName;

    public OutsourcedPart(String companyName, int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        this.companyName = new SimpleStringProperty(companyName);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName = new SimpleStringProperty(companyName);
    }
    
}
