package Management;

import Users.*;
import FileManagement.*;
import DBMS.*;

import java.util.Calendar;
import java.util.Vector;

public class Course implements DBMSAccessor{
	private String courseName;
	DirectoryManager dM;
	
	private Vector<CourseAssignment> ca;
	private String iId;
	


	/**
	 * Creates a new, empty course
	 */
	public Course(){
		ca = new Vector<CourseAssignment>();
		courseName = "";
	}
	


	/**
	 * Creates a course with a name and instructor ID associated with it
	 */
	public Course(String cName,String instrID){
		iId = instrID;
		courseName = cName;
		//ca = null if course did not exist
		ca = null;
		
		
		//ca = new CourseAssignment[] { all assignments listed }
		ca.addElement(new CourseAssignment());
	}



	/**
	 * Creates an assignment in this course
	 *
	 * @param cor - The course this assignment belongs to
	 * //TODO Do we need cor since we are in a course already?? ****
	 * @param desc - The description of the course
	 * @param date - The due date of the course
	 * @param assNum - The assignment number
	 * //TODO Since the assignment numbers are auto-incrementing, does this need to be here? ****
	 * @param assVis - Whether the assignment is visible to students or not
	 * @param gradeVis - Whether the grades for this assignment are visible to students or not
	 */
	public void createAssignment(Course cor,String desc,Calendar date,int assNum,boolean assVis,boolean gradeVis){
		CourseAssignment newAssignment = new CourseAssignment(cor,desc,date,assNum,assVis,gradeVis);
		ca.addElement(newAssignment);
		dM = new DirectoryManager (iId);
		dM.createAssignDir( courseName, assNum );
	}



	/**
	 * Lists the assignments in this course
	 * //TODO Instead of saying that the method can be changed to output to a file
	 * 		might as well have an overloaded method that takes in the path...e.g.
	 * 		listAssignment( String path )
	 */
	private void listAssignments(){
		for(int i =0;i <ca.size();i++){
			System.out.println(ca.elementAt(i));//can be changed to output in file
		}
	}
	


	/**
	 * @return - The name of this course
	 */
	public String getCourseName(){
		return courseName;
	}



	/**
	 * @return - This course's instructor
	 */
	public String getInstructorId(){
		return iId;
	}



	/**
	 * Gets an assignment that belongs to this course
	 *
	 * @param assNum - The assignment number to get
	 * 
	 * @return - The assignment asked for
	 */
	public CourseAssignment getAssignment(int assNum){
		for(int i =0; i< ca.size(); i++){
			if(ca.elementAt(i).getAssignmentNumber() == assNum){
				return ca.elementAt(i);
			}
		}
		return null;
	}
}
