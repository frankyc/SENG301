/**
 * FileManager.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package FileManagement;

import java.io.*;
import java.util.Vector;

public class FileManager
{
	String instructorId;
	String basePath;
	DirectoryManager dirManager;

	/**
	 * Runs test cases for the FileManager class on all methods
	 */
	public static void main( String[] args )
	{
		FileManager fm = new FileManager( "Admin" );

		String gradesPath = System.getProperty( "user.dir" ) + "/res/tmp/Grades.txt"; 

		System.out.println( "############\nTesting FileManager\n#################" );

		// Wrting a grade file
		System.out.println( "Writing grade file..." );
		String[] grades = { "10040377\tA", "10040378\tA" };

		try
		{
			fm.writeGradeFile( gradesPath, grades, true );
		}
		catch( FileExistsException e )
		{
			System.out.println( "ERROR: Something has been seriously messed up writing the grade file..." );
			System.exit(1);
		}

		if( (new File( gradesPath )).exists() )
			System.out.println( "Writing to grade file successful." );
		else
		{
			System.out.println( "ERROR: Writing grade file failed." );
			System.exit(1);
		}


		// Reading a grade file
		System.out.println( "Reading grade file..." );

		String[] readGrades = null;

		readGrades = fm.readGradeFile( gradesPath );

		if( readGrades.length != grades.length )
		{
			System.out.println( "ERROR: Grades before I/O not same as after!" );
			System.exit(1);
		}

		for( int i = 0; i < readGrades.length; i++ )
		{
			if( readGrades[i].compareTo( grades[i] ) != 0 )
			{
				System.out.println( "ERROR: ReadGrades != Grades" );
				System.out.println( "ReadGrades line: " + readGrades[i] );
				System.out.println( "grades line: " + grades[i] );
				System.exit(1);
			}
		}

		System.out.println( "Grades I/O successful!\n\n" );


		// Submitting File
		System.out.println( "Submitting File..." );


		try
		{
			fm.submitFile( "CPSC471", 1, false, gradesPath, "10040377" );
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "ERROR: Cannot find grades file to submit!" );
			System.exit(1);
		}

		File submitted = new File( fm.basePath + "CPSC471/1/OnTime/10040377-Grades.txt" );

		if( !submitted.exists() )
		{
			System.out.println( "ERROR: Submitted grades file is incorrectly named or doesn't exist!" );

			System.exit(1);
		}

		System.out.println( "File submission successful!\n\n" );


		// Downloading submitted files
		System.out.println( "Downloading submitted files..." );

		try
		{
			fm.downloadAllSubmittedFiles( "CPSC471", 1, System.getProperty( "user.dir" ) + "/res/tmp/" );
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "Destination for downloaded files not a directory!" );
			System.exit(1);
		}

		File downloaded = new File( System.getProperty( "user.dir" ) + "/res/tmp/OnTime/10040377-Grades.txt" );

		if( !downloaded.exists() )
		{
			System.out.println( "ERROR: Downloaded file doesn't exist!" );
			System.exit(1);
		}


		System.out.println( "################\nTESTING SUCCESSFUL\n###################\n" );
	}




	/**
	 * Creats a new FileManager for a specific instructor
	 *
	 * @param iId - The instructor's ID
	 */
	public FileManager( String iId )
	{
		instructorId = iId;
		dirManager = new DirectoryManager( instructorId );

		basePath = System.getProperty( "user.dir" ) + "/res/" + instructorId + "/";
	}



	/**
	 * Wrapper for "writeGradeFile" with default overwrite value of false
	 */
	public void writeGradeFile( String path, String[] grades ) throws FileExistsException
	{
		writeGradeFile( path, grades, false );
	}



	/**
	 * Writes the grades for an assignment to a file
	 *
	 * @param path - The path for the destination file
	 * @param grades - The grades to be written; should include the associated student ID
	 * 			using the following format:
	 * 			<student ID>\t<grade>
	 * @param overwrite - Determines if the method will throw an exception if
	 * 			the destination file exists or throw an exception.
	 * 			If true, it will overwrite, otherwise will throw exception
	 * 			FileExistsException
	 */
	public void writeGradeFile( String path, String[] grades, boolean overwrite ) throws FileExistsException
	{
		try
		{
			File dest = new File( path );

			if( dest.exists() && !overwrite )
				throw new FileExistsException();

			BufferedWriter out = new BufferedWriter( new FileWriter(path, false) );

			for( int i = 0; i < grades.length; i++ )
				out.write( grades[i] + "\n" );

			out.close();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Unable to write grade file." );
			e.printStackTrace();
			System.exit(1);
		}
	}





	/**
	 * Reades the data from a grade file
	 *
	 * @param path - The path for the grade file
	 * 			(file should follow same formatting as writeGradeFile())
	 */
	public String[] readGradeFile( String path )
	{
		Vector<String> lines = new Vector<String>();

		try
		{
			BufferedReader in = new BufferedReader( new FileReader(path) );

			String line = null;

			while( (line = in.readLine()) != null )
				lines.addElement( line );
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Unable to read grade file." );
			e.printStackTrace();
			System.exit(1);
		}


		String[] grades = new String[ lines.size() ];

		return lines.toArray( grades );
	}





	/**
	 * Downloads all submitted files for an assignment to a directory,
	 * placing the late and on-time submissions in separate subdirectories
	 *
	 * @param course - The course to download submissions for
	 * @param assignNum - The assignment number to download for
	 * @param destPath - Path to the directory to download the files to
	 *
	 * Throws FileNotFoundException in case the detination path isn't a directory
	 */
	public boolean downloadAllSubmittedFiles( String course, int assignNum, String destPath ) throws FileNotFoundException
	{
		if( !(new File(destPath)).isDirectory() )
			throw new FileNotFoundException();

		dirManager.createSubmissionDirs( destPath );

		File destOnTime = new File( destPath + "OnTime/" );
		File destLate = new File( destPath + "Late/" );

		File srcDirOnTime = new File( basePath + course + "/" + assignNum + "/OnTime/" );
		File srcDirLate = new File( basePath + course + "/" + assignNum + "/Late/" );

		File[] fileList = srcDirOnTime.listFiles();

		if( fileList != null )
		{
			for( int i = 0; i < fileList.length; i++ )
				copyFile( fileList[i], new File(destOnTime.getAbsolutePath() + "/" + fileList[i].getName()) );
		}


		fileList = srcDirLate.listFiles();

		if( fileList != null )
		{
			for( int i = 0; i < fileList.length; i++ )
				copyFile( fileList[i], destLate );
		}

		return true;
	}





	/**
	 * Copies a file to the submission location
	 *
	 * @param course - The course for which the submission is taking place
	 * @param assignNum - The assignment number for which the submisstion is taking place
	 * @param late - True if this is a late submission; false otherwise
	 * @param srcPath - The path to the file that is being submitted
	 * @param id - The ID of the student that is submitting the file
	 */
	public void submitFile( String course, int assignNum, boolean late, String srcPath, String id ) throws FileNotFoundException
	{
		File srcFile = new File( srcPath );

		if( !srcFile.exists() || !srcFile.isFile() )
			throw new FileNotFoundException();

		String lateFolder = null;

		if( late )
			lateFolder = "Late";
		else
			lateFolder = "OnTime";

		File destDir = new File( basePath + course + "/" + assignNum + "/" + lateFolder + "/" );

		if( !destDir.exists() )
			destDir.mkdirs();

		copySubmitFile( srcFile, destDir, id );
	}



	/**
	 * Copies a submitted file to the submission directory,
	 * prepending the student's ID to the filename to keep it unique
	 *
	 * @param src - The source file
	 * @param dest - The destination directory
	 * @param id - The ID of the student submitting the file
	 */
	private void copySubmitFile( File src, File dest, String id ) throws FileNotFoundException
	{
		System.out.println( "SRC NAME = " + src.getName() );

		dest = new File( dest.getAbsolutePath() + "/" + id + "-" + src.getName() );

		copyFile( src, dest );

	}




	/**
	 * Copies a file regardless of extension or type to
	 * another directory; keeps the original name
	 *
	 * @param src - The sourse file
	 * @param dest - The destination file
	 */
	private void copyFile( File src, File dest ) throws FileNotFoundException
	{
		try{
			InputStream in = new FileInputStream(src);

			OutputStream out = new FileOutputStream(dest);

			byte[] buf = new byte[1024];
			int len;

			while ( (len = in.read(buf)) > 0)
				out.write(buf, 0, len);

			in.close();
			out.close();
		}
		catch( FileNotFoundException e )
		{
			throw e;
		}
		catch(IOException e)
		{
			System.out.println( "ERROR copying file." );
			e.printStackTrace();
			System.exit(1);
		}
	}
}
