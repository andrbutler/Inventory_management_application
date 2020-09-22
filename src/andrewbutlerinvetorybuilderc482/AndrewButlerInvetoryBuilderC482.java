/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482;

import andrewbutlerinvetorybuilderc482.Model.InHousePart;
import andrewbutlerinvetorybuilderc482.Model.Inventory;
import andrewbutlerinvetorybuilderc482.Model.OutsourcedPart;
import andrewbutlerinvetorybuilderc482.Model.Part;
import andrewbutlerinvetorybuilderc482.Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andrb
 */
public class AndrewButlerInvetoryBuilderC482 extends Application {
        public static Inventory inventory = new Inventory();
        
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/InventoryView.fxml"));
       
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Part samplePart1 = new InHousePart(504738, 1, "Part 1", 5.00, 200, 5, 300);
        Part samplePart2 = new OutsourcedPart("Company A", 2, "Part 2", 2.00, 20, 5, 300);
        Part samplePart3 = new InHousePart(504739, 3, "Part 3", 6.00, 100, 5, 300);
        Product sampleProduct = new Product(1, "Product 1", 10.00, 10, 5, 300);
        sampleProduct.addAssociatedPart(samplePart3);
        sampleProduct.addAssociatedPart(samplePart1);
        inventory.addPart(samplePart1);
        inventory.addPart(samplePart2);
        inventory.addPart(samplePart3);
        inventory.addProduct(sampleProduct);
        launch(args);
    }
    
}
