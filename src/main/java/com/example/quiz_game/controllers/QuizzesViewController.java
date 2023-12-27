package com.example.quiz_game.controllers;

import com.example.quiz_game.enums.SCENE_IDENTIFIER;
import com.example.quiz_game.utils.ApplicationHandler;
import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.Environment;
import com.example.quiz_game.utils.QuizData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuizzesViewController extends SceneController {
    @FXML
    private Button showQuizzesButton;

    @FXML
    private VBox vbox;
    private List<String> quizzes = new ArrayList<>();

    public void onGetQuizzesClick() {
        showQuizzesButton.setVisible(false);

        DBFunctions db = new DBFunctions();
        Connection conn = db.connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
        quizzes = db.getQuizzes(conn);

        for (String quiz : quizzes) {
            Button button = new Button(quiz);
            button.setOnAction(event -> handleQuizButtonClick(quiz));
            vbox.getChildren().add(button);
        }
    }
    public void handleQuizButtonClick(String quizName) {
        QuizData quizData = QuizData.getInstance();
        quizData.setQuizName(quizName);

        DBFunctions db = new DBFunctions();
        Connection conn = db.connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
        db.setQuizId(conn, quizName); //get the quiz id and set it in the QuizData instance

        //System.out.println(quizData.getQuizId());
        changeScene(SCENE_IDENTIFIER.QUIZ);
    }
}
