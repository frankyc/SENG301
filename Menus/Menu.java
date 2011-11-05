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

public abstract class Menu
{
	// CONSTANTS
	protected static final int QUIT = -1;
	protected static final int INVALID = -2;


	// VARS
	protected String[] menuItems;
	int selectedMenuItem;


	// ABSTRACT METHODS
	public abstract void processInput();
	

	// DEFINED METHODS
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


	protected void getInput() { getInput( false ); }


	protected void getInput( boolean invalid )
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
				selectedMenuItem = QUIT;
				return;
			}

			selectedMenuItem = Integer.parseInt( input );

			if( selectedMenuItem < 0 || selectedMenuItem >= menuItems.length )
				throw new NumberFormatException();
		}
		catch( NumberFormatException e )
		{
			selectedMenuItem = INVALID;
		}
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
		for( int i = 0; i < this.menuItems.length; i++ )
		{
			if( numberItems )
				System.out.println( i + ". " + menuItems[i] );
			else
				System.out.println( menuItems[i] );
		}
	}
}
