/**Class: MonsterDB.java
 * @author Miranda Darlington, Bradley Iverson
 * @updated Kyle Williams
 * @version 1.0
 * course: ITEC 3860 Spring 2022
 * Written: Apr 6 - Apr 27, 2022
 * 
 *This class updates and queries the database in regards to the puzzle functionality.
 *Taken and modified from the monsterDB class from hp repository in GitHub
 **/

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Item;
import controller.Monster;
import gameExceptions.GameException;

public class MonsterDB
{
    //Don't think this is needed
	/**
     * Method: getNextMonsterID
     * Purpose: Gets the id for the next monster.
     * @return int
     */
    public int getNextMonsterID() throws SQLException {
        SQLiteDB sdb = null;
        try {
            sdb = new SQLiteDB();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int next = sdb.getMaxValue("MonsterID", "Monster") + 1;
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return next;
    }

    /**
     * @throws GameException
     * @purpose decrease monster's HP
     */
    public void attackedMonster(int monsterID, int damage, int playerID) throws GameException, SQLException, ClassNotFoundException
    {
        SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE MONSTER SET hitPoints = hitPoints - " + damage + " WHERE MonsterID = " + monsterID + " AND playerID = " + playerID;
        sdb.updateDB(sql);
        sdb.close();
    }

    /**
     * @throws GameException
     * @purpose decrease monster's HP then attack back (if still alive)
     */
    public int getMonsterAttack (int monsterID, int playerID) throws GameException, SQLException, ClassNotFoundException
    {
        int attackDamage = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT attackDamage FROM MONSTER WHERE MonsterID = " + monsterID + " AND playerID = " + playerID;
        ResultSet rs = sdb.queryDB(sql);
        while(rs.next())
        {
            attackDamage = rs.getInt("attackDamage");
        }
        sdb.close();
        return attackDamage;
    }

    /**
     * @throws GameException
     * @purpose update the monster isDead status to true after beaten
     */
    public void updateMonster(int monsterID, int playerID) throws GameException, SQLException, ClassNotFoundException
    {
        SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE MONSTER SET isDead = 1 WHERE MonsterID = " + monsterID + " AND playerID = " + playerID;
        sdb.updateDB(sql);
        sdb.close();
    }
    
    /**
     * @throws GameException
     * @purpose updates True Terror's roomID to appear in the docks
     */
    public void updateTrueTerror(int playerID) throws GameException, SQLException, ClassNotFoundException
    {
    	//I think what should happen is that it'll check to see if the player has the fuel in their inventory, then this will happen
    	//I don't think this method would pass anything, but lemme know if it should
    	//something like this from minigame 2 from the commands.java file
//    	for (Item it : player.getInventory())
//		{
//			if (cmd.equalsIgnoreCase(it.getItemName()))
//			{
//				return it.toString() + "\nThis is in your backpack!";
//			}
//		}
    	
    	//if(player inventory has ItemID == 103):
    	System.out.println("After you pick up the fuel, you hear a load crash somewhere. Maybe it'd be good to check out?");
    	SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE MONSTER SET RoomNumber = 2 WHERE MonsterID = 5 AND playerID = " + playerID;
        sdb.updateDB(sql);
        sdb.close();
    }

    // To check if a certain monster is dead (1) or still alive (0)
    public int isDead(int monsterID, int playerID) throws SQLException, ClassNotFoundException {
        int deadNum = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT isDead FROM MONSTER WHERE MonsterID = " + monsterID + " AND playerID = " + playerID;
        ResultSet rs = sdb.queryDB(sql);
        while(rs.next())
        {
            deadNum = rs.getInt("isDead");
        }
        sdb.close();
        System.out.println(deadNum);
        return deadNum;
    }

    /**
     * Method: getMonster
     * Purpose: handles db interactions to retrieve a single Monster
     * @param id
     * @return Monster
     * @throws SQLException
     */
    //This method doesn't appear to be used either.
    public Monster getMonster(int id, int playerID) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Monster mon = new Monster();
        String sql = "Select * from Monster WHERE MonsterID = " + id + " AND playerID = " + playerID;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            mon.setMonsterID(rs.getInt("MonsterID"));
            mon.setRoomID(rs.getInt("roomNumber"));
            mon.setMonsterName(rs.getString("monsterName"));
            mon.setMonsterDescription(rs.getString("monsterDescription"));
            mon.setHitPoints(rs.getInt("hitPoints"));
            mon.setAttackDamage(rs.getInt("attackDamage"));
            mon.setDead(rs.getInt("isDead"));

        }
        else {
        	throw new SQLException("Monster " + id + " not found.");
		}

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return mon;
    }

    /**
     * Method: getAllMonsters
     * Purpose: Handles the DB interactions to retrieve all monsters
     * @return ArrayList<Monster>
     * @throws SQLException
     */
    // This method doesn't appear to be used - might need to be deleted - Kyle Williams
    public ArrayList<Monster> getAllMonsters(int playerID) throws SQLException, ClassNotFoundException {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "Select * from MONSTER WHERE playerID = " + playerID;

        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            Monster mon = new Monster();
            mon.setMonsterID(rs.getInt("MonsterID"));
            mon.setRoomID(rs.getInt("RoomNumber"));
            mon.setMonsterName(rs.getString("MonsterName"));
            mon.setMonsterDescription(rs.getString("MonsterDescription"));
            mon.setHitPoints(rs.getInt("hitPoints"));
            mon.setAttackDamage(rs.getInt("attackDamage"));
            mon.setDead(rs.getInt("isDead"));
            monsters.add(mon);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return monsters;
    }

    //Prints out all the monster for a certain room
    public ArrayList<Monster> currRoomMonsters(int currRoom, int playerID)
    {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        try {
            SQLiteDB sdb = new SQLiteDB();
            String sql = "Select * from MONSTER WHERE isDead = 0 AND RoomNumber = " + currRoom + " AND playerID = " + playerID;
            ResultSet rs = sdb.queryDB(sql);
            while(rs.next()) {
                Monster mon = new Monster();
                mon.setMonsterID(rs.getInt("MonsterID"));
                mon.setRoomID(rs.getInt("RoomNumber"));
                mon.setMonsterName(rs.getString("MonsterName"));
                mon.setMonsterDescription(rs.getString("MonsterDescription"));
                mon.setHitPoints(rs.getInt("hitPoints"));
                mon.setAttackDamage(rs.getInt("attackDamage"));
                mon.setDead(rs.getInt("isDead"));
                monsters.add(mon);
            }
            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return monsters;
    }
}
