package cmc.backend;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserSchools extends University{
public UserSchools(String name, String location, String state, int population, double tuition, String control,
			double percentFemale, int satVerbal, int satMath, double expenses, double percentFinancialAid,
			int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale,
			int qualityScale) {
		super(name, location, state, population, tuition, control, percentFemale, satVerbal, satMath, expenses,
				percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate, academicScale, socialScale,
				qualityScale);
	}
private Map<University, Map<User, Date>> savedSchools;
private DatabaseController myDBController;
	
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
	public void isSchoolSaved(int username, int name) {
		
		/**boolean bool = false;
		
		return bool; */
	}
	public void clearSavedSchoolsCount(int username) {
		
	}
	public int getSavedShoolsCount(int username) {
		
		
		return 0;
	}
	public Date getSavedDate(int username, int name) {
		return null;
	}
	
}
	
