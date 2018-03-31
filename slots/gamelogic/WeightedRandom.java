package slots.gamelogic;

import java.util.ArrayList;
import java.util.Random;


/** The weightedRandom class, generates a random symbol for a position within a reel dependant upon how 
  * likely it is for a specific item to be within a reel thus simulating an actual slot machine.
  *
  * @var    items - an array list containing all of the items (cherry, watermelon, lucky, etc) that
  *         could be in a particular reel
  */
public class WeightedRandom {    
    private ArrayList <Item> items = new ArrayList <Item> (); 
    
    /** Constructor
      *
      * The user has given an array list of all of the different items that could be in a particular
      * reel for the position it is given. The for loop is used to iterate through the inputted arraylist
      * and add each item to the items array list. A for loop is used to prevent against privacy leaks so
      * that the array lists are not the same, when this for loop is iterated through a new Item is created
      * to ensure that no privacy leaks occur
      *
      * @param     i - an array list containing all of the items for a particular reel within the game
      */
    public WeightedRandom(ArrayList<Item> i) {
        for (item: i){
            items.add(new Item(item.getWeight());
        }
    }
 
 
    /** The method, get, gets a random item from the array list of items (items) in accordance with their weights.
      * It does this by getting the weight of a particular item which is a uniform random number from 0 to the total weight.
      * Then using the java Random class, the method gets a new random double which is multiplied by the total weight of
      * the current array list. The method then uses a for loop to find the next item with the */
    public Item get(Random random) {
        double total = getTotalWeight();
        double current = random.nextDouble() * total;
        for (Item i : items) {
            // get a random element with the weights
            if (i.getWeight() > current) {
                return i;
            }
            current -= i.getWeight();
        }
        return null;
        // The code should never reach this point
    }
    private double getTotalWeight() {
        //Gets the sum of the weights of all the items
        //This method is used by the get() method
        double w = 0.0;
        for (Item i : items) {
            w += i.getWeight();
        }
        return w;
    }
    
    /**/
    public static class Item {
        //This classes can be accessed by typing WeightedRandom.Item
        private double weight;
        
        public Item(double w) {
            //just a standard constructor
            weight = w;
        }
        public double getWeight() {
            //just a standard getter method
            return weight;
        }
    }
    
    public static void main(String[] args) {
        //THIS METHOD IS A TEST METHOD
        //IT IS NOT USED IN THE ACTUAL GAME
        
        Item i = new WeightedRandom.Item(3);
        Item j = new WeightedRandom.Item(10);
        Item k = new WeightedRandom.Item(100);
        ArrayList<Item> a = new ArrayList<Item>();
        a.add(i);
        a.add(j);
        a.add(k);
        WeightedRandom wr = new WeightedRandom(a);
        Random r = new Random();
        for (int ii=0; ii<100; ii++) {
            System.out.println(wr.get(r).getWeight());
        }
    }
}
