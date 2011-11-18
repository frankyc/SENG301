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
	protected boolean submitted;
	protected boolean late;
	


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
			) 
	{
		super(assignNum, cor,desc,date,assVis,gradeVis);

		sID = s;
		this.late = late;

		uADbms = new UserAssignmentDbms(
				tDbms.getInstructorId(sDbms.getTaId(sID, this.getCourseName()), this.getCourseName()),
				this.getCourseName(),
				this.getAssignmentNumber()
				);

		try
		{
			if( uADbms.exists( sID, late ) )
			{
				grade = uADbms.getGrade(sID, late);
				comment = uADbms.getComments(sID, late);
				submitted = true;
			}
			else
			{
				grade = "-";
				comment = "-";
				submitted = false;
			}
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "Something went wrong!" );
			
			e.printStackTrace();
			System.exit(1);
		}
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

		FileManager fm = new FileManager( c.getInstructorId() );

		try
		{
			uADbms.deleteSubmission( sID, false /* late */ );

			fm.deleteSubmission( c.getCourseName(), assignmentNumber, late, sID );
		}
		catch( AssignmentNotExistException e )
		{
			System.out.println( "This assignment doesn't exist?  Something went wrong deleting this assignment!" );

			e.printStackTrace();
		}

		submitted = false;

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

		if( uADbms == null )
		{
			System.out.println( "DBMS is NULL" );
			System.exit(1);
		}

		uADbms.add( sID, late );

		submitted = true;
	}



	/**
	 * @return - The grade for this assignment
	 */
	public String getGrade()
	{
		return grade;
	}



	/**
	 * @return - The student ID for this assignment
	 */
	public String getId()
	{
		return sID;
	}


	/**
	 * @return - True if this assignment has a submission associated with it, false otherwise
	 */
	public boolean submitted()
	{
		return submitted;
	}
}
