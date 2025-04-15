package cmc.frontend;

import cmc.CMCException;
import cmc.backend.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class UserInteraction {
	
	private Account loggedInUser;
	
	private UniversityController universityController;
	private AccountController accountController;
	private DatabaseController databaseController;
	
	// Construct a UserInteraction using the basic (no parameter)
	// SystemController as the single underlying controller object.
	// TODO: Someday, we should refactor the single SystemController class
	//       into multiple classes for better organization of functionalities.
	public UserInteraction() {
		this.accountController = new AccountController();
		this.databaseController = new DatabaseController();
		this.loggedInUser = null;
	}

	// attempt to login, print message, and return success or failure
	public boolean login(Scanner s) throws CMCException {
		System.out.println("Enter Username: ");
		String username = s.nextLine();
		
		System.out.println("Enter Password: ");
		String password = s.nextLine();
		Account result = null;
		result = this.databaseController.getUser(username);
		if (result == null || ! result.getPassword().equals(password)) {
			System.out.println("Incorrect user or password");
			return false;
		}
		else if(result.getActive() != 'Y') {
			System.out.println("Deactivated account can not log in");
			return false;
		}
		else  {
			System.out.println("Login successful!");
			this.loggedInUser = result;
			return true;
		}
	}
	
	// returns true if there is a user to log out, otherwise false
	public boolean logout() {
		if (this.loggedInUser == null) {
			return false;
		}
		else {
			this.loggedInUser = null;
			return true;
		}
	}
	
	
	// ask the admin for details and then attempt to add a user to the
	// database
	public boolean addUser(Scanner s) throws CMCException {
		System.out.print("firstName: ");
		String firstName = s.nextLine();
		System.out.print("lastName: ");
		String lastName = s.nextLine();
		System.out.print("username: ");
		String username = s.nextLine();
		System.out.print("password: ");
		String password = s.nextLine();
		System.out.print("Admin? (Y or N): ");
		boolean isAdmin = false;
		if (s.nextLine().trim().equalsIgnoreCase("y"))
			isAdmin = true;
		
		return this.accountController.createAccount(firstName, lastName, username, password, isAdmin ? 'A' : 'U');
	}
	
	// ask the admin for a username and then remove that user from the
	// database
	public boolean removeUser(Scanner s) throws CMCException {
		System.out.print("Username: ");
		String username = s.nextLine();

		Account acc = this.databaseController.getUser(username);
		if (acc != null) {
			return this.accountController.deleteAccount(username);
		}
		return false;
	}
	
	public List<University> search(Scanner s) throws CMCException {
		
	    System.out.print("enter text to searh by certain criteria (Enter 1 to not search by this criterion): ");
	    String criteria = s.nextLine().trim();
    	
	    if (criteria.equals("1")) {
	    	return this.databaseController.getAllUniversities();
	    }
	   List<University> universities = this.databaseController.getAllUniversities();
	   List<University> selectedUnis = new ArrayList<University>();
	    if (universities.isEmpty()) {
	        System.out.println("No universities found with the given search criteria.");
	    } 
	    else if(criteria.toLowerCase().equals("name")) {
	    	System.out.println("enter name of school: ");
	    	String name = s.nextLine();
	    	
	    	System.out.println("University found:");
	    	
	        for (University university : universities) {
	        	if(university.getName().equals(name)) {
	        		selectedUnis.add(university);
	        	}
	    }
	        return selectedUnis;
	    }
	    else if(criteria.toLowerCase().equals("location")) {
	    	System.out.println("Enter location of school, SUBURBAN, URBAN, SMALL-CITY: ");
	    	String location = s.nextLine();
	    	
	    	System.out.println("Universities found:");
	    	
	        for (University university : universities) {
	           if(location.equals(university.getLocation())) {
	        	   selectedUnis.add(university);
	           }
	        }
	        return selectedUnis;
	    }
	    
	    else if(criteria.toLowerCase().equals("state")) {
	    	System.out.println("Enter state of schools: ");
	    	String state = s.nextLine();
	    	
	        System.out.println("Universities found:");
	        
	        for (University university : universities) {
	            if(state.equals(university.getState())) {
	            	selectedUnis.add(university);
	            }
	        }
	        return selectedUnis;
	    }
	    else if(criteria.toLowerCase().equals("control")) {
	    	System.out.println("Enter the control of school, PRIVATE, CITY, STATE: ");
	    	String control = s.nextLine();
	    	
	        System.out.println("Universities found:");
	        
	        for (University university : universities) {
	            if(control.equals(university.getControl())) {
	            	selectedUnis.add(university);
	            }
	        }
	        return selectedUnis;
	    }
	    else if(criteria.toLowerCase().equals("population")) {
	    	System.out.println("Enter the population of the school, ranges are 10-15k, 15-25k, 25-35k, 35-40k: ");
	    	String population = s.nextLine();
	    	int num = Integer.parseInt(population);
	        System.out.println("Universities found:");
	        
	           if(num>=10000 && num<=15000) {
	        	   for (University university : universities) {
	        		   if(university.getPopulation()>= 10000 && university.getPopulation()<=15000) {
	        			   selectedUnis.add(university);
	        		   }
	        	   }
	        	   return selectedUnis;
	           }
	           else if(num>=15001 && num<=25000) {
	        	   for (University university : universities) {
	        		   if(university.getPopulation()>= 15001 && university.getPopulation()<=25000) {
	        			   selectedUnis.add(university);
	        		   }
	        	   }
	        	   return selectedUnis;
	           }
	           else if(num>=25001 && num<=35000) {
	        	   for (University university : universities) {
	        		   if(university.getPopulation()>= 25001 && university.getPopulation()<=35000) {
	        			   selectedUnis.add(university);
	        		   }
	        	   }
	        	   return selectedUnis;
	           }
	           else if(num>=35001 && num<=40000) {
	        	   for (University university : universities) {
	        		   if(university.getPopulation()>= 35001 && university.getPopulation()<=40000) {
	        			   selectedUnis.add(university);
	        		   }
	        	   }
	        	   return selectedUnis;
	           }
	        }
	    
	    else if(criteria.toLowerCase().equals("percentfemale")) {
	    	System.out.println("Enter the percent of females in the school, ranges are 20-40, 40-60, 60-80, 80-100%: ");
	    	String percfem = s.nextLine();
	    	int num = Integer.parseInt(percfem);

	        System.out.println("Universities found:");
	        
	        if(num>=20 && num <=40) {
	        	for(University university : universities) {
	        		if(university.getPercentFemale() >= 20 && university.getPercentFemale() <=40) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=41 && num <=60) {
	        	for(University university : universities) {
	        		if(university.getPercentFemale() >= 41 && university.getPercentFemale() <=60) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=61 && num <=80) {
	        	for(University university : universities) {
	        		if(university.getPercentFemale() >= 61 && university.getPercentFemale() <=80) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=81 && num <=100) {
	        	for(University university : universities) {
	        		if(university.getPercentFemale() >= 81 && university.getPercentFemale() <=100) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("satverbal")) {
	    	System.out.println("Enter the SAT verbal, -1, 400-500, 500-600, 600-700: ");
	    	String satverbal = s.nextLine();
	    	int num = Integer.parseInt(satverbal);
	    	
	        System.out.println("Universities found:");
	        if(num == -1) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatVerbal()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 400 && num <= 500) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatVerbal()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 501 && num <= 600) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatVerbal()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 601 && num <= 700) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatVerbal()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("satmath")) {
	    	System.out.println("Enter the SAT math, -1, 400-500, 500-600, 600-700, 700-800: ");
	    	String satmath = s.nextLine();
	    	int num = Integer.parseInt(satmath);
	    	
	        System.out.println("Universities found:");
	        if(num == -1) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatMath()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 400 && num <= 500) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatMath()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 501 && num <= 600) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatMath()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 601 && num <= 700) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatMath()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	        else if(num >= 701 && num <= 800) {
	        	 for (University university : universities) {
	 	            if(num == university.getSatMath()) {
	 	            	selectedUnis.add(university);
	 	            }
	 	        }
	        	 return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("expenses")) {
	    	System.out.println("Enter expenses, 10-20, 20-30, 30-40k: ");
	    	String expenses = s.nextLine();
	    	int num = Integer.parseInt(expenses);

	        System.out.println("Universities found:");
	        if(num >= 10000 && num <= 20000) {
	        	for (University university : universities) {
		           if(university.getExpenses()>=10000 && university.getExpenses()<=20000) {
		        	   selectedUnis.add(university);
		           }
		        }
	        	return selectedUnis;
	        }
	        else if(num >= 20001 && num <= 30000) {
	        	for (University university : universities) {
		           if(university.getExpenses()>=20001 && university.getExpenses()<=30000) {
		        	   selectedUnis.add(university);
		           }
		        }
	        	return selectedUnis;
	        }
	        else if(num >= 30001 && num <= 40000) {
	        	for (University university : universities) {
		           if(university.getExpenses()>=30001 && university.getExpenses()<=40000) {
		        	   selectedUnis.add(university);
		           }
		        }
	        	return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("percentfinancialaid")) {
	    	System.out.println("Enter the percent of financial aid in the school, ranges are 20-40, 40-60, 60-80, 80-100%: ");
	    	String finAid = s.nextLine();
	    	int num = Integer.parseInt(finAid);

	        System.out.println("Universities found:");
	        
	        if(num>=20 && num <=40) {
	        	for(University university : universities) {
	        		if(university.getPercentFinancialAid() >= 20 && university.getPercentFinancialAid() <=40) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=41 && num <=60) {
	        	for(University university : universities) {
	        		if(university.getPercentFinancialAid() >= 41 && university.getPercentFinancialAid() <=60) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=61 && num <=80) {
	        	for(University university : universities) {
	        		if(university.getPercentFinancialAid() >= 61 && university.getPercentFinancialAid() <=80) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=81 && num <=100) {
	        	for(University university : universities) {
	        		if(university.getPercentFinancialAid() >= 81 && university.getPercentFinancialAid() <=100) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }	       
	    }
	    else if(criteria.toLowerCase().equals("numberapplicants")) {
	    	System.out.println("Enter the number of applicants for the school, ranges are 4-8k, 8-12k, 12-17k: ");
	    	String numapp = s.nextLine();
	    	int num = Integer.parseInt(numapp);

	        System.out.println("Universities found:");
	        if(num >= 4000 && num<=8000) {
	        	for (University university : universities) {
		            if(university.getNumberApplicants() >= 4000 && university.getNumberApplicants()<=8000) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num >= 8001 && num<=12000) {
	        	for (University university : universities) {
		            if(university.getNumberApplicants() >= 8001 && university.getNumberApplicants()<=12000) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num >= 12001 && num<=17000) {
	        	for (University university : universities) {
		            if(university.getNumberApplicants() >= 12001 && university.getNumberApplicants()<=17000) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("acceptancerate")) {
	    	System.out.println("Enter the Acceptance Rate for the school, ranges are 20-40, 40-60, 60-80, 80-100%: ");
	    	String arate = s.nextLine();
	    	int num = Integer.parseInt(arate);

	        System.out.println("Universities found:");
	        
	        if(num>=20 && num <=40) {
	        	for(University university : universities) {
	        		if(university.getAcceptanceRate() >= 20 && university.getAcceptanceRate() <=40) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=41 && num <=60) {
	        	for(University university : universities) {
	        		if(university.getAcceptanceRate() >= 41 && university.getAcceptanceRate() <=60) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=61 && num <=80) {
	        	for(University university : universities) {
	        		if(university.getAcceptanceRate() >= 61 && university.getAcceptanceRate() <=80) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=81 && num <=100) {
	        	for(University university : universities) {
	        		if(university.getAcceptanceRate() >= 81 && university.getAcceptanceRate() <=100) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }	       
	    }
	    else if(criteria.toLowerCase().equals("enrollmentrate")) {
	    	System.out.println("Enter the Enrollment Rate for the school, ranges are 15-20, 20-40, 40-60, 60-80, 80-100%: ");
	    	String erate = s.nextLine();
	    	int num = Integer.parseInt(erate);

	        System.out.println("Universities found:");
	        
	        if(num>=15 && num <=20) {
	        	for(University university : universities) {
	        		if(university.getEnrollmentRate() >= 15 && university.getEnrollmentRate() <=20) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=21 && num <=40) {
	        	for(University university : universities) {
	        		if(university.getEnrollmentRate() >= 20 && university.getEnrollmentRate() <=40) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=41 && num <=60) {
	        	for(University university : universities) {
	        		if(university.getEnrollmentRate() >= 41 && university.getEnrollmentRate() <=60) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=61 && num <=80) {
	        	for(University university : universities) {
	        		if(university.getEnrollmentRate() >= 61 && university.getEnrollmentRate() <=80) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }
	        else if(num>=81 && num <=100) {
	        	for(University university : universities) {
	        		if(university.getEnrollmentRate() >= 81 && university.getEnrollmentRate() <=100) {
	        			selectedUnis.add(university);
	        		}
	        	}
	        	return selectedUnis;
	        }	       
	    }
	    else if(criteria.toLowerCase().equals("academicscale")) {
	    	System.out.println("Enter the academic scale for the university, 1-2-3-4-5: ");
	    	String aScale = s.nextLine();
	    	
	    	int num = Integer.parseInt(aScale);

	        System.out.println("Universities found:");
	        if(num==1) {
	        	for (University university : universities) {
		            if(university.getAcademicScale()==1) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        
	        else if(num==2) {
	        	for (University university : universities) {
		            if(university.getAcademicScale()==2) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==3) {
	        	for (University university : universities) {
		            if(university.getAcademicScale()==3) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==4) {
	        	for (University university : universities) {
		            if(university.getAcademicScale()==4) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==5) {
	        	for (University university : universities) {
		            if(university.getAcademicScale()==5) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("socialscale")) {
	    	System.out.println("Enter the social scale for the university, 1-2-3-4-5: ");
	    	String sScale = s.nextLine();
	    	
	    	int num = Integer.parseInt(sScale);

	        System.out.println("Universities found:");
	        if(num==1) {
	        	for (University university : universities) {
		            if(university.getSocialScale()==1) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        
	        else if(num==2) {
	        	for (University university : universities) {
		            if(university.getSocialScale()==2) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==3) {
	        	for (University university : universities) {
		            if(university.getSocialScale()==3) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==4) {
	        	for (University university : universities) {
		            if(university.getSocialScale()==4) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==5) {
	        	for (University university : universities) {
		            if(university.getSocialScale()==5) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	    }
	    else if(criteria.toLowerCase().equals("qualityscale")) {
	    	System.out.println("Enter the quality scale for the university, 1-2-3-4-5: ");
	    	String qScale = s.nextLine();
	    	
	    	int num = Integer.parseInt(qScale);
	    	

	        System.out.println("Universities found:");
	        if(num==1) {
	        	for (University university : universities) {
		            if(university.getQualityScale()==1) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        
	        else if(num==2) {
	        	for (University university : universities) {
		            if(university.getQualityScale()==2) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==3) {
	        	for (University university : universities) {
		            if(university.getQualityScale()==3) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==4) {
	        	for (University university : universities) {
		            if(university.getQualityScale()==4) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	        else if(num==5) {
	        	for (University university : universities) {
		            if(university.getQualityScale()==5) {
		            	selectedUnis.add(university);
		            }
		        }
	        	return selectedUnis;
	        }
	    }
	    return null;
	}
	public boolean editProfile(Scanner s) throws CMCException {
	    if (this.loggedInUser == null) {
	        System.out.println("No user is logged in.");
	        return false;
	    }

	    System.out.println("Enter the updates you would like to make. (Leave blank if no change is wanted)");

	    // Update username
	    System.out.println("Current Username: " + loggedInUser.getUsername());
	    System.out.println("Username cannot be changed.");

	    // Update password
	    System.out.println("Current Password: " + loggedInUser.getPassword() + "\nNew Password:");
	    String newPassword = s.nextLine();
	    if (newPassword.isEmpty()) {
	        newPassword = loggedInUser.getPassword();
	    }

	    // Update first name
	    System.out.println("Current First Name: " + loggedInUser.getFirstName() + "\nNew First Name:");
	    String newFirstName = s.nextLine();
	    if (newFirstName.isEmpty()) {
	        newFirstName = loggedInUser.getFirstName();
	    }

	    // Update last name
	    System.out.println("Current Last Name: " + loggedInUser.getLastName() + "\nNew Last Name:");
	    String newLastName = s.nextLine();
	    if (newLastName.isEmpty()) {
	        newLastName = loggedInUser.getLastName();
	    }

	    // Save changes to the database
	    if (this.accountController.editAccount(loggedInUser.getUsername(), newFirstName, newLastName, newPassword, loggedInUser.getType(), loggedInUser.getActive())) {
	        System.out.println("Profile updated successfully.");
	        return true;
	    } else {
	        System.out.println("Failed to update profile.");
	        return false;
	    }
	}
	
	
	// get the list of saved school names for the currently-logged-in user
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getSavedSchools() {
		return this.databaseController.getUserSavedSchoolMap();
	}

	/**
	 * Get the current username for the current user logged in via
	 * this UserInteraction class.
	 * 
	 * @return the username for the logged in user
	 */
	public Account getLoggedInUser() {
		return this.loggedInUser;
	}

	public boolean saveSchool(Scanner s) {
		/*
		List<String> schoolsSaved1 = getSavedSchools(username);

		for(String schol: schoolsSaved1) {			
			if(schol.equals(name)) {
				new IllegalArgumentException("School Already Saved");
			}
		}
		return this.databaseController.saveSchool(username, name);
		*/
		System.out.println("Enter the school you would like to save:");
		 String name = s.nextLine();
		
		return this.databaseController.saveSchool1(loggedInUser, name);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return this.databaseController.getAllUsers();
	}

	public boolean removeSchool(Scanner s) {
		System.out.println("Enter the school you would like to remove:");
		 String name = s.nextLine();

		return this.databaseController.removeSavedSchool(loggedInUser, name);
	}
	
	//TODO: FINISH EDIT USER METHOD. MIGHT WANT TO RETHINK HOW IT WORKS TBH
	// User gets prompted for username, then asked for each field they want to change. CANNOT CHANGE USERNAME.
	public boolean editUser(Scanner s) throws CMCException {
	    System.out.println("Enter the username of the user you would like to edit:");
	    String username = s.nextLine();

	    Account editUser = databaseController.getUser(username);
	    if (editUser == null) {
	        System.out.println("User not found.");
	        return false;
	    }
		char newTypeChar = editUser.getType();

	    System.out.println("Enter the updates you would like to make. (Leave blank if no change is wanted)");

	    // Update password
	    System.out.println("Current Password: " + editUser.getPassword() + "\nNew Password:");
	    String newPassword = s.nextLine();
	    if (newPassword.equals("")) {
	        newPassword = editUser.getPassword();
	    }

	    // Update first name
	    System.out.println("Current First Name: " + editUser.getFirstName() + "\nNew First Name:");
	    String newFirstName = s.nextLine();
	    if (newFirstName.equals("")) {
	        newFirstName = editUser.getFirstName();
	    }

	    // Update last name
	    System.out.println("Current Last Name: " + editUser.getLastName() + "\nNew Last Name:");
	    String newLastName = s.nextLine();
	    if (newLastName.equals("")) {
	        newLastName = editUser.getLastName();
	    }

	    // Update user type
	    while (true) {
	        System.out.println("Current User Type: " + editUser.getType() + "\nNew User Type (u or a):");
	        String newType = s.nextLine();
	        if (newType.equals("")) {
	            break;
	        } else if (newType.equalsIgnoreCase("u") || newType.equalsIgnoreCase("a")) {
	            newTypeChar = newType.toLowerCase().charAt(0);
	            break;
	        } else {
	            System.out.println("Invalid input. Try again.");
	        }
	    }

		//update active status
		System.out.println("Current Active Status: " + editUser.getActive() + "\nNew Active Status (Y or N):");
		String newActive = s.nextLine();
		char newActiveChar = editUser.getActive();
		while(true){
			if (newActive.equalsIgnoreCase("y") || newActive.equalsIgnoreCase("n")) {
				newActiveChar = newActive.toUpperCase().charAt(0);
				break;
			} else if (newActive.equals("")) {
				break;
			} else {
				System.out.println("Invalid input. Try again.");
				newActive = s.nextLine();
			}
		}

	    // Save changes to the database
		// String username, String firstname, String lastname, String password, char type, char active
	    if (this.accountController.editAccount(username, newFirstName, newLastName, newPassword, newTypeChar, newActiveChar)) {
	        System.out.println("User details updated successfully.");
	    } else {
	        System.out.println("Failed to update user details.");
	        return false;
	    }
	    return true;
	}
	

}
