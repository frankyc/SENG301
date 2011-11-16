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

public abstract class TeachingStaffMenu extends Menu
{
	protected TeacherAssignment teachAssign;

	protected static final MenuItem[] commonMenuItems =
	{
		new MenuItem( "Comment on Submitted Assignment" )
		{
			run() { commentOnSubmission(); }
		},

		new MenuItem("Toggle Comment Visibility" )
		{
			run() { toggleCommentVisibility(); }
		},

		new MenuItem( "Assign Grade")
		{
			run() { assignGrade(); }
		},

		new MenuItem( "Upload Grades" )
		{
			run() { uploadGrades(); }
		},

		new MenuItem( "Download Grades" )
		{
			run() { downloadGrades(); }
		}
	};

	private commentOnSubmission()
	{
		outputHead( "Comment on Submitted Assignment" );

		System.out.println( "Comment on submitted assignment here!" );
	}


	private toggleCommentVisiblity()
	{
		if( curCourse.getCommentVisibility() == true )
			curCourse.setCommentVisibility( false );
		else
			curCourse.setCommentVisibility( true );
	}

	private assignGrade()
	{
		System.out.println( "Assign Grade Here!" );
	}

	private uploadGrades()
	{
		System.out.println( "Upload grades here!" );
	}

	private downloadGrades()
	{
		System.out.println( "Download grades here!" );
	}

	protected static MenuItem[] joinMenuItems( MenuItem[] newMenuItems )
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
	protected TeacherAssignment getAssignment( Course c )
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

			//return c.getTeacherAssignment( selectedMenuItem );
			return null;
		}

		return null;
	}
}
