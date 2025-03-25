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
	
	public boolean createAccount(int accountID, String username, String email, String password, char type, char active) {
		Account newacc = new Account(accountID, username, email, password, 'U', 'Y');
		
		return this.myDBcontroller.addAccount(newacc);
	}
	
	public boolean deleteAccount(int accountID) {
		List<Account> loopUnis = this.myDBcontroller.getAllaccounts();
		
		for(Account acc : loopUnis) {
			if( acc.getAccountID() == accountID) {
				return this.myDBcontroller.deleteAccount(acc);
			}
		}
		return false;
	}
	
	
	public Account updateAccountDetails(String username, String email, String Password, int accountID) {
		return null;
	}
	
	public String changePassword(int accountID, String oldPassword, String newPassword) {
		return null;
	}
	
	public Account login(String userName, String password) {
		return null;
	}
	
	public boolean logOut(int accountID) {
		return false;
	}
	
}
