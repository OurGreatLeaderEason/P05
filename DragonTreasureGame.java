//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Dragon Treasure Game 2.0
// Course:   CS 300 Fall 2022
//
// Author:   Eason Xiao
// Email:    xiao227@wisc.edu
// Lecturer: Jeff Nyhoff
//
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * The class that emulates the game logic
 */
public class DragonTreasureGame extends PApplet{
    private ArrayList<Room> roomList;
    File mapInfo;
    File roomInfo;
    private ArrayList<Character> characters;
    private boolean isDragonTurn;
    private int gameState;
    public static void main(String[] args){
       
        PApplet.main("DragonTreasureGame");
    }

    /**
     * Initializes that size of the frame
     */
    @Override   
    public void settings(){
        size(800,600);
    }

    /**
     * Sets up the GUI and game elements
     */
    @Override
    public void setup(){
        
        this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
        this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
        //as the top-left corner
        this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
        //as top-left corner and bottom-right corner respectively
        this.focused = true; // window will be active upon running program
        this.textAlign(CENTER); // sets the text alignment to center
        this.textSize(20); //sets the font size for the text
        Room.setProcessing(this);
       
       
        
        this.mapInfo=new File("map.txt");
        this.roomInfo=new File("roominfo.txt");
        this.roomList=new ArrayList<Room>();
        TreasureRoom.setTreasureBackground(this.loadImage("images/treasure.jpg"));
        PortalRoom.setPortalImage(this.loadImage("images/portal.png"));
        characters=new ArrayList<Character>();
        
        loadRoomInfo();
        loadMap();
        this.loadCharacters();
       
        this.isDragonTurn=false;
        this.gameState=0;
      
        
        
     
    }

    /**
     * Continuously run to draw elements onto the GUI
     */
    public void draw(){
        System.out.println("Dragon: "+this.characters.get(2).getCurrentRoom().getID());
        System.out.println("Player: "+this.characters.get(1).getCurrentRoom().getID());
        this.characters.get(1).getCurrentRoom().draw();
         if(((Player) this.characters.get(1)).hasKey() && this.characters.get(1).getCurrentRoom() instanceof TreasureRoom){
            this.gameState=1;
            this.text("You won!", 300, 585);
        }
        if(this.characters.get(1).getCurrentRoom().equals(this.characters.get(2).getCurrentRoom())){
            this.gameState=2;
            this.text(Dragon.getDragonEncounter(), 200, 485);

        }
        if(this.gameState==0){

        if(((Player) characters.get(1)).isTreasureNearby()){
            this.text(TreasureRoom.getTreasureWarning(), 100, 585);
        }
        if(((Player) this.characters.get(1)).isDragonNearby((Dragon) this.characters.get(  2))){
            this.text(Dragon.getDragonWarning(), 750, 585);
           
        }
        if(this.characters.get(1).getCurrentRoom().equals(this.characters.get(0).getCurrentRoom())){
            ((Player) this.characters.get(1)).obtainKey();
            this.text("KEY OBTAINED", 300, 585);
        }
        if(((Player) this.characters.get(1)).teleport()){
            ((Player) this.characters.get(1)).changeRoom(((PortalRoom) this.characters.get(1).getCurrentRoom()).getTeleportLocation());
            this.text(PortalRoom.getTeleportMessage(), 500, 585);
        }
        if(this.isDragonTurn){
            ((Dragon) this.characters.get(2)).changeRoom(((Dragon) this.characters.get(2)).pickRoom());
            this.isDragonTurn=false;
        }
    }
        /* 
        if(((Player) this.characters.get(1)).hasKey() && this.characters.get(1).getCurrentRoom() instanceof TreasureRoom){
            this.gameState=1;
            this.text("You won!", 300, 585);
        */
        /* 
        if(this.characters.get(1).getCurrentRoom().equals(this.characters.get(2).getCurrentRoom())){
            this.gameState=2;
            this.text(Dragon.getDragonEncounter(), 200, 485);

        }}
        */
        
    }
        
        
        
    
     /** Loads in room info using the file stored in roomInfo
   *  @author Michelle 
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
   
    try {
      
      //scanner to read from file
      fileReader= new Scanner(roomInfo);
      
      //read line by line until none left
      while(fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();
        
        //parse info and create new room 
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); //get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;
        
        if(parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);
         
        }
        
        if(parts.length == 4) {
          description = parts[3].trim(); //get the room description
        }
   
        switch(parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            
            newRoom = new PortalRoom(ID, description, image);
            
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            
            break;
          default:
            break;
        }  
        
        if(newRoom != null) {
          roomList.add(newRoom);
        }
      }
     
    }catch(IOException e) { //handle checked exception
      e.printStackTrace();
    }finally {
      if(fileReader != null)
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
    }
  }
  
  /** Loads in room connections using the file stored in mapInfo
   *  @author Michelle 
   */
  private void loadMap() {
    
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
          //scanner to read from file
          fileReader= new Scanner(mapInfo);
          
        //read line by line until none left
          while(fileReader.hasNext()) {
            
            //parse info
            String nextLine = fileReader.nextLine();
            String parts[] = nextLine.split(" ");
            int id = Integer.parseInt(parts[0]);
            
            Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms
            
            //add all the rooms to the adj room list of toEdit
            for(int i=1; i<parts.length; i++) {
              Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
              toEdit.addToAdjacentRooms(toAdjAdd);
            }
          }
        }catch(IOException e) { //handle checked exception
          e.printStackTrace();
        }finally {    //close scanner regardless of what happened for security reasons :)
          if(fileReader != null)
            fileReader.close();
        }
  }
  
  /**
   * Get the room objected associated with the given ID.
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id){
    int indexToEdit = roomList.indexOf(new Room(id,"dummy", null));
    Room toEdit = roomList.get(indexToEdit); 
    return toEdit;
  }

  /**
   * Helper methods that loads characters
   */
  private void loadCharacters() {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5),"KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
    }

  /**
   * Method runs when key pressed
   */
  @Override
  public void keyPressed(){
     
    if (this.gameState==0){
        if(((Player) this.characters.get(1)).canMoveTo(this.roomList.get(Integer.parseInt(String.valueOf(key))-1))){
            ((Player) this.characters.get(1)).changeRoom(this.roomList.get(Integer.parseInt(String.valueOf(key))-1));
            this.isDragonTurn=true;
            
        }
        else{
            System.out.println("Pick a valid room!");
        }
    }
    
  
   
    
  }

  
}
