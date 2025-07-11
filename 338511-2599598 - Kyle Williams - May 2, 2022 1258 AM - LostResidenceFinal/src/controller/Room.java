/**Class: Room.java
 * @author Kyle Williams, Rick Price, Bradley Iverson, Miranda Darlington
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27
 *
 *
 * This class serves as the blueprint for all room objects that get stores in the room database.
 *
 */

package controller;

import gameExceptions.GameException;
//import model.ItemDB;
import model.ItemDB;
import model.RoomDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class Room {

    // Attributes
    private String description;
    private ArrayList<Exit> exits;
    private ArrayList<Integer> items = new ArrayList<>();
    private String name;
    private int roomID;
    private boolean visited;
    private ArrayList<Puzzle> roomPuzzle = new ArrayList<>();

    /**
     * @throws GameException
     * @purpose no arg constructor
     */
    public Room() throws GameException {

    }

    /**
     * @param id
     * @throws GameException
     * @purpose constructor that only assigns the id.
     */
    public Room (int id) throws GameException {
        this.roomID = id;
    }

    /**
     * @throws GameException
     * @purpose replaces the old room in the database with the new one or "this" room.
     */
    public void updateRoom() throws GameException, SQLException, ClassNotFoundException {

        RoomDB roomDB = new RoomDB();
        roomDB.updateRoom(roomID);
    }

    /**
     * @return build
     * @purpose build a string representation of all the exit object associated with this room.
     */
    public String displayExits () {

        String build = "";

        for (Exit exit : this.getExits()){
            build += "Exit Direction:  " + "[" + exit.getDirection() + "] | ";
        }

        return build;
    }

    /**
     * @param cmd
     * @return exit.getDestination
     * @throws GameException
     * @purpose tests if the user entered in a direction that is valid for the room they're currently in.
     */
    public int validDirection(String cmd) throws GameException {

        for (Exit exit : this.exits) {
            if (cmd.equalsIgnoreCase(exit.getDirection())){
                return exit.getDestination();
            }
        }
        return 0;
    }

    /**
     * @return currItemList
     * @throws GameException
     * @purpose Get's the list of items that exist in the current room object.
     */
    public ArrayList<Item> getRoomItems(int playerID) throws GameException, SQLException, ClassNotFoundException {
        ItemDB itemDB = new ItemDB();

        ArrayList<Item> itemArrayList;
        itemArrayList = itemDB.getRoomItems(roomID, playerID);

        return itemArrayList;
    }

    public void removeItem(int itemID, int playerID) throws SQLException, GameException, ClassNotFoundException {
        ItemDB itemDB = new ItemDB();
        itemDB.removeItem(itemID, playerID);
    }

    // Getters, setters, and toString for Room from here on.

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {

            this.items = items;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public boolean isVisited() throws SQLException, ClassNotFoundException {
        RoomDB roomDB = new RoomDB();
        int result = roomDB.isVisited(roomID);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Room #" + roomID + ": " + name + "\n" + description;
    }

    public ArrayList<Puzzle> getRoomPuzzle() {
        return roomPuzzle;
    }

    public void setRoomPuzzle(ArrayList<Puzzle> roomPuzzle) {
        this.roomPuzzle = roomPuzzle;
    }
}
