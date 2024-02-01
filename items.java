/**
    23013604   
    Muhammad Chikhoun
*/

/**
 * The class for creating items used in the game
 * 
 * create items to be used in the game with the appropriate attributes 
 * 
 * @author Muhamamd Chikhoun
 * @version 6.12.2023
 */
public class Items
{
    private String name;
    
    private int weight;
    
    private Room currentLocation;
    
    private boolean canPickup;
    private boolean inInventory;
    private boolean canUse;
    private boolean destroyed;


    /**
     * Constructor for objects of class Items
     * 
     * @param name of the item
     * @param description of the item
     * @param how heavy it is
     * @param what room the item is in
     * @param whether the item can be picked up
     * @param whether the item is in the inventory
     * @param whether the item can be used
     * @param whether the item is destroyed
     * 
     */
    public Items(String name,String description, int weight, Room currentLocation, boolean canPickup, boolean inInventory, boolean canUse, boolean destroyed)
    {
        this.name = name;
        this.weight = weight;
        this.currentLocation = currentLocation;
        this.canPickup = canPickup;
        this.inInventory = inInventory;
        this.canUse = canUse;
        this.destroyed = destroyed;
    }
    
    /**
     * 
     * @return the room in which the item is located 
     */
    public Room getCurrentLocation(){
        return currentLocation;
    }
    
    /**
     * 
     * @return the name of the item is located 
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return the item's weight
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     * check to see if the item is in the inventory
     * @return true if it is in the inventory
     */    
    
    public boolean getInInventory(){
        return inInventory;
    }
    
    /**
     * check to see if the item can be picked up
     * @return true if it can be picked up
     */    
    public boolean getCanPickup(){
        return canPickup;
    }
    
    /**
     * 
     * @return return whether the item can be used on other items
     */  
    public boolean getCanUse(){
        return canUse;
    }
    
    /**
     * 
     * @return return whether the item has been destroyed 
     */  
    public boolean getDestroyed(){
        return destroyed;
    }
    
    /**
     * Change the state of the boolean so the item can be added or removed from the inventory
     * 
     */    
    public void setInInventory(){
        inInventory = !inInventory;
    }
    
    /**
     * Change the location of the room if it is dropped in a different room from where it is picked up
     * 
     */  
    public void setCurrentLocation(Room room){
        currentLocation = room;
    }
    
    /**
     * Change the state of the boolean so the item can no longer be picked up
     * 
     */  
    public void setCanPickup(){
        canPickup = false;
    }
    
    
    /**
     * Change the state of the boolean so the item is recognised as destroyed
     * 
     */  

    public void setDestroyed(){
        destroyed = true;
    }

}
    
