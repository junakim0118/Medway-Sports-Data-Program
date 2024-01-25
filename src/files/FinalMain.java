package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FinalMain {

	public static void main(String[] args) throws FileNotFoundException {

		String filename; // This variable acts as the verified filename of the CSV file
		String rostername; // This variable acts as the verified filename of the roster file
		// String current; // This variable will act as the current line of the CSV file
		// during the
		// scanning process
		String currentteam; // This variable represents the current team of the roster being updated.
		String line; // This variable represents the current line of the on scanner, used when
						// updating roster
		String name; // Used to compare players when updating roster
		String find; // Player name that is being searched
		String choice;
		Scanner in = new Scanner(System.in);
		int counter = -1; // counts iterations of while loop to allow for array element access
		int options; // int represents users menu selection

//------------------------Getting CSV file from user-----------------------------------

		System.out.println("Hi, welcome to the Medway Highschool Sportsheet editor.");
		System.out.println(
				"Before running the program, please make sure to download the spreadsheet as a csv file and add it to the folder that contains this program.");

		do {
			System.out.println("Please enter the name of the spreadsheet file here: ");
			filename = in.nextLine();

		} while (FinalMain.testFile(filename) == false);
		File csv = new File(filename);
//--------------------------------------------------------------------------------------

//----------------------Finding length of CSV file--------------------------------------		
		int csvlength = 0; // all purpose variable

		Scanner length = new Scanner(csv);

		while (length.hasNext()) { // finding length of file
			csvlength++;
			length.nextLine();
		}

//--------------------------------------------------------------------------------------

//-------------------Adding CSV file to athlete object------------------------------------
		Scanner sc = new Scanner(csv); // scanner for csv file
		athlete[] athlete = new athlete[csvlength]; // initializing array of athlete objects, one for each line of the
		// file(1 per student athlete). this uses the length previously found
		String current = " ";
		while (sc.hasNext()) {
			current = sc.nextLine();
			counter++;

			athlete[counter] = new athlete(); // each individual element of the array of objects must be initialized.

			athlete[counter].firstName = current.split(",")[0]; // using the split method with comma as the delimiter
			// will
			// parse each current line at the comma. this returns an
			// array of strings

			athlete[counter].lastName = current.split(",")[1]; // each element the split method returns corresponds with
			// a
			// vertical column in the spreadsheet
			athlete[counter].age = current.split(",")[2]; // **NOTE: the split method will not return empty spaces, if
															// there
			// is no value it will throw indexoutofbounds.**
			athlete[counter].awards = current.split(",")[3];
			athlete[counter].sports = current.split(",")[4];

		}

//--------------------------------------------------------------------------------------		

//----------------Displaying menu of options and getting choice-------------------------
		while (true) {
			System.out.println("What do you want to do today? Please select an option. ");
			System.out.println(
					"1. Update the spreadsheet with a roster 2. Manual update 3. Check Athletic M eligibilities 4. Exit the program");
			options = Integer.parseInt(in.nextLine());

//--------------------------------------------------------------------------------------						

//-----------------------------Updating roster-----------------------------------------

			if (options == 1) {

				do {
					System.out.println("Please enter the name of the roster file here: ");
					rostername = in.nextLine();

				} while (FinalMain.testFile(rostername) == false);
				File roster = new File(rostername);
				Scanner on = new Scanner(roster);

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
						System.out.println(line + "  " + name);

						if (line.equals(name)) {
							System.out.println(line + "  " + name);
							if (athlete[i].sports.equals("j")) {
								athlete[i].sports = currentteam;

							} else {
								athlete[i].sports = athlete[i].sports + " . " + currentteam;

							}
						}
					}
				}
				System.out.println(
						"The roster has been added to the file on this computer. Do not close this program until prompted to prevent progress loss.");
			}

//--------------------------------------------------------------------------------------		

//-------------------------------Searching and updating a player------------------------	

			else if (options == 2) {

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
							System.out.println(athlete[i].age);

						} else if (choice.equals("S")) {
							System.out.println("What sport do you need to add to this player?");
							athlete[i].sports = athlete[i].sports + " . " + in.nextLine();

						} else if (choice.equals("A")) {
							System.out.println("What award did this player win?");
							athlete[i].awards = athlete[i].awards + " . " + in.nextLine();

						} else {
							System.out.println("Wrong choice. Try again. Make sure to use an upper case letter");
							choice = in.nextLine();
						}
					}
				}

			}

//--------------------------------------------------------------------------------------		

//-----------------------------Check for athletic M winners-----------------------------		

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

			}

//--------------------------------------------------------------------------------------	

//-------------------------------Exiting the program------------------------------------		

			else if (options == 4) {
				FileOutputStream fileHandle; // creates file object
				PrintWriter fileOut; // creates print writer object
				fileHandle = new FileOutputStream(filename); // streams under defined filename to output
				fileOut = new PrintWriter(fileHandle); // creates object to write to file
				for (int i = 0; i < athlete.length; i++) {
					fileOut.println(athlete[i].firstName + "," + athlete[i].lastName + "," + athlete[i].age + ","
							+ athlete[i].awards + "," + athlete[i].sports);
				}
				System.out.println("File Successfully Updated.");
				System.out
						.println("To export the file, make a new google sheet and import the file into the new sheet");
				fileOut.close();
				length.close();
				in.close();
				System.exit(0);
			}

//--------------------------------------------------------------------------------------		

//----------------------------------Invalid input---------------------------------------		

			else {
				System.out.println("Please enter a valid option.");
			}

//=======================================================================================		
		} // end of while loop
	}// end of main

	/**
	 * This method tests if an user given filename corresponds to a file.
	 * 
	 * @param filename The name of the file being checked for
	 * @return fileworks Boolean true if file is found, false if file is not found
	 */
	public static boolean testFile(String filename) {
		File testfile;
		boolean fileworks = false;
		try {
			testfile = new File(filename);
			Scanner testingfile = new Scanner(testfile);
			testingfile.nextLine();
			testingfile.close();
			fileworks = true;
		} catch (FileNotFoundException fnfe) {
			System.out.println("Sorry, the file under the name '" + filename + "' was not found. Please try again.");
		}
		return fileworks;
	}

}
