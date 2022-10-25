import java.util.ArrayList;

public class Player extends Character implements Moveable{
    private boolean hasKey;

    /**
     * Constructor for player object. The label should be "PLAYER" and not have a key by default.
     * @param currentRoom the room that the player should start in
     */
    public Player(Room currentRoom) throws IllegalArgumentException{
        super(currentRoom, "You find yourself in the entrance to a cave holding treasure.");
        this.hasKey=false;
        if(!currentRoom.getDescription().equals("You find yourself in the entrance to a cave holding treasure.")){
            throw new IllegalArgumentException("You can only start in a start room!");
        }
    }

    /**
     * Determines if the player has the key.
     * @return true if the player has the key, false otherwise
     */
    public boolean hasKey(){
        return this.hasKey;
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
     * Gives player the key.
     */
    public void obtainKey(){
        this.hasKey=true;
    }

    /**
	 * Checks whether or not this object can move to that room.
	 * @param destination, the Room to check if it can move to
	 * @return true if it can move there (a valid move), and false otherwise
	 */
    @Override
    public boolean canMoveTo(Room destination) {
        for(int i=0; i<this.getCurrentRoom().getAdjacentRooms().size(); i++){
            if(this.getCurrentRoom().getAdjacentRooms().get(i).equals(destination)){
                return true;
            }
        }
        return false;
    }

    /**
   * Determines whether or not the treasure room is in a nearby room.
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby()
  {
    ArrayList<Room> adjRooms = this.getAdjacentRooms();
    for(Room r: adjRooms) {
      if(r instanceof TreasureRoom)
        return true;
    }
    
    return false;      
  }

    /**
   * Determines whether or not a portal room is in a nearby room.
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby()
  {
    ArrayList<Room> adjRooms = this.getAdjacentRooms();
    for(Room r: adjRooms) {
      if(r instanceof PortalRoom)
        return true;
    }
    
    return false;      
  }


   /**
   * Determines whether or not the player needs to teleport.
   * Players teleport when their current room is of type PORTAL
   * @return true if they should teleport, false otherwise
   */
  public boolean teleport()
  {
    if(this.getCurrentRoom() instanceof PortalRoom)
    {
      return true; 
    }
    return false;
  }

  /**
   * Determines whether or not the given dragon is nearby. A dragon is considered nearby if it is in one of the adjacent rooms.
   * @param d  the dragon to check if nearby
   * @return true if the dragon is nearby, false otherwise
   */
  public boolean isDragonNearby(Dragon d){
    for(int i=0; i<this.getAdjacentRooms().size(); i++){
        if(this.getAdjacentRooms().get(i).equals(d.getCurrentRoom())){
            return true;
        }
    }
    return false;
  }
    
}
