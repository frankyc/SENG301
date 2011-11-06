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

		String[] newMenuItems = 
		{
			"First Item",
			"Second Item",
			"Third Item"
		};

		menuItems = joinMenuItems( newMenuItems );
	}


	public void processInput()
	{
		System.out.println( "Selected!" );

	}
}
