/**Class: Commands
 * @author Kyle Williams, Bradley Iverson, Miranda Darlington, Rick Price
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27

 * This class distributes commands passed in by the user. It interacts with the databases based on what commands are
 * given at a any particular time.

 * Purpose: Acts as an interface between the database, and the view.
 */

package controller;

import gameExceptions.GameException;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commands {

    // Attributes
    public static final int EXIT_COMMAND = 0;
    protected static final List<String> ITEM_COMMANDS = new ArrayList<>();
    private Player player;
    protected static final List<String> VALID_DIRECTIONS = new ArrayList<>();
    protected static final List<String> CHALLENGE_COMMANDS = new ArrayList<>();
    protected static final List<String> OTHER_COMMANDS = new ArrayList<>();

    public Player getPlayer() {
        return player;
    }

    /**
     * @purpose: Constructor creates a new instance of the player class.
     */
    public Commands() {
        player = new Player();
    }

    /**
     * @param cmdLine
     * @return
     * @throws GameException
     * @purpose This method ensures that the user input is valid and if it is, it needs to be directed to the
     * appropriate method for handling.
     */
    private int validateCommand(String cmdLine) throws GameException {
        // Solution 1
        int validation = 0;

        String upperCaseCmd = cmdLine.toUpperCase();
        String[] cmdArray = cmdLine.split(" ");

        VALID_DIRECTIONS.add("NORTH");
        VALID_DIRECTIONS.add("SOUTH");
        VALID_DIRECTIONS.add("EAST");
        VALID_DIRECTIONS.add("WEST");
        VALID_DIRECTIONS.add("UP");
        VALID_DIRECTIONS.add("NORTH WEST");
        VALID_DIRECTIONS.add("NORTH EAST");
        VALID_DIRECTIONS.add("SOUTH EAST");
        VALID_DIRECTIONS.add("SOUTH WEST");
        VALID_DIRECTIONS.add("BACK");
        VALID_DIRECTIONS.add("DOWN");
        VALID_DIRECTIONS.add("FORWARD");
        VALID_DIRECTIONS.add("NEXT");

        for (String direction : VALID_DIRECTIONS)
        {
                if (cmdLine.equalsIgnoreCase(direction))
            {
                return 1;
            }
        }

        ITEM_COMMANDS.add("COLLECT");
        ITEM_COMMANDS.add("DROP");
        ITEM_COMMANDS.add("INSPECT");
        ITEM_COMMANDS.add("PACK");
        ITEM_COMMANDS.add("USE");
        ITEM_COMMANDS.add("BACKPACK");

        for (String itemCommand : ITEM_COMMANDS)
        {
            if (cmdLine.equalsIgnoreCase(itemCommand))
            {
                return 2;
            }
        }

        //if ((cmdLine.substring(0, 4).equalsIgnoreCase("LOOK")) || upperCaseCmd.charAt(0) == 'L') {
        if ((cmdArray[0].equalsIgnoreCase("LOOK")))
        {
            return 3;
        }

        CHALLENGE_COMMANDS.add("ATTACK");
        CHALLENGE_COMMANDS.add("HINT");
        CHALLENGE_COMMANDS.add("RETREAT"); //To go back to previous room
        for (String itemCommand : CHALLENGE_COMMANDS) {
            if (cmdLine.equalsIgnoreCase(itemCommand)) {
                return 4;
            }
        }

        OTHER_COMMANDS.add("COMMAND");
        OTHER_COMMANDS.add("STATS");
        OTHER_COMMANDS.add("MAP");
        OTHER_COMMANDS.add("LOCATION");
        OTHER_COMMANDS.add("COMPLETE");

        for (String itemCommand : OTHER_COMMANDS)
        {
            if (cmdLine.equalsIgnoreCase(itemCommand))
            {
                return 5;
            }
        }

        if (cmdLine.equalsIgnoreCase("Exit") || cmdLine.equalsIgnoreCase("x")) {
            exit(EXIT_COMMAND);
        }

        return 0;
    }


    /**
     * @param cmd
     * @return
     * @throws GameException
     * @purpose Pushed the command entered by the user to the correct method for data processing.
     */
    protected String executeCommand(String cmd) throws GameException, SQLException, ClassNotFoundException
    {
        // Protects the current room from every being equal to 0
        if (player.getCurrRoom() == 0) {
            player.setCurrRoom(1);
        }

        String result = null;
        cmd = letterValidation(cmd);
        int commandInt = validateCommand(cmd);

        if (commandInt == 1) {
            result = move(cmd);
        }
        else if (commandInt == 2) {
            result = itemCommand(cmd);
        } else if (commandInt == 3) {
            result = look(cmd);
        } else if (commandInt == 4) {
            result = challengeCommand(cmd);
        } else if (commandInt == 5) {
            result = otherCommand(cmd);
        }else if (commandInt == 0) {
            result = invalidCommand();
        } else {
            throw new GameException("Something went wrong with the command you entered, please restart the program.");
        }
        return result;
    }

    private String letterValidation(String cmd) throws GameException
    {
        String cmdExt = "";
        String[] cmdArray = cmd.split(" ");

        for(int i = 0; i < cmdArray.length; i++) {
            switch (cmdArray[i].toUpperCase()) {
                case "N":
                    cmdExt += "NORTH";
                    break;
                case "S":
                    cmdExt += "SOUTH";
                    break;
                case "E":
                    cmdExt += "EAST";
                    break;
                case "W":
                    cmdExt += "WEST";
                    break;
                case "U":
                    cmdExt += "UP";
                    break;
                case "D":
                    cmdExt += "DOWN";
                    break;
                case "NW":
                    cmdExt += "NORTH WEST";
                    break;
                case "NE":
                    cmdExt += "NORTH EAST";
                    break;
                case "SW":
                    cmdExt += "SOUTH WEST";
                    break;
                case "SE":
                    cmdExt += "SOUTH EAST";
                    break;
                case "F":
                    cmdExt += "FORWARD";
                    break;
                case "B":
                    cmdExt += "BACK";
                    break;
                case "R":
                    cmdExt += "RETREAT";
                    break;
                case "L":
                    cmdExt += "LOOK ";
                    break;
                case "A":
                    cmdExt += "ATTACK";
                    break;
                case "C":
                    cmdExt += "COLLECT";
                    break;
                case "DR":
                    cmdExt += "DROP";
                    break;
                case "I":
                    cmdExt += "INSPECT";
                    break;
                case "USE":
                    cmdExt += "USE";
                    break;
                case "P":
                    cmdExt += "PACK";
                    break;
                case "BP":
                    cmdExt += "PACK";
                    break;
                case "X":
                    cmdExt += "EXIT";
                    break;
                case "HELP":
                    cmdExt += "HINT";
                    break;
                case "H":
                    cmdExt += "HINT";
                    break;
                case "CMD":
                    cmdExt += "COMMAND";
                    break;
                case "CL":
                    cmdExt += "COMMAND";
                    break;
                default:
                    cmdExt = cmd;
                    break;
            }
        }
        return cmdExt;
    }

    /**
     * @param cmd
     * @return
     * @throws GameException
     * @purpose If the user enters a valid directional command, then they are directed to this method.
     */
    private String move(String cmd) throws GameException, SQLException, ClassNotFoundException
    {
        int currRoom = player.getCurrRoom();

        RoomDB roomDB = new RoomDB();
        Room room = roomDB.getRoom(player.getCurrRoom());

        ExitDB exitDB = new ExitDB();
        PlayerDB playerDB = new PlayerDB();
        PuzzleDB puzzleDB = new PuzzleDB();
        MonsterDB monsterDB = new MonsterDB();

        ArrayList<Exit> currListOfExits = exitDB.currRoomExitOptions(currRoom);
        room.setExits(currListOfExits);

        ArrayList<Puzzle> roomListOfPuzzles = puzzleDB.currRoomPuzzles(player.getCurrRoom(), player.getPlayerID());
        ArrayList<Monster> roomListOfMonsters = monsterDB.currRoomMonsters(player.getCurrRoom(), player.getPlayerID());

        if (roomListOfPuzzles.size() == 0 && roomListOfMonsters.size() == 0) // prevent move before completing challenge
        {
            room.updateRoom();

            for (Exit exit : currListOfExits)
            {
                if (exit.getDirection().equalsIgnoreCase(cmd))
                {
                    player.setPrevRoom(currRoom);
                    player.setCurrRoom(exit.getDestination());
                    playerDB.updatePlayerRoom(player.getPlayerID(), exit.getDestination(), currRoom);
                    // Updating the room object to be the new current room.
                    room = roomDB.getRoom(player.getCurrRoom());
                    roomListOfPuzzles = puzzleDB.currRoomPuzzles(player.getCurrRoom(), player.getPlayerID());
                    roomListOfMonsters = monsterDB.currRoomMonsters(player.getCurrRoom(), player.getPlayerID());
                    break;
                }
            }

        } else
        {
            System.out.println("You can't pass without completing the Challenge!");
        }

        String puzzleDesc = "\n> No Puzzle in this room";
        for (Puzzle p : roomListOfPuzzles)
        {
            puzzleDesc = "\n> PUZZLE: " + p.getDescription();
            puzzleDesc += "\nType [USE] to solve the puzzle or [RETREAT] to move back to prev room";
        }

        String monsterDesc = "\n> No Monster in this room";
        for (Monster m : roomListOfMonsters)
        {
            monsterDesc = "\n> MONSTER: " + m.getMonsterName() + "\n" + m.getMonsterDescription() + "\nHP: " + m.getHitPoints() + " | Attack: " + m.getAttackDamage();
            monsterDesc += "\nType [ATTACK] to attack the monster or [RETREAT] to move back to prev room";
        }
        
        String itemSt = "\n>Type [COLLECT] to see if there's any items here!";

        if (room.isVisited()) {
            System.out.println("You have been in this room before.");
        } else {
            System.out.println("You have not been in this room before.");
        }
        
        
	        String returnString = "Room Name: " + room.getName() + "\n" + room.getDescription() + puzzleDesc + monsterDesc + itemSt;
	        return returnString;
    }

    private String look(String cmd) throws GameException, SQLException, ClassNotFoundException {
        int currRoom = player.getCurrRoom();
        boolean exitFound = false;

        //Remove "LOOK" or "L" from cmd input line
        String[] lines = cmd.split(" ");
        String removedCmd = cmd.replaceFirst(lines[0] + " ", "");
        removedCmd = letterValidation(removedCmd);

        RoomDB roomDB = new RoomDB();
        Room room = roomDB.getRoom(player.getCurrRoom());

        ExitDB exitDB = new ExitDB();

        ArrayList<Exit> currListOfExits = exitDB.currRoomExitOptions(currRoom);
        room.setExits(currListOfExits);

        for (Exit exit : currListOfExits) {
            if (exit.getDirection().equalsIgnoreCase(removedCmd)) {
                exitFound = true;
                room = roomDB.getRoom(exit.getDestination());
                break;
            }
        }

        String puzzleMonsterRoom = printMonsterPuzzle(room.getRoomID());

        String returnString = "";
        if (exitFound)
            returnString = "On your " + removedCmd.toUpperCase() + ", there is a ";
        else
            returnString = "Exit to " + removedCmd.toUpperCase() + " is not found.\n";
        returnString += room.getName() + "\n" + room.getDescription() + "\n" + puzzleMonsterRoom;

        return returnString;
    }

    private String challengeCommand(String cmd) throws GameException, SQLException, ClassNotFoundException
    {
        RoomDB roomDB = new RoomDB();
        Room room = roomDB.getRoom(player.getCurrRoom());

        PlayerDB playerDB = new PlayerDB();
        PuzzleDB puzzleDB = new PuzzleDB();
        MonsterDB monsterDB = new MonsterDB();
        ArrayList<Puzzle> roomListOfPuzzles = puzzleDB.currRoomPuzzles(room.getRoomID(), player.getPlayerID());
        ArrayList<Monster> roomListOfMonsters = monsterDB.currRoomMonsters(player.getCurrRoom(), player.getPlayerID());

        String returnString = "";

        if (roomListOfPuzzles.size() >= 1 || roomListOfMonsters.size() >= 1)
        {
            if (cmd.equalsIgnoreCase("RETREAT"))
            {
                player.setCurrRoom(player.getPrevRoom()); //send back to previous room
                playerDB.updatePlayerRoom(player.getPlayerID(), player.getPrevRoom(), player.getPrevRoom());
                // Updating the room object to be the new current room.
                room = roomDB.getRoom(player.getCurrRoom());
                String puzzleMonsterRoom = printMonsterPuzzle(room.getRoomID());
                String itemSt = "\n>Type [COLLECT] to see if there's any items here!";
                returnString = "Room Name: " + room.getName() + "\n" + room.getDescription() + "\n" + puzzleMonsterRoom + itemSt;
            }
            else if (cmd.equalsIgnoreCase("HINT"))
            {
                if(roomListOfMonsters.size() == 0)
                {
                    for (Puzzle p : roomListOfPuzzles)
                        returnString = "Hint: " + p.getHint();
                }
                else
                    returnString = "There are no puzzle in the room.";
            }
            else if (cmd.equalsIgnoreCase("ATTACK"))
            {
                if(roomListOfMonsters.size() >= 1)
                {
                    int currentMonsterID = roomListOfMonsters.get(0).getMonsterID();
                    monsterDB.attackedMonster(currentMonsterID, playerDB.getAttackDamage(player.getPlayerID()), player.getPlayerID());
                    if (monsterDB.getMonster(currentMonsterID, player.getPlayerID()).getHitPoints() > 0)
                    {
                        playerDB.attackedPlayer(player.getPlayerID(), monsterDB.getMonsterAttack(currentMonsterID, player.getPlayerID()));
                        returnString += printMonster(room.getRoomID()) + "\n" + playerStats() + "\nUse [ATTACK] or [RETREAT].";
                    } else
                    {
                        monsterDB.updateMonster(currentMonsterID, player.getPlayerID());
                        returnString += "You have beaten the Monster with " + playerDB.getHealthPoint(player.getPlayerID()) + " HP left!\n" + playerLocation();
                    }
                }
            }
        }
        else
            returnString = "You can only this command when there is a challenge in the room.";

        return returnString;
        }

    private String printMonster(int roomID)
    {
        MonsterDB monsterDB = new MonsterDB();
        ArrayList<Monster> roomListOfMonsters = monsterDB.currRoomMonsters(roomID, player.getPlayerID());

        String monsterDesc = "> No Monster in this room";
        for (Monster m : roomListOfMonsters)
        {
            monsterDesc = m.getMonsterName() + "'s HP: " + m.getHitPoints() + " | Attack: " + m.getAttackDamage();
        }
        return monsterDesc;
    }

    private String printMonsterPuzzle(int roomID)
    {
        PuzzleDB puzzleDB = new PuzzleDB();
        MonsterDB monsterDB = new MonsterDB();
        ArrayList<Puzzle> roomListOfPuzzles = puzzleDB.currRoomPuzzles(roomID, player.getPlayerID());
        ArrayList<Monster> roomListOfMonsters = monsterDB.currRoomMonsters(roomID, player.getPlayerID());

        String puzzleDesc = "> No Puzzle in this room";
        for (Puzzle p : roomListOfPuzzles)
        {
            puzzleDesc = "> PUZZLE: " + p.getDescription();
        }
        String monsterDesc = "\n> No Monster in this room";
        for (Monster m : roomListOfMonsters)
        {
            monsterDesc = "\n> MONSTER: " + m.getMonsterName() + "\n" + m.getMonsterDescription() + "\nHP: " + m.getHitPoints() + " | Attack: ";
        }
        return puzzleDesc + monsterDesc;
    }

    private boolean checkPuzzle(String itemName) throws SQLException, ClassNotFoundException, GameException
    {
        boolean returnBoolean = false;

        PuzzleDB puzzleDB = new PuzzleDB();
        ArrayList<Puzzle> roomListOfPuzzles = puzzleDB.currRoomPuzzles(player.getCurrRoom(), player.getPlayerID());

        if (itemName.equalsIgnoreCase(roomListOfPuzzles.get(0).getItemToSolve()))
        {
            returnBoolean = true;
            puzzleDB.updatePuzzle(roomListOfPuzzles.get(0).getPuzzleID(), player.getPlayerID());
            System.out.println("You have solved the puzzle!");
        }
        return returnBoolean;
    }

    /**
     * @param cmd
     * @param room
     * @return
     * @throws GameException
     * @purpose This method gets the item from the room for the user and then updates the respective classes/database.
     */
    private String collect(String cmd, Room room) throws GameException, SQLException, ClassNotFoundException {

    	MonsterDB monDB = new MonsterDB();
        String result = "";
        Scanner scan = new Scanner(System.in);

        ArrayList<Item> currRoomItemList = room.getRoomItems(player.getPlayerID());

        if (currRoomItemList.isEmpty()) {
            return "Sorry, there are no items to collect in this room.";
        }


        System.out.println("Which item?");

        // Displaying the item choices to the user.
        for (Item item : currRoomItemList) {
            System.out.println("[" + item.getItemName() + "]");
        }

        System.out.println("Type [NO] at any time to be done collecting.");
        String input = scan.nextLine();

        while(!input.equalsIgnoreCase("NO")) {

            for (Item item : currRoomItemList) {

                if (item.getItemName().equalsIgnoreCase(input)) {

                    // updating room
                    System.out.println(item.getItemID());
                    room.removeItem(item.getItemID(), player.getPlayerID());
                    // updating player
                    player.addItem(item.getItemID());

                    result += item.getItemName() + " has been picked up from the room and successfully " +
                            "added to the player inventory! " + "\n";
                    
                    //checking to see if the player has the fuel to add the final enemy to the game
                    if(item.getItemName().equalsIgnoreCase("fuel"))
                    {
                    	monDB.updateTrueTerror(player.getPlayerID());
                    }

                } else {
                    //System.out.println("Invalid item choice, please try again.");
                }
            }
            System.out.println("Want to collect anything else? Type [NO] to finish.");
            input = scan.nextLine();

        }
        if (result.equalsIgnoreCase("")) {
            return "Ok, please enter in a move, item, or exit command.";
        }
        return result;
    }

    /**
     * @param cmd
     * @return
     * @throws GameException
     * @purpose This method sorts through all the appropriate item commands and directed the user input to
     * the correct method.
     */
    private String itemCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {
        RoomDB roomDB = new RoomDB();
        PlayerDB playerDB = new PlayerDB();
        PuzzleDB puzzleDB = new PuzzleDB();
        MonsterDB monsterDB = new MonsterDB();
        Scanner scan = new Scanner(System.in);
        ItemDB itemDB = new ItemDB();

        //New line of code to get current room.
        Room room = roomDB.getRoom(playerDB.getCurrentRoom(player.getPlayerID()));

        // Default in case the user's item command is incorrect.
        String result = "Something went wrong with your item command entry. " +
                "If you typed b trying to go back, Type [RETURN] instead, or R";

        if (cmd.equalsIgnoreCase("COLLECT")) {
            result = collect(cmd, room);
        } else if(cmd.equalsIgnoreCase("DROP")) {
            result = drop(cmd, room);
        } else if (cmd.equalsIgnoreCase("INSPECT")){
            // Further command detail is needed for inspect command.
            System.out.println("Which item would you like to inspect? ");
            String command = scan.nextLine();
            result = inspectItem(command, room);
        } else if (cmd.equalsIgnoreCase("USE"))
        {
            ArrayList<Puzzle> roomListOfPuzzles = puzzleDB.currRoomPuzzles(room.getRoomID(), player.getPlayerID());
            ArrayList<Monster>roomListOfMonsters = monsterDB.currRoomMonsters(player.getCurrRoom(), player.getPlayerID());
            if(roomListOfPuzzles.isEmpty() && roomListOfMonsters.isEmpty() && openBackpack().isEmpty())
            {
                result = "There are no challenge in the current room.";
            }
            else
            {
                result = useItem();
            }
        } else if(cmd.equalsIgnoreCase("BACKPACK") || cmd.equalsIgnoreCase("PACK")) {
            result = openBackpack();
        }
        return result;
    }

    public String useItem() throws SQLException, ClassNotFoundException, GameException
    {
        ItemDB itemDB = new ItemDB();
        PlayerDB playerDB = new PlayerDB();

        PuzzleDB puzzleDB = new PuzzleDB();

        RoomDB roomDB = new RoomDB();
        Room room = roomDB.getRoom(playerDB.getCurrentRoom(player.getPlayerID()));
        ArrayList<Puzzle> roomListOfPuzzles = puzzleDB.currRoomPuzzles(room.getRoomID(), player.getPlayerID());

        ArrayList<Item> itemArrayList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        int itemID = 0;
        // trying to see if we can use an item based of it's item name versus it's string - Kyle Williams
        String itemName = "";
        int canHeal = 0;
        int healValue = 0;
        String returnString = "";

        boolean go = false;
        if(!openBackpack().contains("is empty."))
            go = true;
        else
        {
            returnString = openBackpack();
            if(!roomListOfPuzzles.isEmpty())
            {
                returnString += " You can only use [RETREAT].";
            }
            else {
                returnString += " Use [ATTACK] or [RETREAT].";
            }

        }

        while(go)
        {
            System.out.println(openBackpack());
            System.out.println("Which item would you like to Use? Type [NO] to end.");
            String command = scan.nextLine();
            itemArrayList = itemDB.searchItemID(command, player.getPlayerID());
            if (command.equalsIgnoreCase("NO"))
            {
                go = false;
                break;
            }

            for (Item item : itemArrayList)
            {
                itemID = item.getItemID();
                itemName = item.getItemName();
                canHeal = item.getCanHeal();
                if (item.getCanHeal() == 1){
                    healValue = item.getHealValue();
                }

            }

            if (itemArrayList.isEmpty())
            {
                System.out.println("Item is not found.");

            }
            if(itemID > 0 && canHeal == 0)
            {
                if(!roomListOfPuzzles.isEmpty())
                {
                    if(!checkPuzzle(itemName))
                    {
                        returnString = "This is not the correct item. Type [RETREAT] to move back to previous room.";
                    }
                }
                if(roomListOfPuzzles.isEmpty())
                {
                    returnString = "You can't use this item during a battle.";
                }
                go = false;
            }
            else if (canHeal == 1)
            {
                playerDB.heal(player.getPlayerID(), healValue);
                itemDB.removeItem(itemID, player.getPlayerID());
                returnString += "You've restored " + healValue + " HP\n" + playerStats();
                go = false;
            }
        }
        return returnString;
    }

    private String otherCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {

        // Default in case the user's item command is incorrect.
        String result = "";

        if (cmd.equalsIgnoreCase("COMMAND"))
        {
            commandList();
        }
        else if(cmd.equalsIgnoreCase("MAP"))
        {
            //result += (roomDB.getRoom(playerDB.getCurrentRoom(player.getPlayerID())));
        }
        else if(cmd.equalsIgnoreCase("LOCATION"))
        {
            result += playerLocation();
        }
        else if(cmd.equalsIgnoreCase("STATS"))
        {
            result += playerStats();
        }else if(cmd.equalsIgnoreCase("COMPLETE"))
        {
            result = getEnding();
        }

        return result;
    }

    private String getEnding() throws GameException, SQLException, ClassNotFoundException {
        String ending = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Are try to complete and end the game?");
        String decision = scan.next();
        if (decision.equalsIgnoreCase("no")) {
            ending = "Please enter in another command, to keep playing";
        } else {

            Puzzle toolBoxPuzzle = new Puzzle();
            Puzzle fuelPuzzle = new Puzzle();

            PuzzleDB puzzleDB = new PuzzleDB();
            ItemDB itemDB = new ItemDB();

            ArrayList<Puzzle> puzzleArrayList = puzzleDB.getPuzzles(player.getPlayerID());
            ArrayList<Item> backpack = itemDB.getBackpack(player.getPlayerID());

            Item compass = new Item();

            for (Puzzle pz : puzzleArrayList) {
                if (pz.getItemToSolve().equalsIgnoreCase("fuel")) {
                    fuelPuzzle = pz;
                }
                if (pz.getItemToSolve().equalsIgnoreCase("tool box")) {
                    toolBoxPuzzle = pz;
                }
            }

            for (Item item : backpack) {
                if (item.getItemName().equalsIgnoreCase("compass")) {
                    compass = item;
                }
            }

            if (fuelPuzzle.isSolved() && toolBoxPuzzle.isSolved() && backpack.contains(compass)) {
                ending = "With the boat repaired, and refueled, you set your escape plan in motion." + "\n" +
                        "Fortunately, you came prepared for the journey, but time will tell whether this ordeal" + "\n" +
                        "will haunt you, or if you shall recover.";
            } else if (fuelPuzzle.isSolved() && toolBoxPuzzle.isSolved() && !(backpack.contains(compass))) {
                ending = "After succeeding in repairing the boat, to the best of your abilities, you set sail. " + "\n" +
                        "Unfortunately, you forgot to bring any navigation equipment onboard, and there was none present to begin with. " + "\n" +
                        "So, you soon find yourself drifting across a seemingly endless sea, with the island always creeping back into view…";
            } else if (!fuelPuzzle.isSolved() && !toolBoxPuzzle.isSolved()) {
                ending = "After swimming… and swimming… and swimming… you begin to tire, " + "\n" +
                        "you can’t tell how long it’s been, only that you must keep going. " + "\n" +
                        "Eventually, you start to slip under the waves, never to surface again…";
            } else {
                ending = "You can't end the game yet.";
            }
        }
        return ending;
    }

    private String playerLocation() throws SQLException, ClassNotFoundException, GameException
    {
        RoomDB roomDB = new RoomDB();
        PlayerDB playerDB = new PlayerDB();
        return roomDB.getRoom(playerDB.getCurrentRoom(player.getPlayerID())).toString();
    }

    private String playerStats() throws SQLException, ClassNotFoundException, GameException
    {
        PlayerDB playerDB = new PlayerDB();
        return "You have " + playerDB.getHealthPoint(player.getPlayerID()) + " HP | Attack: "
                + playerDB.getAttackDamage(player.getPlayerID());
    }

    private void commandList()
    {
        System.out.println(" ----------------------------------   ----------------------------------");
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "DIRECTION    ",  "SHORTCUT ", "DIRECTION    ",  "SHORTCUT ");
        System.out.println(" ----------------------------------   ----------------------------------");
        // Directions
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "NORTH            ", "[N]    ", "NORTH EAST       ", "[NE]   ");
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "EAST             ",  "[E]    ", "SOUTH EAST       ",  "[SE]   ");
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "SOUTH            ",  "[S]    ", "NORTH WEST       ",  "[NW]   ");
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "WEST             ",  "[W]    "    , "SOUTH WEST       ",  "[SW]   ");
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "BACK             ",  "[B]    ", "UP               ",  "[U]    ");
        System.out.printf ("|%20s | %10s | |%20s | %10s |\n", "FORWARD          ", "[F]    ", "DOWN             ",  "[D]    ");
        System.out.println(" ----------------------------------   ----------------------------------");

        System.out.println(" -----------------------------------------------------------------------");
        System.out.printf ("|%20s | %10s | %35s|\n", "KEYWORD      ",  "SHORTCUT ", "FUNCTIONALITY          ");
        System.out.println(" -----------------------------------------------------------------------");
        // Directions
        System.out.printf ("|%20s | %10s | %35s|\n", "LOOK +[DIRECTION]  ", "[L] + dir ", "LOOK FOR AN EXIT IN THE ROOM      ");
        // Item Commands
        System.out.printf ("|%20s | %10s | %35s|\n", "COLLECT            ", "[C]    ", "COLLECT ITEM FROM THE ROOM        ");
        System.out.printf ("|%20s | %10s | %35s|\n", "INSPECT            ", "[I]    ", "INSPECT AN ITEM IN THE ROOM      ");
        System.out.printf ("|%20s | %10s | %35s|\n", "USE                ", "[USE]  ", "USE AN ITEM IN YOUR BACKPACK     ");
        System.out.printf ("|%20s | %10s | %35s|\n", "DROP               ", "[DR]  ", "DROP AN ITEM FROM YOUR INVENTORY   ");
        System.out.printf ("|%20s | %10s | %35s|\n", "BACKPACK/PACK      ", "[BP]/[P] ", "SHOW WHAT IS IN YOUR INVENTORY   ");
        // Challenge Commands
        System.out.printf ("|%20s | %10s | %35s|\n", "ATTACK             ", "[A]    ", "ATTACK MONSTER IN ROOM            ");
        System.out.printf ("|%20s | %10s | %35s|\n", "RETREAT            ", "[R]    ", "FORFEIT THE CHALLENGE             ");
        System.out.printf ("|%20s | %10s | %35s|\n", "HINT/HELP          ", "[H]    ", "SHOW HINT TO ROOM PUZZLE          ");
        // Other Commands
        System.out.printf ("|%20s | %10s | %35s|\n", "STATS              ", "[N]    ", "SHOW HP, ATTACK, SCORE            ");
        System.out.printf ("|%20s | %10s | %35s|\n", "MAP                ", "[M]    ", "PRINTS OUT THE GAME MAP           ");
        System.out.printf ("|%20s | %10s | %35s|\n", "LOCATION           ", "[L]    ", "PRINTS CURRENT ROOM               ");
        System.out.printf ("|%20s | %10s | %35s|\n", "COMMAND            ", "[CMD]/[CL]", "PRINTS OUT THE COMMAND LIST       ");
        System.out.printf ("|%20s | %10s | %35s|\n", "EXIT               ", "[X]    ", "EXIT THE GAME                     ");
        System.out.println(" -----------------------------------------------------------------------");
    }

    /**
     * @param cmd
     * @param room
     * @return
     * @throws GameException
     * @purpose returns the description of the item, the user wants to see.
    //     */
    private String inspectItem(String cmd, Room room) throws GameException, SQLException, ClassNotFoundException {

        //room = room.retrieveByID(player.getCurrRoom());
        ArrayList<Item> listOfItems = room.getRoomItems(player.getPlayerID());

        // Default in case the user's item selection is incorrect.
        String itemDesc = "Item not found, type [INSPECT] again if you think you made a typo." + "\n" +
                "Otherwise, that item is NOT in your backpack or in this room";

        // Testing if the user entered item is in the room.
        for (Item item : listOfItems) {
            if (cmd.equalsIgnoreCase(item.getItemName())) {
                itemDesc = item.display();
            }
        }

        // Testing if the user entered item is in the backpack.
        for (Item inventoryItem : player.getInventory()) {
            if (cmd.equalsIgnoreCase(inventoryItem.getItemName())) {
                itemDesc = inventoryItem.display();
            }
        }

        return itemDesc;
    }

    /**
     * @param cmd
     * @param room
     * @return
     * @throws GameException
     * @purpose removes the selected item from backpack and puts it in the current room.
     */
    private String drop(String cmd, Room room) throws GameException, SQLException, ClassNotFoundException {

        ItemDB itemDB = new ItemDB();

        // If the player's inventory is not empty then it will display so the user can make a selection.
        if (!itemDB.getBackpack(player.getPlayerID()).isEmpty()) {
            itemDB.printInventory(player.getPlayerID());
        } else if (itemDB.getBackpack(player.getPlayerID()).isEmpty()) {
            return "You don't have any items in your backpack to drop.";
        }

        String result = "";

        Scanner scan = new Scanner(System.in);

        System.out.println("Which item would you like to drop?");

        String input = scan.nextLine();

        for (Item item : itemDB.getBackpack(player.getPlayerID())) {
            // testing if the item in the backpack matches what the user selected
            // also don't need to access this code if the player's backpack is empty.
            if (item.getItemName().equalsIgnoreCase(input) && !itemDB.getBackpack(player.getPlayerID()).isEmpty()) {

                // update player's backpack. // update current room.
                itemDB.dropItem(room.getRoomID(), item.getItemID(), player.getPlayerID());

                result = "You have dropped " + item.getItemName() + "!" + " It now resides in " + room.getName() + " " +
                        "please type [DROP] again to drop another item.";
                break;
            } else {
                result = "Please enter a valid item name to remove it from your inventory - first, you must type drop " +
                        "again.";
            }
        }
        return result;
    }

    /**
     * @param room
     * @return
     * @throws GameException
     * @prupose returns the current room's list of exit's so the user can see them again if they forgot their options
     */
    private String getCurrentExits(Room room) throws GameException {
        return room.displayExits();
    }

    /**
     * @return
     * @purpose allows the user to type in "PACK" and see all the items that exist in their ArrayList.
     */
    private String openBackpack() throws SQLException, ClassNotFoundException
        {
            String result = "";

            ItemDB itemDB = new ItemDB();
            ArrayList<Item> itemArrayList = itemDB.getBackpack(player.getPlayerID());

            for (Item item : itemArrayList)
            {
                result += "> Item: " + item.getItemName() + " ";
            }

            if (itemArrayList.isEmpty())
            {
                result = "Your backpack is empty.";
            }

            return result;
        }


    /**
     * @param exitCommand
     * @purpose exits out of the program upon the user typing exit.
     */
    private void exit(int exitCommand) {
        System.out.println("Thanks for playing!");
        System.exit(exitCommand);
    }

    /**
     * @return String
     * @purpose notifies the user that they have entered an invalid command.
     */
    private String invalidCommand() {
        return "That command is not recognized, please enter a valid command";
    }

}
