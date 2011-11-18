/**
 * StudentDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.Vector;

public class StudentDbms extends UserDbms
{
	/**
	 * Forms a new StudentDbms, prepping it with a path to the DB file and lines read in
	 * Creates a new, blank DB file if it doesn't already exist
	 */
	public StudentDbms()
	{
		pathToDb = System.getProperty( "user.dir" ) + "/res/StudentList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				createDbFile( dbFile );

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating Student database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Appends a new Student to the end of the DB file
	 *
	 * @param id - The ID of the student
	 * @param course - The course that the student is in
	 *
	 * @return - TRUE if student is successfully added; FALSE if the student already exists
	 */
	public void update( String id, String course, String taId )
	{
		try
		{
			// Check if exists and overwrite if so
			for( int i = 0; i < dbLines.length; i++ )
			{
				String[] line = dbLines[i].split( "\t" );

				if( line[0].compareTo( id ) == 0 )
				{
					dbLines[i] = id + "\t" + course + "\t" + taId + "\n";

					writeLinesToFile();

					return;
				}
			}

			// Doesn't exist so append
			BufferedWriter out = new BufferedWriter( new FileWriter( dbFile, true /* append */ ) );

			out.write( id + "\t" + course + "\t" + taId + "\n" );

			out.close();

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Unable to update student - " + id + " - to db - " + dbFile.getAbsolutePath() );
			e.printStackTrace();
			System.exit(1);
		}
	}




	/**
	 * Gets the TA ID for the student in a particular course
	 *
	 * @param id - The student's id
	 * @param course - The course in which to look for the student
	 *
	 * @return - The TA's id or null if the student doesn't exist
	 */
	public String getTaId( String id, String course )
	{
		if( !exists(id) )
			return null;

		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );
			if( line[0].compareTo(id) == 0){
				return line[2];
			}
		}

		return null;
	}




	/**
	 * Gets a list of the ID's of all students in a given course
	 *
	 * @param course - The course in which to look for students
	 *
	 * @return - Array of ID's for those students in course; null if none found
	 */
	public String[] getStudentsInCourse( String course )
	{
		Vector<String> ids = new Vector<String>();

		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[1].compareTo( course ) == 0 )
					ids.addElement( line[0] );
		}

		if( ids.size() == 0 )
			return null;
		else
		{
			String[] idsArr = new String[ ids.size() ];

			return ids.toArray( idsArr );
		}
	}
}
