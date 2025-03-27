package cmc.backend;



import cmc.CMCException;

public class AccountController {
	
	private DatabaseController myDBcontroller;

	public AccountController() {
		this.myDBcontroller = new DatabaseController();
	}
	

	
	public boolean createAccount(String firstname, String lastname, String username, String password, char type, char active) throws CMCException {
		Account newacc = new User(firstname, lastname, username, password, 'U', 'Y');
		
		return this.myDBcontroller.addAccount(newacc);
	}
	
	public boolean deleteAccount(String username) throws CMCException {
		Account acc = this.myDBcontroller.getUser(username);
		if (acc != null) {
			return this.myDBcontroller.deleteAccount(acc);
		}
		return false;
	}
	
	public Account updateAccountDetails(String firstname, String lastname, String username, String password, char type, char active) throws CMCException {
		Account acc = this.myDBcontroller.getUser(username);
		if (acc != null) {
			acc.setFirstName(firstname);
			acc.setLastName(lastname);
			acc.setPassword(password);
			acc.setType(type);
			acc.setActive(active);
			this.myDBcontroller.updateAccount(acc);
			return acc;
		}
		return null;
	}
	
	public String changePassword(String username, String oldPassword, String newPassword) throws CMCException {
		Account acc = this.myDBcontroller.getUser(username);
		if (acc != null) {
			if (acc.getPassword().equals(oldPassword)) {
				acc.setPassword(newPassword);
				this.myDBcontroller.updateAccount(acc);
				return "Password changed successfully.";
			} else {
				return "Old password is incorrect.";
			}
		}
		return "Account not found.";
	}
	
	public Account login(String userName, String password) throws CMCException {
		Account acc = this.myDBcontroller.getUser(userName);
		System.out.println(acc);
		if (acc != null && acc.getPassword().equals(password) && acc.getActive() == 'Y') {
			return acc;
		}
		return null;
	}
	
	public boolean logOut() {
		return true;
	}
}
	

