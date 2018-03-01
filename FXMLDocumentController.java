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

public class FXMLDocumentController implements Initializable {

        @FXML
        private Button bet30;
        @FXML
        private Button bet20;
        @FXML
        private Button bet10;
        @FXML
        private VBox leftReel;
        @FXML
        private VBox midReel;
        @FXML
        private VBox rightReel;
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
        private ImageView lTop;
        @FXML
        private Label balance;
        

        private Reel lReel = new Reel(0);
        private Reel mReel = new Reel(1);
        private Reel rReel = new Reel(2);
        private Game backend = new Game(lReel, mReel, rReel);

        /*Image disp = new Image(filenam);
        inageViewer.setImage(disp); */

        @Override
        public void initialize(URL url, ResourceBundle rb) {
        }

        @FXML
        private void bet10Click(ActionEvent event) {
                backend.bet(10);
                backend.rollAll();
                int didWin = backend.winTest();
                if (didWin > 0){
                        int winnings = backend.winnings(10,didWin);
                        backend.collectWinnings(winnings);
                }
                int[] llst = lReel.getReel();
                int[] mlst = mReel.getReel();
                int[] rlst = rReel.getReel();
                this.balance.setText("Balance: $"+Double.toString(backend.getPlayerBalance()));
                this.lTop.setImage("2.png")

        }

        @FXML
        private void bet20Click(ActionEvent event) {
                backend.bet(20);
                backend.rollAll();
                int didWin = backend.winTest();
                if (didWin > 0){
                        int winnings = backend.winnings(20,didWin);
                        backend.collectWinnings(winnings);
                }
        }

        @FXML
        private void bet30Click(ActionEvent event) {
                backend.bet(30);
                backend.rollAll();
                int didWin = backend.winTest();
                if (didWin > 0){
                        int winnings = backend.winnings(30,didWin);
                        backend.collectWinnings(winnings);
                }
        }
}
