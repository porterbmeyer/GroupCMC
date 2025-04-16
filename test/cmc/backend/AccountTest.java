package cmc.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * The AccountTest class provides unit tests for the functionality of the Account class.
 * It verifies that the TestAccount subclass properly sets fields during construction,
 * correctly reports administrative status, and compares accounts accurately.
 */
@SuppressWarnings("deprecation")
public class AccountTest {
    private static TestAccount testAccount;

    /**
     * Sets up the test environment by creating a TestAccount instance.
     */
    @Before
    public void setUp() {
        testAccount = new TestAccount("Test", "User", "testuname", "testpass", 'u', 'Y');
    }
    
    /**
     * Tears down the test environment.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        // No teardown actions required
    }

    /**
     * Tests that the TestAccount constructor initializes fields correctly.
     */
    @Test
    public void testConstructor() {
        Assert.assertEquals("Test", testAccount.getFirstName());
        Assert.assertEquals("User", testAccount.getLastName());
        Assert.assertEquals("testuname", testAccount.getUsername());
        Assert.assertEquals("testpass", testAccount.getPassword());
        Assert.assertEquals('u', testAccount.getType());
        Assert.assertEquals('Y', testAccount.getActive());
    }
    
    /**
     * Tests that the isAdmin method correctly identifies administrative accounts.
     */
    @Test
    public void testIsAdmin() {
        Assert.assertFalse(testAccount.isAdmin()); // 'u' is not admin
        testAccount.setType('a');
        Assert.assertTrue(testAccount.isAdmin()); // 'a' should be admin
    }
    
    /**
     * Tests that the equal method returns true for accounts with identical field values.
     */
    @Test
    public void testEqualMethod() {
        TestAccount sameAccount = new TestAccount("Test", "User", "testuname", "testpass", 'u', 'Y');
        Assert.assertTrue(testAccount.equal(sameAccount));
    }
}
