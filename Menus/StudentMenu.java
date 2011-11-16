/**
 * StudentMenu.java
 * Represents the menu shown to a student user
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;
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

			new MenuItem("Delete a Submission" )
			{
				public void run() { ((StudentMenu) menu).deleteAssignment(); }
			}
		};

		try
		{
			courseManager = new CourseManager( s.getName(), s.getPermissions() );
		}
		catch( AssignmentNotExistException e ) {}
	}



	void submitAssignment()
	{
		outputHeader( "Submit an Assignment" );

		System.out.println( "Submit an assignment here!" );
	}


	void deleteAssignment()
	{
		outputHeader( "Delete a Submission" );

		System.out.println( "Delete an assignment here!" );
	}



	/**
	 * Gets an assignment choice from the user
	 *
	 * @param course - The course that the user is in
	 *
	 * @return - The assignment chosen or null if they chose to quit the menu
	 */
	private StudentAccess getAssignmentChoice( Course c )
	{
		outputHeader( c.getCourseName() + " - Choose Assignment" );

		int numAssignments = c.totalNumberOfAssignments();

		selectedMenuItem = 0;

		while( selectedMenuItem != QUIT )
		{
			if( selectedMenuItem != INVALID )
				getInput( numAssignments );
			else
				getInput( numAssignments, true /* invalid */ );

			if( selectedMenuItem == INVALID )
				continue;

			//return c.getStudentAccess( selectedMenuItem );
			return null;
		}

		return null;
	}
}
