package regression;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmc.CMCException;
import cmc.backend.University;
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
	public void searchByStatetest() throws CMCException {

		UserInteraction sc = new UserInteraction();
		Scanner validScanner = new Scanner(new StringReader("1"));
		List<University> result1 = sc.search(validScanner);
		Assert.assertTrue(result1.size() > 0);
	}
}