package pl.wojtasik.myBudget.database;

import pl.wojtasik.myBudget.exception.BudgetConnectionException;

import java.sql.*;
import java.util.logging.Logger;


public class DatabaseSettings {

    private static final Logger LOGGER = Logger.getLogger(DatabaseSettings.class.getName());

    static void prepareTables(Connection connection) throws BudgetConnectionException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(TableCoreDefinition.CREATE_TABLE_OUTCOMES);
            connection.commit();
            statement.execute(TableCoreDefinition.CREATE_TABLE_OUT_CATEGORIES);
            connection.commit();
            statement.execute(TableCoreDefinition.CREATE_TABLE_INCOMES);
            connection.commit();
            statement.execute(TableCoreDefinition.CREATE_TABLE_OUT_CATEGORIES);
            connection.commit();
            LOGGER.info("Tables prepared.");
        } catch (SQLException e) {
            throw new BudgetConnectionException("Cannot prepare table", e);
        }
    }

    static void dropTables(Connection connection) throws BudgetConnectionException {
        try{
            Statement statement = connection.createStatement();
            statement.execute(TableCoreDefinition.DROP_TABLE_OUT_CATEGORIES);
            connection.commit();
            statement.execute(TableCoreDefinition.DROP_TABLE_IN_CATEGORIES);
            connection.commit();
            statement.execute(TableCoreDefinition.DROP_TABLE_INCOMES);
            connection.commit();
            statement.execute(TableCoreDefinition.DROP_TABLE_OUTCOMES);
            connection.commit();
        }catch (SQLException e){
            throw new BudgetConnectionException("Dropping tables failed", e);
        }
    }

    static void printTables(Connection connection) throws BudgetConnectionException {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet result = metaData.getTables(null, null, "%", null);
            while (result.next()) {
                System.out.println(result.getString(3));
            }
        } catch (SQLException e) {
            throw new BudgetConnectionException("Cannot print tables", e);
        }
    }

}
