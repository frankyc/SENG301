package Login;




import java.io.*;
import java.util.Scanner;

	
public class UserIdentification implements UserSecurity {

	private User users;
	private boolean loggedOn = false;

	public int Login() {
		User List;
		File Logs = null;
		Scanner o_in = null;
		
		
		Logs = new File("../Assignment4/src/LoginList.txt");
		
		try {
			o_in = new Scanner(Logs);
		}catch (FileNotFoundException a){
			System.err.println("/Assignment4/src/LoginList.txt was not found");
		}catch (IOException e) {
			System.err.println("File Not Found");
			e.printStackTrace();
		}	



InputStreamReader istream = new InputStreamReader(System.in) ;
      BufferedReader bufRead = new BufferedReader(istream) ;
      try {
		System.out.println("Enter Your Username:");
		String username = bufRead.readLine();
		System.out.println("Enter Your Password:");
		String password = bufRead.readLine();
		users = new User(username , password , "" );
} catch (IOException err) {
		System.err.println("Could not read line");
		err.printStackTrace();
}
while(LoggedIn() == false && o_in.hasNext()){
		List = new User(o_in.next(),o_in.next(),o_in.next());
		System.out.println(List.getName() + " " + List.getPassword());
		if(List.getName().compareTo(users.getName()) == 0 && List.getPassword().compareTo(users.getPassword()) == 0){
				loggedOn = true;
				users = List;
				System.out.println(users.getType());
				return 1;
		}
}
		
		System.out.println("UserName or Password is Incorrect \nPress Enter to Continue");
		return 0;
		
	}
	
	public void Logout(){
		loggedOn = false;
		System.out.println("\n\n\n\n");
	}


	public boolean LoggedIn() {	
		return loggedOn;
	}
}
