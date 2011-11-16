/**
 * StudentMenu.java
 * Represents the menu shown to a student user
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;
import Users.*;

public class StudentMenu extends Menu
{
	private String[] menuItems;
	private Student student;
	private StudentAccess assignment;

	public StudentMenu( Student s )
	{
		student = s;

		menuItems = new String[]
		{
			new MenuItem( "Submit an Assignment" )
			{
				run() { submitAssignment(); }
			},

			new MenuItem("Delete a Submission" )
			{
				run() { deleteAssignment(); }
			}
		};

		courseManager = new CourseManager( s, s.getPermissions() );
	}



	private submitAssignment()
	{
		outputHeader( "Submit an Assignment" );

		System.out.println( "Submit an assignment here!" );
	}


	private deleteAssignment()
	{
		outputHead( "Delete a Submission" );

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

		int numAssignments = c.getNumAssignments();

		selectedMenuItem = 0;

		while( selectedMenuItem != QUIT )
		{
			if( selectedMenuItem != INVALID )
				getInput( courses.length );
			else
				getInput( courses.length, true /* invalid */ );

			if( selectedMenuItem == INVALID )
				continue;

			//return c.getStudentAccess( selectedMenuItem );
			return null;
		}

		return null;
	}
}
