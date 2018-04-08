import static org.junit.Assert.*;
import org.junit.Test;
import slots.gamelogic.*;
import slots.exception.ReelException;

public class ReelTest {

    @Test
    public void test_Reel_constructors() {
        try{
            Reel reel1 = new Reel(0);
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
            
            assertEquals("Left reel needs location of 0", 0, reel1.getLocation());
            assertEquals("Middle reel needs location of 1", 1, reel2.getLocation());
            assertEquals("Right reel needs location of 2", 2, reel3.getLocation());
            Game g = new Game(reel1, reel2, reel3);
            assertEquals("Should have balance of $100", 100, g.getPlayerBalance());
            
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelExceptioon", false); // fail if it gets to this line
        }
    }

    @Test
    public void test_Reel_money() {
        try{
            Reel reel1 = new Reel(0);
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
        
        
            Game g = new Game(reel1, reel2, reel3);
            assertEquals("Should have balance of $100", 100, g.getPlayerBalance());
            g.bet(33);
            assertEquals("Should have balance of $67 after betting $33", 67, g.getPlayerBalance());
            g.collectWinnings(73773);
            assertEquals("Should have balance of $73840", 73840, g.getPlayerBalance());
            
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelExceptioon", false); // fail if it gets to this line
        }
            
    }

    @Test
    public void test_Reel_getReels() {
        try{
            Reel reel1 = new Reel(0);
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
            Game g = new Game(reel1, reel2, reel3);
            assertEquals("Reel1 should match", reel1.getReel()[0], g.getLeftList()[0]);
            assertEquals("Reel1 should match", reel1.getReel()[1], g.getLeftList()[1]);
            assertEquals("Reel1 should match", reel1.getReel()[2], g.getLeftList()[2]);
            assertEquals("Reel2 should match", reel2.getReel()[0], g.getMidList()[0]);
            assertEquals("Reel2 should match", reel2.getReel()[1], g.getMidList()[1]);
            assertEquals("Reel2 should match", reel2.getReel()[2], g.getMidList()[2]);
            assertEquals("Reel3 should match", reel3.getReel()[0], g.getRightList()[0]);
            assertEquals("Reel3 should match", reel3.getReel()[1], g.getRightList()[1]);
            assertEquals("Reel3 should match", reel3.getReel()[2], g.getRightList()[2]);
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelExceptioon", false); // fail if it gets to this line
        }
    }

    @Test
    public void test_Reel_rollReels() {
        try {
            Reel reel1 = new Reel(0);
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
            Game g = new Game(reel1, reel2, reel3);
            g.rollAll();
            assertTrue("Left reel needs to have all different items", testReel(reel1));
            assertTrue("Middle reel needs to have all different items", testReel(reel2));
            assertTrue("Reel reel needs to have all different items", testReel(reel3));
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelExceptioon", false); // fail if it gets to this line
        }
    }
    
    @Test
    public void test_Reel_setValues_valid() {
        try{
            Reel reel = new Reel(0);
            reel.rollReel();
            reel.setReelValues(new int[]{3,1,5});
            int[] thing = reel.getReel();
            assertEquals("Top slot should be 3", thing[0], 3);
            assertEquals("Middle slot should be 1", thing[1], 1);
            assertEquals("Bottom slot should be 5", thing[2], 5);
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelExceptioon", false); // fail if it gets to this line
        }
    }
    
    @Test
    public void test_Reel_setValues_invalid() {
        Reel reel;
        try{
            reel = new Reel(0);
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelExceptioon", false); // fail if it gets to this line
        }
            
        reel.rollReel();
        
        try {
            reel.setReelValues(new int[]{3333,1,-5});
            int[] thing = reel.getReel(); // it should throw ReelException on this line
            assertTrue("getReel() should throw ReelException", false); // fail if it gets to this line
        } catch (ReelException e) {}
    }    

    private boolean testReel(Reel reel) {
        int[] list = reel.getReel();
        if ((list[0] == list[1]) || (list[0] == list[2]) || (list[1] == list[2])) {
            return false;
        } else {
            return true;
        }
    }
}
