/**
 * TeachingStaff.java
 * Contains common menu items amongst Teaching Staff users:
 * 	- Instructor
 * 	- TA
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;

import Users.*;
import Management.*;
import DBMS.AssignmentNotExistException;

public abstract class TeachingStaffMenu extends Menu
{
	protected TeacherAssignment assignment;

	protected MenuItem[] commonMenuItems =
	{
		new MenuItem( "Comment on Submitted Assignment", this )
		{
			public void run() { ((TeachingStaffMenu) menu).commentOnSubmission(); }
		},

		new MenuItem("Toggle Comment Visibility", this )
		{
			public void run() { ((TeachingStaffMenu) menu).toggleCommentVisibility(); }
		},

		new MenuItem( "Assign Grade", this )
		{
			public void run() { ((TeachingStaffMenu) menu).assignGrade(); }
		},

		new MenuItem( "Upload Grades", this )
		{
			public void run() { ((TeachingStaffMenu) menu).uploadGrades(); }
		},

		new MenuItem( "Download Grades", this )
		{
			public void run() { ((TeachingStaffMenu) menu).downloadGrades(); }
		}
	};

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

				assignment = getAssignment( curCourse );
			}
			catch( NoCoursesException e )
			{
				reportError( "No courses to perform actions on." );

				return;
			}
			catch( NoAssignmentsException e )
			{
				reportError( "No assignments to work with." );

				selectedMenuItem = 0;
				continue;
			}

			while( selectedMenuItem != QUIT )
			{
				display();

				if( selectedMenuItem == INVALID )
					getInput( true /* Invalid */ );
				else
					getInput();

				processInput();
			}
		}
	}

	void commentOnSubmission()
	{
		outputHeader( "Comment on Submitted Assignment" );

		System.out.println( "Comment on submitted assignment here!" );
	}


	void toggleCommentVisibility()
	{
		if( assignment.getCommentVisabiltiy() == true )
		{
			try
			{
				assignment.setCommentVisability( false );

				reportError( "Comments are not visible." );
			}
			catch( AssignmentNotExistException e ) {}
		}
		else
		{
			try
			{
				assignment.setCommentVisability( true );

				reportError( "Comments are visible." );
			}
			catch( AssignmentNotExistException e ) {}
		}
	}

	void assignGrade()
	{
		outputHeader( "Assign a Grade" );

		System.out.print( "Please enter the ID of the student you would like to assign a grade to: " );
		String sId = getInputCore();

		StudentAssignment studentAssignment = null;

		studentAssignment = assignment.getStudentAssignment( sId );

		if( studentAssignment == null )
		{
			reportError( "This student has not submitted an assignment for grading." );
			return;
		}

		if( studentAssignment.getGrade() != null && studentAssignment.getGrade().compareTo( "" ) != 0 )
		{
			System.out.println( "Current grade: " + studentAssignment.getGrade() );
			System.out.println( "Cannot assign grade since one exists." );

			pressEnter();
			return;
		}

		System.out.print( "Please enter the grade you would like to assign: " );
		String grade = getInputCore();

		studentAssignment.assignGrades( grade );
	}

	void uploadGrades()
	{
		System.out.println( "Upload grades here!" );
	}

	void downloadGrades()
	{
		System.out.println( "Download grades here!" );
	}

	protected MenuItem[] joinMenuItems( MenuItem[] newMenuItems )
	{
		if( newMenuItems == null )
			return commonMenuItems;

		MenuItem[] jointMenuItems = new MenuItem[newMenuItems.length + commonMenuItems.length];

		for( int i = 0; i < jointMenuItems.length; i++ )
		{
			if( i < commonMenuItems.length )
				jointMenuItems[i] = commonMenuItems[i];
			else
				jointMenuItems[i] = newMenuItems[i - commonMenuItems.length];
		}

		return jointMenuItems;
	}


	/**
	 * Gets an assignment choice from the user
	 *
	 * @param course - The course that the user is in
	 *
	 * @return - The assignment chosen or null if they chose to quit the menu
	 */
	protected TeacherAssignment getAssignment( Course c ) throws NoAssignmentsException
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

			return c.getTeacherAssignment( selectedMenuItem + 1 );
		}

		return null;
	}
}
