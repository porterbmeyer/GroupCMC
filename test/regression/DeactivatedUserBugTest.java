package regression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.Account;
import cmc.backend.DatabaseController;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DeactivatedUserBugTest {

	@Before
	public void setUp() throws Exception {
		
		  DatabaseController tester1 = new DatabaseController();
		  tester1.addUser("firstname", "lastname", "username9947", "password", 'U');
		  tester1.deactivateUser("username9947");
		}

	@After
	public void tearDown() throws Exception {
		
		  DatabaseController tester2 = new DatabaseController();
		  tester2.removeUser("username9947");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deactivatedUserTest() {
		  DatabaseController tester3 = new DatabaseController();
		  Account test = tester3.getUser("username9947");
		  char tesr = test.getActive();
		  
		  Assert.assertEquals(tesr, 'N');
	}
}
