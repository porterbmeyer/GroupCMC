package cmc.backend;

import java.util.List;

/**
 * The User class represents a user account with functionality to manage saved schools.
 * It extends the Account class and provides methods to add or remove saved schools.
 */
public class User extends Account {

    private List<String> savedSchoolList;

    /**
     * Constructs a new User instance with the specified details.
     *
     * @param firstname the user's first name
     * @param lastname  the user's last name
     * @param username  the unique username for the account
     * @param password  the user's password
     * @param type      the type of user
     * @param active    the active status of the account
     */
    public User(String firstname, String lastname, String username, String password, char type, char active) {
        super(firstname, lastname, username, password, type, active);
        //setSavedSchool();
    }

    /**
     * Initializes the list of saved schools from the database using the user's username.
     */
    private void setSavedSchool() {
        this.savedSchoolList = DatabaseController.getUserSavedSchoolMap(this.getUsername());
    }

    /**
     * Retrieves the list of saved schools for the user.
     *
     * @return a List of saved school names
     */
    public List<String> getSavedSchoolList() {
        return savedSchoolList;
    }

    /**
     * Sets the saved school list for the user.
     *
     * @param savedSchoolList a List of saved school names to set
     */
    public void setSavedSchoolList(List<String> savedSchoolList) {
        this.savedSchoolList = savedSchoolList;
    }
    
    /**
     * Adds a school to the user's saved school list.
     *
     * @param school the name of the school to add
     * @return true if the school was successfully added; false otherwise
     */
    public boolean addSavedSchool(String school) {
        return DatabaseController.saveSchool1(this, school);
    }

    /**
     * Removes a school from the user's saved school list.
     *
     * @param school the name of the school to remove
     * @return true if the school was successfully removed; false otherwise
     */
    public boolean removeSavedSchool(String school) {
        return DatabaseController.removeSavedSchool(this, school);
    }
}
