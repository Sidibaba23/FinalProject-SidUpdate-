/**Class: ItemDB.java
 * @author Kyle Williams, Rick Price, Bradley Iverson, Miranda Darlington
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27
 *
 *
 * The purpose of this class is to hold the queries that update and get information
 * from the item table in the database.
 *
 */

package model;

import controller.Item;
import gameExceptions.GameException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDB {

    /**
     * @param itemId
     * @return item object
     * @throws GameException
     * @purpose returns the correct item object based on the id passed into the constructor.
     */
    public Item getItem(int itemId) throws GameException, SQLException, ClassNotFoundException {

        Item item = new Item();
        SQLiteDB sqLiteDB = new SQLiteDB();
        String sql = "SELECT * FROM ITEM WHERE itemID = " + itemId;
        ResultSet rs = sqLiteDB.queryDB(sql);
        while(rs.next()) {
            item.setItemID(rs.getInt("itemID"));
            item.setRoomID(rs.getInt("roomID"));
            item.setItemName(rs.getString("itemName"));
            item.setItemDescription(rs.getString("itemDesc"));
        }
        sqLiteDB.close();
        return item;
    }

    public ArrayList<Item> getRoomItems(int roomId, int playerID) throws GameException, SQLException, ClassNotFoundException {
        ArrayList<Item> listOfRoomItems = new ArrayList<>();

        Item item = new Item();
        SQLiteDB sqLiteDB = new SQLiteDB();
        String sql = "SELECT * FROM ITEM WHERE roomID = " + roomId + " AND playerID = " + playerID;
        ResultSet rs = sqLiteDB.queryDB(sql);
        while(rs.next()) {
            Item it = new Item();
            it.setItemID(rs.getInt("itemID"));
            it.setRoomID(rs.getInt("roomID"));
            it.setItemName(rs.getString("itemName"));
            it.setItemDescription(rs.getString("itemDesc"));
            it.setCanHeal(rs.getInt("canHeal"));
            it.setHealValue(rs.getInt("healValue"));
            listOfRoomItems.add(it);
        }
        sqLiteDB.close();
        return listOfRoomItems;
    }

    public void removeItem(int itemId, int playerID) throws GameException, SQLException, ClassNotFoundException
    {
        SQLiteDB sqLiteDB = new SQLiteDB();
        String sql = "UPDATE ITEM SET roomID = 0, inInventory = 0 WHERE itemID = " + itemId + " AND playerID = " + playerID;
        sqLiteDB.updateDB(sql);
        sqLiteDB.close();
    }

    public void dropItem(int roomId, int itemId, int playerID) throws GameException, SQLException, ClassNotFoundException {
        SQLiteDB sqLiteDB = new SQLiteDB();
        String sql1 = "UPDATE ITEM SET inInventory = " + 0 + " WHERE itemID = " + itemId + " AND playerID = " + playerID;
        String sql2 = "UPDATE ITEM SET roomID = " + roomId + " WHERE itemID = " + itemId + " AND playerID = " + playerID;
        sqLiteDB.updateDB(sql1);
        sqLiteDB.updateDB(sql2);
        sqLiteDB.close();
    }

    public void addPlayerItem(int itemId, int playerID) throws SQLException, ClassNotFoundException {
        SQLiteDB sqLiteDB = new SQLiteDB();
        String sql = "UPDATE ITEM SET inInventory = 1 WHERE itemID = " + itemId + " AND playerID = " + playerID;
        sqLiteDB.updateDB(sql);
        sqLiteDB.close();
    }

    public ArrayList<Item> searchItemID(String itemName, int playerID) throws SQLException, ClassNotFoundException
    {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        try {
            SQLiteDB sdb = new SQLiteDB();
            String sql = "SELECT * FROM ITEM WHERE inInventory = 1 AND itemName LIKE '" + itemName + "'" + " AND playerID = " + playerID;
            ResultSet rs = sdb.queryDB(sql);
            while(rs.next()) {
                Item it = new Item();
                it.setItemID(rs.getInt("itemID"));
                it.setItemName(rs.getString("itemName"));
                it.setItemDescription(rs.getString("itemDesc"));
                it.setHealValue(rs.getInt("healValue"));
                it.setCanHeal(rs.getInt("canHeal"));
                itemArrayList.add(it);
            }
            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("Item is not found in the Database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemArrayList;
    }

    public ArrayList<Item> getBackpack(int playerID) throws SQLException, ClassNotFoundException
    {
        ArrayList<Item> backpackArrayList = new ArrayList<>();
        try {
            SQLiteDB sdb = new SQLiteDB();
            String sql = "Select * from ITEM WHERE playerID = " + playerID + " AND inInventory = 1;";
            ResultSet rs = sdb.queryDB(sql);
            while(rs.next()) {
                Item it = new Item();
                it.setItemID(rs.getInt("itemID"));
                it.setItemName(rs.getString("itemName"));
                it.setItemDescription(rs.getString("itemDesc"));
                it.setHealValue(rs.getInt("healValue"));
                it.setCanHeal(rs.getInt("canHeal"));
                backpackArrayList.add(it);
            }
            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return backpackArrayList;
    }

    public void printInventory(int playerID) throws SQLException, ClassNotFoundException {

            SQLiteDB sdb = new SQLiteDB();
            String sql = "Select * from ITEM WHERE playerID = " + playerID + " AND inInventory = 1";
            ResultSet rs = sdb.queryDB(sql);

            while(rs.next()) {
                Item it = new Item();
                it.setItemID(rs.getInt("itemID"));
                it.setItemName(rs.getString("itemName"));
                it.setItemDescription(rs.getString("itemDesc"));
                System.out.println("Item Name: [" + it.getItemName() + "]");
            }
            sdb.close();

    }
}
