import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.lang.Integer;
import java.lang.Double;
import javafx.animation.AnimationTimer;
import java.lang.Integer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class GUI extends Application {
<<<<<<< HEAD
=======
        //This is the main class for the GUI version of the game.
    
        //Set up the reels
        private Reel leftReel = new Reel(0);
        private Reel midReel = new Reel(1);
        private Reel rightReel = new Reel(2);
        //Set up the game object
        private Game backend = new Game(leftReel,midReel,rightReel);
>>>>>>> 8a854dc7a4f382d53ef6eb421caa0bdd38d4ca4c
    
    @Override
    public void start(Stage stage) throws Exception {
        //Set everything up (root, scene, stage, etc...)
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("SLOTS");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        //The main method of the game
        //The launch method is inherited from Applicaion
        launch(args);
    }
    
}