package cmc.backend;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserSchools extends University{

	private Map<University, Map<User, Date>> savedSchools;
	private DatabaseController myDBController;


	public UserSchools(String name, String state, String location, String control, int population, double percentFemale, int satVerbal, int satMath, int expenses, double percentFinancialAid, int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, int qualityScale) {
		super(name,state,location,control,population,percentFemale,satVerbal,satMath,expenses,percentFinancialAid,numberApplicants,acceptanceRate,enrollmentRate,academicScale,socialScale,qualityScale);
	}

	/**
	 * @param savedSchoolList
	 */
	public boolean saveSchool(String username, String name) {

		List<String> schoolsSaved1 = getSavedSchools(username);

		for(String schol: schoolsSaved1) {			
			if(schol.equals(name)) {
				new IllegalArgumentException("School Already Saved");
			}
		}
		return this.myDBController.saveSchool(username, name);
	}
	
	public void removeSavedSchool(int username, int name) {
		
	} 
	
	public List<String> getSavedSchools(String username) {
		Map<String, List<String>> usersToSavedSchools = this.myDBController.getUserSavedSchoolMap();
		return usersToSavedSchools.get(username);
	}
	
	public Date getSavedDate(int username, int name) {
		return null;
	}
	
	
}
	
