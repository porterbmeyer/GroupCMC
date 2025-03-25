package cmc.backend;

import java.util.*;

public class Admin extends Account{
	private List<User> userList;
	private List<University> schoolList;
	
	/**
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param type
	 * @param active
	 */
	public Admin(String firstname, String lastname, String username, String password, char type, char active) {
		super(firstname, lastname, username, password, type, active);
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
