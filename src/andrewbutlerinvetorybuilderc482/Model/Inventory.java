/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewbutlerinvetorybuilderc482.Model;

import andrewbutlerinvetorybuilderc482.Model.Part;
import andrewbutlerinvetorybuilderc482.Model.Product;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

/**
 *
 * @author andrb
 */
public class Inventory {

    //private ObservableList<Part> allParts = FXCollections.observableArrayList(new ArrayList<Part>());
    //private ObservableList<Product> allProducts = FXCollections.observableArrayList(new ArrayList<Product>());
    private ArrayList<Part> allParts = new ArrayList<Part>();
    private ArrayList<Product> allProducts = new ArrayList<Product>();
    private int partsAdded = 0;
    private int productsAdded = 0;

    public void addPart(Part newPart) {
        partsAdded++;
        this.allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        productsAdded++;
        this.allProducts.add(newProduct);
    }

    public void deletePart(Part selectedPart) {
        this.allParts.remove(selectedPart);
    }

    public void deleteProduct(Product selectedProduct) {
        this.allProducts.remove(selectedProduct);
    }

    public ArrayList<Part> getAllParts() {
        return allParts;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public Product getProduct(int index) {
        return allProducts.get(index);
    }

    public Part getPart(int index) {
        return allParts.get(index);
    }

    public Part lookupPart(int partId) {
        int index = -1;
        for (Part part : allParts) {
            if (partId == part.getId()) {
                index = allParts.indexOf(part);
            }
        }
        return getPart(index);
    }

    public Product lookupProduct(int productID) {
        int index = -1;
        for (Product product : allProducts) {
            if (productID == product.getId()) {
                index = allProducts.indexOf(product);
            }
        }
        return getProduct(index);
    }

    public ArrayList lookupPart(String partName) {
        ArrayList<Part> parts = new ArrayList();
        for (Part part : allParts) {
            if (partName.toLowerCase().equals(part.getName().toLowerCase())) {
                parts.add(part);
            }
        }
        return parts;
    }

    public ArrayList lookupProduct(String productName) {
        ArrayList<Product> products = new ArrayList();
        for (Product product : allProducts) {
            if (productName.toLowerCase().equals(product.getName().toLowerCase())) {
                products.add(product);
            }
        }
        return products;
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    public int getPartsAdded() {
        return partsAdded;
    }

    public int getProductsAdded() {
        return productsAdded;
    }
}
