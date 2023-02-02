package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.JdbcDao;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
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
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button registerButton;

    @FXML
    private Button submitButton;
    public void switchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws SQLException, IOException {
        Window owner = submitButton.getScene().getWindow();

        System.out.println(mailField.getText());
        System.out.println(passwordField.getText());

        if (mailField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        String mail = mailField.getText();
        String password = passwordField.getText();

        UserDaoSQLImpl userDaoSQL = new UserDaoSQLImpl();
        boolean validation = userDaoSQL.validate(mail, password);

        if (validation) {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!",
                    "Welcome :)");
            root = FXMLLoader.load(getClass().getResource("/home.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            showAlert(Alert.AlertType.ERROR,owner,"Login Unsuccessful!","Email or password incorrect, if you don't have an account please register.");
        }
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
