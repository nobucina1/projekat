package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.ClothesManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddController {
    @FXML
    public TextField nameText;
    @FXML
    public TextField categoryText;
    @FXML
    public TextField sizeText;
    @FXML
    public TextField priceText;
    public Button addButton;
    private Parent root;
    private Stage stage;
    private Scene scene;

    ClothesManager clothesManager = new ClothesManager();
    CategoryManager categoryManager = new CategoryManager();

    public void onAdd(ActionEvent event) throws ShopException {
        Window owner = addButton.getScene().getWindow();

        Clothes c = new Clothes();
        Category cat = new Category();
        boolean exists = false;
        cat.setName(categoryText.getText());
        List<Category> categoryList = new ArrayList<>(categoryManager.getAll());
        for (Category tmp:categoryList)
            if (tmp.getName().equals(cat.getName())) {
                exists = true;
                cat = tmp;
                break;
            }
        if (!exists) {
            cat = categoryManager.add(cat);
        }
        c.setClothes_name(nameText.getText());
        c.setIdcategory(cat);
        c.setSize(Integer.parseInt(sizeText.getText()));
        c.setPrice(Integer.parseInt(priceText.getText()));
        clothesManager.add(c);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Successfuly added!",
                "Congratulations :)");
    }
    public void onCancel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
