package cmc.frontend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cmc.CMCException;
import cmc.backend.University;
import cmc.backend.User;

public class Driver {
	
	// the static UserInteraction object to use for all calls into CMC code
	private static UserInteraction ui = new UserInteraction();
	
	// other classes should *not* instantiate this class.  It is "pure static".
	private Driver() throws CMCException {
		throw new CMCException("Attempt to instantiate a Driver");
	}
	
	/**
	 * Get the selected menu option based on user entry.
	 * This reads one line from the provided Scanner.
	 * 
	 * @param s the Scanner from which to read the user's input
	 * @param minChoice the minimum allowed option (inclusive)
	 * @param maxChoice the maximum allowed option (inclusive)
	 * @return the selected integer, or -1 if invalid input is entered
	 */
	private static int getSingleMenuEntry(Scanner s, int minChoice, int maxChoice) {
		String choice = s.nextLine();
		try {
			int numChoice = Integer.parseInt(choice);
			if (numChoice < minChoice || numChoice > maxChoice)
				throw new NumberFormatException("Invalid selection");
			return numChoice;
		}
		catch (Exception e) {
			// here if either a non-integer is entered or it is outside
			// the legal range of values (per min/maxChoice)
			return -1;
		}
	}
	
	/**
	 * Get the selected menu option based on user entry.
	 * This reads lines from the provided Scanner until the user enters
	 * a menu option (number) that matches one of the options provided
	 * (i.e., is between 1 and the number of options).
	 * 
	 * @param s the Scanner from which to read the user's input
	 * @param options the menu options the user has
	 * @return the selected menu option, as an integer (1 more than the
	 * position in the options array)
	 */
	private static int getMenuOption(Scanner s, List<String> options) {		
		int choice = -1;
		while (choice == -1) {
			System.out.println("Choose an option:");
			for (int i = 0; i < options.size(); i++) {
				System.out.println((i+1) + ": " + options.get(i));
			}
			choice = getSingleMenuEntry(s, 1, options.size());
			if (choice == -1)
				System.out.println("Invalid option.");
		}
		
		return choice;
	}
	
	// print the header for the current menu
	private static void printHeader(String title) {
		String dashes = "";
		for (int i = 0; i < title.length(); i++)
			dashes += "-";

		System.out.println(dashes);
		System.out.println(title);
		System.out.println(dashes);
	}
	
	private static void adminUserListMenu(Scanner s) throws CMCException {
		printHeader("Admin User List");
		
		// TODO: it would be nice if this was refactored into a list of User objects...
		List<User> allUsers = ui.getAllUsers();
		for (User user : allUsers) {
			System.out.println(user.getUsername() + " | " + user.getFirstName() + " | " + user.getLastName());
		}
		System.out.println();
		
		int choice = getMenuOption(s, Arrays.asList("Add User", "Edit User", "Remove User", "Go Back"));
		
		switch(choice) {
		case 1:
			if (!ui.addUser(s))
				System.out.println("Failed to add new user.  (Username already exists?)");
			break;
		case 2:
			if(!ui.editUser(s)) {
				System.out.print("Failed to edit user (User does not exist)");
			}
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
	
	private static void adminMenu(Scanner s) throws CMCException {
		printHeader("Admin Menu");
		
		int choice = getMenuOption(s, Arrays.asList("View/Edit List of Users", "Logout"));
		
		switch(choice) {
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
	// TODO: Figure out how this method behaves and add 
	private static void searchResultsMenu(Scanner s, List<University> results) {
		printHeader("Search Results");

		for (University school : results) {
			System.out.println(school.getName() + " | " + school.getState());
		}
		System.out.println();

		int choice = getMenuOption(s, Arrays.asList("Save School", "Go Back"));

		switch(choice) {
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
	// consulted OpenAI's ChatGPT
	private static void userSavedSchoolListMenu(Scanner s) {
	    printHeader("User Saved School List");

	    // Get the saved schools
	    Map<String, List<String>> schools = ui.getSavedSchools(); // Assuming ui.getSavedSchools() returns a Map<String, List<String>>
	    
	    // Check if schools is not null
	    if (schools != null && !schools.isEmpty()) {
	        for (Map.Entry<String, List<String>> entry : schools.entrySet()) {
	            String username = entry.getKey(); // User who saved the school
	            List<String> savedSchools = entry.getValue(); // List of schools saved by the user

	            // Print the username and their saved schools
	            System.out.println("User: " + username);
	            for (String school : savedSchools) {
	                System.out.println("  - " + school);
	            }
	        }
	    } else {
	        // Print message if no schools are saved or the Map is null
	        System.out.println("No schools saved yet.");
	    }

	    System.out.println();  // Blank line for formatting

	    // Provide the option to go back
	    int choice = getMenuOption(s, Arrays.asList("Remove School","Go Back"));

	    switch (choice) {
	        case 1:
	        	if (!ui.removeSchool(s))
					System.out.println("Failed to remove school.  (Check spelling or if is in list)"); 
	        	break;
	        case 2:
	        	return;
	        default:
	            System.err.println("Internal error: Unsupported option.");
	            System.exit(1);
	    }
	}

	
	private static void regularUserMenu(Scanner s) {
		printHeader("User Menu");
		
		int choice = getMenuOption(s, Arrays.asList("Search", "View Saved Schools", "Logout"));
		
		switch(choice) {
		case 1:
			// TODO: it would be cleaner to use objects here (rather than
			//       arrays of strings)
			List<University> searchResult = ui.search(s);
			searchResultsMenu(s, searchResult);
			break;
		case 2:
			userSavedSchoolListMenu(s);
			break;
		case 3:
			ui.logout();
			break;
		default:
			System.err.println("Internal error: Unsupported option.");
			System.exit(1);
		}
	}

	private static void topMenu(Scanner s) throws CMCException {
		printHeader("Welcome to Choose My College (CMC)!");
		System.out.println("Please log in.");
		/*
		 * 
		 
		String username = "";
		while (username.trim().isEmpty()) {
			System.out.print("Username: ");
			username = s.nextLine();
		}

		System.out.print("Password: ");
		String password = s.nextLine();
		boolean success = ui.login(username, password);
		*/
		boolean success = ui.login(s);
		if (success)
			System.out.println("Redirecting to main menu.");
	}

	// main just forever prints the relevant menu
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
