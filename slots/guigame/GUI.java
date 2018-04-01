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
import javafx.scene.paint.Color;

/** This class GUI, runs the gui game of the slot machine using an FXMLDocument and calling to an FXML document
  * controller, therefore it is the main class for the GUI version of the game
  */
public class GUI extends Application {
   
    /** The method, start, is used to set up the GUI game, it does this by creating a primary Parent 
      * grouping of all GUI elements called root. Root loads the GUI using an FXMLDocument loader
      * which loads the FXML document that starts the FXMLDocument controller class. The method
      * than sets the scene to have this root (Parent) Next, the method sets the title on the screen 
      * to "SLOTS" and sets the stage to have the scene, the stage is then shown.
      *
      * @param         stage - the stage where everythin is shown in the GUI
      */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root, Color.BLACK);
        
        stage.setTitle("SLOTS");
        stage.setScene(scene);
        stage.show();
    }

    /** The main method of the GUI game that runs the GUI game, the launch method within main
      * is inherited from Application
      *
      * @param          args - a string array of arguments taken from the command prompt
      */
    public static void main(String[] args) {
        launch(args);
    }
    
}
