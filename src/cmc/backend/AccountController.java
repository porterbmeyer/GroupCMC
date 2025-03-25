package cmc.backend;

import java.time.LocalDateTime;
import java.util.List;

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
		List<Account> loopUnis = this.myDBcontroller.getAllaccounts();
		
		for(Account acc : loopUnis) {
			if( acc.getUsername() == username) {
				return this.myDBcontroller.deleteAccount(acc);
			}
		}
		return false;
	}
	
	public Account updateAccountDetails(String firstname, String lastname, String username, String Password, char type, char active) {
		return null;
	}
	
	public String changePassword(String username, String oldPassword, String newPassword) {
		return null;
	}
	
	public Account login(String userName, String password) {
		return null;
	}
	
	public boolean logOut() {
		return false;
	}
	
}
