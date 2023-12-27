package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.ApplicationHandler;
import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.Environment;
import com.example.quiz_game.utils.QuizCategory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserAfterSignin extends SceneController {
    @FXML
    private Button getCategoriesButton;

    @FXML
    private VBox vbox;
    private List<String> categories = new ArrayList<>();
    public void onGetCategoriesClick() {
        getCategoriesButton.setVisible(false);

        DBFunctions db = new DBFunctions();
        Connection conn = db.connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
        categories = db.getQuizCategories(conn);

        for (String category : categories) {
            Button button = new Button(category);
            button.setOnAction(event -> handleCategoryButtonClick(category));
            vbox.getChildren().add(button);
        }
    }

    private void handleCategoryButtonClick(String category) {
        QuizCategory quizCategory = QuizCategory.getInstance();
        quizCategory.setCategory(category);
        changeScene(SCENE_IDENTIFIER.QUIZZES);
    }
}
