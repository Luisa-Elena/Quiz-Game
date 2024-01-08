package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.QuizCategory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class UserAfterSignin extends SceneController {
    @FXML
    private Button getCategoriesButton;
    @FXML
    private VBox vbox;
    @FXML
    private Label infoLabel;
    private List<String> categories = new ArrayList<>();
    public void onGetCategoriesClick() {
        getCategoriesButton.setVisible(false);
        infoLabel.setText("Choose one of the following quiz categories:");

        DBFunctions db = new DBFunctions();
        categories = db.getQuizCategories();

        for (String category : categories) {
            Button button = new Button(category);
            button.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 17px;");
            VBox.setMargin(button, new Insets(2, 5, 2, 5));
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