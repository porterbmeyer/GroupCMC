package cmc.mocks;

import cmc.CMCException;
import cmc.backend.Account;
import cmc.backend.DatabaseController;
import cmc.backend.University;
import cmc.backend.User;
import dblibrary.project.csci230.UniversityDBLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDataBaseController extends DatabaseController {
    public ArrayList<User> users;
    public ArrayList<University> universities;
    public Map<String, List<String>> userSavedSchools;

    public MockDataBaseController() {
        users = new ArrayList<>();
        universities = new ArrayList<>();
        userSavedSchools = new HashMap<>();
    }

    /**
     * Adds a new user to the mock database.
     * @param firstname String representing the user's first name
     * @param lastName String representing the user's last name
     * @param username String representing the user's username
     * @param password String representing the user's password
     * @param type char representing the user's type
     * @return true if the user was added successfully; false otherwise
     */
    public boolean addUser(String firstname, String lastName, String username, String password, char type) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Duplicate username
            }
        }
        User user = new User(firstname, lastName, username, password, type, 'Y');
        users.add(user);
        return true;
    }
    public boolean addAccount(User user) throws CMCException {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new CMCException("Duplicate Username");
            }
        }
        users.add(user);
        return true;
    }
    /**
     * Removes a user from the mock database.
     * @param username String representing the user's username
     * @return true if the user was removed successfully; false otherwise
     */
    public boolean removeUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                return true; // User removed
            }
        }
        return false; // User not found
    }
    /**
     * Retrieves a user from the mock database.
     * @param username String representing the user's username
     * @return User object if found; null otherwise
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }
    /**
     * Retrieves all users from the mock database.
     * @return List of User objects
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Adds a new university to the mock database.
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
     */
    public boolean addUniversity(String name, String state, String location, String control, int population,
                                 double percentFemale, int satVerbal, int satMath, int expenses, double percentFinancialAid,
                                 int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale,
                                 int socialScale, int qualityScale) {
        for (University university : universities) {
            if (university.getName().equals(name)) {
                return false; // Duplicate university
            }
        }
        University university = new University(name, state, location, control, population,
                percentFemale, satVerbal, satMath, expenses,
                percentFinancialAid, numberApplicants,
                acceptanceRate, enrollmentRate,
                academicScale, socialScale,
                qualityScale);
        universities.add(university);
        return true;
    }
    /**
     * Removes a university from the mock database.
     * @param university University representing the university's name
     * @return true if the university was removed successfully; false otherwise
     */
    public boolean removeUniversity(University university) {
        for (University uni : universities) {
            if (uni.getName().equals(university.getName())) {
                universities.remove(uni);
                return true; // University removed
            }
        }
        return false; // University not found
    }
    /**
     * Retrieves a university from the mock database.
     * @param name String representing the university's name
     * @return University object if found; null otherwise
     */
    public University getUniversity(String name) {
        for (University university : universities) {
            if (university.getName().equals(name)) {
                return university;
            }
        }
        return null; // University not found
    }
    /**
     * Saves a school for a user in the mock database.
     * @param username String representing the user's username
     * @param schoolName String representing the school's name
     * @return true if the school was saved successfully; false otherwise
     */
    public boolean saveSchool(String username, String schoolName) {
        if (!userSavedSchools.containsKey(username)) {
            userSavedSchools.put(username, new ArrayList<>());
        }
        userSavedSchools.get(username).add(schoolName);
        return true;
    }
    /**
     * Edits the details of an existing university in the mock database.
     * @param name the name of the university to edit
     * @param state the new state of the university
     * @param location the new location of the university
     * @param control the new control type (e.g., public or private)
     * @param population the new student population
     * @param percentFemale the new percentage of female students
     * @param satVerbal the new SAT verbal score
     * @param satMath the new SAT math score
     * @param expenses the new expenses for attending the university
     * @param percentFinancialAid the new percentage of financial aid offered
     * @param numberApplicants the new number of applicants
     * @param acceptanceRate the new acceptance rate as a decimal value
     * @param enrollmentRate the new enrollment rate as a decimal value
     * @param academicScale the new academic rating scale
     * @param socialScale the new social environment rating scale
     * @param qualityScale the new quality of life rating scale
     * @return true if the university was updated successfully; false otherwise
     */
    public boolean editSchool(String name, String state, String location, String control, int population,
                              double percentFemale, int satVerbal, int satMath, int expenses, double percentFinancialAid,
                              int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale,
                              int socialScale, int qualityScale) {
        for (University university : universities) {
            if (university.getName().equals(name)) {
                university.setState(state);
                university.setLocation(location);
                university.setControl(control);
                university.setPopulation(population);
                university.setPercentFemale(percentFemale);
                university.setSatVerbal(satVerbal);
                university.setSatMath(satMath);
                university.setExpenses(expenses);
                university.setPercentFinancialAid(percentFinancialAid);
                university.setNumberApplicants(numberApplicants);
                university.setAcceptanceRate(acceptanceRate);
                university.setEnrollmentRate(enrollmentRate);
                university.setAcademicScale(academicScale);
                university.setSocialScale(socialScale);
                university.setQualityScale(qualityScale);
                return true; // University updated
            }
        }
        return false; // University not found
    }
    /**
     * Removes a saved school for a user in the mock database.
     * @param username String representing the user's username
     * @param schoolName String representing the school's name
     * @return true if the school was removed successfully; false otherwise
     */
    public boolean removeSavedSchool(String username, String schoolName) {
        if (userSavedSchools.containsKey(username)) {
            List<String> savedSchools = userSavedSchools.get(username);
            if (savedSchools.contains(schoolName)) {
                savedSchools.remove(schoolName);
                return true;
            }
        }
        return false; // School not found in user's saved schools
    }
    /**
     * Retrieves a user's saved schools from the mock database.
     * @param username String representing the user's username
     * @return List of saved school names; empty list if none found
     */
    public List<String> getUserSavedSchools(String username) {
        return userSavedSchools.getOrDefault(username, new ArrayList<>());
    }
    /**
     * Retrieves all universities from the mock database.
     * @return List of University objects
     */
    /*
    public List<University> getAllUniversities() {
        return universities;
    }
     */
    /**
     * Deactivates a user in the mock database.
     * @param username String representing the user's username
     * @return true if the user was deactivated successfully; false otherwise
     */
    public boolean deactivateUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setActive('N');
                return true;
            }
        }
        return false; // User not found
    }
    /**
     * Reactivates a user in the mock database.
     * @param username String representing the user's username
     * @return true if the user was reactivated successfully; false otherwise
     */
    public boolean reActivateUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setActive('Y');
                return true;
            }
        }
        return false; // User not found
    }

    /**
     * Updates an existing account in the mock database.
     * @param acc Account object containing updated account details
     * @return true if the account was updated successfully; false otherwise
     * @throws CMCException if an error occurs during the update process
     */
    public boolean updateAccount(Account acc) throws CMCException {
        for (User user : users) {
            if (user.getUsername().equals(acc.getUsername())) {
                user.setFirstName(acc.getFirstName());
                user.setLastName(acc.getLastName());
                user.setPassword(acc.getPassword());
                user.setType(acc.getType());
                user.setActive(acc.getActive());
                return true; // User updated
            }
        }
        return false; // User not found
    }

    /**
     * Retrieves the mapping of users to their saved schools.
     * @return Map where keys are usernames and values are lists of saved school names
     */
    public Map<String, List<String>> getUserSavedSchoolMap() {
        return userSavedSchools;
    }

    public boolean deleteAccount(Account acc) throws CMCException {
        for (User user : users) {
            if (user.getUsername().equals(acc.getUsername())) {
                users.remove(user);
                return true; // User removed
            }
        }
        throw new CMCException("User not found");
        //return false; // User not found
    }

}