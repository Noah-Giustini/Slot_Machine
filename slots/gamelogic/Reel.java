package slots.gamelogic;

import java.util.Random;      
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import slots.exception.ReelException;


/*
 * @author	Noah Guistini 
 * @author      Brian Kehrig
 * @author      Erin Brintnell
 *  
 * @version     3.0
 *       
 * The class, Reel is used to keep track of the data of each individual reel including the values of each of its 3 positions
 * stored in instance variables, as well as methods to roll the reel changing the values as well as a method to return 
 * these values in an array.
 *
 * @var         topBox - the top position in the reel of the slot that keeps track of which symbol is in the position
 * @var         middleBox - the middle position in the reel of the slot that keeps track of which symbol is in the position
 * @var         bottomBox - the bottom position in the reel of the slot that keeps track of which symbol is in the position
 * @var         location - the placement of the particular reel. The location is 0 for the left reel, 1 for the middle reel
 *              and 2 for the right reel
 * @var         cherry - the first potential item that can be chosen by the slot machine, which is chosen the most often
 * @var         watermelon - the second potential item that can be chosen by the slot machine, which is chosen the second most often
 * @var         horseshoe - the third potential item that can be chosen by the slot machine, which is chosen the third most often
 * @var         diamond - the forth potential item that can be chosen by the slot machine, which relatively rare and chosen the 
 *              second least often
 * @var         seven - the fifth potential item that can be chosen by the slot machine, which is the most valuable and rare
 *              and chosen the least often
 * @var         lucky - the reel item only present in the right reel that is very rare and special
 * @var         leftMidArray - an array list showing the potential items in the left and middle reels
 * @var         rightArray - an array list showing the potential items in the right reel
 * @var         wr1 - an instance of the weighted random class which choses items for the left and middle reels
 * @var         wr2 - an instance of teh weighted random class which choses items for the right reel
 */

public class Reel{
        private int topBox;           
        private int middleBox;         
        private int bottomBox;        
        private int location; 
        private static Random rand = new Random();
        
        private final static WeightedRandom.Item cherry = new WeightedRandom.Item(45); 
        private final static WeightedRandom.Item watermelon = new WeightedRandom.Item(35);
        private final static WeightedRandom.Item horseshoe = new WeightedRandom.Item(25); 
        private final static WeightedRandom.Item diamond = new WeightedRandom.Item(10);
        private final static WeightedRandom.Item seven = new WeightedRandom.Item(5); 
        private final static WeightedRandom.Item lucky = new WeightedRandom.Item(2); 

        private static ArrayList<WeightedRandom.Item> leftMidArray = new ArrayList(5);
        private static ArrayList<WeightedRandom.Item> rightArray = new ArrayList(6);
        
        /** This static statement adds each of the items to their repective array lists, the leftMidArray arraylist
          * gets all of the items except the lucky item. The rightArray arraylist gets all of the items.
          */
        static {
                leftMidArray.add(cherry);
                leftMidArray.add(watermelon);
                leftMidArray.add(horseshoe);
                leftMidArray.add(diamond);
                leftMidArray.add(seven);
                rightArray.add(cherry);
                rightArray.add(watermelon);
                rightArray.add(horseshoe);
                rightArray.add(diamond);
                rightArray.add(seven);
                rightArray.add(lucky);
        }
        
        private final static WeightedRandom wr1 = new WeightedRandom(leftMidArray);
        private final static WeightedRandom wr2 = new WeightedRandom(rightArray);

        
        /** Default Constuctor
          *
          * No arguments have been given as to the location of the reel.
          *
          */
        public Reel (){
        }
        
        
        /** Constructor
          *
          * This constructor sets up a reel according to a particular location given as a parameter by setting the
          * location to the parameter.
          *
          * @param      locat - the location of the reel.
          */
        public Reel (int locat) throws ReelException{
                if ((locat >= 0) && (locat <= 2)){
                        this.location = locat;
                }
                else {
                        throw new ReelException();
                }
                        
        }
        
        
        /** The method rollReel, is used to roll the reel changing the values in all three boxes while also ensuring there 
          * are no 2 same values on one reel. It does this by getting a random symbol from each of the different boxes
          * the while loop runs until all three symbols are different.
          *
          */
        public void rollReel(){        
                boolean differentSymbols = false;
                while (!differentSymbols) {
                        topBox = getRandom();
                        middleBox = getRandom();
                        bottomBox = getRandom();
                        if (topBox != middleBox && topBox != bottomBox && middleBox != bottomBox) {
                                differentSymbols = true;
                        }
                } 
        }

        
        /** The method, getRandom, gets a random symbol based upon its particular weight within the game
          * if the reel with the random symbol to be gotten is the right or middle reel, wr1 will be used
          * otherwise wr2 will be used. The method then returns a number dependant upon the value given.
          *
          * @return     a number that depends upon the value given by an instance of the weighted random
          *             class.
          */
        private int getRandom() {
                WeightedRandom.Item i;
                if (location == 2) {
                        i = wr2.get(rand);
                } else {
                        i = wr1.get(rand);
                }
                if (i == cherry) {
                        return 1;
                } else if (i == watermelon) {
                        return 2;
                } else if (i == horseshoe) {
                        return 3;
                } else if (i == diamond) {
                        return 4;
                } else if (i == seven) {
                        return 5;
                } else if (i == lucky) {
                        return 6;
                }
                return 0; //should never happen
        }

        
        /** The method, getReel, is used to get the instance variables of the reel in the form of an array. First
          * an array of the three boxes on the reel is made on the lst variable. Then this variable is returned to
          * the user.
          *
          * @return     lst - a list of the three boxes on the reel.
          *
          */
        public int[] getReel(){        
                int[] lst = {this.topBox,this.middleBox,this.bottomBox};     
                return lst;        
        }
        
        
        /** The method, getLocation, returns where a particular reel is located in the slot machine.
          *
          * @return     location - the location of the reel in the slot machine.
          *
          */
        public int getLocation() {
                return location;
        }
        
        /** The method, setReelValues, is used to set the initial reel values for the saved game. The method
          * checks to make sure that all of the values of the reels are actual values and then sets the reels
          * to these values. If they are not actual values for the symbols on the reels, a ReelException is thrown
          *
          * @param      reelValues - an array of the values of the symbols for the particular reel.
          */
        public void setReelValues(int [] reelValues) throws ReelException{
                int [] boxesArray = {this.topBox, this.middleBox, this.bottomBox};
                for (int arrayPos = 0; arrayPos < boxesArray.length; arrayPos ++){
                        if ((reelValues[arrayPos] < 6) &&(reelValues [arrayPos] > 0)){
                                boxesArray[arrayPos] = reelValues[arrayPos];
                        }
                        else{
                                throw new ReelException();
                        }
                }
                System.out.println(this.topBox);
                System.out.println(this.middleBox);
                System.out.println(this.bottomBox);
        }
  
}

