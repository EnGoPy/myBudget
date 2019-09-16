public class Data {
    public static final String IMPORT_XLXS = "ImportData.xlsx";
    public static final String DB_NAME = "myBudget.db";
    public static String CONNECTION_STRING = "jdbc:sqlite:/home/engopy/programming/myprojects/MyBudget/src/Data/"+DB_NAME;

    public static final String TABLE_CATINCOMES = "catIncomes";
    public static final String COLUMN_CATINCOMES_ID = "_id";
    public static final String COLUMN_CATINCOMES_NAME = "name";

    public static final String TABLE_CATOUTCOMES = "catOutcomes";
    public static final String COLUMN_CATOUTCOMES_ID = "_id";
    public static final String COLUMN_CATOUTCOMES_NAME = "name";

    public static final String TABLE_OUTCOMES = "outcomes";
    public static final String COLUMN_OUTCOMES_ID = "_id";
    public static final String COLUMN_OUTCOMES_DATE = "date";
    public static final String COLUMN_OUTCOMES_CATOUT = "catOut";
    public static final String COLUMN_OUTCOMES_AMOUNT = "amount";
    public static final String COLUMN_OUTCOMES_COMMENT = "comment";
    public static final String COLUMN_OUTCOMES_CONT = "cont";
    public static final String COLUMN_OUTCOMES_STARTDATE = "startDate";
    public static final String COLUMN_OUTCOMES_ENDDATE = "endDate";

    public static final String TABLE_INCOMES = "incomes";
    public static final String COLUMN_INCOMES_ID = "_id";
    public static final String COLUMN_INCOMES_DATE = "date";
    public static final String COLUMN_INCOMES_CATOUT = "catInc";
    public static final String COLUMN_INCOMES_AMOUNT = "amount";
    public static final String COLUMN_INCOMES_COMMENT = "comment";
    public static final String COLUMN_INCOMES_CONT = "cont";
    public static final String COLUMN_INCOMES_STARTDATE = "startDate";
    public static final String COLUMN_INCOMES_ENDDATE = "endDate";

    public static final int ORDER_BY_NONE=1;
    public static final int ORDER_BY_ASC=2;
    public static final int ORDER_BY_DESC=3;

//    final static String DATE_FORMAT = "dd-MM-yyyy";
//    final static java.text.SimpleDateFormat DATE_FORMAT = new java.text.SimpleDateFormat("yyyy-MM-dd");
    public final static String DATE_FORMAT = "yyyy-MM-dd";


    public static final String ADD_CATEGORY_1 = "INSERT INTO ";
    public static final String ADD_CATEGORY_2 = "(name) VALUES('";

    public static final String ADD_EXPENSE_SHORT_1 = "INSERT INTO ";
    public static final String ADD_EXPENSE_SHORT_2 = "INSERT INTO ";


    // VIEWS
    public static final String EXPENSE_VIEW = "expense_view";
    public static final String CREATE_EXPENSE_VIEW = "CREATE IF NOT EXISTS "+
            EXPENSE_VIEW +" AS SELECT "+TABLE_OUTCOMES+"."+COLUMN_OUTCOMES_DATE+","+
            TABLE_OUTCOMES+"."+COLUMN_OUTCOMES_AMOUNT+","+
            TABLE_CATOUTCOMES+"."+COLUMN_CATOUTCOMES_NAME+" FROM "+TABLE_OUTCOMES+
            " INNER JOIN "+TABLE_CATOUTCOMES+" ON "+
            TABLE_OUTCOMES+"."+COLUMN_OUTCOMES_CATOUT+" = "+TABLE_CATOUTCOMES+"."+COLUMN_CATINCOMES_ID+
            " ORDER BY "+TABLE_OUTCOMES+"."+COLUMN_OUTCOMES_DATE;



}
