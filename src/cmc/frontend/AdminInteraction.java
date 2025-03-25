package cmc.frontend;

import java.util.List;
import java.util.Scanner;

import cmc.CMCException;
import cmc.backend.AccountController;
import cmc.backend.Admin;
import cmc.backend.DatabaseController;
import cmc.backend.University;
import cmc.backend.UniversityController;
import cmc.backend.User;


public class AdminInteraction {
	
	private AccountController accountController;
	private UniversityController universityController;
	private Admin loggedInAdmin;
	private DatabaseController databaseController;
	
	public boolean createUser(Scanner s) throws CMCException {
		
		System.out.print("First Name: ");
		String firstname = s.nextLine();
		System.out.print("Last Name: ");
		String lastname = s.nextLine();
		System.out.print("Username Name: ");
		String userName = s.nextLine();
		System.out.print("Password: ");
		String Password = s.nextLine();
		System.out.print("Admin? (A or U): ");
		String type = s.nextLine();
		String active = "Y";
		
		return this.accountController.createAccount(firstname, lastname, userName, Password, type.charAt(0), active.charAt(0));
	}
	
	
	public List<User> getAllUsers(){
		return this.databaseController.getAllUsers();
		
	}
	
	public boolean deleteUser(Scanner s) throws CMCException {
		String username = s.nextLine();
		return this.accountController.deleteAccount(username);
	}
	
	public boolean addUniversity(Scanner s) throws CMCException {
		return false;
		//return this.universityController.addUniversity(u);
	}
}
	
