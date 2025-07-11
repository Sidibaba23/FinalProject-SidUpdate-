/**Class: PlayerDB.java
 * @author Bradley Iverson, Kyle Williams
 * @version 1.0
 * course: ITEC 3860 Spring 2022
 * Written: Apr 6 - Apr 27, 2022
 *
 *This class updates and queries the database in regards to the player functionality.
 **/

package model;

import gameExceptions.GameException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDB {

    public void updatePlayerRoom(int playerID, int currRoom, int prevRoom) {
        try {
            SQLiteDB sdb = new SQLiteDB();
            String sql = "UPDATE PLAYER SET curr_Room = " + currRoom + " WHERE playerID = " + playerID;
            sdb.updateDB(sql);
            sdb.close();
            sdb = new SQLiteDB();
            sql = "UPDATE PLAYER SET prev_Room = " + prevRoom + " WHERE playerID = " + playerID;
            sdb.updateDB(sql);
            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentRoom(int playerID) throws SQLException, ClassNotFoundException {
            int currRoom = 0;
            SQLiteDB sdb = new SQLiteDB();
            String sql = "SELECT curr_Room FROM PLAYER WHERE playerID = " + playerID;
            ResultSet rs = sdb.queryDB(sql);
            if (rs.next()){
                currRoom = rs.getInt("curr_Room");
            }
            sdb.close();
            return currRoom;
    }

    public int getHealthPoint(int playerID) throws SQLException, ClassNotFoundException {
        int currRoom = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT healthPoint FROM PLAYER WHERE playerID = " + playerID;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()){
            currRoom = rs.getInt("healthPoint");
        }
        sdb.close();
        return currRoom;
    }

    /**
     * @throws GameException
     * @purpose decrease monster's HP
     */
    public void attackedPlayer(int playerID, int damage) throws GameException, SQLException, ClassNotFoundException
    {
        SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE PLAYER SET healthPoint = healthPoint - " + damage + " WHERE playerID = " + playerID;
        sdb.updateDB(sql);
        sdb.close();
    }

    public void heal(int playerID, int heal) throws GameException, SQLException, ClassNotFoundException
    {
        SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE PLAYER SET healthPoint = healthPoint + " + heal + " WHERE playerID = " + playerID;
        sdb.updateDB(sql);
        if(getHealthPoint(playerID) > 100)
        {
            sql = "UPDATE PLAYER SET healthPoint = 100 WHERE playerID = " + playerID;
            sdb.updateDB(sql);
        }
        sdb.close();
    }

    public int getAttackDamage(int playerID) throws SQLException, ClassNotFoundException {
        int attackDamage = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT attackDamage FROM PLAYER WHERE playerID = " + playerID;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()){
            attackDamage = rs.getInt("attackDamage");
        }
        sdb.close();
        return attackDamage;
    }

    public int getPlayerID(String name, int acctID) throws SQLException, ClassNotFoundException, GameException {
        SQLiteDB sdb = new SQLiteDB();
        int newPlayerID = 0;

            String getPlayerID = "SELECT playerID FROM PLAYER WHERE playerName = " + "'"+name+"'" + " and acctID = " + acctID;
            ResultSet rs = sdb.queryDB(getPlayerID);
            if (rs.next()) {
                newPlayerID = rs.getInt("playerID");
            }
            sdb.close();

        return newPlayerID;
    }
}
