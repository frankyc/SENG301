/**
 * TAMenu.java
 * Represents the menu that is shown to a TA user
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;
import Users.*;
import java.io.*;
import Management.*;
import DBMS.AssignmentNotExistException;

public class TAMenu extends TeachingStaffMenu
{
	private TA ta;

	public TAMenu( TA t )
	{
		ta = t;

		menuItems = joinMenuItems( null );

		selectedMenuItem = 0;
		
		try
		{
			courseManager = new CourseManager( t.getName(), t.getPermissions() );
		}
		catch( AssignmentNotExistException e ) {}
	}
}
