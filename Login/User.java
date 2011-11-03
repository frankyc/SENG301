package Login;


public class User{

	private String UserName;
	private String Password;
	private int Permissions;
	private static final int INSTRUCTOR = 1;
	private static final int TA = 2;
	private static final int STUDENT = 3;
	public User(){
		this("","",0);
	}

	public User(String username, String password, int T) {
		setUsername(username);
		setPassword(password);
		setType(T);
	}
	public void setUsername(String user){
		UserName = user;
	}
	public void setPassword(String pass){
		Password = pass;
	}
	public void setType(int T){
		Permissions = T;
	}
	public int getPermissions(){
		return Permissions;
	}
	public String getName(){
		return UserName;
	}
	public String getPassword(){
		return Password;
	}
	

}
