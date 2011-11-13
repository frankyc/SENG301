package Management;
import Users.*;

public class StudentAssignment extends StudentAccess{

	public void assignGrades(String gr){
		grade = gr;
	}
	public void addComment(String comm){
		comment = comm;
	}
	public void editGrade(String newGr){
		System.out.println("Confirm?y/n");
		//if yes
		grade = newGr;
		//else System.out.println("No changes");
	}
}
