/**
 * BaseDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;

public class InstructorDbms extends BaseDbms
{
	public InstructorDbms()
	{
		pathToDb = System.getProperty( "user.dir" ) + "/res/InstructorList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				dbFile.createNewFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating Instructor database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}

	public bool InstructorExists( String id )
	{
		String[] dbLines = readDbFile();

		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] lineParts = dbLines.split( "\t" );

			if( lineParts[i].compare( id ) == 0 )
				return true;
		}

		return false;
	}
}
