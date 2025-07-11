/**Class: PlayerDB.java
 * @author Bradley Iverson
 * @updated Kyle Williams
 * @version 1.0
 * course: ITEC 3860 Spring 2022
 * Written: Apr 6 - Apr 27, 2022
 *
 *This class updates and queries the database in regards to the puzzle functionality.
 **/

package model;

import controller.Puzzle;
import gameExceptions.GameException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuzzleDB
{
    private ArrayList<Puzzle> puzzles = new ArrayList<>();
    /**
     * @param playerID
     * @return
     * @throws GameException
     * @purpose Gets a particular puzzle object based on the room ID.
     */
    public ArrayList<Puzzle> getPuzzles(int playerID) throws GameException {
        ArrayList<Puzzle> puzzleArrayList = new ArrayList<>();

        try {
            SQLiteDB sqLiteDB = new SQLiteDB();
            String sqlCmd = "Select * from PUZZLE WHERE playerID = " + playerID;
            ResultSet rs = sqLiteDB.queryDB(sqlCmd);
            while (rs.next()) {
                Puzzle pz = new Puzzle();
                pz.setPuzzleID(rs.getInt("puzzleID"));
                pz.setDescription(rs.getString("description"));
                pz.setItemToSolve(rs.getString("ItemToSolve"));
                if (rs.getInt("solved") == 1) {
                    pz.setSolved(true);
                } else if (rs.getInt("solved") == 0) {
                    pz.setSolved(false);
                }
                pz.setHint(rs.getString("hint"));
                pz.setRoomID(rs.getInt("roomID"));
                puzzleArrayList.add(pz);
            }
            sqLiteDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return puzzleArrayList;
    }

    /**
     * @throws GameException
     * @purpose update the puzzle solved status to true after completed
     */
    public void updatePuzzle(int puzzleID, int playerID) throws GameException, SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        String sql = "UPDATE PUZZLE SET solved = 1 WHERE puzzleID = " + puzzleID + " AND playerID = " + playerID;
        sdb.updateDB(sql);
        sdb.close();
    }

    public int isSolved(int puzzleID, int playerID) throws SQLException, ClassNotFoundException {
        int solvedNum = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT solved FROM PUZZLE WHERE puzzleID = " + puzzleID + " AND playerID = " + playerID;
        ResultSet rs = sdb.queryDB(sql);
        while(rs.next()) {
            solvedNum = rs.getInt("solved");
        }
        sdb.close();
        System.out.println(solvedNum);
        return solvedNum;
    }

    public ArrayList<Puzzle> currRoomPuzzles(int currRoom, int playerID)
    {
        ArrayList<Puzzle> puzzleArrayList = new ArrayList<>();
        try {
            SQLiteDB sdb = new SQLiteDB();
            String sql = "Select * from PUZZLE WHERE solved = 0 AND roomID = " + currRoom + " AND playerID = " + playerID;
            ResultSet rs = sdb.queryDB(sql);
            while(rs.next()) {
                Puzzle pz = new Puzzle();
                pz.setPuzzleID(rs.getInt("puzzleID"));
                pz.setDescription(rs.getString("description"));
                pz.setItemToSolve(rs.getString("ItemToSolve"));
                pz.setHint(rs.getString("hint"));
                pz.setRoomID(rs.getInt("roomID"));
                puzzleArrayList.add(pz);
            }
            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (GameException e) {
            e.printStackTrace();
        }
        return puzzleArrayList;
    }
}


