package Management;


import java.util.Calendar;

public class DueDate {

	public boolean pastDue(Calendar dueDate){
		Calendar cal = Calendar.getInstance();
		if(cal.compareTo(dueDate) < 0)
		{
		return false;
		}
		else
		{
		return true;
		}
	}



}
