package Management;


import java.util.Calendar;

public class DueDate {
	// TODO Do we need a constructor and/or member variables for this?
	
	/**
	 * Checks if the current date is past the due date
	 *
	 * @param dueDate - The due date to check against
	 *
	 * @return - True if the current date is past due date, false otherwise
	 */
	public boolean pastDue(Calendar dueDate)
	{
		Calendar cal = Calendar.getInstance();

		if(cal.compareTo(dueDate) < 0)
			return false;
		else
			return true;
	}



}
