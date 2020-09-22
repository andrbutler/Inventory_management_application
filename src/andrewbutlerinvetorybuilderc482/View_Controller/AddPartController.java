/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482.View_Controller;

import static andrewbutlerinvetorybuilderc482.AndrewButlerInvetoryBuilderC482.inventory;
import andrewbutlerinvetorybuilderc482.Model.InHousePart;
import andrewbutlerinvetorybuilderc482.Model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andrb
 */
public class AddPartController implements Initializable {

    @FXML
    RadioButton inHouseRadioButton;
    @FXML
    RadioButton outsourcedRadioButton;
    @FXML
    private Label machineOrCompanyLabel;

    @FXML
    private AnchorPane AddPartView;
    @FXML
    private Button saveButtonHandler;
    @FXML
    private Button cancelButtonHandler;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField inventoryField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TextField uniqueIdentifierField;

    private ToggleGroup outsourcedPartToggleGroup;
    private boolean isOutsourcedPart;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void radioButtonChanged() {
        if (this.outsourcedPartToggleGroup.getSelectedToggle().equals(this.outsourcedRadioButton)) {
            machineOrCompanyLabel.setText("Company Name");
            this.uniqueIdentifierField.setText("Company Name");
            this.isOutsourcedPart = true;
        } else if (this.outsourcedPartToggleGroup.getSelectedToggle().equals(this.inHouseRadioButton)) {
            machineOrCompanyLabel.setText("Machine ID");
            this.uniqueIdentifierField.setText("Machine ID");
            this.isOutsourcedPart = false;
        }
    }

    public void cancelButtonPressed(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Parent inventoryParent = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
            Scene inventoryScene = new Scene(inventoryParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(inventoryScene);
            window.show();
        }
    }

    public void saveButtonPressed(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.ERROR);
        if (Integer.parseInt(minField.getText()) > Integer.parseInt(maxField.getText())) {
            alert.setTitle("Error");
            alert.setHeaderText("Min Greater than Max");
            alert.setContentText("error: maximum value must be greater than minimum value.");
            alert.showAndWait();
            maxField.setText("Max");
        }
        if ((Integer.parseInt(inventoryField.getText()) > Integer.parseInt(maxField.getText()))
                || (Integer.parseInt(inventoryField.getText()) < Integer.parseInt(minField.getText()))) {
            alert.setTitle("Error");
            alert.setHeaderText("Inventory level out of bounds");
            alert.setContentText("Error: Inventory level must be greater than minimum value, and less than maximum.");
            alert.showAndWait();
            inventoryField.setText("Inv");
        }
        if (!(inventoryField.getText().equalsIgnoreCase("inv"))
                && !(maxField.getText().equalsIgnoreCase("max"))
                && !(minField.getText().equalsIgnoreCase("min"))) {
            if (isOutsourcedPart) {
                inventory.addPart(new OutsourcedPart(uniqueIdentifierField.getText(),
                        inventory.getPartsAdded() + 1, nameField.getText(), Double.parseDouble(priceField.getText()),
                        Integer.parseInt(inventoryField.getText()),
                        Integer.parseInt(minField.getText()),
                        Integer.parseInt(maxField.getText())));
            } else {
                inventory.addPart(new InHousePart(Integer.parseInt(uniqueIdentifierField.getText()),
                        inventory.getPartsAdded() + 1, nameField.getText(), Double.parseDouble(priceField.getText()),
                        Integer.parseInt(inventoryField.getText()),
                        Integer.parseInt(minField.getText()),
                        Integer.parseInt(maxField.getText())));
            }
            Parent inventoryParent = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
            Scene inventoryScene = new Scene(inventoryParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(inventoryScene);
            window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outsourcedPartToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(outsourcedPartToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(outsourcedPartToggleGroup);
        this.isOutsourcedPart = false;
        this.inHouseRadioButton.setSelected(true);
        this.machineOrCompanyLabel.setText("Machine ID");
        this.idField.setText("Auto Generated");
        this.nameField.setText("Part Name");
        this.inventoryField.setText("Inventory");
        this.priceField.setText("Price/Cost");
        this.maxField.setText("Max");
        this.minField.setText("Min");
        this.uniqueIdentifierField.setText("Machine ID");
        this.idField.setEditable(false);
    }

}
