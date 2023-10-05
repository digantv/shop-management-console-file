package user_management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import user_management.UserOptions;

public class UserManagement {
	static ArrayList<User> al = new ArrayList<>();
	
	static {
		try {
			loadDataFromFileToArrayList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void userManagement() throws IOException {
//		loadDataFromFileToArrayList();
		Scanner scanner = new Scanner(System.in);
		boolean canIKeepRunningTheProgram = true;

		while (canIKeepRunningTheProgram == true) {

			System.out.println("****Welcome To User Management****");
			System.out.println("\n");
			System.out.println("What Do You Want To Do?");
			System.out.println("1.Add User");
			System.out.println("2.Edit User");
			System.out.println("3.Delete User");
			System.out.println("4.Search User");
			System.out.println("5.Quit");

			int optionSelectedByUser = scanner.nextInt();

			if (optionSelectedByUser == UserOptions.QUIT) {
				File file = new File(
						"C:\\Users\\yash\\eclipse-workspace\\ShopManagement\\src\\user_management\\Users.csv");
				FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);

				for (User user : al) {
					bw.write(user.userName + "," + user.loginName + "," + user.password + "," + user.userRole + "\n");
				}
				bw.close();
				fw.close();
				file = null;
				System.out.println("Program Closed!!!");
				canIKeepRunningTheProgram = false;

			} else if (optionSelectedByUser == UserOptions.ADD_USER) {

				addUser();
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.EDIT_USER) {

				System.out.println("Enter User Name To Edit:");
				scanner.nextLine();
				String en = scanner.nextLine();
				editUser(en);
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.SEARCH_USER) {

				System.out.println("Enter User Name to Search:");
				scanner.nextLine();
				String sn = scanner.nextLine();
				searchUser(sn);
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.DELETE_USER) {

				System.out.println("Enter User Name to Delete :");
				scanner.nextLine();
				String dn = scanner.nextLine();
				deleteUser(dn);
				System.out.println("\n");
			}
		}
	}

	public static void addUser() {

		Scanner scanner = new Scanner(System.in);
		User userObject = new User();

		System.out.print("User Name: ");
		userObject.userName = scanner.nextLine();

		System.out.print("Login Name: ");
		userObject.loginName = scanner.nextLine();

		System.out.print("Password: ");
		userObject.password = scanner.nextLine();

		System.out.print("Confirm Password: ");
		userObject.confirmPassword = scanner.nextLine();

		System.out.print("User Role: ");
		userObject.userRole = scanner.nextLine();
		al.add(userObject);
		System.out.println("User Added Successfully!!");

	}

	public static void editUser(String userName) {

		for (User user : al) {

			if (user.userName.equalsIgnoreCase(userName)) {
				Scanner scanner = new Scanner(System.in);

				System.out.println("Editing User : " + user.userName);

				System.out.println("New User Name: ");
				user.userName = scanner.nextLine();

				System.out.println("New Login Name: ");
				user.loginName = scanner.nextLine();

				System.out.println("New Password: ");
				user.password = scanner.nextLine();

				System.out.println("New Confirm Password: ");
				user.confirmPassword = scanner.nextLine();

				System.out.println("New User Role: ");
				user.userRole = scanner.nextLine();

				System.out.println("User Information Updated");
				return;

			}
		}
		System.out.println("User Not Found!!!");
	}

	public static void deleteUser(String userName) {

		Iterator<User> itr = al.iterator();

		while (itr.hasNext()) {
			User user = itr.next();
			if (user.userName.equalsIgnoreCase(userName)) {
				itr.remove();
				System.out.println("User " + user.userName + " has been Deleted!!!");
				break;
			}
		}
		System.out.println("User Not Found!!!");
	}

	public static void searchUser(String userName) {

		for (User user : al) {
			if (user.userName.equalsIgnoreCase(userName)) {
				System.out.println("User Name: " + user.userName);
				System.out.println("Login Name: " + user.loginName);
				System.out.println("Password: " + user.password);
				System.out.println("User Role: " + user.userRole);
				return;
			}
		}
		System.out.println("User not Found.");
	}

	public static void loadDataFromFileToArrayList() throws IOException {

		File file = new File("C:\\Users\\yash\\eclipse-workspace\\ShopManagement\\src\\user_management\\Users.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";

		while ((line=br.readLine()) != null) {
			User user = new User();
			String[] userData = line.split(",");

			if (userData.length > 3) {
				
				user.userName = userData[0];
				user.loginName = userData[1];
				user.password = userData[2];
				user.userRole = userData[3];

				al.add(user);
			}
		}
		br.close();
		fr.close();
		file = null;
	}

//	public static boolean validateUserAndPassword(String loginName1, String password1) {
//
//		Iterator<User> userIterator = al.iterator();
//		while (userIterator.hasNext()) {
//			User user =userIterator.next();
//			if (user.loginName.equalsIgnoreCase(loginName1) && user.password.equalsIgnoreCase(password1)) {
//				return true;
//			}
//		}
//		return false;
//
//	}
	public static boolean validateUserandPassword(String loginName ,String password)
	{
		 Iterator<User> userIterator = al.iterator();
		 while(userIterator.hasNext())
		 {
			 User u1 = userIterator.next();
			 if(u1.loginName.equalsIgnoreCase(loginName)&&u1.password.equalsIgnoreCase(password))
			 {
				 return true;
			 }
		 }
		 return false;
	 }
}
