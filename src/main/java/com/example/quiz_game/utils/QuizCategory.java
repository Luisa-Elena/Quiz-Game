package com.example.quiz_game.utils;

public class QuizCategory {
    private String category;
    public static QuizCategory instance = null;
    public static QuizCategory getInstance() {
        if(QuizCategory.instance == null){
            QuizCategory.instance = new QuizCategory();
        }
        return QuizCategory.instance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
