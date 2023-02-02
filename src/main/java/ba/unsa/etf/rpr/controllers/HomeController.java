package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.ClothesManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;

public class HomeController {
    @FXML
    public ImageView imageView;
    @FXML
    public TextField searchClothes;
    @FXML
    public Button searchButton;
    @FXML
    public ChoiceBox categoryFilter;
    @FXML
    public TableColumn<Clothes,String> nameColumn;
    @FXML
    public TableColumn<Clothes,String> categoryColumn;
    @FXML
    public TableColumn<Clothes,Integer> sizeColumn;
    @FXML
    public TableColumn<Clothes,Integer> priceColumn;
    @FXML
    public TableView tableView;
    private ClothesManager manager = new ClothesManager();
    private CategoryManager categoryManager = new CategoryManager();

    @FXML
    public void initialize() throws ShopException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Clothes,String>("clothes_name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Clothes,String>("idcategory"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Clothes,Integer>("size"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Clothes,Integer>("price"));
        ObservableList<Clothes> items = FXCollections.observableList(manager.getAll());
        tableView.setItems(items);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            Clothes c = (Clothes) newValue;
            // update the image view here
            Image newImage = new Image(getClass().getResource("/img/" + c.getClothes_name() + ".jpg").toString());
            imageView.setImage(newImage);
        });


    }

    public void searchClothes(ActionEvent event) throws ShopException {
        ObservableList<Clothes> items = FXCollections.observableList(manager.searchClothes(searchClothes.getText()));
        tableView.setItems(items);
    }
}
