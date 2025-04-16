package systemtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import cmc.backend.Account;
import cmc.backend.AccountController;

/**
 * The AdminDeleteUserTest class contains unit tests for the deletion of a user account
 * from the system using administrative operations. It verifies that a user can be logged in,
 * then deleted, and that subsequent deletion attempts fail.
 */
public class AdminDeleteUserTest {

    private AccountController accountController;

    /**
     * Sets up the test environment by creating a test user.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        accountController = new AccountController();
        boolean created = accountController.createAccount("AdminTest", "User", "testuser", "testpass", 'U');
        if (!created) {
            throw new Exception("Failed to create test user in setUp.");
        }
    }

    /**
     * Cleans up the test environment by attempting to delete the test user.
     * Any exceptions during teardown are logged.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        try {
            accountController.deleteAccount("testuser");
        } catch (CMCException e) {
            System.err.println("Error in tearDown: " + e.getMessage());
        }
    }

    /**
     * Tests that an admin can successfully delete a user.
     * <p>
     * The test performs the following steps:
     * <ul>
     *   <li>Logs in the test user to ensure the account exists.</li>
     *   <li>Deletes the user using the deleteAccount method.</li>
     *   <li>Verifies that subsequent login attempts fail.</li>
     *   <li>Ensures that trying to delete the same non-existent user returns false.</li>
     * </ul>
     * </p>
     *
     * @throws CMCException if an error occurs during the deletion process
     */
    @Test
    public void testAdminDeleteUser() throws CMCException {
        Account acc = accountController.login("testuser", "testpass");
        assertNotNull("User should be able to login before deletion", acc);
        
        boolean deleted = accountController.deleteAccount("testuser");
        assertTrue("Admin should be able to delete the user successfully", deleted);
        
        Account accAfterDeletion = accountController.login("testuser", "testpass");
        assertNull("User should not be able to login after deletion", accAfterDeletion);
        
        boolean secondDeletionAttempt = accountController.deleteAccount("testuser");
        assertFalse("Deleting a non-existent user should return false", secondDeletionAttempt);
    }
}
