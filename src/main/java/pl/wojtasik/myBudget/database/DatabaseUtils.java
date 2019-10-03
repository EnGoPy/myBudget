package pl.wojtasik.myBudget.database;

import pl.wojtasik.myBudget.exception.BudgetConnectionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseUtils {

    private static Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());

    private static Connection connection;

    private DatabaseUtils() {
    }

    public static Connection getConnection() throws BudgetConnectionException {
        if (connection == null) {
            try {
                Properties properties = readProperties();
                System.out.println("PROPERTIES: " + properties);
                connection = DriverManager.getConnection(
                        properties.getProperty(TableCoreDefinition.URL),
                        properties.getProperty(TableCoreDefinition.USERNAME),
                        properties.getProperty(TableCoreDefinition.PASSWORD));
                connection.setAutoCommit(Boolean.valueOf(properties.getProperty(TableCoreDefinition.AUTO_COMMIT, "false")));
                LOGGER.info("Connection acquired");

            } catch (SQLException e) {
                throw new BudgetConnectionException("Cannot connect to DB ", e);
            }
        }
        return connection;
    }

    private static Properties readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DatabaseUtils.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void closeConnection() throws BudgetConnectionException {
        if (connection != null) {
            try {

                connection.close();
            }catch (SQLException e){
                throw new BudgetConnectionException("Cannot close connection with DB ",e);
            }
        }
    }

}
