package slots.rungame;

import java.util.Scanner;
import slots.gamelogic.*;
import slots.guigame.*;
import slots.exception.*;
import java.io.*;

/*
 * @author	Noah Guistini 
 * @author      Erin Brintnell
 * @author	Brian Kehrig
 *  
 * @version     unknown
 *       
 * The TextGame class allows players to play the text version of the slot machine. It encorporates the Reel, Game and ReelException classes
 * It has one method which is the main method
 *
 */

public class TextGame {
    /** The method main is used to put all the functions of the text based game into action. 
     * it creates new instances of the Reel class for leftReel, middleReel, and rightReel and an
     * instance of the Scanner class for sc. Then a switch statement is used to see if the user would like to play a saved game
     * and after that a new instance of the Game class is made. This method rolls the reels and shows them and uses
     * switch statements to prompt the user to save the game, bet an amount and hold the middle reel. if the player runs out
     * of money the console will tell the player they have no more money and will not allow them to play anymore.
     *
     *@param args - String array of launch arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Reel leftReel = null;
        Reel middleReel = null;
        Reel rightReel = null;
        
        try {
            leftReel = new Reel(0);
            middleReel = new Reel(1);
            rightReel = new Reel(2);
        } catch (ReelException e) {
            System.out.println("There seems to be a problem with the reels of the slot machine. The program" +
                "will now close... Try restarting it, if the problem persists please contact us");
            System.exit(0);
        }

        int gameChoice = 0;
        while (gameChoice == 0) {
            System.out.println("Would you like to play a saved game? (Yes/No)");
            String choice = sc.nextLine();
            switch (choice.toUpperCase()) {
                case "YES":
                    gameChoice = 1;
                    break;
                case "Y":
                    gameChoice = 1;
                    break;
                case "NO":
                    gameChoice = 2;
                    break;
                case "N":
                    gameChoice = 2;
                    break;
                default:
                    System.out.println("Invalid selection (yes/no)");
                    break;
            }
        }

        Game game = null;
        if (gameChoice == 1) {
            //This exception handeling ensures that a saved game can actually be played. If the saved game cannot be played a new game will run
            try {   
                game = new SavedGame(leftReel, middleReel, rightReel);
            } catch (FileNotFoundException e) {
                System.out.println("I'm sorry I can't find a saved file, loading new game...");
                game = new Game(leftReel, middleReel, rightReel);
            } catch (IOException e) {
                System.out.println("An error has occured in loading the saved game, loading new game...");
            } catch (NegativeBalanceException e) {
                System.out.println("The game you are trying to load has a negative balance, loading a new game...");
            } catch (ReelException e) {
                System.out.println("The game you are trying to load has invalid reels, loading a new game...");
            }
        } else {
            game = new Game(leftReel, middleReel, rightReel);
        }

        game.rollAll();
        game.showGame();

        while (game.getPlayerBalance() > 0) {
            int saveChoice = 0;
            
            while (saveChoice == 0) {
                System.out.println("Would you like to save? (Yes/No)");
                String choice = sc.nextLine();
                switch (choice.toUpperCase()) {
                    case "YES":
                        saveChoice = 1;
                        break;
                    case "Y":
                        saveChoice = 1;
                        break;
                    case "NO":
                        saveChoice = 2;
                        break;
                    case "N":
                        saveChoice = 2;
                        break;
                    default:
                        System.out.println("Invalid selection (yes/no)");
                        break;
                }
            }

            if (saveChoice == 1) 
                try {
                    game.saveGame();
                } catch (IOException e) {
                    System.out.println("I'm sorry the game could not be saved);
                }
            }
            
            System.out.println("You have " + game.getPlayerBalance() + "$, how much would you like to bet? (10/20/30)");
            int userBet = 0;
            int didWin = 0;
            while (userBet == 0) {
                String betVal = sc.nextLine();
                switch (betVal) {
                    case "10":
                        userBet = 1;
                        break;
                    case "20":
                        userBet = 2;
                        break;
                    case "30":
                        userBet = 3;
                        break;
                    default:
                        System.out.println("Invalid selection (10/20/30)");
                        break;
                }
            }
                                       
            int holdChoice = 0;
            while (holdChoice == 0) {
                System.out.println("Would you like to hold the middle reel for $10?");
                String holdchoice = sc.nextLine();
                switch (holdchoice.toUpperCase()) {
                    case "YES":
                        holdChoice = 1;
                        break;
                    case "Y":
                        holdChoice = 1;
                        break;
                    case "NO":
                        holdChoice = 2;
                        break;
                    case "N":
                        holdChoice = 2;
                        break;
                    default:
                        System.out.println("Invalid selection (yes/no)");
                        break;
                }
            }

            if ((game.getPlayerBalance() - (userBet * 10)) >= 0) { //check to make sure the bet wont give the player a negative balace
                if (holdChoice == 1) {
                    if ((game.getPlayerBalance() - (userBet * 10) - 10) >= 0) {
                        game.mainGame(userBet, 10);
                    } else {
                        System.out.println("You did not have enough to hold the reel and bet that amount");
                        game.mainGame(userBet, 0);
                    }
                }
                if (holdChoice == 2) {
                    game.mainGame(userBet, 0);
                }
            }


        }
        System.out.println("You have no more money. oops! "); //tell the player they have no more money (need to re-evaluate life choices)
    }
}
