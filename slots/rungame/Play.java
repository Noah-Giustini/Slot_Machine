package slots.rungame;

import java.util.Scanner;		//import the scanner utility
import java.io.*;
import slots.gamelogic.*;
import slots.guigame.*;


/*
*	The play class is the main game which will be played. it incorporates the Game and Reel classes 
*	it has no instance variables and its only method is the main method in which the game runs.
*/
public class Play {
        public static void main(String[] args){		//main method (this is where the magic happens ;) 
                Reel leftReel = new Reel(0);		//create the left reel
                Reel middleReel = new Reel(1);		//create the middle reel
                Reel rightReel = new Reel(2);		//create the right reel

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
			int gameChoice = 0;			//variable to keep track of the user's choice of game
		    	while(gameChoice == 0){			//while loop that checks if the user's input is valid
				System.out.println("Would you like to play a saved game? (Yes/No)");
				String choice = sc.nextLine(); //take user input as a string
				switch (choice.toUpperCase()) {//switch statement to go through all posibilities
					case "YES":  gameChoice = 1;
						break;
					case "Y":  gameChoice = 1;
						break;
					case "NO":  gameChoice = 2;
						break;
					case "N":  gameChoice = 2;
						break;
					default: System.out.println("Invalid selection (yes/no)");
						break;
				}
			}
			
			
			Game game;
			if (gameChoice == 1){
				game = new SavedGame(leftReel,middleReel,rightReel);	//create a new instance of SavedGame which is constructed with the reels we created
			}
			else {
				game = new Game(leftReel,middleReel,rightReel);	//create a new instance of SavedGame which is constructed with the reels we created
			}
			
			game.rollAll();						//roll all reels so there will be data to display
			game.showGame();					//show the reels
			if(userChoice == 5){
				game.collectWinnings(99900);
			}
			while(game.getPlayerBalance()>0){			//while the player has money
				int saveChoice = 0;			//variable to keep track of the user's choice of game
			    	while(saveChoice == 0){			//while loop that checks if the user's input is valid
					System.out.println("Would you like to save? (Yes/No)");
					String choice = sc.nextLine(); //take user input as a string
					switch (choice.toUpperCase()) {//switch statement to go through all posibilities
					case "YES":  saveChoice = 1;
						break;
					case "Y":  saveChoice = 1;
						break;
					case "NO":  saveChoice = 2;
						break;
					case "N":  saveChoice = 2;
						break;
					default: System.out.println("Invalid selection (yes/no)");
						break;
					}
				}

				if (saveChoice == 1){
					game.saveGame();
				}
				System.out.println("You have "+game.getPlayerBalance()+"$, how much would you like to bet? (10/20/30)");
				int userBet = 0;				//variable to keep track of the users bet
				int didWin = 0;					//variable to see if the user won
				while(userBet == 0){				//while the user has not bet a valid amount
					String betVal = sc.nextLine();		//take in user input
					switch (betVal) {			//switch statement to go through the possibilities of the user's bet
					case "10":  userBet = 1;
						break;
					case "20":  userBet = 2;
						break;
					case "30":  userBet = 3;
						break;
					default: System.out.println("Invalid selection (10/20/30)");
						break;
					}
				}
				int holdChoice = 0;
			    	while(holdChoice == 0){	
					System.out.println("Would you like to hold the middle reel for $10?");
					String holdchoice = sc.nextLine();
					switch (holdchoice.toUpperCase()) {
					case "YES":  holdChoice = 1;
						break;
					case "Y":  holdChoice = 1;
						break;
					case "NO":  holdChoice = 2;
						break;
					case "N":  holdChoice = 2;
						break;
					default: System.out.println("Invalid selection (yes/no)");
						break;
					}
				}

				if ((game.getPlayerBalance() - (userBet*10)) >=0){	//check to make sure the bet wont give the player a negative balace
					if(holdChoice == 1){
						if((game.getPlayerBalance() - (userBet*10) - 10) >=0){
							game.mainGame(userBet,10);
						}
						else{
							System.out.println("You did not have enough to hold the reel and bet that amount");
							game.mainGame(userBet,0);
						}
					}
					if (holdChoice == 2){
						game.mainGame(userBet,0);
					}
				}
				
				
			}
			System.out.println("You have no more money. oops! ");		//tell the player they have no more money (need to re-evaluate life choices)
		}
		if (userChoice == 1){
			GUI.main(new String[0]);
		}
	}
}
