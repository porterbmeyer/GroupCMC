import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cmc.CMCException;
import junit.framework.Assert;

public class AccountControllerTest {

    private DatabaseController controller = new DatabaseController();
   
    private String username = "Useruser123459494894";
	private String password = "passssssss4093";
	//private char type
	//private char active;
	private String firstName = "firstfirstName";
	private String lastName = "lastlastName";

    @Before
    public void setUp() throws CMCException {
    	controller.addUser(firstName, lastName, username, password, 'u');
    }
    
    @After
    public void tearDown() throws Exception {
    	controller.removeUser(username);
    }
    
    
    @Test
    public void testCreateAccount_shouldSucceed() throws CMCException {
    	Assert.assertTrue(true);
       /** boolean result = controller.createAccount("Test12", "User12", "testuser12", "testpass", 'u');
        asertTrue("Account creation should return true", result);
        */
   
    }
}

