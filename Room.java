/* Class to represent each room of the dungeon as an object*/
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/** @author Michelle
*/
public class Room {
  
  private String description; //verbal description of the room
private ArrayList<Room> adjRooms; //list of all rooms directly connect
private final int ID; // a "unique" identifier for each room
protected static PApplet processing; //PApplet object which the rooms will use to
//draw stuff to the GUI
private PImage image; //stores the image that corresponds to the background of a room

 
  /**
   * Constructor for the room object. Assigns values to the non-static fields. 
   * The default type should be RoomType.NORMAL.
   * @param id the unique id number for this room
   * @param roomDescription a brief description of this room
   * @param image the image that should be used as a background when drawing this Room.
   */
  public Room(int ID, String Description, processing.core.PImage image) {
    this.ID = ID;
    this.description = Description;
    this.adjRooms =  new ArrayList<Room>();
   
    this.image=image;
  }
  

  
  /**
   * Getter for the id instance field
   * @return this object's id
   */
  public int getID() {
    return this.ID;
  }
  
  /**
   * Getter for the adjRooms instance field
   * @return this object's list of rooms adjacent to it
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.adjRooms;
  }
  
  /**
   * Takes the given room and adds it to this object's list of adjacent rooms
   * @param toAdd room to be added to the adjacent rooms list
   */
  public void addToAdjacentRooms(Room toAdd) {
 
    this.adjRooms.add(toAdd);
  }
  
 


  

  
  /**
   * Checks whether this given room is adjacent to this one or not.
   * @param r The room that you are seeing if it is adjacent to this.
   * @return true if they are adjacent, false otherwise
   */
  public boolean isAdjacent(Room r)
  {
    return adjRooms.contains(r);
  }
  
  /**Checks whether or not this Room is equal to the other.*/
  @Override
  public boolean equals(Object other)
  {
    if(other instanceof Room) {
      Room otherRoom = (Room)other;
      return this.ID == otherRoom.ID;
    }
    
    return false;
  }
  
  /**
   * Returns a String representation of this Room.
   */
  @Override
  public String toString()
  {
    String s = this.ID +": " + this.description+ "\n Adjacent Rooms: ";
    for(int i = 0; i<adjRooms.size(); i++)
    {
      s+= adjRooms.get(i).ID +" ";
    }
    
    return s;
  } 
  
  /**
   * Sets the processing for the class.
   * @param processing the PApplet that this room will use to draw to the window
   */
  public static void setProcessing(PApplet processing){
    Room.processing=processing;
  }

  /**
   * Getter for description.
   * @return the verbal description of this Room
   */
  public String getDescription(){
    return this.description;
  }

  /**
   * Draws this Room to the window by drawing the background image, a rectangle, and some text.
   */
  public void draw(){
    processing.image(this.image, 0, 0);
  
    processing.fill(-7028);
    processing.rect(0, 500, 800, 600);
    processing.fill(0);
    processing.text(this.toString(), 300, 525);

  }
  
  
 
}
