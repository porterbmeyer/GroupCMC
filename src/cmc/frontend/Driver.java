package cmc.frontend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cmc.CMCException;
import cmc.backend.University;
import cmc.backend.User;

/**
 * The Driver class provides a text-based menu-driven interface for the Choose My College (CMC)
 * application. This class is not meant to be instantiated; it contains only static methods.
 * It directs input to user interaction methods, displays menus, and handles simple user input validation.
 */
public class Driver {

    // The static UserInteraction object used for all calls to CMC functionality.
    private static UserInteraction ui = new UserInteraction();

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws CMCException always thrown to indicate that instantiation is not allowed
     */
    private Driver() throws CMCException {
        throw new CMCException("Attempt to instantiate a Driver");
    }

    /**
     * Reads one line of input from the provided Scanner and converts it to an integer.
     * Validates that the input number is between the given minimum and maximum values.
     *
     * @param s the Scanner from which input is read
     * @param minChoice the minimum allowed value (inclusive)
     * @param maxChoice the maximum allowed value (inclusive)
     * @return the integer corresponding to the user's choice, or -1 if invalid input is entered
     */
    private static int getSingleMenuEntry(Scanner s, int minChoice, int maxChoice) {
        String choice = s.nextLine();
        try {
            int numChoice = Integer.parseInt(choice);
            if (numChoice < minChoice || numChoice > maxChoice)
                throw new NumberFormatException("Invalid selection");
            return numChoice;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Repeatedly prompts the user to select one of the menu options until a valid choice is made.
     *
     * @param s the Scanner used to read user input
     * @param options the list of menu option strings
     * @return the selected menu option as an integer (1-indexed)
     */
    private static int getMenuOption(Scanner s, List<String> options) {
        int choice = -1;
        while (choice == -1) {
            System.out.println("Choose an option:");
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }
            choice = getSingleMenuEntry(s, 1, options.size());
            if (choice == -1)
                System.out.println("Invalid option.");
        }
        return choice;
    }

    /**
     * Prints a header for the current menu based on the given title.
     *
     * @param title the title of the menu header
     */
    private static void printHeader(String title) {
        String dashes = "";
        for (int i = 0; i < title.length(); i++)
            dashes += "-";
        System.out.println(dashes);
        System.out.println(title);
        System.out.println(dashes);
    }

    /**
     * Displays the administrator user list menu, which shows all users and prompts the admin
     * to add, edit, or remove users.
     *
     * @param s the Scanner used to read user input
     * @throws CMCException if an error occurs during user operations
     */
    private static void adminUserListMenu(Scanner s) throws CMCException {
        printHeader("Admin User List");
        List<User> allUsers = ui.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user.getUsername() + " | " + user.getFirstName() + " | " + user.getLastName());
        }
        System.out.println();
        int choice = getMenuOption(s, Arrays.asList("Add User", "Edit User", "Remove User", "Go Back"));
        switch (choice) {
            case 1:
                if (!ui.addUser(s))
                    System.out.println("Failed to add new user.  (Username already exists?)");
                break;
            case 2:
                if (!ui.editUser(s))
                    System.out.print("Failed to edit user (User does not exist)");
                break;
            case 3:
                if (!ui.removeUser(s))
                    System.out.println("Failed to remove user.  (Invalid username?)");
                break;
            case 4:
                return;
            default:
                System.err.println("Internal error: Unsupported option.");
                System.exit(1);
        }
    }

    /**
     * Displays the administrator main menu, which allows the admin to view or edit the list of users
     * or to log out.
     *
     * @param s the Scanner used to read user input
     * @throws CMCException if an error occurs during menu operations
     */
    private static void adminMenu(Scanner s) throws CMCException {
        printHeader("Admin Menu");
        int choice = getMenuOption(s, Arrays.asList("View/Edit List of Users", "Logout"));
        switch (choice) {
            case 1:
                adminUserListMenu(s);
                break;
            case 2:
                ui.logout();
                break;
            default:
                System.err.println("Internal error: Unsupported option.");
                System.exit(1);
        }
    }

    /**
     * Displays the search results menu. This method prints a header and all university names
     * with their state, and then prompts the user to either save a school from the results or go back.
     *
     * @param s the Scanner used to read user input
     * @param results the list of University objects returned by a search query
     */
    private static void searchResultsMenu(Scanner s, List<University> results) {
        if (results == null) {
            System.out.println("Wrong Input, you will be directed back to User menu.");
        } else {
            printHeader("Search Results");
            for (University school : results) {
                System.out.println(school.getName() + " | " + school.getState());
            }
            System.out.println();
            int choice = getMenuOption(s, Arrays.asList("Save School", "Go Back"));
            switch (choice) {
                case 1:
                    if (!ui.saveSchool(s))
                        System.out.println("Failed to save school.  (Already in saved list?)");
                    break;
                case 2:
                    return;
                default:
                    System.err.println("Internal error: Unsupported option.");
                    System.exit(1);
            }
        }
    }

    /**
     * Displays the menu for viewing saved schools for a user.
     * It shows the list of saved schools for each user, then allows removal of a school.
     *
     * @param s the Scanner used to read user input
     */
    private static void userSavedSchoolListMenu(Scanner s) {
        printHeader("User Saved School List");
        Map<String, List<String>> schools = ui.getSavedSchools();
        if (schools != null && !schools.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : schools.entrySet()) {
                String username = entry.getKey();
                List<String> savedSchools = entry.getValue();
                System.out.println("User: " + username);
                for (String school : savedSchools) {
                    System.out.println("  - " + school);
                }
            }
        } else {
            System.out.println("No schools saved yet.");
        }
        System.out.println();
        int choice = getMenuOption(s, Arrays.asList("Remove School", "Go Back"));
        switch (choice) {
            case 1:
                if (!ui.removeSchool(s))
                    System.out.println("Failed to remove school. (Check spelling or if it is in list)");
                break;
            case 2:
                return;
            default:
                System.err.println("Internal error: Unsupported option.");
                System.exit(1);
        }
    }

    /**
     * Displays the regular user menu, which allows the user to search for universities,
     * view saved schools, edit their profile, or log out.
     *
     * @param s the Scanner used to read user input
     * @throws CMCException if an error occurs during the menu operations
     */
    private static void regularUserMenu(Scanner s) throws CMCException {
        printHeader("User Menu");
        int choice = getMenuOption(s, Arrays.asList("Search", "View Saved Schools", "Edit Profile", "Logout"));
        switch (choice) {
            case 1:
                List<University> searchResult = ui.search(s);
                searchResultsMenu(s, searchResult);
                break;
            case 2:
                userSavedSchoolListMenu(s);
                break;
            case 3:
                if (!ui.editProfile(s)) {
                    System.out.println("Failed to edit profile.");
                }
                break;
            case 4:
                ui.logout();
                break;
            default:
                System.err.println("Internal error: Unsupported option.");
                System.exit(1);
        }
    }

    /**
     * Displays the top menu which prompts the user to log in.
     *
     * @param s the Scanner used to read user input
     * @throws CMCException if an error occurs during login
     */
    private static void topMenu(Scanner s) throws CMCException {
        printHeader("Welcome to Choose My College (CMC)!");
        System.out.println("Please log in.");
        boolean success = ui.login(s);
        if (success)
            System.out.println("Redirecting to main menu.");
    }

    /**
     * The main entry point of the application.
     * This method continuously checks for a logged in user and routes the user
     * to the appropriate menu based on their account type.
     *
     * @param args command-line arguments (not used)
     * @throws CMCException if an error occurs during application execution
     */
    public static void main(String[] args) throws CMCException {
        Scanner s = new Scanner(System.in);
        while (true) {
            if (ui.getLoggedInUser() == null)
                topMenu(s);
            else if (ui.getLoggedInUser().isAdmin())
                adminMenu(s);
            else
                regularUserMenu(s);
        }
    }
}
