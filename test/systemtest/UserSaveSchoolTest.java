package systemtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.Account;
import cmc.backend.DatabaseController;
import cmc.backend.User;
import junit.framework.Assert;

public class UserSaveSchoolTest {

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
		//test to save school pass
		Assert.assertTrue(pass);
		//test to fail already in system
		Assert.assertFalse(dbcon.saveSchool("Username1","BARD"));
		
	}
}
