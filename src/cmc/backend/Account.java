package cmc.backend;

import cmc.CMCException;

public abstract class Account {
	private String username;
	private String password;
	private char type;
	private char active;
	private String firstName;
	private String lastName;
	
	/**
	 * @param username
	 * @param lastname
	 * @param firstname
	 * @param password
	 * @param type
	 * @param active
	 */
	public Account(String firstname, String lastname, String username, String password, char type, char active) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.active = active;
		this.firstName = firstname;
		this.lastName = lastname;
	}	
	
	public boolean equal(Account account) {
		if(!this.username.equalsIgnoreCase(account.getUsername()))
			return false;
		
		if(!this.password.equalsIgnoreCase(account.getPassword()))
			return false;
		
		if(!this.firstName.equalsIgnoreCase(account.getFirstName()))
			return false;
		
		if(!this.lastName.equalsIgnoreCase(account.getLastName()))
			return false;
		
		if(this.active != account.getActive())
			return false;
	
		return isAdmin() == account.isAdmin();
		
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}
	/**
	 * @return the active
	 */
	public char getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(char active) {
		this.active = active;
	}
	
    

    public boolean isAdmin() {
        return this.type == 'a';
    }
}
