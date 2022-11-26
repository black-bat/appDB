package org.app.db;

import java.io.*;
import java.net.URL;
import java.security.KeyStore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartController {
    Stage stage;
    Map<String, String> userStart = new HashMap<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text notification;

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button enterButton;

    @FXML
    private Button registrationButton;

    @FXML
    void enter(ActionEvent event) throws Exception {

    }

    @FXML
    void registration(ActionEvent event1) throws Exception {

        registrationButton.setOnAction(event -> {
            registrationButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("thirdWindow.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch ( IOException e ) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("paninskii");
            stage.show();
        });
        notification.setOpacity(0);
        passwordText.setBackground(Background.fill(Color.WHITE));
        loginText.setBackground(Background.fill(Color.WHITE));
    }

    @FXML
    void initialize() {
        enterButton.setOnAction(event -> {
            if (loginText.getText().equals("") && passwordText.getText().equals("")) {
                System.out.println("nuull");
                notification.setOpacity(1);
                notification.setText("empty fields");
                passwordText.setBackground(Background.fill(Color.YELLOW));
                loginText.setBackground(Background.fill(Color.YELLOW));
            } else {
                String login = loginText.getText().trim();
                String password = passwordText.getText().trim();
                if (!(login.equals("") && !(password.equals("")))) {
                    try {
                        loginUser(login, password);
                    } catch ( SQLException e ) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void loginUser(String logintText, String passworDText) throws SQLException {

        CRUDutils cruDutils = new CRUDutils();
        Employee employee = new Employee();
        employee.setFirst_name(logintText);
        employee.setLast_name(passworDText);
        ResultSet result = cruDutils.getEmployee(employee);
        int counter = 0;
        while (result.next()) counter++;
        if (counter >= 1) {
            enterButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondWindow.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch ( IOException e ) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("paninskii");
            stage.show();
        } else {
            notification.setOpacity(1);
            notification.setText("incorrect data");
            passwordText.setBackground(Background.fill(Color.RED));
            loginText.setBackground(Background.fill(Color.RED));
        }
    }
}
