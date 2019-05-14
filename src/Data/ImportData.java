/*
This class is made to import data from existing excel file to the database.
An existing excel file was been producing for more than one year, so many real and important datas has been stored inside.
This class allows to connect to the .xlxs file, import important cells and put in correct order to the database

 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImportData extends Data{

    private static Datasource datasource = new Datasource();

    public void dataImport() {
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        try {
            File excelFile = new File("ImportData.xlsx");
            FileInputStream fis = new FileInputStream(excelFile);
        }catch (FileNotFoundException e){
            System.out.println("Error while connecting to EXCEL file: "+e.getMessage());
        }

//        XSSFWorkbook



        datasource.close();
    }



}
