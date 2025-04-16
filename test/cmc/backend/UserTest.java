package cmc.backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * The UserTest class contains unit tests for user-related operations,
 * specifically testing the functionality for saving and removing schools
 * for a user. These tests verify that a school is added correctly to a user's
 * saved list and that the removal operation behaves as expected.
 */
public class UserTest {

    /**
     * Sets up the test environment by adding a test user with username "Username1".
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        DatabaseController dbcon = new DatabaseController();
        dbcon.addUser("firstname", "lastname", "Username1", "password", 'U');
    }

    /**
     * Cleans up the test environment by removing the test user with username "Username1".
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        DatabaseController dbcon = new DatabaseController();
        dbcon.removeUser("Username1");
    }

    /**
     * Tests the saveSchool functionality for a user.
     * <p>
     * The test first attempts to save the school "BARD" for the test user, expecting the operation to succeed.
     * It then attempts to save the same school again, expecting the operation to fail since the school
     * is already present in the saved list.
     * </p>
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testAddSavedSchool() {
        DatabaseController dbcon = new DatabaseController();
        // Attempt to add "BARD" to the saved school list for the test user.
        boolean pass = dbcon.saveSchool("Username1", "BARD");
        // Expect the first save to succeed.
        Assert.assertTrue(pass);
        // Attempting to add "BARD" a second time should fail.
        Assert.assertFalse(dbcon.saveSchool("Username1", "BARD"));
    }

    /**
     * Tests the removeSavedSchool functionality for a user.
     * <p>
     * This test creates a new User object corresponding to the test user "Username1"
     * and then attempts to remove the school "BARD" from the saved school list.
     * It expects the removal to succeed. It also tests removal of a school ("BAR")
     * that might not exist and verifies the expected behavior.
     * </p>
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testRemoveSavedSchool() {
        DatabaseController dbcon = new DatabaseController();
        Account acc = new User("firstname", "lastname", "Username1", "password", 'U', 'Y');
        // Attempt to remove "BARD" from the saved school list.
        boolean pass = dbcon.removeSavedSchool(acc, "BARD");
        // Expect removal to succeed.
        Assert.assertTrue(pass);
        // Attempt to remove a school ("BAR") that may not be in the list.
        // The expected behavior as per your implementation is that it returns true.
        boolean pass1 = dbcon.removeSavedSchool(acc, "BAR");
        Assert.assertTrue(pass1);
    }
}
