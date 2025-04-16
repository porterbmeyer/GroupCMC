package regression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;
import junit.framework.Assert;

/**
 * The AddSavedSchoolTest class verifies the functionality for saving a school to a user's saved list.
 * It tests that attempts to save a school with incorrect or missing information return false.
 */
public class AddSavedSchoolTest {

    /**
     * Sets up the test environment by adding a test user.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        DatabaseController tester1 = new DatabaseController();
        tester1.addUser("firstname", "lastname", "username9947", "password", 'U');
    }

    /**
     * Tears down the test environment by removing the test user.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        DatabaseController tester12 = new DatabaseController();
        tester12.removeUser("username9947");
    }

    /**
     * Tests the save school functionality when an invalid username or empty school name is provided.
     * The test expects that saving a school under such conditions will return false.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void addSchoolTest() {
        DatabaseController tess = new DatabaseController();
        Boolean result = tess.saveSchool("username99332", "");
        Assert.assertFalse("Saving a school with an invalid username or empty school name should return false", result);
    }
}
