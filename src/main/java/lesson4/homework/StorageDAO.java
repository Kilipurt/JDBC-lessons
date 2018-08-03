package lesson4.homework;

import java.sql.*;

public class StorageDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    private static final String saveStorageRequest = "INSERT INTO STORAGE VALUES (?, ?, ?, ?)";
    private static final String deleteStorageRequest = "DELETE FROM STORAGE WHERE STORAGE.ID = ?";
    private static final String updateStorageRequest = "UPDATE STORAGE SET STORAGE.FORMATS_SUPPORTED = ?, STORAGE.STORAGE_COUNTRY = ?, STORAGE.STORAGE_SIZE = ? WHERE STORAGE.ID = ?";
    private static final String findByIdStorageRequest = "SELECT * FROM STORAGE WHERE STORAGE.ID = ?";

    public Storage save(Storage storage) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveStorageRequest)) {

            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, storage.formatsSupportedToString());
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageSize());

            int res = preparedStatement.executeUpdate();
            System.out.println("Storage with id " + storage.getId() + " was saved with result " + res);

        } catch (SQLException e) {
            System.err.println("Storage with id " + storage.getId() + " was not saved");
            e.printStackTrace();
        }

        return storage;
    }

    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteStorageRequest)) {

            preparedStatement.setLong(1, id);

            int res = preparedStatement.executeUpdate();
            System.out.println("Storage with id " + id + " was deleted with result " + res);

        } catch (SQLException e) {
            System.err.println("Storage with id " + id + " was not deleted");
            e.printStackTrace();
        }
    }

    public Storage update(Storage storage) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateStorageRequest)) {

            preparedStatement.setString(1, storage.formatsSupportedToString());
            preparedStatement.setString(2, storage.getStorageCountry());
            preparedStatement.setLong(3, storage.getStorageSize());
            preparedStatement.setLong(4, storage.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Storage with id " + storage.getId() + " was updated with result " + res);

        } catch (SQLException e) {
            System.err.println("Storage with id " + storage.getId() + " was not updated");
            e.printStackTrace();
        }

        return storage;
    }

    public Storage findById(long id) {
        Storage storage = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdStorageRequest)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] formatsSupported = resultSet.getString(2).split(", ");

                storage = new Storage(resultSet.getLong(1), formatsSupported, resultSet.getString(3), resultSet.getLong(4));
            }

        } catch (SQLException e) {
            System.err.println("Storage was not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return storage;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
