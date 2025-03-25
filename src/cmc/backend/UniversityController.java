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
	public boolean addUniversity(String name, String location, String state, int population, double tuition, String control,
			double percentFemale, int satVerbal, int satMath, double expenses, double percentFinancialAid,
			int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale,
			int qualityScale) throws CMCException {
		List<University> loopUnis = getAllUniversities();
		
		for(University u : loopUnis) {
			if(u == newUni) {
				return false;
			}
		}
		return this.myDBcontroller.addUniversity(name,location, state, population, tuition, control, percentFemale, satVerbal, satMath, expenses, percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate, academicScale, socialScale,qualityScale);
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
}
