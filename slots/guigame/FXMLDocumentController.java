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
        
        //Set up the reel and game objects
        private Reel lReel = null;
        private Reel mReel = null;
        private Reel rReel = null;
        private Game backend;
        private int hold = 1;
        private String holdStatus;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
                //At the beginning of the game when the user is choosing which game to playNormalGame
                // for this reason the bet buttons are invisible
		try{
			lReel = new Reel(0);
        		mReel = new Reel(1);
       			rReel = new Reel(2);
		}
		catch (ReelException e){
			System.exit(0);
		}
                bet30.setVisible(false);
                bet20.setVisible(false);
                bet10.setVisible(false);
                balance.setVisible(false);
                save.setVisible(false);
		holdButton.setVisible(false);
		holdStatusLabel.setVisible(false);
		
		title.setImage(new Image("title.png"));
        }
		
        //Sets up a normal game that has (ie. not a saved game)
        @FXML
        private void playNormalGame (ActionEvent event){
                backend = new Game(lReel, mReel, rReel);
		backend.rollAll();
		startGame(event);
        }

        //Sets up a game from a save file
        @FXML
        private void playSavedGame (ActionEvent event){
                backend = new SavedGame(lReel, mReel, rReel);
		backend = (SavedGame) backend;
                startGame(event);
        }
		
        @FXML
        private void bet10Click(ActionEvent event) {
            betClick(event, 10); //When you bet 10
        }
        @FXML
        private void bet20Click(ActionEvent event) {
            betClick(event, 20); //When you bet 20
        }
        @FXML
        private void bet30Click(ActionEvent event) {
            betClick(event, 30); //When you bet 30
        }
		
        @FXML
        private void saveClick(ActionEvent event){
                backend.saveGame(); //game is saved
        }

        //This method removes the starting buttons and brings up the betting buttons as well as showing the game
        private void startGame(ActionEvent event){
                bet30.setVisible(true);
                bet20.setVisible(true);
                bet10.setVisible(true);
                balance.setVisible(true);
                save.setVisible(true);
                savedGame.setVisible(false);
                normalGame.setVisible(false);
		title.setVisible(false);
		holdButton.setVisible(true);
		holdStatusLabel.setVisible(true);
		showScreen(event);
        }

        //Shows the screen where all the images etc. are
        private void showScreen(ActionEvent event){

                //Change the text of the reel
                this.balance.setText("Balance: $"+Double.toString(backend.getPlayerBalance()));

                //Set up the new images
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
        @FXML
        private void holdClick(ActionEvent event){
                this.hold++;
                statusCalculate();
                this.holdStatusLabel.setText(holdStatus);
        }
        private void statusCalculate(){
                if ((this.hold % 2) == 0){
                    this.holdStatus = "Held";
                }
                else{
                    this.holdStatus = "Not Held";
                }
        }
		
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
						
			showScreen(event);
                        
                        
                }

        }
}
