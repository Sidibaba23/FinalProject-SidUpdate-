/**Class: Item
 * @author Kyle Williams, Rick Price, Bradley Iverson, Miranda Darlington
 * @version 1.0
 * Course: ITEC 3860, Spring 2022
 * Written: 02/18 - 04/27
 *
 *
 * This class acts as a blueprint for all Item objects.
 *
 */

package controller;

public class Item {

    // Attributes
    private String itemDescription;
    private int itemID;
    private String itemName;
    private int roomID;
    private int canHeal;
    private int healValue;


    public int getCanHeal() {
        return canHeal;
    }

    public void setCanHeal(int canHeal) {
        this.canHeal = canHeal;
    }

    public int getHealValue() {
        return healValue;
    }

    public void setHealValue(int healValue) {
        this.healValue = healValue;
    }

    /**
     * @return roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * @purpose no arg constructor.
     */
    public Item() {

    }

    /**
     * @return itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @param itemID
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return String for displaying an Item obj.
     */
    public String display() {

        return "This item is called: " + getItemName() + ". " + getItemDescription();
    }

    /**
     * @return toString for Item obj.
     */
    @Override
    public String toString() {
        return "Item{" +
                "itemDescription='" + itemDescription + '\'' +
                ", itemID=" + itemID +
                ", roomID=" + roomID +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
