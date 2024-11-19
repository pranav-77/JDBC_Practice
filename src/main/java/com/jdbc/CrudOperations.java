package com.jdbc;

import java.sql.*;

public class CrudOperations {
    static String url = "jdbc:mysql://localhost:3306/employer";
    static String userName = "root";
    static String password = "root";

    public static void main(String[] args) throws SQLException {
//        createTable();
//        insertData();
//        insertByVariable();
//        insertByPreparedStatement();
//        updateValue();
//        updateValueByPreparedStatement();
//        deleteData();
        readData();
    }

    //Created Table
    private static void createTable() throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "create table Student(id int primary key, name varchar(20), age int)";
        statement.executeUpdate(query);
        System.out.println("table is created successfully");
    }

    //Reading Data
    private static void readData() throws SQLException {
        String query = "select * from student";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            System.out.println("ID: " + res.getInt(1));
            System.out.println("Name: " + res.getString(2));
            System.out.println("Age: " + res.getInt(3));
            System.out.println();
        }
    }

    //Update or insert data by passing values
    public static void insertData() throws SQLException {
        String query = "insert into student values(2, 'Akash', 23)";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(query);
        System.out.println(row + " row executed");
    }

    //insert data by using variable
    private static void insertByVariable() throws SQLException {
        int id = 3;
        String name = "Raman";
        int age = 21;
        String query = "insert into student values(" + id + ", '" + name + "'," + age + ")";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(query);
        System.out.println(row + " row executed");
    }

    //insert using prepared statement
    private static void insertByPreparedStatement() throws SQLException {
        int id = 4;
        String name = "Akhil";
        int age = 24;
        String query = "insert into student values(?,?,?)";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setInt(3, age);
        int row = statement.executeUpdate();
        System.out.println(row + " row executed");
    }

    //Update value
    private static void updateValue() throws SQLException {
        int id = 3;
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "update student set name='Sanjay' where id=" + id;
        int row = statement.executeUpdate(query);
        System.out.println(row + " rows updated");
    }

    //Update value using prepared statements
    private static void updateValueByPreparedStatement() throws SQLException {
        int id = 4;
        String query = "update student set age=35 where id=?";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        int row = statement.executeUpdate();
        System.out.println(row + " row updated");
    }

    //Delete Data
    private static void deleteData() throws SQLException {
        int id = 2;
        String query = "delete from student where id=?";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        int row = statement.executeUpdate();
        System.out.println(row + " row deleted");
    }
}
