/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482.Model;

import andrewbutlerinvetorybuilderc482.Model.Part;

/**
 *
 * @author andrb
 */
public class InHousePart extends Part{
    private int machineID;

    public InHousePart(int machineID, int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
