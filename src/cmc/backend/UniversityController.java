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
	public boolean addUniversity(String name, String state, String location, String control, int numOfStudents, int percFemale, int SATVerbal, int SATMath, int expenses, int percFinAId, int numOfApps, int percAdmit, int percEnroll, int academicScale, int socialScale, int qualOfLife) throws CMCException {
		
		return this.myDBcontroller.addUniversity(name, state, location, control, numOfStudents, percFemale, SATVerbal, SATMath, expenses, percFinAId, numOfApps, percAdmit, percEnroll, academicScale, socialScale, qualOfLife);
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
