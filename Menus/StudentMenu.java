/**
 * StudentMenu.java
 * Represents the menu shown to a student user
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;
import java.io.*;
import Users.*;
import Management.*;
import DBMS.AssignmentNotExistException;

public class StudentMenu extends Menu
{
	private Student student;
	private StudentAccess assignment;

	public StudentMenu( Student s )
	{
		student = s;

		menuItems = new MenuItem[]
		{
			new MenuItem( "Submit an Assignment", this )
			{
				public void run() { ((StudentMenu) menu).submitAssignment(); }
			},

			new MenuItem("Delete a Submission", this )
			{
				public void run() { ((StudentMenu) menu).deleteAssignment(); }
			}
		};

		try
		{
			courseManager = new CourseManager( s.getName(), s.getPermissions() );
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "AssignmentNotExistException!!!!!" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	void submitAssignment()
	{
		outputHeader( "Submit an Assignment" );


		try
		{
			if( (curCourse = getCourseChoice()) == null )
				return;
		}
		catch( NoCoursesException e )
		{
			reportError( "No courses to submit for." );
			return;
		}

		try
		{
			if( (assignment = getAssignmentChoice(curCourse)) == null )
				return;
		}
		catch( NoAssignmentsException e )
		{
			reportError( "This course has no assignments to submit to." );
			return;
		}


		boolean validPath = false;

		while( !validPath )
		{
			System.out.print( "Please enter the path of the file you would like to submit: " );
			String path = getInputCore();

			try
			{
				assignment.submitAssignment( path );
				validPath = true;
			}
			catch( FileNotFoundException e )
			{
				System.out.print( "That file doesn't exist.  Please enter another path: " );
			}
		}
	}


	void deleteAssignment()
	{
		outputHeader( "Delete a Submission" );

		try
		{
			if( (curCourse = getCourseChoice()) == null )
				return;
		}
		catch( NoCoursesException e )
		{
			reportError( "No courses to delete for." );
			return;
		}

		try
		{
			if( (assignment = getAssignmentChoice( curCourse )) == null )
			{
				reportError( "Submission not deleted." );
				return;
			}
		}
		catch( NoAssignmentsException e )
		{
			reportError( "No assignments to delete for." );
			return;
		}

		System.out.println( "Are you sure you want to delete this submission? (y/n)" );
		String choice = getInputCore();

		if( choice.compareTo("y") == 0 )
		{
			if( !assignment.deleteStudentAssignment() )
				System.out.println( "Unable to delete submission since it is after the due date." );
			else
				System.out.println( "Submission successfully deleted." );
		}
		else
			System.out.println( "Submission not deleted." );
	}



	/**
	 * Gets an assignment choice from the user
	 *
	 * @param course - The course that the user is in
	 *
	 * @return - The assignment chosen or null if they chose to quit the menu
	 */
	private StudentAccess getAssignmentChoice( Course c ) throws NoAssignmentsException
	{
		outputHeader( c.getCourseName() + " - Choose Assignment" );

		int numAssignments = c.totalNumberOfAssignments();

		if( numAssignments == 0 )
			throw new NoAssignmentsException();

		selectedMenuItem = 0;

		while( selectedMenuItem != QUIT )
		{
			System.out.println( "You have " + numAssignments + " assignments to pick from.\n" );

			if( selectedMenuItem != INVALID )
				getInput( numAssignments );
			else
				getInput( numAssignments, true /* invalid */ );

			if( selectedMenuItem == INVALID )
				continue;

			return (StudentAccess) c.getAssignment( selectedMenuItem + 1 );
		}

		return null;
	}
}
