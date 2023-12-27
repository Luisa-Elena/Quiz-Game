package com.example.quiz_game.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBFunctions {
    public Connection connectToDB(String dbName, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
            if(conn != null) {
                //System.out.println("Connection established");
            } else {
                System.out.println("Connection failed");
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return conn;
    }

    public void insertNewUser(Connection conn, String tableName, String name, String password, String role) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name,password,role) values('%s','%s','%s')", tableName, name, password, role);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("New user inserted");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean fetchUser(Connection conn, String tableNAme, String name, String password) {
        Statement statement;
        boolean ok = false;
        try{
            String query = String.format("select * from %s where name='%s' and password='%s'",tableNAme,name,password);
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                ok = true;
                UserData userData = UserData.getInstance();
                userData.setId(Integer.parseInt(resultSet.getString("id")));
                userData.setName(resultSet.getString("name"));
                userData.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ok;
    }

    public List<String> getQuizCategories(Connection conn) {
        Statement statement;
        ResultSet resultSet;
        List<String> categories = new ArrayList<>();
        try {
            String query = String.format("select category_name from quiz_category");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                categories.add(resultSet.getString("category_name"));
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return categories;
    }

    public List<String> getQuizzes(Connection conn) {
        Statement statement;
        ResultSet resultSet;
        List<String> quizzes = new ArrayList<>();
        try {
            String query = String.format("select name from quizzes q \n" +
                    "join quiz_category qc on q.category = qc.id \n" +
                    "where qc.category_name = '%s'", QuizCategory.getInstance().getCategory());
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                quizzes.add(resultSet.getString("name"));
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return quizzes;
    }

    public ResultSet getQuestions (Connection conn) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            String query = String.format("select * from questions q\n" +
                    "join quizzes q2 on q.quiz_id = q2.id \n" +
                    "where q2.name = '%s'", QuizData.getInstance().getQuizName());
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

        } catch(Exception e) {
            System.out.println(e);
        }

        return resultSet;
    }

    public void setQuizId (Connection conn, String quizName) {
        Statement statement;
        try{
            String query = String.format("select id from quizzes q \n" +
                    "where q.\"name\" = '%s';", quizName);
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                QuizData.getInstance().setQuizId(Integer.parseInt(resultSet.getString("id")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean searchScore(Connection conn, int userId, int quizId) {
        boolean ok = true;
        Statement statement;
        try{
            String query = String.format("select * from score s \n" +
                    "where quiz_id = %d and user_id = %d;", quizId, userId);
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                ok = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return ok;
    }

    public void insertScore (Connection conn, int userId, int quizId, int points) {
        Statement statement;
        try {
            String query = String.format("insert into score (user_id, quiz_id, score)\n" +
                    "values (%d, %d, %d);", userId, quizId, points);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("New score inserted");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateScore(Connection conn, int userId, int quizId, int points) {
        Statement statement;
        try {
            String query = String.format("update score\n" +
                    "set score = %d\n" +
                    "where user_id = %d\n" +
                    "  and quiz_id = %d;", points, userId, quizId);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Score updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
