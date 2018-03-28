package slots.rungame;

import java.util.Scanner;
import java.io.*;
import slots.guigame.*;


/*
*	The play class is the main game which will be played. it incorporates the Game and Reel classes 
*	it has no instance variables and its only method is the main method in which the game runs.
*/
public class Play {
        public static void main(String[] args){		//main method (this is where the magic happens ;) 
		

                Scanner sc = new Scanner(System.in);	//create an instance of Scanner
                int userChoice = 0;			//variable to keep track of the user's choice
                while(userChoice == 0){			//while loop that checks if the user's input is valid
			System.out.println("Would you like to enable GUI for this game? (Yes/No)");
			String mmchoice = sc.nextLine(); //take user input as a string
			switch (mmchoice.toUpperCase()) {//switch statement to go through all posibilities
			case "YES":  userChoice = 1;
				break;
			case "Y":  userChoice = 1;
				break;
			case "NO":  userChoice = 2;
				break;
			case "N":  userChoice = 2;
				break;
			case "QUIT":  userChoice =3;
				break;
			case "Q":  userChoice = 3;
				break;
			case "YES I AM RICH": userChoice = 4;
				break;
			case "NO I AM RICH": userChoice = 5;
				break;
			default: System.out.println("Invalid selection (yes/no/quit)");
				break;
			} 
		}
		if (userChoice == 2 | userChoice == 5){		//if the user choses the option with no gui
			TextGame.main(new String[0]);
		}
		if (userChoice == 1){
			GUI.main(new String[0]);
		}
	}
}
