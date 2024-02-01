/**
    23013604   
    Muhammad Chikhoun
*/

/**
 * Write a description of class Objective here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Objective
{
    // instance variables - replace the example below with your own
    private boolean done;
    private String description;

    /**
     * Constructor for objects of class Objective
     * @param description of the objective
     * @param whether the objective is complete
     */
    public Objective(boolean done,String description)
    {
        this.description = description;
        this.done = done;

    }

    /**
     * matches the task done to its relative objective
     * @param task done 
     * @return if it matches or doesnt
     */
    public boolean checkDescription(String s){
        if(description.contains(s)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * changes the state of the objective so that it is complete
     * 
     */
    public void completeObjective(){
        done = true;
    }

    /**
     * changes the state of the objective to see if it is done
     * @return whether it is completed or not
     */
    public boolean getDone(){
        return done;
    }

    /**
     * gets the description of the objective
     * @return the desciption
     */
    public String getDescription(){
        return description;
    }
}
