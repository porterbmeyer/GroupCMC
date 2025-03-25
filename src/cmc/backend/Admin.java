package cmc.backend;

import java.util.*;

public class Admin {
	private List<User> userList;
	private List<University> schoolList;
	/**
	 * @param userList
	 * @param schoolList
	 */
	public Admin(List<User> userList, List<University> schoolList) {
		super();
		this.userList = userList;
		this.schoolList = schoolList;
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
