/**Class: logIn
 * @author Kyle Williams
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 04/23
 *
 *
 * Creates the structure for the logIn method to be used in the GameController.
 *
 */

package controller;

import gameExceptions.GameException;

import java.sql.SQLException;

public interface logIn {

    void logIn() throws InterruptedException, SQLException, ClassNotFoundException, GameException;
}
