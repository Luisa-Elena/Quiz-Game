package com.example.quiz_game.utils;

public class UserQuizScore {
    private String userName;
    private String quizName;
    private int userScore;

    public UserQuizScore(String userName, String quizName, int userScore) {
        this.userName = userName;
        this.quizName = quizName;
        this.userScore = userScore;
    }

    public String getUserName() {
        return userName;
    }

    public String getQuizName() {
        return quizName;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}