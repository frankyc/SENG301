/*
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */
package Users;

public class Instructor extends User{

	public Instructor(){
		super();
		super.setPermission(INSTRUCTOR);
	}
	public Instructor(String username, String Course,String Instr){
		super(username,Course,TA);
	}
}
