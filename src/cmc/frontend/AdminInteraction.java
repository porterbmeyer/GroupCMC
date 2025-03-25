package cmc.frontend;

import java.util.List;

import cmc.CMCException;
import cmc.backend.AccountController;
import cmc.backend.Admin;
import cmc.backend.DatabaseController;
import cmc.backend.University;
import cmc.backend.UniversityController;


public class AdminInteraction {
	
	private AccountController accountController;
	private DatabaseController databaseController;
	private UniversityController universityController;
	private Admin loggedInAdmin;
	
	public boolean createUser(String firstname, String lastname, String username, String password, char type, char active) {
		return this.accountController.createAccount(firstname, lastname, username, password, type, active);
	}
	
	
	public List<String[]> getAllUsers(){
		return this.databaseController.getAllUsers();
	}
	
	public boolean deleteUser(String username) throws CMCException {
		return this.databaseController.removeUser(username);
	}
	
	public boolean addUniversity(University u) throws CMCException {
		return this.databaseController.addUniversity(u);
	}
}
	
