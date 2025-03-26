package cmc.backend;

import java.util.ArrayList;
import java.util.List;

public class User extends Account{
	
	private List<String> savedSchoolList;

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
		setSavedSchool();
		//this.savedSchoolList =  functio to grab saved school
	}
	
	
	private void setSavedSchool() {
		this.savedSchoolList = DatabaseController.getUserSavedSchoolMap(this.getUsername());
	}

	/**
	 * @return the savedSchoolList
	 */
	public List<String> getSavedSchoolList() {
		return savedSchoolList;
	}

	/**
	 * @param savedSchoolList the savedSchoolList to set
	 */
	public void setSavedSchoolList(List<String> savedSchoolList) {
		this.savedSchoolList = savedSchoolList;
	}
	
	
	public  boolean addSavedSchool(String school) {
		 return DatabaseController.saveSchool(this, school);
	}

	public  boolean removeSavedSchool(String school) {
		
		return DatabaseController.removeSavedSchool(this, school);
	}
	
	
}
