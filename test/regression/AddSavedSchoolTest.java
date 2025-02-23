package regression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;
import cmc.backend.SystemController;
import junit.framework.Assert;

public class AddSavedSchoolTest {

	@Before
	public void setUp() throws Exception {
		 DatabaseController tester1 = new DatabaseController();
		 tester1.addUser("username99332", "password123432121", 'u', "firstname1","lastname1");
	}

	@After
	public void tearDown() throws Exception {
		 DatabaseController tester12 = new DatabaseController();
		 tester12.removeUser("username99332");
	}

	@Test
	public void addSchoolTest() {
		SystemController tess = new SystemController();
		tess.saveSchool("username99332", "");
		Assert.assertEquals(tess.saveSchool("username99332", ""), new IllegalArgumentException());
		
	}

}
