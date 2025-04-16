package regression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.Account;
import cmc.backend.DatabaseController;
import junit.framework.Assert;

/**
 * The DeactivatedUserBugTest class tests the behavior of user deactivation.
 * It verifies that after a user is deactivated the active status is set to 'N'.
 */
@SuppressWarnings("deprecation")
public class DeactivatedUserBugTest {

    /**
     * Sets up the test environment by adding a new user and deactivating that user.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        DatabaseController tester1 = new DatabaseController();
        tester1.addUser("firstname", "lastname", "username9947", "password", 'U');
        tester1.deactivateUser("username9947");
    }

    /**
     * Tears down the test environment by removing the test user.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        DatabaseController tester2 = new DatabaseController();
        tester2.removeUser("username9947");
    }

    /**
     * Tests that after deactivating a user, the active status is 'N'.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void deactivatedUserTest() {
        DatabaseController tester3 = new DatabaseController();
        Account test = tester3.getUser("username9947");
        char status = test.getActive();
        Assert.assertEquals("User active status should be 'N' after deactivation", 'N', status);
    }
}
