/**Class: Puzzle
 * @author Bradley Iversen
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 04-13-2022
 *
 *
 * This class serves as the blueprint for all puzzle objects that get stores in the puzzle database.
 *
 */

package controller;

import gameExceptions.GameException;
import model.PuzzleDB;
import model.RoomDB;

import java.sql.SQLException;

public class Puzzle
{
    // Attributes
    private int puzzleID;
    private String description;
    private String ItemToSolve;
    private String hint;
    private int roomID;
    private boolean solved;

    /**
     * @throws GameException
     * @purpose constructor that only assigns the id.
     */
    public Puzzle() throws GameException {
        this.puzzleID = puzzleID;
    }

    // Getters and Setters
    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemToSolve() {
        return ItemToSolve;
    }

    public void setItemToSolve(String ItemToSolve) {
        this.ItemToSolve = ItemToSolve;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleID=" + puzzleID +
                ", description='" + description + '\'' +
                ", IDToSolve=" + ItemToSolve +
                ", hint='" + hint + '\'' +
                ", roomID=" + roomID +
                ", solved=" + solved +
                '}';
    }
}






