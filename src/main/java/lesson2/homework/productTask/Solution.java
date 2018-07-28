package lesson2.homework.productTask;

import java.sql.*;
import java.util.ArrayList;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    private static final String saveProductQuery = "INSERT INTO PRODUCT VALUES (999, 'toy', 'for children', 60)";
    private static final String deleteProductsQuery = "DELETE FROM PRODUCT WHERE NAME = 'toy'";
    private static final String deleteProductsByPriceQuery = "DELETE FROM PRODUCT WHERE PRICE < 100";
    private static final String getAllProductsQuery = "SELECT * FROM PRODUCT";
    private static final String getProductsByPriceQuery = "SELECT * FROM PRODUCT WHERE PRICE <= 100";
    private static final String getProductsByDescriptionQuery = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50";
    private static final String increasePriceQuery = "UPDATE PRODUCT SET PRICE = PRICE + 100 WHERE PRICE < 970";
    private static final String getProdByDescriptionQuery = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 20";
    private static final String updateProductsDescription = "UPDATE PRODUCT SET DESCRIPTION = ? WHERE ID = ?";

    public static void saveProduct() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(saveProductQuery);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void deleteProducts() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(deleteProductsQuery);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void deleteProductsByPrice() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(deleteProductsByPriceQuery);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getAllProducts() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getAllProductsQuery);

            return mapToObjects(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static ArrayList<Product> getProductsByPrice() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getProductsByPriceQuery);

            return mapToObjects(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static ArrayList<Product> getProductsByDescription() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getProductsByDescriptionQuery);

            return mapToObjects(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void increasePrice() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(increasePriceQuery);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void changeDescription() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getProdByDescriptionQuery);

            ArrayList<Product> allProducts = mapToObjects(resultSet);
            for (Product product : allProducts) {
                PreparedStatement preparedStatement = connection.prepareStatement(updateProductsDescription);
                preparedStatement.setString(1, deleteLastSentence(product.getDescription()));
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static String deleteLastSentence(String text) {
        int lastIndexOfPoint = text.lastIndexOf('.', text.length() - 2);
        int lastIndexOfQuery = text.lastIndexOf('?', text.length() - 2);
        int lastIndexOfExclamation = text.lastIndexOf('!', text.length() - 2);

        int lastSentenceStartIndex = lastIndexOfPoint > lastIndexOfQuery ? lastIndexOfPoint : lastIndexOfQuery;
        lastSentenceStartIndex = lastSentenceStartIndex > lastIndexOfExclamation ? lastSentenceStartIndex : lastIndexOfExclamation;

        return lastSentenceStartIndex == -1 ? "" : text.substring(0, lastSentenceStartIndex + 1);
    }

    private static ArrayList<Product> mapToObjects(ResultSet resultSet) throws SQLException {
        ArrayList<Product> allProducts = new ArrayList<>();

        while (resultSet.next()) {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);

            allProducts.add(new Product(id, name, description, price));
        }

        return allProducts;
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}