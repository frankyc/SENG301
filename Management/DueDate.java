package Management;


import java.util.Calendar;

public class DueDate {
	// TODO Do we need a constructor and/or member variables for this?
	// Reply I dont think so, it will be a singleton, created once and used over and over again
	
	/**
	 * Checks if the current date is past the due date
	 *
	 * @param dueDate - The due date to check against
	 *
	 * @return - True if the current date is past due date, false otherwise
	 */
	public static boolean pastDue(Calendar dueDate)
	{
		Calendar cal = Calendar.getInstance();

		if(cal.compareTo(dueDate) < 0)
			return false;
		else
			return true;
	}



}
