/**
 * LoginMenu.java
 * Provides the UI for a user to login to the system
 *
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Menus;

import Users.*;
import Management.LoginManager;
import java.io.*;

public class LoginMenu extends Menu
{
	private String username;
	private String password;
	private LoginManager loginManager;


	public static void main( String[] args )
	{
		LoginMenu lm = new LoginMenu();

		lm.run();
	}


	public LoginMenu()
	{
		loginManager = new LoginManager();

		String[] newMenuItems = {"##### Sub/Feed #####", "Created by: Franky Cheung, Colin Williams"};

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
		Menu.clearScreen();

		outputMenuItems( false );

		Menu.pressEnter();

	}


	public void getInput()
	{
		getInput( false );
	}


	public void getInput( boolean invalid )
	{
		Menu.clearScreen();

		if( invalid )
			System.out.println( "Invalid ID or password.  Please try again.\n");
		else
			System.out.println( "To login you will need to enter your ID and password.\n" );

		getUsername();

		getPassword();
	}


	public void processInput()
	{
		User user = null;

		while( (user = loginManager.ValidateLogin( username, password )) == null )
			getInput( true );


		switch( user.getPermissions() )
		{
			case User.INSTRUCTOR:
				System.out.println( "You're a instructor!" );
				break;

			case User.TA:
				TAMenu taMenu = new TAMenu( (TA)user );

				taMenu.run();

				break;

			case User.STUDENT:
				System.out.println( "You're a student!" );
				break;
			default:
				System.out.println( "Whoops!  You're nothing!  We should get around to fixing that probably.  Just sayin'..." );
		}
	}


	private void getUsername()
	{
		try
		{
			System.out.print( "Please enter your ID: " );

			BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );

			username = in.readLine();
		}
		catch( IOException e )
		{
			System.out.println( "Unable to read in username.  Please restart the program and try again." );
		}
	}


	private void getPassword()
	{
		System.out.print( "Please enter your password: " );

		password = new String( System.console().readPassword() );
	}
}
