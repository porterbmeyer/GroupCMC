package cmc.backend;

import java.util.*;

public class Admin extends Account{
	private List<User> userList;
	private List<University> schoolList;
	
	/**
	 * @param accountID
	 * @param username
	 * @param email
	 * @param password
	 * @param type
	 * @param active
	 */
	public Admin(int accountID, String username, String email, String password, char type, char active) {
		super(accountID, username, email, password, type, active);
		this.userList = new ArrayList<User>();
		this.schoolList = new ArrayList<University>();
	}
	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	/**
	 * @return the schoolList
	 */
	public List<University> getSchoolList() {
		return schoolList;
	}
	/**
	 * @param schoolList the schoolList to set
	 */
	public void setSchoolList(List<University> schoolList) {
		this.schoolList = schoolList;
	}
	
	
}
