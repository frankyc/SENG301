/**
 * BaseDbms.java
 * SENG301 Assignment 4/5
 *
 * By: Franky Cheung
 * 	Colin Williams
 */

public abstract class BaseDbms
{
	protected String pathToDb;
	protected File dbFile;

	protected String[] ReadDbFile()
	{
		String lines[] = new String[ countLines(in) ];

		try {
		BufferedReader in = new BufferedReader( new FileReader( pathToDb ) );

		String line = null;

		for( int i = 0; i < lines.length; i++ )
			lines[i] = in.readLine();
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Can't read db file." );
			e.printStackTrace();
			System.exit(1);
		}
	}


	protected countLines( BufferedReader in )
	{
		try
		{
			int i = 0;

			while( in.readLine() != null )
				i++;
		}
		catch( IOException e )
		{
			System.out.println( "ERROR: Can't read number of lines from db file." );
			e.printStackTrace();
			Sytem.exit(1);
		}

		return i;
	}
}
