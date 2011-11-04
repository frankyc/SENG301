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

public class TAMenu extends Menu implements TeachingStaff
{
	private TA ta;
	int selectedMenuItem;

	public TAMenu( TA t )
	{
		ta = t;

		String[] newMenuItems = {"First Item", "Second Item", "Third Item"};

		menuItems = newMenuItems;
	}

	public void run()
	{
		display();

		getInput();

		processInput();
	}


	public void display()
	{
		clearScreen();

		outputMenuItems( true );	
	}


	public void getInput()
	{
		getInput( false );
	}


	private void getInput( boolean invalid )
	{
		if( invalid )
			System.out.println( "Invalid option selected." );

		System.out.println( "Please enter a menu option (q to logout): " );

		BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );

		String input = null;

		// Get the raw input
		try
		{
			input = in.readLine();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Couldn't read menu item input.  Please run the program again." );
			System.exit(1);
		}

		// Check input for errors
		try
		{
			if( input.compareTo( "q" ) == 0 )
			{
				selectedMenuItem = -1;
				return;
			}

			selectedMenuItem = Integer.parseInt( input );

			if( selectedMenuItem < 0 || selectedMenuItem >= menuItems.length )
				throw new NumberFormatException();
		}
		catch( NumberFormatException e )
		{
			getInput( true );
		}
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
