/**
 * UserAssignmentDbmsTest.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

package DBMS;

import java.io.*;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class UserAssignmentDbmsTest
{
	private UserAssignment d;
	private Calendar dueDate;
	private static String dbPath = System.getProperty( "user.dir" ) + "/res/Test/SENG301/AssignmentList.txt";


	public static void main( String[] args )
	{
		org.junit.runner.JUnitCore.main( "DBMS.UserAssignmentDbmsTest" );
	}

	static void delete(File f) 
	{
		if (f.isDirectory())
		{
			for (File c : f.listFiles())
				delete(c);
		}

		if (!f.delete())
		{
			System.out.println( "Failed to delete file: " + f );
			System.exit(1);
		}
	}


	void outputAssignments() 
	{
		try
		{
			BufferedWriter out = new BufferedWriter( new FileWriter( dbPath ) );

			for( int i = 0; i < 5; i++ )
				out.write( i + "\tfalse\tA\tComments\r\n" );

			out.close();

			d.readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Error outputting UserAssignments." );
			e.printStackTrace();
			System.exit(1);
		}
	}



	@BeforeClass
		public static void setup()
		{
			File file = new File( dbPath );

			if( file.exists() )
				delete( file );
		}

	@Before
		public void initialize()
		{
			d = new UserAssignmentDbms();
		}


	@Test
		public void createDb()
		{
			File file = new File( dbPath );

			assertTrue( file.exists() );
		}


	@Test
		public void updateTestAppend() 
		{
			try
			{
				// Writing initial output
				BufferedWriter out = new BufferedWriter( new FileWriter( dbPath ) );

				out.write( "0\tfalse\tA\tComments\r\n" );

				out.close();

				d.readDbFile();

				// Appending to initial output
				d.update( "1", false, "A", "Updated!" );


				// Assert that new lines were added
				assertTrue( d.dbLines.length == 2 );
			}
			catch( IOException e )
			{
				System.out.println( "IO Error appending an instructor." );
				e.printStackTrace();
				System.exit(1);
			}
		}

	@Test
		public void updateTestReplace() 
		{
			try
			{
				outputAssignments();

				// Updating initial output
				d.update( "0", false, "A", "Updated!" );

				// Checking update worked
				BufferedReader in = new BufferedReader( new FileReader( dbPath ) );

				String line = in.readLine();

				in.close();

				assertTrue( line.compareTo( "0\tfalse\tA\tUpdated!" ) == 0 );
			}
			catch( IOException e )
			{
				System.out.println( "IO Error updating UserAssignment." );
				e.printStackTrace();
				System.exit(1);
			}
		}


	@Test
		public void existsTestTrue()
		{
			outputAssignments();

			assertTrue( d.exists( 1, false );
		}

	@Test
		public void existsTestFalse()
		{
			outputAssignments();
			
			assertFalse( d.exists( 1, true );
		}


	@Test
		public void getGradeTest()
		{
			outputAssignments();

			assertTrue( d.getGrade( 3, false ).compareTo("A") == 0 );
		}


	@Test
		public void getCommentsTest
		{
			outputAssignments();

			assertTrue( d.getComments( 2, false ).compareTo("Comments") == 0 );
		}


	@After
		public void tearDown()
		{
			File file = new File( dbPath );

			delete( file );

			d = null;
			dueDate = null;
		}

	@AfterClass
		public static void teardown()
		{
			File file = new File( System.getProperty( "user.dir" ) + "/res/TAListBACKUP.txt" );

			if( file.exists() )
				file.renameTo( new File(file.getAbsolutePath() + "TAList.txt") );
		}
}
