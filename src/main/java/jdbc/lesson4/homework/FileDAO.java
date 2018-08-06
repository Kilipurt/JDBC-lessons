package jdbc.lesson4.homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    private static final String saveFileRequest = "INSERT INTO FILES VALUES (?, ?, ?, ?, ?)";
    private static final String deleteFileRequest = "DELETE FROM FILES WHERE FILES.ID = ?";
    private static final String updateFileRequest = "UPDATE FILES SET FILES.NAME = ?, FILES.FORMAT = ?, FILES.FILE_SIZE = ?, FILES.STORAGE = ? WHERE FILES.ID = ?";
    private static final String findByIdFileRequest = "SELECT * FROM FILES WHERE FILES.ID = ?";
    private static final String findFilesByStorageIdRequest = "SELECT * FROM FILES WHERE FILES.STORAGE = ?";

    private static StorageDAO storageDAO = new StorageDAO();

    public File save(File file) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveFileRequest)) {

            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getFileSize());
            preparedStatement.setObject(5, null);

            int res = preparedStatement.executeUpdate();
            System.out.println("File with id " + file.getId() + " was saved with result " + res);

        } catch (SQLException e) {
            System.err.println("File with id " + file.getId() + " was not saved");
            e.printStackTrace();
        }

        return file;
    }

    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteFileRequest)) {

            preparedStatement.setLong(1, id);

            int res = preparedStatement.executeUpdate();
            System.out.println("File with id " + id + " was deleted with result " + res);

        } catch (SQLException e) {
            System.err.println("File with id " + id + " was not deleted");
            e.printStackTrace();
        }
    }

    public File update(File file) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateFileRequest)) {

            preparedStatementUpdate.setString(1, file.getName());
            preparedStatementUpdate.setString(2, file.getFormat());
            preparedStatementUpdate.setLong(3, file.getFileSize());

            if (file.getStorage() != null) {
                preparedStatementUpdate.setLong(4, file.getStorage().getId());
            } else {
                preparedStatementUpdate.setObject(4, null);
            }

            preparedStatementUpdate.setLong(5, file.getId());

            int res = preparedStatementUpdate.executeUpdate();
            System.out.println("File with id " + file.getId() + " was updated with result " + res);

        } catch (SQLException e) {
            System.err.println("File with id " + file.getId() + " was not updated");
            e.printStackTrace();
        }

        return file;
    }

    public File findById(long id) throws Exception {
        File file = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdFileRequest)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));

                file.setStorage(storageDAO.findById(resultSet.getLong(5)));
            }

        } catch (SQLException e) {
            System.err.println("File with id " + id + " was not found");
            e.printStackTrace();
        }

        return file;
    }

    public List<File> findFilesByStorageId(long id) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findFilesByStorageIdRequest)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<File> files = new ArrayList<>();
            while (resultSet.next()) {
                File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
                file.setStorage(storageDAO.findById(id));

                files.add(file);
            }

            return files;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void changeStorage(List<File> files, long storageId) throws Exception {
        try (Connection connection = getConnection()) {
            changeStorageForList(files, storageId, connection);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private void changeStorageForList(List<File> files, long storageId, Connection connection) throws Exception {
        long fileId = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateFileRequest)) {

            connection.setAutoCommit(false);

            for (File file : files) {
                fileId = file.getId();

                preparedStatement.setString(1, file.getName());
                preparedStatement.setString(2, file.getFormat());
                preparedStatement.setLong(3, file.getFileSize());
                preparedStatement.setLong(4, storageId);
                preparedStatement.setLong(5, file.getId());

                preparedStatement.executeUpdate();
            }

            connection.commit();
            System.out.println("Transfer was finished successfully");

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("File with id " + fileId + " was not transfer. " + e.getMessage());
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
