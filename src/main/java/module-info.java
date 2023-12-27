module com.example.quiz_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.quiz_game to javafx.fxml;
    exports com.example.quiz_game;
    exports com.example.quiz_game.enums;
    opens com.example.quiz_game.enums to javafx.fxml;
    exports com.example.quiz_game.utils;
    opens com.example.quiz_game.utils to javafx.fxml;
    exports com.example.quiz_game.controllers;
    opens com.example.quiz_game.controllers to javafx.fxml;
}