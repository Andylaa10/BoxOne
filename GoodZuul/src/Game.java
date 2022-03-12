import java.util.ArrayList;
import java.util.List;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Room> backRooms = new ArrayList<>();
    private ArrayList<Room> inventory = new ArrayList<>();


    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        ArrayList<Room> backRooms = new ArrayList<>();
        ArrayList<Room> inventory = new ArrayList<>();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExits("east", theater);
        outside.setExits("south", lab);
        outside.setExits("west", pub);
        theater.setExits("west", outside);
        pub.setExits("east", outside);
        lab.setExits("north", outside);
        lab.setExits("east", office);
        office.setExits("west", lab);

        outside.addItem("dog", "Looks like Nicklas");
        theater.addItem("popcorn", "salty cold popcorn");
        pub.addItem("beer", "Sexy cold beer");
        lab.addItem("laptop", "eww, nasty ass Macbook");
        office.addItem("woman", "Looks like Nicklas' mom, thicc and sexy");
        currentRoom = outside;  // start game outside

    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {

        CommandWord commandWord = commandWord;
        boolean wantToQuit = false;

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case BACK:
                goBack();
                break;
            case LOOK:
                look();
                break;
            case INSPECT:
                inspect(command);
            case EAT:
                System.out.println("You have eaten\n" +
                        "now and you are not hungry any more.");
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }


    private void goBack() {
        if (backRooms.isEmpty()){
            System.out.println("There is no back donkey");
            return;
        }
        currentRoom = backRooms.get(backRooms.size() - 1);
        System.out.println(currentRoom.getLongDescription());
        backRooms.remove(backRooms.size()-1);
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
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
        if (nextRoom == null)
            System.out.println("There is no door!");
        else{
            backRooms.add(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(backRooms);
        }
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

    /**
     * This prints out the look of the environment
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    private void inspect(Command command){
        if (!command.hasSecondWord()){
            System.out.println("what item?");
        }
        String item = command.getSecondWord();
        String description = currentRoom.getItemDescription(item);
        System.out.println(description);
    }

    private void printInventory() {
        String output = " ";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).getItemString();
        }
        System.out.println("You are carrying:");
        System.out.println(output);
    }

}
