/**Class: RoomDB.java
 * @author Kyle Williams, Rick Price, Hyesung Park
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27
 *
 *
 * The purpose of this class is to hold methods that update and query the database.
 *
 */

package model;

import controller.Item;
import controller.Room;
import gameExceptions.GameException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDB {

    private ArrayList<Room> rooms = new ArrayList<>();

    /**
     * @param roomID
     * @return
     * @throws GameException
     * @purpose Get's a particular room object based on the room ID.
     */
    public Room getRoom(int roomID) throws GameException {
        Room rm = new Room();
        try {
            SQLiteDB sqLiteDB = new SQLiteDB();
            String sqlCmd = "Select * from ROOM WHERE roomNumber = " + roomID;
            ResultSet rs = sqLiteDB.queryDB(sqlCmd);
            while (rs.next()) {
                rm.setRoomID(rs.getInt("roomNumber"));
                rm.setName(rs.getString("roomName"));
                rm.setDescription(rs.getString("roomDescription"));
                rooms.add(rm);
            }
            sqLiteDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rm;
    }


    public ArrayList<Item> getItems(int roomID) throws GameException {

        return null;
    }


    public String getMap() {

        return null;
    }


    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void updateRoom(int roomID) throws GameException, SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE ROOM SET visited = 1 WHERE roomNumber = " + roomID;
        sdb.updateDB(sql);
        sdb.close();
    }

    public int isVisited(int roomID) throws SQLException, ClassNotFoundException {
        int visitedNum = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT visited FROM ROOM WHERE roomNumber = " + roomID;
        ResultSet rs = sdb.queryDB(sql);
        while(rs.next()) {
            visitedNum = rs.getInt("visited");
        }
        sdb.close();
        return visitedNum;
    }


}
