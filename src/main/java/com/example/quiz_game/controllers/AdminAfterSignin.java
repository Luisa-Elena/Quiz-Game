package com.example.quiz_game.controllers;

import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.Environment;
import com.example.quiz_game.utils.UserQuizScore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminAfterSignin extends SceneController{

    @FXML
    private Button viewScoresButton;

    @FXML
    private AnchorPane anchorPane;

    public void onCloseAppClick() {
        closeApplication();
    }

    public void onViewScoresClick() {
        viewScoresButton.setVisible(false);

        TableView<UserQuizScore> tableView = new TableView<>();
        TableColumn<UserQuizScore, String> userNameColumn = new TableColumn<>("User Name");
        TableColumn<UserQuizScore, String> quizNameColumn = new TableColumn<>("Quiz Name");
        TableColumn<UserQuizScore, Integer> userScoreColumn = new TableColumn<>("User Score");

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<>("quizName"));
        userScoreColumn.setCellValueFactory(new PropertyValueFactory<>("userScore"));

        tableView.getColumns().addAll(userNameColumn, quizNameColumn, userScoreColumn);

        DBFunctions db = new DBFunctions();
        Connection connection = db.connectToDB(Environment.DBNAME, Environment.DBUSER, Environment.DBPASSWORD);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_quiz_scores_view");

            while (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                String quizName = resultSet.getString("quiz_name");
                int userScore = resultSet.getInt("user_score");

                tableView.getItems().add(new UserQuizScore(userName, quizName, userScore));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AnchorPane.setTopAnchor(tableView, 50.0);
        AnchorPane.setRightAnchor(tableView, 20.0);
        AnchorPane.setBottomAnchor(tableView, 20.0);
        AnchorPane.setLeftAnchor(tableView, 20.0);

        anchorPane.getChildren().add(tableView);
    }
}
