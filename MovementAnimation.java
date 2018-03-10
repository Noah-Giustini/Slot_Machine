import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.scene.shape.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
public class AnimationsPractice extends Application{
	
	
	public static void main (String[]args){
		launch (args);
	}
	
	public void start (Stage stage) throws Exception{
		VBox root = new VBox();
		// root.setPadding(new Insets(20,50,400,20));
		Image image = new Image("2.png");
		ImageView iv1 = new ImageView();
		Image image2 = new Image ("1.png");
		ImageView iv2 = new ImageView();
		Image image3 = new Image("3.png");
		ImageView iv3 = new ImageView();
		Image image4 = new Image ("4.png");
		ImageView iv4 = new ImageView();
		iv2.setImage (image2);
		iv1.setImage (image);
		iv3.setImage (image3);
		iv4.setImage (image4);
		Path path = new Path();
		path.getElements().addAll(new MoveTo(50, -500), new VLineTo(550));
		Timeline timeline = new timeline();
		root.getChildren().addAll(iv1,iv2,iv3,iv4);				 
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
					 

		PathTransition pt = new PathTransition(Duration.millis(1000), path, iv1);
		pt.setCycleCount(10);
		// pt.setAutoReverse(false);
		pt.play();
		
		PathTransition pt2 = new PathTransition(Duration.millis(1000), path, iv2);
		pt2.setCycleCount(10);
		// pt2.setAutoReverse(false);
		pt2.play();
		
		PathTransition pt3 = new PathTransition(Duration.millis(1000), path, iv3);
		pt3.setCycleCount(10);
		// pt.setAutoReverse(false);
		pt3.play();
		
		PathTransition pt4 = new PathTransition(Duration.millis(1000), path, iv4);
		pt4.setCycleCount(10);
		// pt2.setAutoReverse(false);
		pt4.play();

	}
}
