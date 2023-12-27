package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.ApplicationHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

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