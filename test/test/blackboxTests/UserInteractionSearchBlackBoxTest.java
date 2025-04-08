package test.blackboxTests;
import static org.junit.Assert.*;
import org.junit.Test;

import cmc.backend.University;
import cmc.frontend.UserInteraction;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class UserInteractionSearchBlackBoxTest {
    
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