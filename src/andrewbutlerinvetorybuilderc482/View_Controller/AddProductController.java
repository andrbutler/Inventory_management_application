/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482.View_Controller;

import static andrewbutlerinvetorybuilderc482.AndrewButlerInvetoryBuilderC482.inventory;
import andrewbutlerinvetorybuilderc482.Model.Part;
import andrewbutlerinvetorybuilderc482.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andrb
 */
public class AddProductController implements Initializable {

    @FXML
    private AnchorPane addProductView;
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
    private Button deleteButtonHandler;
    @FXML
    private Button saveButtonHandler;
    @FXML
    private Button cancelButtonHandler;
    @FXML
    private Button searchButtonHandler;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private Button addButtonHandler;
    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;
    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> associatedPartsInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;

    private Product product = new Product(0, "Product Name", 0, 0, 0, 0);

    public void saveButtonPressed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
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
        product.setId(inventory.getProductsAdded() + 1);
        product.setName(nameField.getText());
        product.setMax(Integer.parseInt(maxField.getText()));
        product.setMin(Integer.parseInt(minField.getText()));
        product.setStock(Integer.parseInt(inventoryField.getText()));
        product.setPrice(Double.parseDouble(maxField.getText()));
        inventory.addProduct(product);

        Parent inventoryParent = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
        Scene inventoryScene = new Scene(inventoryParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(inventoryScene);
        window.show();
    }

    public void partSearchButtonPressed() {
        String searchTerm = searchField.getText();
        if (searchTerm.equals("")) {
            partTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
        } else {
            partTable.setItems(FXCollections.observableArrayList(
                    inventory.lookupPart(searchTerm)));
            searchField.clear();
        }
    }

    public void addPartButtonPressed(ActionEvent event) throws IOException {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        product.addAssociatedPart(selectedPart);
        associatedPartsTable.setItems(FXCollections.observableArrayList(product.getAllAssociatedParts()));
    }

    public void deletePartButtonPressed(ActionEvent event) throws IOException {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        String deleteMessage = "are you sure you want to dissociate Part:\n"
                + "Id#: " + selectedPart.getId() + " Name: " + selectedPart.getName();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, deleteMessage, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            product.deleteAssociatedPart(selectedPart);
            associatedPartsTable.setItems(FXCollections.observableArrayList(product.getAllAssociatedParts()));
        }
    }

    public void cancelButtonPressed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Parent inventoryParent = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
            Scene inventoryScene = new Scene(inventoryParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(inventoryScene);
            window.show();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.idField.setText("Auto Generated");
        this.nameField.setText("Produt Name");
        this.inventoryField.setText("Inventory");
        this.priceField.setText("Price/Cost");
        this.maxField.setText("Max");
        this.minField.setText("Min");
        this.idField.setEditable(false);

        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
        partPriceColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", price));
                }
            }
        });

        associatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        associatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedPartsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        associatedPartsTable.setItems(FXCollections.observableArrayList(product.getAllAssociatedParts()));
        associatedPartsPriceColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", price));
                }
            }
        });
    }

}
