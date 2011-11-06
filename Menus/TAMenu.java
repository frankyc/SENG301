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

public class TAMenu extends TeachingStaffMenu
{
	private TA ta;

	public TAMenu( TA t )
	{
		ta = t;

		String[] newMenuItems =
		{
			"First Item",
			"Second Item",
			"Third Item"
		};

		menuItems = joinMenuItems( newMenuItems );

		selectedMenuItem = 0;
	}


	public void processInput()
	{
		switch( selectedMenuItem )
		{
			case 0:
				System.out.println( "First option!" );
				break;
			
			case 1:
				System.out.println( "Second option!" );
				break;

			case 2:
				System.out.println( "Third option!" );
		}
	}
}
