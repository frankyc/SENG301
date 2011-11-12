/**
 * InstructorDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.Vector;

public class InstructorDbms extends UserDbms
{
	/**
	 * Forms a new InstructorDbms, prepping it with a path to the DB file and lines read in
	 * Creates a new, blank DB file if it doesn't already exist
	 */
	public InstructorDbms()
	{
		pathToDb = System.getProperty( "user.dir" ) + "/res/InstructorList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				createDbFile( dbFile );

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating Instructor database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Appends a new instructor/course combination to the end of the instructor DB file
	 *
	 * @param id - The ID of the instructor
	 * @param course - The course that the instructor is teaching
	 *
	 * @return - TRUE if instructor is successfully added; FALSE if the instructor already exists
	 */
	public void update( String id, String course )
	{
		try
		{
			for( int i = 0; i < dbLines.length; i++ )
			{
				String[] line = dbLines[i].split( "\t" );

				if( line[0].compareTo( id ) == 0 )
				{
					dbLines[i] = id + "\t" + course + "\n";

					writeLinesToFile();

					return;
				}
			}

			// Doesn't exist so append
			BufferedWriter out = new BufferedWriter( new FileWriter( dbFile, true /* append */ ) );

			out.write( id + "\t" + course + "\n" );

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
}
