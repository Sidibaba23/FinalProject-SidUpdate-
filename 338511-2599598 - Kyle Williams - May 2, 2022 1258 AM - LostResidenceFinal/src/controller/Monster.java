/**Class: Monster.java
 * @author Miranda Darlington, Bradley Iverson, Kyle Williams, Rick Price
 * @version 1.0
 * course: ITEC 3860 Spring 2022
 * Written: Apr 5, 2022
 *
 *This class- !!!!!!
 *taken and modified from monster.java from hp repository on GitHub
 **/

package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.MonsterDB;

public class Monster
{

	private int monsterID;
	private int roomID;
	private String monsterName;
	private String monsterDescription;
	private int hitPoints;
	private int attackDamage;
	private int isDead;

	/**
	 * Constructor: Monster
	 */
	public Monster()
	{
		MonsterDB mdb = new MonsterDB();
		try
		{
			monsterID = mdb.getNextMonsterID();
		} catch (SQLException sqe)
		{
			System.out.println(sqe.getMessage());
		}
	}

	/**
	 * Method: getMonsterID
	 * 
	 * @return the monsterID
	 */
	public int getMonsterID()
	{
		return monsterID;
	}

	/**
	 * Method: setMonsterID
	 *
	 * @param monsterID the monsterID to set
	 */
	public void setMonsterID(int monsterID)
	{
		this.monsterID = monsterID;
	}

	/**
	 * Method: getMonsterName
	 * 
	 * @return the monsterName
	 */
	public String getMonsterName()
	{
		return monsterName;
	}

	/**
	 * Method: setMonsterName
	 * 
	 * @param monsterName the monsterName to set
	 */
	public void setMonsterName(String monsterName)
	{
		this.monsterName = monsterName;
	}

	/**
	 * Method: getMonsterDescription
	 * 
	 * @return the monsterDescription
	 */
	public String getMonsterDescription()
	{
		return monsterDescription;
	}

	/**
	 * Method: setMonsterDescription
	 * 
	 * @param monsterDescription the monsterDescription to set
	 */
	public void setMonsterDescription(String monsterDescription)
	{
		this.monsterDescription = monsterDescription;
	}

	/**
	 * Method: getHitPoints
	 * 
	 * @return the hitPoints
	 */
	public int getHitPoints()
	{
		return hitPoints;
	}

	/**
	 * Method: setHitPoints
	 * 
	 * @param hitPoints the hitPoints to set
	 */
	public void setHitPoints(int hitPoints)
	{
		this.hitPoints = hitPoints;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	/**
	 * Getter Method for isDead
	 * 
	 * @return the isDead
	 */
	public int isDead()
	{
		return isDead;
	}

	/**
	 * @param isDead the isDead to set
	 */
	public void setDead(int isDead)
	{
		this.isDead = isDead;
	}

	/**
	 *Getter Method for roomID
	 * 
	 *@return the roomID
	 */
	public int getRoomID()
	{
		return roomID;
	}

	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(int roomID)
	{
		this.roomID = roomID;
	}


	/**
	 * Method: toString Purpose: Returns a String of the Monster class
	 * 
	 * @return
	 */
	@Override
	public String toString()
	{
		return "Monster ID #" + monsterID + ": " + monsterName + "\n" + monsterDescription + "\n" + "HP: " + hitPoints + " | Damage: " + attackDamage;
	}
}
