package regression;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.backend.SystemController;
import cmc.frontend.UserInteraction;
import junit.framework.Assert;

public class SearchByStateBug {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void searchByStatetest() {

		UserInteraction sc = new UserInteraction();
		List<String[]> result1 = sc.search("");
		Assert.assertTrue(result1.size() > 0);
	}
}