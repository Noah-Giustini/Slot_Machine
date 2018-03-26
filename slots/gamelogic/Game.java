package slots.gamelogic;

import java.util.Arrays;
import java.io.*;
import slots.exception.NegativeBalanceException;
	
/*
 * @author	Noah Guistini 
 * @author      Erin Brintnell
 * @author	Brian Kehrig
 *  
 * @version     3.0
 *       
 * The Game class is used to handle all of the basic functions of the slot machine. It contains the three reels of the 
 * slot machine and calculates the amount of money that the user has after each time the reels are rolled.
 *
 *
 * @var		leftReel - an instance of the Reel class that contains the information for the reel on the left
 * @var		midReel - an instance of the Reel class that contains the information for the reel in the middle
 * @var		rightReel - an instance of the Reel class that contains the information for the reel on the right
 * @var		playerBalance - an integer value of the amount of money the user has.
 * @var		winningList - an array of the payout multipliers for the different symbols on each of the reels
 *       there are instance variables for each of the three reels of the slot machine and one for the players money.
 *       This class also contains method variables to show the reels, roll the reels, get the player balance, bet money, collect winnings
 *       and test if the player won. this class also has a constructor that is to be used setting the instance variables for the left, middle,
 *       and right reels as well as the player balance to the default value of 100.pl
 */

public class Game{
        private Reel leftReel;          
        private Reel midReel;          
        private Reel rightReel;  
        private int playerBalance; 
        private final int[] winningList = new int[]{0,1,2,4,6,11}; 

	
	/** Constructor
	  *
	  * A constructor which takes parameters of thhe 3 Reels for the left middle and right reels in order to
	  * set up the game. The lefReel variable is set to l, the midReel variable is set to m and the rightReel
	  * variable is set to r. The player balance is also set to a default value of $100.
	  *
	  * @var	l - an instance of the Reel class for the left reel
	  * @var	m - an instance of the Reel class for the middle reel
	  * @var	r - an instance of the Reel class for the right reel
	  *
	  */
        public Game(Reel l,Reel m,Reel r){     
                this.leftReel = l;             
                this.rightReel = r;            
                this.midReel = m;
		this.playerBalance = 100;
        }
	
