package cmc.backend;

/**
 * The Account class is an abstract representation of a user account in the system.
 * It contains common properties such as username, password, type, active status,
 * first name, and last name. It also provides methods to access and modify these properties
 * as well as a method to compare accounts.
 */
public abstract class Account {
    private String username;
    private String password;
    private char type;
    private char active;
    private String firstName;
    private String lastName;

    /**
     * Constructs a new Account with the specified details.
     *
     * @param firstname the first name of the account holder
     * @param lastname the last name of the account holder
     * @param username the unique username for the account
     * @param password the password for the account
     * @param type the account type (for example, admin or user)
     * @param active the active status of the account ('Y' for active, 'N' for inactive)
     */
    public Account(String firstname, String lastname, String username, String password, char type, char active) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.active = active;
        this.firstName = firstname;
        this.lastName = lastname;
    }

    /**
     * Compares this account with another account for equality.
     * The comparison is based on username, password, first name, last name, active status, and admin status.
     *
     * @param account the account to compare against
     * @return true if the accounts are considered equal; false otherwise
     */
    public boolean equal(Account account) {
        if (!this.username.equalsIgnoreCase(account.getUsername()))
            return false;
        if (!this.password.equalsIgnoreCase(account.getPassword()))
            return false;
        if (!this.firstName.equalsIgnoreCase(account.getFirstName()))
            return false;
        if (!this.lastName.equalsIgnoreCase(account.getLastName()))
            return false;
        if (this.active != account.getActive())
            return false;
        return isAdmin() == account.isAdmin();
    }

    /**
     * Returns the first name of the account holder.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the account holder.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the account holder.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the account holder.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the username associated with this account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this account.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for this account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this account.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the type of this account.
     *
     * @return the account type
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the type of this account.
     *
     * @param type the new account type
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * Returns the active status of this account.
     *
     * @return the active status ('Y' for active, 'N' for inactive)
     */
    public char getActive() {
        return active;
    }

    /**
     * Sets the active status of this account.
     *
     * @param active the new active status ('Y' for active, 'N' for inactive)
     */
    public void setActive(char active) {
        this.active = active;
    }

    /**
     * Determines whether this account is an admin.
     *
     * @return true if the account type indicates an admin; false otherwise
     */
    public boolean isAdmin() {
        return this.type == 'a';
    }
}
