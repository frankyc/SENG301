package Users;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testStudent() {
		Student newStudent = new Student();
		assertEquals(3,newStudent.getPermissions());
		assertEquals("",newStudent.getName());
		assertNull(newStudent.getCourse());
	}

	@Test
	public void testStudentStringStringArrayStringString() {
		String[] courses = {"SENG301","CPSC471"};
		Student newStudent = new Student( "StudentA",courses, "TA1");
		assertEquals(3,newStudent.getPermissions());
		assertEquals("StudentA",newStudent.getName());
		assertEquals(courses[0],newStudent.getCourse()[0]);
		assertEquals(courses[1], newStudent.getCourse()[1]);
		assertEquals("TA1",newStudent.getTA());
	}

	@Test
	public void testSetTA() {
		Student newStudent= new Student();
		newStudent.setTA("HELLO");
		assertEquals("HELLO",newStudent.getTA());
		newStudent.setTA("");
		assertEquals("",newStudent.getTA());
	}

	@Test
	public void testGetTA() {
		Student newStudent= new Student();
		assertEquals("",newStudent.getTA());
		String[] courses = {"SENG301","CPSC471"};
		newStudent = new Student( "StudentA",courses, "TA1");
		assertEquals("TA1",newStudent.getTA());
	}

}
