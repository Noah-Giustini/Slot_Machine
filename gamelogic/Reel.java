import java.util.Random;        //import the random utilities for sudo random number generation
import java.util.ArrayList;
import java.util.Arrays;

package gamelogic;

/*
*       This class is used to keep track of the data of each individual reel including the values of each of its 3 positions
*       stored in instance variables, as well as methods to roll the reel changing the values as well as a method to return 
*       these values in an array.
*/

public class Reel{
        private int topBox;             // instance variable for the top box value
        private int middleBox;          //instance variable for the middle box value
        private int bottomBox;          //instance variable for the bottom box value
        private int location;           //0 = left reel, 1 = middle, 2 = right
        private static Random rand = new Random();
        private static WeightedRandom.Item item1 = new WeightedRandom.Item(45); //cherry
        private static WeightedRandom.Item item2 = new WeightedRandom.Item(35); //watermelon
        private static WeightedRandom.Item item3 = new WeightedRandom.Item(25); //horseshoe
        private static WeightedRandom.Item item4 = new WeightedRandom.Item(10); //diamond
        private static WeightedRandom.Item item5 = new WeightedRandom.Item(5); //7
        private static WeightedRandom.Item itemL = new WeightedRandom.Item(2); //lucky

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
        private static WeightedRandom wr1 = new WeightedRandom(leftMidArray);
        private static WeightedRandom wr2 = new WeightedRandom(rightArray);

        public Reel (int locat){
                this.location = locat;
        }
        public Reel (){
        }

        public void rollReel(){         //method used to roll the reel changing the values in all three boxes while also ensuring there are no 2 same values on one reel
                /*
                int max;                //largest possible value for the reels
                int min = 1;               //smallest possible value that can end up on the reels
                if (this.location == 2){
                        max = 6;
                }
                else{
                        max = 5;
                }
                int x = 0;              //variable for while loop
                int i = 0;              //variable for while loop
                Random rand = new Random(); //new instance of Random called rand
                int tempTop = rand.nextInt((max - min) + 1) + min;      //generate a random number between min and max
                this.topBox = tempTop;  //set the instance variable of the top box to the number we just created
                int tempMid = rand.nextInt((max - min) + 1) + min;      //create a new random number between the min and max
                
                while (x != 1){         //while loop that is used to make sure that the top box number is not the same as what we just generated
                        if (tempMid != this.topBox){            //if the numbers are not the same
                                this.middlebox = tempMid;       //set instance variable for the middle box to the sudo random number we created
                                x = 1;                          //break from the while loop
                        }
                        else{
                                tempMid = rand.nextInt((max - min) + 1) + min;  //regenerate a new number and go back through the while loop
                        }

                }
                int tempBot = rand.nextInt((max - min) + 1) + min;      //generate a new sudo random number between min and max
                while (i != 1){        //while loop to ensure the top and middle numbers are not the same as the new one we just generated
                        if (tempBot != this.topBox && tempBot != this.middlebox){       //if the numbers are not the same
                                this.bottomBox = tempBot;                               //set the bottom box instance variable to the number we generated
                                i = 1;  //break from the loop
                        }
                        else{
                                tempBot = rand.nextInt((max - min) + 1) + min;          //generate a new random number between min and max
                        }

                }*/

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

