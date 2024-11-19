package com.jdbc;

import java.sql.*;

public class JDBCoperations {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employer";
        String userName = "root";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
//            try (Statement statement = connection.createStatement()){
//                String createTable = "create table Student(id int primary key, name varchar(20), age int)";
//                statement.executeUpdate(createTable);
//                System.out.println("table is created successfully");
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//            try (Statement statement = connection.createStatement()) {
//                String insertData = "insert into student(id, name, age) values (1,'pranav',22)";
//                statement.executeUpdate(insertData);
//                System.out.println("data is inserted successfully");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
            try (Statement statement = connection.createStatement()) {
                String fetchData = "Select * from student";
                ResultSet resultSet = statement.executeQuery(fetchData);
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    System.out.println(id+" "+name+" "+age);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}