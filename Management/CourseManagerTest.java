package Management;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import DBMS.AssignmentNotExistException;

public class CourseManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File x = new File("./res/TAList.txt");	
		BufferedWriter out = new BufferedWriter( new FileWriter( x) );
		if(x.exists())
			x.delete();
			out.write("TA1\tSENG301\tAdmin\n");
			out.close();
		x = new File("./res/InstructorList.txt");	
		out = new BufferedWriter( new FileWriter( x ) );
		if(x.exists())
			x.delete();
			
			out.write("Admin\tSENG301\n");
			out.close();
		x = new File("./res/StudentList.txt");	
		out = new BufferedWriter( new FileWriter( x));
		
		if(x.exists())
			x.delete();
		assert(!x.exists());
		
			out.write("Student1\tSENG301\tTA1\n");
			out.close();
		x = new File("./res/UserList.txt");	

		out = new BufferedWriter( new FileWriter( x) );
		if(x.exists())
			x.delete();
		assert(!x.exists());
		
			out.write("Student1\tSENG301\nTA1\tSENG301\nAdmin\tSENG301\n");
			out.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCourseManagerStringInt() {
		CourseManager student = null,tA = null,instructor = null;
		try {
			student = new CourseManager("Student1",3);
		} catch (AssignmentNotExistException e) {
			fail("Could not create Student course manager");
		}
		try {
			tA = new CourseManager("TA1",2);
		} catch (AssignmentNotExistException e) {
			fail("Could not create TA course manager");
		}
		try {
			instructor = new CourseManager("Admin",1);
		} catch (AssignmentNotExistException e) {
			fail("Could not Create instructor Course Manager");
		}
		assertNotNull(student);
		assertNotNull(tA);
		assertNotNull(instructor);
	}

	@Test
	public void testCourseManagerString() {
		/**
		 //TODO
		 * This is part of instructor adding course implementation
		 * */
	}

	@Test
	public void testCreateCourse() {
		/**
		 //TODO - part of instructor class creation
		 * */
	}

	@Test
	public void testListCourse() {
		CourseManager student = null,tA = null,instructor = null;
		try {
			student = new CourseManager("Student1",3);
		} catch (AssignmentNotExistException e) {
			fail("Could not create Student course manager");
		}
		try {
			tA = new CourseManager("TA1",2);
		} catch (AssignmentNotExistException e) {
			fail("Could not create TA course manager");
		}
		try {
			instructor = new CourseManager("Admin",1);
		} catch (AssignmentNotExistException e) {
			fail("Could not Create instructor Course Manager");
		}
		assertNotNull(student);
		assertNotNull(tA);
		assertNotNull(instructor);
		
		String[] studentCourse,instructorCourse,tACourse;
		studentCourse = student.ListCourse();
		instructorCourse = instructor.ListCourse();
		tACourse = tA.ListCourse();
		
		assertEquals(1,studentCourse.length);
		assertEquals(1,instructorCourse.length);
		assertEquals(1,tACourse.length);
		
		assertEquals("SENG301",studentCourse[0]);
		assertEquals("SENG301",instructorCourse[0]);
		assertEquals("SENG301",tACourse[0]);
		
	}

	@Test
	public void testGetCourse() {
		CourseManager student = null,tA = null,instructor = null;
		try {
			student = new CourseManager("Student1",3);
		} catch (AssignmentNotExistException e) {
			fail("Could not create Student course manager");
		}
		try {
			tA = new CourseManager("TA1",2);
		} catch (AssignmentNotExistException e) {
			fail("Could not create TA course manager");
		}
		try {
			instructor = new CourseManager("Admin",1);
		} catch (AssignmentNotExistException e) {
			fail("Could not Create instructor Course Manager");
		}
		assertNotNull(student);
		assertNotNull(tA);
		assertNotNull(instructor);

		assertNotNull(student.getCourse("SENG301"));
		assertNotNull(instructor.getCourse("SENG301"));
		assertNotNull(tA.getCourse("SENG301"));
		
		assertNull(student.getCourse("DOES NOT EXIST"));
		assertNull(instructor.getCourse("DOES NOT EXIST"));
		assertNull(tA.getCourse("DOES NOT EXIST"));
	}

}
