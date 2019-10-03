package pl.wojtasik.myBudget.database;

import static pl.wojtasik.myBudget.database.TableCoreDefinition.*;

public class TableQueries {

    // TABLES INSERTION
    public final static String INSERT_OUTCOMES ="INSERT INTO "+TABLE_OUTCOMES+"("+
            COLUMN_OPERATION_DATE+" , "+
            COLUMN_OPERATION_CATOUT +" , "+
            COLUMN_OPERATION_AMOUNT +" , "+
            COLUMN_OPERATION_COMMENT +" , "+
            COLUMN_OPERATION_STARTDATE +" , "+
            COLUMN_OPERATION_ENDDATE +" ) VALUES (?,?,?,?,?,?)";
    public final static String INSERT_INCOMES ="INSERT INTO "+TABLE_INCOMES+"("+
//            COLUMN_INCOMES_ID+" , "+
            COLUMN_OPERATION_DATE+" , "+
            COLUMN_OPERATION_CATOUT+" , "+
            COLUMN_OPERATION_AMOUNT+" , "+
            COLUMN_OPERATION_COMMENT+" , "+
            COLUMN_OPERATION_STARTDATE+" , "+
            COLUMN_OPERATION_ENDDATE+" ) VALUES (?,?,?,?,?,?)";
    public final static String INSERT_CATOUTCOMES ="INSERT INTO "+TABLE_CATOUTCOMES+"("+
            COLUMN_CATOUTCOMES_NAME+") VALUES (?)";
    public final static String INSERT_CATINCOMES ="INSERT INTO "+TABLE_CATINCOMES+"("+
            COLUMN_CATEGORY_NAME +") VALUES (?)";

    public final static String FIND_OUTCOME = "SELECT FROM "+TABLE_OUTCOMES+
            " WHERE "+ COLUMN_OPERATION_ID +" = ?";
    public final static String UPDATE_OUTCOME = "UPDATE "+TABLE_OUTCOMES+
            " SET "+COLUMN_OPERATION_DATE+"=?, "+
            " SET "+ COLUMN_OPERATION_CATOUT +"=?, "+
            " SET "+ COLUMN_OPERATION_AMOUNT +"=?, "+
            " SET "+ COLUMN_OPERATION_COMMENT +"=?, "+
            " SET "+ COLUMN_OPERATION_STARTDATE +"=?, "+
            " SET "+ COLUMN_OPERATION_ENDDATE +"=? "+
            " WHERE "+ COLUMN_OPERATION_ID +"=?";
    public final static String DELETE_OUTCOME = "DELETE FROM "+TABLE_OUTCOMES+
            " WHERE "+ COLUMN_OPERATION_ID +"=?";
    public final static String LIST_OUTCOMES_BETWEEN_DATES="SELECT * FROM "+TABLE_OUTCOMES+
            " WHERE ? > = "+COLUMN_OPERATION_DATE+
            " AND ? < = "+COLUMN_OPERATION_DATE+
            " ORDER BY "+COLUMN_OPERATION_DATE;


}
