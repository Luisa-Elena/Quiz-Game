package com.example.quiz_game.enums;

public enum SCENE_IDENTIFIER {

    WELCOME("welcome-page.fxml"),
    SIGNUP("signup-page.fxml"),
    SIGNIN("signin-page.fxml"),
    USER("user-after-signin.fxml"),
    ADMIN("admin-after-signin.fxml"),
    QUIZZES("quizzes-view.fxml"),
    QUIZ("quiz.fxml");

    public final String label;

    SCENE_IDENTIFIER(String label) { this.label = label; }
}
