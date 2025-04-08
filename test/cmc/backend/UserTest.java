package cmc.backend;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private static User testUser;
/**
	private static String testUname = "testuname";
	private static String testPass = "testpass";
	private static String testFName = "Test";
	private static String testLName = "AUser";
	private static char testType = 'u';
	private static char testAct = 'Y';
*/
	@Before
	public void setUp() throws Exception {
		testUser = new User("Test", "AUser", "testuname", "testpass", 'u', 'Y');
	}

	@After
	public void tearDown() throws Exception {
		// No database use for unit testing this class, so nothing to "tear down"!
	}
	
	@Test
	public void testConstructor() {
		Assert.assertEquals("testuname", testUser.getFirstName());
		/**Assert.assertEquals(testLName, testUser.getLastName());
		Assert.assertEquals(testUname, testUser.getUsername());
		Assert.assertEquals(testPass, testUser.getPassword());
		Assert.assertEquals(testAct, testUser.getActive());
		*/
	}
/**
	@Test
	public void testIsAdmin() {
		Assert.assertFalse(testUser.isAdmin());
		testUser.setType('a');
		Assert.assertTrue(testUser.isAdmin());
	}
*/
}
