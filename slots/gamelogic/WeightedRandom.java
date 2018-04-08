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
        double total = getTotalWeight();
        double current = random.nextDouble() * total;
        try {
            for (Item i: items) {
                // get a random element with the weights
                if (i.getWeight() > current) {
                    return i;
                }
                current -= i.getWeight();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
        return null;
        // The code should never reach this point
    }

    private double getTotalWeight() {
        //Gets the sum of the weights of all the items in the list
        //This method is used by the get() method
        double w = 0.0;
        for (Item i: items) {
            w += i.getWeight();
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

        public Item(double w) {
            //just a standard constructor
            weight = w;
        }
        public double getWeight() {
            //just a standard getter method
            return weight;
        }
    }
}
