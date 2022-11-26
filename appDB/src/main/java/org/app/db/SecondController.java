package org.app.db;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SecondController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    public TextField birthText;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea textDB;

    @FXML
    private TextField genderText;

    @FXML
    private ScrollBar scroll;

    @FXML
    private Button clearButton;

    @FXML
    private Button updateButton;

    @FXML
    private TextField idText;

    @FXML
    private Button changeButton;

    @FXML
    private TextField emailText;

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void change(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void initialize() {
        //update
        updateButton.setOnAction(event -> {
            CRUDutils cruDutils = new CRUDutils();
            List<Employee> employees = new ArrayList<>();
            String query = "SELECT * FROM employee;";
            String query1 = "";
            employees.addAll(cruDutils.getEmployeeData(query));
            for (Employee s : employees) {
                query1 = s.getId() + " " + s.getFirst_name() + " " + s.getLast_name() + " " +
                        s.getGender() + " " + s.getEmail() + " " + s.getDate_of_birth();
                textDB.appendText(query1 + "\n");
            }
        });
        //clear
        clearButton.setOnAction(event -> {
            textDB.clear();
            firstName.setBackground(Background.fill(Color.WHITE));
            lastName.setBackground(Background.fill(Color.WHITE));
            genderText.setBackground(Background.fill(Color.WHITE));
            emailText.setBackground(Background.fill(Color.WHITE));
            idText.setBackground(Background.fill(Color.WHITE));
            //  birthText.setBackground(Background.fill(Color.WHITE));
        });
        // change
        changeButton.setOnAction(event -> {
            if (idText.getText().equals("") && firstName.getText().equals("") &&
                    lastName.getText().equals("") && genderText.getText().equals("") &&
                    emailText.getText().equals("") && birthText.getText().equals("")) {
                System.out.println("nuull");
                firstName.setBackground(Background.fill(Color.YELLOW));
                lastName.setBackground(Background.fill(Color.YELLOW));
                genderText.setBackground(Background.fill(Color.YELLOW));
                emailText.setBackground(Background.fill(Color.YELLOW));
                birthText.setBackground(Background.fill(Color.YELLOW));

            } else {
                int idNumber = Integer.parseInt(idText.getText().trim());
                String firstname = firstName.getText().trim();
                String surname = lastName.getText().trim();
                String gender = genderText.getText().trim();
                String email = emailText.getText().trim();
                String date = birthText.getText().trim();
                CRUDutils cruDutils = new CRUDutils();
                cruDutils.updateEmployee(idNumber, firstname, surname, gender, email, date);
                System.out.println("good");
            }
        });
        // delete
        deleteButton.setOnAction(event -> {
            if (idText.getText().equals("")) {
                System.out.println("nuull");
                idText.setBackground(Background.fill(Color.YELLOW));
            } else {
                int idNumb = Integer.parseInt(idText.getText().trim());
                CRUDutils cruDutils = new CRUDutils();
                cruDutils.deleteEmployee(idNumb);
                System.out.println("good");
            }
        });
    }
}
