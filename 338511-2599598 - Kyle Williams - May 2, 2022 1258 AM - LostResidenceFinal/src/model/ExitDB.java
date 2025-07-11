/**Class: ExitDB.java
 * @author Kyle Williams
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 04/15 - 04/16
 *
 *
 * This class get the needed information in regards to exits from the database.
 *
 */

package model;

import controller.Exit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExitDB {


    public ArrayList<Exit> currRoomExitOptions(int currRoom) {

        ArrayList<Exit> exitArrayList = new ArrayList<>();

        try {
            SQLiteDB sdb = new SQLiteDB();

            String sql = "SELECT EXIT.direction, EXIT.destination" +
                         " FROM EXIT" +
                         " INNER JOIN ROOM ON EXIT.roomID=ROOM.roomNumber" +
                         " WHERE ROOM.roomNumber = " + currRoom;
            ResultSet rs = sdb.queryDB(sql);
            while(rs.next()) {
                Exit exit = new Exit();
                exit.setDirection(rs.getString("direction"));
                exit.setDestination(rs.getInt("destination"));
                exitArrayList.add(exit);
            }
            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return exitArrayList;
    }
}
