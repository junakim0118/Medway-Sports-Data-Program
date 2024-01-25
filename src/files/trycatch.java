package files;
import java.util.*;
import java.io.*;
public class trycatch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hi. please enter the name of the file here: ");
		String file = sc.nextLine();
		try {
		File csv = new File(file);
		Scanner c = new Scanner(csv);
		System.out.println(c.nextLine());
		c.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Sorry, that file name is incorrect. Please try again.");
		}
		finally { 
			
			sc.close();
		}
	}

}
