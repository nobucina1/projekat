package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.MainFX;
import ba.unsa.etf.rpr.business.ClothesManager;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HomeController {
    public ListView categoryList;
    public ListView sizeList;
    public ListView priceList;
    private ClothesManager manager = new ClothesManager();
    public ListView nameList;

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
    }

}
