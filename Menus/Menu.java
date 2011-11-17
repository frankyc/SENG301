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

abstract class Menu
{
	// CONSTANTS
	protected static final int QUIT = -3;
	protected static final int INVALID = -2;
	protected static final int UP = -1;


	// VARS
	protected MenuItem[] menuItems;
	protected int selectedMenuItem;
	protected CourseManager courseManager;
	protected Course curCourse;

	 
	// DEFINED METHODS
	public void processInput()
	{
		if( selectedMenuItem >= 0 )
			menuItems[selectedMenuItem].run();
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
		display( menuItems );
	}

	protected void display( MenuItem[] items )
	{
		clearScreen();

		outputMenuItems( items, true /* Number items */ );	
	}


	protected void getInput()
	{
		getInput( menuItems.length, false );
	}

	protected void getInput( boolean invalid )
	{
		getInput( menuItems.length, invalid );
	}
	
	protected void getInput( int numItems )
	{
		getInput( numItems, false );
	}

	protected void getInput( int numItems, boolean invalid )
	{
		if( invalid )
			System.out.println( "Invalid option selected.\n" );

		System.out.println( "Please enter a menu option (q to logout): " );

		String input = getInputCore();

		// Check input for errors
		try
		{
			if( isQuit(input) )
			{
				selectedMenuItem = QUIT;
				return;
			}
			else if( input.compareTo( "0" ) == 0 )
			{
				selectedMenuItem = UP;
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
		BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );

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

		return null;
	}


	protected boolean isQuit( String input )
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
	protected void outputMenuItems( MenuItem[] items, boolean numberItems )
	{
		String[] sItems = new String[ items.length ];

		for( int i = 0; i < items.length; i++ )
			sItems[i] = items[i].toString();

		outputMenuItems( sItems, numberItems );
	}



	protected void outputMenuItems( String[] items, boolean numberItems )
	{
		for( int i = 0; i < items.length; i++ )
		{
			String output = "";

			if( numberItems )
				output = i+1 + ". ";
			
			 output = output + items[i].toString() ;

			 System.out.println( output );
		}

		System.out.println( "" );

		if( numberItems )
			System.out.println( "0. Up one level.\n" );

	}




	



	/**
	 * Gets a course choice from the user
	 *
	 * @param course - The course that the user is in
	 *
	 * @return - The assignment chosen or null if they chose to quit the menu
	 */
	protected Course getCourseChoice() throws NoCoursesException
	{
		outputHeader( "Choose the Course to Work In" );

		String[] courses = courseManager.ListCourse();

		if( courses == null || courses.length == 0 )
			throw new NoCoursesException();

		outputMenuItems( courses, true /* numberItems */ );

		selectedMenuItem = 0;

		while( selectedMenuItem != QUIT )
		{
			if( selectedMenuItem != INVALID )
				getInput( courses.length );
			else
				getInput( courses.length, true );

			if( selectedMenuItem == QUIT || selectedMenuItem == INVALID )
				continue;

			return courseManager.getCourse( courses[selectedMenuItem] );
		}

		return null;
	}

	

	protected void outputHeader( String header )
	{
		clearScreen();

		System.out.println( header );
		
		String underline = "";
		for( int i = 0; i < header.length(); i++ )
			underline = underline + "-";

		System.out.println( underline );
	}



	protected void reportError( String message )
	{
		System.out.println( message );
		pressEnter();
	}
}
