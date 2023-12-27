package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.ApplicationHandler;
import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.Environment;
import com.example.quiz_game.utils.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.util.Objects;

public class SignInController extends SceneController{

    @FXML
    private Text signInInfo;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    public void onGoBackButtonClick() {
        changeScene(SCENE_IDENTIFIER.WELCOME);
    }

    public void onCloseAppClick() { closeApplication(); }

    public void onSignInButtonClick() {
        if(nameField.getText()!="" && passwordField.getText()!="") {
            if (validateSignIn(nameField.getText(), passwordField.getText())) {
                //signInInfo.setText("Sign in successful!");
                //System.out.println(UserData.getInstance().getId());
                //System.out.println(UserData.getInstance().getName());
                //System.out.println(UserData.getInstance().getRole());
                if(Objects.equals(UserData.getInstance().getRole(), "user")) {
                    changeScene(SCENE_IDENTIFIER.USER);
                } else {
                    changeScene(SCENE_IDENTIFIER.ADMIN);
                }
            } else {
                signInInfo.setText("Invalid username or password");
            }
        } else {
            signInInfo.setText("All fields are required! - Please provide username and password");
        }
    }

    private boolean validateSignIn(String username, String password) {
        DBFunctions db = new DBFunctions();
        Connection conn = db.connectToDB(Environment.DBNAME, Environment.DBUSER, Environment.DBPASSWORD);

        return db.fetchUser(conn, "users", nameField.getText(), passwordField.getText());
    }
}
