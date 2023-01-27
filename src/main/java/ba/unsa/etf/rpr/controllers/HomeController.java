package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.ClothesManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


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
    public ListView categoryListMenu;
    private ClothesManager manager = new ClothesManager();
    private CategoryManager categoryManager = new CategoryManager();
    @FXML
    public ListView nameList;

    private Map<String, Image> clothingImage;

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
        nameList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedItem = (String) nameList.getSelectionModel().getSelectedItem();
                Image image = clothingImage.get(selectedItem);
                imageView.setImage(image);
            }
        });*/
        categoryListMenu.setItems(FXCollections.observableList(categoryManager.getAll()));
        categoryListMenu.getSelectionModel().selectedItemProperty().addListener((obs,oldItem, newItem) -> {
           Clothes oldClothes = (Clothes) oldItem;
           Clothes newClothes = (Clothes) newItem;
            if (oldItem != null) {
               nameList.itemsProperty().unbindBidirectional(oldClothes.clothes_nameProperty());
               categoryList.itemsProperty().unbindBidirectional(oldClothes.getIdcategory().nameProperty());
               sizeList.itemsProperty().unbindBidirectional(oldClothes.sizeProperty());
               priceList.itemsProperty().unbindBidirectional(oldClothes.priceProperty());
           }
            if(newItem != null) {
                nameList.itemsProperty().bindBidirectional(newClothes.clothes_nameProperty());
                categoryList.itemsProperty().bindBidirectional(newClothes.getIdcategory().nameProperty());
                sizeList.itemsProperty().bindBidirectional(newClothes.sizeProperty());
                priceList.itemsProperty().bindBidirectional(newClothes.priceProperty());
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

}
