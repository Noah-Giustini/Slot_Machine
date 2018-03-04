import java.util.ArrayList;
import java.util.Random;

public class WeightedRandom {
    //This class generates weighted random numbers
    
    private ArrayList<Item> items; //list of items
    
    public WeightedRandom(ArrayList<Item> i) {
        //just a standard constructor
        items = i;
    }
    public Item get(Random random) {
        //This method gets a random item from the list of items
        //in accordance with their weights
        
        double total = getTotalWeight(); //uniform random number from 0 to the total weight
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