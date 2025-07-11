/**Class: Player.java
 * @author Kyle Williams, Rick Price, Bradley Iverson, Miranda Darlington
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 03/11
 *
 *
 * This class acts as a blueprint for all Player objects.
 *
 */

package controller;

import model.ItemDB;
import model.PlayerDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class Player {

    // Attributes
    private int playerID;
    private int acctID;
    private int currRoom;
    private ArrayList<Item> inventory;
    private int prevRoom;
    PlayerDB playerDB;

    /**
     * @purpose no arg constructor that creates a new arraylist only if there isn't one already.
     */
    protected Player () {
        if (inventory == null) {
            inventory = new ArrayList<>();
        }
    }

    public void updatePlayerDB(){
        playerDB = new PlayerDB();
        playerDB.updatePlayerRoom(playerID, currRoom, prevRoom);
    }

    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }

    /**
     * @param
     * @purpose adds the item passed in to the player's inventory or "backpack".
     */
    protected void addItem(int itemId) throws SQLException, ClassNotFoundException {
        ItemDB itemDB = new ItemDB();
        itemDB.addPlayerItem(itemId, playerID);
    }

    /**
     * @param item
     * @purpose removes the item passed in from the player's inventory or "backpack".
     */
    protected void removeItem(Item item) {

        inventory.remove(item);
    }

    /**
     * @purpose This method returns a string for all the items in the player's inventory.
     */
    protected void printInventory() {
        for (Item item : inventory) {
            System.out.println("[" + item.getItemName() + "]");
        }
    }

    /**
     * @return currRoom
     */
    public int getCurrRoom() {
        return currRoom;
    }

    /**
     * @param current
     */
    public void setCurrRoom(int current) {
        this.currRoom = current;
    }

    /**
     * @return inventory
     */
    protected ArrayList<Item> getInventory() {

        return inventory;
    }

    public int getPrevRoom() {
        return prevRoom;
    }

    public void setPrevRoom(int prevRoom) {
        this.prevRoom = prevRoom;
    }

    public int getAcctID() {
        return acctID;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }

    public int getPlayerID() {
        return playerID;
    }
}
