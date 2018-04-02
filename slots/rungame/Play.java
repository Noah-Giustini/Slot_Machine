package slots.rungame;

import java.util.Scanner;
import java.io.*;
import slots.guigame.*;

/*
 * @author	Noah Guistini 
 * @author      Erin Brintnell
 * @author	Brian Kehrig
 *  
 * @version     unknown
 *       
 * The Play class is used to allow the player to choose between the gui game and the text game
 *
 *      This class has one method which is the main method which is used to choose between the GUI and text version of the game
 */

public class Play {

	/**	the method, main runs through a switch statement that alows the user to choose between the text version and 
	*	the GUI version of the game. 
	*
	*@param	args - a string array of launch arguments
	*
	*/
        public static void main(String[] args){
		
                Scanner sc = new Scanner(System.in);
                int userChoice = 0;
                while(userChoice == 0){
			System.out.println("Would you like to enable GUI for this game? (Yes/No)");
			String mmchoice = sc.nextLine();
			switch (mmchoice.toUpperCase()) {
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
			default: System.out.println("Invalid selection (yes/no/quit)");
				break;
			} 
		}
		if (userChoice == 2){
			//launch the text version of the game
			TextGame.main(new String[0]);
		}
		if (userChoice == 1){
			//launch the GUI version of the game
			GUI.main(new String[0]);
		}
	}
}
