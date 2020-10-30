import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;


public class UserDataBase {

	static String fileSeparator = System.getProperty("file.separator");
	
	static String supUsers = fileSeparator+"Server"+fileSeparator+"src"+fileSeparator+"userList.txt";
	static File userList = new File(supUsers);
	
	
	String usernames,passwords,ids;
	private String[] usernames1;
	private String[] passwords1;
	private String[] ids1;
	static int kn;

	

	void makeFolder() {
		try {
			if(userList.createNewFile()) {
				System.out.println(userList+"File created");
			}else {
				System.out.println("File"+userList+"already exists");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void makeFile() {
		if(!userList.exists()) {
			userList.mkdirs();		
		}
	}
	

	
	void readFile() {
		try{
			FileReader fr = new FileReader(userList+"\\UserList.txt");
			System.out.println("File exists");
		} catch (FileNotFoundException ex) {
			try {
				FileWriter fw = new FileWriter(userList);
				System.out.println("File created");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void getUser(String username){
		
	}
	
	
	public static void createUser(String username, String password, int id) {
		
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(userList+"\\UserList.txt", "rw");
			for (int i = 0 ; i<kn;i++) {
				raf.readLine();
			}
			raf.writeBytes("Username:"+User.username+"\r\n");
			raf.writeBytes("Password:"+User.password+"\r\n");
			raf.writeBytes("user ID:"+User.id+"\r\n");
			raf.writeBytes("\r\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void checkUser(String username, String password, int id) {
		try {
			RandomAccessFile ruf = new RandomAccessFile(userList+"\\UserList.txt", "rw");
			String line = ruf.readLine();
			usernames = line.substring(9);
			passwords = ruf.readLine().substring(9);
			ids = ruf.readLine().substring(8);
			
			if(username.equals(username)& password.equals(password)) {
				
			} else {
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void countLines() {
		try {
			kn=1;
			RandomAccessFile rif = new RandomAccessFile(userList+"\\UserList.txt", "rw");
			for(int i=0;rif.readLine()!=null;i++) {
				kn++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void logic(String username, String password) {
		try {
			RandomAccessFile rof = new RandomAccessFile(userList+"\\UserList.txt", "rw");
			for(int i = 0 ; i<kn;i+=4) {
				String fUser = rof.readLine().substring(9);
				String fPassword = rof.readLine().substring(9);
				if(username.equals(fUser)& password.equals(fPassword)) {
					
				}else if(i==(kn-3)){
					
				}
				for(int k=1 ; k<2 ; k++) {
					rof.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void deleteUser(String username) {
		if (username.equals(username)) {
			
		}
	}
	
}

