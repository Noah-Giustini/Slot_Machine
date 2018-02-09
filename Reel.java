import java.util.Random;        //import the random utilities for sudo random number generation

/*
*       This class is used to keep track of the data of each individual reel including the values of each of its 3 positions
*       stored in instance variables, as well as methods to roll the reel changing the values as well as a method to return 
*       these values in an array.
*/

public class Reel{
        private int topBox;             // instance variable for the top box value
        private int middlebox;          //instance variable for the middle box value
        private int bottomBox;          //instance variable for the bottom box value

        public void rollReel(){         //method used to roll the reel changing the values in all three boxes while also ensuring there are no 2 same values on one reel
                int max = 5;            //largest possible value for the reels
                int min = 1;            //smallest possible value that can end up on the reels
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

                }
        }
        public int[] getReel(){         //method used to get the instance variables of the reel in the form of an array
                int[] lst = {this.topBox,this.middlebox,this.bottomBox};        //create the array to be returned
                return lst;             //return the array we created
        }
}