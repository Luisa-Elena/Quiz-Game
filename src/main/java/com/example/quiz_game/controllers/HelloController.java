package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController extends SceneController {
    @FXML
    protected  void onSignUpButtonClick() {
        changeScene(SCENE_IDENTIFIER.SIGNUP);
    }

    @FXML
    protected  void onSignInButtonClick() {
        changeScene(SCENE_IDENTIFIER.SIGNIN);
    }
}