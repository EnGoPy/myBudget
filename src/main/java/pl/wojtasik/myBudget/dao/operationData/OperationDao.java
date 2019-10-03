package pl.wojtasik.myBudget.dao.operationData;

import pl.wojtasik.myBudget.Mappers.DBvsOperationMapper;
import pl.wojtasik.myBudget.Mappers.DateMapper;
import pl.wojtasik.myBudget.dao.operationData.entity.Operation;
import pl.wojtasik.myBudget.database.DatabaseUtils;
import pl.wojtasik.myBudget.database.TableQueries;
import pl.wojtasik.myBudget.exception.BudgetConnectionException;
import pl.wojtasik.myBudget.exception.BudgetException;
import pl.wojtasik.myBudget.exception.OperationDatabaseAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperationDao {

    private static Connection connection;

    public Optional<Operation> create(Operation operation, String tableType) throws BudgetException {
        try {
            connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(tableType);
            statement.setString(1, DateMapper.getStringFromDate(operation.getDate()));
            statement.setInt(2, operation.getCategory());
            statement.setDouble(3, operation.getPrice());
            statement.setString(4, operation.getDescription());
            statement.setString(5, DateMapper.getStringFromDate(operation.getStartDate()));
            statement.setString(6, DateMapper.getStringFromDate(operation.getEndDate()));
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                Optional<Operation> returnedValue;
                returnedValue = DBvsOperationMapper.createOperationWithId(id, operation);
                return returnedValue;
            }
        } catch (BudgetConnectionException e) {
            throw new BudgetConnectionException("Cannot connect to DB");
        } catch (SQLException e) {
            throw new OperationDatabaseAccessException("Failed to add outcome to DB");
        }
        return Optional.empty();
    }

    public Optional<Operation> read(int id, String tableType) throws BudgetException {

        try {
            PreparedStatement statement = connection.prepareStatement(tableType);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Optional<Operation> returnedValue;
                returnedValue = DBvsOperationMapper.createOperationFromDBResultSet(resultSet);
                return returnedValue;
            }
        } catch (SQLException e) {
            throw new OperationDatabaseAccessException("Failed to find outcome in DB");
        }
        return Optional.empty();
    }

    public Optional<Operation> update(int id, Operation updatedOperation, String tableType) throws OperationDatabaseAccessException {
        try {
            PreparedStatement statement = connection.prepareStatement(TableQueries.UPDATE_OUTCOME);
            statement.setString(1, DateMapper.getStringFromDate(updatedOperation.getDate()));
            statement.setInt(2, updatedOperation.getCategory());
            statement.setDouble(3, updatedOperation.getPrice());
            statement.setString(4, updatedOperation.getDescription());
            statement.setString(5, DateMapper.getStringFromDate(updatedOperation.getStartDate()));
            statement.setString(6, DateMapper.getStringFromDate(updatedOperation.getEndDate()));
            statement.setInt(7, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Optional<Operation> returnedValue;
                returnedValue = DBvsOperationMapper.createOperationFromDBResultSet(resultSet);
                return returnedValue;
            }
        } catch (SQLException e) {
            throw new OperationDatabaseAccessException("Couldn't update outcome.");
        }
        return Optional.empty();
    }

    public void delete(int id, String tableType) throws OperationDatabaseAccessException {
        try {
            PreparedStatement statement = connection.prepareStatement(tableType);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new OperationDatabaseAccessException("Couldn't delete outcome.");
        }
    }
    public List<Operation> list(OperationFiltering filter, String tableType) throws OperationDatabaseAccessException {
        List<Operation> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(tableType);
            statement.setString(1, DateMapper.getStringFromDate(filter.getStartDate()));
            statement.setString(2, DateMapper.getStringFromDate(filter.getEndDate()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(DBvsOperationMapper.createOperationFromDBResultSet(resultSet).get());
            }
        } catch (SQLException e) {
            throw new OperationDatabaseAccessException("Cannot delete outcome.");
        }
        return list;
    }

}
