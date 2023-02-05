package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Clothes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class OrderController {

    @FXML
    public Button submitButton;
    @FXML
    public Label price;
    @FXML
    public Button cancel;
    @FXML
    public ListView<String> listView;

    private ObservableList<Clothes> selectedClothes = FXCollections.observableArrayList();
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {
        double finalPrice = 0;
        for(Clothes c: this.selectedClothes) {
            finalPrice += c.getPrice();
        }
        price.setText(String.format("%.2f", finalPrice));
    }

    public void onOrder(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Order successful!",
                "Clothes are on their way :)");
    }

    public void onCancel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setSelectedItems(ObservableList<Clothes> selectedClothes) {
        this.selectedClothes = selectedClothes;
        ObservableList<String> name = FXCollections.observableArrayList();
        for (Clothes c:selectedClothes)
            name.add(c.getClothes_name());
        listView.setItems(name);
        double finalPrice = 0;
        for(Clothes c: this.selectedClothes) {
            finalPrice += c.getPrice();
        }
        price.setText(String.format("%.2f", finalPrice));
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
