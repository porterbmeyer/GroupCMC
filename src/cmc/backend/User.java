package cmc.backend;

import java.util.List;

public class User{
	
	private List<University> savedSchoolList;
	

	/**
	 * @param savedSchoolList
	 */
	public User(List<University> savedSchoolList) {
		super();
		this.savedSchoolList = savedSchoolList;
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
