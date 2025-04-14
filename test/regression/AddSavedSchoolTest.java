package regression;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;
import junit.framework.Assert;

public class AddSavedSchoolTest {

	@Before
	public void setUp() throws Exception {
		 DatabaseController tester1 = new DatabaseController();
		 tester1.addUser("firstname", "lastname", "username9947", "password", 'U');
	}

	@After
	public void tearDown() throws Exception {
		 DatabaseController tester12 = new DatabaseController();
		 tester12.removeUser("username9947");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addSchoolTest() {
		DatabaseController tess = new DatabaseController();
		Boolean b = tess.saveSchool("username99332", "");
		Assert.assertFalse(b);
		
	}

}
