package Management;

import static org.junit.Assert.*;	

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DBMS.UserAssignmentDbms;

public class StudentAccessTest {
	private static StudentAccess studentAccessTest;
	UserAssignmentDbms test = new UserAssignmentDbms("Admin", "SENG301", 1);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	File x = new File("./res/TaList.txt");	
	BufferedWriter out = new BufferedWriter( new FileWriter( x, true /* append */ ) );
	if(x.exists())
		x.delete();
	
		out.write("TA1\tSENG301\tAdmin\n");
		out.close();
	x = new File("./res/InstructorList.txt");	
	out = new BufferedWriter( new FileWriter( x, true /* append */ ) );
	if(x.exists())
		x.delete();
		
		out.write("Admin\tSENG301\n");
		out.close();
	x = new File("./res/Student1List.txt");	
	out = new BufferedWriter( new FileWriter( x, true /* append */ ) );
	
	if(x.exists())
		x.delete();
	
		out.write("Student1\tSENG301\tTA1\n");
		out.close();
	x = new File("./res/UserList.txt");	
	
	out = new BufferedWriter( new FileWriter( x, true /* append */ ) );
	if(x.exists())
		x.delete();
	
		out.write("Student1\tSENG301\tTA1\tSENG301\tAdmin\tSENG301\n");
	out.close();
	
	x = new File("./res/Admin/SENG301/1/AssignmentList.txt");	
	if(x.exists())
		x.delete();
	x = new File("./res/Admin/SENG301/AssignmentList.txt");
	out = new BufferedWriter( new FileWriter( x, true /* append */ ) );
	if(x.exists())
		x.delete();
		out.write("1\ttrue\tTA1\ttrue\ttrue\tThisIsDescription\t" + Calendar.getInstance()+ "\r\n");
	out.close();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStudentAccess() {
		assertNull("Should be empty",studentAccessTest);
		studentAccessTest = new StudentAccess(1, "Student1", false, new Course("SENG301","Admin"),"This is a Test For" +
				"constructor", Calendar.getInstance(), false, false);
		assertNotNull("Not null",studentAccessTest);
	}

	@Test
	public void testDeleteStudentAssignment() {
		/**
		 //TODO
		 */
	}

	@Test
	public void testSubmitAssignment() {
		assertTrue(!studentAccessTest.submitted);
		try {
			studentAccessTest.submitAssignment("./res/TAList.txt");
		} catch (FileNotFoundException e) {
			fail("could not submit file");
		}
			assertTrue(studentAccessTest.submitted);
	}

	@Test
	public void testGetGrade() {
		/**
		 * //TODO
		 * 
		 * 
		File x = new File("./res/Admin/SENG301/1/AssignmentList.txt");
		 
		BufferedWriter out = null;
		try {
			out = new BufferedWriter( new FileWriter( x, true /* append */ /** ) );
		} catch (IOException e1) {
			fail("Failed to open file to write");
		}
		if(x.exists())
			x.delete();
		
		try {
			out.write("Student1" + "\t" + false + "\t" + "A" + "\t" + "This is a TestForGrades" + "\n");
		} catch (IOException e) {
			fail("Could Not Create");
		}
		try {
			out.close();
		} catch (IOException e) {
			fail("Failed to Close");
		}
		studentAccessTest = new StudentAccess(1, "Student1", false, new Course("SENG301","Admin"),"This is a Test", Calendar.getInstance(), false, false);
		
		assertEquals("Should be A","A",studentAccessTest.getGrade());
		*/
	}

	@Test
	public void testGetId() {
		assertEquals("Should be same id as initialized in c" +
				"onstuctor","Student1",studentAccessTest.getId());
	}

	@Test
	public void testSubmitted() {
		File x = new File("./res/Admin/SENG301/1/AssignmentList.txt");
		BufferedWriter out = null;
		try {
			out = new BufferedWriter( new FileWriter( x, true /* append */ ) );
		} catch (IOException e1) {
			fail("Failed to open file to write");
		}
		if(x.exists())
			x.delete();
		
		try {
			out.write("Student1" + "\t" + false + "\t" + "A" + "\t" + "This is a Test" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("Could Not Create");
		}
		try {
			out.close();
		} catch (IOException e) {
			fail("Failed to Close");
		}
		assertTrue(studentAccessTest.submitted);
		assertTrue(!(new StudentAccess(2, "Student1", false, new Course("SENG301","Admin"),"This is a Test" +
				"for Submitted boolean", Calendar.getInstance(), false, false)).submitted);
		
		
	}

}
