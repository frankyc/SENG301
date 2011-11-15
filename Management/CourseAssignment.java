package Management;

import java.util.Calendar;

import Users.*;
import FileManagement.*;
import DBMS.*;
public class CourseAssignment implements DBMSAccessor{
	private Course c;
	private String description;
	private Calendar dueDate;
	private int assignmentNumber =0;
	private boolean assignmentVisable;
	private boolean gradesVisable;
	private boolean commentVisable;

	public CourseAssignment(){
		c = null;
		description = "N/A";
		dueDate = null;
		assignmentNumber = 0;
		assignmentVisable = false;
		gradesVisable = false;
		commentVisable = false;
	}
	
	public CourseAssignment(Course cor,String desc,Calendar date,int assNum,boolean assVis,boolean gradeVis){
		c = cor;
		description = desc;
		dueDate = date;
		assignmentNumber = assNum;
		assignmentVisable = assVis;
		gradesVisable = gradeVis;
	}
	public Calendar getDueDate(){
		return dueDate;
	}

	protected void setGradeVisability(boolean T_F){
		gradesVisable = T_F;
	}
	protected void setCommentVisability(boolean T_F){
		commentVisable = T_F;
	}
	protected boolean getCommentVisabiltiy(){
		return commentVisable;
	}
	
	public void setAssignmentVisable(boolean T_F){
		assignmentVisable = T_F;
	}
	protected void setDueDate(Calendar newDue){
		dueDate = newDue;
	}
	public void updateDescription(String newDesc){
		description = newDesc;
	}
	
	public String getCourseName(){
		return c.getCourseName();
	}
	public int getAssignmentNumber(){
		return assignmentNumber;
	}
	public String getInstructorId(){
		return c.getInstructorId();
	}
	public String getDescription(){
		return description;
	}
}
