package Management;

import java.util.Calendar;

import Users.*;
import FileManagement.*;
import DBMS.*;
public class CourseAssignment implements DBMSAccessor{
	private Course c;
	private String description;
	private Calendar dueDate;
	private static int assignmentNumber =0;
	private boolean assignmentVisable;
	private boolean gradesVisable;
	private boolean commentVisable;
	private AssignmentDbms aDbms;
	/**
	 * //TODO Do we need an empty constructor?  Basically are we ever creating an empty course?
	 *
	 * Creates a new, empty CourseAssignment for use
	 */
	public CourseAssignment(){
		c = null;
		description = "N/A";
		dueDate = null;
		assignmentNumber = 0;
		assignmentVisable = false;
		gradesVisable = false;
		commentVisable = false;
	}
	


	/**
	 * Creates a Course Assignment
	 *
	 * @param cor - The course this assignment belongs to
	 * @param desc - The description of the course
	 * @param date - The due date of the assignment
	 * @param assVis - Whether the assignment is visible to students or not
	 * @param gradeVis - Whether grades for this assignment are visible or not
	 */
	public CourseAssignment(Course cor,String desc,Calendar date,boolean assVis,boolean gradeVis){
		c = cor;
		description = desc;
		dueDate = date;
		assignmentNumber++;
		assignmentVisable = assVis;
		gradesVisable = gradeVis;
		aDbms = new AssignmentDbms(c.getInstructorId(), c.getCourseName());

	}



	/**
	 * @return - The due date for this assignment
	 */
	public Calendar getDueDate(){
		return dueDate;
	}



	/**
	 * Sets the grade visibility for this assignment
	 *
	 * @param T_F - The new grade visibility; true if the grades should be visible, false otherwise
	 * @throws AssignmentNotExistException 
	 */
	protected void setGradeVisability(boolean T_F) throws AssignmentNotExistException{
		gradesVisable = T_F;
		aDbms.update(assignmentNumber, assignmentVisable, gradesVisable, gradesVisable, description, dueDate);

	}



	/**
	 * Sets the comments visibility for this assignment
	 *
	 * @param T_F - The new comments visibility; true if the comments should be visible, false otherwise
	 * @throws AssignmentNotExistException 
	 */
	protected void setCommentVisability(boolean T_F) throws AssignmentNotExistException{
		commentVisable = T_F;
		aDbms.update(assignmentNumber, assignmentVisable, gradesVisable, gradesVisable, description, dueDate);
	}



	/**
	 * @return - The visiblity of the comments; true if they are visibile, false otherwise
	 */
	protected boolean getCommentVisabiltiy(){
		return commentVisable;
	}
	


	/**
	 * Sets the visibility of this assignment to students
	 *
	 * @param T_F - The new visibility of the assignment; true if visible, false otherwise
	 * @throws AssignmentNotExistException 
	 */ 
	public void setAssignmentVisable(boolean T_F) throws AssignmentNotExistException{
		assignmentVisable = T_F;
		aDbms.update(assignmentNumber, assignmentVisable, gradesVisable, gradesVisable, description, dueDate);

	}



	/**
	 * Sets the due date of this assignment
	 *
	 * @param newDue - The new due date of the assignment
	 * @throws AssignmentNotExistException 
	 */
	protected void setDueDate(Calendar newDue) throws AssignmentNotExistException{
		dueDate = newDue;
		aDbms.update(assignmentNumber, assignmentVisable, gradesVisable, gradesVisable, description, dueDate);

	}



	/**
	 * Updates the description for this assignment
	 *
	 * @param newDesc - The new description to be applied
	 * @throws AssignmentNotExistException 
	 */
	public void updateDescription(String newDesc) throws AssignmentNotExistException{
		description = newDesc;
		aDbms.update(assignmentNumber, assignmentVisable, gradesVisable, gradesVisable, newDesc, dueDate);

	}
	


	/**
	 * @return - The name of the course that this assignment belongs to
	 */
	public String getCourseName(){
		return c.getCourseName();
	}



	/**
	 * @return - The number that this assignment is
	 */
	public int getAssignmentNumber(){
		return assignmentNumber;
	}



	/**
	 * @return - The ID of the instructor for this course
	 */
	public String getInstructorId(){
		return c.getInstructorId();
	}



	/**
	 * @return - The description of this course
	 */
	public String getDescription(){
		return description;
	}
}
