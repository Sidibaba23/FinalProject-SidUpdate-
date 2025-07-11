/**Class: GameDBCreate.java
 * @author Rick Price, Kyle Williams, Bradley Iverson, Miranda Darlington
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 03/11 - 04/27
 *
 *
 * This class get the needed information in regards creating the database tables
 * and inserting information.
 *
 */

package model;

import gameExceptions.GameException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class GameDBCreate {

    SQLiteDB sdb;

    public void buildTables() throws SQLException, ClassNotFoundException, GameException {
        buildAccount();
        buildPlayer();
        buildMonster();
        buildItem();
        buildPuzzle();
        buildRoom();
        buildExit();

    }

    // This is called every time a new player is created in the game.
    public void updateNewPlayerTables (int playerID) throws SQLException, ClassNotFoundException {
        addNewPlayerItems(playerID);
        addNewPlayerMonsters(playerID);
        addNewPlayerPuzzles(playerID);
    }

    private void addNewPlayerMonsters(int playerID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB();

        String sql = "INSERT INTO MONSTER(RoomNumber, MonsterName, MonsterDescription, hitPoints, attackDamage, isDead, playerID) Values(20, 'Zombie', " +
                "'A grotesque humanoid figure that shambles towards you. Their flesh is putrid and falling off. The more you look at it, the more you want to throw up.'," +
                " 50, 5, 0, " + playerID + ");";
        sdb.updateDB(sql);

        sql = "INSERT INTO MONSTER(RoomNumber, MonsterName, MonsterDescription, hitPoints, attackDamage, isDead, playerID) Values(18, 'knight', " +
                "'A suite of armor comes to life and blocks your way forward. It holds a rather menacing axe that has dried blood on its end. " +
                "While it looks like you can push it over from how rusted and old it looks, " +
                "you know that getting close to it would probably spell your doom', 25, 5, 0, " + playerID + ");";
        sdb.updateDB(sql);

        sql = "INSERT INTO MONSTER(RoomNumber, MonsterName, MonsterDescription, hitPoints, attackDamage, isDead, playerID) Values(28, 'Demon Dog', " +
                "'What you thought was a guard dog turns out to be something that came straight from hell. " +
                "It growls in an unnatural voice and the teeth look sharp enough to tear you to shreds. " +
                "Their eyes burn with a firery red', 75, 10, 0, " + playerID + ");";
        sdb.updateDB(sql);

        sql = "INSERT INTO MONSTER(RoomNumber, MonsterName, MonsterDescription, hitPoints, attackDamage, isDead, playerID) Values(32, 'Infected Soldier', " +
                "'A soldier who looks like he should not be alive. By the looks of it, he died a very nasty bullet wound to the head, " +
                "if the gapping hole on the side of his head is any indication. Despite that, he jerks around as though whoever is piloting him " +
                "is still getting used to piloting a body', 85, 10, 0, " + playerID + ");";
        sdb.updateDB(sql);

        sql = "INSERT INTO MONSTER(RoomNumber, MonsterName, MonsterDescription, hitPoints, attackDamage, isDead, playerID) Values(0, 'True Terror', " +
                "'Words cannot even begin to describe what stands before you. Their form is large, taller and bulkier than anything you have seen before. " +
                "You can not seem to find their eyes, yet you know that they are staring you down like a piece of meat. " +
                "You feel rather petrified as their mouth opens to reveal sharp teeth and a long, serpentine tongue.', 100, 15, 0, " + playerID + ");";
        sdb.updateDB(sql);

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
    }

    private void addNewPlayerItems(int playerID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB();

        String sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal) Values (1, 0, 'Compass', " +
                "'Your trustworthy tool of navigation, useful for navigating even without its bulkier siblings. " +
                "Though it may be scratched, it is still very much functional and legible.', " + playerID + ", 0);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal) Values (11, 0, " +
                "'Tool Box', 'A box full of useful sets of tools. It may be heavy, and some of the tools have damage from being left in humid air, " +
                "but it is worth its weight in gold to you in your current predicament.', " + playerID + ", 0);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal) Values (33, 0, 'Fuel', 'Salvaged from an inoperable flamethrower, " +
                "this large container still contains quite a bit of fuel. " +
                "Hopefully, it will be suitable for use inside a boat’s engine. " +
                "Though, even if it is not, a coin-flip of explosion or being stranded is better than being trapped here.', " + playerID + ", 0);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal) Values (7, 0, 'Bait', " +
                "'A nondescript, sealed, bag of residue. Useful for making yourself puke from its wretched stench, " +
                "or distracting some beast either by making it flee in disgust, or approach with hunger.', " + playerID + ", 0);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal) Values (8, 0, 'Bait', 'Just more bait to add to the collection.', " + playerID + ", 0);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal, healValue) Values (1, 0, 'Apple', 'Increase your HP by 20.', " + playerID + ", 1, 60);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal, healValue) Values (1, 0, 'Beef', 'Increase your HP by 50.', " + playerID + ", 1, 80);";
        sdb.updateDB(sql);

        sql = "INSERT INTO ITEM (roomID, inInventory, itemName, itemDesc, playerID, canHeal, healValue) Values (2, 0, 'Med Kit', 'Restore you HP back to full.', " + playerID + ", 1, 100);";
        sdb.updateDB(sql);

        sdb.close();
    }

    private void addNewPlayerPuzzles(int playerID) throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB();

        String sql = "INSERT INTO PUZZLE(description, ItemToSolve, hint, roomID, solved, playerID) Values('You need something that helps guide you.'," +
                " 'compass', 'You need a compass', 13, 0, " + playerID + ");";
        sdb.updateDB(sql);

        sql = "INSERT INTO PUZZLE(description, ItemToSolve, hint, roomID, solved, playerID) Values('You to some tools that could open the door.', 'tool box', 'You need a toolbox', 4, 0, " + playerID + ");";
        sdb.updateDB(sql);

        sql = "INSERT INTO PUZZLE(description, ItemToSolve, hint, roomID, solved, playerID) Values('You need to refuel the engine in order to leave the island', 'fuel', 'You need fuel', 5, 0, " + playerID + ");";
        sdb.updateDB(sql);
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
    }

    public void buildAccount() throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB();
        String sql  = "CREATE TABLE ACCOUNT(accountID integer not null primary key autoincrement, userName text not null, password text not null)";
        sdb.updateDB(sql);
        sdb.close();

    }

    /** 
	 * 
	 */
	private void buildMonster() throws SQLException, ClassNotFoundException, GameException
	{
        sdb = new SQLiteDB();

        FileReader fileReader;
        try {
            fileReader = new FileReader("monsters.txt");
            Scanner inFile = new Scanner(fileReader);
            while(inFile.hasNextLine()) {
                String sql = inFile.nextLine();
                sdb.updateDB(sql);
            }
            inFile.close();
        } 
        catch (FileNotFoundException e) {
            throw new GameException("monsters.txt was not found");
        }
        sdb.close();
		
	}

    public void buildPuzzle() throws SQLException, ClassNotFoundException, GameException
    {
        sdb = new SQLiteDB();

        FileReader fileReader;
        try {
            fileReader = new FileReader("puzzles.txt");
            Scanner inFile = new Scanner(fileReader);
            while(inFile.hasNextLine())
            {
                String sql = inFile.nextLine();
                sdb.updateDB(sql);
            }
            inFile.close();

        } catch (FileNotFoundException e)
        {
            throw new GameException("puzzles.txt was not found");
        }
        sdb.close();
    }

	public void buildRoom() throws SQLException, ClassNotFoundException, GameException {
        sdb = new SQLiteDB();

        FileReader fileReader;
        try {
            fileReader = new FileReader("rooms.txt");
            Scanner inFile = new Scanner(fileReader);
            while(inFile.hasNextLine()) {
                String sql = inFile.nextLine();
                sdb.updateDB(sql);
            }
            inFile.close();

        } catch (FileNotFoundException e) {
            throw new GameException("rooms.txt was not found");
        }
        sdb.close();

    }


    public void buildPlayer() throws SQLException, ClassNotFoundException, GameException {
        sdb = new SQLiteDB();

        try {
            String sql = "CREATE TABLE PLAYER(playerID integer not null primary key autoincrement, playerName varchar(25) not null, healthPoint int not null, attackDamage int not null, curr_Room int not null, prev_Room int not null, acctID integer not null); " +
                    " FOREIGN KEY (acctID) REFERENCES ACCOUNT(accountID);";
            sdb.updateDB(sql);


        } catch (SQLException e) {
            System.out.println(e);
        }
        sdb.close();

    }

    public void addPlayer(String name, int acctID) throws SQLException, ClassNotFoundException, GameException {
        sdb = new SQLiteDB();

        try {

            String sqlNewPlayer = "INSERT INTO PLAYER(playerName, healthPoint, attackDamage, curr_Room, prev_room, acctID) Values ('"+name+"', 100, 10, 1, 1, " + acctID + ")";
            sdb.updateDB(sqlNewPlayer);

        } catch (SQLException e) {
            System.out.println(e);
        }
        sdb.close();
    }

    public void buildItem() throws SQLException, ClassNotFoundException, GameException {
        sdb = new SQLiteDB();

        FileReader fileReader;
        try {
            fileReader = new FileReader("items.txt");
            Scanner inFile = new Scanner(fileReader);
            while(inFile.hasNextLine()) {
                String sql = inFile.nextLine();
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new GameException("items.txt was not found");
        }
        sdb.close();

    }

    public void buildExit() throws SQLException, ClassNotFoundException, GameException {
        sdb = new SQLiteDB();

        FileReader fileReader;
        try {
            fileReader = new FileReader("exits.txt");
            Scanner inFile = new Scanner(fileReader);
            while(inFile.hasNextLine()) {
                String sql = inFile.nextLine();
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            throw new GameException("exits.txt was not found");
        }
        sdb.close();

    }
}
