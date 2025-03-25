package cmc.frontend;

import cmc.CMCException;
import cmc.backend.*;
import java.util.List;
import java.util.Scanner;

import cmc.backend.SystemController;
import cmc.backend.User;

public class UserInteraction {
	
	private Account loggedInUser;
	
	private AccountController accountController;
	private DatabaseController databaseController;
	
	// Construct a UserInteraction using the basic (no parameter)
	// SystemController as the single underlying controller object.
	// TODO: Someday, we should refactor the single SystemController class
	//       into multiple classes for better organization of functionalities.
	public UserInteraction() {
		this.accountController = new AccountController();
		this.databaseController = new DatabaseController();
		this.loggedInUser = null;
	}

	// attempt to login, print message, and return success or failure
	public boolean login(String username, String password) {
		Account result = this.accountController.login(username, password);
		if (result != null) {
			System.out.println("Login successful!");
			this.loggedInUser = result;
			return true;
		}
		else {
			System.out.println("Login failed!  Incorrect username or password.");
			this.loggedInUser = null;
			return false;
		}
	}
	
	// returns true if there is a user to log out, otherwise false
	public boolean logout() {
		if (this.loggedInUser == null) {
			return false;
		}
		else {
			this.loggedInUser = null;
			return true;
		}
	}
	
	// for admins, this gets the list of all users in the system
	public List<User> getAllUsers() {
		return this.databaseController.getAllUsers();
	}
	
	// ask the admin for details and then attempt to add a user to the
	// database
	public boolean addUser(Scanner s) throws CMCException {
		System.out.print("Username: ");
		String username = s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		System.out.print("First Name: ");
		String firstName = s.nextLine();
		System.out.print("Last Name: ");
		String lastName = s.nextLine();
		System.out.print("Admin? (Y or N): ");
		boolean isAdmin = false;
		if (s.nextLine().trim().equalsIgnoreCase("y"))
			isAdmin = true;
		
		Account newAccount = new Account(firstName, lastName, username, password, isAdmin ? 'A' : 'U','Y'); 
		return this.accountController.createAccount(username, password, firstName, lastName, isAdmin ? 'A' : 'U', 'Y');
	}
	
	// ask the admin for a username and then remove that user from the
	// database
	public boolean removeUser(Scanner s) throws CMCException {
		System.out.print("Username: ");
		String username = s.nextLine();

		Account acc = this.databaseController.getAccount(username);
		if (acc != null) {
			return this.accountController.deleteAccount(username);
		}
		return false;
	}
	
	public List<University> search(Scanner s) {
	    System.out.print("State (leave blank to not search by this criterion): ");
	    String state = s.nextLine().trim();

	    // Use DatabaseController to get the search results
	    List<University> universities = this.databaseController.searchUniversities(state);
	    
	    if (universities.isEmpty()) {
	        System.out.println("No universities found with the given search criteria.");
	    } else {
	        System.out.println("Universities found:");
	        for (University university : universities) {
	            System.out.println("Name: " + university.getName() + ", Location: " + university.getLocation() +
	                ", State: " + university.getState());
	        }
	    }

	    return universities;
	}
	
	
	// get the list of saved school names for the currently-logged-in user
	@SuppressWarnings("unchecked")
	public List<String> getSavedSchools() {
		return (List<String>) this.databaseController.getUserSavedSchoolMap();
	}

	/**
	 * Get the current username for the current user logged in via
	 * this UserInteraction class.
	 * 
	 * @return the username for the logged in user
	 */
	public Account getLoggedInUser() {
		return this.loggedInUser;
	}
	
	

}
