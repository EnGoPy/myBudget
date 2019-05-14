import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Locale;

public class Datasource extends Data {

    private Connection conn;

    /// GENERAL METHODS

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("Connected");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public void printTables() {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getTables(null, null, "%", null);
            System.out.println("Im in try");
            while (result.next()) {
                System.out.println(result.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("ERROR; PRINT TABLES : " + e.getMessage());
        }
    }

    public String dateFiller(String date) {

        if (date.length() <= 5) {
            if (date.length() <= 1) {
                date = "0" + date;
            }
            if (date.length() <= 2) {
                date = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + date;
            }
            date = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + "-" + date;
            System.out.println(date);
        }
        if (dateValidation(date)) {
            return date;
        } else {
            return null;
        }
    }

    private boolean dateValidation(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            LocalDate ld = LocalDate.parse(date, f);
            //  System.out.println("Tested and passed date : "+ ld);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format. Needed yyyy-MM-dd.");
            System.out.println("Or not existing date.");
            return false;
        }
    }

    // CATEGORY TABLES

    public boolean addOutCategory(String tableName, String name) {
        // CHECKING IF NAME IS UNIQUE
        if (findOutCategory(tableName, name)) {
            System.out.println("Category " + name + " already exists in " + tableName + ".");
            printCategories(tableName);
            return false;
        }
        try (Statement statement = conn.createStatement()) {
            System.out.println("Query : " + ADD_CATEGORY_1 + tableName + ADD_CATEGORY_2 + name + "')");
            statement.execute(ADD_CATEGORY_1 + tableName + ADD_CATEGORY_2 + name + "')");
            //System.out.println("Category "+name+" sucessfully added to OUTCOMES category.");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR while adding category to " + tableName + ": " + e.getMessage());
            return false;
        }
    }

    private boolean findOutCategory(String tableName, String name) {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + COLUMN_CATOUTCOMES_NAME + "='" + name + "' COLLATE NOCASE")) {
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Searching in " + tableName + " category failed: " + e.getMessage());
            return false;
        }
    }

    private boolean findOutCatByNumber(String table, int catNumber) {
        try (Statement statement = conn.createStatement();
             ResultSet resultset = statement.executeQuery("SELECT * FROM " + table + " WHERE _id=" + Integer.toString(catNumber))) {
            if (resultset.next()) {
                System.out.println("znalazlem ");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error in findOutCatByNumber: " + e.getMessage());
            return false;
        }
    }

    public void printCategories(String table) {
        try (Statement statement = conn.createStatement();
             ResultSet resultset = statement.executeQuery("SELECT * FROM " + table + " ORDER BY _id")) {
            if (resultset.isBeforeFirst()) {
                while (resultset.next()) {
                    System.out.println(resultset.getInt(COLUMN_CATOUTCOMES_ID) + "\t" + resultset.getString(COLUMN_CATOUTCOMES_NAME));
                }
            } else {
                System.out.println("There is nothing inside.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR while printing categories: " + e.getMessage());
        }
    }


    // EXPENSE TABLES
    public void addExpense(String date, int category, float amount, String name) {
        String insertExecution = ADD_EXPENSE_SHORT_1 + Datasource.TABLE_OUTCOMES +
                "(" + Datasource.COLUMN_OUTCOMES_DATE + "," +
                Datasource.COLUMN_OUTCOMES_CATOUT + "," +
                Datasource.COLUMN_OUTCOMES_AMOUNT + "," +
                Datasource.COLUMN_OUTCOMES_COMMENT + ") " +
                "VALUES('" + date + "'," + category + "," + amount + ",'" + name + "')";
        System.out.println(insertExecution);
        if (findOutCatByNumber(Datasource.TABLE_CATOUTCOMES, category)) {

            try {
                Statement statement = conn.createStatement();
                statement.execute(insertExecution);
            } catch (SQLException e) {
                System.out.println("Error while add expense: " + e.getMessage());
            }
        } else {
            System.out.println("Cannot execute query. Outcome category wasn't found. Choose " + category + ", available:");
            printCategories(Datasource.TABLE_CATOUTCOMES);
        }
    }

    public void addExpense(String date, int category, float amount, String name, String startDate, String endDate) {
        if (findOutCatByNumber(Datasource.TABLE_CATOUTCOMES, category)) {
            String insertExecution = ADD_EXPENSE_SHORT_1 + Datasource.TABLE_OUTCOMES +
                    "(" + Datasource.COLUMN_OUTCOMES_DATE + "," +
                    Datasource.COLUMN_OUTCOMES_CATOUT + "," +
                    Datasource.COLUMN_OUTCOMES_AMOUNT + "," +
                    Datasource.COLUMN_OUTCOMES_COMMENT + "," +
                    Datasource.COLUMN_OUTCOMES_STARTDATE + "," +
                    Datasource.COLUMN_OUTCOMES_ENDDATE + ") " +
                    "VALUES('" + date + "'," + category + "," + amount + ",'" + name + "'," + startDate + "," + endDate + ")";
            try {
                Statement statement = conn.createStatement();
                statement.execute(insertExecution);
            } catch (SQLException e) {
                System.out.println("Error while add expense: " + e.getMessage());
            }
        } else {
            System.out.println("Cannot execute query. Outcome category wasn't found. Choose " + category + ", available:");
            printCategories(Datasource.TABLE_CATOUTCOMES);
        }
    }

    public void printExpenseContinous() {
        try (Statement statement = conn.createStatement();
             ResultSet resultset = statement.executeQuery("SELECT * FROM " + TABLE_OUTCOMES + " WHERE " + COLUMN_OUTCOMES_CONT + "=1 ORDER BY _id")) {
            if (resultset.isBeforeFirst()) {
                System.out.println(resultset.getInt("KEY\tDATE\tCATEGORY\tAMOUNT\tNAME\tSTART DATE\tEND DATE"));
                while (resultset.next()) {
                    System.out.println(resultset.getInt(COLUMN_OUTCOMES_ID) + "\t" +
                            COLUMN_OUTCOMES_DATE + "\t" +
                            COLUMN_OUTCOMES_CATOUT + "\t" +
                            COLUMN_OUTCOMES_AMOUNT + "\t" +
                            COLUMN_OUTCOMES_COMMENT + "\t" +
                            COLUMN_OUTCOMES_STARTDATE + "\t" +
                            COLUMN_OUTCOMES_ENDDATE);
                }
            } else {
                System.out.println("There is nothing inside.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR while printing continous outcomes: " + e.getMessage());
        }
    }

    public void printIncomesContinous() {
        try (Statement statement = conn.createStatement();
             ResultSet resultset = statement.executeQuery("SELECT * FROM " + TABLE_INCOMES + " WHERE " + COLUMN_INCOMES_CONT + "=1 ORDER BY _id")) {
            if (resultset.isBeforeFirst()) {
                System.out.println(resultset.getInt("KEY\tDATE\tCATEGORY\tAMOUNT\tNAME\tSTART DATE\tEND DATE"));
                while (resultset.next()) {
                    System.out.println(resultset.getInt(COLUMN_INCOMES_ID) + "\t" +
                            COLUMN_INCOMES_DATE + "\t" +
                            COLUMN_INCOMES_CATOUT + "\t" +
                            COLUMN_INCOMES_AMOUNT + "\t" +
                            COLUMN_INCOMES_COMMENT + "\t" +
                            COLUMN_INCOMES_STARTDATE + "\t" +
                            COLUMN_INCOMES_ENDDATE);
                }
            } else {
                System.out.println("There is nothing inside.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR while continous incomes : " + e.getMessage());
        }
    }


}
