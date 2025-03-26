package cmc.backend;

import java.util.*;

public class Admin extends Account{
	
	/**
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param type
	 * @param active
	 */
	public Admin(String firstname, String lastname, String username, String password, char type, char active) {
		super(firstname, lastname, username, password, type, active);
	}
	
	
}
