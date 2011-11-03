package Management;


import java.io.*;
import java.util.Scanner;

import Users.*;

	/*//conventions
	 * variables = int varF
	 * Class name = User UserName
	 * constants = ALLCAPSRAGE
	 * function = varables*/
public class LoginManager 
{
	//Path Name can be changed anytime.
	private static final String LOGINPATH = "../Assignment4/src/LoginListEncrypted.txt";

	private User users;
	private boolean loggedOn = false;
	//Call this to GetUser Validation
	public User ValidateLogin(String username,String password) 
	{
		File Logs = null;
		Scanner o_in = null;
		String saltedPassword = mD5Salted(password),dBUsername,dBPassword;
		int dBPermission;
		
		Logs = new File(LOGINPATH);
		
		try {
			o_in = new Scanner(Logs);
		}catch (FileNotFoundException a){
			System.err.println(LOGINPATH +" was not found");
		}catch (IOException e) {
			System.err.println("File Not Found");
			e.printStackTrace();
		}	
		while(LoggedIn() == false && o_in.hasNext()){
			dBUsername = o_in.next();
			dBPassword = o_in.next();
			dBPermission = o_in.nextInt();
			if(dBUsername.compareTo(username) == 0 && dBPassword.compareTo(saltedPassword) == 0){
					loggedOn = true;
					getUser(dBPermission);
					users.setUsername(username);
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
	
	//Using MD5 algorithm multiple times with salted methods to encrypt
	public String mD5Salted(String password){
		String newPassword = mD5(password);
		newPassword.concat(password);
		newPassword = mD5(newPassword);
		newPassword = newPassword.concat("SENG301");
		newPassword = mD5(newPassword);
		return newPassword;
		
	}
	
	private void getUser(int permission){
		switch(permission){
		case 1: users = new Instructor();
		break;
		case 2: users = new TA();
		break;
		case 3: users = new Student();
		break;
		default: users = null;
		}
		
	}

}