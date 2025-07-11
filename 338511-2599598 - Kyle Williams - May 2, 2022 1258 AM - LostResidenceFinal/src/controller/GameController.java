/**Class: GameController
 * @author Kyle Williams, Rick Price, Miranda Darlington, Bradley Iverson
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27
 *
 *
 * This class acts as a birds eye view of where the commands go.
 * There is also a lot of account functionality in this class.
 *
 */

package controller;

import gameExceptions.GameException;
import model.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController implements logIn{

    // Attributes
    private Commands commands;
    private static ArrayList<String> VALID_COMMANDS = null;
    private int acctID;
    private int playerID;
    private String userName;

    /**
     * @purpose instantiates new instance of Commands class.
     */
    public GameController() throws SQLException, ClassNotFoundException {
        commands = new Commands();

    }

    public void createAccount() throws SQLException, ClassNotFoundException, InterruptedException {
        SQLiteDB sdb = new SQLiteDB();
        AccountDB accountDB = new AccountDB();
        Scanner scan = new Scanner(System.in);
        boolean unique = false;

        while(!unique){

            System.out.println("============Please enter a username below============");
            String userName = scan.nextLine();
            System.out.println("============Please enter a password below============");
            String password = scan.nextLine();

            unique = accountDB.checkUnique(userName, password);

            if (unique){
                String sql = "INSERT INTO ACCOUNT(userName, password) Values ('"+userName+"', '"+password+"')";
                sdb.updateDB(sql);
                this.userName = userName;
                sdb.close();
                break;

            }
            System.out.println("Your user name or password is not unique");
        }

        System.out.println("Thanks for creating an account!");
    }

    public void logIn() throws InterruptedException, SQLException, ClassNotFoundException, GameException {
        SQLiteDB sdb = new SQLiteDB();
        Scanner scan = new Scanner(System.in);
        PlayerDB playerDB = new PlayerDB();
        AccountDB accountDB = new AccountDB();
        int count = 0;

        boolean exists = false;

        while(!exists) {

            System.out.println("Please enter a username:");
            String userName = scan.nextLine();
            System.out.println("Please enter a password: ");
            String password = scan.nextLine();
            exists = accountDB.checkExists(userName, password);

            if (exists){

                ArrayList<String> listOfPlayersAssociated = new ArrayList<>();
                int acctID = accountDB.getAccountIDAfterLogIn(userName);
                this.acctID = acctID;

                System.out.println("Log in successful, would you like to [CREATE] a new player or [CHOOSE] from existing players: (Please type CREATE, or CHOOSE)");
                String userDecision = scan.next();
                if (userDecision.equalsIgnoreCase("CREATE")){
                    System.out.println("Please enter in a player name: ");
                    String pName = scan.next();
                    createPlayer(pName);
                } else if (userDecision.equalsIgnoreCase("CHOOSE")) {

                    String sql = "SELECT playerName FROM PLAYER WHERE acctID = " + acctID;
                    ResultSet rs = sdb.queryDB(sql);

                    while (rs.next()) {
                        listOfPlayersAssociated.add(rs.getString("playerName"));
                    }

                    System.out.println("Please choose one of your saved players by typing in the name");

                    for (String playerName : listOfPlayersAssociated) {
                        count++;
                        System.out.println("Player Option " + count + ": " + playerName + " playerID = " + playerID);
                    }

                    String playerChoice = scan.next();

                    for (String playerName : listOfPlayersAssociated) {
                        if (playerName.equalsIgnoreCase(playerChoice)) {
                            playerChoice = playerChoice.substring(0,1).toUpperCase() + playerChoice.substring(1).toLowerCase();
                            playerID = playerDB.getPlayerID(playerChoice, acctID);
                            commands.getPlayer().setPlayerID(playerID);
                            commands.getPlayer().setCurrRoom(playerDB.getCurrentRoom(playerID));
                            System.out.println(playerID);
                            System.out.println(playerChoice);
                            System.out.println(acctID);
                            break;
                        }
                    }
                }

            } else {
                System.out.println("Login failed, please try again!");
                exists = false;
            }
        }

    }

    public void start() throws GameException {

        File dbFile = new File("Game.db");
//        if (dbFile.exists())
//        {
//
//            dbFile.delete();
//        }

        if (!dbFile.exists())
        {
            CreateFilesController cfc = new CreateFilesController();
            cfc.createFile();
        }
    }

    public String displayFirstRoom() throws GameException {
        RoomDB roomDB = new RoomDB();
        Room room = roomDB.getRoom(commands.getPlayer().getCurrRoom());
        System.out.println(commands.getPlayer().getCurrRoom());
        return room.getName() + ": \n" + room.getDescription();
    }

    public void createPlayer(String name) throws SQLException, GameException, ClassNotFoundException {
        GameDBCreate gameDBCreate = new GameDBCreate();
        PlayerDB playerDB = new PlayerDB();
        gameDBCreate.addPlayer(name, acctID);
        playerID = playerDB.getPlayerID(name, acctID);
        commands.getPlayer().setCurrRoom(playerDB.getCurrentRoom(playerID));
        gameDBCreate.updateNewPlayerTables(playerID);
        commands.getPlayer().setPlayerID(playerID);
    }

    public String executeCommand(String cmd) throws GameException, SQLException, ClassNotFoundException
    {
        String result = commands.executeCommand(cmd);
        String returnString = "";
        returnString = result.replaceAll(".{100}", "$0\n");

        return returnString;

    }

    public String printMap() throws GameException {

        return "";
    }

    public void setAccountID() throws SQLException, ClassNotFoundException, InterruptedException {
        System.out.println("Loading..");

        System.out.println(userName);
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT accountID FROM ACCOUNT WHERE userName = " + "'"+userName+"'";
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()){
            int acctID = rs.getInt("accountID");
            this.acctID = acctID;
            commands.getPlayer().setAcctID(acctID);
        }
        sdb.close();

    }
}
