package cmc.backend;

import java.util.List;

import cmc.CMCException;

/**
 * The UniversityController class provides methods for managing university data.
 * It supports operations such as adding, editing, retrieving, and deleting university records.
 */
public class UniversityController {

    private DatabaseController myDBcontroller;

    /**
     * Deletes a university from the database based on the university name.
     *
     * @param uniName the name of the university to delete
     * @return true if the university was successfully deleted; false otherwise
     * @throws CMCException if an error occurs during the deletion process
     */
    public boolean deleteUniversity(String uniName) throws CMCException {
        University u = getUniversityByName(uniName);
        
        if (u == null) {
            return false;
        } else {
            return this.myDBcontroller.removeUniversity(u);
        }
    }

    /**
     * Retrieves a list of all universities from the database.
     *
     * @return a List of University objects representing all universities
     */
    public List<University> getAllUniversities() {
        List<University> universityList = this.myDBcontroller.getAllUniversities();
        return universityList;
    }

    /**
     * Initiates an edit operation for the university identified by its name.
     * Note: This method is not fully implemented.
     *
     * @param name the name of the university to edit
     * @return false since the functionality is not yet implemented
     */
    public boolean editUniversity(String name) {
        University newUni = getUniversityByName(name);
        
        if (newUni == null) {
            return false;
        }
        
        return false;
    }
    
    /**
     * Adds a new university to the database with the provided parameters.
     *
     * @param name                the name of the university
     * @param state               the state where the university is located
     * @param location            the location of the university
     * @param control             the control type (for example, public or private)
     * @param numOfStudents       the number of students enrolled
     * @param percentFemale       the percentage of female students
     * @param SATVerbal           the SAT verbal score
     * @param SATMath             the SAT math score
     * @param expenses            the expenses for attending the university
     * @param percentFinancialAid the percentage of financial aid offered
     * @param numOfApps           the number of applicants
     * @param acceptanceRate      the university's acceptance rate
     * @param enrollmentRate      the enrollment rate after acceptance
     * @param academicScale       the academic quality scale rating
     * @param socialScale         the social environment scale rating
     * @param qualOfLife          the quality of life scale rating
     * @return true if the university was successfully added; false otherwise
     * @throws CMCException if an error occurs while adding the university
     */
    public boolean addUniversity(String name, String state, String location, String control, int numOfStudents, 
                                 double percentFemale, int SATVerbal, int SATMath, int expenses, double percentFinancialAid, 
                                 int numOfApps, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, 
                                 int qualOfLife) throws CMCException {
        
        return this.myDBcontroller.addUniversity(name, state, location, control, numOfStudents, percentFemale, SATVerbal, 
                                                  SATMath, expenses, percentFinancialAid, numOfApps, acceptanceRate, enrollmentRate, 
                                                  academicScale, socialScale, qualOfLife);
    }
    
    /**
     * Retrieves a University object by its name.
     *
     * @param name the name of the university to search for
     * @return the University object if found; otherwise, null
     */
    public University getUniversityByName(String name) {
        if (name == null) {
            return null;
        }
        List<University> loopUnis = getAllUniversities();
        
        for (University currUni : loopUnis) {
            if (currUni.getName().equals(name)) {
                return currUni;
            }
        }
        return null;
    }
    
    /**
     * Edits the details of an existing university.
     * For any parameter provided as a default (such as null or 0), the current value is retained.
     *
     * @param name                the name of the university to edit
     * @param state               the new state, or null to keep the existing state
     * @param location            the new location, or null to keep the existing location
     * @param control             the new control type, or null to keep the existing control
     * @param numOfStudents       the new number of students, or 0 to keep the existing population
     * @param percentFemale       the new percentage of female students, or 0.0 to keep the existing value
     * @param SATVerbal           the new SAT verbal score, or 0 to keep the existing score
     * @param SATMath             the new SAT math score, or 0 to keep the existing score
     * @param expenses            the new expenses, or 0 to keep the existing value
     * @param percentFinancialAid the new financial aid percentage, or 0.0 to keep the existing value
     * @param numOfApps           the new number of applicants, or 0 to keep the existing value
     * @param acceptanceRate      the new acceptance rate, or 0.0 to keep the existing value
     * @param enrollmentRate      the new enrollment rate, or 0.0 to keep the existing value
     * @param academicScale       the new academic scale rating, or 0 to keep the existing rating
     * @param socialScale         the new social scale rating, or 0 to keep the existing rating
     * @param qualOfLife          the new quality of life rating, or 0 to keep the existing rating
     * @return true if the university was successfully edited; false otherwise
     * @throws CMCException if an error occurs during the edit operation
     */
    public boolean editUniversity(String name, String state, String location, String control, int numOfStudents, 
                                  double percentFemale, int SATVerbal, int SATMath, int expenses, double percentFinancialAid, 
                                  int numOfApps, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, 
                                  int qualOfLife) throws CMCException {
        University current = this.myDBcontroller.getUniversity(name);
        if (current == null) {
            return false; 
        }

        state = (state == null) ? current.getState() : state;
        location = (location == null) ? current.getLocation() : location;
        control = (control == null) ? current.getControl() : control;
        numOfStudents = (numOfStudents == 0) ? current.getPopulation() : numOfStudents;
        percentFemale = (percentFemale == 0.0) ? current.getPercentFemale() : percentFemale;
        SATVerbal = (SATVerbal == 0) ? current.getSatVerbal() : SATVerbal;
        SATMath = (SATMath == 0) ? current.getSatMath() : SATMath;
        expenses = (expenses == 0) ? (int) current.getExpenses() : expenses;
        percentFinancialAid = (percentFinancialAid == 0.0) ? current.getPercentFinancialAid() : percentFinancialAid;
        numOfApps = (numOfApps == 0) ? current.getNumberApplicants() : numOfApps;
        acceptanceRate = (acceptanceRate == 0.0) ? current.getAcceptanceRate() : acceptanceRate;
        enrollmentRate = (enrollmentRate == 0.0) ? current.getEnrollmentRate() : enrollmentRate;
        academicScale = (academicScale == 0) ? current.getAcademicScale() : academicScale;
        socialScale = (socialScale == 0) ? current.getSocialScale() : socialScale;
        qualOfLife = (qualOfLife == 0) ? current.getQualityScale() : qualOfLife;

        return this.myDBcontroller.editSchool(name, state, location, control, numOfStudents, percentFemale, SATVerbal, 
                                               SATMath, expenses, percentFinancialAid, numOfApps, acceptanceRate, enrollmentRate, 
                                               academicScale, socialScale, qualOfLife);
    }
    public void injectMock(DatabaseController mockDBController) {
        this.myDBcontroller = mockDBController;
    }
}
