/*
 * SENG 301: Assignment 4
 * Franky Cheung; Colin Williams
 */

package Users;


public abstract class User{

	private String userName;
	private String[] course;
	private int permissions;
	public static final int INSTRUCTOR = 1;
	public static final int TA = 2;
	public static final int STUDENT = 3;
	public User(){
		this("",null,0);
	}

	public User(String username, String[] Course, int T) {
		setUsername(username);
		setCourse(Course);
		setPermission(T);
	}
	public void setUsername(String user){
		userName = user;
	}
	public void setCourse(String[] cour){
		course = cour;
	}
	public void setPermission(int T){
		permissions = T;
	}
	public int getPermissions(){
		return permissions;
	}
	public String getName(){
		return userName;
	}
	public String[] getCourse(){
		return course;
	}
	

}
