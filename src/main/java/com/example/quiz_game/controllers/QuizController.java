package com.example.quiz_game.controllers;

import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.QuizData;
import com.example.quiz_game.utils.UserData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

public class QuizController extends SceneController{
    @FXML
    private Button startQuizButton;

    @FXML
    private Label questionText;

    @FXML
    private RadioButton a1;

    @FXML
    private RadioButton a2;

    @FXML
    private RadioButton a3;

    @FXML
    private RadioButton a4;

    @FXML
    private Button next;

    private ToggleGroup toggleGroup = new ToggleGroup();
    private RadioButton selectedAnswer = new RadioButton();
    private String correctAnswer;
    private int points = 0;
    private int maxPoints = 0; //the number of questions (each question values one point)
    private ResultSet resultSet;

    private Timer timer = new Timer();
    private int currentQuestionDuration;

    @FXML
    private Label time;
    @FXML
    private Label timeInfo1;
    @FXML
    private Label timeInfo2;
    @FXML
    private Label label;

    private DBFunctions db = new DBFunctions();

    public void onStartQuizButtonClick() {
        timeInfo1.setVisible(true);
        timeInfo2.setVisible(true);
        label.setVisible(true);
        startQuizButton.setVisible(false);
        startQuizButton.setDisable(true);

        questionText.setVisible(true);
        a1.setVisible(true);
        a2.setVisible(true);
        a3.setVisible(true);
        a4.setVisible(true);
        next.setVisible(true);

        a1.setToggleGroup(toggleGroup);
        a2.setToggleGroup(toggleGroup);
        a3.setToggleGroup(toggleGroup);
        a4.setToggleGroup(toggleGroup);

        resultSet = db.getQuestions();

        getNextQuestion(resultSet);
    }

    public void onRadioButtonClicked() {
        selectedAnswer = (RadioButton) toggleGroup.getSelectedToggle();
    }

    public void getNextQuestion(ResultSet resultSet) {
        selectedAnswer = null;
        try {
            if (resultSet.next()) {
                maxPoints++;

                correctAnswer = resultSet.getString("correct_a");
                questionText.setText(resultSet.getString("q_text"));
                a1.setText(resultSet.getString("a1"));
                a2.setText(resultSet.getString("a2"));
                a3.setText(resultSet.getString("a3"));
                a4.setText(resultSet.getString("a4"));
                currentQuestionDuration = resultSet.getInt("duration");
                time.setText(String.valueOf(currentQuestionDuration));

                // Schedule a TimerTask to decrement the variable every one second
                timer.scheduleAtFixedRate(new DecrementTask(), 1000, 1000);
            }
            else {
                timeInfo1.setVisible(false);
                timeInfo2.setVisible(false);
                questionText.setVisible(false);

                label.setText("Your score: " + points + " points out of " + maxPoints);

                boolean isToInsert = db.searchScore(UserData.getInstance().getId(), QuizData.getInstance().getQuizId());
                if(isToInsert) { //insert new score for this quiz and user
                    //System.out.println("insert");
                    db.insertScore(UserData.getInstance().getId(), QuizData.getInstance().getQuizId(), points);

                } else { //update score for this quiz and user
                    //System.out.println("update");
                    db.updateScore(UserData.getInstance().getId(), QuizData.getInstance().getQuizId(), points);
                }

                time.setVisible(false);
                a1.setVisible(false);
                a2.setVisible(false);
                a3.setVisible(false);
                a4.setVisible(false);
                next.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void seeIfCorrect() {
        if(selectedAnswer != null && correctAnswer.equals(selectedAnswer.getText())) {
            points++;
        }
    }
    public void onNextButtonClick() {
        timer.cancel();
        timer.purge();
        timer = new Timer(); //we need to create a new instance

        seeIfCorrect();
        a1.setSelected(false);
        a2.setSelected(false);
        a3.setSelected(false);
        a4.setSelected(false);
        getNextQuestion(resultSet);
    }

    public void onCloseAppClick() {
        closeApplication();
    }

    private class DecrementTask extends TimerTask {
        @Override
        public void run() {
            // Decrement the variable
            currentQuestionDuration--;

            // Update UI
            Platform.runLater(() -> {
                time.setText(String.valueOf(currentQuestionDuration));
            });

            if (currentQuestionDuration <= 0) {
                // Update UI components
                Platform.runLater(() -> {
                   onNextButtonClick();
                });
            }
        }
    }
}
