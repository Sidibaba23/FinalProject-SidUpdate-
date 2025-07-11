/**Class: Adventure.java
 * @author Kyle Williams, Bradley Iverson, Miranda Darlington, Rick Price
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 03/11
 *
 *
 * This class houses the main method and is the starting point of the program.
 *
 */

package view;

import controller.*;
import gameExceptions.GameException;

import java.sql.SQLException;
import java.util.Scanner;

public class Adventure {

    // Making gc an instance of GameController.
    private GameController gc;
    private Scanner input = new Scanner(System.in);
    private int acctID;

    public Adventure() throws SQLException, ClassNotFoundException, GameException {
        gc = new GameController();

    }

    public void startDB() throws GameException {

        gc.start();
    }

    private void createPlayer() throws SQLException, GameException, ClassNotFoundException {
        System.out.println("Please enter in a player name.");
        String name = input.next();
        gc.createPlayer(name);
    }

    public void accountSetUpLogIn() throws SQLException, ClassNotFoundException, GameException, InterruptedException {
        boolean correctAnswer = false;
        while(!correctAnswer) {
            System.out.println("Do you have an account?");
            String answer = input.next();
            if (answer.equalsIgnoreCase("no")) {
                gc.createAccount();
                gc.setAccountID();
                createPlayer();
                correctAnswer = true;
            } else if (answer.equalsIgnoreCase("yes")) {
                gc.logIn();
                correctAnswer = true;
            } else {
                System.out.println("You might have made a typo, please type yes, or no.");
                correctAnswer = false;
            }
        }
    }

    private void playGame() throws GameException, SQLException, ClassNotFoundException {

        System.out.println("Welcome to Lost Residence..");

        // Just putting it here in case anyone wants to add this XD
        System.out.println("______              _____     ________            ______________           _____");
        System.out.println("___  / _______________  /_    ___  __ \\______________(_)_____  /_____________  /_");
        System.out.println("__  /  _  __ \\_  ___/  __/    __  /_/ /  _ \\_  ___/_  /_  __  /_  _ \\_  __ \\  __");
        System.out.println("_  /___/ /_/ /(__  )/ /_      _  _, _//  __/(__  )_  / / /_/ / /  __/  / / / /_ ");
        System.out.println("/_____/\\____//____/ \\__/      /_/ |_| \\___//____/ /_/  \\__,_/  \\___//_/ /_/\\__/ ");

        System.out.println("Would you like to play? Type [exit] to back out.");

        String choice = input.next();

        if (!choice.equalsIgnoreCase("exit")) {
            System.out.println(gc.displayFirstRoom());
        }

        while(!choice.equalsIgnoreCase("exit")) {
            String cmd = input.next();
            String result = gc.executeCommand(cmd);
            System.out.println(result);
        }
    }


    /**
     * @return choice
     */
    private String getCommand(){
        String choice = input.nextLine();
        return choice;
    }

    /**
     * @param args
     * @throws GameException
     * @purpose creates the room database, item database and starts the game.
     */
    public static void main(String[] args) throws GameException, SQLException, ClassNotFoundException, InterruptedException {

        // Start of game.
        Adventure adventure = new Adventure();
        adventure.startDB();
        adventure.accountSetUpLogIn();
        adventure.playGame();


    }
}
