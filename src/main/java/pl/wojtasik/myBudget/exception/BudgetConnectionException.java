package pl.wojtasik.myBudget.exception;

public class BudgetConnectionException extends BudgetException {

    public BudgetConnectionException(String message) {
        super(message);
    }
    public BudgetConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
