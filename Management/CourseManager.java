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



	/**
	 * Creates a new Course Manager with an instructor
	 *
	 * @param iID - The Instructor ID associated with the assignments in this course
	 */
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
	


	/**
	 * Creates a new course being taught by this instructor
	 *
	 * @param cName - The name of the new course
	 * @param instrID - The ID of the instructor
	 */
	public void CreateCourse(String cName,String instrID) {
		c.addElement(new Course());
		dM = new DirectoryManager(instrID);
		dM.createCourseDir(cName);
	}



	/**
	 * //TODO Should this be outputting the courses to the screen or returning an array of them?
	 * 		Returning an array might be better and then Menus can output as necessary
	 *
	 * Lists the courses in this course manager, outputting them to the standard output
	 */
	public void ListCourse(){
		int i = 0;
		while( i < c.size()){
			System.out.println(c.elementAt(i));
			i++;
		}
	}
	



	/**
	 * Gets a course from this course manager
	 *
	 * @param courseName - The name of the course to get
	 *
	 * @return - The course specified with courseName
	 */
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
