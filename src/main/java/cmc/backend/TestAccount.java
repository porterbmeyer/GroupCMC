package cmc.backend;

/**
 * The TestAccount class is used for testing account-related functionality.
 * It extends the Account class.
 */
public class TestAccount extends Account {

    /**
     * Constructs a new TestAccount with the specified user details.
     *
     * @param firstname the first name of the test account
     * @param lastname  the last name of the test account
     * @param username  the username for the test account
     * @param password  the password for the test account
     * @param type      the type of account
     * @param active    the active status of the test account
     */
    public TestAccount(String firstname, String lastname, String username, String password, char type, char active) {
        super(firstname, lastname, username, password, type, active);
    }
}
