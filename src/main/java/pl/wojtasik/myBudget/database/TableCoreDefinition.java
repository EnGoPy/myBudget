package pl.wojtasik.myBudget.database;

public class TableCoreDefinition {

    public static final String URL ="datasource.url"; //"jdbc:h2:~/test";
    public static final String USERNAME = "datasource.user"; //"sa";
    public static final String PASSWORD = "datasource.password";//"";
    public static final String AUTO_COMMIT = "datasource.autocommit";

    public static final String TABLE_CATINCOMES = "catIncomes";
    public static final String COLUMN_CATEGORY_ID = "_id";
    public static final String COLUMN_CATEGORY_NAME = "name";

    public static final String TABLE_CATOUTCOMES = "catOutcomes";
    public static final String COLUMN_CATOUTCOMES_ID = "_id";
    public static final String COLUMN_CATOUTCOMES_NAME = "name";

    public static final String TABLE_OUTCOMES = "outcomes";
    public static final String COLUMN_OPERATION_ID = "_id";
    public static final String COLUMN_OPERATION_DATE = "date";
    public static final String COLUMN_OPERATION_CATOUT = "catOut";
    public static final String COLUMN_OPERATION_AMOUNT = "amount";
    public static final String COLUMN_OPERATION_COMMENT = "comment";
    public static final String COLUMN_OPERATION_STARTDATE = "startDate";
    public static final String COLUMN_OPERATION_ENDDATE = "endDate";

    public static final String TABLE_INCOMES = "incomes";

    public final static String DATE_FORMAT = "yyyy-MM-dd";

    //TABLES CREATION
    public final static String CREATE_TABLE_OUTCOMES = "CREATE TABLE IF NOT EXISTS"+TABLE_OUTCOMES+"("+
            COLUMN_OPERATION_ID +" BIGINT NOT NULL AUTO_INCREMENT, "+
            COLUMN_OPERATION_DATE+" TEXT, "+
            COLUMN_OPERATION_CATOUT +" INTEGER, "+
            COLUMN_OPERATION_AMOUNT +" DECIMAL(2), "+
            COLUMN_OPERATION_COMMENT +" TEXT, "+
            COLUMN_OPERATION_STARTDATE +" TEXT, "+
            COLUMN_OPERATION_ENDDATE +" TEXT)"
                                                        ;
    public final static String CREATE_TABLE_INCOMES = "CREATE TABLE IF NOT EXISTS"+TABLE_INCOMES+"("+
            COLUMN_OPERATION_ID+" BIGINT NOT NULL AUTO_INCREMENT, "+
            COLUMN_OPERATION_DATE+" TEXT, "+
            COLUMN_OPERATION_CATOUT+" INTEGER, "+
            COLUMN_OPERATION_AMOUNT+" DECIMAL(2), "+
            COLUMN_OPERATION_COMMENT+" TEXT, "+
            COLUMN_OPERATION_STARTDATE+" TEXT, "+
            COLUMN_OPERATION_ENDDATE+" TEXT)";

    public final static String CREATE_TABLE_OUT_CATEGORIES = "CREATE TABLE IF NOT EXISTS "+TABLE_CATOUTCOMES+"("+
            COLUMN_CATEGORY_ID+" BIGINT NOT NULL AUTO_INCREMENT, "+
            COLUMN_CATEGORY_NAME+" TEXT)";

    public final static String CREATE_TABLE_INC_CATEGORIES = "CREATE TABLE IF NOT EXISTS "+TABLE_CATINCOMES+"("+
            COLUMN_CATEGORY_ID +" BIGINT NOT NULL AUTO_INCREMENT, "+
            COLUMN_CATEGORY_NAME +" TEXT)";

    // TABLES DROPPING

    public static final String DROP_TABLE_OUTCOMES="DROP TABLE "+TABLE_OUTCOMES;
    public static final String DROP_TABLE_INCOMES="DROP TABLE "+TABLE_INCOMES;
    public static final String DROP_TABLE_OUT_CATEGORIES="DROP TABLE "+TABLE_CATOUTCOMES;
    public static final String DROP_TABLE_IN_CATEGORIES="DROP TABLE "+TABLE_CATINCOMES;






}
