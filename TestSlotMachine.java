//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!
//THESE IMPORT STATEMENTS CRASH!!!!!!!!
//I DON'T KNOW WHY!!!!!!!!

import static org.junit.Assert.*;

import org.junit.Test;

///////////////////////////////////

///////////////////////////////////



public class TestSlotMachine {

	/*@Test
	public void test_getIDNum(){
		assertTrue("Instance variables should be private with correct name and type.", instanceVariablesArePrivate());
		assertTrue("Employee should not have the default constructor.", noDefaultConstructor());
		String[] methods = {"double getMonthlyPay()", "String getStatus()"};
		assertTrue("The Employee class, getStatus and getMonthlyPay methods should be abstract with correct signature.", hasRequiredAbstractMethods(methods));
		assertTrue("constructor should be public with correct signature.", hasRequiredPublicMethods());
		
		
		Emp e1 = new Emp("Adam","1");
		
		assertEquals("Created Adam, 1.", "1", e1.getIDNum());
	}*/
    
    @Test
    public void test_constructors() {
        Reel reel1 = new Reel(0);
        Reel reel2 = new Reel(1);
        Reel reel3 = new Reel(2);
        assertEquals("Left reel needs location of 0", 0, reel1.getLocation());
        assertEquals("Middle reel needs location of 1", 1, reel2.getLocation());
        assertEquals("Right reel needs location of 2", 2, reel3.getLocation());
        Game g = new Game(reel1, reel2, reel3);
        assertEquals("Should have balance of $100", 100, g.getPlayerBalance());
    }
    
    @Test
    public void test_money() {
        Reel reel1 = new Reel(0);
        Reel reel2 = new Reel(1);
        Reel reel3 = new Reel(2);
        Game g = new Game(reel1, reel2, reel3);
        assertEquals("Should have balance of $100", 100, g.getPlayerBalance());
        g.bet(33);
        assertEquals("Should have balance of $67 after betting $33", 67, g.getPlayerBalance());
        g.collectWinnings(73773);
        assertEquals("Should have balance of $73840", 73840, g.getPlayerBalance());
    }
    
    @Test
	public void test_getReels() {
		Reel reel1 = new Reel(0);
        Reel reel2 = new Reel(1);
        Reel reel3 = new Reel(2);
        Game g = new Game(reel1, reel2, reel3);
        assertEquals("Reel1 should match", reel1.getReel()[0], g.getLeftList()[1]);
	assertEquals("Reel1 should match", reel1.getReel()[1], g.getLeftList()[2]);
	assertEquals("Reel1 should match", reel1.getReel()[2], g.getLeftList()[3]);
        assertEquals("Reel2 should match", reel2.getReel()[0], g.getMidList()[1]);
	assertEquals("Reel2 should match", reel2.getReel()[1], g.getMidList()[2]);
	assertEquals("Reel2 should match", reel2.getReel()[2], g.getMidList()[3]);
        assertEquals("Reel3 should match", reel3.getReel()[0], g.getRightList()[1]);
	assertEquals("Reel3 should match", reel3.getReel()[1], g.getRightList()[2]);
	assertEquals("Reel3 should match", reel3.getReel()[2], g.getRightList()[3]);
	}
    
    @Test
	public void test_rollReels() {
		Reel reel1 = new Reel(0);
        Reel reel2 = new Reel(1);
        Reel reel3 = new Reel(2);
        Game g = new Game(reel1, reel2, reel3);
        g.rollAll();
        assertTrue("Left reel needs to have all different items", testReel(reel1));
        assertTrue("Middle reel needs to have all different items", testReel(reel2));
        assertTrue("Reel reel needs to have all different items", testReel(reel3));
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
