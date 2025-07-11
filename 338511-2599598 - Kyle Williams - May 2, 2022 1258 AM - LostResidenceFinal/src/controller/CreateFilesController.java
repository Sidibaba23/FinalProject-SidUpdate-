/**
 * Class : CreateFilesController.java
 * @author: Rick Price
 * @updated: Hyesung Park
 * @version: 2.0
 * Course: ITEC 3860
 * updated: March 26, 2022
 * This class calls into the model to build the database if it doesn't exist.
 */

package controller;

import java.sql.SQLException;

import gameExceptions.GameException;
import model.GameDBCreate;


public class CreateFilesController {

    public void createFile() throws GameException {
        try {
            GameDBCreate sdb = new GameDBCreate();
            sdb.buildTables();

        } catch(SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
