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



	/**
	 * //TODO is this setting the grade visibility to whatever is passed in,
	 * 	or only setting it to on?  If it's whatever is passed in then
	 * 	the name doesn't portray that properly and should probably be updated
	 * 	Comment should be updated as appropriate
	 *
	 * Releases grades for an assignment
	 *
	 * @param T_F - What to set the grade visibility to
	 */
	public void releaseGrade(boolean T_F){
		setGradeVisability(T_F);
	}



	/**
	 * Toggles the comment visibility
	 *
	 * If the visibility was false, it becomes true and visa-versa
	 */
	public void toggleCommentVisablility(){
		if(getCommentVisabiltiy() == true){
			setCommentVisability(false);
		}else {
			setCommentVisability(true);
		}
	}




	/**
	 * Downloads all grades for this assignment to the specified file
	 *
	 * @param path - The path to the file that the grades should be written to
	 *
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
	


	/**
	 * Uploades grades from a file into the system
	 *
	 * @param path - The path for the source file
	 */
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
	


	/**
	 * // TODO empty function?  Do we need it?
	 */
	public void getAssignment(String id, String course, String assNum){
		
	}
	


	/**
	 * // TODO I feel that the course and assignNum path shouldn't be needed here if the
	 * 		other methods above work; they don't need that info so why should this one?
	 *
	 * Downloads all submitted files to a directory
	 *
	 * @param course - The course to download the files for
	 * @param destPath - The destination directory
	 * @param assignNum - The assignment number to download files from
	 */
	public void downloadSubmittedFiles(String course,String destPath,int assignNum) throws FileNotFoundException{
		fM = new FileManager(this.getInstructorId());
		fM.downloadAllSubmittedFiles( course, assignNum,destPath );
	}
}
