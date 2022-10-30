//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Dragon Treasure Game 2.0
// Course:   CS 300 Fall 2022
//
// Author:   Eason Xiao
// Email:    xiao227@wisc.edu
// Lecturer: Jeff Nyhoff
//
import processing.core.PImage;

/**
 * The class that represents a start room
 */
public class StartRoom extends Room{

    /**
     * Constructor for start room
     * @param ID id of the room, default set to 1
     * @param image the image of the room
     */
    public StartRoom(int ID, PImage image){
        super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
    }
    
}
