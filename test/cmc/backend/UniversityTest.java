package cmc.backend;

import static org.junit.Assert.*;
import org.junit.Test;

public class UniversityTest {

    @Test
    public void testConstructorAndGetters() {
        University uni = new University("Test University", "CA", "Urban", "Private","5000", "55.5", "600", "650", "40000", "70.0","8000", "75.0", "40.0", "4", "3", "5");

        assertEquals("Test University", uni.getName());
        assertEquals("CA", uni.getState());
        assertEquals("Urban", uni.getLocation());
        assertEquals("Private", uni.getControl());
        assertEquals(5000, uni.getPopulation());
        assertEquals(55.5, uni.getPercentFemale(), 0.001);
        assertEquals(600, uni.getSatVerbal());
        assertEquals(650, uni.getSatMath());
        assertEquals(40000, (int)uni.getExpenses());
        assertEquals(70.0, uni.getPercentFinancialAid(), 0.001);
        assertEquals(8000, uni.getNumberApplicants());
        assertEquals(75.0, uni.getAcceptanceRate(), 0.001);
        assertEquals(40.0, uni.getEnrollmentRate(), 0.001);
        assertEquals(4, uni.getAcademicScale());
        assertEquals(3, uni.getSocialScale());
        assertEquals(5, uni.getQualityScale());
    }

    @Test
    public void testSetters() {
        University uni = new University("Old Name", "CA", "Urban", "Private","1000", "50.0", "500", "500", "30000", "60.0","2000", "50.0", "30.0", "2", "2", "2");

        uni.setName("New Name");
        uni.setPopulation(3000);
        uni.setSatMath(700);

        assertEquals("New Name", uni.getName());
        assertEquals(3000, uni.getPopulation());
        assertEquals(700, uni.getSatMath());
    }
}
