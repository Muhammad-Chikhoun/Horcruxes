/**
    23013604   
    Muhammad Chikhoun
*/

import java.util.ArrayList; // import the ArrayList class
import java.util.Random; //import the random class

/**
 * Write a description of class Character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String name;
    private boolean challengeCompleted;
    private Room currentRoom;
    private Items requiredItem;
    private ArrayList<Room> path = new ArrayList<Room>();
    
    Random random = new Random();
    
    /**
     * Constructor for objects of class Character
     * @param the name of the character
     * @param whether the challenge/objective associated with the character is complete
     * @param which room they are in
     * @paramw what item they need
     */
    public Character(String name, boolean challengeCompleted,Room currentRoom,Items requiredItem)
    {
        this.name = name;
        this.challengeCompleted = challengeCompleted;
        this.currentRoom = currentRoom;
        this.requiredItem = requiredItem;
        this.path = path;        
    }

    /**
     * method to add rooms which the character moves between
     * @param the roomn added to the character's path
     */    
    public void addPath(Room room1, Room room2){
        path.add(room1);
        path.add(room2);
    }
    
    /**
     * method to move the character randomly between the neighbouring rooms
     * 
     */ 
    public void moveCharacter(){
        int randomNumber = random.nextInt(2);
        currentRoom=path.get(randomNumber);
    }
    
    /**
     * method to get the name of the character
     * @return the name of the character
     */ 
    public String getName(){
        return name;
    }
    
    /**
     * method to move the character randomly between the neighbouring rooms
     * 
     */ 
    public Items getRequiredItem(){
        return requiredItem;
    }
    
    /**
     * method to get the current room the character is in
     * @return the room of the character
     */ 
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * method to check if the characters challenge is completed
     * @return whether the challegen has been completed 
     */
    public boolean getChallengeCompleted(){
        return challengeCompleted;
    }
    
    /**
     * method to set the challenge to complete
     *
     */
    public void setChallengeCompleted(){
        challengeCompleted = true;
    }

}
    
    


