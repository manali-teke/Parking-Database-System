package src;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static Connection connection = null;
    public static Statement statement = null;
    public static String user = null;
    public static String password = null;
    

    public static void main(String[] args) {

        try {
            getUser();
            connectToDatabase("jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user, user, password);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down...");
                close();
            }));
            Scanner sc = new Scanner(System.in);
            int option;

            do {
                try {
                    System.out.println("=============================================================================");
                    System.out.println("Choose the operation from the main menu by inputting the respective number:");
                    System.out.println("=============================================================================");
                    System.out.println("1. Information Processing");
                    System.out.println("2. Maintaining permits and vehicle information for each driver");
                    System.out.println("3. Generating and maintaining citations");
                    System.out.println("4. Reports");
                    System.out.println("5. Display Tables");
                    System.out.println("100. Exit");
                    System.out.println("=============================================================================");
                    System.out.print("Enter Your Choice: ");
                    
                    option = sc.nextInt();
                    switch (option) {
                        case 1:
                            informationProcessingMenu();
                            break;
                        case 2:
                            maintainingPermitsMenu();
                            break;
                        case 3:
                            citationsMenu();
                            break;
                        case 4:
                            reportsMenu();
                            break;
                        case 5:
                            DisplayTables.main(connection);
                            break;
                        case 100:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } catch (java.util.NoSuchElementException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    // Consume the invalid input to avoid an infinite loop
                    sc.nextLine();
                    option = 0;
                }
            } while (option != 100);
            sc.close();
            close();
        }  catch (Exception e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
            close();
        }
    }

    static void getUser() {
        Properties properties = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream("../db_keys");
            properties.load(input);
            user = properties.getProperty("username");
            password = properties.getProperty("password");
            System.out.println("User Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void connectToDatabase(String jdbcURL, String user, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, user, password);
        statement = connection.createStatement();
        System.out.println("Connected to Database");
    }

    static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Database Connection Terminated");
    }

    private static void informationProcessingMenu() {
        int option = 0;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("\nInformation Processing Menu:");
                System.out.println("Choose the operation from the menu by inputting the respective number:");
                System.out.println("1.Enter driver info");
                System.out.println("2.Update driver info");
                System.out.println("3.Delete driver info");
                System.out.println("4.Enter parking lot info");
                System.out.println("5.Update parking lot info");
                System.out.println("6.Delete parking lot info");
                System.out.println("7.Enter zone info");
                System.out.println("8.Update lot info");
                System.out.println("9.Delete lot info");
                System.out.println("10.Enter space info");
                System.out.println("11.Update space info");
                System.out.println("12.Delete space info");
                System.out.println("13.Enter permit info");
                System.out.println("14.Update permit info");
                System.out.println("15.Delete permit info");
                System.out.println("16.Assign zones to each parking lot");
                System.out.println("17.Assign a type to a given space.");
                System.out.println("18.Request citation appeal");
                System.out.println("19.Update citation payment");
                System.out.println("100. Return to main menu");
                option = sc.nextInt();
                sc.nextLine();
                infoProcessing ip = new infoProcessing();
                switch (option) {
                    
                    case 1:
                        ip.enterDriverInfo(statement);
                        // Implement code for entering driver information
                        break;
                    case 2:
                        // Update driver info
                        ip.updateDriverInfo(statement);
                        // Implement code for updating driver information
                        break;
                    case 3:
                        // Delete driver info
                        // Implement code for deleting driver information
                        ip.deleteDriverInfo(statement);
                        break;
                    case 4:
                        // Enter parking lot info
                        ip.enterParkingLotInfo(statement);
                        // Implement code for entering parking lot information
                        break;
                    case 5:
                        // Update parking lot info
                        // Implement code for updating parking lot information
                        ip.updateParkingLotInfo(statement);
                        break;
                    case 6:
                        // Delete parking lot info
                        // Implement code for deleting parking lot information
                        ip.deleteParkingLotInfo(statement);
                        break;
                    case 7:
                        // Enter zone info
                        // Implement code for entering zone information
                        ip.enterZoneInfo(statement);
                        break;
                    case 8:
                        // Update zone info
                        // Implement code for updating zone information
                        ip.updateZoneInfo(statement);
                        break;
                    case 9:
                        // Delete zone info
                        // Implement code for deleting zone information
                        ip.deleteZoneInfo(statement);
                        break;
                    case 10:
                        // Enter space info
                        // Implement code for entering space information
                        ip.enterSpaceInfo(statement);
                        break;
                    case 11:
                        // Update space info
                        // Implement code for updating space information
                        ip.updateSpaceInfo(statement);
                        break;
                    case 12:
                        // Delete space info
                        // Implement code for deleting space information
                        ip.deleteSpaceInfo(statement);
                        
                        break;
                    case 13:
                        // Enter permit info
                        // Implement code for entering permit information
                        ip.enterPermitInfo(statement);
                        break;
                    case 14:
                        // Update permit info
                        // Implement code for updating permit information
                        ip.updatePermitInfo();
                        break;
                    case 15:
                        // Delete permit info
                        // Implement code for deleting permit information
                        ip.deletePermitInfo(statement);
                        break;
                    case 16:
                        // Assign zones to each parking lot
                        // Implement code for assigning zones to parking lots
                        // TODO how is this different from insert?
                        ip.enterZoneInfo(statement);
                        break;
                    case 17:
                        // Assign a type to a given space
                        // Implement code for assigning space type
                        ip.assignTypeToSpace(statement);
                        break;
                    case 18:
                        // Request citation appeal
                        // Implement code for appealing a citation
                        ip.requestCitationAppeal(statement);
                        break;
                    case 19:
                        // Update citation payment
                        // Implement code for updating citation payment
                        ip.updateCitationPayment(statement);
                        break;
                    case 100:
                        System.out.println("Exiting...");
                        sc.close();
                        break; // Return to main menu
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input and discard it
            }
            sc.close();
        } while (option != 100);
    }

    private static void maintainingPermitsMenu() {
        int option = 0;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Choose the operation from the menu by inputting the respective number:");
                System.out.println("\nMaintaining Permits Menu:");
                System.out.println("1. Assign permits to drivers");
                System.out.println("2. Enter permit information");
                System.out.println("3. Update permit information");
                System.out.println("4. Add vehicle");
                System.out.println("5. Update vehicle ownership information");
                System.out.println("6. Remove vehicle");
                System.out.println("100. Return to main menu");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        // Logic to assign permits to drivers
                        break;
                    case 2:
                        // Logic to enter permit information
                        break;
                    case 3:
                        // Logic to update permit information
                        break;
                    case 4:
                        // Logic to add vehicle
                        break;
                    case 5:
                        // Logic to update vehicle ownership information
                        break;
                    case 6:
                        // Logic to remove vehicle
                        break;
                    case 100:
                        System.out.println("Exiting...");
                        sc.close();
                        break; // Return to main menu
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input and discard it
            }
            sc.close();
        } while (option != 100);
    }

    private static void citationsMenu() {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do {        
            try{
                System.out.println("=============================================================================");
                System.out.println("Generating and Maintaining Citations Menu:");
                System.out.println("=============================================================================");
                System.out.println(
                        "1.Detect parking violations by checking if a car has a valid permit in the lot,zone and space.");
                System.out.println("2.Generate a citation");
                System.out.println("3.Maintain a citation");
                System.out.println("4.Pay citation");
                System.out.println("5.Appeal citation");
                System.out.println("6.Return to main menu");
                System.out.println("=============================================================================");
                System.out.print("Enter Your Choice: ");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        try {
                            Citations.detectParkingViolations(connection);       
                        } catch (Exception e) {
                            System.out.println("Sorry. Try Again.");
                        }
                        break;
                    case 2:
                        try {
                            Citations.generateCitation(connection);       
                        } catch (Exception e) {
                            System.out.println("Sorry. Try Again.");
                        }
                        break;
                    case 3:
                        try {
                            Citations.maintainCitation(connection);       
                        } catch (Exception e) {
                            System.out.println("Sorry. Try Again.");
                        }         
                        break;
                    case 4:
                        try {
                            Citations.payCitation(connection);       
                        } catch (Exception e) {
                            System.out.println("Sorry. Try Again.");
                        }
                        break;
                    case 5:
                        try {
                            Citations.appealCitation(connection);       
                        } catch (Exception e) {
                            System.out.println("Sorry. Try Again.");
                        }
                        break;
                    case 6:
                        break;
                    case 100:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input and discard it
            }
        } while (option != 6);
        sc.close();
    }

    private static void reportsMenu() {
        int option = 0;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Choose the operation from the menu by inputting the respective number:");
                System.out.println("\nReports Menu:");
                System.out.println("1. Generate a report for citations");
                System.out.println(
                        "2. For each lot, generate a report for the total number of citations given in all zones in the lot for a given month");
                System.out.println(
                        "3. For each lot, generate a report for the total number of citations given in all zones in the lot for a given year");
                System.out.println("4. Return the list of zones for each lot as tuple pairs (lot, zone)");
                System.out.println("5. Return the number of cars that are currently in violation");
                System.out.println("6. Return the number of employees having permits for a given parking zone");
                System.out.println("7. Return permit information given an ID or phone number");
                System.out.println("8. Return an available space number given a space type in a given parking lot");
                System.out.println("100. Return to main menu");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        // Generate a report for citations
                        // Implement code to generate the citation report
                        break;
                    case 2:
                        // For each lot, generate a report for the total number of citations given in
                        // all zones in the lot for a given month
                        // Implement code for generating the monthly citation report for each lot
                        break;
                    case 3:
                        // For each lot, generate a report for the total number of citations given in
                        // all zones in the lot for a given year
                        // Implement code for generating the yearly citation report for each lot
                        break;
                    case 4:
                        // Return the list of zones for each lot as tuple pairs (lot, zone)
                        // Implement code to return the list of zones for each parking lot
                        break;
                    case 5:
                        // Return the number of cars that are currently in violation
                        // Implement code to count the number of cars in violation
                        break;
                    case 6:
                        // Return the number of employees having permits for a given parking zone
                        // Implement code to count the employees with permits in a specific zone
                        break;
                    case 7:
                        // Return permit information given an ID or phone number
                        // Implement code to retrieve permit information by ID or phone number
                        break;
                    case 8:
                        // Return an available space number given a space type in a given parking lot
                        // Implement code to find an available space of a specific type in a parking lot
                        break;
                    case 100:
                        System.out.println("Exiting...");
                        sc.close();
                        break; // Return to main menu
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input and discard it
            }
            sc.close();
        } while (option != 100);
    }
}