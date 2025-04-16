package cmc.backend;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The UserSchools class extends University to provide additional functionality 
 * for managing a user's saved schools.
 */
public class UserSchools extends University {

    private Map<University, Map<User, Date>> savedSchools;
    private DatabaseController myDBController;

    /**
     * Constructs a new UserSchools instance with the specified university details.
     *
     * @param name                the name of the university
     * @param state               the state where the university is located
     * @param location            the location of the university
     * @param control             the control type (for example, public or private)
     * @param population          the population of the university
     * @param percentFemale       the percentage of female students
     * @param satVerbal           the SAT verbal score
     * @param satMath             the SAT math score
     * @param expenses            the expenses for the university
     * @param percentFinancialAid the percentage of financial aid offered
     * @param numberApplicants    the number of applicants
     * @param acceptanceRate      the acceptance rate
     * @param enrollmentRate      the enrollment rate
     * @param academicScale       the academic quality scale rating
     * @param socialScale         the social scale rating
     * @param qualityScale        the overall quality scale rating
     */
    public UserSchools(String name, String state, String location, String control, int population,
                       double percentFemale, int satVerbal, int satMath, int expenses, double percentFinancialAid,
                       int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale,
                       int socialScale, int qualityScale) {
        super(name, state, location, control, population, percentFemale, satVerbal, satMath, expenses,
                percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate, academicScale, socialScale, qualityScale);
    }
    
    /**
     * Saves a school for the given user.
     * <p>
     * If the school already exists in the user's saved list, an IllegalArgumentException is created.
     * Otherwise, the method delegates to the DatabaseController to save the school.
     * </p>
     *
     * @param username the username of the user
     * @param name     the name of the school to be saved
     * @return true if the school was successfully saved; false otherwise
     */
    public boolean saveSchool(String username, String name) {
        List<String> schoolsSaved1 = getSavedSchools(username);

        for (String schol : schoolsSaved1) {
            if (schol.equals(name)) {
                new IllegalArgumentException("School Already Saved");
            }
        }
        return this.myDBController.saveSchool(username, name);
    }
    
    /**
     * Removes a school from the saved list of a user.
     *
     * @param username the identifier for the user (as an integer id)
     * @param name     the identifier for the school (as an integer id)
     */
    public void removeSavedSchool(int username, int name) {
        // Method not implemented
    }
    
    /**
     * Retrieves the list of saved school names for the specified user.
     *
     * @param username the username of the user
     * @return a List of school names that the user has saved
     */
    public List<String> getSavedSchools(String username) {
        Map<String, List<String>> usersToSavedSchools = this.myDBController.getUserSavedSchoolMap();
        return usersToSavedSchools.get(username);
    }
    
    /**
     * Gets the date when a school was saved by the user.
     *
     * @param username the user's identifier (as an integer id)
     * @param name     the school's identifier (as an integer id)
     * @return the Date when the school was saved, or null if not found
     */
    public Date getSavedDate(int username, int name) {
        return null;
    }
}
