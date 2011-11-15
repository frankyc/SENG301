/**
 * AssignmentDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.Vector;

public class AssignmentDbms extends BaseDbms
{
	private String course;

	/**
	 * Forms a new Assignment, prepping it with a path to the DB file and lines read in
	 * Creates a new, blank DB file if it doesn't already exist
	 *
	 * @param c - The course to which this assignment dbms belongs
	 */
	public AssignmentDbms( String instructorId, String c )
	{
		course = c;

		pathToDb = System.getProperty( "user.dir" ) + "/res/" + instructorId + "/" + c + "/AssignmentList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				createDbFile( dbFile );

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating Assignment database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Appends a new Assignment to the end of the assignment DB file if it doesn't exist
	 * If assignment exists, it is overwritten
	 *
	 * @param assignNum - The assignment number
	 * @param visible - The visibilty of the assignment to students
	 * @param gradesVisible - The visibility of assignment grades to students
	 * @param commentsVisible - The visibility of comments to students
	 * @param description - The description of the assignment; set to null or "" for blank comment
	 */
	public void update( int assignNum, boolean visible, boolean gradesVisible, boolean commentsVisible, String description )
	{
		if( description == null || description.compareTo( "" ) == 0 )
			description = "-";

		try
		{
			for( int i = 0; i < dbLines.length; i++ )
			{
				String[] line = dbLines[i].split( "\t" );

				if( line[0].compareTo( String.valueOf(assignNum) ) == 0 )
				{
					dbLines[i] = assignNum + "\t" + visible + "\t" + gradesVisible + "\t" + commentsVisible + "\t" + description + "\r\n";
					writeLinesToFile();

					return;
				}
			}

			// Doesn't exist so append
			System.out.println( "Assignment doesn't exist, so appending..." );
			BufferedWriter out = new BufferedWriter( new FileWriter( dbFile, true /* append */ ) );

			out.write( assignNum + "\t" + visible + "\t" + gradesVisible + "\t" + commentsVisible + "\t" + description + "\r\n" );

			out.close();

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Unable to update Assignment - " + assignNum + " - to db - " + dbFile.getAbsolutePath() );
			e.printStackTrace();
			System.exit(1);
		}
	}




	/**
	 * Checks if an assignment exists in the DB
	 *
	 * @param assignNum - The assignment number to check for
	 * 
	 * @return - True if the file exists, false otherwise
	 */
	public boolean exists( int assignNum )
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo( String.valueOf(assignNum) ) == 0 )
				return true;
		}

		return false;
	}



	/**
	 * Gets whether the assignment is visible or not
	 *
	 * @param assignNum - The assignment number
	 *
	 * @return - True if the assignemnt is set to be visible, false if not
	 */
	public boolean assignVisible( int assignNum ) throws AssignmentNotExistException
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo( String.valueOf(assignNum) ) == 0 )
				return Boolean.parseBoolean( line[1] );
		}

		throw new AssignmentNotExistException();
	}



	/**
	 * Gets whether the assignment grades are visible or not
	 *
	 * @param assignNum - The assignment number
	 *
	 * @return - True if the assignemnt grades are set to be visible, false if not
	 */
	public boolean assignGradesVisible( int assignNum ) throws AssignmentNotExistException
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo( String.valueOf(assignNum) ) == 0 )
				return Boolean.parseBoolean( line[2] );
		}

		throw new AssignmentNotExistException();
	}



	/**
	 * Gets whether the assignment comments are visible or not
	 *
	 * @param assignNum - The assignment number
	 *
	 * @return - True if the assignemnt comments are set to be visible, false if not
	 */
	public boolean assignCommentsVisible( int assignNum ) throws AssignmentNotExistException
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo( String.valueOf(assignNum) ) == 0 )
				return Boolean.parseBoolean( line[3] );
		}

		throw new AssignmentNotExistException();
	}



	/**
	 * Gets the assignment description
	 *
	 * @param assignNum - The assignment number
	 *
	 * @return - The description of the assignment
	 */
	public String getDescription( int assignNum ) throws AssignmentNotExistException
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo( String.valueOf(assignNum) ) == 0 )
			{
				if( line[4].compareTo( "-" ) == 0 )
					return "";
				else
					return line[4];
			}
		}

		throw new AssignmentNotExistException();
	}
}
