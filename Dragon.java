import java.util.ArrayList;
import java.util.Random;

public class Dragon extends Character implements Moveable{
    private Random randGen; //random num generator used for moving
    private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
    private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

     /**
   * Constructor for a dragon object. Assigns values to all non-static fields.
   * @param currentRoom the current location of the dragon
   */
  public Dragon(Room currentRoom) throws IllegalArgumentException{
    super(currentRoom, "DRAGON");
    this.randGen = new Random();
    if(currentRoom instanceof TreasureRoom==false){
        throw new IllegalArgumentException("Dragon must start in treasure room!");
    }
  }

  

   /**
   * Returns the string that is the dragon class's warning,
   * indicating that there is one nearby.
   * @return The dragon warning message string
   */
  public static String getDragonWarning()
  {
    return DRAGON_WARNING;
  }

   /**
   * Returns the string that is the dragon class's warning,
   * indicating that there is one nearby.
   * @return The dragon warning message string
   */
  public static String getDragonEncounter()
  {
    return DRAGON_ENCOUNTER;
  }

  /**
   * Checks if the dragon can move to the given destination. A valid move is the destination not a PortalRoom.
   * @return the room that this Dragon should try to move into
   */
  public Room pickRoom(){
    Room newRoom=this.getAdjacentRooms().get(randGen.nextInt(this.getAdjacentRooms().size()));
    if(newRoom instanceof PortalRoom){
        this.pickRoom();
    }
    return newRoom;
    
    
    
    
    
  }


	/**
	 * Changes the room where the object is.
	 * @param destination, the Room to change it to
	 * @return true if the change is successful (a valid move), and false otherwise
	 */
@Override
public boolean changeRoom(Room destination) {
    if(canMoveTo(destination)){
        this.setCurrentRoom(destination);
        return true;
    }
    return false;
}

/**
	 * Checks whether or not this object can move to that room.
	 * @param destination, the Room to check if it can move to
	 * @return true if it can move there (a valid move), and false otherwise
	 */
@Override
public boolean canMoveTo(Room destination) {
    for(int i=0; i<this.getCurrentRoom().getAdjacentRooms().size(); i++){
        if(this.getCurrentRoom().getAdjacentRooms().get(i).equals(destination) && this.getCurrentRoom().getAdjacentRooms().get(i) instanceof PortalRoom==false){
            return true;
        }
    }
    return true;
    
}


  


}
