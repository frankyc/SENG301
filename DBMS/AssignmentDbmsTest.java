/**
 * AssignmentDbmsTest.java
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

public class AssignmentDbmsTest
{
	private AssignmentDbms ad;
	private Calendar dueDate;
	private static String dbPath = System.getProperty( "user.dir" ) + "/res/Test/SENG301/AssignmentList.txt";


	public static void main( String[] args )
	{
		org.junit.runner.JUnitCore.main( "DBMS.AssignmentDbmsTest" );
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

			// Write some assignments
			for( int i = 0; i < 5; i++ )
				out.write( i + "\tfalse\tfalse\tfalse\tDescription\t" + dueDate.getTimeInMillis() + "\r\n" );

			out.close();

			ad.readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Error outputting assignments." );
			e.printStackTrace();
			System.exit(1);
		}
	}



	Calendar createDueDate()
	{
		Calendar dueDate = new GregorianCalendar();

		dueDate.set( 2011, 11, 17 );

		return dueDate;
	}
		

		

	@BeforeClass
		public static void setup()
		{
			File file = new File( System.getProperty( "user.dir" ) + "/res/Test/" );

			if( file.exists() )
				delete( file );
		}

	@Before
		public void initialize()
		{
			ad = new AssignmentDbms( "Test", "SENG301" );
			
			dueDate = createDueDate();
		}
	



	@Test
		public void createDb()
		{
			File file = new File( System.getProperty( "user.dir" ) + "/res/Test/SENG301/AssignmentList.txt" );

			assertTrue( file.exists() );
		}


	@Test
		public void updateAssignment() throws AssignmentNotExistException
		{
			try
			{
				// Writing initial output
				BufferedWriter out = new BufferedWriter( new FileWriter( dbPath ) );

				out.write( "1\tfalse\tfalse\tfalse\tDescription\t" + dueDate.getTimeInMillis() + "\r\n" );

				out.close();

				ad.readDbFile();

				// Updating initial output
				ad.update( 1, false, false, false, "Updated!", dueDate );

				// Checking update worked
				BufferedReader in = new BufferedReader( new FileReader( dbPath ) );

				String line = in.readLine();

				in.close();

				assertTrue( line.compareTo( "1\tfalse\tfalse\tfalse\tUpdated!\t" + dueDate.getTimeInMillis() ) == 0 );
			}
			catch( IOException e )
			{
				System.out.println( "IO Error updating assignment." );
				e.printStackTrace();
				System.exit(1);
			}
		}

	@Test(expected=AssignmentNotExistException.class)
		public void updateAssignmentAssignmentNotExist() throws AssignmentNotExistException
		{
			try
			{
				// Writing initial output
				BufferedWriter out = new BufferedWriter( new FileWriter( dbPath ) );

				out.write( "1\tfalse\tfalse\tfalse\tDescription\t" + dueDate.getTimeInMillis() + "\r\n" );

				out.close();

				ad.readDbFile();

				// Updating initial output
				ad.update( 5, false, false, false, "Updated!", dueDate );

				// Checking update worked
				BufferedReader in = new BufferedReader( new FileReader( dbPath ) );

				String line = in.readLine();

				in.close();

				assertTrue( line.compareTo( "1\tfalse\tfalse\tfalse\tUpdated!\t" + dueDate.getTimeInMillis() ) == 0 );
			}
			catch( IOException e )
			{
				System.out.println( "IO Error updating assignment." );
				e.printStackTrace();
				System.exit(1);
			}
		}


	@Test
		public void addAssignment()
		{
			try
			{
				ad.add( false, false, false, "Description", dueDate );

				BufferedReader in = new BufferedReader( new FileReader( dbPath ) );

				int i = 0;
				String line = null;
				while( (line = in.readLine()) != null )
				{
					String[] parts = line.split( "\t" );

					assertTrue( parts.length == 6 );

					i++;
				}

				assertTrue( i == 1 );

				in.close();
			}
			catch( IOException e )
			{
				System.out.println( "IO Error adding assignment." );
				e.printStackTrace();
				System.exit(1);
			}
		}


	@Test
		public void assignmentExistsFalse()
		{
			outputAssignments();

			assertFalse( ad.exists( 10 ) );
		}

	@Test
		public void assignmentExistsTrue()
		{
			outputAssignments();

			assertTrue( ad.exists( 2 ) );
		}


	@Test
		public void assignVisibleTest()
		{
			outputAssignments();

			try
			{
				assertFalse( ad.assignVisible( 1 ) );
			}
			catch( AssignmentNotExistException e )
			{
				Assert.fail();
			}
		}


	@Test
		public void assignGradesVisibleTest()
		{
			outputAssignments();

			try
			{
				assertFalse( ad.assignGradesVisible( 1 ) );
			}
			catch( AssignmentNotExistException e )
			{
				Assert.fail();
			}
		}

	@Test
		public void assignCommentsVisibleTest()
		{
			outputAssignments();

			try
			{
				assertFalse( ad.assignCommentsVisible( 1 ) );
			}
			catch( AssignmentNotExistException e )
			{
				Assert.fail();
			}
		}

	@Test
		public void getAssignmentDescriptionTest()
		{
			outputAssignments();

			try
			{
				assertTrue( ad.getDescription( 1 ).compareTo( "Description" ) == 0 );
			}
			catch( AssignmentNotExistException e )
			{
				Assert.fail();
			}
		}


	@Test
		public void getDueDateTest()
		{
			outputAssignments();

			try
			{
				assertTrue( ad.getDueDate( 1 ).compareTo( dueDate ) == 0 );
			}
			catch( AssignmentNotExistException e )
			{
				Assert.fail();
			}
		}


	@After
		public void tearDown()
		{
			File file = new File( System.getProperty( "user.dir" ) + "/res/Test/SENG301/AssignmentList.txt" );

			delete( file );

			ad = null;
			dueDate = null;
		}

	@AfterClass
		public static void teardown()
		{
			File file = new File( System.getProperty( "user.dir" ) + "/res/Test/" );

			delete( file );
		}
}
