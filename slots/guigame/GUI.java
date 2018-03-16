package slots.guigame;

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
        //This is the main class for the GUI version of the game.
    @Override
    public void start(Stage stage) throws Exception {
        //Set everything up (root, scene, stage, etc...)
        Parent root = FXMLLoader.load(getClass().getResource("slots/guigame/FXMLDocument.fxml"));
        
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
