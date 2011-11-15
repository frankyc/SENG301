package Management;
import Users.*;
import FileManagement.*;
import DBMS.*;

public class StudentAssignment extends StudentAccess{
	public StudentAssignment(Student s) {
		super(s);
	}
	private UserAssignmentDbms uADbms = new UserAssignmentDbms(student.getInstructor(),this.getCourseName(),this.getAssignmentNumber());
	public void assignGrades(String gr){
		grade = gr;
	}
	public void addComment(String comm){
		comment = comm;
	}
	public void updateGrade(String newGr,boolean isLate) throws AssignmentNotExistException{
		grade = newGr;
		uADbms.update(student.getName(), isLate, newGr,uADbms.getComments(student.getName(), isLate));
	}
	public void updateComment(String comm,boolean isLate){
		comment = comm;
		uADbms.update(student.getName(), isLate,uADbms.getGrade(student.getName(), isLate), comm);
	}
	
}
