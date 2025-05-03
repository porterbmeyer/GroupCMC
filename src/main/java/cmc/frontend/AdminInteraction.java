package cmc.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cmc.CMCException;
import cmc.backend.AccountController;
import cmc.backend.Admin;
import cmc.backend.DatabaseController;
import cmc.backend.University;
import cmc.backend.UniversityController;
import cmc.backend.User;

/**
 * The AdminInteraction class supports the administrator interface for managing users
 * and universities. It provides methods to create, deactivate, reactivate, and delete users,
 * as well as to add, edit, and delete university records.
 */
public class AdminInteraction {

    private AccountController accountController;
    private UniversityController universityController;
    private DatabaseController databaseController;
    private Admin loggedInAdmin;

    /**
     * Prompts the administrator for details to create a new user.
     *
     * @param s the Scanner used to read administrator input
     * @return true if the user account is created successfully; false otherwise
     * @throws CMCException if a database error occurs during account creation
     */
    public boolean createUser(Scanner s) throws CMCException {
        System.out.print("First Name: ");
        String firstname = s.nextLine();
        System.out.print("Last Name: ");
        String lastname = s.nextLine();
        System.out.print("Username Name: ");
        String userName = s.nextLine();
        System.out.print("Password: ");
        String Password = s.nextLine();
        System.out.print("Admin? (A or U): ");
        String type = s.nextLine();
        return this.accountController.createAccount(firstname, lastname, userName, Password, type.charAt(0));
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of User objects representing all users
     */
    public List<User> getAllUsers() {
        return this.databaseController.getAllUsers();
    }

    /**
     * Retrieves only active users from the database.
     *
     * @return a list of User objects representing active users
     */
    public List<User> getActiveUsers() {
        List<User> userlist = this.databaseController.getAllUsers();
        List<User> activeUsers = new ArrayList<User>();
        for (User user : userlist) {
            if (user.getActive() == 'Y') {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }

    /**
     * Retrieves all universities from the database.
     *
     * @return a list of University objects representing all universities
     */
    public List<University> getAllUniversities() {
        return this.databaseController.getAllUniversities();
    }

    /**
     * Prompts for a username and attempts to deactivate the corresponding user.
     *
     * @param s the Scanner used to read user input
     * @return true if the user is deactivated successfully; false otherwise
     * @throws CMCException if a database error occurs during deactivation
     */
    public boolean deactivateUser(Scanner s) throws CMCException {
        System.out.println("Enter username to deactivate: ");
        String username = s.nextLine();
        return this.databaseController.deactivateUser(username);
    }

    /**
     * Prompts for a username and attempts to reactivate the corresponding user.
     *
     * @param s the Scanner used to read user input
     * @return true if the user is reactivated successfully; false otherwise
     * @throws CMCException if a database error occurs during reactivation
     */
    public boolean reActivateUser(Scanner s) throws CMCException {
        System.out.println("Enter username to reactivate: ");
        String username = s.nextLine();
        return this.databaseController.reActivateUser(username);
    }

    /**
     * Logs out the administrator.
     *
     * @return true if an admin is logged in and is now logged out; false otherwise
     */
    public boolean logout() {
        if (this.loggedInAdmin == null) {
            return false;
        } else {
            this.loggedInAdmin = null;
            return true;
        }
    }

    /**
     * Prompts for a username and deletes the corresponding user from the database.
     *
     * @param s the Scanner used to read input
     * @return true if the user is deleted successfully; false otherwise
     * @throws CMCException if a database error occurs during deletion
     */
    public boolean deleteUser(Scanner s) throws CMCException {
        System.out.print("Enter User name to delete: ");
        String username = s.nextLine();
        return this.accountController.deleteAccount(username);
    }

    /**
     * Prompts for a university name and deletes the corresponding university from the database.
     *
     * @param s the Scanner used to read input
     * @return true if the university is deleted successfully; false otherwise
     * @throws CMCException if a database error occurs during deletion
     */
    public boolean deleteUniversity(Scanner s) throws CMCException {
        System.out.print("Enter university name to delete: ");
        String username = s.nextLine();
        return this.universityController.deleteUniversity(username);
    }

    /**
     * Prompts the administrator for details to add a new university to the database.
     * All university attributes are read from input.
     *
     * @param scanner the Scanner used to read input
     * @return true if the university is added successfully; false otherwise
     * @throws CMCException if a database error occurs during the addition
     */
    public boolean addUniversity(Scanner scanner) throws CMCException {
        System.out.print("Enter university name: ");
        String name = scanner.nextLine();
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter control (Public/Private): ");
        String control = scanner.nextLine();
        System.out.print("Enter population: ");
        int population = scanner.nextInt();
        System.out.print("Enter percentage of female students: ");
        double percentFemale = scanner.nextDouble();
        System.out.print("Enter SAT verbal score: ");
        int satVerbal = scanner.nextInt();
        System.out.print("Enter SAT math score: ");
        int satMath = scanner.nextInt();
        System.out.print("Enter expenses: ");
        int expenses = scanner.nextInt();
        System.out.print("Enter percentage of financial aid: ");
        double percentFinancialAid = scanner.nextDouble();
        System.out.print("Enter number of applicants: ");
        int numberApplicants = scanner.nextInt();
        System.out.print("Enter acceptance rate: ");
        double acceptanceRate = scanner.nextDouble();
        System.out.print("Enter enrollment rate: ");
        double enrollmentRate = scanner.nextDouble();
        System.out.print("Enter academic scale rating: ");
        int academicScale = scanner.nextInt();
        System.out.print("Enter social scale rating: ");
        int socialScale = scanner.nextInt();
        System.out.print("Enter quality scale rating: ");
        int qualityScale = scanner.nextInt();
        return this.universityController.addUniversity(name, state, location, control, population, percentFemale, satVerbal, satMath, expenses, percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate, academicScale, socialScale, qualityScale);
    }

    /**
     * Prompts for a university name and then for new attribute values to edit that university in the database.
     * If a field is left blank, it is not updated.
     *
     * @param scanner the Scanner used to read input
     * @return true if the university information is updated successfully; false otherwise
     * @throws CMCException if a database error occurs during the update
     */
    public boolean editUni(Scanner scanner) throws CMCException {
        System.out.println("Enter name of University to edit: ");
        String name1 = scanner.nextLine();
        University currUni = this.universityController.getUniversityByName(name1);
        if (currUni == null || !currUni.getName().equals(name1)) {
            System.out.print("No school to edit");
            return false;
        }
        System.out.println("Either enter new info, or leave blank");

        System.out.print("Enter new university name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new state: ");
        String state = scanner.nextLine();
        System.out.print("Enter new location: ");
        String location = scanner.nextLine();
        System.out.print("Enter new control (Public/Private): ");
        String control = scanner.nextLine();
        System.out.print("Enter new population: ");
        int population = scanner.nextInt();
        System.out.print("Enter new percentage of female students: ");
        double percentFemale = scanner.nextDouble();
        System.out.print("Enter new SAT verbal score: ");
        int satVerbal = scanner.nextInt();
        System.out.print("Enter new SAT math score: ");
        int satMath = scanner.nextInt();
        System.out.print("Enter new expenses: ");
        int expenses = scanner.nextInt();
        System.out.print("Enter new percentage of financial aid: ");
        double percentFinancialAid = scanner.nextDouble();
        System.out.print("Enter new number of applicants: ");
        int numberApplicants = scanner.nextInt();
        System.out.print("Enter new acceptance rate: ");
        double acceptanceRate = scanner.nextDouble();
        System.out.print("Enter new enrollment rate: ");
        double enrollmentRate = scanner.nextDouble();
        System.out.print("Enter new academic scale rating: ");
        int academicScale = scanner.nextInt();
        System.out.print("Enter new social scale rating: ");
        int socialScale = scanner.nextInt();
        System.out.print("Enter new quality scale rating: ");
        int qualityScale = scanner.nextInt();
        return this.universityController.editUniversity(name, state, location, control, population, percentFemale, satVerbal, satMath, expenses, percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate, academicScale, socialScale, qualityScale);
    }
}
