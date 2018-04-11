package slots.gamelogic;

import java.util.ArrayList;
import java.util.Random;


/** The weightedRandom class, generates a random symbol for a position within a reel dependent upon how 
 * likely it is for a specific item to be within a reel thus simulating an actual slot machine.
 *
 * @var    items - an array list containing all of the items (cherry, watermelon, lucky, etc) that
 *         could be in a particular reel
 */
public class WeightedRandom {
    private ArrayList < Item > items;

    /** Constructor
     *
     * The user has given an array list of all of the different items that could be in a particular
     * reel for the position it is given. Since the array list is set to the arraylist of items without
     * making a copy this is a potential spot for a privacy leak, however since Items are static this
     * privacy leak cannot be prevented without messing up the game.
     *
     * @param     i - an array list containing all of the items for a particular reel within the game
     */
    public WeightedRandom(ArrayList < Item > i) {
        items = i;
    }

    /** This method chooses one item randomly from the list of items, while respecting
     * weights.  For example, if X = new Item(1.0) and Y = new Item(3.0), then
     * a WeightedRandom instance with {X,Y} as its list will choose Y 3 times as
     * often as X, because Y's weight has 3 times greater than X's weight. The method will cascade
     * ArrayIndexOutOfBoundsExceptions if they occur
     *
     * @param      random - an instance of Random to use.
     */
    public Item get(Random random) throws ArrayIndexOutOfBoundsException {
        double total = getTotalWeight(); //Get the total weight of all the items
        double current = random.nextDouble() * total; //Get a random number from 0 to the total weight.
        
        /** The algorithm works as follows (this is an analogy).
         * Think of a line with a length equal to the total weight. Then you split up the line into
         * some parts, where each part has a length equal to one of the items. So if your weights are
         * Then it selects a random point on the line and finds which section it landed on. It returns
         * the item corresponding to that section. So if you have items of weights 3, 8, and 2, the
         * line would look like this: 3338888888822.
         */
        
        try {
            for (Item i: items) {
                // Iterate over all the items.
                // It will return the first item if the random number is smaller than the item's weight.
                // If not, then it subtracts the item's weight from the random number (it will certainly
                // be positive) and then repeats the process with the next item.
                if (i.getWeight() > current) {
                    return i;
                }
                current -= i.getWeight();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
        return null; // The code should never reach this point
    }
    
   /** This method sums all the weights of all the items in the list, and returns that value.
    * This method is used by the get() method.
    */
    private double getTotalWeight() {
        double w = 0.0; //This variable keeps track of the total weight.
        for (Item i: items) {
            w += i.getWeight(); //Add the item's weight to the total weight.
        }
        return w;
    }

    /** The Item inner class of WeightedRandom represents an item.  The WeightedRandom
     * class has a list of items to choose from, and the get() method chooses one item
     * randomly, while respecting weights.  If X = new Item(1.0) and Y = new Item(3.0),
     * then a WeightedRandom instance with {X,Y} as its list will choose Y 3 times as
     * often as X, because Y's weight has 3 times greater than X's weight.
     *
     * @var   weight - a value which is the weight of the item.  A higher value means
     *                 it is more likely to be chosen by the WeightedRandom class.
     */
    public static class Item {

        private double weight;
       /** Constructor
         *
         * Creates an item with the specified weight. Items with a larger weight are
         * more likely to be chosen by the get() method of WeightedRandom.
         *
         * @param     w - the weight of the item.
         */
        public Item(double w) {
            weight = w;
        }
        
        /** Just a standard getter method.  Returns the weight of the item.
         */
        public double getWeight() {
            return weight;
        }
    }
}
