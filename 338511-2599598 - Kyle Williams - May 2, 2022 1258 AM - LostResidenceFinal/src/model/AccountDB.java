/**Class: AccountDB.java
 * @author Kyle Williams
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 04/15 - 04/25
 *
 *
 * This class get the needed information in regards to accounts from the database.
 *
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDB {

    public boolean checkUnique(String userName, String password) throws SQLException, ClassNotFoundException {

        ArrayList<String> listOfUserNames = new ArrayList<>();
        ArrayList<String> listOfPasswords = new ArrayList<>();
        SQLiteDB sdb = new SQLiteDB();
        String sql1 = "SELECT userName FROM ACCOUNT";
        ResultSet rs1 = sdb.queryDB(sql1);
        boolean bothUnique = true;
        boolean uniqueUserName = true;
        boolean uniquePassword = true;

        while (rs1.next()) {
            listOfUserNames.add(rs1.getString("userName"));
        }

        for (String pword : listOfPasswords) {
            if (password.equalsIgnoreCase(password)) {
                uniquePassword = false;
                break;
            } else {
                uniquePassword = true;
            }
        }

        String sql2 = "SELECT password FROM ACCOUNT";
        ResultSet rs2 = sdb.queryDB(sql2);

        while (rs2.next()) {
            listOfPasswords.add(rs2.getString("password"));
        }

        for (String name : listOfUserNames) {
            if (name.equalsIgnoreCase(userName)) {
                uniqueUserName = false;
                break;
            } else {
                uniqueUserName = true;
            }
        }

        sdb.close();

        if (uniquePassword && uniqueUserName) {
            bothUnique = true;
        } else {
            bothUnique = false;
        }
        return bothUnique;

    }

    public boolean checkExists(String userName, String password) throws SQLException, ClassNotFoundException {

        ArrayList<String> listOfUserNames = new ArrayList<>();
        ArrayList<String> listOfPasswords = new ArrayList<>();
        SQLiteDB sdb = new SQLiteDB();

        String sql1 = "SELECT userName FROM ACCOUNT";
        ResultSet rs1 = sdb.queryDB(sql1);

        boolean existsUserName = true;
        boolean existsPassword = true;
        boolean bothExist = true;

        if (!rs1.next()){
            return false;
        }

        ResultSet rs3 = sdb.queryDB(sql1);

        while (rs3.next()) {
            listOfUserNames.add(rs3.getString("userName"));
        }

        String sql2 = "SELECT password FROM ACCOUNT";
        ResultSet rs2 = sdb.queryDB(sql2);

        while (rs2.next()) {
            listOfPasswords.add(rs2.getString("password"));
        }

        for (String pword : listOfPasswords) {
            if (pword.equalsIgnoreCase(password)) {
                existsPassword = true;
                break;
            } else {
                existsPassword = false;
            }
        }

        for (String name : listOfUserNames) {
            if (name.equalsIgnoreCase(userName)) {
                existsUserName = true;
                break;
            } else {
                existsUserName = false;
            }
        }

        if (existsPassword && existsUserName) {
            bothExist = true;
        } else {
            bothExist = false;
        }

        sdb.close();

        return bothExist;
    }

    public int getAccountIDAfterLogIn(String userName) throws SQLException, ClassNotFoundException {
        int acctID = 0;
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT accountID FROM ACCOUNT WHERE userName = " + "'"+userName+"'";
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            acctID = rs.getInt("accountID");
        } else {
            acctID = 0;
        }
        sdb.close();
        return acctID;
    }

}
