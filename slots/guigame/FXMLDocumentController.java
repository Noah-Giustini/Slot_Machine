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



public class FXMLDocumentController implements Initializable {
        //This class handles all the screen elements, including the buttons, VBoxes, ImageViews, and labels.
        
        //Set up the buttons
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

		
        //Set up the VBoxes
        @FXML
        private VBox leftReel;
        @FXML
        private VBox midReel;
        @FXML
        private VBox rightReel;

        //Set up the ImageViews.  There are 9 in total, 3 for each reel.
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
		

        //Set up the label that will display our balance.
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
