package lesson4;

import lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    private static final String saveProductRequest = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?)";

    public static void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        long productId = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveProductRequest)) {

            connection.setAutoCommit(false);

            for (Product product : products) {
                productId = product.getId();

                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                preparedStatement.executeUpdate();
            }

            connection.commit();
            System.out.println("Save was finished successfully");

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Product with id " + productId + " was not saved. " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
