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
/**
	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditAccount() {
		fail("Not yet implemented");
	} */

}
