/**Class: Exit
 * @author Kyle Williams, Rick Price, Bradley Iverson, Miranda Darlington
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 03/11
 *
 *
 * This class acts as the blueprint for which Exit object is based on.
 *
 */

package controller;

import gameExceptions.GameException;

public class Exit {

    // Attributes
    private int destination;
    private String direction;
    //private final List<String> VALID_DIRECTIONS = Commands.VALID_DIRECTIONS;

    /**
     * no arg constructor for Exit class
     */
    public Exit() {

    }

    /**
     * @return direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return destination
     */
    public int getDestination() { return destination; }

    /**
     * @param destination
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * @return String version of this exit object.
     */
    @Override
    public String toString() {
        return "Exit{" +
                "destination=" + destination +
                ", direction='" + direction + '\'' +
                '}';
    }
}
