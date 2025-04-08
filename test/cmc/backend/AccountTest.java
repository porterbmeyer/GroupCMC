/**
 * 
 */
package cmc.backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.Account;
import cmc.backend.TestAccount;
import junit.framework.Assert;

/**
 * 
 */
public class AccountTest {
private static TestAccount testAccount;
//private static TestAccount anotherAccount;

	@Before
	public void setUp() {
		testAccount = new TestAccount("Test", "User", "testuname", "testpass", 'u', 'Y');
	}
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		Assert.assertEquals("Test", testAccount.getFirstName());
		Assert.assertEquals("User", testAccount.getLastName());
		Assert.assertEquals("testuname", testAccount.getUsername());
		Assert.assertEquals("testpass", testAccount.getPassword());
		Assert.assertEquals('u', testAccount.getType());
		Assert.assertEquals('Y', testAccount.getActive());
	}
	
	@Test
    public void testIsAdmin() {
        Assert.assertFalse(testAccount.isAdmin()); // 'u' is not admin
        testAccount.setType('a');
        Assert.assertTrue(testAccount.isAdmin()); // 'a' should be admin
    }
	
	@Test
    public void testEqualMethod() {
        TestAccount sameAccount = new TestAccount("Test", "User", "testuname", "testpass", 'u', 'Y');
        Assert.assertTrue(testAccount.equal(sameAccount)); // Should be true
    }

}
