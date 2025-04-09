package test.blackboxTests;

import static org.junit.Assert.*;
import org.junit.Test;

import cmc.backend.University;
import cmc.frontend.UserInteraction;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Black-box tests for the search functionality in the UserInteraction class.
 * This class verifies that the search method returns the expected results based on user input.
 */
public class UserInteractionSearchBlackBoxTest {

    /**
     * Tests the search functionality by searching universities by state.
     * Verifies that the results are not null and that all returned universities are from the specified state.
     */
    @Test
    public void testSearchByState() {
        String testState = "Minnesota";
        String input = testState + "\n";
        InputStream originalIn = System.in;
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Scanner scanner = new Scanner(System.in);

        UserInteraction userInteraction = new UserInteraction();

        try {
            List<University> results = userInteraction.search(scanner);

            assertNotNull("Search results should not be null", results);

            if (!results.isEmpty()) {
                for (University university : results) {
                    assertEquals("All results should be from the specified state", 
                                testState, university.getState());
                }
            }
        } finally {
            scanner.close();
            System.setIn(originalIn);
        }
    }
}