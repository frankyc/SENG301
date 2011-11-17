package Management;

import java.util.Calendar;

import Users.*;
import FileManagement.*;
import DBMS.*;

public class StudentAssignment extends StudentAccess implements DBMSAccessor{
	/**
	 * Creates a new Student Assignment
	 *
	 * @param s - The student this assignment belongs to
	 * @throws AssignmentNotExistException 
	 */
	public StudentAssignment(int assignNum, String s,boolean late,Course cor,String desc,Calendar date,boolean assVis,boolean gradeVis) throws AssignmentNotExistException
	{
		super( assignNum, s, late, cor, desc, date, assVis, gradeVis );
	}


	/**
	 * Assigns a grade to this assignment
	 *
	 * @param gr - the grade to be assigned
	 */
	public void assignGrades(String gr){
		grade = gr;
	}



	/**
	 * Adds a comment to this assignment
	 *
	 * @param comm - The comment to add
	 */
	public void addComment(String comm){
		comment = comm;
	}



	/**
	 * Updates the grade for this assignment
	 *
	 * @param newgr - The new grade to be assigned
	 * @param isLate - True if the assignment is late, false otherwise
	 */
	public void updateGrade(String newGr,boolean isLate) throws AssignmentNotExistException{
		grade = newGr;
		uADbms.update(sID, isLate, newGr,uADbms.getComments(sID, isLate));
	}



	/**
	 * Updates the comment for this assignment
	 *
	 * @param comm - The new comment
	 * @param isLate - Whether the assignment is late or not
	 */
	public void updateComment(String comm,boolean isLate){
		comment = comm;
		uADbms.update(sID, isLate,uADbms.getGrade(sID, isLate), comm);
	}
	
}
