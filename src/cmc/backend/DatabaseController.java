package cmc.backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmc.CMCException;
import dblibrary.project.csci230.*;

/**
 * The DatabaseController class is the primary interaction class with the
 * database library.  It currently just calls the lower-level methods and
 * forwards the result (possibly throwing some exceptions along the way).
 * 
 * @author Sally Sparrow
 */
public class DatabaseController {
	private UniversityDBLibrary database;

	// The default constructor that connects to the underlying
	// UniversityDBLibrary object using your team's info.
	public DatabaseController() {
		// TODO: we'll need to update this to our team's actual database someday!
		this.database = new UniversityDBLibrary("pmrpmd", "Csci230$");
	}

	// add a user to the db
	// TODO: it would be nice if this could take a User object instead
	// (so "higher-abstraction" classes don't have to worry about the order
	//  of properties)
	public boolean addUser(String username, String password, char type,
			String firstName, String lastName) throws CMCException {
		int result = this.database.user_addUser(firstName, lastName, username, password, type);
		
		if (result == -1) {
			throw new CMCException("Error adding user to the DB");
		}
		else {
			return true;
		}
	}
	
	// remove a user from the db
	public boolean removeUser(String username) throws CMCException {
		int result = this.database.user_deleteUser(username);
		if (result != 1) {
			// TODO: How can we tell the difference?
			throw new CMCException("Error removing user from the DB.  Not present?  DB error?");
		}
		else {
			return true;
		}
	}
	
	// get a user; null if not in DB
	public String[] getUser(String username) {
		String[][] databaseUserStrings = this.database.user_getUsers();
		
		for (String[] singleUser : databaseUserStrings) {
			String thisUsername = singleUser[2];
			if (thisUsername.equals(username)) {
				return singleUser;
			}
		}
		
		return null;
	}
	
	// get the list of all the users in the DB
	public List<User> getAllUsers() {
		User[] dbUserList = this.database.user_getUsers();
		
		List<User> result = new List<User>();
		for (User user : dbUserList) {
			result.add(user);
		}
		
		return result;
	}
	
	// get the list of all the universities in the DB
	public List<University> getAllUniversities() {
		University[][] dbUniversityList = this.database.university_getUniversities();

		ArrayList<University> result = new ArrayList<University>();
		for (University[]  university: dbUniversityList) {
			result.add(university);
		}
		return result;
	}
	
	List<University> universityList = this.myDBcontroller.getAllUniversities();
	// save a school to a particular user's list
	// TODO: It feels like we should be able to do this as part of
	//       "updating" a user in the DB.
	public boolean saveSchool(String username, String schoolName) {
		int result = this.database.user_saveSchool(username, schoolName);
		if (result != 1) {
			// TODO: How can we tell the difference?
			throw new Error("Error saving school to user in the DB.  Already present?  DB error?");
		}
		else {
			return true;
		}
	}
	
	// get the mapping from users to their saved universities in the DB
	// e.g., peter -> {CSBSJU, HARVARD}
	//       juser -> {YALE, AUGSBURG, STANFORD}
	public Map<String, List<String>> getUserSavedSchoolMap() {
		String[][] dbMapping = this.database.user_getUsernamesWithSavedSchools();

		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		
		for (String[] entry : dbMapping) {
			String user = entry[0];
			String school = entry[1];
			
			if (!result.containsKey(user))
				result.put(user, new ArrayList<String>());
			
			result.get(user).add(school);
		}

		return result;
	}
	
	// deactivate a user in the database
	// This is messy, and it would be much cleaner to do
	// an editUser with an updated User object!
	public boolean deactivateUser(String username) throws CMCException {
		String[] theUser = getUser(username);
		if (theUser == null)
			return false;
		int result = this.database.user_editUser(theUser[2], theUser[0], theUser[1],
				theUser[3], theUser[4].charAt(0), 'N');
		if (result == -1) {
			throw new CMCException("Error editing user (to deactivate) in the DB");
		}
		else {
			return true;
		}
			
	}
	
	public boolean removeUniversity(University u) throws CMCException {
	    if (u == null) {
	        throw new CMCException("Cannot remove a null university.");
	    }

	    int result = this.database.university_deleteUniversity(u.getName());

	    if (result != 1) {
	        throw new CMCException("Error removing university from the database. It may not exist.");
	    }

	    return true;
	}
	
	public boolean addUniversity(University u) throws CMCException {
	    if (u == null) {
	        throw new CMCException("Cannot add a null university.");
	    }

	    int result = this.database.university_addUniversity(
	        u.getName(),
	        u.getState(),
	        u.getLocation(),
	        u.getControl(),
	        u.getPopulation(),
	        u.getPercentFemale(),
	        u.getSatVerbal(),
	        u.getSatMath(),
	        u.getExpenses(),
	        u.getPercentFinancialAid(),
	        u.getNumberApplicants(),
	        u.getAcceptanceRate(),
	        u.getEnrollmentRate(),
	        u.getAcademicScale(),
	        u.getSocialScale(),
	        u.getQualityScale()
	    );

	    if (result == -1) {
	        throw new CMCException("Error adding university to the database.");
	    }

	    return true;
	}

	//TODO
	public boolean addAccount(Account acc) {
		return false;
	}
	
	//TODO
	public boolean deleteAccount(Account acc) {
		return false;
	}

	//TODO
	public List<Account> getAllaccounts() {
		return null;
	}
		
}