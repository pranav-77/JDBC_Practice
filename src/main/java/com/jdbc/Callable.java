package com.jdbc;

import java.sql.*;

public class Callable {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String userName = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url,userName,password);
        String query = "call getbranches1(?)";
        CallableStatement statement = connection.prepareCall(query);
        statement.setString(1,"alan");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println("name: "+resultSet.getString("empname"));
            System.out.println("id: "+resultSet.getInt("empid"));
        }
    }
}
