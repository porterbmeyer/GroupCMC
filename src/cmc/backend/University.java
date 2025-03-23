package cmc.backend;

public class University {

	private String name;
	private String location;
	private String state;
	private int population;
	private double tuition;
	
	/**
	 * @param name
	 * @param location
	 * @param state
	 * @param population
	 * @param tuition
	 */
	public University(String name, String location, String state, int population, double tuition) {
		this.name = name;
		this.location = location;
		this.state = state;
		this.population = population;
		this.tuition = tuition;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @return the tuition
	 */
	public double getTuition() {
		return tuition;
	}

	/**
	 * @param tuition the tuition to set
	 */
	public void setTuition(double tuition) {
		this.tuition = tuition;
	}
	
	
	
	
}
