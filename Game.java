/**
    23013604   
    Muhammad Chikhoun
*/

import java.util.ArrayList; // import the ArrayList class
import java.util.Random; //import the random class
/**
 * This class is the main class of the "The Horcruxes" application. 
 * "The Horcruxes" is a very simple, text based adventure game.
 * Users walk around scenery collecting objects to finish objectives.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, characters, items, and objectives.
 *  It creates the parser and starts the game.
 *  It also evaluates and executes the commands that the parser returns.

 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private boolean finished = false;
    
    private ArrayList<Items> allItems = new ArrayList<Items>();
    private ArrayList<Character> allCharacters = new ArrayList<Character>();
    private ArrayList<Objective> allObjectives = new ArrayList<Objective>();
    private ArrayList<Room> allRooms = new ArrayList<Room>();
    
    private Room emptyRoom;
    private Items emptyItem;
    private Character emptyCharacter;
    
    Random random = new Random();
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        initialiseGame();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all the items and place them in their allocated rooms, and add them to the arraylist with all items
     * Create all the characters, assign the rooms they walk between, and add them to an arraylist with all the characters
     * Create all the objectives and place them in an arraylist with all the objectives to complete in order to win
     */
    private void initialiseGame()
    {
        //Rooms
        Room dumbledoresOffice, hallway, roomOfRequirement, gryffindorCommonRoom, bellatrixsVault, ministryOfMagic, toilets, greatHall, ravenclawCommonRoom, forbiddenForest, chamberOfSecrets;
        
        
        // create the rooms
        dumbledoresOffice = new Room("in Dumbledore's Office");
        hallway = new Room("in the hallway");
        roomOfRequirement = new Room("in the room of requirement");
        gryffindorCommonRoom = new Room("in the Gryffindor common room");
        bellatrixsVault = new Room("in Bellatrix's vault");
        ministryOfMagic = new Room("in the Ministry of magic");
        toilets = new Room("in the toilets");
        greatHall = new Room("in the great hall");
        ravenclawCommonRoom = new Room("in the Ravenclaw common room");
        forbiddenForest = new Room("in the forbidden forest");
        chamberOfSecrets = new Room("in the chamber of secrets");
        
        
        
        
        // initialise room exits
        hallway.setExit("north",dumbledoresOffice);
        hallway.setExit("east",gryffindorCommonRoom);
        hallway.setExit("south",greatHall);
        hallway.setExit("west",roomOfRequirement);
        
        gryffindorCommonRoom.setExit("north",bellatrixsVault);
        gryffindorCommonRoom.setExit("east",ministryOfMagic);
        gryffindorCommonRoom.setExit("south",toilets);
        gryffindorCommonRoom.setExit("west",hallway);
                
        greatHall.setExit("north",hallway);
        greatHall.setExit("east",toilets);
        greatHall.setExit("south",forbiddenForest);
        greatHall.setExit("west",ravenclawCommonRoom);
                
        toilets.setExit("north",gryffindorCommonRoom);
        toilets.setExit("south",chamberOfSecrets);
        toilets.setExit("west",greatHall);
        
        ministryOfMagic.setExit("west",gryffindorCommonRoom);
        
        bellatrixsVault.setExit("south",gryffindorCommonRoom);
        
        ravenclawCommonRoom.setExit("east",greatHall);
        
        forbiddenForest.setExit("north",greatHall);

        chamberOfSecrets.setExit("north",toilets);
        
        roomOfRequirement.setExit("east",hallway);
        
        dumbledoresOffice.setExit("south",hallway);
        
        
        // start game in Dumbledore's office
        currentRoom = dumbledoresOffice;  
        
        
        //adding items to a list of all items
        allRooms.add(hallway);
        allRooms.add(gryffindorCommonRoom);
        allRooms.add(greatHall);
        allRooms.add(toilets);
        allRooms.add(ministryOfMagic);
        allRooms.add(bellatrixsVault);
        allRooms.add(ravenclawCommonRoom);
        allRooms.add(forbiddenForest);
        allRooms.add(chamberOfSecrets);
        allRooms.add(dumbledoresOffice);
        allRooms.add(roomOfRequirement);
        
        
        
        
        //items
        Items diary, ring, locket, cup, diadem, sword, fang;
        
        
        // create items
        diary = new Items("diary","Tom Riddle's Diary",1,gryffindorCommonRoom, true,false,false,false);
        ring = new Items("ring","Marvolo Gaunt's Ring",1,dumbledoresOffice,false,false,false,false);
        locket = new Items("locket","Salazar Slytherin's Locket",2,ministryOfMagic,true,false,false,false);
        cup = new Items("cup","Helga Hufflepuff's Cup",2,bellatrixsVault,true,false,false,false);
        diadem = new Items("diadem","Rowena Ravenclaw's Diadem",2,ravenclawCommonRoom,true,false,false,false);
        sword = new Items("sword","Godric Gryffindor's Sword",5,gryffindorCommonRoom,true,false,true,false);
        fang = new Items("fang","Basilisk Fang",1, chamberOfSecrets,true,false,true,false);
        
        
        //adding items to a list of all items
        allItems.add(diary);
        allItems.add(ring);
        allItems.add(locket);
        allItems.add(cup);
        allItems.add(diadem);
        allItems.add(sword);
        allItems.add(fang);
        
        
        
        //Characters
        Character dumbledore, ron, hermione, neville;
        
        
        //Creating the characters
        dumbledore = new Character("dumbledore",false, dumbledoresOffice, fang);
        ron = new Character("ron", false, gryffindorCommonRoom, locket);
        hermione = new Character("hermione",false,hallway, cup);
        neville = new Character("neville", false, hallway, sword);
        
        dumbledore.addPath(dumbledoresOffice,dumbledoresOffice);
        hermione.addPath(hallway,greatHall);
        neville.addPath(hallway,greatHall);
        ron.addPath(gryffindorCommonRoom,gryffindorCommonRoom);
        
        //adding objectives to a list of all characters
        allCharacters.add(dumbledore);
        allCharacters.add(ron);
        allCharacters.add(hermione);
        allCharacters.add(neville);
        

         
        //Objectives
        Objective destroyDiary,destroyDiadem,giveFang,giveLocket,giveCup,giveSword,goForest;
        
        
        //Creating the objectives
        destroyDiary = new Objective(false, "Destroy Tom Riddle's diary using the sword of Gryffindor or the basilisk's fang");
        destroyDiadem = new Objective(false, "Destroy Rowena Ravenclaw's diadem sword of Gryffindor or the basilisk's fangg");
        giveFang = new Objective(false, "Give Dumbledore the basalisk's fang so he can destroy Marvolo Gaunt's Ring");
        giveLocket = new Objective(false, "Give Ron the Salazar Slytherin's Locket");
        giveCup = new Objective(false, "Give Hermione Helga Hufflepuff's Cup");
        giveSword = new Objective(false, "Give Neville the Sword Of Gryffindor");
        goForest = new Objective(false, "Go to the Forbidden Forest once all the horcruxes are destroyed to face Voldemort");
        
        
        //adding objectives to a list of all objectives
        allObjectives.add(destroyDiary);
        allObjectives.add(destroyDiadem);
        allObjectives.add(giveFang);
        allObjectives.add(giveLocket);
        allObjectives.add(giveCup);
        allObjectives.add(giveSword);
        allObjectives.add(goForest);

    }
    
    /**
     *  Main play routine.  Loops until end of play.
     *  Play ends when the game is quit, or objectives are all complete
     */
    public void play() 
    {            
        printWelcome();
        finished = false;
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
            
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("\nDumbledore:\n");
        System.out.println("Welcome Harry, to Hogwarts\n");
        System.out.println("Your Task is to find and destory all of Voldemort's horcruxes and then go off to face him yourself. \nYou will destroy some horcruxes on your own, and Myself, Ron, Hermione, and Neville will help");
        System.out.println("I currently have the first horcrux, but I need the Basilik's Fang from the chamber of secrets to destroy it. Start by retrieving that for me.");
        System.out.println("You can complete your objectives in any order except for facing Voldemort which you must do after destroying all the horcruxes to weaken him.\n");
        System.out.println("\nYou can use the floo network from the Gryffindor common room to go to places outside the castle");
        System.out.println("If you go to the room of  requirement, it will take you to a random room");
        System.out.println("\nYou can find Ron in the Gryffindor common room");
        System.out.println("You can find Hermione and Neville walking between the hallway and the great hall");
        System.out.println("Type 'help' to see the list of commands and objectives.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        player.addPath(currentRoom);
        whatsInTheRoom();
        whosInTheRoom();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        int loopCounter = 0;
        String commandWord = command.getCommandWord();//For all commands
        String itemName = command.getSecondWord(); //For pickup and drop commands
        
                
        //Command Regocnition
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        
        if (commandWord.equals("help")) {
            printHelp();
        }
        
        
        
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        
        
        
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        
        
        
        else if (commandWord.equals("inventory")){
            player.showInventory();
        }
        
        
        
        else if (commandWord.equals("back")){
            Room lastRoom = player.getLastRoom();
            if(player.checkPath(currentRoom) == true){
                System.out.println("You can't go any further back!");
            }
            else{
                currentRoom = player.goBack();
                System.out.println("/n" + currentRoom.getLongDescription());
                whatsInTheRoom();
                whosInTheRoom();
            }
        }
        
        
        
        else if (commandWord.equals("pickup")){
            
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know what to pickup...
                System.out.println("I dont know what item to pickup?");
            }
            
            
            else{
                for(Items i: allItems){
                    loopCounter += 1;
                    if(itemName.equals(i.getName())){
                        pickupItem(command, i);
                        break;
                    }
                    if(loopCounter ==7 && !(itemName.equals(i.getName()))){
                        System.out.println(itemName + " is not an item.");
                    }
                }
            }
        }
        
        
        
        else if (commandWord.equals("drop")){

            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know what to drop...
                System.out.println("I dont know what item to drop?");
            }
            
            
            else{
                for(Items i: allItems){
                    loopCounter += 1;
                    if(i.getInInventory() == true && i.getName().equals(itemName)){
                        player.dropItem(i,currentRoom);
                        System.out.println("You dropped " + itemName + " your current weight is " + player.getCurrentWeight() + "/" + player.getMaxWeight());
                        break;
                    }
                    if(loopCounter ==7 && !(itemName.equals(i.getName()))){
                        System.out.println(itemName + " is not in your inventory");
                    }
                }
            }
        }
        
        
        
        else if(commandWord.equals("use")){
            String itemName1 = command.getSecondWord();
            String itemName2 = command.getThirdWord();
            boolean error = false;
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know what item to use...
                System.out.println("I dont know what item to use?");
            }
            
            
            else if(!command.hasThirdWord()){
                // if there is no second word, we don't know what item to use it on...
                System.out.println("I dont know what to use " + itemName1 + " on?");
            }
            
            
            else{
                outerloop:
                for(Items i: allItems){
                    loopCounter += 1;
                    for(Items j: allItems){
                        if(itemName1.equals(i.getName()) && i.getInInventory() == true && itemName2.equals(j.getName()) && j.getInInventory() == true){
                            useItem(command,i,j,currentRoom);
                            break outerloop;
                        }
                        
                        else if(loopCounter ==7 && (!(itemName1.equals(i.getName())) || !(itemName2.equals(i.getName())))){
                            error = true;
                        }
                    }
                }
            }
            if(error == true){
                System.out.println(itemName1 + " " + itemName2 + " are not in your inventory to use");
            }    
        }
        
        
        
        else if(commandWord.equals("give")){
            String person = command.getSecondWord();
            String item = command.getThirdWord();
            boolean error = false;
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know who to give the item...
                System.out.println("I dont know who you or what you want me to give?");
            }
             
            
            else if(!command.hasThirdWord()){
                // if there is no second word, we don't know where to go...
                System.out.println("What do you want me to give them?");
            }
            
            
            else{
                outerloop:
                for(Character p: allCharacters){
                    loopCounter += 1;
                    for(Items i: allItems){
                        if(p.getName().equals(person) && i.getName().equals(item)){
                            giveItem(p,i,command);
                            break outerloop;
                        }
                        
                        else if(loopCounter == 4 && (!(person.equals(p.getName())) || !(item.equals(i.getName())))){
                            error = true;
                        }
                    }
                }
            }
            
            if(error == true){
                System.out.println("That person and/item do not exist");
            } 
        }
        
        for(Character c : allCharacters){
            c.moveCharacter();
        }
        
        if(currentRoom.getLongDescription().contains("forbidden forest") && player.getObjectivesCompleted() == 6){
            System.out.println("\n----------------------------------------------\nYour destroyed all the horcruxes, and will now face voldemort.\nGet part two of the game to fight him");
            return true;
        }
        
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * command words.
     * objectives
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
        printObjectives();
        
    }
    
    private void printObjectives(){
        System.out.println("\nYour remaining objectives are:");
        
        for(Objective i: allObjectives){
            if(i.getDone() == false){
                System.out.println(i.getDescription());
            }
        }
    }
    
    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The command to be processed.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            System.out.println("\n" + currentRoom.getLongDescription());
            currentRoom = nextRoom;
            if(currentRoom.getShortDescription().equals("in the room of requirement")){
                System.out.println("You enter the room of requirement and You teleport");
                teleport();
            }
            else{
                player.addPath(currentRoom);
                System.out.println("\n" + currentRoom.getLongDescription());
                whatsInTheRoom();
                whosInTheRoom();
            }
        }
    }

    /** 
     * Pickup an item and add it to the inventory
     * Print error if the item isnt in the room
     * or the weight exceeds inventory capabilities
     * or the item isnt allowed to be picked up
     * or already in the inventory
     * @param command The command to be processed
     * @param Items The item to be picked up
     */
    private void pickupItem(Command command,Items i) 
    {
        String itemName = command.getSecondWord();
        boolean allAttributesCorrect = true;
        if (!currentRoom.equals(i.getCurrentLocation())) {
                System.out.println("That item is not in this room");
                allAttributesCorrect = false;
        }
        else if (i.getDestroyed() == true){
                System.out.println("That item is destroyed, you cant pick it up");
                allAttributesCorrect = false; 
        }
        else if (!(player.getCurrentWeight() + i.getWeight() <= player.getMaxWeight())) {
                System.out.println("Its too heavy, your inventory is full");
                allAttributesCorrect = false;  
        }
        else if (i.getCanPickup() == false) {
                System.out.println("Cant pick the "+ i + " up, its cursed");
                allAttributesCorrect = false;  
        }
        else if (i.getInInventory() == true){
                System.out.println("Already in your inventory");
                allAttributesCorrect = false; 
        }
        else if (allAttributesCorrect) {
                // All conditions are met
                player.pickupItem(i);
                System.out.println("You picked up " + itemName + " your current weight is " + player.getCurrentWeight() + "/" + player.getMaxWeight());
        }
    }
    
    /** 
     * Use an item on another item from the inventory
     * Print error if either item isnt in the inventory
     * or the item can't be used in that manner
     * @param command The command to be processed
     * @param Items The item to be used
     * @param Items The item to be used on
     * @param room the item is dropped into once it is destroyed
     */
    private void useItem(Command command, Items item, Items item2, Room room){
        String itemName = command.getSecondWord();
        String itemName2 = command.getThirdWord();
        
        if(item.getCanUse() == true && itemName2.equals("diary") && item2.getDestroyed()==false){
            item2.setDestroyed();
            System.out.println(item2.getDestroyed());
            player.dropItem(item2,currentRoom);
            System.out.println("You Destroyed " + itemName2 + " and dropped it. your current weight is " + player.getCurrentWeight() + "/" + player.getMaxWeight());
            
            for(Objective o: allObjectives){
                if(o.checkDescription(itemName2)){
                        o.completeObjective();
                }
            }
            printObjectives();
            player.addOne();
        }
        
        else if(item.getCanUse() == true && itemName2.equals("diadem") && item2.getDestroyed()==false){
            item2.setDestroyed();
            System.out.println(item2.getDestroyed());
            player.dropItem(item2,currentRoom);
            System.out.println("You Destroyed " + itemName2 + " and dropped it. Your current weight is " + player.getCurrentWeight() + "/" + player.getMaxWeight());
                        
            for(Objective o: allObjectives){
                if(o.checkDescription(itemName2)){
                        o.completeObjective();
                }
            }
            printObjectives();
            player.addOne(); 
        }
        
        else{
            System.out.println("You cant use " + itemName + " on " + itemName2);
        }
        
    }
    
    /** 
     * Give an item to a character in the room
     * Print error if the item isnt in the inventory
     * or the character isnt in the room
     * or the item can't be given to that character
     * @param command The command to be processed
     * @param Items The item to be given
     * @param character the person who receives the item
     * 
     */
    private void giveItem(Character person, Items item, Command command){
        String name = command.getSecondWord();
        String itemName = command.getThirdWord();
        boolean allAttributesCorrect = true;
        if (!currentRoom.equals(person.getCurrentRoom())) {
                System.out.println(name+" is not in this room");
                allAttributesCorrect = false;
        }
        else if (!person.getRequiredItem().equals(item)){
                System.out.println(name+" does not want "+itemName);
                allAttributesCorrect = false;  
        }
        else if (item.getInInventory() == false){
                System.out.println(itemName + " is not in your inventory");
                allAttributesCorrect = false; 
        }
        else if (person.getChallengeCompleted() == true){
                System.out.println("Already given "+itemName+" to them");
                allAttributesCorrect = false; 
        }
        else if (allAttributesCorrect) {
                // All conditions are met
                person.setChallengeCompleted();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                System.out.println("you gave " + itemName + " to " + person.getName());
                for(Objective o: allObjectives){
                    if(o.checkDescription(name)){
                        o.completeObjective();
                    }
                }
                player.giveItem(item,currentRoom);
                printObjectives();
                player.addOne();
        }
        
    }
    
    /** 
     * Check and print all the characters in the room
     */
    private void whosInTheRoom(){
        boolean roomEmpty = true;
        for(Character c :allCharacters){
            if(c.getCurrentRoom().equals(currentRoom)){
                System.out.println("You see " + c.getName() + " in the room");
                roomEmpty = false;
            }
        }
        
        if(roomEmpty == true){
            System.out.println("No characters in the room");
        }
    }
    
    /** 
     * Check and print all the items in the room
     */
    private void whatsInTheRoom(){
        boolean roomEmpty = true;
        for(Items i :allItems){
            if(i.getCurrentLocation().equals(currentRoom)&&i.getInInventory()==false&&i.getCanPickup()==true){
                System.out.println("You see the " + i.getName()+ " in the room");
                roomEmpty = false;
            }
        }
        
        if(roomEmpty == true){
            System.out.println("No items in the room");
        }
    }
    
    private void teleport(){
        int randomNumber= random.nextInt(10);
        currentRoom = allRooms.get(randomNumber);
        player.addPath(currentRoom);
        System.out.println("\n" + currentRoom.getLongDescription());
        whatsInTheRoom();
        whosInTheRoom();
            
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
