package cmc.backend;



import cmc.CMCException;

public class AccountController {
	
	private DatabaseController myDBcontroller;

	/**
	 * @param myDBcontroller
	 */
	public AccountController(DatabaseController myDBcontroller) {
		this.myDBcontroller = myDBcontroller;
	}
	
	public boolean createAccount(String firstname, String lastname, String username, String password, char type, char active) {
		Account newacc = new Account(firstname, lastname, username, password, 'U', 'Y');
		
		return this.myDBcontroller.addAccount(newacc);
	}
	
	public boolean deleteAccount(String username) {
		Account acc = this.myDBcontroller.getAccount(username);
		if (acc != null) {
			return this.myDBcontroller.deleteAccount(acc);
		}
		return false;
	}
	
	public Account updateAccountDetails(String firstname, String lastname, String username, String password, char type, char active) throws CMCException {
		Account acc = this.myDBcontroller.getAccount(username);
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
		Account acc = this.myDBcontroller.getAccount(username);
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
	
	public Account login(String userName, String password) {
		Account acc = this.myDBcontroller.getAccount(userName);
		if (acc != null && acc.getPassword().equals(password) && acc.getActive() == 'Y') {
			return acc;
		}
		return null;
	}
	
	public boolean logOut() {
		return true;
	}
}
	

