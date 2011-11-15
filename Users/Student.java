/*
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */
package Users;

public class Student extends User{
	private String TA;
	private String instructor;
	
	public Student(){
		super();
		super.setPermission(STUDENT);
		setTA("");
	}
	public Student(String username, String[] course,String ta,String instr){
		super(username,course,STUDENT);
		setTA(ta);
		setInstructor(instr);
	}
	public void setTA(String tA) {
		TA = tA;
	}
	public String getTA() {
		return TA;
	}
	public void setInstructor(String instr){
		instructor = instr;
	}
	public String getInstructor() {
		return instructor;
	}
}
