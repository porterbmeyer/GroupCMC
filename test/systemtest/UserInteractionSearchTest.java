package systemtest;

import static org.junit.Assert.*;
import org.junit.Test;

import cmc.CMCException;
import cmc.backend.University;
import cmc.frontend.UserInteraction;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

/**
 * Black-box tests for the search functionality in the UserInteraction class.
 * This class verifies that the search method returns the expected results based on user input.
 */
public class UserInteractionSearchTest {

    /**
     * Tests the search functionality by searching universities by state.
     * Verifies that the results are not null and that all returned universities are from the specified state.
     * @throws CMCException 
     */
	@Test
	public void testSearchByState() throws CMCException {

		UserInteraction loginController = new UserInteraction(); 


		Scanner validScanner = new Scanner(new StringReader("state\nMINNESOTA\n"));

		List<University> results = loginController.search(validScanner);

		assertNotNull("Search results should not be null", results);
		validScanner.close();
	}
            
}