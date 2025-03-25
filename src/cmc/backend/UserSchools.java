package cmc.backend;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserSchools extends University{
private Map<University, Map<User, Date>> savedSchools;
private DatabaseController myDBController;
	public UserSchools() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * @param savedSchoolList
	 */
	public boolean saveSchool(String userID, String schoolID) {
		
		List<String> schoolsSaved1 = getSavedSchools(userID);
		
		for(String schol: schoolsSaved1) {			
			if(schol.equals(schoolID)) {
				new IllegalArgumentException("School Already Saved");
			}
		}
		return this.myDBController.saveSchool(userID, schoolID);
	}
	public void removeSavedSchool(int userID, int school) {
		
	}          
	public List<String> getSavedSchools(String userID) {
		Map<String, List<String>> usersToSavedSchools = this.myDBController.getUserSavedSchoolMap();
		return usersToSavedSchools.get(userID);
	}
	public void isSchoolSaved(int userID, int schoolID) {
		
		/**boolean bool = false;
		
		return bool; */
	}
	public void clearSavedSchoolsCount(int userID) {
		
	}
	public int getSavedShoolsCount(int userID) {
		
		
		return 0;
	}
	public Date getSavedDate(int userID, int schoolID) {
		return null;
	}
	
}
	
