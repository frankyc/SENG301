package Management;

import Users.*;

import java.sql.Date;
import java.util.Vector;

public class Course {
	private Vector<CourseAssignment> ca;
	
	public Course(){
		ca = new Vector<CourseAssignment>();
	}
	
	public Course(String courseName){
		//ca = null if course does not exist
		ca = null;
		
		//ca = new CourseAssignment[] { all assignments listed }
		ca.addElement(new CourseAssignment());
	}
	public void createAssignment(Course cor,String desc,Date date,int assNum,boolean assVis,boolean gradeVis){
		CourseAssignment newAssignment = new CourseAssignment(cor,desc,date,assNum,assVis,gradeVis);
		ca.addElement(newAssignment);
	}
	
	private void listAssignments(){
		for(int i =0;i <ca.size();i++){
			System.out.println(ca.elementAt(i));//can be changed to output in file
		}
	}
	
}
