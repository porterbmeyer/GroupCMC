package cmc.backend;
import static org.junit.Assert.*;
import org.junit.Test;

import cmc.frontend.UserInteraction;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class UserInteractionSearchBlackBoxTest {
    
    @Test
    public void testSearchByState() {
        // Setup - prepare input simulation
        String testState = "Minnesota";
        String input = testState + "\n";
        InputStream originalIn = System.in;
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
        
        // Create a scanner with the simulated input
        Scanner scanner = new Scanner(System.in);
        
        // Create UserInteraction instance
        UserInteraction userInteraction = new UserInteraction();
        
        try {
            // Execute the search method
            List<University> results = userInteraction.search(scanner);
            
            // Assertions
            assertNotNull("Search results should not be null", results);
            
            // Only check if there are results (we don't know the database content)
            if (!results.isEmpty()) {
                // If we got results, they should all be from the specified state
                for (University university : results) {
                    assertEquals("All results should be from the specified state", 
                                testState, university.getState());
                }
            }
        } finally {
            // Clean up
            scanner.close();
            System.setIn(originalIn);
        }
    }
}