	/** The method, getPlayerBalance, is a getter for the players balance within the slot machine.
	  *
	  * @return 	this.playerBalance - the current player balance for the user of the slot machine.
	  *
	  */
	public int getPlayerBalance(){        
                return this.playerBalance;     
        }
	
	
	/** The set method, setPlayerBalance, sets the balance of the bank account to the value given as the startBalance. 
	  * since one will never have a negative balance on the game as they must stop playing when they run
	  * out of money, the method throws and exception if the given balance is a negative number. 
	  *
	  * @var	startBalance - the starting balance for the slot machine.
	  *
	  */	  
	protected void setPlayerBalance(int startBalance) throws NegativeBalanceException{
		if (startBalance > 0){
			this.playerBalance = startBalance;
		}
		else{
			throw new NegativeBalanceException("Tried to set balance to negative number");
		}
	}
	
	
	/** The method getLeftList returns the values of the left most reel in the slot machine.
	  *
	  * @return	The numerical representations of the data within the left reel of the machine.
	  *
	  */
        public int[] getLeftList(){
                return leftReel.getReel();
        }
	
	
	/** The method getMidList returns the values of the middle reel in the slot machine.
	  *
	  * @return	The numerical representations of the data within the middle reel of the machine.
	  *
	  */
	public int[] getMidList(){
                return midReel.getReel();
        }
	
	
	/** The method getRightList returns the values of the right most reel in the slot machine.
	  *
	  * @return	The numerical representations of the data within the right reel of the machine.
	  *
	  */
        public int[] getRightList(){
                return rightReel.getReel();
        }
	

	
	/** The method showGame shows the state of the three reels for the slot machine. It does this by getting
	  * the data from each of the three reels in the form of arrays and then it uses a for loop to print out
	  * each row of the game at a time. Until three rows have been printed (the number of placements on the 
	  * reels. Exception handeling is used to ensure that there is a spot at each position in these arrays.
	  * If there is not a spot within the arrays the program will close.
	  *
	  */
        public void showGame(){                         
                int[] leftList = leftReel.getReel();    
                int[] midList = midReel.getReel();     
                int[] rightList = rightReel.getReel(); 
                System.out.print ("\n\n\n");    //Prints out a space between different displays of the game
		try{
			for(int rowPos=0;rowPos<3;rowPos++){
				System.out.println(leftList[rowPos]+"          "+midList[rowPos]+"          "+rightList[rowPos]); 
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("It looks as if the reels within the slot machine may have incorrect formatting, " +
					   "the slot machine will now close. Try recompiling the program");
			System.exit(0);
		}
        }
        
		
		
        /** The method save game writes the new game to the save file, called saveFile.txt, using the BufferedWriter
	  * class of javas io package. The method first gets data pertaining to the state of each of the three reels
	  * and puts this into an array that has the information for all three reels. The method then writes the 
	  * players current balance on the first line of the text document before using a nested for loop to interate through
	  * each of the reel arrays in the allReels array writing the data from these arrays to the text document.
	  * Exception handeling is used to ensure that the text document is written to properly.
	  *
	  */
        public void saveGame (){
                int[] leftList = leftReel.getReel();    
                int[] midList = midReel.getReel();      
                int[] rightList = rightReel.getReel(); 
                int[][] allReels = {leftList, midList, rightList};	
                
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
		catch (FileNotFoundException e){
			System.out.println("I'm sorry I can't find the save file and will be unable to save.");
		}
                catch (IOException e){
                        System.out.println("There has been a problem within the file IO of the game, unable to save");
                }
        }    
     
	
	
	/** The method, rollAll, rolls all three of the reels within the slot machine by calling the rollReel
	  * methods in the Reel objects. This stimulates how the reels spinning around on a physical slot machine.
	  *
	  */
        public void rollAll(){  
                leftReel.rollReel();   
                rightReel.rollReel();   
                midReel.rollReel();  
        }
	
	
	/** The method, rollNotAll, rolls the left and the right reels but does not roll the middle reel. This method
	  * is called when the middle reel is held simulating the outside reels rotating on a physical slot machine.
	  *
	  */
        public void rollNotAll(){   
                leftReel.rollReel();    
                rightReel.rollReel(); 
        }
       
	
	/** The method, bet, allows the user to bet a certain amount of money on a particular game board set up. 
	  * this amount of money is then substracted from that users current balance.
	  *
	  * @param	amnt - the amount of money that the user is betting.
	  *
	  */
        public void bet(int amnt){    
                this.playerBalance -= amnt;    
        }
	
	
	/** The method, collectWinnings, adds the winnings of a certian game to the users current account
	  * balance game. 
	  *
	  * @param 	moneys - the amount of money that the user has won from a particular round
	  *
	  */
        public void collectWinnings(int moneys){    
                this.playerBalance += moneys;       
        }
	
	
	/**The method, winTest, tests to see if the user has won any money according to the current game board
	  * set up. It does this by getting information from each of the reels and then iterating through the
	  * arrays containing this information to check for winning sequences (ie. 3 in a row horizontally, vertically
	  * or diagonally). The variable winLines counts how many times the game has been won, each time their 
	  * is a winning sequence winLines is incremented.
	  *
	  * @return	winLines - the number of lines that the user has won on (these could be horizontal, diagonal or vertical)
	  */
        public int winTest(){                          
                int[] leftList = leftReel.getReel();   
                int[] midList = midReel.getReel();    
                int[] rightList = rightReel.getReel();  

                int winLines = 0;                    
                //int[] wonOn = new ArrayList<>(5);
                for(int x=0;x<3;x++){                       //loop to itterate through and see if the player won on the horizontal lines
                        if (rightList[x] != 6){
                                if ((leftList[x] == midList[x]) && (leftList[x] == rightList[x]) && (midList[x] == rightList[x])){     
                                        //wonOn[winLines] = x;
                                        winLines += winningList[leftList[x]];         

                                }
                        }
                        else{
                                if ((leftList[x] == midList[x]) && (6 == rightList[x])){    
                                        //wonOn[winLines] = x;
                                        winLines += winningList[leftList[x]];               

                                }
                        }
                }
                if(((leftList[0] == midList[1]) && (leftList[0] == rightList[2]) && (midList[1] == rightList[2])) ||
                  (leftList[0] == midList[1] && rightList[2] == 6)){       //checks to see if there is a win on the diagonal
                        winLines += winningList[leftList[0]];                   
                }
                if(((leftList[2] == midList[1]) && (leftList[2] == rightList[0]) && (midList[1] == rightList[0])) ||
                  (leftList[2] == midList[1] && rightList[0] == 6)){       //checks to see if there is a win on the diagonal
                        winLines += winningList[leftList[2]];              
                }
                return winLines;                      
                        
        }
	
	
	/** The method winnings determines how much money the player has won for a particular round. It does this
	  * by multiplying the amount a user bet by the number of lines that they won on.
	  *
	  * @param 	betAmt - the amount of money that the user bet for a particular round
	  * @param	linesWon - the number of lines that the user has won on for a particular game set up
	  * @return	win - the amount of money that the user has won calculated by multiplying the users
	  *		bet by the number of lines they won on.
	  *
	  */
        public int winnings(int betAmt,int linesWon){
                int win = (betAmt*linesWon);      
                return win;                          
        }
	
	
	/** The method mainGame runs the main game play for a particular round on the slot machine. First the method 
	  * bets the amount of money that the user wants to bet. It also uses the bet method to substract the amount
	  * of money needed to hold the middle reel ($10). If the reel is not held (ie. the hold value is 0) the method
	  * then rolls all the reels, otherwise it rolls all the reels except the middle one. The game then tests for 
	  * a win and shows the gameBoard. If the game was won, winnings are calculated and the user is shown their 
	  * winnings. This method is only used for the text version of the game.
	  *
	  * @param	bettings - the amount of money the player is betting on a current round
	  * @param	hold - the value needed to either hold or not hold the reels, this value is either 10 or 0
	  * 		dependant upon whether the middle reel is held or not.
	  *
	  */
        public void mainGame(int bettings,int hold){
                int value = (bettings * 10);
                bet(value);				
                bet(hold);
                if (hold == 0){
                        rollAll();				
                }
                if(hold == 10){
                        rollNotAll();
                }
                int didWin = winTest();		
                showGame();			
                if (didWin > 0){			
                        int winnings = winnings(value,didWin);	
                        collectWinnings(winnings);			
                        System.out.println("YOU HAVE WON $"+winnings+"!"); //tell player how much they have won
                }
        }
	
}

