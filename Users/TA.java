/*
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Users;

public class TA extends User{
	private String instructor;
	
	public TA(){
		super();
		super.setPermission(TA);
		setInstructor("");
	}
	public TA(String username, String course,String instr){
		super(username,course,TA);
		setInstructor(instr);
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getInstructor() {
		return instructor;
	}
}
