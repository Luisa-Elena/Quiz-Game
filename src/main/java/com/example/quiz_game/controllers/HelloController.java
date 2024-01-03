package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;

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