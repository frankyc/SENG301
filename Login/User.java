package Login;


public class User{

	private String UserName;
	private String Password;
	private String UserClass;

	public User(){
		this("","","");
	}

	public User(String username, String password, String T) {
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
	public void setType(String T){
		UserClass = T;
	}
	public String getType(){
		return UserClass;
	}
	public String getName(){
		return UserName;
	}
	public String getPassword(){
		return Password;
	}
	

}
