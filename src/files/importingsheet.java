//This version of final project is focused on importing a google sheet. 
//It uses the athelete.java object class to store data. 
//NOTE**"Athelete" is misspelled here, should be fixed in version two**

package files;

import java.util.Scanner;
import java.io.*;

public class importingsheet {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in); // initializing scanner
		String current; // declaring current variable. current acts as a current line string that can be
						// split at each comma

		File spread = new File("file.csv"); // declaring file. in future versions this will be automated

		Scanner sc = new Scanner(spread); // scanner for csv file
		Scanner length = new Scanner(spread); // scanner for length of csv file
		int x = 0; // all purpose variable
		while (length.hasNext()) { // finding length of file
			x++;
			length.nextLine();
		}

		athlete[] athelete = new athlete[x]; // initializing array of athelete objects, one for each line of the
												// file(1 per student athelete). this uses the length previously found
		x = -1; // changing x to -1, allowing it to be used as a counter for the next loop
		while (sc.hasNext()) {
			x += 1;
			athelete[x] = new athlete(); // each individual element of the array of objects must be initialized.
			current = sc.nextLine();
			athelete[x].firstName = current.split(",")[0]; // using the split method with comma as the delimiter will
															// parse each current line at the comma. this returns an
															// array of strings
			athelete[x].lastName = current.split(",")[1]; // each element the split method returns corresponds with a
															// vertical column in the spreadsheet
			athelete[x].age = current.split(",")[2]; // **NOTE: the split method will not return empty spaces, if there
														// is no value it will throw indexoutofbounds.**
			athelete[x].awards = current.split(",")[3];
			athelete[x].sports = current.split(",")[4];
		}

	}

}
