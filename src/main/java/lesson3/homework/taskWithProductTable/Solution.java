package lesson3.homework.taskWithProductTable;

import lesson3.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    private static final String findProductsByPriceRequest = "SELECT * FROM PRODUCT WHERE PRODUCT.PRICE BETWEEN ? AND ?";
    private static final String findProductsByNameRequest = "SELECT * FROM PRODUCT WHERE PRODUCT.NAME LIKE ?";
    private static final String emptyDescriptionProductsRequest = "SELECT * FROM PRODUCT WHERE PRODUCT.DESCRIPTION IS NULL";

    public List<Product> findProductsByPrice(int price, int delta) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(findProductsByPriceRequest);

            preparedStatement.setInt(1, price - delta);
            preparedStatement.setInt(2, price + delta);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> findProductsByName(String word) throws Exception {
        validateProductName(word);

        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(findProductsByNameRequest);

            preparedStatement.setString(1, "%" + word + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> findProductsWithEmptyDescription() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(emptyDescriptionProductsRequest);

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return products;
    }

    private void validateProductName(String word) throws Exception {
        if (word.length() < 3)
            throw new Exception("Word " + word + " is not enough long");

        for (char ch : word.toCharArray()) {
            if (!Character.isLetterOrDigit(ch))
                throw new Exception("Word " + word + " contains special characters");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
