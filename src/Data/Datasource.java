import java.sql.*;

public class Datasource {
    public static final String DB_NAME = "myBudget.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/engopy/programming/myprojects/MyBudget/src/Data/"+DB_NAME;

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
    public static final String COLUMN_ICOMES_AMOUNT = "amount";
    public static final String COLUMN_ICOMES_COMMENT = "comment";
    public static final String COLUMN_ICOMES_CONT = "cont";
    public static final String COLUMN_ICOMES_STARTDATE = "startDate";
    public static final String COLUMN_ICOMES_ENDDATE = "endDate";

    public static final int ORDER_BY_NONE=1;
    public static final int ORDER_BY_ASC=2;
    public static final int ORDER_BY_DESC=3;

    public static final String ADD_CATEGORY_1 = "INSERT INTO ";
    public static final String ADD_CATEGORY_2 = "(name) VALUES('";



    private Connection conn;

    public boolean open() {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("Connected");
            return true;
        } catch(SQLException e){
            System.out.println("Couldn't connect to database: "+e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: "+ e.getMessage());
        }
    }

    public void printTables(){
        try{
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getTables(null, null, "%", null);
            System.out.println("Im in try");
            while (result.next()){
                System.out.println(result.getString(3));
            }
        }catch (SQLException e){
            System.out.println("ERROR; PRINT TABLES : "+e.getMessage());
        }
    }


                                                                        // INCOMES CATEGORY

    public boolean addOutCategory(String tableName, String name){
        // CHECKING IF NAME IS UNIQUE
        if(findOutCategory(tableName, name)){
            System.out.println("Category "+name+" already exists in "+tableName+".");
            printCategories(tableName);
            return false;
        }
        try(Statement statement = conn.createStatement())
        {
            System.out.println("Query : "+ADD_CATEGORY_1+tableName+ADD_CATEGORY_2+name+"')");
            statement.execute(ADD_CATEGORY_1+tableName+ADD_CATEGORY_2+name+"')");
            //System.out.println("Category "+name+" sucessfully added to OUTCOMES category.");
            return true;
        } catch(SQLException e){
            System.out.println("ERROR while adding category to "+tableName+": "+e.getMessage());
            return false;
        }
    }

    private boolean findOutCategory(String tableName, String name) {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + tableName + " WHERE "+COLUMN_CATOUTCOMES_NAME+"='"+ name + "' COLLATE NOCASE")) {
            if (results.next()){
                return true;
            }
            return false;
        } catch (SQLException e){
            System.out.println("Searching in "+tableName+" category failed: "+e.getMessage());
            return false;
        }
    }



                                                                        // GENERAL CATEGORIES
    public void printCategories(String table){
        try(Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM "+table+" ORDER BY _id")){
            if(resultset.isBeforeFirst()) {
                while (resultset.next()) {
                    System.out.println(resultset.getInt(COLUMN_CATOUTCOMES_ID) + "\t" + resultset.getString(COLUMN_CATOUTCOMES_NAME));
                }
            }else{
                System.out.println("There is nothing inside.");
            }
        }catch (SQLException e){
            System.out.println("ERROR while printing categories: "+e.getMessage());
        }
    }






}
