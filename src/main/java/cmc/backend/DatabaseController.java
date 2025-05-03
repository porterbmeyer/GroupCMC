package cmc.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmc.CMCException;
import dblibrary.project.csci230.*;

/**
 * The DatabaseController class handles interactions with the underlying UniversityDBLibrary.
 * It offers methods to add, remove, update, and retrieve information about users, universities,
 * and accounts from the database.
 */
public class DatabaseController {
    private static UniversityDBLibrary database;

    /**
     * Constructs a new DatabaseController and initializes the UniversityDBLibrary connection
     * with preset credentials.
     */
    public DatabaseController() {
        // TODO: Update the credentials to match the team's actual database.
        DatabaseController.database = new UniversityDBLibrary("pmrpmd", "Csci230$");
    }

    /**
     * Adds a new user to the database.
     *
     * @param firstname the user's first name
     * @param lastName the user's last name
     * @param username the chosen username
     * @param password the user's password
     * @param type the user type, which indicates the role
     * @return true if the user was added successfully; false otherwise
     * @throws CMCException if an error occurs during the addition
     */
    public boolean addUser(String firstname, String lastName, String username, String password, char type) throws CMCException {
        int result = this.database.user_addUser(firstname, lastName, username, password, type);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Removes a user from the database based on the username.
     *
     * @param username the username of the user to be removed
     * @return true if the user was removed successfully; false otherwise
     * @throws CMCException if an error occurs during removal
     */
    public boolean removeUser(String username) throws CMCException {
        int result = this.database.user_deleteUser(username);
        if (result != 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retrieves an account for the user with the specified username.
     *
     * @param username the username to search for
     * @return an Account object if the user is found; null otherwise
     */
    public Account getUser(String username) {
        String[][] databaseUserStrings = this.database.user_getUsers();

        for (String[] user : databaseUserStrings) {
            String thisUsername = user[2];
            if (thisUsername.equals(username)) {
                Account user1 = new User(user[0], user[1], user[2], user[3], user[4].charAt(0), user[5].charAt(0));
                return user1;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of all users in the database.
     *
     * @return a list of User objects representing every user in the database
     */
    public List<User> getAllUsers() {
        String[][] dbUserList = this.database.user_getUsers();

        ArrayList<User> result = new ArrayList<User>();
        for (String[] user : dbUserList) {
            User user1 = new User(user[0], user[1], user[2], user[3], user[4].charAt(0), user[5].charAt(0));
            result.add(user1);
        }
        return result;
    }

    /**
     * Retrieves a list of all universities in the database.
     *
     * @return a list of University objects representing all universities
     */
    public List<University> getAllUniversities() {
        String[][] dbUniversityList  = database.university_getUniversities();

        ArrayList<University> result = new ArrayList<University>();
        for (String[] university : dbUniversityList) {
            result.add(new University(university[0], university[1], university[2], university[3],
                      university[4], university[5], university[6], university[7],
                      university[8], university[9], university[10], university[11],
                      university[12], university[13], university[14], university[15]));
        }
        return result;
    }

    /**
     * Retrieves a university from the database by its name.
     *
     * @param name the name of the university to search for
     * @return a University object if found; null if not present
     * @throws CMCException if an error occurs during retrieval
     */
    public University getUniversity(String name) throws CMCException {
        String[][] dbUniversityList  = this.database.university_getUniversities();
        for (String[] university : dbUniversityList) {
            if (university[2].equals(name))
                return new University(university[0], university[1], university[2], university[3],
                        university[4], university[5], university[6], university[7],
                        university[8], university[9], university[10], university[11],
                        university[12], university[13], university[14], university[15]);
        }
        return null;
    }

    /**
     * Saves a school to the list of saved schools for a user.
     *
     * @param username the username of the user
     * @param schoolName the name of the school to save
     * @return true if the school was saved successfully; false otherwise
     */
    public boolean saveSchool(String username, String schoolName) {
        int result = this.database.user_saveSchool(username, schoolName);
        if (result != 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Saves a school for an account.
     *
     * @param account the Account object for the user
     * @param schoolName the name of the school to save
     * @return true if the school was saved successfully; false otherwise
     */
    public static boolean saveSchool1(Account account, String schoolName) {
        int result = database.user_saveSchool(account.getUsername(), schoolName);
        if (result != -1) {
            return true;
        }
        return false;
    }

    /**
     * Removes a saved school from an account's list.
     *
     * @param account the Account object for the user
     * @param school the name of the school to remove
     * @return true if the school was removed successfully; false otherwise
     */
    public boolean removeSavedSchool(Account account, String school) {
        int result = database.user_removeSchool(account.getUsername(), school);
        if (result == 1) { // Assuming 1 indicates successful removal
            return true;
        }
        return false;
    }

    /**
     * Removes a saved school from a user's list using the username.
     *
     * @param username the username of the user
     * @param school the name of the school to remove
     * @return true if the school was removed successfully; false otherwise
     */
    public static boolean removeSavedSchool1(String username, String school) {
        int result = database.user_removeSchool(username, school);
        if (result == 1) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves a mapping from usernames to their lists of saved schools.
     *
     * @return a map where each key is a username and each value is a list of saved school names
     */
    private static Map<String, List<String>> getUsersSavedSchoolMap() {
        String[][] dbMapping = database.user_getUsernamesWithSavedSchools();

        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        if (dbMapping == null) {
            return result;
        }
        for (String[] entry : dbMapping) {
            String user = entry[0];
            String school = entry[1];
            if (!result.containsKey(user))
                result.put(user, new ArrayList<String>());
            result.get(user).add(school);
        }
        return result;
    }

    /**
     * Retrieves the list of saved schools for a specific account.
     *
     * @param account the username of the account
     * @return a list of saved school names if present; otherwise, an empty list
     */
    public static List<String> getUserSavedSchoolMap(String account) {
        Map<String, List<String>> dbMapping = getUsersSavedSchoolMap();

        List<String> value = null;
        if (dbMapping.containsKey(account)) {
            value = dbMapping.get(account);
        }
        return value;
    }

    /**
     * Retrieves the complete mapping of users to their saved schools.
     *
     * @return a map where keys are usernames and values are lists of saved school names;
     *         returns null if the mapping is not available
     */
    public Map<String, List<String>> getUserSavedSchoolMap() {
        String[][] dbMapping = this.database.user_getUsernamesWithSavedSchools();

        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        if (dbMapping == null) {
            return null;
        }
        for (String[] entry : dbMapping) {
            String user = entry[0];
            String school = entry[1];
            if (!result.containsKey(user))
                result.put(user, new ArrayList<String>());
            result.get(user).add(school);
        }
        return result;
    }

    /**
     * Deactivates a user's account in the database.
     *
     * @param username the username of the account to deactivate
     * @return true if deactivation is successful
     * @throws CMCException if the account is null or an error occurs during deactivation
     */
    public boolean deactivateUser(String username) throws CMCException {
        Account acc = getUser(username);
        acc.setActive('N');

        if (acc == null) {
            throw new CMCException("Cannot deactivate a null account.");
        }

        int result = this.database.user_editUser(
            acc.getUsername(),
            acc.getFirstName(),
            acc.getFirstName(),
            acc.getPassword(),
            acc.getType(),
            acc.getActive()
        );

        if (result == -1) {
            throw new CMCException("Error deactivating account in the database.");
        }
        return true;
    }

    /**
     * Reactivates a user's account in the database.
     *
     * @param username the username of the account to reactivate
     * @return true if reactivation is successful
     * @throws CMCException if the account is null or an error occurs during reactivation
     */
    public boolean reActivateUser(String username) throws CMCException {
        Account acc = getUser(username);
        acc.setActive('Y');

        if (acc == null) {
            throw new CMCException("Cannot reactivate a null account.");
        }

        int result = this.database.user_editUser(
            acc.getUsername(),
            acc.getFirstName(),
            acc.getFirstName(),
            acc.getPassword(),
            acc.getType(),
            acc.getActive()
        );

        if (result == -1) {
            throw new CMCException("Error reactivating account in the database.");
        }
        return true;
    }

    /**
     * Removes the specified university from the database.
     *
     * @param u the University object to remove
     * @return true if the university was removed successfully
     * @throws CMCException if the university is null or an error occurs during removal
     */
    public boolean removeUniversity(University u) throws CMCException {
        if (u == null) {
            throw new CMCException("Cannot remove a null university.");
        }

        int result = this.database.university_deleteUniversity(u.getName());

        if (result != 1) {
            throw new CMCException("Error removing university from the database. It may not exist.");
        }
        return true;
    }

    /**
     * Adds a new university to the database with the provided details.
     *
     * @param name the name of the university
     * @param state the state where the university is located
     * @param location the location of the university
     * @param control the type of control (for example, public or private)
     * @param population the student population
     * @param percentFemale the percentage of female students
     * @param satVerbal the SAT verbal score
     * @param satMath the SAT math score
     * @param expenses the expenses for attending the university
     * @param percentFinancialAid the percentage of financial aid offered
     * @param numberApplicants the number of applicants
     * @param acceptanceRate the acceptance rate as a decimal value
     * @param enrollmentRate the enrollment rate as a decimal value
     * @param academicScale the academic rating scale
     * @param socialScale the social environment rating scale
     * @param qualityScale the quality of life rating scale
     * @return true if the university was added successfully; false otherwise
     * @throws CMCException if an error occurs during the addition
     */
    public boolean addUniversity(String name, String state, String location, String control, int population,
                                 double percentFemale, int satVerbal, int satMath, int expenses, double percentFinancialAid,
                                 int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale,
                                 int socialScale, int qualityScale) throws CMCException {
        int result = this.database.university_addUniversity(name, state, location, control, population, percentFemale,
                satVerbal, satMath, expenses, percentFinancialAid, numberApplicants, acceptanceRate, enrollmentRate,
                academicScale, socialScale, qualityScale);

        if (result == -1) {
            throw new CMCException("Error adding university to the database.");
        }
        return true;
    }

    /**
     * Adds a new account to the database.
     *
     * @param acc the Account object to add
     * @return true if the account was added successfully; false otherwise
     * @throws CMCException if an error occurs during the addition
     */
    public boolean addAccount(Account acc) throws CMCException {
        int result = DatabaseController.database.user_addUser(
            acc.getFirstName(),
            acc.getLastName(),
            acc.getUsername(),
            acc.getPassword(),
            acc.getType()
        );

        if (result == -1) {
            throw new CMCException("Error adding account to the DB");
        } else {
            return true;
        }
    }

    /**
     * Deletes an account from the database.
     *
     * @param acc the Account object to delete
     * @return true if the account was deleted successfully; false otherwise
     * @throws CMCException if the account is not found or an error occurs during deletion
     */
    public boolean deleteAccount(Account acc) throws CMCException {
        int result = this.database.user_deleteUser(acc.getUsername());

        if (result != 1) {
            throw new CMCException("Error deleting account from the DB. Account not found.");
        } else {
            return true;
        }
    }

    /**
     * Updates an existing account in the database with the details in the provided Account object.
     *
     * @param acc the Account object containing updated information
     * @return true if the account was updated successfully; false otherwise
     * @throws CMCException if an error occurs during the update
     */
    public boolean updateAccount(Account acc) throws CMCException {
        if (acc == null) {
            throw new CMCException("Cannot update a null account.");
        }

        int result = this.database.user_editUser(
            acc.getUsername(),
            acc.getFirstName(),
            acc.getLastName(),
            acc.getPassword(),
            acc.getType(),
            acc.getActive()
        );

        if (result == -1) {
            throw new CMCException("Error updating account in the database.");
        }
        return true;
    }

    /**
     * Edits the details of a university in the database.
     *
     * @param name the name of the university to update
     * @param state the updated state value
     * @param location the updated location
     * @param control the updated control type
     * @param numOfStudents the updated number of students
     * @param percentFemale the updated percentage of female students
     * @param SATVerbal the updated SAT verbal score
     * @param SATMath the updated SAT math score
     * @param expenses the updated expenses
     * @param percentFinancialAid the updated percentage of financial aid offered
     * @param numOfApps the updated number of applicants
     * @param acceptanceRate the updated acceptance rate
     * @param enrollmentRate the updated enrollment rate
     * @param academicScale the updated academic rating scale
     * @param socialScale the updated social environment rating scale
     * @param qualOfLife the updated quality of life rating scale
     * @return true if the university was updated successfully; false otherwise
     * @throws CMCException if an error occurs during the update
     */
    public boolean editSchool(String name, String state, String location, String control, int numOfStudents,
                              double percentFemale, int SATVerbal, int SATMath, int expenses, double percentFinancialAid,
                              int numOfApps, double acceptanceRate, double enrollmentRate, int academicScale,
                              int socialScale, int qualOfLife) throws CMCException {
        int result = this.database.university_editUniversity(name, state, location, control, numOfStudents,
                percentFemale, SATVerbal, SATMath, expenses, percentFinancialAid,
                numOfApps, acceptanceRate, enrollmentRate, academicScale, socialScale, qualOfLife);

        if (result == -1) {
            throw new CMCException("Error updating account in the database.");
        }
        return true;
    }
}
