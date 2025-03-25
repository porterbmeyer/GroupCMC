package cmc.backend;

import java.util.ArrayList;
import java.util.List;

public class User extends Account{
	
	private List<University> savedSchoolList;

	/**
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param type
	 * @param active
	 */
	public User(String firstname, String lastname, String username, String password, char type, char active) {
		super(firstname, lastname, username, password, type, active);
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
