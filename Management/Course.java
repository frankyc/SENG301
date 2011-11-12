package Management;

import Users.*;

public class Course {
	private CourseAssignment ca[];
	
	public Course(){
		ca = new CourseAssignment[] {null};
	}
	
	public Course(String courseName){
		//uses DB to find courseName;
		//ca = new CourseAssignment[] { all assignments listed }
	}
	private void createAssignment(){
		CourseAssignment newCourse;
	}
	
	private void listAssignments(){
		
	}
	
}
