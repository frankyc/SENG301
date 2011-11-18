/**
 * InstructorDbmsTest.java
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

public class InstructorDbmsTest
{
	private InstructorDbms d;
	private Calendar dueDate;
	private static String dbPath = System.getProperty( "user.dir" ) + "/res/InstructorList.txt";


	public static void main( String[] args )
	{
		org.junit.runner.JUnitCore.main( "DBMS.InstructorDbmsTest" );
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


	void outputInstructors() 
	{
		try
		{
			BufferedWriter out = new BufferedWriter( new FileWriter( dbPath ) );

			// Write some assignments
			for( int i = 0; i < 5; i++ )
				out.write( i + "\tSENG301\r\n" );

			out.close();

			d.readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Error outputting instructors." );
			e.printStackTrace();
			System.exit(1);
		}
	}



	@BeforeClass
		public static void setup()
		{
			File file = new File( dbPath );

			if( file.exists() )
				file.renameTo( new File(file.getAbsolutePath() + "InstructorListBACKUP.txt") );
		}

	@Before
		public void initialize()
		{
			d = new InstructorDbms();
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

				out.write( "Test\tSENG301\r\n" );

				out.close();

				d.readDbFile();

				// Appending to initial output
				d.update( "1", "CPSC471" );


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
		public void updateInstructor() 
		{
			try
			{
				outputInstructors();

				// Updating initial output
				d.update( "0", "CPSC471" );

				// Checking update worked
				BufferedReader in = new BufferedReader( new FileReader( dbPath ) );

				String line = in.readLine();

				in.close();

				assertTrue( line.compareTo( "0\tCPSC471" ) == 0 );
			}
			catch( IOException e )
			{
				System.out.println( "IO Error updating instructor." );
				e.printStackTrace();
				System.exit(1);
			}
		}


	@Test
		public void existsId()
		{
			outputInstructors();

			assertTrue( d.exists("1") );
			
			assertFalse( d.exists("100") );
		}

	@Test
		public void existsIdCourse()
		{
			outputInstructors();

			assertTrue( d.exists( "1", "SENG301" ) );
			assertFalse( d.exists( "1", "CPSC471" ) );
		}


	@Test
		public void getCoursesTest()
		{
			outputInstructors();

			String[] courses = d.getCourses( "3" );

			assertTrue( courses.length == 1 );
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
			File file = new File( System.getProperty( "user.dir" ) + "/res/InstructorListBACKUP.txt" );

			if( file.exists() )
				file.renameTo( new File(file.getAbsolutePath() + "InstructorList.txt") );
		}

}
