
import java.util.List;
import java.util.Scanner;


public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Datasource datasource = new Datasource();
    public static void main(String[] args) {



        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        datasource.printTables();

        boolean quit = false;
        int choose;
        while(!quit){
              printMenu();
            System.out.println("");
            System.out.print("Please enter your choose...");
            choose=inputValidation();
            switch (choose){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    printOutcomes();
                    break;
                case 5:
                    addOutcomeCat();
                    break;
                case 6:
                    addIncomeCat();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    printMenu();
                    break;
                case 0:
                    quit=true;
                    System.out.println("Quitting program...");
                    System.out.println("GoodBye");
                    break;

            }
        }

        datasource.close();
    }
    private static int inputValidation(){
        int chooseInt;
        String chooseString = sc.next();
        try {
            chooseInt = Integer.parseInt(chooseString);
            if(chooseInt>=0 && chooseInt<=9){
                return chooseInt;
            }
            else {
                System.out.println("Your choose must be integer, between 0 and 10.");
                return 9;
            }
        }catch(NumberFormatException e){
            System.out.println("Wrong input: "+e.getMessage());
            return 9;
        }
    }

    private static void printMenu(){
        System.out.println("====== MAIN MENU ======");
        System.out.println("1.  ");
        System.out.println("2.  ");
        System.out.println("3.  ");
        System.out.println("4.  Print outcomes categories");
        System.out.println("5.  Add outcome category");
        System.out.println("6.  Add income category");
        System.out.println("9.  Print Menu");  // The same value is thrown while input in Scanner is wrong (can't parse to int value). See inputValidation()
        System.out.println("0.  QUIT program");
    }

    private static void printOutcomes(){
        datasource.printCategories(Datasource.TABLE_CATOUTCOMES);
    }

    private static void addOutcomeCat(){
        String newCategory;
        System.out.println("Enter name of new outcome category: ");
        newCategory = sc.next();

        if(datasource.addOutCategory(Datasource.TABLE_CATOUTCOMES, newCategory)){
            System.out.println("Category "+newCategory+" successfully added.");
        }else{
            System.out.println("An error occured while adding "+newCategory+" to "+Datasource.TABLE_CATOUTCOMES+".");
        }
    }
    private static void addIncomeCat(){
        String newCategory;
        System.out.println("Enter name of new outcome category: ");
        newCategory = sc.next();

        if(datasource.addOutCategory(Datasource.TABLE_CATINCOMES, newCategory)){
            System.out.println("Category "+newCategory+" successfully added.");
        }else{
            System.out.println("An error occured while adding "+newCategory+" to "+Datasource.TABLE_CATINCOMES+".");
        }
    }

}
