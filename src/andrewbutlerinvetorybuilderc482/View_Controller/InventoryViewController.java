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
import javafx.application.Platform;
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
public class InventoryViewController implements Initializable {

    @FXML
    private AnchorPane inventoryView;
    @FXML
    private Button partSearchButtonHandler;
    @FXML
    private Button productSearchButtonHandler;
    @FXML
    private TextField partSearchField;
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
    private Button addPartButtonHandler;
    @FXML
    private Button modifyPartButtonHandler;
    @FXML
    private Button deletePartButtonHandler;
    @FXML
    private TextField productSearchField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    @FXML
    private Button addProductButtonHandler;
    @FXML
    private Button modifyProductButtonHandler;
    @FXML
    private Button deleteProductButtonHandler;
    @FXML
    private Button exitButtonHandler;
    public static Part selectedPartToModify;
    public static Product selectedProductToModify;

    public void addProductButtonPressed(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addProductScene);
        window.show();
    }

    public void partSearchButtonPressed() {
        String searchTerm = partSearchField.getText();
        if (searchTerm.equals("")) {
            partTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
        } else {
            partTable.setItems(FXCollections.observableArrayList(
                    inventory.lookupPart(searchTerm)));
            partSearchField.clear();
        }
    }

    public void productSearchButtonPressed() {
        String searchTerm = productSearchField.getText();
        if (searchTerm.equals("")) {
            productTable.setItems(FXCollections.observableArrayList(inventory.getAllProducts()));
        } else {
            productTable.setItems(FXCollections.observableArrayList(
                    inventory.lookupProduct(searchTerm)));
            productSearchField.clear();
        }
    }

    public void addPartButtonPressed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    public void modifyProductButtonPressed(ActionEvent event) throws IOException {
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            selectedProductToModify = productTable.getSelectionModel().getSelectedItem();
            Parent modifyProductParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
            Scene modifyProductScene = new Scene(modifyProductParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(modifyProductScene);
            window.show();
        }
    }

    public void modifyPartButtonPressed(ActionEvent event) throws IOException {

        selectedPartToModify = partTable.getSelectionModel().getSelectedItem();
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(modifyPartScene);
        window.show();

    }

    public void deletePartButtonPressed(ActionEvent event) throws IOException {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        String deleteMessage = "are you sure you want to delete Part:\n"
                + "Id#: " + selectedPart.getId() + " Name: " + selectedPart.getName();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, deleteMessage, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            inventory.deletePart(selectedPart);
            partTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
        }
    }

    public void deleteProductButtonPressed(ActionEvent event) throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        String deleteMessage = "are you sure you want to delete Product:\n"
                + "Id#: " + selectedProduct.getId() + " Name: " + selectedProduct.getName();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, deleteMessage, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            inventory.deleteProduct(selectedProduct);
            productTable.setItems(FXCollections.observableArrayList(inventory.getAllProducts()));
        }
    }

    public void exitButtonPressed(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectedPartToModify = null;
        selectedProductToModify = null;
        productIDColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        productPriceColumn.setCellFactory(tc -> new TableCell<Product, Double>() {

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
        productTable.setItems(FXCollections.observableArrayList(inventory.getAllProducts()));

        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
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
        partTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
    }

}
