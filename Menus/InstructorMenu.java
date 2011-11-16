/**
 * InstructorMenu.java
 * Generates and handles the main menu system for
 * the "instructor" user
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;
import Users.*;

public class InstructorMenu extends TeachingStaffMenu
{
	private Instructor instructor;


	public InstructorMenu( Instructor i )
	{
		instructor = i;

		courseManager = new CourseManager( i, i.getPermissions() );

		MenuItem[] newMenuItems = 
		{
			new MenuItem( "Create a New Assignment" )
			{
				run() { createAssignment(); }
			},

			new MenuItem("Release Grades" )
			{
				run() { releaseGrades(); }
			},

			new MenuItem( "Edit Grades")
			{
				run() { releaseGrades(); }
			}
		};

		menuItems = joinMenuItems( newMenuItems );
	}


	private void createAssignment()
	{
		System.out.println( "Creating assignment!" );
		return;

		String name = null;
		String description = null;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;

		Calendar dueDate = null;

		outputHeader( curCourse.getCourseName() + " - Create an Assignment" );

		System.out.print( "Enter the course name: " );
		name = getInputCore();

		if( isQuit(name) )
			return;

		System.out.print( "Enter the course description: " );
		description = getInputCore();

		if( isQuit(description) )
			return;

		System.out.print( "Enter year due: " );
		year = getInputCore();

		if( isQuit(year) )
			return;

		System.out.print( "Enter month due: " );
		month = getInputCore();

		if( isQuit(month) )
			return;

		System.out.print( "Enter day due: " );
		month = getInputCore();

		if( isQuit(day) )
			return;

		System.out.print( "Enter hour due: " );
		hour = getInputCore();

		if( isQuit(hour) )
			return;

		System.out.print( "Enter minute due: " );
		minute = getInputCore();

		if( isQuit(minute) )
			return;

		dueDate = new GregorianCalendar( Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute) );

		curCourse.createAssignment( description, dueDate, false, false );
	}


	public void ReleaseGrades()
	{
		assignment.setGradeVisibility( true );
	}


	public void editGrade()
	{
		outputHeader( "Edit a Grade" );

		String studentId = null;

		System.out.print( "Please enter the ID of the student you would like to change the grade for: " );
		
		studentId = getInputCore();
	}
}
