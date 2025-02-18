package cmc.backend;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.User;
import junit.framework.Assert;

public class DeactivatedUserBugTest {

	@Before
	public void setUp() throws Exception {
		/*
		 * DatabaseController tester1 = new DatabaseController();
		 * tester1.addUser("username9947", "password", 'u', "firstname","lastname");
		 */
		}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void deactivatedUserTest() {
		
		SystemController tester1 = new SystemController();
		User result = tester1.login("luser", "user");
		Assert.assertNull(result);
	}

}
