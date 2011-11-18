package Management;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DueDateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPastDue() {
		Calendar fakeDate = Calendar.getInstance();
		fakeDate.set(2000, 12, 2, 9, 21);
		assertTrue(DueDate.pastDue(fakeDate));
		fakeDate.set(2020, 12, 2, 9, 21);
		assertTrue(!DueDate.pastDue(fakeDate));
		
	}

}
