package Management;
import DBMS.*;
import FileManagement.*;
import Users.*;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Vector;

public class TeacherAssignment extends CourseAssignment implements DBMSAccessor{
	private Vector<StudentAssignment> sa;
	private UserAssignmentDbms uADbms;
	private FileManager fM;

	static final int STUDENTID = 0;
	static final int ISLATE = 1;
	static final int STUDENTGRADE = 2;

	TeacherAssignment(int assignNum, Course cor,String desc,Calendar date,boolean assVis,boolean gradeVis) throws AssignmentNotExistException
	{
		super(assignNum, cor,desc, date, assVis, gradeVis);

		String[] students;
		students = sDbms.getStudentsInCourse(cor.getCourseName());

		sa = new Vector<StudentAssignment>();

		if( students != null )
		{
			//TODO needs false to be late or not
			for(int i =0; i< students.length;i++)
			{
				sa.addElement( new StudentAssignment(assignNum, students[i], false, cor, desc, date, assVis, gradeVis) );
			}
		}
	}

	TeacherAssignment( CourseAssignment ca ) throws AssignmentNotExistException
	{
		super( ca.getAssignmentNumber(), ca.getCourse(), ca.getDescription(), ca.getDueDate(), ca.isVisible(), ca.gradesVisible() );

		sa = new Vector<StudentAssignment>();

		String[] students = sDbms.getStudentsInCourse( ca.getCourse().getCourseName());

		if( students != null )
		{
			for( int i = 0; i < students.length; i++ )
			{
				sa.addElement( new StudentAssignment( ca.getAssignmentNumber(), students[i], false, ca.getCourse(), ca.getDescription(), ca.getDueDate(), ca.isVisible(), ca.gradesVisible() ) );
			}
		}
	}

	/**
	 * //TODO is this setting the grade visibility to whatever is passed in,
	 * 	or only setting it to on?  If it's whatever is passed in then
	 * 	the name doesn't portray that properly and should probably be updated
	 * 	Comment should be updated as appropriate
	 *
	 * Releases grades for an assignment
	 *
	 * @param T_F - What to set the grade visibility to
	 * @throws AssignmentNotExistException 
	 */
	public void releaseGrade(boolean T_F) throws AssignmentNotExistException{
		for(int i =0; i< sa.size(); i++){
			sa.elementAt(i).setGradeVisability(T_F);
		}
	}



	/**
	 * Toggles the comment visibility
	 *
	 * If the visibility was false, it becomes true and visa-versa
	 * @throws AssignmentNotExistException 
	 */
	public void toggleCommentVisablility() throws AssignmentNotExistException{
		if(getCommentVisabiltiy() == true){
			setCommentVisability(false);
		}else {
			setCommentVisability(true);
		}
	}

	/**
	 * sets ALL Comment visibility to true or false
	 *
	 * 
	 * @throws AssignmentNotExistException 
	 */
	public void releaseComments(boolean T_F) throws AssignmentNotExistException{
		for(int i =0; i< sa.size(); i++){	
			sa.elementAt(i).setCommentVisability(T_F);
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
		String[] students = sDbms.getStudentsInCourse(this.getCourseName());
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
	public void uploadGrades(String path) throws AssignmentNotExistException, FileNotFoundException
	{
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
	 * Gets a specific student's assignment
	 */
	public StudentAssignment getStudentAssignment( String sId )
	{
		for( int i = 0; i < sa.size(); i++ )
		{
			if( sa.elementAt(i).getId().compareTo( sId ) == 0 )
				return sa.elementAt(i);
		}
		
		return null;
	}



	/**
	 * // TODO I feel that the course and assignNum path shouldn't be needed here if the
	 * 		other methods above work; they don't need that info so why should this one?
	 * // Franky: good point, used this pointer to get parent courseNames and assignmnetNumbers
	 *
	 * Downloads all submitted files to a directory
	 *
	 * @param course - The course to download the files for
	 * @param destPath - The destination directory
	 * @param assignNum - The assignment number to download files from
	 */
	public void downloadSubmittedFiles(String destPath) throws FileNotFoundException{
		fM = new FileManager(this.getInstructorId());
		fM.downloadAllSubmittedFiles(this.getCourseName(), this.getAssignmentNumber(),destPath );
	}
}
