package cmc.backend;

public class University {

	private String name;
	private String location;
	private String state;
	private int population;
	private String control;
	private double percentFemale;
	private int satVerbal;
	private int satMath;
	private int expenses;
	private double percentFinancialAid;
	private int numberApplicants;
	private double acceptanceRate;
	private double enrollmentRate;
	private int academicScale;
	private int socialScale;
	private int qualityScale;
	
	
	
	/**
	 * @param name
	 * @param location
	 * @param state
	 * @param population
	 * @param control
	 * @param percentFemale
	 * @param satVerbal
	 * @param satMath
	 * @param expenses
	 * @param percentFinancialAid
	 * @param numberApplicants
	 * @param acceptanceRate
	 * @param enrollmentRate
	 * @param academicScale
	 * @param socialScale
	 * @param qualityScale
	 */

	
	/** This is a test comment
	 */
	public University(String name, String state, String location, String control, int population, double percentFemale, int satVerbal, int satMath, int expenses, double percentFinancialAid, int numberApplicants, double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, int qualityScale) {
		this.name = name;
		this.location = location;
		this.state = state;
		this.population = population;
		this.control = control;
		this.percentFemale = percentFemale;
		this.satVerbal = satVerbal;
		this.satMath = satMath;
		this.expenses = expenses;
		this.percentFinancialAid = percentFinancialAid;
		this.numberApplicants = numberApplicants;
		this.acceptanceRate = acceptanceRate;
		this.enrollmentRate = enrollmentRate;
		this.academicScale = academicScale;
		this.socialScale = socialScale;
		this.qualityScale = qualityScale;
	}
	
	public University(String name, String state, String location, String control, String population, String percentFemale, String satVerbal, String satMath, String expenses, String percentFinancialAid, String numberApplicants, String acceptanceRate, String enrollmentRate, String academicScale, String socialScale, String qualityScale) {
		
		this.name = name;
		this.location = location;
		this.state = state;
		this.population = Integer.parseInt(population);	
		this.control = control;
		this.percentFemale = Double.parseDouble(percentFemale);
		this.satVerbal = Integer.parseInt(satVerbal);
		this.satMath = Integer.parseInt(satMath);
		this.expenses = Integer.parseInt(expenses);
		this.percentFinancialAid = Double.parseDouble(percentFinancialAid);
		this.numberApplicants = Integer.parseInt(numberApplicants);
		this.acceptanceRate = Double.parseDouble(acceptanceRate);
		this.enrollmentRate = Double.parseDouble(enrollmentRate);
		this.academicScale = Integer.parseInt(academicScale);
		this.socialScale = Integer.parseInt(socialScale);
		this.qualityScale = Integer.parseInt(qualityScale);
	}
	/**
	 * @return the qualityScale
	 */
	public int getQualityScale() {
		return qualityScale;
	}

	/**
	 * @param qualityScale the qualityScale to set
	 */
	public void setQualityScale(int qualityScale) {
		this.qualityScale = qualityScale;
	}

	/**
	 * @return the control
	public List<Un
	 */
	public String getControl() {
		return control;
	}

	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}

	/**
	 * @return the percentFemale
	 */
	public double getPercentFemale() {
		return percentFemale;
	}

	/**
	 * @param percentFemale the percentFemale to set
	 */
	public void setPercentFemale(double percentFemale) {
		this.percentFemale = percentFemale;
	}

	/**
	 * @return the satVerbal
	 */
	public int getSatVerbal() {
		return satVerbal;
	}

	/**
	 * @param satVerbal the satVerbal to set
	 */
	public void setSatVerbal(int satVerbal) {
		this.satVerbal = satVerbal;
	}

	/**
	 * @return the satMath
	 */
	public int getSatMath() {
		return satMath;
	}

	/**
	 * @param satMath the satMath to set
	 */
	public void setSatMath(int satMath) {
		this.satMath = satMath;
	}

	/**
	 * @return the expenses
	 */
	public double getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(int expenses) {
		this.expenses = expenses;
	}

	/**
	 * @return the percentFinancialAid
	 */
	public double getPercentFinancialAid() {
		return percentFinancialAid;
	}

	/**
	 * @param percentFinancialAid the percentFinancialAid to set
	 */
	public void setPercentFinancialAid(double percentFinancialAid) {
		this.percentFinancialAid = percentFinancialAid;
	}

	/**
	 * @return the numberApplicants
	 */
	public int getNumberApplicants() {
		return numberApplicants;
	}

	/**
	 * @param numberApplicants the numberApplicants to set
	 */
	public void setNumberApplicants(int numberApplicants) {
		this.numberApplicants = numberApplicants;
	}

	/**
	 * @return the acceptanceRate
	 */
	public double getAcceptanceRate() {
		return acceptanceRate;
	}

	/**
	 * @param acceptanceRate the acceptanceRate to set
	 */
	public void setAcceptanceRate(double acceptanceRate) {
		this.acceptanceRate = acceptanceRate;
	}

	/**
	 * @return the enrollmentRate
	 */
	public double getEnrollmentRate() {
		return enrollmentRate;
	}

	/**
	 * @param enrollmentRate the enrollmentRate to set
	 */
	public void setEnrollmentRate(double enrollmentRate) {
		this.enrollmentRate = enrollmentRate;
	}

	/**
	 * @return the academicScale
	 */
	public int getAcademicScale() {
		return academicScale;
	}

	/**
	 * @param academicScale the academicScale to set
	 */
	public void setAcademicScale(int academicScale) {
		this.academicScale = academicScale;
	}

	/**
	 * @return the socialScale
	 */
	public int getSocialScale() {
		return socialScale;
	}

	/**
	 * @param socialScale the socialScale to set
	 */
	public void setSocialScale(int socialScale) {
		this.socialScale = socialScale;
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

}
