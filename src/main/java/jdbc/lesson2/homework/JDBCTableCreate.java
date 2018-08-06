package jdbc.lesson2.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTableCreate {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode.c4qju9dqz8qa.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "2016UKRainian";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("CREATE TABLE PRODUCT (\n" +
                    "ID NUMBER NOT NULL ENABLE,\n" +
                    "    CONSTRAINT PROD_PK PRIMARY KEY (ID),\n" +
                    "    NAME NVARCHAR2(20) NOT NULL,\n" +
                    "    DESCRIPTION CLOB,\n" +
                    "    PRICE NUMBER NOT NULL)");
            System.out.println(response);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
