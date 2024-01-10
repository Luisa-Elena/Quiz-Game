package com.example.quiz_game.utils;

import java.sql.*;
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

    public void insertNewUser(String name, String password, String role) {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
        Statement statement;
        try {
            String query = String.format("insert into users(name,password,role) values('%s','%s','%s')", name, password, role);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("New user inserted");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean fetchUser(String name, String password) {
        Connection conn = connectToDB(Environment.DBNAME, Environment.DBUSER, Environment.DBPASSWORD);
        Statement statement;
        boolean ok = false;
        try{
            String query = String.format("select * from users where name='%s' and password='%s'",name,password);
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

    public List<String> getQuizCategories() {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public List<String> getQuizzes() {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public ResultSet getQuestions () {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public void setQuizId (String quizName) {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public boolean searchScore(int userId, int quizId) {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public void insertScore (int userId, int quizId, int points) {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public void updateScore(int userId, int quizId, int points) {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
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

    public ResultSet getTableViewData () {
        ResultSet resultSet = null;
        Connection connection = connectToDB(Environment.DBNAME, Environment.DBUSER, Environment.DBPASSWORD);
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user_quiz_scores_view");
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public List<String> getAllUsers() {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
        Statement statement;
        ResultSet resultSet;
        List<String> users = new ArrayList<>();
        try {
            String query = String.format("select * from users");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                String role = resultSet.getString("role");
                if(role.equals("user")) { //only display users such that admins cannot be deleted
                    users.add(resultSet.getString("name"));
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return users;
    }

    public void deleteUser(String userName) {
        Connection conn = connectToDB(Environment.DBNAME,Environment.DBUSER, Environment.DBPASSWORD);
        Statement statement1;
        Statement statement2;
        try {
            String query1 = String.format("DELETE FROM score WHERE user_id IN (SELECT id FROM users WHERE name = '%s')", userName);
            statement1 = conn.createStatement();
            statement1.executeUpdate(query1);

            String query2 = String.format("DELETE FROM users WHERE name = '%s'", userName);
            statement2 = conn.createStatement();
            statement2.executeUpdate(query2);

            System.out.println("Successfully deleted");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}