package cmc.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import junit.framework.Assert;

/**
 * The DatabaseControllerTest class provides unit tests for the DatabaseController.
 * It verifies the behavior of user addition, removal, account management,
 * and university editing functionalities.
 */
@SuppressWarnings("deprecation")
public class DatabaseControllerTest {

    private String firstName = "kfjwjejkewjkejknw";
    private String lastName = "ewothioweoitoiwejotjetoji";
    private String username = "kjweewkjejkfwekjckjwekjkjwef";
    private String password = " werwhueroiowrioiowriowiorew";
    private char type = 'u';
    
    private DatabaseController controller = new DatabaseController();
    University fakeUni = new University("fakename", "fakestate", "fakelo", "testCon", 1000, 43.5, 630, 650, 30756, 89.2, 450, 78.9, 92.0, 1, 5, 7);
    private User newUser = new User("John", "Doe", "differntnamethanjohn", "pass123", 'u', 'Y');
    
    /**
     * Sets up the test environment.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        // Setup logic if necessary
    }

    /**
     * Tears down the test environment by adding dummy users and removing test-specific ones.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        controller.addUser("first", "last", "uniqueuser", "p", 'a');
        controller.addUser("firs", "las", "otheruser", "pass", 'u');
        controller.removeUser("heyyy");
    }
    
    /**
     * Tests that adding a user returns true for unique usernames and false for duplicates.
     *
     * @throws CMCException if an error occurs during user addition
     */
    @Test
    public void testAddUser() throws CMCException {
        boolean answer1 = controller.addUser(firstName, lastName, username, password, type);
        Assert.assertTrue(answer1);
        
        boolean answer2 = controller.addUser(firstName, lastName, username, password, type);
        Assert.assertFalse(answer2);
        
        boolean answer3 = controller.addUser(firstName, lastName, "heyyy", password, type);
        Assert.assertTrue(answer3); 
        
        boolean answer4 = controller.addUser("sonotarealfirstname", lastName, username, password, type);
        Assert.assertFalse(answer4);
        
        boolean answer5 = controller.addUser("sonotarealname", lastName, "heyyy", password, type);
        Assert.assertFalse(answer5);
    }
    
    /**
     * Tests that removing an existing user returns true and removing a non-existent user returns false.
     *
     * @throws CMCException if an error occurs during user removal
     */
    @Test 
    public void testRemoveUser() throws CMCException {
        boolean result = controller.removeUser(username);
        Assert.assertTrue(result);
        
        boolean result1 = controller.removeUser("Userthatdoesntexist");
        Assert.assertFalse(result1);
    } 
    
    /**
     * Tests that addAccount returns true when a new user is added.
     *
     * @throws CMCException if an error occurs during account addition
     */
    @Test
    public void testAddAccountReturnTrue() throws CMCException {
        boolean result = controller.addAccount(newUser);
        Assert.assertTrue(result);
    }

    /**
     * Tests that attempting to add an account for an already existing user throws an exception.
     *
     * @throws CMCException expected when trying to add a duplicate account
     */
    @Test(expected = CMCException.class)
    public void testAddAccountThrowException() throws CMCException {
        User existingUserr = new User("Jane", "Doe", "johndoe123", "pass123", 'u', 'Y');
        controller.addAccount(existingUserr);
    }
    
    /**
     * Tests that deleting an account returns true when successfully deleted.
     *
     * @throws CMCException if an error occurs during account deletion
     */
    @Test 
    public void testDeleteAccount() throws CMCException {
        boolean idk = controller.deleteAccount(newUser);
        Assert.assertTrue(idk);
    }
    
    /**
     * Tests that deleting a non-existent user throws an exception.
     *
     * @throws CMCException expected because the user does not exist
     */
    @Test(expected = CMCException.class)
    public void testDeleteAccountThrowsExceptionWhenUserDoesNotExist() throws CMCException {
        User nonExistentUser = new User("Ghost", "User", "ghost123", "pass", 'u', 'Y');
        controller.deleteAccount(nonExistentUser);
    }
    
    /**
     * Tests that updating an account returns true for an existing user.
     *
     * @throws CMCException if an error occurs during account update
     */
    @Test
    public void testUpdateAccount() throws CMCException {
        User changeto = new User("Johnnyboy", "Doe", "differntnamethanjohn", "pass123", 'u', 'Y');
        boolean bool = controller.updateAccount(changeto);
        Assert.assertTrue(bool);
    }
    
    /**
     * Tests that updating an account for a non-existent user throws an exception.
     *
     * @throws CMCException expected because the user is not present
     */
    @Test(expected = CMCException.class)
    public void testUpdateAccountWhenThrows() throws CMCException {
        User notindb = new User("Ghost", "User", "ghost123", "pass", 'u', 'Y');
        controller.deleteAccount(notindb);
    }
    
    /**
     * Tests that editing a school's information returns true for a valid university.
     *
     * @throws CMCException if an error occurs during the edit operation
     */
    @Test
    public void testEditSchool() throws CMCException {
        boolean result = controller.editSchool("fakename", "fakestate", "fakelo", "testCon", 1000, 43.5, 630, 650, 30756, 89.2, 450, 78.9, 92.0, 1, 5, 7);
        Assert.assertTrue("Expected editSchool to return true for a valid university", result);
    }
}
