package systemtest;

import static org.junit.Assert.*;

import org.junit.Test;

import cmc.CMCException;
import cmc.backend.University;
import cmc.frontend.UserInteraction;

import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

/**
 * The UserInteractionSearchTest class contains black-box tests for the search functionality
 * provided by the UserInteraction class. It simulates user input and verifies that search
 * results are properly returned based on specified criteria.
 */
public class UserInteractionSearchTest {

    /**
     * Tests the search functionality by specifying the state as the search criterion.
     * This method checks that the search results are not null.
     *
     * @throws CMCException if an error occurs during the search operation
     */
    @Test
    public void testSearchByState() throws CMCException {
        UserInteraction ui = new UserInteraction();
        // Simulate user input: entering "state" as criterion and "MINNESOTA" as state value
        Scanner validScanner = new Scanner(new StringReader("state\nMINNESOTA\n"));
        List<University> results = ui.search(validScanner);
        assertNotNull("Search results should not be null", results);
        validScanner.close();
    }
}
