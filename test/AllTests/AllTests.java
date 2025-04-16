package AllTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cmc.backend.AccountControllerTest;
import cmc.backend.AccountTest;
import cmc.backend.DatabaseControllerTest;
import cmc.backend.UniversityControllerTest;
import cmc.backend.UniversityTest;
import cmc.backend.UserTest;

/**
 * The AllTests class aggregates multiple JUnit test classes into a single test suite.
 * This suite is designed to run unit tests for various components of the system, including:
 * <ul>
 *   <li>Account management (AccountControllerTest and AccountTest)</li>
 *   <li>Database operations (DatabaseControllerTest)</li>
 *   <li>University related functionality (UniversityControllerTest and UniversityTest)</li>
 *   <li>User-related functionality (UserTest)</li>
 * </ul>
 * Running this suite ensures that all critical functionalities of the system are verified together.
 */
@RunWith(Suite.class)
@SuiteClasses({
    AccountControllerTest.class,
    AccountTest.class,
    DatabaseControllerTest.class,
    UniversityControllerTest.class,
    UniversityTest.class,
    UserTest.class
})
public class AllTests {
    // This class remains empty. It is used only as a holder for the above annotations.
}
