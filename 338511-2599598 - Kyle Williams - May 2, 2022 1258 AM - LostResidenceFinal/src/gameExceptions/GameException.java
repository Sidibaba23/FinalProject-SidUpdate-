/**Class: GameController.java
 * @author Kyle Williams, Rick Price
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27
 *
 *
 * This class serves the purpose of exception handling.
 *
 */

package gameExceptions;

import java.io.IOException;

public class GameException extends IOException {

    /**
     * @purpose no arg constructor that transitions to IOException constructor if called.
     */
    public GameException() { super();}

    /**
     * @param message
     * @purpose super calls the the constructor class of IOException.
     */
    public GameException(String message) {
        super(message);
    }

}
