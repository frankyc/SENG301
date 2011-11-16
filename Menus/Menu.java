/**
 * Menu.java
 * Represents a generic menu with menu items
 * and basic display and processing capabilities
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;

import java.io.*;
import java.util.Scanner;
import Management.*;

public abstract class Menu
{
	// CONSTANTS
	protected static final int QUIT = -1;
	protected static final int INVALID = -2;


	// VARS
	protected MenuItem[] menuItems;
	int selectedMenuItem;
	CourseManager courseManager;
	Course curCourse;

	 
	// DEFINED METHODS
	public void processInput();
	{
		menuItems[i].run();
	}


	public void run()
	{
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


	protected void display()
	{
		clearScreen();

		outputMenuItems( true /* Number items */ );	
	}


	protected void getInput()
	{
		getInput( menuItems.length, false );
	}

	protected void getInput( boolean invalid )
	{
		getInput( menItems.length, invalid );
	}
	
	protected void getInput( numItems )
	{
		getInput( numItems, false );
	}

	protected void getInput( numItems, boolean invalid )
	{
		if( invalid )
			System.out.println( "Invalid option selected.\n" );

		System.out.println( "Please enter a menu option (q to logout): " );

		BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );

		String input = getInputCore();

		// Check input for errors
		try
		{
			if( input.compareTo( "q" ) == 0 )
			{
				selectedMenuItem = QUIT;
				return;
			}

			// Subtract one to account for numbering starting at '1' on display
			selectedMenuItem = Integer.parseInt( input ) - 1;

			if( selectedMenuItem < 0 || selectedMenuItem >= numItems )
				throw new NumberFormatException();
		}
		catch( NumberFormatException e )
		{
			selectedMenuItem = INVALID;
		}
	}



	/**
	 * Gets a single line from the keyboard
	 *
	 * @return - The input line
	 */
	protected String getInputCore()
	{
		try
		{
			return in.readLine();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Couldn't read input.  Please run the program again." );
			e.printStackTrace();

			System.exit(1);
		}
	}


	protected isQuit( String input )
	{
		if( input.compareTo( "q" ) == 0 )
			return true;
		
		return false;
	}


	public static void clearScreen()
	{
		for( int i = 0; i < 100; i++ )
			System.out.println( "\n" );
	}


	/**
	 * Prompts the user to press enter and waits for them to
	 * Will absorb any input before the enter press
	 */
	public static void pressEnter()
	{
		System.out.println( "<< Press Enter to Continue >>" );

		Scanner sc = new Scanner( System.in );

		sc.nextLine();
	}


	/**
	 * Outputs menu items with or without prepending the menu-item nuber in front
	 *
	 * @param numberItems - If true will prepend numbers in front of each menu item starting with 0.
	 * 			If false will output each menu item on a new line with no modification
	 */
	protected void outputMenuItems( boolean numberItems )
	{
		String[] items = new String[ menuItems.length ];

		for( int i = 0; i < menuItems.length; i++ )
			items[i] = menuItems[i];

		outputMenuItems( items, numberItems );
	}



	protected void outputMenuItems( String[] items, boolean numberItems )
	{
		for( int i = 0; i < items.length; i++ )
		{
			String output = null;

			if( numberItems )
				output = i+1 + ". ";
			
			 output = output + items[i].toString();

			 System.out.println( output );
		}

	}




	



	/**
	 * Gets a course choice from the user
	 *
	 * @param course - The course that the user is in
	 *
	 * @return - The assignment chosen or null if they chose to quit the menu
	 */
	protected Course getCourseChoice()
	{
		outputHeader( "Choose the Course to Work In" );

		String[] courses = courseManager.ListCourse();

		outputMenuItems( courses, true /* numberItems */ );

		selectedMenuItem = 0;

		while( selectedMenuItem != QUIT )
		{
			if( selectedMenuItem != INVALID )
				getInput( courses.length );
			else
				getInput( courses.length, true );

			if( selectedMenuItem == INVALID )
				continue;

			return courseManager.getCourse( courses[i] );
		}

		return null;
	}

	

	protected void outputHeader( String header )
	{
		clearScreen();

		System.out.println( header );
		
		String underline = "";
		for( int i = 0; i < header.length; i++ )
			underline = underline + "-";

		System.our.println( underline );
	}
}
