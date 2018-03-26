package slots.gamelogic;

import java.util.Random;      
import java.util.ArrayList;
import java.util.Arrays;


/*
 * @author	Noah Guistini 
 * @author      Brian Kehrig
 *  
 * @version     2.0
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
 * @var         item1 - the 
 */

public class Reel{
        private int topBox;           
        private int middleBox;         
        private int bottomBox;        
        private int location;        
        private final static WeightedRandom.Item item1 = new WeightedRandom.Item(45); //cherry
        private final static WeightedRandom.Item item2 = new WeightedRandom.Item(35); //watermelon
        private final static WeightedRandom.Item item3 = new WeightedRandom.Item(25); //horseshoe
        private final static WeightedRandom.Item item4 = new WeightedRandom.Item(10); //diamond
        private final static WeightedRandom.Item item5 = new WeightedRandom.Item(5); //7
        private final static WeightedRandom.Item itemL = new WeightedRandom.Item(2); //lucky

        private static ArrayList<WeightedRandom.Item> leftMidArray = new ArrayList(5);
        private static ArrayList<WeightedRandom.Item> rightArray = new ArrayList(6);
        static {
                leftMidArray.add(item1);
                leftMidArray.add(item2);
                leftMidArray.add(item3);
                leftMidArray.add(item4);
                leftMidArray.add(item5);
                rightArray.add(item1);
                rightArray.add(item2);
                rightArray.add(item3);
                rightArray.add(item4);
                rightArray.add(item5);
                rightArray.add(itemL);
        }
        private final static WeightedRandom wr1 = new WeightedRandom(leftMidArray);
        private final static WeightedRandom wr2 = new WeightedRandom(rightArray);

        public Reel (int locat){
                this.location = locat;
        }
        public Reel (){
        }

        public void rollReel(){         //method used to roll the reel changing the values in all three boxes while also ensuring there are no 2 same values on one reel
                

                boolean good = false;
                while (!good) {
                        topBox = getRandom();
                        middleBox = getRandom();
                        bottomBox = getRandom();
                        if (topBox != middleBox && topBox != bottomBox && middleBox != bottomBox) {
                                good = true;
                        }
                } 
        }

        private int getRandom() {
                WeightedRandom.Item i;
                if (location == 2) {
                        i = wr2.get(rand);
                } else {
                        i = wr1.get(rand);
                }
                if (i == item1) {
                        return 1;
                } else if (i == item2) {
                        return 2;
                } else if (i == item3) {
                        return 3;
                } else if (i == item4) {
                        return 4;
                } else if (i == item5) {
                        return 5;
                } else if (i == itemL) {
                        return 6;
                }
                return 0; //should never happen
        }

        public int[] getReel(){         //method used to get the instance variables of the reel in the form of an array
                int[] lst = {this.topBox,this.middleBox,this.bottomBox};        //create the array to be returned
                return lst;             //return the array we created
        }
        
        public int getLocation() {
                return location;
        }
        
        //method used to set the initial reel values for the saved game
        public void setReelValues(int [] reelValues){
                if ((reelValues[0] < 6) &&(reelValues [0] > 0)){
                        topBox = reelValues[0];
                }
                if ((reelValues[1] < 6) &&(reelValues [1] > 0)){
                        middleBox = reelValues[1];
                }
                if ((reelValues[2] < 6) &&(reelValues [2] > 0)){
                        bottomBox = reelValues[2];
                }
        }
  
}

