package cmc.backend;

import java.util.List;

import cmc.CMCException;

public class UniversityController {

	private DatabaseController myDBcontroller;


	/**
	 * method deleteUniversity removes university from the DB
	 * @param uniName
	 * @return boolean
	 * @throws CMCException
	 */
	public boolean deleteUniversity(String uniName) throws CMCException {
		University u = getUniversityByName(uniName);
		
		if(u == null) {
			return false;
		}
		else {
			return this.myDBcontroller.removeUniversity(u);
		}
	}
	
	/**
	 * method getAllUniversities gets a list of the Universities in the DB
	 * @return universityList
	 */
	public List<University> getAllUniversities(){
		List<University> universityList = this.myDBcontroller.getAllUniversities();
		return universityList;
	}

	//TODO finish this enhancement
	public boolean editUniversity(String name) {
		University newUni = getUniversityByName(name);
		
		if(newUni == null) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * method addUniversity add university to the DB
	 * @param newUni
	 * @return boolean
	 * @throws CMCException
	 */
	public boolean addUniversity(String name, String state, String location, String control, int numOfStudents, double percentFemale, int SATVerbal, int SATMath, int expenses, double percentFinancialAid, int numOfApps, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, int qualOfLife) throws CMCException {
		
		return this.myDBcontroller.addUniversity(name, state, location, control, numOfStudents, percentFemale, SATVerbal, SATMath, expenses, percentFinancialAid, numOfApps, acceptanceRate, enrollmentRate, academicScale, socialScale, qualOfLife);
	}
	
	/**
	 * method getUniversityByName takes name and returns the University 
	 * @param String name
	 * @return University
	 */
	public University getUniversityByName(String name) {
		if(name == null) {
			return null;
		}
		List<University> loopUnis = getAllUniversities();
		
		for(University currUni : loopUnis) {
			if(currUni.getName().equals(name)) {
				return currUni;
			}
		}
		return null;
	}
	
	public boolean editUniversity(String name, String state, String location, String control, int numOfStudents, double percentFemale, int SATVerbal, int SATMath, int expenses, double percentFinancialAid, int numOfApps, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, int qualOfLife) throws CMCException {
		University current = this.myDBcontroller.getUniversity(name);
	    if (current == null) {
	        return false; 
	    }

	    state = (state == null) ? current.getState() : state;
	    location = (location == null) ? current.getLocation() : location;
	    control = (control == null) ? current.getControl() : control;
	    numOfStudents = (numOfStudents == 0) ? current.getPopulation() : numOfStudents;
	    percentFemale = (percentFemale == 0.0) ? current.getPercentFemale() : percentFemale;
	    SATVerbal = (SATVerbal == 0) ? current.getSatVerbal() : SATVerbal;
	    SATMath = (SATMath == 0) ? current.getSatMath() : SATMath;
	    expenses = (int) ((expenses == 0) ? current.getExpenses() : expenses);
	    percentFinancialAid = (percentFinancialAid == 0.0) ? current.getPercentFinancialAid() : percentFinancialAid;
	    numOfApps = (numOfApps == 0) ? ((University) current).getNumberApplicants() : numOfApps;
	    acceptanceRate = (acceptanceRate == 0.0) ? current.getAcceptanceRate() : acceptanceRate;
	    enrollmentRate = (enrollmentRate == 0.0) ? current.getEnrollmentRate() : enrollmentRate;
	    academicScale = (academicScale == 0) ? current.getAcademicScale() : academicScale;
	    socialScale = (socialScale == 0) ? current.getSocialScale() : socialScale;
	    qualOfLife = (qualOfLife == 0) ? current.getQualityScale() : qualOfLife;

		return this.myDBcontroller.editSchool(name, state, location, control, numOfStudents, percentFemale, SATVerbal, SATMath, expenses, percentFinancialAid, numOfApps, acceptanceRate, enrollmentRate, academicScale, socialScale, qualOfLife);
	}
}
