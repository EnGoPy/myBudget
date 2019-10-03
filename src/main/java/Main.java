
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Datasource datasource = new Datasource();

    public static void main(String[] args) {


        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }
        // datasource.printTables();

        datasource.dateFiller("25");
        datasource.dateFiller("13-19");
        datasource.dateFiller("23=3");

        boolean mainQuit = false;
        int choose;
        while (!mainQuit) {
            keepCalm();
            printMenu();
//            System.out.println("");
            System.out.print("Please enter your choose...");
            choose = inputValidation();
            //   clrscrn();
            switch (choose) {
                case 1:
                    break;
                case 2:
                    expenseNavigator();
                    break;
                case 3:
                    break;
                case 9:
                    printMenu();
                    break;
                case 0:
                    mainQuit = true;
                    System.out.println("Quitting program...");
                    System.out.println("GoodBye");
                    break;

            }

        }

        datasource.close();
    }


    ////////////////////////////////////////////////////////////////EXPENSE METHODS////////////////////////////////////////////////////////////////////
    private static void expenseNavigator() {
        int subchoose = 9;
        while (subchoose != 0) {
            printExpenseMenu();
            System.out.print("Please enter your choose...");
            subchoose = inputValidation();
            switch (subchoose) {
                case 1:
                    addExpense(false);
                    break;
                case 2:
                    addExpense(true);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    printOutcomes();
                    break;
                case 6:
                    addCategory(Datasource.TABLE_CATOUTCOMES);
                    break;
                case 9:
                    printExpenseMenu();
                    break;
                case 0:
                    subchoose = 0;
                    System.out.println("Quitting to Main menu");
                    break;
            }
        }
    }

    private static void printExpenseMenu() {
        System.out.println("====== EXPENSE MENU ======");
        System.out.println("1.  Add expense");
        System.out.println("2.  Add continous expense");
        System.out.println("3.  Edit continous expense (NOT WORKING)");
        System.out.println("4.  DELETE continous expense (NOT WORKING)");
        System.out.println("5.  Show expense categories");
        System.out.println("6.  Add expense category");
        System.out.println("9.  Print Expense Menu");  // The same value is thrown while input in Scanner is wrong (can't parse to int value). See inputValidation()
        System.out.println("0.  Back to Main Menu");
    }


    private static void printOutcomes() {
        datasource.printCategories(Datasource.TABLE_CATOUTCOMES);
    }

    private static void addExpense(boolean cont) {
        // Order of typing Date-category-amount-name
        // If you type as a date single number it will be assumed as a expense for current month
        // If you type a s a date two digits yyyy-MM-dd  it will be assumed as a expense for a current year in specific date
        System.out.println("Current outcomes categories:");
        datasource.printCategories(Datasource.TABLE_CATOUTCOMES);
        String date;
        int category;
        float amount;
        String name;
        System.out.println("DATE (yyyy-MM-dd) : ");
        date = dateCorrectInput();
        System.out.println("CATEGORY : ");
        category = sc.nextInt();
        System.out.println("AMOUNT : ");
        amount = sc.nextFloat();
        System.out.println("NAME : ");
        name = sc.next();
        name += sc.nextLine();
        if (cont) {
            String startDate;
            String endDate;
            System.out.println("START DATE (yyyy-MM-dd) : ");
            startDate = dateCorrectInput();
            System.out.println("END DATE (yyyy-MM-dd) : ");
            endDate = dateCorrectInput();
            System.out.println("Your input: DATE\tCATEGORY\tAMOUNT\tNAME\tSTART DATE\tEND DATE");
            System.out.println("Your input: " + date + "\t" + category + "\t" + amount + "\t" + name + "\t" + startDate + "\t" + endDate);
            datasource.addExpense(date, category, amount, name, startDate, endDate);
        } else {
            System.out.println("Your input: DATE\tCATEGORY\tAMOUNT\tNAME");
            System.out.println("Your input: " + date + "\t" + category + "\t" + amount + "\t" + name);
            datasource.addExpense(date, category, amount, name);
        }
    }

    private static String dateCorrectInput() {
        do {
            String testDate = sc.next();
            String alreadyTested = datasource.dateFiller(testDate);
            if (alreadyTested != null) {
                return alreadyTested;
            } else {
                System.out.println("Type again :");
            }
        } while (true);
    }


    private static void addCategory(String table) {
        System.out.println("Current outcomes categories:");
        datasource.printCategories(table);
        String newCategory;
        System.out.println("Enter name of new outcome category: ");
        newCategory = sc.next();

        if (datasource.addOutCategory(table, newCategory)) {
            System.out.println("Category " + newCategory + " successfully added.");
        } else {
            System.out.println("An error occured while adding " + newCategory + " to " + table + ".");
        }
    }

    private static void addOutcomeCat() {
        System.out.println("Current outcomes categories:");
        datasource.printCategories(Datasource.TABLE_CATOUTCOMES);
        String newCategory;
        System.out.println("Enter name of new outcome category: ");
        newCategory = sc.next();

        if (datasource.addOutCategory(Datasource.TABLE_CATOUTCOMES, newCategory)) {
            System.out.println("Category " + newCategory + " successfully added.");
        } else {
            System.out.println("An error occured while adding " + newCategory + " to " + Datasource.TABLE_CATOUTCOMES + ".");
        }
    }

    private static void addIncomeCat() {
        System.out.println("Current incomes categories:");
        datasource.printCategories(Datasource.TABLE_CATINCOMES);
        String newCategory;
        System.out.println("Enter name of new outcome category: ");
        newCategory = sc.next();

        if (datasource.addOutCategory(Datasource.TABLE_CATINCOMES, newCategory)) {
            System.out.println("Category " + newCategory + " successfully added.");
        } else {
            System.out.println("An error occured while adding " + newCategory + " to " + Datasource.TABLE_CATINCOMES + ".");
        }
    }

    ////////////////////////////////////////////////////////////////GENERAL METHODS////////////////////////////////////////////////////////////////////
    private static int inputValidation() {
        int chooseInt;
        String chooseString = sc.next();
        try {
            chooseInt = Integer.parseInt(chooseString);
            if (chooseInt >= 0 && chooseInt <= 9) {
                return chooseInt;
            } else {
                System.out.println("Your choose must be integer, between 0 and 10.");
                return 9;
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input: " + e.getMessage());
            return 9;
        }
    }

    private static void printMenu() {
        System.out.println("====== MAIN MENU ======");
        System.out.println("1.  ");
        System.out.println("2.  Expense Submenu");
        System.out.println("3.  Incomes Submenu");
        System.out.println("9.  Print Menu");  // The same value is thrown while input in Scanner is wrong (can't parse to int value). See inputValidation()
        System.out.println("0.  QUIT program");
    }

    public static void clrscrn() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void keepCalm() {
        try {
            for (int i = 0; i <= 2; i++) {
                System.out.print(" . ");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("An unexpected error: " + e.getMessage());
        }
    }

}
