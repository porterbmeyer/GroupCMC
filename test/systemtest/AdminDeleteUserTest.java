package systemtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import cmc.backend.Account;
import cmc.backend.AccountController;

public class AdminDeleteUserTest {

    private AccountController accountController;

    @Before
    public void setUp() throws Exception {
        // Instantiate the AccountController.
        accountController = new AccountController();
        
        // Create a test account that will be deleted later.
        boolean created = accountController.createAccount(
                "AdminTest", "User", "testuser", "testpass", 'U');
        if (!created) {
            throw new Exception("Failed to create test user in setUp.");
        }
    }

    @After
    public void tearDown() throws Exception {
        // Clean up the test account if it still exists.
        try {
            accountController.deleteAccount("testuser");
        } catch (CMCException e) {
            System.err.println("Error in tearDown: " + e.getMessage());
        }
    }

    @Test
    public void testAdminDeleteUser() throws CMCException {
        // Verify that the test user can log in before deletion.
        Account acc = accountController.login("testuser", "testpass");
        assertNotNull("User should be able to login before deletion", acc);
        
        // Simulate admin deleting the user.
        boolean deleted = accountController.deleteAccount("testuser");
        assertTrue("Admin should be able to delete the user successfully", deleted);
        
        // After deletion, verify that the test user can no longer log in.
        Account accAfterDeletion = accountController.login("testuser", "testpass");
        assertNull("User should not be able to login after deletion", accAfterDeletion);
        
        // Verify that attempting deletion again returns false.
        boolean secondDeletionAttempt = accountController.deleteAccount("testuser");
        assertFalse("Deleting a non-existent user should return false", secondDeletionAttempt);
    }
}

