package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.aforce.bean.Customer;

public class CustomerDAO {
    // データベースの接続情報
    private String jdbcURL = "jdbc:mysql://localhost:3306/login_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "aforce01";

    // データベースに接続するメソッド
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // ログイン情報を検索するメソッド
    public Customer search(String login, String password) {
        String SELECT_USER_SQL = "SELECT * FROM login WHERE id = ? AND password = ?";
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL);) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                customer = new Customer(login, name, password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    // SQL例外のエラー処理
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
