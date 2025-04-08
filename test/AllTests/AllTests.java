package AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cmc.backend.AccountTest;
import cmc.backend.DatabaseControllerTest;

@RunWith(Suite.class)
@SuiteClasses({AccountTest.class,DatabaseControllerTest.class})
public class AllTests {

}
