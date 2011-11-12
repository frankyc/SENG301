package Management;

import java.sql.Date;
import Users.*;

public class CourseAssignment {
	private Course c;
	private String description;
	private Date dueDate;
	private static int assignmentNumber =0;
	private boolean assignmentVisable;
	private boolean gradesVisable;
	
	public CourseAssignment(){
		c = null;
		description = "N/A";
		dueDate = null;
		assignmentNumber = 0;
		assignmentVisable = false;
		gradesVisable = false;
	}
}
