package slots.guigame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.lang.Integer;
import java.lang.Double;
import javafx.scene.control.CheckBox;
import slots.gamelogic.*;
import slots.exception.ReelException;


/** This class, FXMLDocumentController, handles all of the screen elements of the GUI game according
  * to the FXML document including the buttons, VBoxes, ImageViews, and labels.
  * 
  * @var	bet30 - the button that a user presses when they want to bet $30
  * @var	bet20 - the button that a user presses when they want to bet $20
  * @var	bet10 - the button that a user presses when they want to bet $10
  * @var	save - the button that a user presses when they want to save the game
  * @var	savedGame - the button that a user presses when they want to start a previously saved game
  * @var	normalGame - the button that a user presses when they want to start a brand new game
  * @var	holdButton - the button that a user presses when they want to hold the middle reel of the game
  * @var	lTop - an image view showing the item in the top section of the left reel
  * @var	mTop - an image view showing the item in the top section of the middle reel
  * @var	rTop - an image view showing the item in the top section of the right reel
  * @var	lMid - an image view showing the item in the middle section of the left reel
  * @var	mMid - an image view showing the item in the middle section of the middle reel
  * @var	rMid - an image view showing the item in the middle section of the right reel
  * @var	lBot - an image view showing the item in the bottom section of the left reel
  * @var	mBot - an image view showing the item in the bottom section of the middle reel
  * @var	rBot - an image view showing the item in the bottom section of the right reel
  * @var	title - an image view that shows the intro title screen for the game
  * @var	balance - a label showing the user's current balance in the game
  * @var	holdStatusLabel - a label showing whether or not the middle reel is held for the game
  * @var	lReel - an instance of the reel class for the left reel of the game
  * @var	mReel - an instance of the reel class for the middle reel of the game
  * @var	rReel - an instance of the reel class for the right reel of the game
  * @var	backend - the game play that happens behind the scenes (can either be a new game or a saved game)
  * @var	hold - an integer counting how many times the user has pressed the hold button starting with 1 time
  * @var	holdStatus - a string showing the status of the hold
  */
public class FXMLDocumentController implements Initializable {
     
        @FXML
        private Button bet30;
        @FXML
        private Button bet20;
        @FXML
        private Button bet10;
        @FXML
        private Button save;
        @FXML
        private Button savedGame;
        @FXML
        private Button normalGame;
        @FXML
        private Button holdButton;

	
	
        @FXML
        private ImageView lTop;
        @FXML
        private ImageView mTop;
        @FXML
        private ImageView rTop;

        @FXML
        private ImageView lMid;
        @FXML
        private ImageView mMid;
        @FXML
        private ImageView rMid;

        @FXML
        private ImageView lBot;
        @FXML
        private ImageView mBot;
        @FXML
        private ImageView rBot;
	
	@FXML
	private ImageView title;
		


        @FXML
        private Label balance;
        @FXML
        private Label holdStatusLabel;
        

	
        private Reel lReel = null;
        private Reel mReel = null;
        private Reel rReel = null;
        private Game backend;
        private int hold = 1;
        private String holdStatus;

	
	
	/** The method initialize sets up the game when the FXMLDocument controller class is orginally started.
	  * the method begins by trying to set up the reels for the game. If a reel excepion is caught, meaning
	  * that there is an error in the reels than the method displays a message to the user using the
	  * holdStatusLabel and quits the application. The method sets all game objects to invisible 
	  * while keeping objects needed for the starting screen visible. Finally it sets the start screen image 
	  * to the title image.
	  *
	  * @param	url - the url of the FXMLDocument that is read by the FXMLDocument controller
	  * @param	rb - the resource bundle needed to run the FXMLDocumentController class
	  */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
		bet30.setVisible(false);
                bet20.setVisible(false);
                bet10.setVisible(false);
                balance.setVisible(false);
                save.setVisible(false);
		holdButton.setVisible(false);
		
		try{
			lReel = new Reel(0);
        		mReel = new Reel(1);
       			rReel = new Reel(2);
		}
		catch (ReelException e){
			holdStatusLabel.setText("There seems to be an error with the reels of the game, the application"
						+ "will now close");
			System.exit(0);
		}
		
		holdStatusLabel.setVisible(false);
		
