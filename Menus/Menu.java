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
	protected String[] menuItems;


	public abstract void run();


	public abstract void display();


	public abstract void getInput();


	public abstract void processInput();


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
