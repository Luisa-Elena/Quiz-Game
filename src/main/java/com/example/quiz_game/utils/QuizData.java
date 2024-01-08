package com.example.quiz_game.utils;

public class QuizData {
    private String quizName;

    private int quizId;
    public static QuizData instance = null;
    public static QuizData getInstance() {
        if(QuizData.instance == null){
            QuizData.instance = new QuizData();
        }
        return QuizData.instance;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuizId() {
        return quizId;
    }
}