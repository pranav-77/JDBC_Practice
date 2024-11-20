package com.crud;

import java.sql.*;

public class CrudUsingJdbc {
    static String url = "jdbc:mysql://localhost:3306/employer";
    static String userName = "root";
    static String password = "root";

    public static void main(String[] args) throws SQLException {
//        createTable();
//        insertData();
//        displayData();
//        UpdateData();
//        alterData();
//        dropData();
//        deleteData();
//        truncateData();
//        dropTable();

        //SALARY TABLE
//        createSalaryTable();
//        insertSalaryData();
//        displayDataWithSalary();
//        dropSalaryTable();
//        groupByOrderByHaving();
    }

    private static void dropTable() throws SQLException {
        String query = "Drop table AddressBook";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Executed Successfully");
    }

    private static void truncateData() throws SQLException {
        String query = "Truncate table AddressBook";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Executed Successfully");
    }

    private static void deleteData() throws SQLException {
        int id = 1;
        String query = "Delete from AddressBook where id=?";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println("Deleted Successfully");
    }

    private static void dropData() throws SQLException {
        String query = "Alter table AddressBook drop column age";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Executed");
    }

    private static void alterData() throws SQLException {
        String query = "alter table AddressBook add column age int";
        Connection connection = DriverManager.getConnection(url, userName, password);
        try (Statement statement = connection.createStatement()) {
            int row = statement.executeUpdate(query);
            System.out.println(row + " row executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "update AddressBook set age=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query2);
        preparedStatement.setInt(1, 25);
        preparedStatement.setInt(2, 2);
        int row = preparedStatement.executeUpdate();
        System.out.println(row + " row executed");
    }

    private static void UpdateData() throws SQLException {
        int id = 1;
        String firstName = "Alan";
        String query = "update AddressBook set firstname=? where id=?";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, firstName);
        statement.setInt(2, id);
        int row = statement.executeUpdate();
        System.out.println(row + " row updated");
    }

    private static void displayData() throws SQLException {
        String query = "Select * from AddressBook";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            System.out.println("ID: " + result.getInt(1));
            System.out.println("FirstName: " + result.getString(2));
            System.out.println("LastName: " + result.getString(3));
            System.out.println("Address: " + result.getString(4));
            System.out.println("City: " + result.getString(5));
            System.out.println("State: " + result.getString(6));
            System.out.println("Zip: " + result.getInt(7));
            System.out.println("Phone: " + result.getLong(8));
            System.out.println("Email: " + result.getString(9));
            System.out.println();
        }
    }

    private static void insertData() throws SQLException {
        String query = "insert into AddressBook (id,firstname,lastname,address,city,state,zip,phone,email)" +
                "values (?,?,?,?,?,?,?,?,?)";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "John");
        preparedStatement.setString(3, "Doe");
        preparedStatement.setString(4, "123 Elm Street");
        preparedStatement.setString(5, "New York");
        preparedStatement.setString(6, "NY");
        preparedStatement.setInt(7, 645623);
        preparedStatement.setLong(8, 1234567890L);
        preparedStatement.setString(9, "john.doe@example.com");
        int row = preparedStatement.executeUpdate();
        System.out.println(row + " row inserted");

        // Row 2
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Jane");
        preparedStatement.setString(3, "Smith");
        preparedStatement.setString(4, "456 Oak Avenue");
        preparedStatement.setString(5, "Los Angeles");
        preparedStatement.setString(6, "CA");
        preparedStatement.setInt(7, 965123);
        preparedStatement.setLong(8, 9876543210L);
        preparedStatement.setString(9, "jane.smith@example.com");
        row = preparedStatement.executeUpdate();
        System.out.println(row + " row inserted");
    }

    private static void createTable() throws SQLException {
        String query = "CREATE TABLE AddressBook (" +
                "id INT PRIMARY KEY, " +
                "firstname VARCHAR(20), " +
                "lastname VARCHAR(20), " +
                "address VARCHAR(50), " +
                "city VARCHAR(20), " +
                "state VARCHAR(20), " +
                "zip INT, " +
                "phone BIGINT, " +
                "email VARCHAR(30)" +
                ");";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(query);
        System.out.println(row + " row executed");
    }

    //Salary Table
    private static void createSalaryTable() throws SQLException {
        String query = "CREATE TABLE Salary (" +
                "emp_id INT, " +
                "salary INT, " +
                "FOREIGN KEY (emp_id) REFERENCES AddressBook(id)" +
                ");";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Salary table created successfully.");
        }
    }

    private static void insertSalaryData() throws SQLException {
        String query = "INSERT INTO Salary (emp_id, salary) VALUES (?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 20000);
            preparedStatement.executeUpdate();
            System.out.println("Salary for employee 1 inserted.");

            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 25000);
            preparedStatement.executeUpdate();
            System.out.println("Salary for employee 2 inserted.");
        }
    }

    private static void displayDataWithSalary() throws SQLException {
        String query = "SELECT A.id, A.firstname, A.lastname, A.address, A.city, A.state, A.zip, A.phone, A.email, S.salary " +
                "FROM AddressBook A " +
                "JOIN Salary S ON A.id = S.emp_id";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                System.out.println("ID: " + result.getInt(1));
                System.out.println("FirstName: " + result.getString(2));
                System.out.println("LastName: " + result.getString(3));
                System.out.println("Address: " + result.getString(4));
                System.out.println("City: " + result.getString(5));
                System.out.println("State: " + result.getString(6));
                System.out.println("Zip: " + result.getInt(7));
                System.out.println("Phone: " + result.getLong(8));
                System.out.println("Email: " + result.getString(9));
                System.out.println("Salary: " + result.getBigDecimal(10));
                System.out.println();
            }
        }
    }

    private static void dropSalaryTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS Salary";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Salary table dropped successfully.");
        }
    }

    private static void groupByOrderByHaving() throws SQLException {
        String query = "SELECT A.city, AVG(S.salary) AS average_salary " +
                "FROM AddressBook A " +
                "JOIN Salary S ON A.id = S.emp_id " +
                "GROUP BY A.city " +
                "HAVING AVG(S.salary) > 50000 " +
                "ORDER BY average_salary DESC";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                String city = result.getString("city");
                double averageSalary = result.getDouble("average_salary");
                System.out.println("City: " + city + ", Average Salary: " + averageSalary);
            }
        }
    }


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
