package com.jdbc;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Locale;

public class Practice2 {
    static String url = "jdbc:mysql://localhost:3306/jdbc";
    static String userName = "root";
    static String password = "root";

    public static void main(String[] args) throws SQLException {
//        createTable();
//        insertData();
//        alterTable();
//        updateTable();
//        deleteTable();
        groupByTable();
//        // Next Table
//        createTable2();
//        insertData2();
//        viewData();
    }

    private static void viewData() throws SQLException {
        String query = "SELECT student.rollNo, student.name, student.email, mark.marks " +
                "FROM mark " +
                "JOIN student ON mark.rollNo = student.rollNo";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            System.out.println("Roll No: "+res.getInt(1));
            System.out.println("name: "+res.getString(2));
            System.out.println("email: "+res.getString(3));
            System.out.println("marks: "+res.getInt(4));
            System.out.println();
        }
    }

    private static void insertData2() throws SQLException {
        String query = "insert into student (rollNo, name, email)" +
                "values (?,?,?)";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, 1);
        statement.setString(2, "akash");
        statement.setString(3, "akash@gmail.com");
        int row = statement.executeUpdate();
        System.out.println(row + " row data inserted");

        statement.setInt(1, 2);
        statement.setString(2, "alan");
        statement.setString(3, "alan@gmail.com");
        row = statement.executeUpdate();
        System.out.println(row + " row data inserted");

        statement.setInt(1, 3);
        statement.setString(2, "prakash");
        statement.setString(3, "prakash@gmail.com");
        row = statement.executeUpdate();
        System.out.println(row + " row data inserted");

        statement.setInt(1, 4);
        statement.setString(2, "nikhil");
        statement.setString(3, "nikhil@gmail.com");
        row = statement.executeUpdate();
        System.out.println(row + " row data inserted");
    }

    private static void  createTable2() throws SQLException {
        String query = "create table student (rollNo int primary key, name varchar(30), email varchar(50))";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Table created");
    }

    // next Table
    private static void groupByTable() throws SQLException {
        String query = "select mark.marks, count(student.rollNo) as count " +
                "from student join mark on student.rollNo = mark.rollNo " +
                "group by student.rollNo";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        System.out.println(statement.executeQuery(query));
    }

    private static void deleteTable() {
    }

    private static void updateTable() throws SQLException {
        String query = "update mark " +
                "set marks=87 " +
                "where rollNo=1;";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Updated Successfully");
    }

    private static void alterTable() {
    }

    private static void insertData() {
        String query = "insert into mark (rollNo, marks)" +
                "values(?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 1);
            statement.setInt(2, 67);
            statement.executeUpdate();

            statement.setInt(1, 2);
            statement.setInt(2, 87);
            statement.executeUpdate();

            statement.setInt(1, 3);
            statement.setInt(2, 90);
            statement.executeUpdate();

            statement.setInt(1, 4);
            statement.setInt(2, 88);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Execution Successfull");
    }

    private static void createTable() throws SQLException {
        String query = "create table mark (rollNo int," +
                "foreign key (rollNo) references student (rollNo)," +
                "marks int)";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Table created");
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
