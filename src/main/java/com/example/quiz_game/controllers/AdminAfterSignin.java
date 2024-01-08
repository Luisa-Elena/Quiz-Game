package com.example.quiz_game.controllers;

import com.example.quiz_game.utils.DBFunctions;
import com.example.quiz_game.utils.UserQuizScore;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminAfterSignin extends SceneController{

    @FXML
    private Button viewScoresButton;
    @FXML
    private Button viewUsersButton;
    @FXML
    private AnchorPane anchorPane;

    private List<String> users = new ArrayList<>();

    private VBox labelContainer;
    private TableView<UserQuizScore> tableView;

    public AdminAfterSignin() {
        // Initialize containers
        labelContainer = new VBox();
        tableView = new TableView<>();

        // Set initial visibility
        labelContainer.setVisible(false);
        tableView.setVisible(false);
    }

    public void onCloseAppClick() {
        closeApplication();
    }

    public void onViewScoresClick() {
        labelContainer.setVisible(false);
        tableView.setVisible(true);

        anchorPane.getChildren().clear();

        TableView<UserQuizScore> tableView = new TableView<>();
        TableColumn<UserQuizScore, String> userNameColumn = new TableColumn<>("User Name");
        TableColumn<UserQuizScore, String> quizNameColumn = new TableColumn<>("Quiz Name");
        TableColumn<UserQuizScore, Integer> userScoreColumn = new TableColumn<>("User Score");

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<>("quizName"));
        userScoreColumn.setCellValueFactory(new PropertyValueFactory<>("userScore"));

        tableView.getColumns().addAll(userNameColumn, quizNameColumn, userScoreColumn);

        DBFunctions db = new DBFunctions();
        ResultSet resultSet = db.getTableViewData();
        try {
            while (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                String quizName = resultSet.getString("quiz_name");
                int userScore = resultSet.getInt("user_score");

                tableView.getItems().add(new UserQuizScore(userName, quizName, userScore));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AnchorPane.setTopAnchor(tableView, 30.0);
        AnchorPane.setRightAnchor(tableView, 20.0);
        AnchorPane.setBottomAnchor(tableView, 20.0);
        AnchorPane.setLeftAnchor(tableView, 20.0);

        anchorPane.getChildren().add(tableView);
    }

    public void onViewUsersClick() {
        tableView.setVisible(false);
        labelContainer.setVisible(true);

        anchorPane.getChildren().clear();

        DBFunctions db = new DBFunctions();
        users = db.getAllUsers();

        VBox labelContainer = new VBox();

        for (String user : users) {
            HBox userBox = new HBox();

            Label label = new Label(user);
            label.setStyle("-fx-font-size: 17px;");

            Button userButton = new Button("Delete");
            userButton.setOnAction(e -> handleUserButtonClick(user));

            userBox.getChildren().addAll(label, userButton);
            VBox.setMargin(userBox, new Insets(2, 5, 2, 20));

            labelContainer.getChildren().add(userBox);
        }

        AnchorPane.setTopAnchor(labelContainer, 30.0);
        anchorPane.getChildren().add(labelContainer);
    }

    private void handleUserButtonClick(String userName) {
        //System.out.println("Deleted user: " + userName);
        DBFunctions db = new DBFunctions();
        db.deleteUser(userName);
    }
}