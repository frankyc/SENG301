package Login;


import java.io.*;
import java.util.Scanner;

	/*//conventions
	 * variables = int varF
	 * Class name = User UserName
	 * constants = ALLCAPSRAGE
	 * function = varables*/
public class LoginManager 
{

	private User users;
	private boolean loggedOn = false;
	//Call this to GetUser Validation
	public User ValidateLogin(String username,String password) 
	{
		User List;
		File Logs = null;
		Scanner o_in = null;
		String newPassword = mD5Salted(password);
		users = new User(username,newPassword,0);
		
		Logs = new File("../Assignment4/src/LoginList.txt");
		
		try {
			o_in = new Scanner(Logs);
		}catch (FileNotFoundException a){
			System.err.println("/Assignment4/src/LoginList.txt was not found");
		}catch (IOException e) {
			System.err.println("File Not Found");
			e.printStackTrace();
		}	
		while(LoggedIn() == false && o_in.hasNext()){
			List = new User(o_in.next(),o_in.next(),o_in.nextInt());
			if(List.getName().compareTo(users.getName()) == 0 && List.getPassword().compareTo(users.getPassword()) == 0){
					loggedOn = true;
					users = List;
					System.out.println(users.getPermissions());
					return users;
			}
		}
		
		System.out.println("UserName or Password is Incorrect \nPress Enter to Continue");
		return null;
		
	}
	
	public void Logout(){
		loggedOn = false;
		System.out.println("\n\n\n\n");
	}


	public boolean LoggedIn() {	
		return loggedOn;
	}
	
	//MD5 algorithm got from internet
	public String mD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
	}
	
	//Using MD5 algorthm multiple times with salted methods to encrypt
	public String mD5Salted(String password){
		String newPassword = mD5(password);
		newPassword.concat(password);
		newPassword = mD5(newPassword);
		newPassword = newPassword.concat("SENG301");
		newPassword = mD5(newPassword);
		return newPassword;
		
	}

}
