package Management;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DBMS.AssignmentNotExistException;

public class CourseAssignmentTest {
	Course SENG301 = new Course("SENG301","Admin");
	CourseAssignment courseAssignmentTest;
	Calendar dateTest = Calendar.getInstance();;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
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
	public void testCourseAssignmentIntCourseStringCalendarBooleanBoolean() {
		assertNull(courseAssignmentTest);
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertNotNull(courseAssignmentTest);
	}

	@Test
	public void testGetDueDate() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(dateTest.getTimeInMillis(),courseAssignmentTest.getDueDate().getTimeInMillis());
	}

	@Test
	public void testGetCourse() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertTrue(courseAssignmentTest.getCourse().equals(SENG301));
	}

	@Test
	public void testIsVisible() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.isVisible(),false);
		
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,true);
		assertEquals(courseAssignmentTest.gradesVisible(),true);
	}

	@Test
	public void testGradesVisible() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.gradesVisible(),false);
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,true);
		assertEquals(courseAssignmentTest.gradesVisible(),true);
	}

	@Test
	public void testSetGradeVisability() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.gradesVisable,false);
		try {
			courseAssignmentTest.setGradeVisability(true);
		} catch (AssignmentNotExistException e) {
			fail("Could not set Grade Visiablilty true");
		}
		assertEquals(courseAssignmentTest.gradesVisible(),true);
		try {
			courseAssignmentTest.setGradeVisability(false);
		} catch (AssignmentNotExistException e) {
			fail("Could not set Grade Visiablilty false");
		}
		assertEquals(courseAssignmentTest.gradesVisible(),false);
	}

	@Test
	public void testSetCommentVisability() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.commentVisable,false);
		try {
			courseAssignmentTest.setCommentVisability(true);
		} catch (AssignmentNotExistException e) {
			fail("Could not set comment Visiablilty true");
		}
		assertEquals(courseAssignmentTest.commentVisable,true);
		try {
			courseAssignmentTest.setCommentVisability(false);
		} catch (AssignmentNotExistException e) {
			fail("Could not set comment Visiablilty false");
		}
		assertEquals(courseAssignmentTest.commentVisable,false);
	}

	@Test
	public void testGetCommentVisabiltiy() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		try {
			courseAssignmentTest.setCommentVisability(true);
		} catch (AssignmentNotExistException e) {
			fail("Could not set comment visiablity to true");
		}
		assertEquals(true,courseAssignmentTest.getCommentVisabiltiy());
		
	}

	@Test
	public void testSetAssignmentVisable() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.assignmentVisable,false);
		try {
			courseAssignmentTest.setAssignmentVisable(true);
		} catch (AssignmentNotExistException e) {
			fail("Could not set assignment Visiablilty true");
		}
		assertEquals(courseAssignmentTest.assignmentVisable,true);
		try {
			courseAssignmentTest.setAssignmentVisable(false);
		} catch (AssignmentNotExistException e) {
			fail("Could not set assignment Visiablilty false");
		}
		assertEquals(courseAssignmentTest.assignmentVisable,false);
	}

	@Test
	public void testSetDueDate() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(dateTest.getTimeInMillis(),courseAssignmentTest.dueDate.getTimeInMillis());
		Calendar newDue = Calendar.getInstance();
		try {
			courseAssignmentTest.setDueDate(newDue);
		} catch (AssignmentNotExistException e) {
			fail("Could not set new dueDate");
		}
		assertEquals(newDue.getTimeInMillis(),courseAssignmentTest.dueDate.getTimeInMillis());
		try {
			newDue = Calendar.getInstance();
			newDue.set(1999, 12, 14);
			courseAssignmentTest.setDueDate(newDue);
		} catch (AssignmentNotExistException e) {
			fail("Could not set comment Visiablilty false");
		}
		assertEquals(newDue.getTimeInMillis(),courseAssignmentTest.dueDate.getTimeInMillis());
	}

	@Test
	public void testUpdateDescription() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.description,"This Course is alot of work");
		try {
			courseAssignmentTest.updateDescription("This is SENG301");
		} catch (AssignmentNotExistException e) {
			fail("Could not change assignment Description");
		}
		assertEquals(courseAssignmentTest.description,"This is SENG301");
		try {
			courseAssignmentTest.updateDescription("This is Assignment 5 of SENG301");
		} catch (AssignmentNotExistException e) {
			fail("This Course is alot of work");
		}
		assertEquals(courseAssignmentTest.description,"This is Assignment 5 of SENG301");
	
	}

	@Test
	public void testGetCourseName() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals("SENG301",courseAssignmentTest.getCourseName());
		courseAssignmentTest = new CourseAssignment(1,new Course("CPSC471","Admin"),"This Course is alot of work",dateTest,false,false);
		assertEquals("CPSC471",courseAssignmentTest.getCourseName());
	}

	@Test
	public void testGetAssignmentNumber() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.getAssignmentNumber(),1);
		courseAssignmentTest = new CourseAssignment(2,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals(courseAssignmentTest.getAssignmentNumber(),2);
	}

	@Test
	public void testGetInstructorId() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals("Admin",courseAssignmentTest.getInstructorId());
		courseAssignmentTest = new CourseAssignment(1,new Course("SENG301","NewAdmin"),"This Course is alot of work",dateTest,false,false);
		assertEquals("NewAdmin",courseAssignmentTest.getInstructorId());
	}

	@Test
	public void testGetDescription() {
		courseAssignmentTest = new CourseAssignment(1,SENG301,"This Course is alot of work",dateTest,false,false);
		assertEquals("This Course is alot of work",courseAssignmentTest.getDescription());
		courseAssignmentTest.description = "more work";
		assertEquals("more work", courseAssignmentTest.getDescription());
	}

}
