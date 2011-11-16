package Management;
import java.io.FileNotFoundException;
import java.util.Calendar;

import Users.*;
import FileManagement.*;
import DBMS.*;


public class StudentAccess extends CourseAssignment{
	protected String sID;
	protected String grade;
	protected String comment;
	protected String path;
	private FileManager fM;
	private UserAssignmentDbms uADbms;
	


	/**
	 * Creates a new student access object associated with a student
	 *
	 * @param s - The student to associate with
	 * @throws AssignmentNotExistException 
	 */
	public StudentAccess(String s,boolean late,Course cor,String desc,Calendar date,boolean assVis,boolean gradeVis) throws AssignmentNotExistException
	{
		super(cor,desc,date,assVis,gradeVis);
		sID = s;
		UserAssignmentDbms uADbms = new UserAssignmentDbms(tDbms.getInstructorId(sDbms.getTaId(sID, this.getCourseName()), this.getCourseName()),this.getCourseName(),this.getAssignmentNumber());
		grade = uADbms.getGrade(sID, late);
		comment = uADbms.getComments(sID, late);
	
	}



	/**
	 * Doesn't Allow For Assignment to be deleted AFTER dueDate*/
	/**
	 * Deletes an assignment from a course 
	 *
	 * @param assNumber - The assignment number to delete the assignment from
	 * @param course - The course to delete from
	 *
	 * //TODO Is this deleting a submission from a student, or an assignment in a course?
	 * 		I think it's a student submission, but the name is ambiguous
	 * 	Franky: renamed it with refactor...hope it doesnt screw things up
	 */
	public void deleteStudentAssignment(int assNumber,String course) throws AssignmentNotExistException{
		uADbms = new UserAssignmentDbms(this.getInstructorId(),this.getCourseName(),this.getAssignmentNumber());
		DueDate Due = new DueDate();
		boolean isDue = Due.pastDue(this.getDueDate());
		uADbms.deleteSubmission(sID, isDue);
	}



	/**
	 * Submits an assignment for a student including copying their submission file
	 *
	 * @param assignNum - The assignment number that the submission is for
	 * @param dueDate - The due date of the assignment
	 */
	public void SubmitAssignment(int assignNum, Calendar dueDate, String srcPath) throws FileNotFoundException
	{
		DueDate due = new DueDate();
		fM = new FileManager(tDbms.getInstructorId(sDbms.getTaId(sID, this.getCourseName()), this.getCourseName()));
		fM.submitFile(this.getCourseName(),this.getAssignmentNumber(), due.pastDue(this.getDueDate()),srcPath,sID );
	}
}
