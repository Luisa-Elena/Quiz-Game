package com.example.quiz_game.utils;

public class UserData {
    private String name;
    private String role;
    private int id;

    public static UserData instance = null;
    public static UserData getInstance() {
        if(UserData.instance == null){
            UserData.instance = new UserData();
        }
        return UserData.instance;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }
}
