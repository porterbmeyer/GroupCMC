package cmc.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import junit.framework.Assert;

/**
 * The AccountControllerTest class provides unit tests for account management operations
 * performed by the AccountController. It verifies the functionality of creating, deleting,
 * updating, changing passwords, and logging into accounts.
 */
@SuppressWarnings("deprecation")
public class AccountControllerTest extends AccountController {

    private AccountController ac = new AccountController();
    private AccountController testac = new AccountController();

    /**
     * Sets up the test environment by creating a test account.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        testac.createAccount("Test", "User", "testuname", "testpass", 'u');
    }

    /**
     * Cleans up the test environment by deleting the test account.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        testac.deleteAccount("testuname");
    }

    /**
     * Tests that the createAccount method returns true when a new account is created.
     *
     * @throws CMCException if an error occurs during account creation
     */
    @Test
    public void testCreateAccount() throws CMCException {
        boolean answer = ac.createAccount("testfirst", "testlast", "testuser", "testpass", 'u');
        Assert.assertTrue(answer);
    }

    /**
     * Tests the deleteAccount method.
     * <p>
     * This method verifies that deletion returns true for a valid account,
     * and false when attempting to delete a non-existent account or when passing null or a mis-typed username.
     * </p>
     *
     * @throws CMCException if an error occurs during deletion
     */
    @Test
    public void testDeleteAccount() throws CMCException {
        boolean answer1 = ac.deleteAccount("testuser");
        Assert.assertTrue(answer1);
        
        boolean answer2 = ac.deleteAccount("accountthatdoesntexist");
        Assert.assertFalse(answer2);
        
        boolean trynull = ac.deleteAccount(null);
        Assert.assertFalse(trynull);
        
        boolean withtypo = ac.deleteAccount("testuserr");
        Assert.assertFalse(withtypo);
    }

    /**
     * Tests that updateAccountDetails properly updates user account information.
     * It verifies updates for all fields and also verifies that passing non-existent usernames returns null.
     *
     * @throws CMCException if an error occurs during update
     */
    @Test
    public void testUpdateAccount() throws CMCException {
        // Update all fields
        Account actual = testac.updateAccountDetails("NewFirst", "NewLast", "testuname", "newpass", 'a', 'Y');
        Assert.assertEquals("NewFirst", actual.getFirstName());
        Assert.assertEquals("NewLast", actual.getLastName());
        Assert.assertEquals("testuname", actual.getUsername());
        Assert.assertEquals("newpass", actual.getPassword());
        Assert.assertEquals('a', actual.getType());
        Assert.assertEquals('Y', actual.getActive());
        
        // Update only the first name while keeping other fields the same
        Account diffFname = testac.updateAccountDetails("diffname", "User", "testuname", "testpass", 'u', 'Y');
        Assert.assertEquals("diffname", diffFname.getFirstName());
        Assert.assertEquals("User", diffFname.getLastName());
        Assert.assertEquals("testuname", diffFname.getUsername());
        Assert.assertEquals("testpass", diffFname.getPassword());
        Assert.assertEquals('u', diffFname.getType());
        Assert.assertEquals('Y', diffFname.getActive());
        
        // Update only the password field
        Account changedpass = testac.updateAccountDetails("testfirst", "User", "testuname", "somethingdiff", 'u', 'Y');
        Assert.assertEquals("testfirst", changedpass.getFirstName());
        Assert.assertEquals("User", changedpass.getLastName());
        Assert.assertEquals("testuname", changedpass.getUsername());
        Assert.assertEquals("somethingdiff", changedpass.getPassword());
        Assert.assertEquals('u', changedpass.getType());
        Assert.assertEquals('Y', changedpass.getActive());
        
        // Attempt to update a non-existent account should return null
        Account testnull = testac.updateAccountDetails("doesntexist", "notreal", "nouser", "nothere", 'u', 'Y');
        Assert.assertNull(testnull);
    }

    /**
     * Tests the changePassword method.
     * <p>
     * This test verifies that changing the password returns the correct messages for cases where:
     * the account does not exist, the old password is incorrect, and a successful password change.
     * </p>
     *
     * @throws CMCException if an error occurs during the password change
     */
    @Test
    public void testChangePassword() throws CMCException {
        // Test when the username does not exist
        String testnull = testac.changePassword("nouser", "norealpassword", "realpassword");
        Assert.assertEquals("Account not found.", testnull);
        
        // Test with incorrect old password
        String wrongpass = testac.changePassword("testuname", "passtest", "newpass");
        Assert.assertEquals("Old password is incorrect.", wrongpass);
        
        // Test with successful password change
        String changepass = testac.changePassword("testuname", "testpass", "newerpassword");
        Assert.assertEquals("Password changed successfully.", changepass);
    }

    /**
     * Tests the login method.
     * <p>
     * The test checks that login fails with invalid credentials and succeeds with valid ones.
     * </p>
     *
     * @throws CMCException if an error occurs during login
     */
    @Test
    public void testLogin() throws CMCException {
        Account shouldntwork = testac.login("notreal", "doesntmatter");
        Assert.assertNull(shouldntwork);
        
        Account wrongpass = testac.login("testUser", "doesntmatter");
        Assert.assertNull(wrongpass);
        
        Account wronguname = testac.login("dontexist", "testpass");
        Assert.assertNull(wronguname);
        
        Account shouldpass = testac.login("testuname", "testpass");
        Assert.assertEquals("testuname", shouldpass.getUsername());
        Assert.assertEquals("testpass", shouldpass.getPassword());
    }

    /**
     * Tests the editAccount method.
     * <p>
     * This test verifies that editing an existing account returns true, while attempting to edit a non-existent
     * account returns false.
     * </p>
     *
     * @throws CMCException if an error occurs during the edit operation
     */
    @Test
    public void testEditAccount() throws CMCException {
        boolean changedinfo = testac.editAccount("testuname", "diffFname", "lastlast", "diffPass", 'a', 'N');
        Assert.assertTrue(changedinfo);
        
        boolean dontexist = testac.editAccount("brandnewguy", "New", "Guy", "guyNew", 'a', 'Y');
        Assert.assertFalse(dontexist);
    }
}
