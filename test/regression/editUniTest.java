//package test.regression;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import src.cmc.backend.String;
//import src.cmc.backend.University;
//
//class editUniTest {
//
//	private SystemController systemController;
//    private DatabaseController mockDB;
//    private User adminUser;
//
//    public String editUniversity(String[] correction) {
//    	import org.junit.Test;
//    	import static org.junit.Assert.*;
//
//    	// Very simple regression test to check university editing
//    	public class EditUniversityTest {
//    	    
//    	    @Test
//    	    public void testEditUniversity() {
//    	        // Step 1: Set up test objects
//    	        SystemController systemController = new SystemController();
//    	        
//    	        // Step 2: Prepare test data - create a test university first
//    	        String[] newUniversity = {"TEST UNIVERSITY", "CA", "SUBURBAN", "PRIVATE", "3000", "50.0", 
//    	                                 "600.0", "650.0", "40000.0", "75.0", "8000", "20.0", "40.0", "3", "4", "4"};
//    	        systemController.addUniversity(newUniversity); // Assuming you have this method
//    	        
//    	        // Step 3: Create the edit data
//    	        String[] correction = new String[6];
//    	        correction[0] = "TEST UNIVERSITY";  // Name of university to edit
//    	        correction[1] = "5000";            // New number of students
//    	        correction[2] = "10000";           // New number of applicants 
//    	        correction[3] = "5";               // New academics scale
//    	        correction[4] = "5";               // New social scale
//    	        correction[5] = "5";               // New quality of life scale
//    	        
//    	        // Step 4: Call the edit method
//    	        String result = systemController.editUniversity(correction);
//    	        
//    	        // Step 5: Check if edit was successful
//    	        assertEquals("University details updated successfully.", result);
//    	        
//    	        // Step 6: Get the university details again to verify changes
//    	        University updatedUni = systemController.myDBController.getUniDetails("TEST UNIVERSITY");
//    	        
//    	        // Step 7: Check if all values were updated correctly
//    	        assertEquals(5000, updatedUni.getNumberOfStudents());
//    	        assertEquals(10000, updatedUni.getNumberOfApplicants());
//    	        assertEquals(5, updatedUni.getAcademicsScale());
//    	        assertEquals(5, updatedUni.getSocialScale());
//    	        assertEquals(5, updatedUni.getQualityOfLifeScale());
//    	        
//    	        // Step 8: Clean up - delete test university
//    	        systemController.deleteUniversity("TEST UNIVERSITY"); // Assuming you have this method
//    	    }
//    	    
//    	    @Test
//    	    public void testPartialEdit() {
//    	        // Test what happens when we only edit some fields
//    	        SystemController systemController = new SystemController();
//    	        
//    	        // Create a test university if it doesn't exist
//    	        String[] newUniversity = {"TEST UNIVERSITY2", "CA", "SUBURBAN", "PRIVATE", "3000", "50.0", 
//    	                                 "600.0", "650.0", "40000.0", "75.0", "8000", "20.0", "40.0", "3", "4", "4"};
//    	        systemController.addUniversity(newUniversity);
//    	        
//    	        // Get original values to compare later
//    	        University originalUni = systemController.myDBController.getUniDetails("TEST UNIVERSITY2");
//    	        int originalStudents = originalUni.getNumberOfStudents();
//    	        
//    	        // Only change applicants and scales
//    	        String[] correction = new String[6];
//    	        correction[0] = "TEST UNIVERSITY2"; 
//    	        correction[1] = "-1";              // Don't change this
//    	        correction[2] = "15000";           // Change applicants
//    	        correction[3] = "5";               // Change academics
//    	        correction[4] = "-1";              // Don't change this
//    	        correction[5] = "5";               // Change quality of life
//    	        
//    	        // Edit the university
//    	        systemController.editUniversity(correction);
//    	        
//    	        // Get updated university
//    	        University updatedUni = systemController.myDBController.getUniDetails("TEST UNIVERSITY2");
//    	        
//    	        // Check that only the specified fields changed
//    	        assertEquals(originalStudents, updatedUni.getNumberOfStudents()); // Shouldn't change
//    	        assertEquals(15000, updatedUni.getNumberOfApplicants());          // Should change
//    	        assertEquals(5, updatedUni.getAcademicsScale());                  // Should change
//    	        assertEquals(4, updatedUni.getSocialScale());                     // Shouldn't change
//    	        assertEquals(5, updatedUni.getQualityOfLifeScale());              // Should change
//    	        
//    	        // Clean up
//    	        systemController.deleteUniversity("TEST UNIVERSITY2");
//    	    }
//    	}