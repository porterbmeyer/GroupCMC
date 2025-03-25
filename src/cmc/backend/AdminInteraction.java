package cmc.backend;

import java.util.List;

java.util.*;

public class AdminInteraction {
	
	private AccountController accountController;
	private Admin loggedInAdmin;
	
	
	
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
	
