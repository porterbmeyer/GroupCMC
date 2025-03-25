package cmc.backend;

public class Account {
	private int accountID;
	private String username;
	private String email;
	private String password;
	private char type;
	private char active;
	private String firstName;
	private String lastName;
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
	 * @param accountID
	 * @param username
	 * @param email
	 * @param password
	 * @param type
	 * @param active
	 */
	public Account(int accountID, String username, String email, String password, char type, char active) {
		super();
		this.accountID = accountID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
		this.active = active;
	}
	
	public Account(String accountID, String username, String email, String password, String type, String active) {
		super();
		this.accountID = Integer.parseInt(accountID);
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type.charAt(0);
		this.active = active.charAt(0);
	}
	
	public Account(String username, String password, char type, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	
	
}
