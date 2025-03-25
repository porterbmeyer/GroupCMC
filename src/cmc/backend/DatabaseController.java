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
			throw new CMCException("Error removing user from the DB.  Not present?  DB error?");
		}
		else {
			return true;
		}
	}
	
	// get a user; null if not in DB
	//TODO make a user object and return the user object
	public User getUser(String username) {
		String[][] databaseUserStrings = this.database.user_getUsers();
		
		for (String[] user : databaseUserStrings) {
			String thisUsername = user[2];
			if (thisUsername.equals(username)) {
				User user1 = new User(user[0], user[1], user[2], user[3], user[4].charAt(0), user[5].charAt(0));
				return user1;
			}
		}
		
		return null;
	}
	
	// get the list of all the users in the DB
	//TODO make a list of user objects and return it
	public List<User> getAllUsers() {
		String[][] dbUserList = this.database.user_getUsers();
		
		ArrayList<User> result = new ArrayList<User>();
		for (String[] user : dbUserList){
			User user1 = new User(user[0], user[1], user[2], user[3], user[4].charAt(0), user[5].charAt(0));
			result.add(user1);
		}
		
		return result;
	}
	
	// get the list of all the universities in the DB
	public List<University> getAllUniversities() {
		String[][] dbUniversityList  = this.database.university_getUniversities();
		
		ArrayList<University> result = new ArrayList<University>();
		for (String[] university: dbUniversityList) {
			result.add(new University(university[0],university[1],university[2],university[3],university[4],university[5],university[6],university[7],university[8],university[9],university[10], university[11], university[12], university[13], university[14], university[15], university[16]));
		}
		return result;
	}
	
	public University getUniversities(String name) throws CMCException {
		String[][] dbUniversityList  = this.database.university_getUniversities();
		for (String[] university: dbUniversityList) {
			if (university[2].equals(name))
				return new University(university[0],university[1],university[2],university[3],university[4],university[5],university[6],university[7],university[8],university[9],university[10], university[11], university[12], university[13], university[14], university[15], university[16]);
		}
		throw new CMCException("Was not able to find specific University specified");
	}
	
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
	    User user = getUser(username); 

	    if (user == null) {
	        return false;
	    }

	    int result = this.database.user_editUser(
	        user.getUsername(),
	        user.getFirstName(),
	        user.getLastName(),
	        user.getPassword(),
	        user.getType(),
	        'N' 
	    );

	    if (result == -1) {
	        throw new CMCException("Error editing user (to deactivate) in the DB");
	    }

	    return true;
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
	
	public boolean addUniversity(String name, String state, String location, String control, int numOfStudents, int percFemale, int SATVerbal, int SATMath, int expenses, int percFinAId, int numOfApps, int percAdmit, int percEnroll, int academicScale, int socialScale, int qualOfLife) throws CMCException {
	   

	    int result = this.database.university_addUniversity(name, state, location, control, numOfStudents, percFemale, SATVerbal, SATMath, expenses, percFinAId, numOfApps, percAdmit, percEnroll, academicScale, socialScale, qualOfLife);

	    if (result == -1) {
	        throw new CMCException("Error adding university to the database.");
	    }

	    return true;
	}

	public boolean addAccount(Account acc) throws CMCException {
	    int result = this.database.user_addUser(
	        acc.getFirstName(),
	        acc.getLastName(),
	        acc.getUsername(),
	        acc.getPassword(),
	        acc.getType()
	    );
	    
	    if (result == -1) {
	        throw new CMCException("Error adding account to the DB");
	    } else {
	        return true;
	    }
	}
	
	public boolean deleteAccount(Account acc) throws CMCException {
	    int result = this.database.user_deleteUser(acc.getUsername());

	    if (result != 1) {
	        throw new CMCException("Error deleting account from the DB. Account not found.");
	    } else {
	        return true;
	    }
	}

	public Account getAccount(String username) {
	    User user = getUser(username);

	    if (user != null) {
	        return new Account(
	            user.getFirstName(),
	            user.getLastName(),
	            user.getUsername(),
	            user.getPassword(),
	            user.getType(),
	            user.getActive()
	        );
	    }
	    return null;
	}
	public boolean updateAccount(Account acc) throws CMCException {
	    if (acc == null) {
	        throw new CMCException("Cannot update a null account.");
	    }

	    int result = this.database.user_editUser(
	        acc.getUsername(),
	        acc.getFirstName(),
	        acc.getFirstName(),
	        acc.getPassword(),
	        acc.getType(),
	        acc.getActive()
	    );

	    if (result == -1) {
	        throw new CMCException("Error updating account in the database.");
	    }
	    
	    return true;
	}
	
	public List<University> searchUniversities(String state) {
	    String[][] dbUniversityList = this.database.university_getUniversities();
	    List<University> result = new ArrayList<>();

	    for (String[] university : dbUniversityList) {
	        if (!state.isEmpty() && university[3].equalsIgnoreCase(state)) {
	            result.add(new University(
	                university[0], university[1], university[2], university[3], university[4], 
	                university[5], university[6], university[7], university[8], university[9],
	                university[10], university[11], university[12], university[13], university[14],
	                university[15], university[16]
	            ));
	        }
	    }
	    return result;
	}
}