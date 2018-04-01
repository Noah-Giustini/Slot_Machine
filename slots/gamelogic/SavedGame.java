package slots.gamelogic;

import java.io.*;
import slots.exception.NegativeBalanceException;
import slots.exception.ReelException;



/** @author      Erin Brintnell
  *  
  * @version     1.0
  *       
  * The class SavedGame begins a game that has already been saved by the user. It takes in
  * the past games balance and state and then makes a new game with this state. The Game class
  * is inherited to run all of the game functions. Additionally this class does not roll the reels
  * on the first round because the user has not interacted with the saved game yet.
  *
  * @var	isFirstRound - a boolean telling if this is the first time the user is 
  *		interacting with the slot machine since the previous state has been
  *		reloaded.
  *
  */
public class SavedGame extends Game{
	boolean isFirstRound = true;
	
	/** Constructor
	  * 
	  * Initializes the super class and runs the readPastGame method changing each of the reels
	  *
	  * @param 	l - the leftmost reel for the game setup
	  * @param	m - the middle reel for the game setup
	  * @param	r - the right reel for the game setup
	  *
	  */
	public SavedGame(Reel l,Reel m,Reel r){
		super (l,m,r);
		readPastGame(l,m,r);	
	}
	
	
	/** Overridden rollAll method. This method runs the super classes rollAll method 
	  * if the round is not the first round being played. Otherwise nothing happens
	  * besides changing the isFirst variable to false. This makes sure that the 
	  * saved reels are displayed for the first game.
	  *
	  */
	public void rollAll(){
		if (isFirstRound){
			isFirstRound = false;
		}
		else {
			super.rollAll();
		}
	}
	
	
	
	/** The method readPastGame, reads a text file whih has all of the information
	  * from the past game written to a buffered reader is used to do the reading
	  * for each line of the saveFile. The the balance and values of the reels 
	  * are then set according to the text document which holds a previous state
	  * of the game. If the saved game cannot find the file that has the saved game
	  * or if there is an IO exception it will print a statement saying that it 
	  * cannot find the specified file, and will start a new game. If the saved file
	  * does not have propper formatting (ie. there is a negative balance), an message
	  * will be printed and a new game will be started
	  *
	  * @var	lineRead -  the String read from each line of the document
	  * @var	in - a buffered reading the incoming file IO
	  * @var	numLinesRead - the number of lines read by the BufferedReader
	  * @var	lReelValues - the values representing the state of the leftmost reel
	  * @var	mReelValues - the values representing the state of the middle reel
	  * @var	rReelValues - the values representing the state of the rightmost reel
	  * @param 	l - the leftmost reel for the game setup
	  * @param	m - the middle reel for the game setup
	  * @param	r - the right reel for the game setup
	  */
	private void readPastGame(Reel l, Reel m, Reel r){
		String lineRead = "";
		BufferedReader in = null;
		int numLinesRead = 0;
		int [] lReelValues = new int [3];
		int [] mReelValues = new int [3];
		int [] rReelValues = new int [3];
		
		try{
			in = new BufferedReader( new FileReader("saveFile.txt"));
			
			// Reads lines of the text document until there are no lines left
			while (lineRead != null){
				lineRead = in.readLine();
				
				//The first line of the document is the player balance, this is set
				// to the player's balance
				if (numLinesRead == 0){
					super.setPlayerBalance( Integer.parseInt(lineRead));
					numLinesRead ++;
				}
				
				//The next three lines are the values of the left reel, which are added
				// to the left reel array
				else if (numLinesRead < 4){
					lReelValues[numLinesRead -1] = Integer.parseInt(lineRead);
					numLinesRead ++;
				}
				
				
				//The next three lines are the values of the middle reel, which are added
				// to the middle reel array
				else if (numLinesRead < 7){
					mReelValues[numLinesRead - 4] = Integer.parseInt(lineRead);
					numLinesRead ++;
				}
				
				//The last three lines are the values of the right reel, which are added
				// to the right reel array
				else if (numLinesRead < 11){
					rReelValues[numLinesRead - 7] = Integer.parseInt(lineRead);
					numLinesRead ++;
				}
				System.out.println(Integer.parseInt(lineRead));	
			}
			in.close();
			
			//Sets the initial values of each of the reels
			l.setReelValues(lReelValues);
			m.setReelValues(mReelValues);
			r.setReelValues(rReelValues);
		}
		
		catch (FileNotFoundException e){
			System.out.println("I'm sorry I can't find a saved file, loading new game...");
			super.rollAll();
		}
		
		catch (IOException e){
			System.out.println("An error has occured in loading the saved game, loading new game...");
			super.rollAll();
		}
		
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("There seems to be a problem with the state of the saved file, loading new game...");
			super.rollAll();
			
		}
		catch (NegativeBalanceException e){
			System.out.println("The game you are trying to load has a negative balance, loading a new game...");
			super.rollAll();
		}
		catch (ReelException e){
			System.out.println("The game you are trying to load has invalid reels, loading a new game...");
			super.rollAll();
		}
		
		
	}	
		
}