		title.setImage(new Image("title.png"));
        }
		
	
	
        /** The method, playNormalGame, sets up a normal game that has not yet been played (ie. not a saved game)
	  * it does this by setting the backend to a new Game with the three reels and rolling all of the reels
	  * it then calls to the startGame method to begin the game.
	  *
	  * @param	event - the action event of pressing the normalGame button to start a new game
	  */
        @FXML
        private void playNormalGame (ActionEvent event){
                backend = new Game(lReel, mReel, rReel);
		backend.rollAll();
		startGame(event);
        }
	
	
	
        /** The method, playSavedGame, sets up a saved game from a saved file
	  * it does this by setting the backend to a new SavedGame with the three reels, and typecasting backend to 
	  * a saved game. It then calls to the startGame method to begin the game.
	  *
	  * @param	event - the action event of pressing the savedGame button to start a new game
	  */
        @FXML
        private void playSavedGame (ActionEvent event){
                backend = new SavedGame(lReel, mReel, rReel);
		backend = (SavedGame) backend;
                startGame(event);
        }
	
	
	
	/** The method bet10Click, is called when the user presses the bet10 button. When this happens the method
	  * calls to the betClick method with an amount to be bet of $10
	  *
	  * @param	event - the action event of pressing the bet10 button to bet $10
	  *
	  */
        @FXML
        private void bet10Click(ActionEvent event) {
            betClick(event, 10); //When you bet 10
        }
	
	
	
	/** The method bet20Click, is called when the user presses the bet20 button. When this happens the method
	  * calls to the betClick method with an amount to be bet of $20
	  *
	  * @param	event - the action event of pressing the bet20 button to bet $20
	  *
	  */
        @FXML
        private void bet20Click(ActionEvent event) {
            betClick(event, 20); //When you bet 20
        }
	
	
	
	/** The method, bet30Click, is called when the user presses the bet30 button. When this happens the method
	  * calls to the betClick method with an amount to be bet of $30
	  *
	  * @param	event - the action event of pressing the bet30 button to bet $30
	  *
	  */
        @FXML
        private void bet30Click(ActionEvent event) {
            betClick(event, 30); //When you bet 30
        }
	
	
	
	/** The method, saveClick, is called when the user presses the save button. When this happens the method
	  * saves the game in the backend variable
	  *
	  * @param	event - the action event of pressing the save button to save the game
	  *
	  */
        @FXML
        private void saveClick(ActionEvent event){
                backend.saveGame();
        }
	
	
	
	/** The method, holdClick, is called when the user presses the hold button on the gui. When this happens
	  * the hold integer value increments meaning that the button has been pressed one more time. With this
	  * new value the status of the hold is calculated using the statusCalculate method and the holdStatusLabel
	  * is set to the holdStatus string
	  */
	@FXML
        private void holdClick(ActionEvent event){
                this.hold++;
                statusCalculate();
                this.holdStatusLabel.setText(holdStatus);
        }

	
	
        /** This method, startGame, removes the starting buttons and the introscreen by manking them invisible and 
	  * brings up the betting buttons as well as showing the game, through the showScreen method
	  *
	  */
	
        private void startGame(){
                bet30.setVisible(true);
                bet20.setVisible(true);
                bet10.setVisible(true);
                balance.setVisible(true);
                save.setVisible(true);
		holdButton.setVisible(true);
		holdStatusLabel.setVisible(true);
		
		savedGame.setVisible(false);
                normalGame.setVisible(false);
		title.setVisible(false);
		
		showScreen();
        }

	
	
        /** The method, showScreen, shows the screen where all the images, buttons, etc. at the start of the game and 
	  * after each time that the user has bet some money and the reels have rolled. The method first chances the 
	  * balance variable to the new player balance in the backend variable and then sets each of the item image
	  * views to the image of their respective item from the backend variable by getting the reel of their 
	  * area and choosing a position in the array that is returned. The position in 0 of this array is the top
	  * placement, the item in position 1 of the array is the middle item and the item in position 2 is the bottom
	  * item.
	  */
        private void showScreen(){
	
                this.balance.setText("Balance: $"+Double.toString(backend.getPlayerBalance()));


                lTop.setImage(new Image(Integer.toString(lReel.getReel()[0])+".png"));
                lMid.setImage(new Image(Integer.toString(lReel.getReel()[1])+".png"));
                lBot.setImage(new Image(Integer.toString(lReel.getReel()[2])+".png"));

                mTop.setImage(new Image(Integer.toString(mReel.getReel()[0])+".png"));
                mMid.setImage(new Image(Integer.toString(mReel.getReel()[1])+".png"));
                mBot.setImage(new Image(Integer.toString(mReel.getReel()[2])+".png"));

                rTop.setImage(new Image(Integer.toString(rReel.getReel()[0])+".png"));
                rMid.setImage(new Image(Integer.toString(rReel.getReel()[1])+".png"));
                rBot.setImage(new Image(Integer.toString(rReel.getReel()[2])+".png"));
        }
	
	
	
        /** The method, statusCalculate, calculates whether the middle reel needs to be held or not
	  * if the middle reel has been pressed an odd number of times than the method will set
	  * the holdStatus string to "Held", otherwise it will set the holdStatus string to "NotHeld".
	  * The number of times that the hold status button has been pressed is odd if the hold integer
	  * is even because the hold integer value begins as 1 with each game. In this manner the 
	  * method uses an if statement to check for a number with no remainder when divided by 2 and 
	  * if their is no remainder than the middle reel is to be held
	  */
        private void statusCalculate(){
                if ((this.hold % 2) == 0){
                    this.holdStatus = "Held";
                }
                else{
                    this.holdStatus = "Not Held";
                }
        }
		
	
	
	/** The method, betClick, is called whenever a user bets any amount of money through one of the
	  * three betting buttons. The method uses that backend variable to check how much money will be
	  * left if the game 
	  */
        private void betClick(ActionEvent event, int amount) {
                //This is the method that is called when you bet an amount
                //The amount variable is the amount of the bet (can be 10, 20, or 30)
                if ((backend.getPlayerBalance() - amount) >= 0){ //if we are not yet bankrupt
                        if ((this.hold%2) == 0){
                                if ((backend.getPlayerBalance() - amount - 10) >= 0){
                                        backend.bet(amount);
                                        backend.bet(10);
                                        backend.rollNotAll();
                                        int didWin = backend.winTest(); //check if we won
                                        if (didWin > 0){
                                        int winnings = backend.winnings(amount,didWin);
                                         backend.collectWinnings(winnings); //collect our moneys
                                         }
                                }
                        }
                        if (((this.hold+1)%2)== 0){
                                backend.bet(amount); //bet the specified amount
                                backend.rollAll(); //roll the reels
                                int didWin = backend.winTest(); //check if we won
                                if (didWin > 0){
                                        int winnings = backend.winnings(amount,didWin);
                                        backend.collectWinnings(winnings); //collect our moneys
                                }
                        }
						
			showScreen();
                        
                        
                }

        }
}
