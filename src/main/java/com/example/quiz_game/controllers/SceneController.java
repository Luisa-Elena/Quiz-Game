package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.ApplicationHandler;

public class SceneController {
    public void changeScene(SCENE_IDENTIFIER newScene){
        ApplicationHandler.getInstance().changeScene(newScene);
    }

    public void closeApplication(){
        ApplicationHandler.getInstance().closeApplication();
    }
}