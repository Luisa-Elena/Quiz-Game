package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.DBFunctions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController extends SceneController implements Initializable {

    @FXML
    private Text signUpInfo;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> roleField;

    private String[] rolesArray = {"admin", "user"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleField.getItems().addAll(rolesArray);
    }

    @FXML
    protected void onGoBackButtonClick() {
        changeScene(SCENE_IDENTIFIER.WELCOME);
    }

    @FXML
    protected void onSubmitButtonClick() {
        DBFunctions db = new DBFunctions();
        if(nameField.getText()!="" && passwordField.getText()!="" && roleField.getValue()!=null) {
            db.insertNewUser(nameField.getText(), passwordField.getText(), roleField.getValue());
        } else {
            signUpInfo.setText("All fields are required! - Please make sure you enter all data");
        }
    }
}
