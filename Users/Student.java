package Users;

public class Student extends User{
	private String TA;
	
	public Student(){
		super();
		super.setPermission(STUDENT);
		setTA("");
	}
	public Student(String username, String course,String ta){
		super(username,course,STUDENT);
		setTA(ta);
	}
	public void setTA(String tA) {
		TA = tA;
	}
	public String getTA() {
		return TA;
	}
}
