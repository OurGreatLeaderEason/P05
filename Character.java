//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Dragon Treasure Game 2.0
// Course:   CS 300 Fall 2022
//
// Author:   Eason Xiao
// Email:    xiao227@wisc.edu
// Lecturer: Jeff Nyhoff
//
import java.util.ArrayList;

/**
 * Class that emulates the behavior of a Character
 */
public class Character {
    private Room currentRoom; //current room the character is in
    private String label; //a label giving a basic description of the character

    /**
     * Constructor for a Character object. Initializes all instance fields.
     * @param currentRoom the room that the Character is located in
     * @param label a descriptive label of this Character
     */
    public Character(Room currentRoom, String label){
        this.currentRoom=currentRoom;
        this.label=label;
    }

    /**
     * Getter for the current room of this Character.
     * @return the room where the character is
     */
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    /**
     * Getter for the label of this Character.
     * @return this Character's descriptive label
     */
    public String getLabel(){
        return this.label;
    }

    /**
     * Gets the list of rooms adjacent to this Character.
     * @return an ArrayList of rooms adjacent to this character
     */
    public ArrayList<Room> getAdjacentRooms(){
        return this.currentRoom.getAdjacentRooms();
    }

    /**
     * Sets the current room to the one given.
     * @param newRoom the room that should become the current room
     */
    public void setCurrentRoom(Room newRoom){
        this.currentRoom=newRoom;
    }

 
}
