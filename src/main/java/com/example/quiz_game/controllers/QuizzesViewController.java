package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.QuizData;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class QuizzesViewController extends SceneController {
    @FXML
    private Button showQuizzesButton;
    @FXML
    private Label infoLabel;
    @FXML
    private VBox vbox;
    private List<String> quizzes = new ArrayList<>();

    private DBFunctions db = new DBFunctions();

    public void onGetQuizzesClick() {
        showQuizzesButton.setVisible(false);
        infoLabel.setText("Choose one of the following quizzes:");

        quizzes = db.getQuizzes();

        for (String quiz : quizzes) {
            Button button = new Button(quiz);
            button.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 17px;");
            VBox.setMargin(button, new Insets(2, 5, 2, 5));
            button.setOnAction(event -> handleQuizButtonClick(quiz));
            vbox.getChildren().add(button);
        }
    }
    public void handleQuizButtonClick(String quizName) {
        QuizData quizData = QuizData.getInstance();
        quizData.setQuizName(quizName);

        db.setQuizId(quizName); //get the quiz id and set it in the QuizData instance

        //System.out.println(quizData.getQuizId());
        changeScene(SCENE_IDENTIFIER.QUIZ);
    }
}