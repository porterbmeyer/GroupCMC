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

@RunWith(Suite.class)
@SuiteClasses({AccountControllerTest.class,AccountTest.class,DatabaseControllerTest.class, UniversityControllerTest.class,UniversityTest.class,UserTest.class})
public class AllTests {

}
