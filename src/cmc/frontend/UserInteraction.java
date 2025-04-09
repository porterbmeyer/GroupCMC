package cmc.frontend;

import cmc.CMCException;
import cmc.backend.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



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
	public boolean login(Scanner s) throws CMCException {
		System.out.println("Enter Username: ");
		String username = s.nextLine();
		
		System.out.println("Enter Password: ");
		String password = s.nextLine();
		Account result = null;
		result = this.databaseController.getUser(username);
		if (result == null || ! result.getPassword().equals(password)) {
			System.out.println("Incorrect user or password");
			return false;
		}
		else if(result.getActive() != 'Y') {
			System.out.println("Deactivated account can not log in");
			return false;
		}
		else  {
			System.out.println("Login successful!");
			this.loggedInUser = result;
			return true;
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
	
	
	// ask the admin for details and then attempt to add a user to the
	// database
	public boolean addUser(Scanner s) throws CMCException {
		System.out.print("firstName: ");
		String firstName = s.nextLine();
		System.out.print("lastName: ");
		String lastName = s.nextLine();
		System.out.print("username: ");
		String username = s.nextLine();
		System.out.print("password: ");
		String password = s.nextLine();
		System.out.print("Admin? (Y or N): ");
		boolean isAdmin = false;
		if (s.nextLine().trim().equalsIgnoreCase("y"))
			isAdmin = true;
		
		return this.accountController.createAccount(firstName, lastName, username, password, isAdmin ? 'A' : 'U');
	}
	
	// ask the admin for a username and then remove that user from the
	// database
	public boolean removeUser(Scanner s) throws CMCException {
		System.out.print("Username: ");
		String username = s.nextLine();

		Account acc = this.databaseController.getUser(username);
		if (acc != null) {
			return this.accountController.deleteAccount(username);
		}
		return false;
	}
	
	public List<University> search(Scanner s) {
		
	    System.out.print("State (leave blank to not search by this criterion): ");
	    String state = s.nextLine().trim();
	    if (state == null || state.isEmpty()) {
	    	return this.databaseController.getAllUniversities();
	    }
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
	public Map<String, List<String>> getSavedSchools() {
		return this.databaseController.getUserSavedSchoolMap();
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

	public boolean saveSchool(Scanner s) {
		/*
		List<String> schoolsSaved1 = getSavedSchools(username);

		for(String schol: schoolsSaved1) {			
			if(schol.equals(name)) {
				new IllegalArgumentException("School Already Saved");
			}
		}
		return this.databaseController.saveSchool(username, name);
		*/
		System.out.println("Enter the school you would like to save:");
		 String name = s.nextLine();
		
		return this.databaseController.saveSchool(loggedInUser, name);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return this.databaseController.getAllUsers();
	}

	public boolean removeSchool(Scanner s) {
		System.out.println("Enter the school you would like to remove:");
		 String name = s.nextLine();

		return this.databaseController.removeSavedSchool(loggedInUser, name);
	}
	
	//TODO: FINISH EDIT USER METHOD. MIGHT WANT TO RETHINK HOW IT WORKS TBH
	// User gets prompted for username, then asked for each field they want to change. CANNOT CHANGE USERNAME.
	public boolean editUser(Scanner s) throws CMCException {
	    System.out.println("Enter the username of the user you would like to edit:");
	    String username = s.nextLine();

	    Account editUser = databaseController.getUser(username);
	    if (editUser == null) {
	        System.out.println("User not found.");
	        return false;
	    }
		char newTypeChar = editUser.getType();

	    System.out.println("Enter the updates you would like to make. (Leave blank if no change is wanted)");

	    // Update password
	    System.out.println("Current Password: " + editUser.getPassword() + "\nNew Password:");
	    String newPassword = s.nextLine();
	    if (!newPassword.equals("")) {
	        newPassword = editUser.getPassword();
	    }

	    // Update first name
	    System.out.println("Current First Name: " + editUser.getFirstName() + "\nNew First Name:");
	    String newFirstName = s.nextLine();
	    if (!newFirstName.equals("")) {
	        newFirstName = editUser.getFirstName();
	    }

	    // Update last name
	    System.out.println("Current Last Name: " + editUser.getLastName() + "\nNew Last Name:");
	    String newLastName = s.nextLine();
	    if (!newLastName.equals("")) {
	        newLastName = editUser.getLastName();
	    }

	    // Update user type
	    while (true) {
	        System.out.println("Current User Type: " + editUser.getType() + "\nNew User Type (u or a):");
	        String newType = s.nextLine();
	        if (newType.equals("")) {
	            break;
	        } else if (newType.equalsIgnoreCase("u") || newType.equalsIgnoreCase("a")) {
	            newTypeChar = newType.toLowerCase().charAt(0);
	            break;
	        } else {
	            System.out.println("Invalid input. Try again.");
	        }
	    }

	    // Save changes to the database
	    if (this.accountController.editAccount(newFirstName, newLastName, username, newPassword, newTypeChar, 'Y')) {
	        System.out.println("User details updated successfully.");
	    } else {
	        System.out.println("Failed to update user details.");
	        return false;
	    }
	    return true;
	}
	

}
