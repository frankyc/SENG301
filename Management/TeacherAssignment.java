package Management;
import Users.*;
import java.util.Vector;

public class TeacherAssignment extends CourseAssignment{
	private Vector<StudentAssignment> sa;
	
	public void releaseGrade(boolean T_F){
		setGradeVisability(T_F);
	}
	public void toggleCommentVisablility(){
		if(getCommentVisabiltiy() == true){
			setCommentVisability(false);
		}else {
			setCommentVisability(true);
		}
	}
	/**
	 * This should read all grades for each student and output it as a txt with students grades
	 * each line to given path
	 */
	public void downloadGrades(String path){
		
	}
	
	public void uploadGrades(){
		
	}
	
	public void getAssignment(String id, String course, String assNum){
		
	}
	
	public void downloadSubmittedFiles(String course,String path){
		
	}
}
