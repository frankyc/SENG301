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
	
	public Course(){
		ca = new Vector<CourseAssignment>();
		courseName = "";
	}
	
	public Course(String cName,String instrID){
		iId = instrID;
		courseName = cName;
		//ca = null if course did not exist
		ca = null;
		
		
		//ca = new CourseAssignment[] { all assignments listed }
		ca.addElement(new CourseAssignment());
	}
	public void createAssignment(Course cor,String desc,Calendar date,int assNum,boolean assVis,boolean gradeVis){
		CourseAssignment newAssignment = new CourseAssignment(cor,desc,date,assNum,assVis,gradeVis);
		ca.addElement(newAssignment);
		dM = new DirectoryManager (iId);
		dM.createAssignDir( courseName, assNum );
	}

	private void listAssignments(){
		for(int i =0;i <ca.size();i++){
			System.out.println(ca.elementAt(i));//can be changed to output in file
		}
	}
	
	public String getCourseName(){
		return courseName;
	}
	public String getInstructorId(){
		return iId;
	}
	public CourseAssignment getAssignment(int assNum){
		for(int i =0; i< ca.size(); i++){
			if(ca.elementAt(i).getAssignmentNumber() == assNum){
				return ca.elementAt(i);
			}
		}
		return null;
	}
}
