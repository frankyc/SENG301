/**
 * TADbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.Vector;

public class TADbms extends UserDbms
{
	/**
	 * Forms a new TADbms, prepping it with a path to the DB file and lines read in
	 * Creates a new, blank DB file if it doesn't already exist
	 */
	public TADbms()
	{
		pathToDb = System.getProperty( "user.dir" ) + "/res/TAList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				createDbFile( dbFile );

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating TA database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Appends a new Student to the end of the DB file
	 *
	 * @param id - The ID of the TA
	 * @param course - The course that the TA is in
	 *
	 * @return - TRUE if TA is successfully added; FALSE if the student already exists
	 */
	public void update( String id, String course, String instructorId )
	{
		try
		{
			// Check if exists and update line if so
			for( int i = 0; i < dbLines.length; i++ )
			{
				String[] line = dbLines[i].split( "\t" );

				if( line[0].compareTo( id ) == 0 )
				{
					dbLines[i] = id + "\t" + course + "\t" + instructorId + "\n";

					writeLinesToFile();

					return;
				}
			}

			// Ddoesn't exist so append to DB
			BufferedWriter out = new BufferedWriter( new FileWriter( dbFile, true /* append */ ) );

			out.write( id + "\t" + course + "\t" + instructorId + "\n" );

			out.close();

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Unable to update instructor - " + id + " - to db - " + dbFile.getAbsolutePath() );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Gets the Instructor ID for the TA in a particular course
	 *
	 * @param id - The TA's id
	 * @param course - The course in which to look for the TA
	 *
	 * @return - The Instructor's id or null if the TA doesn't exist
	 */
	public String getInstructorId( String id, String course )
	{
		if( !exists(id) )
			return null;

		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0] == id )
				return line[2];
		}

		return null;
	}
}
