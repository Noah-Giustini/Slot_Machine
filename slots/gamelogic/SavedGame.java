package slots.gamelogic;

import java.io.*;



/** The class SavedGame begins a game that has already been saved by the user. It takes in
  * the past games balance and then makes it the starting balance of the next game.
  */
public class SavedGame extends Game{
	boolean isFirstRound = true;
	
	/** Constructor
	  * 
	  * Initializes the super class and runs the read past game document. 
	  *
	  * @param 	l, m, r - instances of the reel class
	  *
	  */
	public SavedGame(Reel l,Reel m,Reel r){
		super (l,m,r);
		readPastGame(l,m,r);	
	}
	
	
	/** Overridden rollAll method. This method runs the super classes rollAll method 
	  * if the round is not the first round being played. Otherwise nothing happens
	  * besides changing the isFirst variable to false. This makes sure that the 
	  * saved reels are displayed.
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
	  * are then set according to the text document
	  *
	  * @param 	1,m,r the three reels of the game
	  */
	private void readPastGame(Reel l, Reel m, Reel r){
		//Initializes necessary variables
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
				else if (numLinesRead < 10){
					rReelValues[numLinesRead - 7] = Integer.parseInt(lineRead);
					numLinesRead ++;
				}
					
			}
			in.close();
			
			//Sets the initial values of each of the reels
			l.setReelValues(lReelValues);
			m.setReelValues(mReelValues);
			r.setReelValues(rReelValues);
		}
		
		catch (IOException i){
			System.out.println("exception");
		}
		
		catch (Exception e){
			super.rollAll();
		}	
		
		
	}	
		
}
