package org.app.db;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.stage.Stage;

public class ThirdController {

    Stage stage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dateText;

    @FXML
    private Text notification;

    @FXML
    private TextField surnameText;

    @FXML
    private Button backButton;

    @FXML
    private TextField genderText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField nameText;

    @FXML
    private Button sendButton;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void sendInformation(ActionEvent event) {

    }


    @FXML
    void initialize() {

        //backButton
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startWindow.fxml"));
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

        //sendButton
        sendButton.setOnAction(event -> {
            if (nameText.getText().equals("") && surnameText.getText().equals("") &&
                    genderText.getText().equals("") && emailText.getText().equals("") &&
                    dateText.getText().equals("")) {
                System.out.println("nuull");
                notification.setOpacity(1);
                notification.setText("empty fields");
                nameText.setBackground(Background.fill(Color.YELLOW));
                surnameText.setBackground(Background.fill(Color.YELLOW));
                genderText.setBackground(Background.fill(Color.YELLOW));
                emailText.setBackground(Background.fill(Color.YELLOW));
                dateText.setBackground(Background.fill(Color.YELLOW));
            } else {
                if (nameText.getText().equals("") && surnameText.getText().equals("") &&
                        genderText.getText().equals("") && emailText.getText().equals("") &&
                        dateText.getText().equals("")) {
                    System.out.println("nuull");
                    notification.setOpacity(1);
                    notification.setText("fill in the fields");
                    nameText.setBackground(Background.fill(Color.YELLOW));
                    surnameText.setBackground(Background.fill(Color.YELLOW));
                    genderText.setBackground(Background.fill(Color.YELLOW));
                    emailText.setBackground(Background.fill(Color.YELLOW));
                    dateText.setBackground(Background.fill(Color.YELLOW));
                } else {
                    String firstName = nameText.getText().trim();
                    String surname = surnameText.getText().trim();
                    String gender = genderText.getText().trim();
                    String email = emailText.getText().trim();
                    String date = dateText.getText().trim();
                    if (!(firstName.equals("") && !(surname.equals("")) &&
                            !(gender.equals("") && !(email.equals("") &&
                                    !(date.equals("")))))) {
                        try {
                            sendUser(firstName, surname, gender, email, date);
                        } catch ( SQLException e ) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }

    private void sendUser(String name, String surname, String gender, String email, String date) throws SQLException {

        CRUDutils cruDutils = new CRUDutils();
        Employee employee = new Employee();
        employee.setFirst_name(name);
        employee.setLast_name(surname);
        employee.setGender(gender);
        employee.setEmail(email);
        employee.setDate_of_birth(date);
        cruDutils.saveEmployeeData(employee);

    }
}

