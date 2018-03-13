import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.scene.shape.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;

/** This class SpinningReelsGUI rolls the reels of the game to show the reel turning.
  * code to do with the timelines was adapted from:
  * https://docs.oracle.com/javase/8/javafx/visual-effects-tutorial/basics.htm#BEIIDFJC
  *
  * @var 	timeline - the timeline by which the animations are based.
  * @var	timer - a timer that counts the time since the animation
  *			has started.
  * @var	image - the image displayed for the particular animation 
  *			spot on the game reel
  *@var		timeMilli - the amount of time that has passed since the
  *			animation began
  * @var	rand - a random number generator.
  * @var	pt - the path transition that each image follows
  * @var	randNum - a random number between 1 and 5, used to choose and
  *			image.
  * @var	path - the path followed by each image.
  * @var	root - the group of all the images
  * @var	timeToMove - the next time that an image can move, sets the 
  *			start of each image.
  * @var	timeNextImage - the time at which the moveImage method will
  * 		be called.
  * @var	reelRow - the row at which the reel is rotated
  *
  */
public class SpinningReelsGUI extends Application{
	private Scene primaryScene;
   	private Timeline timeline = new Timeline();
   	private AnimationTimer timer;
  	private Image image;
	private int timeMilli = 1;
	private Random rand = new Random();
	private PathTransition pt;
	private int randNum = 0;
	private Path path = new Path();
	private Group root = new Group();
	private int timeToMove = 10;
	private Duration timeNextImage = Duration.millis (100);
	private int reelRow = 300;
	
	SpinningReelsGUI ( Scene currentScene){
		primaryScene = currentScene;
	}
	
	/** The method, moveImage, produces an imputted image at the top of the 
	  * screen and lets it fall to the bottom of the screen. It does this by
	  * making a new VBox and allowing the image to fall from the top of that
	  * vbox to the bottom of the vbox. It also creates a new imageview and sets
	  * that imageview to have th imputted image. The image view it then added
	  * to the root. A path transition and fade transition is then made
	  * and played. Finally the timeToMove is changed as the speed to the sequence
	  * changes. This way images won't overlap as the moveImage method will only
	  * do something if the time is right (using the if statement).
	  *
	  * @param		newImage - a newImage that will be transitioned.
	  *
	  */
	public void moveImage(Image newImage){
		if (timeMilli> timeToMove){
			ImageView iv = new ImageView();
			iv.setImage(newImage);
			iv.setX(reelRow-50);
			FadeTransition ft = new FadeTransition (Duration.millis(10*timeMilli), iv);
			ft.setFromValue(3);
			ft.setToValue(0);
			pt = new PathTransition(Duration.millis(5*timeMilli), path, iv);
			ft.play();
			pt.play();
			root.getChildren().add(iv);
			timeToMove += (timeMilli/10);
		}		
	}
	
	/** The method start, runs the animation using the primary stage.
	  *
	  * @var 	stage - the stage of the scene.
	  *
	  */
    @Override 
	public void start(Stage stage) {
		//Creates a new scene
	stage.setScene(scene);
		
		//Creates a path by which the images will move
		path.getElements().addAll(new MoveTo(reelRow, 50), new VLineTo(400));
		//Sets how many times new images are added to the reel
        timeline.setCycleCount(200);
 
		//Counts the amount of time that has passed since the animation
		//began. Uses this number to change the timeMilli variable
		timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
				timeMilli++;
            }
 
        };
		
		//Generates a randome number and then apllies this number to 
		// choose an image which is imputted into the moveImage method
        EventHandler nextImage = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
				randNum = rand.nextInt(5)+1;
				image = new Image (randNum+".png");
				moveImage (image);	
            }
        };
		 
		//Adds the nextImage eventHandler to the timeline using a keyFrame
		KeyFrame keyFrame = new KeyFrame(timeNextImage, nextImage);
        timeline.getKeyFrames().addAll(keyFrame);
		
		//Starts the animation
        timeline.play();
        timer.start();
		stage.show();
		
    }
        
        
    public static void main(String[] args) {
        Application.launch(args);
    }
 
}
