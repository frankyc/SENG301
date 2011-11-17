package Management;

import Users.*;
import FileManagement.*;
import DBMS.*;

import java.util.Calendar;
import java.util.Vector;

public class Course implements DBMSAccessor{
	private String courseName;
	DirectoryManager dM;
	AssignmentDbms aDbms;
	
	private Vector<CourseAssignment> ca;
	private String iId;
	


	/**
	 * Creates a new, empty course
	 */
	public Course(){
		ca = new Vector<CourseAssignment>();
		courseName = "";
	}
	


	/**
	 * Creates a course with a name and instructor ID associated with it
	 */
	public Course( String cName,String instrID )
	{
		iId = instrID;
		courseName = cName;
		//ca = null if course did not exist
		ca = null;
		

		aDbms = new AssignmentDbms( instrID, cName );
		
		ca = new Vector<CourseAssignment>();
	}



	/**
	 * Creates an assignment in this course
	 *
	 * @param cor - The course this assignment belongs to
	 * //TODO Do we need cor since we are in a course already?? **Reply We don't so i used this pointer**
	 * @param desc - The description of the course
	 * @param date - The due date of the course
	 * @param assNum - The assignment number
	 * //TODO Since the assignment numbers are auto-incrementing, does this need to be here? **Removed assignNum, but we need to find a way to retrieve that number**
	 * @param assVis - Whether the assignment is visible to students or not
	 * @param gradeVis - Whether the grades for this assignment are visible to students or not
	 */
	public void createAssignment( String desc, Calendar date, boolean assVis, boolean gradeVis )
	{
		CourseAssignment newAssignment = new CourseAssignment(
				totalNumberOfAssignments() + 1,
				this,
				desc,
				date,
				assVis,gradeVis
				);

		ca.addElement(newAssignment);

		dM = new DirectoryManager (iId);
		dM.createAssignDir( courseName, newAssignment.getAssignmentNumber() );

		aDbms.add( assVis, gradeVis, false, desc, date );
	}
	

	/**
	 * Creates a new student access object for this course
	 *
	 * @param s - The student that this student access will belong to
	 * @param late - Whether the assignment is late or not
	 * @param desc - The description of the assignment
	 * @param date - The due date of the assignment
	 * @param assVis - Whether the assignment is visible or not to the student
	 * @param gradeVis - Whether the grades for this assignemnt are visible or not
	 */
	public void createStudentAccess(
			int assignNum,
			String s,
			boolean late,
			String desc,
			Calendar date,
			boolean assVis,
			boolean gradeVis
			) throws AssignmentNotExistException
	{
		StudentAccess newAssignment = new StudentAccess( assignNum, s, late, this, desc, date, assVis, gradeVis);
		ca.addElement(newAssignment);
	}



	/**
	 * Creates a new Teacher Assignment
	 *
	 * @param desc - The description of the assignment
	 * @param date - The due date of the assignment
	 * @param assVis - Whether the assignment is visible or not
	 * @param gradeVis - Whether the grades for the assignment are visible or not
	 */
	public void createTeacherAssigment(
			int assignNum,
			String desc,
			Calendar date,
			boolean assVis,
			boolean gradeVis
			) throws AssignmentNotExistException
	{
		TeacherAssignment newAssignment = new TeacherAssignment( assignNum, this, desc, date, assVis, gradeVis);
		ca.addElement(newAssignment);
	}


	/**
	 * @return - The name of this course
	 */
	public String getCourseName(){
		return courseName;
	}



	/**
	 * @return - This course's instructor
	 */
	public String getInstructorId(){
		return iId;
	}



	/**
	 * Gets an assignment that belongs to this course
	 *
	 * @param assNum - The assignment number to get
	 * 
	 * @return - The assignment asked for
	 */
	public CourseAssignment getAssignment(int assNum){
		for(int i =0; i< ca.size(); i++){
			if(ca.elementAt(i).getAssignmentNumber() == assNum){
				return ca.elementAt(i);
			}
		}
		return null;
	}



	public TeacherAssignment getTeacherAssignment( int assNum )
	{
		try
		{
		for( int i = 0; i < ca.size(); i++ )
		{
			if( ca.elementAt(i).getAssignmentNumber() == assNum )
				return new TeacherAssignment( ca.elementAt(i) );
		}
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "ERROR: Can't find the assignment.  Exiting." );
			
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}
	
	/**@return total number of assignments listed
	 */
	public int totalNumberOfAssignments(){
		return ca.size();	
	}
}
