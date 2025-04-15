package cmc.backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class UserTest {

	@Before
	public void setUp() throws Exception {
		DatabaseController dbcon = new DatabaseController();
		dbcon.addUser("firstname", "lastname", "Username1", "password", 'U');
		}

	@After
	public void tearDown() throws Exception {
		DatabaseController dbcon = new DatabaseController();
		dbcon.removeUser("Username1");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddSavedSchool() {
		DatabaseController dbcon = new DatabaseController();
		
		boolean pass = dbcon.saveSchool("Username1", "BARD");
		//test to pass
		Assert.assertTrue(pass);
		//test to fail
		Assert.assertFalse(dbcon.saveSchool("Username1","BARD"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveSavedSchool() {
		DatabaseController dbcon = new DatabaseController();
		Account acc = new User("firstname", "lastname", "Username1", "password", 'U', 'Y');
		boolean pass = dbcon.removeSavedSchool(acc, "BARD");
		
		//test to pass
		Assert.assertTrue(pass);
		//test to fail
		boolean pass1 = dbcon.removeSavedSchool(acc, "BAR");
		Assert.assertTrue(pass1);
	}

}
