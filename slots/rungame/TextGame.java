package slots.rungame;

import java.util.Scanner;
import slots.gamelogic.*;
import slots.guigame.*;
import slots.exception.*;
import java.io.*;

public class TextGame{
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                Reel leftReel = null;
		Reel middleReel = null;
		Reel rightReel = null;
		try{
			leftReel = new Reel(0);		//create the left reel
			middleReel = new Reel(1);		//create the middle reel
			rightReel = new Reel(2);		//create the right reel
		}
		catch (ReelException e){
			System.out.println("There seems to be a problem with the reels of the slot machine. The program" +
					   "will now close... Try restarting it, if the problem persists please contact us");
			System.exit(0);
		}

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
			try{
                       		game = new SavedGame(leftReel,middleReel,rightReel);
			}
			catch (FileNotFoundException e){
				System.out.println("I'm sorry I can't find a saved file, loading new game...");
				game = new Game(leftReel, middleReel,rightReel);
			}
			catch (IOException e){
				System.out.println("An error has occured in loading the saved game, loading new game...");
			}
			catch (NegativeBalanceException e){
				System.out.println("The game you are trying to load has a negative balance, loading a new game...");
			}
			catch (ReelException e){
				System.out.println("The game you are trying to load has invalid reels, loading a new game...");
			}
                }
                else {
                        game = new Game(leftReel,middleReel,rightReel);
                }
                        
                game.rollAll();						//roll all reels so there will be data to display
                game.showGame();					//show the reels
                
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
}
