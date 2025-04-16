package systemtest;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.StringReader;
import cmc.backend.DatabaseController;
import cmc.frontend.UserInteraction;

/**
 * Unit tests for the login functionality in the application.
 * This class verifies the behavior of user login with valid and invalid credentials.
 */
public class LoginTest {

    /**
     * Sets up the test environment by adding a test user to the database.
     * This method is executed before each test case.
     * 
     * @throws Exception if an error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        DatabaseController dbcon = new DatabaseController();
        dbcon.addUser("Peter", "Ohmann", "pohmann", "peterword", 'U');
    }

    /**
     * Cleans up the test environment by removing the test user from the database.
     * This method is executed after each test case.
     * 
     * @throws Exception if an error occurs during teardown.
     */
    @After
    public void tearDown() throws Exception {
        DatabaseController dbcon = new DatabaseController();
        dbcon.removeUser("pohmann");
    }

    /**
     * Tests the login functionality with correct and incorrect credentials.
     * Verifies that the system properly authenticates valid users.
     */
    @Test
    public void loginTest() throws Exception {
    	
    	        UserInteraction loginController = new UserInteraction(); // Make sure this is your class with login()

    	        // === Test: Valid login ===
    	        Scanner validScanner = new Scanner(new StringReader("juser\nuser\n"));
    	        boolean validLogin = loginController.login(validScanner);
    	        assertTrue("Login should succeed with correct credentials", validLogin);
    	        validScanner.close();

    	        // === Test: Invalid password ===
    	        Scanner wrongPassScanner = new Scanner(new StringReader("pohmann\nwrongpass\n"));
    	        boolean invalidPass = loginController.login(wrongPassScanner);
    	        assertFalse("Login should fail with incorrect password", invalidPass);
    	        wrongPassScanner.close();

    	        // === Test: Non-existent user ===
    	        Scanner invalidUserScanner = new Scanner(new StringReader("fakeuser\nfakepass\n"));
    	        boolean invalidUser = loginController.login(invalidUserScanner);
    	        assertFalse("Login should fail with non-existent user", invalidUser);
    	        invalidUserScanner.close();
    	    }
}


