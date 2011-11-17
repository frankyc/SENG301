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
	protected FileManager fM;
	protected UserAssignmentDbms uADbms;
	


	/**
	 * Creates a new student access object associated with a student
	 *
	 * @param s - The student to associate with
	 * @throws AssignmentNotExistException 
	 */
	public StudentAccess(
			int assignNum,
			String s,
			boolean late,
			Course cor,
			String desc,
			Calendar date,
			boolean assVis,
			boolean gradeVis
			) throws AssignmentNotExistException
	{
		super(assignNum, cor,desc,date,assVis,gradeVis);

		sID = s;
		UserAssignmentDbms uADbms = new UserAssignmentDbms(tDbms.getInstructorId(sDbms.getTaId(sID, this.getCourseName()), this.getCourseName()),this.getCourseName(),this.getAssignmentNumber());
		grade = uADbms.getGrade(sID, late);
		comment = uADbms.getComments(sID, late);
	
	}



	/**
	 * Doesn't Allow For Assignment to be deleted AFTER dueDate*/
	/**
	 * Deletes this assignment from a course 
	 *
	 * @return - True if the assignment was deleted, false if it is past the due date and therefore cannot be deleted
	 *
	 * //TODO Is this deleting a submission from a student, or an assignment in a course?
	 * 		I think it's a student submission, but the name is ambiguous
	 * 	Franky: renamed it with refactor...hope it doesnt screw things up
	 */
	public boolean deleteStudentAssignment()
	{
		uADbms = new UserAssignmentDbms( this.getInstructorId(), this.getCourseName(), this.getAssignmentNumber() );

		if( DueDate.pastDue( this.getDueDate() ) )
			return false;

		try
		{
			uADbms.deleteSubmission( sID, false /* late */ );
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "This assignment doesn't exist?  Something went wrong deleting this assignment!" );

			e.printStackTrace();
		}

		return true;
	}



	/**
	 * Submits an assignment for a student including copying their submission file
	 *
	 * @param srcPath - The path to the file being submitted
	 */
	public void submitAssignment( String srcPath ) throws FileNotFoundException
	{
		fM = new FileManager( c.getInstructorId() );

		fM.submitFile( this.getCourseName(), this.getAssignmentNumber(), DueDate.pastDue( this.getDueDate() ), srcPath, sID );
	}
}
