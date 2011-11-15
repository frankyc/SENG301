package Management;
import java.util.Vector;
import Users.*;
import FileManagement.*;
import DBMS.*;

public class CourseManager implements DBMSAccessor
{
	private Vector<Course> c;
	private AssignmentDbms aDbms;
	private DirectoryManager dM;
	public CourseManager(String iID) throws AssignmentNotExistException{
		//List of courses for instructor set as c;
		String [] courses = iDbms.getCourses(iID);
		for(int i =0; i < courses.length;i++){
			c.addElement(new Course(courses[i],iID));
			int j=1;
			aDbms = new AssignmentDbms(iID, courses[i]);
			while(aDbms.exists(j)){
			c.elementAt(i).createAssignment(c.elementAt(i), aDbms.getDescription(j), 
					aDbms.getDueDate(j), j, aDbms.assignVisible(j), 
					aDbms.assignGradesVisible(j));
			j++;
			}
		}
	}
	
	public void CreateCourse(String cName,String instrID) {
		c.addElement(new Course());
		dM = new DirectoryManager(instrID);
		dM.createCourseDir(cName);
	}
	public void ListCourse(){
		int i = 0;
		while( i < c.size()){
			System.out.println(c.elementAt(i));
			i++;
		}
	}
	
	public Course getCourse(String courseName){
		int i =0;
		while(i < c.size()){
			i++;
			if(c.elementAt(i).getCourseName() == courseName)
			{
				return c.elementAt(i);
			}
		}
		return null;
	}
}
