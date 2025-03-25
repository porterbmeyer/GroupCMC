package cmc.backend;

import java.util.List;

public class UniversityController {

	private DatabaseController myDBcontroller;

	/**
	 * @param myDBcontroller
	 */
	public UniversityController(DatabaseController myDBcontroller) {
		this.myDBcontroller = myDBcontroller;
	}
	
	/**
	 * method deleteUniversity removes university from the DB
	 * @param uniName
	 * @return boolean
	 */
	public boolean deleteUniversity(String uniName) {
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
	 */
	public boolean addUniversity(University newUni) {
		List<University> loopUnis = getAllUniversities();
		
		for(University u : loopUnis) {
			if(u == newUni) {
				return false;
			}
		}
		return this.myDBcontroller.addUniversity(newUni);
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
