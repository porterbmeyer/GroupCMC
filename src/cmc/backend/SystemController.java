package cmc.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cmc.CMCException;

public class SystemController {
	private DatabaseController myDBController;
	// Construct a SystemController using the basic (no parameter)
	// DatabaseController as the underlying database access.
	public SystemController() {
		this.myDBController = new DatabaseController();
	}
	
	/**
	 * Verify whether the username and password provided match a user in the
	 * database.  Return a Boolean indicating yes or no.
	 * 
	 * TODO: how could we distinguish a DB error from a failed login?
	 * 
	 * @param username the username to check
	 * @param password the password to check for matching the username
	 * @return the matching User object if the username and password match
	 * a database entry, or null otherwise
	 */
	public Account login(String username, String password) {
		String[] userData = this.myDBController.getUser(username);
		
		Account theUser = new Account(userData[2], userData[3], userData[4].charAt(0), userData[0],
				userData[1]);
		
		if (!theUser.password.equals(password) || theUser.activated != 'Y'){
			return null;
		}
		return theUser;
	}

	// this ADMIN ONLY method returns the list of all the users (and their data)
	// TODO: shouldn't this return a List of User objects?
	public List<String[]> getAllUsers() {
		List<String[]> usersList = this.myDBController.getAllUsers();
		return usersList;
	}
	
	// this ADMIN ONLY method attempts to add a user to the database with the
	// provided details
	public boolean addUser(String username, String password,
			String firstName, String lastName, boolean isAdmin) {
		char type = (isAdmin ? 'a' : 'u');
		try {
			return this.myDBController.addUser(username, password, type, firstName, lastName);
		} catch (CMCException e) {
			// TODO: should we let the calling class report the error more
			//       clearly by passing it on?
			return false;
		}
	}
	
	// this ADMIN ONLY method attempts to remove a user from the database
	// based on the provided username
	public boolean removeUser(String username) {
		try {
			return this.myDBController.removeUser(username);
		} catch (CMCException e) {
			// TODO: should we let the calling class report the error more
			//       clearly by passing it on?
			return false;
		}
	}
	
	// this REGULAR USER ONLY method searches for schools in the database
	// based on provided criteria (just state for now)
	public List<String[]> search(String state) {
		
		List<String[]> schoolList = this.myDBController.getAllSchools();
		
		if(state == null || state.trim().isEmpty()) {
			return schoolList;
		}
		
		List<String[]> filteredList = new ArrayList<String[]>();
		
		for (String[] school : schoolList) {
			if (school[1].equalsIgnoreCase(state))
				filteredList.add(school);
		}
		
		return filteredList;
	}
	
	// this REGULAR USER ONLY method attempts to add the provided school
	// to the list of saved schools for the provided username
	public boolean saveSchool(String user, String school) {
		
		List<String> schoolsSaved1 = getSavedSchools(user);
		
		for(String schol: schoolsSaved1) {			
			if(schol.equals(school)) {
				new IllegalArgumentException("School Already Saved");
			}
		}
		return this.myDBController.saveSchool(user, school);
	}
	
	// this REGULAR USER ONLY method attempts to retrieve the list of saved
	// schools for the provided username
	public List<String> getSavedSchools(String user) {
		Map<String, List<String>> usersToSavedSchools = this.myDBController.getUserSavedSchoolMap();
		return usersToSavedSchools.get(user);
	}

}
