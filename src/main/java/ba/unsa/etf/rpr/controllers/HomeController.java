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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX controller for Home screen
 *
 * @author Nermin Obucina
 */
public class HomeController {
    private Parent root;
    private Stage stage;
    private Scene scene;

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
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //setting items in tableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<Clothes,String>("clothes_name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Clothes,String>("idcategory"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Clothes,Integer>("size"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Clothes,Integer>("price"));
        ObservableList<Clothes> items = FXCollections.observableList(manager.getAll());
        tableView.setItems(items);
        imageView.setImage(new Image("/img/logo.jpg"));

        //update the imageView by clicking the clothes
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            Clothes c = (Clothes) newValue;
            if(c != null) {
                String imgName = c.getClothes_name();
                File file = new File("C:\\Users\\nermi\\projekat\\src\\main\\resources\\img\\" + imgName + ".jpg");
                if (file.exists() && !file.isDirectory()) {
                    Image newImage = new Image(getClass().getResource("/img/" + c.getClothes_name() + ".jpg").toString());
                    imageView.setImage(newImage);
                }
                else {
                    imageView.setImage(new Image("/img/noimage.jpg"));
                }
            }
            else {
                tableView.getSelectionModel().clearSelection();
            }
        });

        //updating tableView by choosing a certain category
        Category anyCategory = new Category();
        anyCategory.setName("Any");
        ObservableList<Category> categories = FXCollections.observableList(categoryManager.getAll());
        categories.add(0, anyCategory);
        categoryFilter.setItems(categories);
        categoryFilter.valueProperty().addListener((obs, oldClothes, newClothes) -> {
            if (newClothes != null) {
                if (newClothes.equals(anyCategory)) {
                    try {
                        tableView.setItems(FXCollections.observableArrayList(manager.getAll()));
                    } catch (ShopException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        ObservableList<Clothes> filteredClothingData = FXCollections.observableArrayList(manager.searchByCategory((Category) newClothes));
                        tableView.setItems(filteredClothingData);
                    } catch (ShopException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void searchClothes(ActionEvent event) throws ShopException {
        ObservableList<Clothes> items = FXCollections.observableList(manager.searchClothes(searchClothes.getText()));
        tableView.setItems(items);
    }

    @FXML
    public void deleteClothes(ActionEvent event) throws ShopException {
            Clothes selectedClothes = (Clothes) tableView.getSelectionModel().getSelectedItem();
            if (selectedClothes != null) {
                manager.delete(selectedClothes.getId());
                tableView.getItems().remove(selectedClothes);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No clothing selected");
                alert.setContentText("Please select a clothing to delete.");
                alert.showAndWait();
            }
    }

    @FXML
    public void switchToAdd(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/add.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToOrder(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/order.fxml"));
        root = fxmlLoader.load();
        OrderController orderController = fxmlLoader.getController();
        ObservableList selectedClothes = tableView.getSelectionModel().getSelectedItems();
        orderController.setSelectedItems(selectedClothes);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
