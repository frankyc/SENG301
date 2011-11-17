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
import Management.*;
import java.util.*;
import DBMS.AssignmentNotExistException;

public class InstructorMenu extends TeachingStaffMenu
{
	private Instructor instructor;
	private MenuItem[] courseItems;


	public InstructorMenu( Instructor i )
	{
		instructor = i;

		try
		{
			courseManager = new CourseManager( i.getName(), i.getPermissions() );
		}
		catch( AssignmentNotExistException e ) {}

		courseItems = new MenuItem[]
		{
			new MenuItem( "Create a New Assignment", this )
			{
				public void run() { ((InstructorMenu) menu).createAssignment(); }
			},

			new MenuItem( "Work with an Existing Assignment", this )
			{
				public void run() { ((InstructorMenu) menu).existingAssignment(); }
			}
		};


		MenuItem[] newMenuItems = new MenuItem[]
		{
			new MenuItem("Release Grades", this )
			{
				public void run() { ((InstructorMenu) menu).releaseGrades(); }
			},

			new MenuItem( "Edit Grades", this )
			{
				public void run() { ((InstructorMenu) menu).releaseGrades(); }
			}
		};

		menuItems = joinMenuItems( newMenuItems );
	}


	public void run()
	{
		selectedMenuItem = 0;

		while( selectedMenuItem != QUIT )
		{
			if( selectedMenuItem == UP )
			{
				selectedMenuItem = 0;
				return;
			}

			try
			{
				curCourse = getCourseChoice();
			}
			catch( NoCoursesException e )
			{
				reportError( "No courses to perform actions on." );

				return;
			}


			if( selectedMenuItem == QUIT )
				return;
			else if( selectedMenuItem == UP )
			{
				selectedMenuItem = 0;
				return;
			}


			outputHeader( curCourse.getCourseName() );

			outputMenuItems( courseItems, true /* numberItems */ );

			getInput( courseItems.length );

			if( selectedMenuItem >= 0 )
				courseItems[selectedMenuItem].run();
		}
	}



	void existingAssignment()
	{
		try
		{
			assignment = getAssignment( curCourse );
		}
		catch( NoAssignmentsException e )
		{
			reportError( "No assignments to work with in this course." );
			return;
		}

		selectedMenuItem = 0;
		while( selectedMenuItem != QUIT )
		{
			if( selectedMenuItem == UP )
			{
				selectedMenuItem = 0;
				return;
			}
			
			display();

			if( selectedMenuItem == INVALID )
				getInput( true /* invalid */ );
			else
				getInput();
			
			processInput();
		}
	}
		


	
	void createAssignment()
	{
		Calendar dueDate = null;

		outputHeader( curCourse.getCourseName() + " - Create an Assignment" );

		System.out.println( "Enter 'q' at any time to stop creating the assignment.\n" );

		System.out.println( "Creating assignment number: " + (curCourse.totalNumberOfAssignments() + 1) + "\n" );

		System.out.print( "Enter the assignment description: " );
		String description = getInputCore();

		if( isQuit(description) )
			return;

		System.out.print( "Enter year due: " );
		String year = getInputCore();

		if( isQuit(year) )
			return;

		System.out.print( "Enter month due: " );
		String month = getInputCore();

		if( isQuit(month) )
			return;

		System.out.print( "Enter day due: " );
		String day = getInputCore();

		if( isQuit(day) )
			return;

		System.out.print( "Enter hour due: " );
		String hour = getInputCore();

		if( isQuit(hour) )
			return;

		System.out.print( "Enter minute due: " );
		String minute = getInputCore();

		if( isQuit(minute) )
			return;

		dueDate = new GregorianCalendar( Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute) );

		curCourse.createAssignment( description, dueDate, false, false );

		System.out.println( "Assignment " + curCourse.totalNumberOfAssignments() + " created!\n" );
		pressEnter();
	}


	void releaseGrades()
	{
		try
		{
			assignment.setGradeVisability( true );
		}
		catch( AssignmentNotExistException e )
		{}
	}


	void editGrade()
	{
		outputHeader( "Edit a Grade" );

		String studentId = null;

		System.out.print( "Please enter the ID of the student you would like to change the grade for: " );
		
		studentId = getInputCore();
	}
}
