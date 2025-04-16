package cmc.backend;

import cmc.CMCException;

/**
 * The AccountController class manages operations related to user accounts.
 * These operations include account creation, deletion, detail updates, login, password changes, and log out.
 */
public class AccountController {

    private DatabaseController myDBcontroller;

    /**
     * Constructs a new AccountController and initializes the DatabaseController.
     */
    public AccountController() {
        this.myDBcontroller = new DatabaseController();
    }

    /**
     * Creates a new user account with the given details.
     *
     * @param firstname the first name of the user
     * @param lastname the last name of the user
     * @param username the unique username
     * @param password the password for the account
     * @param type the type of account (for example, admin or regular user)
     * @return true if the account was created successfully; false otherwise
     * @throws CMCException if an error occurs during account creation
     */
    public boolean createAccount(String firstname, String lastname, String username, String password, char type) throws CMCException {
        return this.myDBcontroller.addUser(firstname, lastname, username, password, type);
    }

    /**
     * Deletes an existing account from the database.
     *
     * @param username the username of the account to be deleted
     * @return true if the account was deleted successfully; false otherwise
     * @throws CMCException if an error occurs during deletion
     */
    public boolean deleteAccount(String username) throws CMCException {
        Account acc = this.myDBcontroller.getUser(username);
        if (acc != null) {
            return this.myDBcontroller.deleteAccount(acc);
        }
        return false;
    }

    /**
     * Updates the details of an existing account.
     *
     * @param firstname the new first name of the user
     * @param lastname the new last name of the user
     * @param username the username of the account to update
     * @param password the new password for the account
     * @param type the updated type for the account
     * @param active the updated active status ('Y' for active, 'N' for inactive)
     * @return the updated Account object if the update is successful; null if the account is not found
     * @throws CMCException if an error occurs during the update
     */
    public Account updateAccountDetails(String firstname, String lastname, String username, String password, char type, char active) throws CMCException {
        Account acc = this.myDBcontroller.getUser(username);
        if (acc != null) {
            acc.setFirstName(firstname);
            acc.setLastName(lastname);
            acc.setPassword(password);
            acc.setType(type);
            // Active status update is handled separately if needed.
            this.myDBcontroller.updateAccount(acc);
            return acc;
        }
        return null;
    }

    /**
     * Changes the password for the specified account.
     *
     * @param username the username of the account
     * @param oldPassword the current password
     * @param newPassword the new password to set
     * @return a message indicating whether the password change was successful, if the old password was incorrect,
     *         or if the account was not found
     * @throws CMCException if an error occurs during the update
     */
    public String changePassword(String username, String oldPassword, String newPassword) throws CMCException {
        Account acc = this.myDBcontroller.getUser(username);
        System.out.println(acc);
        if (acc != null && acc.getPassword().equals(oldPassword) && acc.getActive() == 'Y') {
            acc.setPassword(newPassword);
            this.myDBcontroller.updateAccount(acc);
            return "Password changed successfully.";
        } else if (acc != null) {
            return "Old password is incorrect.";
        }
        return "Account not found.";
    }

    /**
     * Logs in a user by validating the username, password, and active status.
     *
     * @param userName the username of the account
     * @param password the password provided for login
     * @return the Account object if login is successful; null otherwise
     * @throws CMCException if an error occurs during the login process
     */
    public Account login(String userName, String password) throws CMCException {
        Account acc = this.myDBcontroller.getUser(userName);
        System.out.println(acc);
        if (acc != null && acc.getPassword().equals(password) && acc.getActive() == 'Y') {
            return acc;
        }
        return null;
    }

    /**
     * Logs out a user.
     *
     * @return true indicating that logout was successful
     */
    public boolean logOut() {
        return true;
    }

    /**
     * Edits an existing account's details. Only non-empty parameters are used to update the account.
     *
     * @param username the username of the account to edit
     * @param firstname the new first name; if empty, the current value is retained
     * @param lastname the new last name; if empty, the current value is retained
     * @param password the new password; if empty, the current value is retained
     * @param type the new account type; if blank, the current value is retained
     * @param active the new active status; if blank, the current value is retained
     * @return true if the account was updated successfully; false if the account was not found
     * @throws CMCException if an error occurs during the update
     */
    public boolean editAccount(String username, String firstname, String lastname, String password, char type, char active) throws CMCException {
        Account acc = this.myDBcontroller.getUser(username);
        if (acc == null) {
            return false;
        }
        if (!firstname.isEmpty()) {
            acc.setFirstName(firstname);
        }
        if (!lastname.isEmpty()) {
            acc.setLastName(lastname);
        }
        if (!password.isEmpty()) {
            acc.setPassword(password);
        }
        if (type != ' ') {
            acc.setType(type);
        }
        if (active != ' ') {
            acc.setActive(active);
        }
        this.myDBcontroller.updateAccount(acc);
        return true;
    }
}
