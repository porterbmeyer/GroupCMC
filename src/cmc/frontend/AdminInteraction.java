package cmc.frontend;

import java.util.List;

import cmc.backend.AccountController;
import cmc.backend.Admin;
import cmc.backend.DatabaseController;
import cmc.backend.University;

java.util.*;

public class AdminInteraction {
	
	private AccountController accountController;
	private DatabaseController databaseController;
	private Admin loggedInAdmin;
	
	public boolean createUser(String firstname, String lastname, String username, String password, char type, char active) {
		return this.accountController.createAccount(firstname, lastname, username, password, type, active);
	}
	
	
	public List<User> getAllUsers{
		List<University> universityList = this.AccountController.getAllUniversities();
		return 
	}
	
	public boolean deleteUser(int userID) {
		Admin u = getUniversityByName(userID);
		
		if(u == null) {
			return false;
		}
		else {
			return this.myDBcontroller.removeUniversity(u);
		}
	}
	

	}
	
