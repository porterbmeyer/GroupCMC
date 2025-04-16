package cmc.backend;

import java.util.*;

/**
 * The Admin class represents an administrative account with elevated privileges.
 * It extends the base Account class.
 */
public class Admin extends Account {

    /**
     * Constructs a new Admin account with the specified details.
     *
     * @param firstname the first name of the admin
     * @param lastname the last name of the admin
     * @param username the username for the admin account
     * @param password the password for the admin account
     * @param type the type of account (should represent an admin)
     * @param active the active status of the account (for example, 'Y' for active)
     */
    public Admin(String firstname, String lastname, String username, String password, char type, char active) {
        super(firstname, lastname, username, password, type, active);
    }
}
