/**
 * TADbmsTest.java
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

public class TADbmsTest
{
	private TADbms d;
	private Calendar dueDate;
	private static String dbPath = System.getProperty( "user.dir" ) + "/res/TAList.txt";


	public static void main( String[] args )
	{
		org.junit.runner.JUnitCore.main( "DBMS.TADbmsTest" );
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


	void outputStudents() 
	{
		try
		{
			BufferedWriter out = new BufferedWriter( new FileWriter( dbPath ) );

			for( int i = 0; i < 5; i++ )
				out.write( i + "\tSENG301\tTest\r\n" );

			out.close();

			d.readDbFile();
		}
		catch( IOException e )
		{
			System.out.println( "Error outputting TA's." );
			e.printStackTrace();
			System.exit(1);
		}
	}



	@BeforeClass
		public static void setup()
		{
			File file = new File( dbPath );

			if( file.exists() )
				file.renameTo( new File(file.getAbsolutePath() + "TAListBACKUP.txt") );
		}

	@Before
		public void initialize()
		{
			d = new TADbms();
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

				out.write( "TA1\tSENG301\tTest\r\n" );

				out.close();

				d.readDbFile();

				// Appending to initial output
				d.update( "1", "CPSC471", "Test" );


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
		public void updateStudent() 
		{
			try
			{
				outputStudents();

				// Updating initial output
				d.update( "0", "CPSC471", "Test" );

				// Checking update worked
				BufferedReader in = new BufferedReader( new FileReader( dbPath ) );

				String line = in.readLine();

				in.close();

				assertTrue( line.compareTo( "0\tCPSC471\tTest" ) == 0 );
			}
			catch( IOException e )
			{
				System.out.println( "IO Error updating instructor." );
				e.printStackTrace();
				System.exit(1);
			}
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
