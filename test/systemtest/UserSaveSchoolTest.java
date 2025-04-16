package systemtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;
import junit.framework.Assert;

/**
 * The UserSaveSchoolTest class contains unit tests for the functionality
 * that saves a school for a user. These tests verify that a school is correctly
 * added to a user’s saved list, that duplicate saves are rejected, and that
 * saving a school not present in the database fails.
 */
public class UserSaveSchoolTest {

    /**
     * Sets up the test environment by adding a test user to the database.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        DatabaseController dbcon = new DatabaseController();
        dbcon.addUser("Peter", "Ohmann", "pohmann", "peterword", 'U');
    }

    /**
     * Tears down the test environment by removing the test user from the database.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        DatabaseController dbcon = new DatabaseController();
        dbcon.removeUser("pohmann");
    }

    /**
     * Tests the save school functionality.
     * <p>
     * This test performs the following checks:
     * <ul>
     *   <li>Ensures that a valid school can be saved successfully.</li>
     *   <li>Confirms that attempting to save an already saved school returns false.</li>
     *   <li>Verifies that saving a school not present in the database returns false.</li>
     * </ul>
     * </p>
     */
    @Test
    public void SaveSchoolTest() {
        DatabaseController dbcon = new DatabaseController();
        dbcon.removeSavedSchool1("pohmann", "BARD");
        boolean pass = dbcon.saveSchool("pohmann", "BARD");
        
        // Test to pass: school added to saved list
        Assert.assertTrue(pass);
        
        // Test to fail: school already in saved list
        Assert.assertFalse(dbcon.saveSchool("pohmann", "BARD"));
        
        // Test to fail: school is not in Database
        Assert.assertFalse(dbcon.saveSchool("pohmann", "notaschool"));
    }
}
