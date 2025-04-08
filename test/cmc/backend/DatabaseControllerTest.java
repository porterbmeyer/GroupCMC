package cmc.backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import junit.framework.Assert;

public class DatabaseControllerTest {
	private String firstName = "kfjwjejkewjkejknw";
	private String lastName = "ewothioweoitoiwejotjetoji";
	private String username = "kjweewkjejkfwekjckjwekjkjwef";
	private String password = " werwhueroiowrioiowriowiorew";
	private char type = 'u';
	
	
	private DatabaseController controller = new DatabaseController();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//controller.removeUser(username);
		controller.removeUser("heyyy");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddUser() throws CMCException {
		
		// adds a user with a unique username
		boolean answer1 = controller.addUser(firstName, lastName, username, password,type);
		Assert.assertTrue(answer1);
		
		//attempts to add a duplicte user
		boolean answer2 = controller.addUser(firstName, lastName, username, password,type);
		Assert.assertFalse(answer2);
		
		//attempts to add a user without the same username but same info should work. (Write a new one before testing)
		boolean answer3 = controller.addUser(firstName, lastName, "heyyy", password,type);
		Assert.assertTrue(answer3); 
	}
	
	@SuppressWarnings("deprecation")
	@Test 
	public void testRemoveUser()throws CMCException{
		
		//test should pass if method workds correctly because this exists
		boolean result = controller.removeUser(username);
		Assert.assertTrue(result);
		
		//test case that should be false because this usernmae doesn't exist and therefore can't be removed
		boolean result1 = controller.removeUser("Userthatdoesntexist");
		Assert.assertFalse(result1);
		
	} 

}
