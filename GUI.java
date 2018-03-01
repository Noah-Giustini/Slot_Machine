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

        private Reel leftReel = new Reel(0);
        private Reel midReel = new Reel(1);
        private Reel rightReel = new Reel(2);

        private Game backend = new Game(leftReel,midReel,rightReel);
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}