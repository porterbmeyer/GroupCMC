package cmc.backend;

import java.util.ArrayList;
import java.util.List;

public class User extends Account{
	
	private List<University> savedSchoolList;

	/**
	 * @param accountID
	 * @param username
	 * @param email
	 * @param password
	 * @param type
	 * @param active
	 */
	public User(int accountID, String username, String email, String password, char type, char active) {
		super(accountID, username, email, password, type, active);
		this.savedSchoolList = new ArrayList<University>();
	}

	/**
	 * @return the savedSchoolList
	 */
	public List<University> getSavedSchoolList() {
		return savedSchoolList;
	}

	/**
	 * @param savedSchoolList the savedSchoolList to set
	 */
	public void setSavedSchoolList(List<University> savedSchoolList) {
		this.savedSchoolList = savedSchoolList;
	}


	
}
