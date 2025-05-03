package cmc.backend;

/**
 * The University class represents a university with various attributes such as name, location, and ratings.
 * It provides constructors to initialize a university and getter and setter methods for its attributes.
 */
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
     * Constructs a new University with the specified details.
     *
     * @param name                the name of the university
     * @param state               the state where the university is located
     * @param location            the location of the university
     * @param control             the control type (for example, public or private)
     * @param population          the student population
     * @param percentFemale       the percentage of female students
     * @param satVerbal           the SAT verbal score
     * @param satMath             the SAT math score
     * @param expenses            the expenses for attending the university
     * @param percentFinancialAid the percentage of financial aid offered
     * @param numberApplicants    the number of applicants
     * @param acceptanceRate      the acceptance rate
     * @param enrollmentRate      the enrollment rate
     * @param academicScale       the academic quality rating
     * @param socialScale         the social environment rating
     * @param qualityScale        the overall quality rating
     */
    public University(String name, String state, String location, String control, int population, double percentFemale,
                      int satVerbal, int satMath, int expenses, double percentFinancialAid, int numberApplicants,
                      double acceptanceRate, double enrollmentRate, int academicScale, int socialScale, int qualityScale) {
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
    
    /**
     * Constructs a new University using an array of strings.
     * The array is expected to hold values in the order required by the main constructor.
     *
     * @param university an array of strings containing university details
     */
    @SuppressWarnings("unused")
    private University(String[] university) {
        new University(university[0], university[1], university[2], university[3], university[4], university[5],
                       university[6], university[7], university[8], university[9], university[10], university[11],
                       university[12], university[13], university[14], university[15]);
    }
    
    /**
     * Constructs a new University with all parameters as strings.
     * The string values are parsed into the appropriate data types.
     *
     * @param name                the name of the university
     * @param state               the state where the university is located
     * @param location            the location of the university
     * @param control             the control type
     * @param population          the population as a string (parsed to int)
     * @param percentFemale       the female percentage as a string (parsed to double)
     * @param satVerbal           the SAT verbal score as a string (parsed to int)
     * @param satMath             the SAT math score as a string (parsed to int)
     * @param expenses            the expenses as a string (parsed to int)
     * @param percentFinancialAid the financial aid percentage as a string (parsed to double)
     * @param numberApplicants    the number of applicants as a string (parsed to int)
     * @param acceptanceRate      the acceptance rate as a string (parsed to double)
     * @param enrollmentRate      the enrollment rate as a string (parsed to double)
     * @param academicScale       the academic scale as a string (parsed to int)
     * @param socialScale         the social scale as a string (parsed to int)
     * @param qualityScale        the quality scale as a string (parsed to int)
     */
    public University(String name, String state, String location, String control, String population, String percentFemale,
                      String satVerbal, String satMath, String expenses, String percentFinancialAid, String numberApplicants,
                      String acceptanceRate, String enrollmentRate, String academicScale, String socialScale, String qualityScale) {
        this.name = name;
        this.location = location;
        this.state = state;
        this.population = (int)Double.parseDouble(population);  
        this.control = control;
        this.percentFemale = Double.parseDouble(percentFemale);
        this.satVerbal = (int)Double.parseDouble(satVerbal);
        this.satMath = (int)Double.parseDouble(satMath);
        this.expenses = (int)Double.parseDouble(expenses);
        this.percentFinancialAid = Double.parseDouble(percentFinancialAid);
        this.numberApplicants = (int)Double.parseDouble(numberApplicants);
        this.acceptanceRate = Double.parseDouble(acceptanceRate);
        this.enrollmentRate = Double.parseDouble(enrollmentRate);
        this.academicScale = (int)Double.parseDouble(academicScale);
        this.socialScale = (int)Double.parseDouble(socialScale);
        this.qualityScale = (int)Double.parseDouble(qualityScale);
    }
    
    /**
     * Returns the overall quality scale rating of the university.
     *
     * @return the quality scale rating
     */
    public int getQualityScale() {
        return qualityScale;
    }

    /**
     * Sets the overall quality scale rating of the university.
     *
     * @param qualityScale the new quality scale rating
     */
    public void setQualityScale(int qualityScale) {
        this.qualityScale = qualityScale;
    }

    /**
     * Returns the control type of the university.
     *
     * @return the control type
     */
    public String getControl() {
        return control;
    }

    /**
     * Sets the control type of the university.
     *
     * @param control the new control type
     */
    public void setControl(String control) {
        this.control = control;
    }

    /**
     * Returns the percentage of female students.
     *
     * @return the percentage of female students
     */
    public double getPercentFemale() {
        return percentFemale;
    }

    /**
     * Sets the percentage of female students.
     *
     * @param percentFemale the new percentage of female students
     */
    public void setPercentFemale(double percentFemale) {
        this.percentFemale = percentFemale;
    }

    /**
     * Returns the SAT verbal score.
     *
     * @return the SAT verbal score
     */
    public int getSatVerbal() {
        return satVerbal;
    }

    /**
     * Sets the SAT verbal score.
     *
     * @param satVerbal the new SAT verbal score
     */
    public void setSatVerbal(int satVerbal) {
        this.satVerbal = satVerbal;
    }

    /**
     * Returns the SAT math score.
     *
     * @return the SAT math score
     */
    public int getSatMath() {
        return satMath;
    }

    /**
     * Sets the SAT math score.
     *
     * @param satMath the new SAT math score
     */
    public void setSatMath(int satMath) {
        this.satMath = satMath;
    }

    /**
     * Returns the expenses for attending the university.
     *
     * @return the expenses
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     * Sets the expenses for attending the university.
     *
     * @param expenses the new expenses value
     */
    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    /**
     * Returns the percentage of financial aid offered.
     *
     * @return the financial aid percentage
     */
    public double getPercentFinancialAid() {
        return percentFinancialAid;
    }

    /**
     * Sets the percentage of financial aid offered.
     *
     * @param percentFinancialAid the new financial aid percentage
     */
    public void setPercentFinancialAid(double percentFinancialAid) {
        this.percentFinancialAid = percentFinancialAid;
    }

    /**
     * Returns the number of applicants.
     *
     * @return the number of applicants
     */
    public int getNumberApplicants() {
        return numberApplicants;
    }

    /**
     * Sets the number of applicants.
     *
     * @param numberApplicants the new number of applicants
     */
    public void setNumberApplicants(int numberApplicants) {
        this.numberApplicants = numberApplicants;
    }

    /**
     * Returns the acceptance rate of the university.
     *
     * @return the acceptance rate
     */
    public double getAcceptanceRate() {
        return acceptanceRate;
    }

    /**
     * Sets the acceptance rate of the university.
     *
     * @param acceptanceRate the new acceptance rate
     */
    public void setAcceptanceRate(double acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    /**
     * Returns the enrollment rate of the university.
     *
     * @return the enrollment rate
     */
    public double getEnrollmentRate() {
        return enrollmentRate;
    }

    /**
     * Sets the enrollment rate of the university.
     *
     * @param enrollmentRate the new enrollment rate
     */
    public void setEnrollmentRate(double enrollmentRate) {
        this.enrollmentRate = enrollmentRate;
    }

    /**
     * Returns the academic quality rating.
     *
     * @return the academic scale rating
     */
    public int getAcademicScale() {
        return academicScale;
    }

    /**
     * Sets the academic quality rating.
     *
     * @param academicScale the new academic rating
     */
    public void setAcademicScale(int academicScale) {
        this.academicScale = academicScale;
    }

    /**
     * Returns the social environment rating.
     *
     * @return the social scale rating
     */
    public int getSocialScale() {
        return socialScale;
    }

    /**
     * Sets the social environment rating.
     *
     * @param socialScale the new social rating
     */
    public void setSocialScale(int socialScale) {
        this.socialScale = socialScale;
    }

    /**
     * Returns the name of the university.
     *
     * @return the university's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the university.
     *
     * @param name the new name for the university
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the location of the university.
     *
     * @return the university's location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the university.
     *
     * @param location the new location for the university
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the state where the university is located.
     *
     * @return the state of the university
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state where the university is located.
     *
     * @param state the new state for the university
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the student population.
     *
     * @return the population of the university
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets the student population.
     *
     * @param population the new population value
     */
    public void setPopulation(int population) {
        this.population = population;
    }
}
