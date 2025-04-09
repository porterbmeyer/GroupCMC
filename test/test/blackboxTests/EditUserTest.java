package test.blackboxTests;

import cmc.CMCException;
import cmc.backend.AccountController;
import cmc.backend.DatabaseController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditUserTest {
	DatabaseController dbController = new DatabaseController();
	AccountController accountController = new AccountController();
	@Before
	public void setUp() throws Exception {
		dbController.addUser("Peter", "Ohmann", "pohmann", "peterword", 'u');
	}

	@After
	public void tearDown() throws Exception {
		dbController.removeUser("pohmann");
	}

	@Test
	public void editUserTest() throws CMCException {
		// Test making changes to the User
		accountController.editAccount("pohmann", "Peterman", "Ohhh", "pword", 'a', 'Y');
		Assert.assertEquals("Peterman", dbController.getUser("pohmann").getFirstName());
		Assert.assertEquals("Ohhh", dbController.getUser("pohmann").getLastName());
		Assert.assertEquals("pword", dbController.getUser("pohmann").getPassword());
		Assert.assertEquals('a', dbController.getUser("pohmann").getType());
		Assert.assertEquals('Y', dbController.getUser("pohmann").getActive());
		// Test making no changes to the User
		accountController.editAccount("pohmann", "", "", "", ' ', ' ');
		Assert.assertEquals("Peterman", dbController.getUser("pohmann").getFirstName());
		Assert.assertEquals("Ohhh", dbController.getUser("pohmann").getLastName());
		Assert.assertEquals("pword", dbController.getUser("pohmann").getPassword());
		Assert.assertEquals('a', dbController.getUser("pohmann").getType());
		Assert.assertEquals('Y', dbController.getUser("pohmann").getActive());

	}
	@Test(expected = CMCException.class)
	public void editUserTestException() throws CMCException {
		// Test making changes to a non-existent User
		accountController.editAccount("nonexistent", "Peterman", "Ohhh", "pword", 'a', 'Y');
		Assert.assertEquals("Peterman", dbController.getUser("pohmann").getFirstName());
		Assert.assertEquals("Ohhh", dbController.getUser("pohmann").getLastName());
		Assert.assertEquals("pword", dbController.getUser("pohmann").getPassword());
		Assert.assertEquals('a', dbController.getUser("pohmann").getType());
		Assert.assertEquals('Y', dbController.getUser("pohmann").getActive());

	}

}
