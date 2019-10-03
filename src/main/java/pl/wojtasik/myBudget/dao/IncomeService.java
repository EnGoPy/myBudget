package pl.wojtasik.myBudget.dao;


import pl.wojtasik.myBudget.dao.operationData.OperationDao;
import pl.wojtasik.myBudget.dao.operationData.OperationFiltering;
import pl.wojtasik.myBudget.dao.operationData.entity.Operation;
import pl.wojtasik.myBudget.database.TableQueries;
import pl.wojtasik.myBudget.exception.BudgetException;
import pl.wojtasik.myBudget.exception.OperationDatabaseAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class IncomeService {
    private static final Logger LOGGER = Logger.getLogger(OutcomeService.class.getName());
    private OperationDao operationDao = new OperationDao();

    public Optional<Operation> create(Operation operation) throws BudgetException {
        try {
            return operationDao.create(operation, TableQueries.INSERT_INCOMES);
        } catch (BudgetException e) {
            LOGGER.info("Failed to create income");
        }
        return Optional.empty();
    }

    public Optional<Operation> read(int id) throws BudgetException {
        try {
            return operationDao.read(id, TableQueries.INSERT_INCOMES);
        } catch (BudgetException e) {
            LOGGER.info("Failed to read income with id=" + id);
        }
        return Optional.empty();
    }

    public Optional<Operation> update(int id, Operation updatedOperation) {
        try {
            return operationDao.update(id, updatedOperation, TableQueries.INSERT_INCOMES);
        } catch (OperationDatabaseAccessException e) {
            LOGGER.info("Failed to update income.");
        }
        return Optional.empty();
    }

    public void delete(int id) {
        try {
            operationDao.delete(id, TableQueries.INSERT_INCOMES);
        } catch (OperationDatabaseAccessException e) {
            LOGGER.info("Failed to delete income with id="+id);
        }
    }

    public List<Operation> list(OperationFiltering filter) throws OperationDatabaseAccessException {
        List<Operation> list = new ArrayList<>();
        try {
            return operationDao.list(filter, TableQueries.INSERT_INCOMES);
        } catch (OperationDatabaseAccessException e) {
            LOGGER.info("Failed to list income");
        }
        return list;
    }

}
