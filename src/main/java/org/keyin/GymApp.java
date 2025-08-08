package org.keyin;

import org.keyin.database.DatabaseConnection;
import org.keyin.customlogger.CustomLogger;
import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipDAO;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassDAO;
import org.keyin.workoutclasses.WorkoutClassService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException, IOException {
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService(new MembershipDAO(DatabaseConnection.getConnection()));
        WorkoutClassService workoutClassService = new WorkoutClassService(new WorkoutClassDAO(DatabaseConnection.getConnection()));

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                    break;
                case 2:
                    logInAsUser(scanner, userService, membershipService, workoutClassService);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) throws IOException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.loginForUser(username, password);
            if (user != null) {
                System.out.println("Login Successful! Welcome " + user.getUsername());
                switch (user.getRole().toLowerCase()) {
                    case "admin":
                        showAdminMenu(scanner, (Admin) user, membershipService);
                        break;
                    case "trainer":
                        showTrainerMenu(scanner, (Trainer) user, membershipService, workoutService);
                        break;
                    case "member":
                        showMemberMenu(scanner, (Member) user, membershipService, workoutService);
                        break;
                    default:

                        break;
                }
            } else {
                System.out.println("Login Failed! Invalid credentials.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            CustomLogger.logError(e.getMessage());
        }
    }

    // Placeholder for Member menu
    private static void showMemberMenu(Scanner scanner, Member user, MembershipService membershipService, WorkoutClassService workoutClassService) throws IOException {
        int choice;

        do {
            System.out.println("\n=== Gym Member Menu ===");
            System.out.println("1. View workout classes");
            System.out.println("2. View your total membership expenses");
            System.out.println("3. Purchase a gym membership");
            System.out.println("4. View all products for sale");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        workoutClassService.listAllWorkoutClasses();
                    } catch (Exception e) {
                        System.out.println("   Error: Could not display all Workout Classes.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        membershipService.displayUserTotalExpenses(user.getID());
                    } catch (Exception e) {
                        System.out.println("   Error: Could not display your Membership expenses.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        membershipService.purchaseMembership(new Membership());
                    } catch (Exception e) {
                        System.out.println("   Error: Could not purchase your membership.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        user.viewAllProducts();
                    } catch (Exception e) {
                        System.out.println("   Error: Could not display products for sale.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Exiting... Thank you, have a good day!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 5);
    }

    // Trainer menu
    private static void showTrainerMenu(Scanner scanner, Trainer user, MembershipService membershipService, WorkoutClassService workoutService) throws IOException {
        int choice;

        do {
            System.out.println("\n=== Gym Trainer Menu ===");
            System.out.println("1. Create workout class");
            System.out.println("2. Update workout class");
            System.out.println("3. Delete workout class");
            System.out.println("4. View your assigned classes");
            System.out.println("5. Purchase a gym membership");
            System.out.println("6. View all products for sale");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the class type: ");
                    String workoutType = scanner.nextLine();

                    System.out.print("Enter a description for the class: ");
                    String workoutDescription = scanner.nextLine();

                    try {
                        workoutService.createWorkoutClass(new WorkoutClass(workoutType, workoutDescription, user.getID()));

                        System.out.println("=== Workout Class Created ===");
                    } catch (Exception e) {
                        System.out.println("   Error: Could not create the workout class.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter the ID of the class to update: ");
                    int workoutID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the updated class' type: ");
                    String workoutUpdatedType = scanner.nextLine();

                    System.out.print("Enter the updated class' description: ");
                    String workoutUpdatedDescription = scanner.nextLine();

                    try {
                        workoutService.updateWorkoutClass(workoutID, workoutUpdatedType, workoutUpdatedDescription);

                        System.out.println("=== Workout Class Updated ===");
                    } catch (Exception e) {
                        System.out.println("   Error: Could not update Workout Class.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter the ID of the class to delete: ");
                    int workoutDeleteID = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        workoutService.deleteWorkoutClass(workoutDeleteID);

                        System.out.println("=== Workout Class Deleted ===");
                    } catch (Exception e) {
                        System.out.println("   Error: Could not delete the specified Workout Class.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        workoutService.listAllTrainerWorkoutClasses(user);
                    } catch (Exception e) {
                        System.out.println("   Error: Could not display your Workout Classes.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        membershipService.purchaseMembership(new Membership());
                    } catch (Exception e) {
                        System.out.println("   Error: Could not purchase your membership.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        user.showAllProducts();
                    } catch (Exception e) {
                        System.out.println("   Error: Could not display products for sale.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Exiting... Thank you, have a good day!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 7);
    }

    // Admin menu
    private static void showAdminMenu(Scanner scanner, Admin user, MembershipService membershipService) throws SQLException, IOException {
        int choice;

        do {
            System.out.println("\n=== Gym Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View all gym memberships and their total revenue");
            System.out.println("4. Add a new product");
            System.out.println("5. Edit the price of a product");
            System.out.println("6. Print item stock report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ArrayList<User> userArray = user.getAllUsers();

                    for (User u : userArray) {
                        System.out.println(u);
                    }
                    System.out.println("=== All Users Printed ===");
                    break;
                case 2:
                    System.out.print("Enter a username of a User to delete: ");
                    String deleteThisUser = scanner.nextLine();

                    try {
                        user.deleteUser(deleteThisUser);

                        System.out.println("=== User Deleted ===");
                    } catch (Exception e) {
                        System.out.println("   Error: Could not find a user with that username to delete.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 3:
                    ArrayList<Membership> allMemberships = membershipService.getAllMemberships(user);

                    for (Membership membership : allMemberships) {
                        System.out.println(membership);
                    }
                    System.out.println("=== All Memberships Displayed ===");
                    membershipService.displayTotalRevenue();
                    break;
                case 4:
                    System.out.print("Enter the name of the product: ");
                    String productName = scanner.nextLine();

                    System.out.print("Enter the category of the product: ");
                    String productCategory = scanner.nextLine();

                    System.out.print("Enter the price of the product: ");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter the stock quantity of the product: ");
                    int productStockQuantity = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter a description of the product: ");
                    String productDescription = scanner.nextLine();

                    try {
                        user.createProduct(productName, productCategory, productPrice, productStockQuantity, productDescription);

                        System.out.println("=== Product Created ===");
                    } catch (Exception e) {
                        System.out.println("   Error: Could not create the product.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Enter ID of the product: ");
                    int productID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter a new price for the product: ");
                    double productNewPrice = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        user.setPrice(productID, productNewPrice);

                        System.out.println("=== Product Price Changed ===");
                    } catch (Exception e) {
                        System.out.println("   Error: Could not update the product's price.");
                        CustomLogger.logError(e.getMessage());
                    }
                    break;
                case 6:
                    user.showAllProducts();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you, have a good day!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 7);
    }

    //Adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) throws SQLException, IOException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number (##########): ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User user = new User(username, password, email, phoneNumber, address, role);
        try {
            userService.registerUser(user);
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error logging error: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }
}