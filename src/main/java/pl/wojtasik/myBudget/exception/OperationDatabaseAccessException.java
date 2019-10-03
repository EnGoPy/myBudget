package pl.wojtasik.myBudget.exception;

public class OperationDatabaseAccessException extends BudgetException{
    public OperationDatabaseAccessException(String message) {
        super(message);
    }

    public OperationDatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
