package test.blackboxTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.DatabaseController;

public class LoginTest {

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
	public void loginTest() {
		
		//testing correct user-name and password
		
	}

}
