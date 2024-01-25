package files;

import java.util.Scanner;
import java.io.*;

public class playersearchversion {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(System.in);
		boolean rosterupdate = false;
		System.out.println("Hi, welcome to the Medway Highschool Sportsheet editor.");
		boolean spreadfile = false;
		do {
			System.out.println("Please enter the name of the spreadsheet file here: ");
			String file = in.nextLine();
			try {
				File csv = new File(file);
				Scanner testingfile = new Scanner(csv);
				testingfile.nextLine();
				testingfile.close();
				spreadfile = true;
			} catch (FileNotFoundException fnfe) {
				System.out.println("Sorry, that file name is incorrect. Please try again.");

			}

		} while (spreadfile == false);

		System.out.println("Do you want to update the spreadsheet with a roster today? Please answer Y or N.");
		if (in.nextLine().equals("Y")) {
			rosterupdate = true;
			boolean rosterfile = false;
			do {
				System.out.println("Please enter the name of the roster file here: ");
				String file = in.nextLine();
				try {
					File roster = new File(file);
					Scanner testingroster = new Scanner(roster);
					testingroster.nextLine();
					testingroster.close();
					rosterfile = true;
				} catch (FileNotFoundException fnfe) {
					System.out.println("Sorry, that file name is incorrect. Please try again.");

				}

			} while (rosterfile == false);
		}

		String current; // declaring current variable. current acts as a current line string that can be
						// split at each comma
		String line;
		String name;
		String find = "";
		String choice = "";
		String currentteam;
		boolean exit = false;
		File spread = new File("file.csv"); // declaring file. in future versions this will be automated
		File roster = new File("sampleRoster.txt");
		Scanner sc = new Scanner(spread); // scanner for csv file
		Scanner length = new Scanner(spread); // scanner for length of csv file
		Scanner on = new Scanner(roster);

		int x = 0; // all purpose variable
		while (length.hasNext()) { // finding length of file
			x++;
			length.nextLine();
		}

		athlete[] athlete = new athlete[x]; // initializing array of athlete objects, one for each line of the
											// file(1 per student athlete). this uses the length previously found
		x = -1; // changing x to -1, allowing it to be used as a counter for the next loop
		while (sc.hasNext()) {
			x += 1;
			athlete[x] = new athlete(); // each individual element of the array of objects must be initialized.
			current = sc.nextLine();
			athlete[x].firstName = current.split(",")[0]; // using the split method with comma as the delimiter will
															// parse each current line at the comma. this returns an
															// array of strings
			athlete[x].lastName = current.split(",")[1]; // each element the split method returns corresponds with a
															// vertical column in the spreadsheet
			athlete[x].age = current.split(",")[2]; // **NOTE: the split method will not return empty spaces, if there
													// is no value it will throw indexoutofbounds.**
			athlete[x].awards = current.split(",")[3];
			athlete[x].sports = current.split(",")[4];

		}
		if (rosterupdate == true) {
			System.out.println("The current teams supported are as follows: Ba - baseball, BB - basketball,"
					+ " C - curling, VB - volleyball, TF - track and field, S - soccer,\n"
					+ "JrH - junior hockey, FB - football, XC - cross country, H - hockey,"
					+ "W - wrestling, B - badminton, Sw - swimming, G - golf, and R - rugby. \n"
					+ "Please enter the abbreiviation for the current roster. NOTE: The program is case-sensitive."
					+ " What team is this roster for? "); // check which team this roster is for
			currentteam = in.nextLine().trim();

			while (on.hasNext()) {
				line = on.nextLine();
				for (int i = 0; i < athlete.length; i++) {
					name = athlete[i].firstName + " " + athlete[i].lastName;
					if (line.equals(name)) {
						if (athlete[i].sports.equals("j")) {
							athlete[i].sports = currentteam;

						} else {
							athlete[i].sports = athlete[i].sports + " . " + currentteam;

						}
					}
				}
				System.out.println(
						"The roster has been added to the file on this computer. Do not close this program until prompted to prevent progress loss.");
			}

		}
		
		boolean makeChanges;
		System.out.println("Would you like to make changes to a specific player? Answer with Y or N");
		if (in.nextLine().equals("Y")) {
			makeChanges = true;
			System.out.println("What is their name?");
			find = in.nextLine();
		} else {
			makeChanges = false;
			exit = true;
		}
		if (makeChanges == true) {
			for (int i = 0; i < athlete.length; i++) {
				name = athlete[i].firstName + " " + athlete[i].lastName;
				if (find.equals(name)) {
					System.out.println(
							"What would you like to add or change? Enter the letter that represents your choice. G: Grade, S: Sports, A: Awards");
					choice = in.nextLine();
					if (choice.equals("G")) {
						System.out.println("What is their new grade?");
						athlete[i].age = in.nextLine();
						System.out.println(athlete[i].age);
						exit = true;
					} else if (choice.equals("S")) {
						System.out.println("What sport do you need to add to this player?");
						athlete[i].sports = athlete[i].sports + " . " + in.nextLine();
						exit = true;
					} else if (choice.equals("A")) {
						System.out.println("What award did this player win?");
						athlete[i].awards = athlete[i].awards + " . " + in.nextLine();
						exit = true;
					}
				}
			}
		}
		if (exit == true) {
			FileOutputStream fileHandle; // creates file object
			PrintWriter fileOut; // creates print writer object
			fileHandle = new FileOutputStream("fakefile.csv"); // streams under defined filename to output
			fileOut = new PrintWriter(fileHandle); // creates object to write to file
			for (int i = 0; i < athlete.length; i++) {
				fileOut.println(athlete[i].firstName + "," + athlete[i].lastName + "," + athlete[i].age + ","
						+ athlete[i].awards + "," + athlete[i].sports);
			}
			System.out.println("File Successfully Written.");
			fileOut.close();
			System.exit(0);
		}
		sc.close();
		length.close();
		on.close();
		in.close();

	}

}
