package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.ClothesManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.util.*;
import java.util.stream.Collectors;
import java.util.Collection;


public class HomeController {
    @FXML
    public ListView categoryList;
    @FXML
    public ListView sizeList;
    @FXML
    public ListView priceList;
    @FXML
    public ImageView imageView;
    @FXML
    public TextField searchClothes;
    @FXML
    public Button searchButton;
    @FXML
    public ChoiceBox categoryFilter;
    private ClothesManager manager = new ClothesManager();
    private CategoryManager categoryManager = new CategoryManager();
    @FXML
    public ListView nameList;

    private Map<String, Image> clothingImage;

    private ClothesModel clothesModel = new ClothesModel();

    @FXML
    public void initialize() throws ShopException {


        ObservableList<String> name;
        ObservableList<String> cat;
        ObservableList<Integer> size;
        ObservableList<Integer> price;
        try {
            ObservableList<Clothes> items = FXCollections.observableList(manager.getAll());
            name = FXCollections.observableArrayList();
            cat = FXCollections.observableArrayList();
            size = FXCollections.observableArrayList();
            price = FXCollections.observableArrayList();
            for (Clothes c : items) {
                name.add(c.getClothes_name());
                cat.add(c.getIdcategory().getName());
                size.add(c.getSize());
                price.add(c.getPrice());
            }
        } catch (ShopException e) {
            throw new RuntimeException(e);
        }
        nameList.setItems(name);
        categoryList.setItems(cat);
        sizeList.setItems(size);
        priceList.setItems(price);

        /*clothingImage = new HashMap<>();
        clothingImage.put("papuce", new Image(getClass().getResource("/img/papuce.jpg").toString()));

        imageView.imageProperty().bind(Bindings.createObjectBinding(() -> {
            String selectedItem = (String) nameList.getSelectionModel().getSelectedItem();
            return clothingImage.get(selectedItem);
        }, nameList.getSelectionModel().selectedItemProperty()));*/

        nameList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            // update the image view here
            Image newImage = new Image(getClass().getResource("/img/" + newValue + ".jpg").toString());
            imageView.setImage(newImage);
        });

        categoryFilter.setItems(FXCollections.observableList(categoryManager.getAll()));
        categoryFilter.getSelectionModel().selectedItemProperty().addListener((obs,oldClothes, newClothes) -> {
            if (oldClothes != null) {
               nameList.itemsProperty().unbindBidirectional(clothesModel.name);
               categoryList.itemsProperty().unbindBidirectional(clothesModel.category);
               sizeList.itemsProperty().unbindBidirectional(clothesModel.size);
               priceList.itemsProperty().unbindBidirectional(clothesModel.price);
           }
            if(newClothes != null) {
                ObservableList<Clothes> clothes = null;
                try {
                    clothes = FXCollections.observableArrayList(manager.searchByCategory((Category) newClothes));
                    name.clear();cat.clear();size.clear();price.clear();
                    for (Clothes c : clothes) {
                        name.add(c.getClothes_name());
                        cat.add(c.getIdcategory().getName());
                        size.add(c.getSize());
                        price.add(c.getPrice());
                    }
                } catch (ShopException e) {
                    throw new RuntimeException(e);
                }

                nameList.setItems(name);
                categoryList.setItems(cat);
                sizeList.setItems(size);
                priceList.setItems(price);

            }
            else {
                nameList.setItems(null);
                categoryList.setItems(null);
                sizeList.setItems(null);
                priceList.setItems(null);
            }
        });
    }

    public void searchClothes(ActionEvent event) {
        ObservableList<String> name;
        ObservableList<String> cat;
        ObservableList<Integer> size;
        ObservableList<Integer> price;
        try {
            ObservableList<Clothes> items = FXCollections.observableList(manager.searchClothes(searchClothes.getText()));
            nameList.getItems().clear();
            categoryList.getItems().clear();
            sizeList.getItems().clear();
            priceList.getItems().clear();
            name = FXCollections.observableArrayList();
            cat = FXCollections.observableArrayList();
            size = FXCollections.observableArrayList();
            price = FXCollections.observableArrayList();
            for (Clothes c : items) {
                name.add(c.getClothes_name());
                cat.add(c.getIdcategory().getName());
                size.add(c.getSize());
                price.add(c.getPrice());
            }
        } catch (ShopException e) {
            throw new RuntimeException(e);
        }
        nameList.setItems(name);
        categoryList.setItems(cat);
        sizeList.setItems(size);
        priceList.setItems(price);
    }

    public class ClothesModel {
        /*private SimpleStringProperty n = new SimpleStringProperty();
        private SimpleStringProperty c = new SimpleStringProperty();
        private SimpleIntegerProperty s = new SimpleIntegerProperty();
        private SimpleIntegerProperty p = new SimpleIntegerProperty();*/

        public SimpleStringProperty name = new SimpleStringProperty();
        public SimpleStringProperty category = new SimpleStringProperty();
        public SimpleIntegerProperty size = new SimpleIntegerProperty();
        public SimpleIntegerProperty price = new SimpleIntegerProperty();


        public ClothesModel() {}

        public void fromClothes(Clothes c) {
            this.name= new SimpleStringProperty(c.getClothes_name());
            this.category = new SimpleStringProperty(c.getIdcategory().getName());
            this.size = new SimpleIntegerProperty(c.getSize());
            this.price = new SimpleIntegerProperty(c.getPrice());
        }
    }

}
