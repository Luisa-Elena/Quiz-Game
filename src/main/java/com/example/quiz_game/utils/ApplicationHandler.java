package com.example.quiz_game.utils;

import com.example.quiz_game.HelloApplication;
import com.example.quiz_game.controllers.QuizzesViewController;
import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ApplicationHandler {
    private final HashMap<SCENE_IDENTIFIER, Pane> views = new HashMap<>();

    private Stage stage;

    private ApplicationHandler() {}

    private void initializeViews() {
        try {
            for (SCENE_IDENTIFIER value : SCENE_IDENTIFIER.values()) {
                this.views.put(value, FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(value.label))));
            }
        } catch (IOException | NullPointerException exception) {
            System.out.println("error - cannot initialize views");
        }
    }

    public void startApplication(Stage stage){
        this.initializeViews();

        this.stage = stage;
        this.stage.setTitle("Quiz Game");
        this.stage.setScene(new Scene(this.views.get(SCENE_IDENTIFIER.WELCOME), 600, 600));
        this.stage.show();
    }

    public void closeApplication(){
        Platform.exit();
        System.exit(0);
    }

    public void changeScene(SCENE_IDENTIFIER newScene) {
        this.stage.getScene().setRoot(views.get(newScene));
    }

    public static ApplicationHandler _instance = null;

    public static ApplicationHandler getInstance() {
        if(ApplicationHandler._instance == null){
            ApplicationHandler._instance = new ApplicationHandler();
        }

        return ApplicationHandler._instance;
    }
}
