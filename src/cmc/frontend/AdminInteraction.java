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
	private DatabaseController databaseController;
	private Admin loggedInAdmin;
	
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
	
	public List<University> getAllUniversities(){
		return this.databaseController.getAllUniversities();
		
	}
	
	public boolean deactivateUser(Scanner s) throws CMCException { 
		System.out.println("Enter username to deactivate: ");
		String username = s.nextLine();
		
		return this.databaseController.deactivateUser(username);
	}
	
	public boolean reActivateUser(Scanner s) throws CMCException { 
		System.out.println("Enter username to reactivate: ");
		String username = s.nextLine();
		
		return this.databaseController.reActivateUser(username);
	}
	
	// returns true if there is a user to log out, otherwise false
		public boolean logout() {
			if (this.loggedInAdmin == null) {
				return false;
			}
			else {
				this.loggedInAdmin = null;
				return true;
			}
		}

	public boolean deleteUser(Scanner s) throws CMCException {

		System.out.print("Enter User name to delete: ");
		String username = s.nextLine();
		return this.accountController.deleteAccount(username);
	}

	public boolean deleteUniversity(Scanner s) throws CMCException {

		System.out.print("Enter university name to delete: ");
		String username = s.nextLine();
		return this.universityController.deleteUniversity(username);
	}

	public boolean addUniversity(Scanner scanner) throws CMCException {
		System.out.print("Enter university name: ");
        String name = scanner.nextLine();

        System.out.print("Enter state: ");
        String state = scanner.nextLine();

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.print("Enter control (Public/Private): ");
        String control = scanner.nextLine();

        System.out.print("Enter population: ");
        int population = scanner.nextInt();

        System.out.print("Enter percentage of female students: ");
        double percentFemale = scanner.nextDouble();

        System.out.print("Enter SAT verbal score: ");
        int satVerbal = scanner.nextInt();

        System.out.print("Enter SAT math score: ");
        int satMath = scanner.nextInt();

        System.out.print("Enter expenses: ");
        int expenses = scanner.nextInt();

        System.out.print("Enter percentage of financial aid: ");
        double percentFinancialAid = scanner.nextDouble();

        System.out.print("Enter number of applicants: ");
        int numberApplicants = scanner.nextInt();

        System.out.print("Enter acceptance rate: ");
        double acceptanceRate = scanner.nextDouble();

        System.out.print("Enter enrollment rate: ");
        double enrollmentRate = scanner.nextDouble();

        System.out.print("Enter academic scale rating: ");
        int academicScale = scanner.nextInt();

        System.out.print("Enter social scale rating: ");
        int socialScale = scanner.nextInt();

        System.out.print("Enter quality scale rating: ");
        int qualityScale = scanner.nextInt();
        
		return this.universityController.addUniversity(name, state, location, control, population, percentFemale, satVerbal, satMath, expenses, percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate, academicScale, socialScale, qualityScale);
	}
	
	
}
	
