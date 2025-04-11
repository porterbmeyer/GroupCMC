package cmc.backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import junit.framework.Assert;

public class AccountControllerTest extends AccountController {

	private DatabaseController controller = new DatabaseController();
	private AccountController ac = new AccountController();
	private AccountController testac = new AccountController();
	//private Account testacc;
	//private String firstname = "testfirstname"
	@Before
	public void setUp() throws Exception {
		testac.createAccount("firstname", "lastname", "username", "p", 'a');
		//Account testacc = controller.getUser("username");
	}

	@After
	public void tearDown() throws Exception {
		testac.deleteAccount("username");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateAccount() throws CMCException {
		boolean answer = ac.createAccount("testfirst", "testlast", "testuser", "testpass", 'u');
		Assert.assertTrue(answer);
	}

	@SuppressWarnings("deprecation")
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
/**
	@Test
	public void testUpdateAccountDetails() {
		fail("Not yet implemented");
	}

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
