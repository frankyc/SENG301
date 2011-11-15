package Management;
import DBMS.*;
import FileManagement.*;
import Users.*;

import java.io.FileNotFoundException;
import java.util.Vector;

public class TeacherAssignment extends CourseAssignment{
	private Vector<StudentAssignment> sa;
	private UserAssignmentDbms uADbms;
	private FileManager fM;
	static final int STUDENTID = 0;
	static final int ISLATE = 1;
	static final int STUDENTGRADE = 2;
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
	 * @throws FileExistsException 
	 */
	public void downloadGrades(String path) throws FileExistsException{
		String[] students = null;
		/**
		 * Needs students
		 * */
		Vector<String> studentsGrades = null;
		int i =0;
		uADbms= new UserAssignmentDbms(this.getInstructorId(),this.getCourseName(),this.getAssignmentNumber());
		while(students.length > i ){
			studentsGrades.add(students[i]+ "\t" + "true"  + "\t"+ uADbms.getGrade(students[i], true));
			studentsGrades.add(students[i] + "\t"+ "false" +"\t" + uADbms.getGrade(students[i], false));
			i++;
		}
		String sgrades[] = new String[studentsGrades.size()];
		studentsGrades.toArray( sgrades );
		fM.writeGradeFile(path, sgrades);
	}
	
	public void uploadGrades(String path) throws AssignmentNotExistException{
		uADbms= new UserAssignmentDbms(this.getInstructorId(),this.getCourseName(),this.getAssignmentNumber());
		String[] studentsGrades = fM.readGradeFile(path);
		String[] students;
		for(int i=0; i< studentsGrades.length;i++){
			students = studentsGrades[i].split("\t");
			if(students[STUDENTID].compareTo("true") == 0)
			{
				uADbms.update(students[STUDENTID], true , students[STUDENTGRADE],uADbms.getComments(students[STUDENTID], true));
			}
			else if(students[ISLATE].compareTo("false") == 0){
				uADbms.update(students[STUDENTID], false , students[STUDENTGRADE],uADbms.getComments(students[STUDENTID], false));
			}
			else
			{
				System.err.println("File not in student\tLate\tGrade Format");
				System.exit(1);
			}
		}
	}
	
	public void getAssignment(String id, String course, String assNum){
		
	}
	
	public void downloadSubmittedFiles(String course,String destPath,int assignNum) throws FileNotFoundException{
		fM = new FileManager(this.getInstructorId());
		fM.downloadAllSubmittedFiles( course, assignNum,destPath );
	}
}
