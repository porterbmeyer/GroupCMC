package cmc.backend;

import dblibrary.project.csci230.UniversityDBLibrary;

/**
 * The SearchController class provides a connection to the university database.
 * It is responsible for initializing the database and will later include search functionality.
 */
public class SearchController {
    private UniversityDBLibrary database;

    /**
     * Constructs a new SearchController and initializes the UniversityDBLibrary with preset credentials.
     * Note: The credentials and database configuration will need to be updated to match the team's actual database.
     */
    public SearchController() {
        this.database = new UniversityDBLibrary("pmrpmd", "Csci230$");
    }
}
