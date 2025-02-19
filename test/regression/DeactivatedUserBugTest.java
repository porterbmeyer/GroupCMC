package regression;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;
import cmc.backend.SystemController;
import cmc.backend.User;
import junit.framework.Assert;

public class DeactivatedUserBugTest {

	@Before
	public void setUp() throws Exception {
		
		  DatabaseController tester1 = new DatabaseController();
		  tester1.addUser("username9947", "password1234322", 'u', "firstname","lastname");
		  tester1.deactivateUser("username9947");
		}

	@After
	public void tearDown() throws Exception {
		
		  DatabaseController tester2 = new DatabaseController();
		  tester2.removeUser("username9947");
	}

	@Test
	public void deactivatedUserTest() {
	    SystemController teste = new SystemController();
		User result = teste.login("username9947", "password1234322");
		Assert.assertNull(result);
	}

}
