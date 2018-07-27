package lesson2.homework.productTask;

import java.sql.*;
import java.util.ArrayList;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    public static void saveProduct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("INSERT INTO PRODUCT VALUES (999, 'toy', 'for children', 60)");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void deleteProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME = 'toy'");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void deleteProductsByPrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM PRODUCT WHERE PRICE < 100");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

            allProducts = mapToObjects(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return allProducts;
    }

    public static ArrayList<Product> getProductsByPrice() {
        ArrayList<Product> allProducts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRICE <= 100");

            allProducts = mapToObjects(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return allProducts;
    }

    public static ArrayList<Product> getProductsByDescription() {
        ArrayList<Product> allProducts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50");

            allProducts = mapToObjects(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return allProducts;
    }

    public static void increasePrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("UPDATE PRODUCT SET PRICE = PRICE + 100 WHERE PRICE < 970");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void changeDescription() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 100");

            ArrayList<Product> allProducts = mapToObjects(resultSet);
            for (int i = 0; i < allProducts.size(); i++) {
                String newDescription = deleteLastSentence(allProducts.get(i).getDescription());
                String query = "UPDATE PRODUCT SET DESCRIPTION = '" + newDescription + "' WHERE ID = " + allProducts.get(i).getId();
                statement.execute(query);
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static String deleteLastSentence(String text) {
        for (int i = text.length() - 2; i >= 0; i--) {
            if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?')
                return text.substring(0, i + 1);
        }

        return "";
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
}