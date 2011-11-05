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
	protected static final String[] commonMenuItems =
	{
		"Common Item 1",
		"Common Item 2"
	};

	protected static String[] joinMenuItems( String[] newMenuItems )
	{
		String[] jointMenuItems = new String[newMenuItems.length + commonMenuItems.length];

		for( int i = 0; i < jointMenuItems.length; i++ )
		{
			if( i < commonMenuItems.length )
				jointMenuItems[i] = commonMenuItems[i];
			else
				jointMenuItems[i] = newMenuItems[i - commonMenuItems.length];
		}

		return jointMenuItems;
	}
}
