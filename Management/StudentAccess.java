package Management;
import java.io.FileNotFoundException;
import java.util.Calendar;

import Users.*;
import FileManagement.*;
import DBMS.*;


public class StudentAccess extends CourseAssignment{
	protected Student student;
	protected String grade;
	protected String comment;
	protected String path;
	private FileManager fM;
	private UserAssignmentDbms uADbms;
	
	public StudentAccess(Student s){
		student = s;
	}
	/**
	 * Doesnt Allow For Assignment to be deleted AFTER dueDate*/
	public void DeleteAssignment(int assNumber,String course) throws AssignmentNotExistException{
		uADbms = new UserAssignmentDbms(this.getInstructorId(),this.getCourseName(),this.getAssignmentNumber());
		DueDate Due = new DueDate();
		boolean isDue = Due.pastDue(this.getDueDate());
		uADbms.deleteSubmission(student.getName(), isDue);
	}
	public void SubmitAssignment( String course, int assignNum, Calendar dueDate, String srcPath) throws FileNotFoundException
	{
		DueDate due = new DueDate();
		fM = new FileManager(student.getInstructor());
		fM.submitFile(course,assignNum, due.pastDue(dueDate),srcPath,student.getName() );
	}

	
}
