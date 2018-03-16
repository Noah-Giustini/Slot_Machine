import java.util.Arrays;
import java.io.*;

package GameLogic
	
/*
*       this class is the game class and is used to handle all of the basic functions of the game
*       there are instance variables for each of the three reels of the slot machine and one for the players money.
*       This class also contains method variables to show the reels, roll the reels, get the player balance, bet money, collect winnings
*       and test if the player won. this class also has a constructor that is to be used setting the instance variables for the left, middle,
*       and right reels as well as the player balance to the default value of 100.
*/

public class Game{
        private Reel leftReel;          //instance variable for the left reel
        private Reel midReel;           //instance variable for the middle reel
        private Reel rightReel;         //instance variable for the right reel
        private int playerBalance;      //instance variable for the player's money

        private final int[] winningList = new int[]{0,1,2,4,6,11}; //list of payout multipliers for the different symbols in the reels

        public Game(Reel l,Reel m,Reel r){      //constructor which takes parameters of 3 Reels for the left middle and right
                this.leftReel = l;              //set the left reel
                this.rightReel = r;             //set the right reel
                this.midReel = m;               //set the middle reel
                this.playerBalance = 100;       //set the player balance to 100
        }

	/** Sets the balance to the new balance. Protected and can only be used by super classes to 
	  * set an initial balance
	  */
	protected void setPlayerBalance (int newBalance){
			this.playerBalance =  newBalance;
		}
	
	
        public void showGame(){                         //method used to show the reels
                int[] leftList = leftReel.getReel();    //get data from left reel
                int[] midList = midReel.getReel();      //get data from middle reel
                int[] rightList = rightReel.getReel();  //get data from right reel
                int var;
                clearConsole();
                for(var=0;var<3;var++){
                        System.out.println(leftList[var]+"          "+midList[var]+"          "+rightList[var]);      //print the top line
                }
        }
        
        //The method save game writes the new game to the save file
        public void saveGame (){
                int[] leftList = leftReel.getReel();    //get the data from left reel
                int[] midList = midReel.getReel();      //get data from middle reel
                int[] rightList = rightReel.getReel();  //get data from the right reel
                int[][] allReels = {leftList, midList, rightList};	//Array of all three reel values
                
                //Writes balance and reel values to the save file
                try{
                        BufferedWriter writer = new BufferedWriter (new FileWriter ("saveFile.txt"));
                        writer.write (playerBalance + "\n");
                        for (int arrayPlacement = 0; arrayPlacement < allReels.length; arrayPlacement ++){
                                for (int arraySpace = 0 ; arraySpace < leftList.length; arraySpace ++){
                                        writer.write(allReels [arrayPlacement] [arraySpace] + "\n");
                                }
                        }
                        writer.close();
                }
                catch (IOException i){
                        System.out.println("io exception");
                }
        }    
     
        public int[] getLeftList(){
                return leftReel.getReel();
        }
        public int[] getMidList(){
                return midReel.getReel();
        }
        public int[] getRightList(){
                return rightReel.getReel();
        }
        public void rollAll(){          //method used to roll all reels
                leftReel.rollReel();    //roll the left reel
                rightReel.rollReel();   //roll the right reel
                midReel.rollReel();     //roll the middle reel
        }
        public void rollNotAll(){          //method used to roll all reels
                leftReel.rollReel();    //roll the left reel
                rightReel.rollReel();   //roll the right reel
        }
        public int getPlayerBalance(){          //getter for the player balance
                return this.playerBalance;      //return the player balance
        }
        public void bet(int amnt){              //method used to bet
                this.playerBalance -= amnt;     //subtract bet amount from playerBalance
        }
        public void collectWinnings(int moneys){        //method to collect winnings
                this.playerBalance += moneys;           //add winnings to player balance
        }
        public int winTest(){                           //method to test if the player won
                int[] leftList = leftReel.getReel();    //get the data from left reel
                int[] midList = midReel.getReel();      //get data from middle reel
                int[] rightList = rightReel.getReel();  //get data from right reel

                int winLines = 0;                       //variable to count how many lines were won on
                //int[] wonOn = new ArrayList<>(5);
                int x = 0;                              //variable for itteration
                for(x=0;x<3;x++){                       //loop to itterate through and see if the player won on the horizontal lines
                        if (rightList[x] != 6){
                                if ((leftList[x] == midList[x]) && (leftList[x] == rightList[x]) && (midList[x] == rightList[x])){      //if the values in a line are all the same
                                        //wonOn[winLines] = x;
                                        winLines += winningList[leftList[x]];             //increment winlines by one

                                }
                        }
                        else{
                                if ((leftList[x] == midList[x]) && (6 == rightList[x])){      //if the values in a line are all the same
                                        //wonOn[winLines] = x;
                                        winLines += winningList[leftList[x]];                 //increment winlines by one

                                }
                        }
                }
                if(((leftList[0] == midList[1]) && (leftList[0] == rightList[2]) && (midList[1] == rightList[2])) ||
                  (leftList[0] == midList[1] && rightList[2] == 6)){       //chechk to see if there is a win on the diagonal
                        winLines += winningList[leftList[0]];                     //increment winLines by one
                }
                if(((leftList[2] == midList[1]) && (leftList[2] == rightList[0]) && (midList[1] == rightList[0])) ||
                  (leftList[2] == midList[1] && rightList[0] == 6)){       //chechk to see if there is a win on the diagonal
                        winLines += winningList[leftList[2]];                     //increment winLines by one
                }
                return winLines;                        //return winLines
                        
        }
        public int winnings(int betamt,int linesWon){   //method to determine how much the player won
                int win = (betamt*linesWon);          //calculate winnings
                return win;                             //return winnings
        }
        public void mainGame(int bettings,int hold){
                int value = (bettings * 10);
                bet(value);				//bet
                bet(hold);
                if (hold == 0){
                        rollAll();				//roll all reels
                }
                if(hold == 10){
                        rollNotAll();
                }
                int didWin = winTest();		//test to see if the player has won
                showGame();			//show the reels
                if (didWin > 0){			//if the player won
                        int winnings = winnings(value,didWin);	//calculate winnings
                        collectWinnings(winnings);			//collect winnings
                        System.out.println("YOU HAVE WON $"+winnings+"!");//tell player how much they have won
                }
        }
        public final static void clearConsole(){
                System.out.println();
                System.out.println();
                System.out.println();
        }
}

