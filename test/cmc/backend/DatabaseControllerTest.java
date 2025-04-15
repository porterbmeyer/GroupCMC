package cmc.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DatabaseControllerTest {

	private String firstName = "kfjwjejkewjkejknw";
	private String lastName = "ewothioweoitoiwejotjetoji";
	private String username = "kjweewkjejkfwekjckjwekjkjwef";
	private String password = " werwhueroiowrioiowriowiorew";
	private char type = 'u';
	
	
	private DatabaseController controller = new DatabaseController();
	University fakeUni = new University("fakename", "fakestate", "fakelo","testCon",1000,43.5,630,650, 30756,89.2,450,78.9,92.0,1,5,7);
	private User newUser = new User("John", "Doe", "differntnamethanjohn", "pass123", 'u', 'Y');
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		controller.addUser("first", "last","uniqueuser","p",'a');
		controller.addUser("firs", "las","otheruser","pass",'u');
		controller.removeUser("heyyy");
		
		//controller.removeUser("johndoe123"); commented out because it doesn't ever make it to the db becasue it throws
	}

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
		
		//attempts to add a user with a unique firstname but same username
		boolean answer4 = controller.addUser("sonotarealfirstname", lastName, username, password,type);
		Assert.assertFalse(answer4);
		
		//should fail because account exists but not with this firstname
		boolean answer5 = controller.addUser("sonotarealname", lastName, "heyyy", password, type);
		Assert.assertFalse(answer5);
		
	}
	
	@Test 
	public void testRemoveUser()throws CMCException{
		
		//test should pass if method workds correctly because this exists
		boolean result = controller.removeUser(username);
		Assert.assertTrue(result);
		
		//test case that should be false because this usernmae doesn't exist and therefore can't be removed
		boolean result1 = controller.removeUser("Userthatdoesntexist");
		Assert.assertFalse(result1);
		
	} 
	
	@Test
	public void testAddAccountReturnTrue() throws CMCException {
	    // Try to add the user to the system
	    boolean result = controller.addAccount(newUser);

	    // Check that it returns true if successful
	    Assert.assertTrue(result);
	}

	//black box testing the true output for deleteaccount method
	@Test(expected = CMCException.class)
	public void testAddAccountThrowException() throws CMCException {
	    User existingUserr = new User("Jane", "Doe", "johndoe123", "pass123", 'u', 'Y');

	    controller.addAccount(existingUserr);
	}
	
	@Test 
	public void testDeleteAccount() throws CMCException{
		boolean idk = controller.deleteAccount(newUser);
		Assert.assertTrue(idk);
		
	}
	//testing the throw output for delete account method
	@Test(expected = CMCException.class)
	public void testDeleteAccountThrowsExceptionWhenUserDoesNotExist() throws CMCException {
	    // Assume this user was never added or was already deleted
	    User nonExistentUser = new User("Ghost", "User", "ghost123", "pass", 'u', 'Y');
	    
	    // This should throw because this account never existed in the system to even be able to delete
	    controller.deleteAccount(nonExistentUser);
	}
	
	@Test
	public void testUpdateAccount() throws CMCException{
		User changeto = new User("Johnnyboy", "Doe", "differntnamethanjohn", "pass123", 'u', 'Y');
		boolean bool = controller.updateAccount(changeto);
		Assert.assertTrue(bool);
	}
	
	@Test(expected = CMCException.class)
	public void testUpdateAccountWhenThrows() throws CMCException{
		User notindb = new User("Ghost", "User", "ghost123", "pass", 'u', 'Y');
		controller.deleteAccount(notindb);
	}
	
	@Test
	public void testEditSchool() throws CMCException {
	    // black box for if it edits an existing uni
	    boolean result = controller.editSchool("fakename", "fakestate", "fakelo", "testCon", 1000, 43.5, 630, 650, 30756, 89.2, 450, 78.9, 92.0, 1, 5, 7);
	    Assert.assertTrue("Expected editSchool to return true for a valid university", result);
	}
	

}