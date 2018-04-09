package slots.test;

import static org.junit.Assert.*;
import org.junit.Test;
import slots.gamelogic.*;
import slots.exception.ReelException;

/** 
  *
  * @author	Noah Guistini 
  * @author Erin Brintnell
  * @author	Brian Kehrig
  * 
  * This class uses JUnit to test the Reel and Game classes (mainly the Reel class).
  */

public class ReelTest {

    @Test
    /** Test the constructors to make sure they give the correct values.
      * Also test the game class to make sure it has the correct balance.
      */
    public void test_Reel_constructors() {
        try{
            Reel reel1 = new Reel(0); //create new reels
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
            
            assertEquals("Left reel needs location of 0", 0, reel1.getLocation()); //test that the reel's locations are correct
            assertEquals("Middle reel needs location of 1", 1, reel2.getLocation());
            assertEquals("Right reel needs location of 2", 2, reel3.getLocation());
            
            Game g = new Game(reel1, reel2, reel3); //create a new game
            //test that the game sets the player balance to the correct default
            assertEquals("Should have balance of $100", 100, g.getPlayerBalance());
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelException", false); // fail if it gets to this line
        }
    }

    /** Test the bet(int) and collectWinnings(int) methods of the game class.
      * They should change the player balance properly.
      */
    @Test
    public void test_Game_money() {
        try{
            Reel reel1 = new Reel(0); //create reels
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
        
        
            Game g = new Game(reel1, reel2, reel3); //create game object
            assertEquals("Should have balance of $100", 100, g.getPlayerBalance());
            
            g.bet(33); //bet some money - we should have 100-67=33 left.
            assertEquals("Should have balance of $67 after betting $33", 67, g.getPlayerBalance());
            
            g.collectWinnings(73773);  //win 73773, we should have 67+73773=73840 money now.
            assertEquals("Should have balance of $73840", 73840, g.getPlayerBalance());
            
        } catch (ReelException e) {
             assertTrue("Making reels should not throw a ReelException", false); // fail if it gets to this line
        }
            
    }
    /** Test the getReel() method of the Reel class to make sure it matches with methods from the Game class.
      */
    @Test
    public void test_Reel_getReels() {
        try{
            Reel reel1 = new Reel(0); //create reels
            Reel reel2 = new Reel(1);
            Reel reel3 = new Reel(2);
            
            Game g = new Game(reel1, reel2, reel3); //create the game
            
            //test that the .getReel() method of Reel returns the same values as getLeftList, getMidList, getRightList of the Game class.
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
             assertTrue("Making reels should not throw a ReelException", false); // fail if it gets to this line
        }
    }
    /** Roll the reels.  Then test that they have 3 different values in their list.
      */
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
            assertTrue("Making reels should not throw a ReelException", false); // fail if it gets to this line
        }
    }
    
    /** This method is used by test_Reel_rollReels().
      * It checks if a single reel contains 3 different values.
      */
    private boolean testReel(Reel reel) {
        int[] list = reel.getReel();
        //Test if all 3 slots are different. We need to test every pair of slots individually for this.
        if ((list[0] == list[1]) || (list[0] == list[2]) || (list[1] == list[2])) {
            return false;
        } else {
            return true;
        }
    }
    
    /** Test the setter methods of the Reel class with valid values.
      */
    @Test
    public void test_Reel_setValues_valid() {
        try{
            Reel reel = new Reel(0); //create a new reel
            reel.rollReel(); //roll it
            reel.setReelValues(new int[]{3,1,5}); //give the reel valid values
            int[] thing = reel.getReel();
            assertEquals("Top slot should be 3", thing[0], 3); //test that its values are what they should be
            assertEquals("Middle slot should be 1", thing[1], 1);
            assertEquals("Bottom slot should be 5", thing[2], 5);
        } catch (ReelException e) {
            assertTrue("Making reels should not throw a ReelException", false); // fail if it gets to this line
        }
    }
    
    /** Test the setter methods of the Reel class with invalid values.
      */
    @Test
    public void test_Reel_setValues_invalid() {
        Reel reel = null;
        try{
            reel = new Reel(0); //create a new reel
        } catch (ReelException e) {
            assertTrue("Making reels should not throw a ReelException", false); // fail if it gets to this line
        }
            
        reel.rollReel(); //roll it
        
        try {
            reel.setReelValues(new int[]{3333,1,-5}); // give the reel invalid values
            int[] thing = reel.getReel(); // it should throw ReelException on this line
            assertTrue("getReel() should throw ReelException", false); // fail if it gets to this line
        } catch (ReelException e) {}
    }    

}
