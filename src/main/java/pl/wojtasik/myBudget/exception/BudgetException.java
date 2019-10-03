package pl.wojtasik.myBudget.exception;

public class BudgetException extends Exception {
    public BudgetException(String message) {
        super(message);
    }

    public BudgetException(String message, Throwable cause) {
        super(message, cause);
    }
}
