package cmc.backend;

import dblibrary.project.csci230.UniversityDBLibrary;

public class SearchController {
	private UniversityDBLibrary database;

	public SearchController() {
		// TODO: we'll need to update this to our team's actual database someday!
		this.database = new UniversityDBLibrary("pmrpmd", "Csci230$");
	}
}
