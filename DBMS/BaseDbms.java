/**
 * BaseDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.util.Vector;
import java.io.*;

public abstract class BaseDbms
{
	/**
	 * This method exists purely for testing the database system
	 * Run this main method to perform tests of all dbms files
	 */
	public static void main( String[] args )
	{
		int numLines = 0;
		int i = 0;
		String[] courses = null;

		// Testing for AssignmentDbms
		// ****************************
		System.out.println( "Testing -AssignmentDbms-\n################\n" );


		System.out.println( "Creating DB..." );
		AssignmentDbms ad = new AssignmentDbms( "Admin", "CPSC471" );

		numLines = ad.dbLines.length;
		System.out.println( "Initial number of lines: " + numLines );

		System.out.println( "Adding new Assignment...\n" );

		for( i = 0; ad.exists( i ); i++ ) {}

		ad.update( i, false, false, false, "Assignment " + i + ".  A test." );

		if( ad.dbLines.length > numLines )
			System.out.println( "Assignment " + i + " successfully added.\n" );
		else
		{
			System.out.println( "Number of lines didn't increase after adding assignment!" );
			System.exit(1);
		}

		System.out.println( "Updating -Assignment " + i + "- to new Description" );

		ad.update( i, false, false, false, "Updated!" );


		try
		{
			if( ad.getDescription( i ).compareTo( "Updated!" ) == 0 )
				System.out.println( "Assignment update successful." );
			else
			{
				System.out.println( "Assignment update NOT successful." );
				System.exit(1);
			}
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "ASSIGNMENT DOESN'T EXIST. Update failed." );
			System.exit(1);
		}

		System.out.println( "Testing of -AssignmentDbms- successful\n#########\n\n" );



		// Testing for UserAssignmentDbms
		// *******************************
		System.out.println( "Testing -UserAssignmentDbms-\n################\n" );
		
		System.out.println( "Creating DB..." );
		UserAssignmentDbms ua = new UserAssignmentDbms( "Admin", "CPSC471", 1 );

		numLines = ua.dbLines.length;

		System.out.println( "Adding new UserAssignment...\n" );

		for( i = 0; ua.exists( String.valueOf(i), false ); i++ ) {}

		ua.update( String.valueOf(i), false, "A" );

		if( ad.dbLines.length > numLines )
			System.out.println( "UserAssignment for ID -" + i + "- successfully added.\n" );
		else
		{
			System.out.println( "Number of lines didn't increase after adding UserAssignment!" );
			System.exit(1);
		}

		System.out.println( "Updating -User Assignemnt " + i + "- to new comment." );

		ua.update( String.valueOf(i), false, "A", "Updated!" );

		try
		{
			if( ua.getComments( String.valueOf(i), false ).compareTo( "Updated!" ) == 0 )
				System.out.println( "User Assignment update successful." );
			else
			{
				System.out.println( "User Assignment update NOT successful." );
				System.exit(1);
			}


			System.out.println( "Deleting non-existent assignment..." );

			try
			{
				ua.deleteSubmission( "NOTEXISTS", false );
			}
			catch( AssignmentNotExistException e )
			{
				System.out.println( "Assignment doesn't exist.  Great! :D" );
			}


			try
			{
				ua.deleteSubmission( String.valueOf(i), false );
			}
			catch( AssignmentNotExistException e )
			{
				System.out.println( "Umm...it should still exist here." );
				System.exit(1);
			}

			System.out.println( "Deleting user assignment from DB..." );

			if( ua.exists( String.valueOf(i), false ) )
			{
				System.out.println( "Submission delete failed!" );
				System.exit(1);
			}

			System.out.println( "User assignment delete successful." );

			try
			{
				System.out.println( "Deleting non-existant assignment..." );

				ua.deleteSubmission( String.valueOf(i), false );
			}
			catch( AssignmentNotExistException e )
			{
				System.out.println( "EXCEPTION THROWN: This is correct behaviour.  Cool." );
			}
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "USER ASSIGNMENT DOESN'T EXIST. User Assignment update failed." );
			System.exit(1);
		}


		System.out.println( "Testing of -UserAssignmentDbms- successful\n#########\n\n" );




		// Testing for InstructorDbms
		// ******************************
		System.out.println( "Testing -InstructorDbms-\n################\n" );

		System.out.println( "Creating DB..." );
		InstructorDbms id = new InstructorDbms();

		numLines = id.dbLines.length;

		for( i = 0; id.exists( String.valueOf(i) ); i++ ) {}
		
		System.out.println( "Adding new Instructor..." );
		id.update( String.valueOf(i), "CPSC571" );
		
		if( id.dbLines.length > numLines )
			System.out.println( "Instructor -" + i + "- added successfully." );
		else
		{
			System.out.println( "Instructor -" + i + "- NOT added successfully." );
			System.exit(1);
		}


		System.out.println( "Updating Instructor to course CPSC471" );

		id.update( String.valueOf(i), "CPSC471" );
		
		courses = id.getCourses( String.valueOf(i) );

		if( courses.length == 1 && courses[0].compareTo( "CPSC471" ) == 0 )
			System.out.println( "Instructor update successful." );
		else
		{
			System.out.println( "Instructor update NOT successful." );
			System.out.println( "length = " + courses.length );
			System.out.println( "courses[0] = " + courses[0] );
			System.exit(1);
		}

		System.out.println( "Testing of -InstructorDbms- successful\n#########\n\n" );





		// Testing for StudentDbms
		// ******************************
		System.out.println( "Testing -StudentDbms-\n################\n" );

		System.out.println( "Creating DB..." );
		StudentDbms sd = new StudentDbms();

		numLines = sd.dbLines.length;

		for( i = 0; sd.exists( String.valueOf(i) ); i++ ) {}

		System.out.println( "Adding new Student..." );
		sd.update( String.valueOf(i), "CPSC571", "TA1" );
		
		if( id.dbLines.length > numLines )
			System.out.println( "Student -" + i + "- added successfully." );
		else
		{
			System.out.println( "Student -" + i + "- NOT added successfully." );
			System.exit(1);
		}


		System.out.println( "Updating student to course CPSC471" );

		sd.update( String.valueOf(i), "CPSC471", "TA1" );
		
		courses = sd.getCourses( String.valueOf(i) );

		if( courses.length == 1 && courses[0].compareTo( "CPSC471" ) == 0 )
			System.out.println( "Student update successful." );
		else
		{
			System.out.println( "Student update NOT successful." );
			System.exit(1);
		}


		System.out.println( "Testing of -StudentDbms- successful\n#########\n\n" );





		// Testing for TADbms
		// ******************************
		System.out.println( "Testing -TADbms-\n################\n" );

		System.out.println( "Creating DB..." );
		TADbms td = new TADbms();

		numLines = td.dbLines.length;

		for( i = 0; td.exists( String.valueOf(i) ); i++ ) {}

		System.out.println( "Adding new TA..." );
		td.update( String.valueOf(i), "CPSC571", "Instructor1" );
		
		if( td.dbLines.length > numLines )
			System.out.println( "TA -" + i + "- added successfully." );
		else
		{
			System.out.println( "TA -" + i + "- NOT added successfully." );
			System.exit(1);
		}


		System.out.println( "Updating TA to course CPSC471" );

		td.update( String.valueOf(i), "CPSC471", "Instructor1" );
		
		courses = td.getCourses( String.valueOf(i) );

		if( courses.length == 1 && courses[0].compareTo( "CPSC471" ) == 0 )
			System.out.println( "TA update successful." );
		else
		{
			System.out.println( "TA update NOT successful." );
			System.exit(1);
		}

		System.out.println( "Testing of -TADbms- successful\n#########\n\n" );



		System.out.println( "###################################\nTESTING COMPLETED SUCCESSFULLY\n###############################\n" );
	}

	protected String pathToDb;
	protected File dbFile;
	protected String[] dbLines;

	/**
	 * Reads in the lines of the database file to the dbLines variable
	 * for manipulation
	 */
	protected void readDbFile()
	{
		Vector<String> lines = new Vector<String>();

		try {
			BufferedReader in = new BufferedReader( new FileReader( pathToDb ) );

			String line = null;

			while( (line = in.readLine()) != null )
			{
				if( line.compareTo( "" ) != 0 )
					lines.addElement( line );
			}

			in.close();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Can't read db file." );
			e.printStackTrace();
			System.exit(1);
		}

		//System.out.println( "NumLines = " + lines.size() );
		String[] linesArr = new String[ lines.size() ];

		dbLines = lines.toArray( linesArr );
	}



	/**
	 * Writes the database lines stored in memory to the database file
	 */
	protected void writeLinesToFile()
	{
		try {
			BufferedWriter out = new BufferedWriter( new FileWriter( pathToDb ) );

			for( int i = 0; i < dbLines.length; i++ )
				out.write( dbLines[i] + "\n" );

			out.close();

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Unable to write dbLines to file -" + pathToDb + "-." );
			e.printStackTrace();
			System.exit(1);
		}
	}


	/**
	 * Creates a new database file and the folder structure leading up to it
	 *
	 * @param dbFile - The database file to create
	 */
	protected void createDbFile( File dbFile ) throws IOException
	{
		dbFile.getParentFile().mkdirs();
		dbFile.createNewFile();
	}
}
