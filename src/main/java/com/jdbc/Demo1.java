package com.jdbc;

import java.sql.*;

public class Demo1 {
    // Reading data from database tables
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employer";
        String userName = "root";
        String password = "root";
        String query = "select * from employee";

        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt(1));
            System.out.println("Name: " + resultSet.getString(2));
            System.out.println("Address: " + resultSet.getString(3));
            System.out.println("Salary: " + resultSet.getInt(4));
            System.out.println("Gender: " + resultSet.getString(5));
            System.out.println("Dept id: " + resultSet.getInt(6));
            System.out.println("Mobile no: " + resultSet.getString(7));
            System.out.println();
        }
    }
}
