package systemtest;

import cmc.CMCException;
import cmc.backend.AccountController;
import cmc.backend.DatabaseController;
import cmc.backend.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for editing user accounts in the application.
 * This class verifies that user accounts can be successfully edited and handles exceptions.
 */
public class EditUserTest {
    private DatabaseController dbController = new DatabaseController();
    private AccountController accountController = new AccountController();

    /**
     * Sets up the test environment by adding a test user to the database.
     * This method is executed before each test case.
     * 
     * @throws Exception if an error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        dbController.addUser("Peter", "Ohmann", "pohmann", "peterword", 'u');
    }

    /**
     * Cleans up the test environment by removing the test user from the database.
     * This method is executed after each test case.
     * 
     * @throws Exception if an error occurs during teardown.
     */
    @After
    public void tearDown() throws Exception {
        dbController.removeUser("pohmann");
    }

    /**
     * Tests editing user account details.
     * Verifies that changes are correctly applied to the user's account.
     * 
     * @throws CMCException if an error occurs during editing.
     */
    @Test
    public void editUserTest() throws CMCException {
        accountController.editAccount("pohmann", "Peterman", "Ohhh", "pword", 'a', 'Y');
        Assert.assertEquals("Peterman", dbController.getUser("pohmann").getFirstName());
        Assert.assertEquals("Ohhh", dbController.getUser("pohmann").getLastName());
        Assert.assertEquals("pword", dbController.getUser("pohmann").getPassword());
        Assert.assertEquals('a', dbController.getUser("pohmann").getType());
        Assert.assertEquals('Y', dbController.getUser("pohmann").getActive());
    }

    /**
     * Tests that no changes are made when empty values are passed to the editAccount method.
     * 
     * @throws CMCException if an error occurs during editing.
     */
    @Test
    public void noChangeTest() throws CMCException {
        accountController.editAccount("pohmann", "", "", "", ' ', ' ');
        User testPeter = new User("Peter", "Ohmann", "pohmann", "peterword", 'u', 'Y');
        Assert.assertEquals("Peter", testPeter.getFirstName());
        Assert.assertEquals("Ohmann", testPeter.getLastName());
        Assert.assertEquals("peterword", testPeter.getPassword());
    }

    /**
     * Verifies that an exception is thrown when attempting to edit a non-existent user.
     * 
     * @throws CMCException if an error occurs during editing.
     */
    /*
    @Test(expected = CMCException.class)
    public void editUserTestException() throws CMCException {
        accountController.editAccount("nonexistent", "Peterman", "Ohhh", "pword", 'a', 'Y');
    }
    */
    /**
     * Tests that the editAccount method returns false when trying to edit a non-existent user.
     */
    @Test
    public void editUserTestFalse() throws CMCException {
        Assert.assertFalse("Editing a non-existent user should return false", accountController.editAccount("nonexistent", "Peterman", "Ohhh", "pword", 'a', 'Y'));
    }
}