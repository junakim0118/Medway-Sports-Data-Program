package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class finalversion {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(System.in);
		boolean rosterupdate = false;

		String line;
		String name;
		String find = "";
		String choice = "";
		String currentteam;
		boolean exit = false;
		
		File csv = null;
		Scanner sc = null; // scanner for csv file
		Scanner length = null; // scanner for length of csv file

		File roster = null;
		Scanner testingroster = null;
		
		boolean spreadfile = false;
		do {
			System.out.println("Please enter the name of the spreadsheet file here: (ex. file.csv)");
			String file = in.nextLine();
			try {
				csv = new File(file);
				Scanner testingfile = new Scanner(csv);
				length = new Scanner(csv);
				sc = new Scanner(csv);
				testingfile.nextLine();
				testingfile.close();
				spreadfile = true;
			} catch (FileNotFoundException fnfe) {
				System.out.println("Sorry, that file name is incorrect. Please try again.");
			}

		} while (spreadfile == false);
		String current; // declaring current variable. current acts as a current line string that can be
		// split at each comma

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
		do {
			int options = 0;
			spreadfile = true;
			System.out.println("What do you want to do today? Please select an option. ");
			System.out.println(
					"1. Update the spreadsheet with a roster 2. Search for a player 3. Check Athletic M eligibilities");
			options = Integer.parseInt(in.nextLine());

			// 1. Update the spreadsheet with a roster
			if (options == 1) {
				rosterupdate = true;
				boolean rosterfile = false; // do you need this?
				do {
					System.out.println("Please make sure to make a text file with the names in the team. ");
					System.out.println("Please enter the name of the roster file here: (ex. file.txt)");
					String file = in.nextLine();
					try {
						roster = new File(file);
						testingroster = new Scanner(roster);
						// testingroster.nextLine();
						// testingroster.close();
						rosterfile = true;
					} catch (FileNotFoundException fnfe) {
						System.out.println("Sorry, that file name is incorrect. Please try again.");
					}

				} while (rosterfile == false);

				if (rosterupdate == true) {
					System.out.println("The current teams supported are as follows: Ba - baseball, BB - basketball,"
							+ " C - curling, VB - volleyball, TF - track and field, S - soccer,\n"
							+ "JrH - junior hockey, FB - football, XC - cross country, H - hockey,"
							+ "W - wrestling, B - badminton, Sw - swimming, G - golf, and R - rugby. \n"
							+ "Please enter the abbreiviation for the current roster. NOTE: The program is case-sensitive."
							+ " What team is this roster for? "); // check which team this roster is for
					currentteam = in.nextLine().trim();

					while (testingroster.hasNext()) {
						line = testingroster.nextLine().trim();
						for (int i = 0; i < athlete.length; i++) {
							name = athlete[i].firstName + " " + athlete[i].lastName;
							if (line.equals(name)) {
								if (athlete[i].sports.equals("j")) {
									athlete[i].sports = currentteam;
									athlete[i].points++;
									System.out.println(athlete[i].sports);

								} else {
									athlete[i].sports = athlete[i].sports + " . " + currentteam;
									athlete[i].points++;
									System.out.println(athlete[i].sports);
								}
							}
						}
					}
					System.out.println("The roster has been added to the file on this computer.");
				}
				exit = true;
			} // 2. Search for a player
			else if (options == 2) {
				// makeChanges = true;
				System.out.println("What is their name?");
				find = in.nextLine();
				for (int i = 0; i < athlete.length; i++) {
					name = athlete[i].firstName + " " + athlete[i].lastName;
					if (find.equals(name)) {
						System.out.println(
								"What would you like to add or change? Enter the letter that represents your choice. G: Grade, S: Sports, A: Awards");
						choice = in.nextLine();
						if (choice.equals("G")) {
							System.out.println("What is their new grade?");
							athlete[i].age = in.nextLine();
							// System.out.println(athlete[i].age);
							exit = true;
						} else if (choice.equals("S")) {
							System.out.println("What sport do you need to add to this player?");
							athlete[i].sports = athlete[i].sports + " . " + in.nextLine();
							athlete[i].points++;
							exit = true;
						} else if (choice.equals("A")) {
							System.out.println("What award did this player win?");
							athlete[i].awards = athlete[i].awards + " . " + in.nextLine();
							exit = true;
						}
					}
				}

			} // 3. Check Athletic M eligibilities
			else if (options == 3) {
				System.out.println("A list of players who will win the M award: ");
				for (int i = 1; i < athlete.length; i++) {
					if (athlete[i].sports.equals("j")) {
						athlete[i].points = 0;
					} else {
						String str = athlete[i].sports;
						String findStr = " . ";
						athlete[i].points = str.split(findStr, -1).length - 1;

						if (athlete[i].age.equals("12") && athlete[i].points >= 1) {
							System.out.println(athlete[i].firstName + " " + athlete[i].lastName);
						}
					}
				}
				System.out.println(" ");
				exit = true;
			} else {
				System.out.println("Please enter a valid option.");
				spreadfile = false;

			}
		} while (spreadfile == false);

		
		
		
		
		if (exit == true) {
			FileOutputStream fileHandle; // creates file object
			PrintWriter fileOut; // creates print writer object
			fileHandle = new FileOutputStream(csv); // streams under defined filename to output
			fileOut = new PrintWriter(fileHandle); // creates object to write to file
			for (int i = 0; i < athlete.length; i++) {
				fileOut.println(athlete[i].firstName + "," + athlete[i].lastName + "," + athlete[i].age + ","
						+ athlete[i].awards + "," + athlete[i].sports);
			}
			System.out.println("File Successfully Updated.");
			System.out.println("To export the file, make a new google sheet and import the file into the new sheet");
			fileOut.close();
			System.exit(0);
		}
		// testingfile.close();
		// length.close();
		// on.close();
		in.close();


	}

}
