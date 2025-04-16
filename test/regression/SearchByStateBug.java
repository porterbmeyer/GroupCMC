package regression;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import cmc.backend.University;
import cmc.frontend.UserInteraction;
import junit.framework.Assert;

/**
 * The SearchByStateBug class contains a regression test for the search functionality
 * in the UserInteraction class when the user selects to show all universities (by entering "1").
 * The test verifies that a non-empty list of University objects is returned.
 */
public class SearchByStateBug {

    /**
     * Sets up the test environment.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        // No specific setup required
    }

    /**
     * Cleans up the test environment.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        // No specific teardown required
    }

    /**
     * Tests that when the search criterion is "1" (i.e., no filtering by a specific state),
     * the search method returns a list of universities that is not empty.
     *
     * @throws CMCException if an error occurs during the search operation
     */
    @Test
    public void searchByStatetest() throws CMCException {
        UserInteraction ui = new UserInteraction();
        // Using "1" to indicate no specific criteria.
        Scanner validScanner = new Scanner(new StringReader("1"));
        List<University> result1 = ui.search(validScanner);
        Assert.assertTrue("Expected at least one university in the search results", result1.size() > 0);
        validScanner.close();
    }
}
