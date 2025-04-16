package cmc.backend;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import cmc.mocks.MockDataBaseController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;

/**
 * The UniversityControllerTest class provides unit tests for the UniversityController.
 * It tests operations for retrieving, adding, and deleting universities from the database.
 */
public class UniversityControllerTest {

    private UniversityController controller;
    private DatabaseController dbController;

    /**
     * Sets up the test environment by creating a UniversityController,
     * injecting a DatabaseController into it, and adding a fake university.
     *
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        controller = new UniversityController();
        dbController = new MockDataBaseController();
        //dbController = new DatabaseController();
        controller.injectMock(dbController);
        // Inject the DatabaseController into the UniversityController using reflection.
        Field dbField = UniversityController.class.getDeclaredField("myDBcontroller");
        dbField.setAccessible(true);
        dbField.set(controller, dbController);

        boolean fakeAdd = controller.addUniversity(
                "Fake University", "Fake State", "Urban", "Public",
                5000, 55.0, 600, 620, 15000, 30.0, 1000, 40.0, 60.0, 5, 4, 3);
        if (!fakeAdd) {
            throw new Exception("Failed to add fake university in setUp.");
        }
    }

    /**
     * Tears down the test environment by attempting to delete the fake university and any added real university.
     *
     * @throws Exception if an error occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        try {
            controller.deleteUniversity("Fake University");
        } catch (CMCException e) {
            System.err.println("Error deleting Fake University in tearDown: " + e.getMessage());
        }
        try {
            controller.deleteUniversity("Real University");
        } catch (CMCException e) {
            System.err.println("Error deleting Real University in tearDown: " + e.getMessage());
        }
    }

    /**
     * Tests that getUniversityByName returns a non-null university for an existing university.
     *
     * @throws CMCException if an error occurs during retrieval
     */
    @Test
    public void testGetUniversityByName() throws CMCException {
        University uni = controller.getUniversityByName("Fake University");
        assertNotNull("Fake University should exist", uni);
        assertEquals("Fake University", uni.getName());
        
        University nonExistent = controller.getUniversityByName("Nonexistent University");
        assertNull("Non-existent university should return null", nonExistent);
    }

    /**
     * Tests that getAllUniversities returns a list containing the fake university.
     *
     * @throws CMCException if an error occurs during retrieval
     */
    @Test
    public void testGetAllUniversities() throws CMCException {
        List<University> allUnis = controller.getAllUniversities();
        boolean foundFake = false;
        for (University uni : allUnis) {
            if ("Fake University".equals(uni.getName())) {
                foundFake = true;
                break;
            }
        }
        assertTrue("Fake University should be present in the list", foundFake);
    }

    /**
     * Tests that a new university can be added and subsequently retrieved.
     *
     * @throws CMCException if an error occurs during addition or retrieval
     */
    @Test
    public void testAddUniversity() throws CMCException {
        boolean added = controller.addUniversity(
                "Real University", "Real State", "Suburban", "Private",
                8000, 50.0, 650, 670, 18000, 35.0, 1100, 45.0, 65.0, 4, 5, 4);
        assertTrue("Real University should be added successfully", added);

        University fakeUni = controller.getUniversityByName("Fake University");
        University realUni = controller.getUniversityByName("Real University");
        assertNotNull("Fake University should exist", fakeUni);
        assertNotNull("Real University should be retrievable", realUni);
    }

    /**
     * Tests that a university can be deleted from the database.
     *
     * @throws CMCException if an error occurs during deletion
     */
    @Test
    public void testDeleteUniversity() throws CMCException {
        boolean deleted = controller.deleteUniversity("Fake University");
        assertTrue("Deleting Fake University should succeed", deleted);

        University uni = controller.getUniversityByName("Fake University");
        assertNull("Fake University should not exist after deletion", uni);

        boolean secondDelete = controller.deleteUniversity("Fake University");
        assertFalse("Deleting a non-existent university should return false", secondDelete);
    }

    // The edit university functionality is pending implementation.
}
