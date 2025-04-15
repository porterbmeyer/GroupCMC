package systemtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;
import junit.framework.Assert;

public class UserSaveSchoolTest {

	@Before
	public void setUp() throws Exception {
		 DatabaseController dbcon = new DatabaseController();
	     dbcon.addUser("Peter", "Ohmann", "pohmann", "peterword", 'U');
	}

	@After
	public void tearDown() throws Exception {
		 DatabaseController dbcon = new DatabaseController();
	     dbcon.removeUser("pohmann");
	}

	@Test
	public void SaveSchoolTest() {
		DatabaseController dbcon = new DatabaseController();	
		boolean pass = dbcon.saveSchool("pohmann", "BARD");
		
		//test to pass added to saved list
		Assert.assertTrue(pass);
		
		//test to fail already in DB
		Assert.assertFalse(dbcon.saveSchool("Username1","BARD"));
		
		//fail school is not in Database
		Assert.assertFalse(dbcon.saveSchool("Username1","notaschool"));
	}

}
