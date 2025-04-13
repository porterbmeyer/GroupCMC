package cmc.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class AccountControllerTest extends AccountController {

	private AccountController ac = new AccountController();
	private AccountController testac = new AccountController();
	
	@Before
	public void setUp() throws Exception {
		testac.createAccount("Test", "User", "testuname", "testpass", 'u');
	}

	@After
	public void tearDown() throws Exception {
		testac.deleteAccount("testuname");
	}

	@Test
	public void testCreateAccount() throws CMCException {
		boolean answer = ac.createAccount("testfirst", "testlast", "testuser", "testpass", 'u');
		Assert.assertTrue(answer);
	}

	@Test
	public void testDeleteAccount() throws CMCException {
		boolean answer1 = ac.deleteAccount("testuser");
		Assert.assertTrue(answer1);
	
		
		
		boolean answer2 = ac.deleteAccount("accountthatdoesntexist");
		Assert.assertFalse(answer2);
		
		boolean trynull = ac.deleteAccount(null);
		Assert.assertFalse(trynull);
		
		boolean withtypo = ac.deleteAccount("testuserr");
		Assert.assertFalse(withtypo);
	}

	
	@Test
	public void testUpdateAccount() throws CMCException {
		// new everything
        Account actual = testac.updateAccountDetails("NewFirst", "NewLast", "testuname", "newpass", 'a', 'Y');

        Assert.assertEquals("NewFirst", actual.getFirstName());
        Assert.assertEquals("NewLast", actual.getLastName());
        Assert.assertEquals("testuname", actual.getUsername());
        Assert.assertEquals("newpass", actual.getPassword());
        Assert.assertEquals('a', actual.getType());
        Assert.assertEquals('Y', actual.getActive());
        
        //test update with the same everything but changed one thing (firstname)
        Account diffFname = testac.updateAccountDetails("diffname", "User", "testuname", "testpass", 'u', 'Y');
        
        Assert.assertEquals("diffname", diffFname.getFirstName());
        Assert.assertEquals("User", diffFname.getLastName());
        Assert.assertEquals("testuname", diffFname.getUsername());
        Assert.assertEquals("testpass", diffFname.getPassword());
        Assert.assertEquals('u', diffFname.getType());
        Assert.assertEquals('Y', diffFname.getActive());
        
      //test update with the same everything but changed one thing (password)
        Account changedpass = testac.updateAccountDetails("testfirst", "User", "testuname", "somethingdiff", 'u', 'Y');
        
        Assert.assertEquals("testfirst", changedpass.getFirstName());
        Assert.assertEquals("User", changedpass.getLastName());
        Assert.assertEquals("testuname", changedpass.getUsername());
        Assert.assertEquals("somethingdiff", changedpass.getPassword());
        Assert.assertEquals('u', changedpass.getType());
        Assert.assertEquals('Y', changedpass.getActive());
        
      //testing the null return on a username that doesn't exist
        Account testnull = testac.updateAccountDetails("doesntexist", "notreal", "nouser", "nothere", 'u', 'Y');
        Assert.assertNull(testnull);
    }

	@Test
	public void testChangePassword() throws CMCException {
		//String firstname, String lastname, String username, String password, char type
		//testing the null return on a username that doesn't exist
        String testnull = testac.changePassword("nouser", "norealpassword","realpassword");
        Assert.assertEquals("Account not found.", testnull);
        
        //wrong password
        String wrongpass = testac.changePassword("testuname", "passtest", "newpass");
        Assert.assertEquals("Old password is incorrect.", wrongpass);
        
      //succesful change
        String changepass = testac.changePassword("testuname", "testpass", "newerpassword");
        Assert.assertEquals("Password changed successfully.", changepass);
	}

	@Test
	public void testLogin() throws CMCException {
		Account shouldntwork = testac.login("notreal", "doesntmatter");
		Assert.assertNull(shouldntwork);
		
		Account wrongpass = testac.login("testUser", "doesntmatter");
		Assert.assertNull(wrongpass);
		
		Account wronguname = testac.login("dontexist", "testpass");
		Assert.assertNull(wronguname);
		
		Account shouldpass = testac.login("testuname", "testpass");
		Assert.assertEquals("testuname", shouldpass.getUsername());
		Assert.assertEquals("testpass", shouldpass.getPassword());
	}
//String username, String firstname, String lastname, String password, char type, char active
	@Test
	public void testEditAccount() throws CMCException {
		//edited Account's information in the database, should pass
		boolean changedinfo = testac.editAccount("testuname", "diffFname", "lastlast", "diffPass", 'a','N');
		Assert.assertTrue(changedinfo);
		
		//account doesn't exist, should be false
		boolean dontexist = testac.editAccount("brandnewguy", "New", "Guy", "guyNew", 'a', 'Y');
		Assert.assertFalse(dontexist);
	} 

}
