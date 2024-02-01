/**
    23013604   
    Muhammad Chikhoun
*/

import java.util.ArrayList; // import the ArrayList class
import java.util.LinkedList;//import the linledList class


/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{

    private int currentWeight;
    private int objectivesCompleted;
    private final int maxWeight = 7;//the maximum weight should never change
    
    private LinkedList<Room> path = new LinkedList<Room>();
    private ArrayList<Items> inventory = new ArrayList<Items>();
    
    

    /**
     * Constructor for objects of class Player
     * This creates the object that the user will be acting controlling
     * 
     */
    public Player()
    {
        // initialise instance variables
        this.currentWeight = 0;
        this.objectivesCompleted = 0;

    }

    /**
     * this method loops through inventory and prints out all the item names in the inventory
     *
     */
    
    public void showInventory()
    {
        if(inventory.size() == 0){
            System.out.println("Your inventory is empty");
        }
        else{
           for (Items i: inventory){
            System.out.println(i.getName());
            }
        }
        System.out.println(" your current weight is " + currentWeight + "/" + maxWeight);
    }
    
    /**
     * this method returns the correct weight of the players inventory
     *  @return current weight
     */
    
    public int getCurrentWeight()
    {
       return currentWeight;
    }
    
    /**
     * this method rertuns the maximum weight the players inventory can hold
     *@return max weight
     */

    public int getMaxWeight(){
        return maxWeight;
    }
    
    /**
     * this method picks up an item and adds it to the inventory
     * @param item that is being picked up
     */

    public void pickupItem(Items item){
        inventory.add(item);
        currentWeight = currentWeight + item.getWeight();
        item.setInInventory();
    }
    
    /**
     * this method removes an item from the invetory
     * @param item that is being dropped
     * @param the room the item is being dropped into
     */
    public void dropItem(Items item,Room room){
        int loopCounter = 0;
        for(Items i : inventory){
            if(i.equals(item)){
                inventory.remove(loopCounter);
                currentWeight = currentWeight - item.getWeight();
                item.setCurrentLocation(room);
                item.setInInventory();
                break;
            }
            else{
                loopCounter += 1;
            }
        }
    }
    
    /**
     * checks if there are any prior rooms to go back to
     * @param the room the player is in
     * @return if the player is in the room they started in so they cant go back
     */
    public boolean checkPath(Room room){
        if(path.getFirst()==path.getLast()){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * this method removes an item from the invetory
     * @param item that is the player is giving
     * @param the room the player and character receiving the item you are in
     */
    public void giveItem(Items item, Room room){
        int loopCounter = 0;
        for(Items i : inventory){
            if(i.equals(item)){
                inventory.remove(loopCounter);
                currentWeight = currentWeight - item.getWeight();
                item.setCurrentLocation(room);
                item.setInInventory();
                item.setCanPickup();
                break;
            }
            else{
                loopCounter += 1;
            }
        }
    }
    
    /**
     * this method adds a room that the player enters to the linked list
     * @param the room the player is in
     */
    public void addPath(Room room){
        path.addLast(room);
    }
    
    /**
     * adds rooms to the linked list of rooms allowing the use of the back button
     * @param the room the player just entered
     */
    public Room goBack(){
        path.removeLast();
        Room lastRoom = path.getLast();
        return lastRoom;
    
    }
    
    /**
     * gets the previous room the player was in
     * @return the room the player just entered
     */
    public Room getLastRoom(){
        Room lastRoom = path.getLast();
        return lastRoom;
    }
    
    /**
     * adds one to the number of objectives the player has completed
     *
     */
    public void addOne(){
        objectivesCompleted +=1;
    }
    
    /**
     * gets the number of objectives the player has completed
     * @return the number of objectives the player has completed
     */
    public int getObjectivesCompleted(){
        return objectivesCompleted;
    }
}
