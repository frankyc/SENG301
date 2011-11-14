/**
 * DirectoryManager.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package FileManagement;

import java.io.*;

public class DirectoryManager
{
	private String instructorId;
	private String basePath;

	/**
	 * Creates a new Directory Manager
	 *
	 * @param iId - The instructor ID that this manager belongs to
	 */
	public DirectoryManager( String iId )
	{
		instructorId = iId;

		basePath = System.getProperty( "user.id" ) + "/res/" + instructorId + "/";

		File file = null;

		if( !(file = new File(basePath)).exists() )
			file.mkdirs();
	}




	/**
	 * Creats an empty course directory
	 *
	 * @param course - The course for which to make a directory
	 */
	public void createCourseDir( String course )
	{
		File file = new File( basePath + course + "/" );

		if( !file.exists() )
			file.mkdirs();
	}




	/**
	 * Creates an assignment directory for a course
	 *
	 * @param course - The course the assignment belongs to
	 * @param assignNum - The assignment number
	 */
	public void createAssignDir( String course, int assignNum )
	{
		File assignDir = new File( basePath + course + "/" + assignNum + "/" );

		if( !assignDir.exists() )
			assignDir.mkdirs();

		createSubmissionDirs( assignDir.getAbsolutePath() + "/" );
	}




	/**
	 * Creates the submission directories for an assignment;
	 * The on time and late directories
	 *
	 * These are for storing the submitted files
	 *
	 * @param base - The path to the directory in which to create
	 * 			the submission directories; should INCLUDE trailing slash
	 */
	void createSubmissionDirs( String base )
	{
		File onTime = new File( base + "OnTime/" );
		File late = new File( base + "Late/" );

		if( !onTime.exists() )
			onTime.mkdirs();

		if( !late.exists() )
			late.mkdirs();
	}
}
