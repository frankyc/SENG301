/**
 * UserAssignmentDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.Vector;

public class UserAssignmentDbms extends BaseDbms
{
	private String course;
	private int assignNum;

	/**
	 * Forms a new Assignment, prepping it with a path to the DB file and lines read in
	 * Creates a new, blank DB file if it doesn't already exist
	 *
	 * @param c - The course to which this assignment dbms belongs
	 * @param assignNum - The assignment number that this assignment is associated with
	 */
	public UserAssignmentDbms( String instructorId, String c, int a )
	{
		course = c;
		assignNum = a;

		pathToDb = System.getProperty( "user.dir" ) + "/res/" + instructorId + "/" + c + "/" + a + "/AssignmentList.txt";

		dbFile = new File( pathToDb );

		try
		{
			if( !dbFile.exists() )
				createDbFile( dbFile );

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: IO Error creating UserAssignment database file.\n" );
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * Creates a new UserAssignemnt with default empty comment and grade values
	 *
	 * @param id - The id of the student that this submission belongs
	 * @param late - Whether the assignment hand-in is past the due date
	 */
	public void add( String id, boolean late )
	{
		update( id, late, "", "" );
	}



	/**
	 * Appends a new User Submitted Assignment to the end of the assignment DB file if it doesn't exist
	 * If assignment exists, it is overwritten
	 *
	 * @param id - The id of the student that this submission belongs
	 * @param late - Whether the assignment hand-in is past the due date
	 * @param grade - The grade for the assignment
	 * @param comments - The comments for this submission
	 */
	public void update( String id, boolean late, String grade, String comments )
	{
		if( grade == null || grade.compareTo( "" ) == 0 )
			grade = "-";

		if( comments == null || comments.compareTo( "" ) == 0 )
			comments = "-";

		try
		{
			for( int i = 0; i < dbLines.length; i++ )
			{
				String[] line = dbLines[i].split( "\t" );

				if( line[0].compareTo( id ) == 0 )
				{
					dbLines[i] = id + "\t" + late + "\t" + grade + "\t" + comments + "\n";

					writeLinesToFile();

					return;
				}
			}

			// Doesn't exist so append
			BufferedWriter out = new BufferedWriter( new FileWriter( dbFile, true /* append */ ) );

			out.write( id + "\t" + late + "\t" + grade + "\t" + comments + "\n" );

			out.close();

			readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Unable to update UserAssignment for id - " + id + " - in db - " + dbFile.getAbsolutePath() );
			e.printStackTrace();
			System.exit(1);
		}
	}




	/**
	 * Checks if a UserAssignment exists in the DB
	 *
	 * @param id - The id of the student to check for
	 * @param late - If true, looks for a late submission for student
	 * 			if fales looks for on-time submission
	 * 
	 * @return - True if the file exists, false otherwise
	 */
	public boolean exists( String id, boolean late )
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo(id) == 0 && line[1].compareTo(String.valueOf(late)) == 0 )
				return true;
		}

		return false;
	}



	/**
	 * Gets whether the assignment is visible or not
	 *
	 * @param id -  The id of the student to look for
	 * @param late - Whether to look for a late assignment or not
	 *
	 * @return - The grade if the submission exists
	 * 		or null if the assignment doesn't exist
	 */
	public String getGrade( String id, boolean late )
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo(id) == 0 && line[1].compareTo(String.valueOf(late)) == 0 )
			{
				if( line[2].compareTo( "-" ) == 0 )
					return "";
				else
					return line[2];
			}
		}

		return null;
	}



	/**
	 * Gets whether the assignment grades are visible or not
	 *
	 * @param id - The id of the student to look for a submission
	 * @param late - Whether to look for a late assignment or not
	 *
	 * @return - The comments for the assignment submission
	 * 		or null if the assignment doesn't exist
	 */
	public String getComments( String id, boolean late ) throws AssignmentNotExistException
	{
		for( int i = 0; i < dbLines.length; i++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo(id) == 0 && line[1].compareTo(String.valueOf(late)) == 0 )
			{
				if( line[3].compareTo( "-" ) == 0 )
					return "";
				else
					return line[3];
			}
		}

		throw new AssignmentNotExistException();
	}



	/**
	 * Removes an assignment submission from the DBMS
	 * @param id - The ID of the student to remove the submission for
	 * @param late - Whether to look for a late assignment or not
	 *
	 */
	public void deleteSubmission( String id, boolean late ) throws AssignmentNotExistException
	{
		if( !exists( id, late ) )
			throw new AssignmentNotExistException();

		String[] newDbLines = new String[dbLines.length-1];

		int i = 0;
		int j = 0;

		for( ; i < dbLines.length; i++, j++ )
		{
			String[] line = dbLines[i].split( "\t" );

			if( line[0].compareTo( id ) != 0 )
				newDbLines[j] = dbLines[i];
			else if( line[1].compareTo( String.valueOf(late) ) == 0 )
				j--;
		}

		if( i == j )
			throw new AssignmentNotExistException();

		dbLines = newDbLines;

		writeLinesToFile();
	}



	/**
	 * Updates the comments on a submitted assignment
	 *
	 * @param id - Id of the student for which to update the submission
	 * @param late - Whether to update a late assignment or not
	 * @param comments - The new comments
	 */
	public void updateComments( String id, boolean late, String comments ) throws AssignmentNotExistException
	{
		update( id, late, getGrade( id, late ), comments );
	}



	/**
	 * Updates the grade on a submitted assignment
	 *
	 * @param id - Id of the student for which to update the submission
	 * @param late - Whether to update a late assignment or not
	 * @param grade - The new grade
	 */
	public void updateGrade( String id, boolean late, String grade ) throws AssignmentNotExistException
	{
		update( id, late, grade, getComments( id, late ) );
	}
}
