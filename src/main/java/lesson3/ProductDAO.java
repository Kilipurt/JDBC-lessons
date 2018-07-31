package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    private static final String saveProductRequest = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?)";
    private static final String updateProductRequest = "UPDATE PRODUCT SET PRODUCT.NAME = ?, PRODUCT.DESCRIPTION = ?, PRODUCT.PRICE = ? WHERE PRODUCT.ID = ?";
    private static final String getProductsRequest = "SELECT * FROM PRODUCT";
    private static final String deleteProductRequest = "DELETE FROM PRODUCT WHERE PRODUCT.ID = ?";

    public Product save(Product product) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(saveProductRequest);

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int res = preparedStatement.executeUpdate();
            System.out.println("Save was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return product;
    }

    public Product update(Product product) throws Exception {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(updateProductRequest);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0)
                throw new Exception("Product with id " + product.getId() + " was not found");

            System.out.println("Update was successful");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getProductsRequest);

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));

                products.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return products;
    }

    public void delete(long id) throws Exception {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(deleteProductRequest);

            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();

            if (result == 0)
                throw new Exception("Product with id " + id + " was not found");

            System.out.println("Product was deleted");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
