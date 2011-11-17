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
			}
			catch( AssignmentNotExistException e ) {}
		}
		else
		{
			try
			{
				assignment.setCommentVisability( true );
			}
			catch( AssignmentNotExistException e ) {}
		}
	}

	void assignGrade()
	{
		System.out.println( "Assign Grade Here!" );
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
			if( selectedMenuItem != INVALID )
				getInput( numAssignments );
			else
				getInput( numAssignments, true /* invalid */ );
			
			if( selectedMenuItem == INVALID )
				continue;

			return (TeacherAssignment) c.getAssignment( selectedMenuItem + 1 );
		}

		return null;
	}
}
