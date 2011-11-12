/**
 * UserDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.Vector;

public class UserDbms extends BaseDbms
{
	/**
	 * Forms a new UserDbms, prepping it with a path to the DB file and lines read in
	 * Creates a new, blank DB file if it doesn't already exist
	 */
	public UserDbms()
	{
		pathToDb = System.getProperty( "user.dir" ) + "/res/UserList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				createDbFile( dbFile );
			
			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating User database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Checks if an user exists at all in the DB file
	 *
	 * @param id - The ID of the user to check for
	 * 
	 * @return - True if the user exists at least once; false otherwise
	 */
	public boolean exists( String id )
	{
		/*if( dbLines == null )
			System.out.println( "No dbLines!!" );
		else if( dbLines.length > 0 )
			System.out.println( "dbLines I guess? lines = " + dbLines.length );
		else
			System.out.println( "No dbLines based on length!!" );*/

		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] lineParts = dbLines[i].split( "\t" );

			if( lineParts[0].compareTo( id ) == 0 )
				return true;
		}

		return false;
	}



	/**
	 * Checks if a user is registered for a course
	 *
	 * @param id - The ID of the user
	 *
	 * @param course - The course to check for
	 *
	 * @return - True if the user is registered with that course; false otherwise
	 */
	public boolean exists( String id, String course )
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] lineParts = dbLines[i].split( "\t" );

			if( lineParts[0].compareTo( id ) == 0 &&
					lineParts[1].compareTo( course ) == 0 )
			{
				return true;
			}
		}

		return false;
	}



	/**
	 * Gets the list of courses a user is registered for
	 *
	 * @param id - The ID of the user
	 *
	 * @return - The list of courses
	 */
	public String[] getCourses( String id )
	{
		Vector<String> courses = new Vector<String>();

		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] lineParts = dbLines[i].split( "\t" );

			if( lineParts[0].compareTo( id ) == 0 )
				courses.addElement( lineParts[1] );
		}

		String[] coursesArr = new String[ courses.size() ];

		return courses.toArray( coursesArr );
	}
}
