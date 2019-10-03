package pl.wojtasik.myBudget.Mappers;

import pl.wojtasik.myBudget.dao.operationData.entity.Operation;
import pl.wojtasik.myBudget.database.TableCoreDefinition;
import pl.wojtasik.myBudget.exception.OperationDatabaseAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class DBvsOperationMapper {

    public static Optional<Operation> createOperationWithId(int id, Operation operation){
        Operation operation1 = new Operation(
                id,
                operation.getDate(),
                operation.getCategory(),
                operation.getPrice(),
                operation.getDescription(),
                operation.getStartDate(),
                operation.getEndDate());
        return Optional.of(operation1);
    }

    public static Optional<Operation> createOperationFromDBResultSet(ResultSet resultSet) throws OperationDatabaseAccessException {

        try {
            int id = resultSet.getInt(1);
            Date transactionDate =  DateMapper.getDateFromString(resultSet.getString(TableCoreDefinition.COLUMN_OPERATION_DATE));
            int cat = resultSet.getInt(TableCoreDefinition.COLUMN_OPERATION_CATOUT);
            double price = resultSet.getDouble(TableCoreDefinition.COLUMN_OPERATION_AMOUNT);
            String comment =resultSet.getString(TableCoreDefinition.COLUMN_OPERATION_COMMENT);
            Date startDate =  DateMapper.getDateFromString(resultSet.getString(TableCoreDefinition.COLUMN_OPERATION_STARTDATE));
            Date endDate =  DateMapper.getDateFromString(resultSet.getString(TableCoreDefinition.COLUMN_OPERATION_ENDDATE));
            Operation receivedOperation = new Operation(id, transactionDate, cat, price, comment, startDate, endDate);
            return Optional.of(receivedOperation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
