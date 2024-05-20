//Meadow Reiber
import java.util.Random;
import java.util.ArrayList;
public class GameLocations{
    //fields and properties----------------------
    private Cave map;
    
    private int batPos = 0;
    private int pitPos = 0;
    private int wumpusPos = 0;

    private int playerPos = 0;
    private Player player;

    //constructor--------------------------------
    public GameLocations(Player player, Cave theCave){
      this.playerPos = this.newRoom();
      this.moveBat();
      this.movePit();
      this.moveWumpus();
    }


    //methods------------------------------------
    //moves the hazards to random romo determined by new room
    private void movePit(){
      this.batPos = this.newRoom();
    }
    private void moveBat(){
      this.batPos = this.newRoom();
    }
    private void moveWumpus(){
      ArrayList<Integer> newRooms = map.getWumpusRooms(this.wumpusPos);
      int possible = newRooms.size();
      this.wumpusPos = newRooms.get((int)(Math.random() * possible) +1);
    }
    //returns the int of a random room that does not overlap positions with anything
    private int newRoom(){
      int pos = (int)(Math.random()*30) + 1;
      while(pos == this.batPos || pos == this.pitPos || pos == this.wumpusPos || pos == this.playerPos){
        pos = (int)(Math.random()*30) + 1;
      }
      return pos;
    }

    //returns true if an encounter happens, and moves the correct things
    public boolean encounterBats(){
      if(this.playerPos == this.batPos){
        this.playerPos = newRoom();
        this.moveBat();
        return true;
      }
      return false;
    }
    public boolean encounterPit(){
      if(this.playerPos == this.pitPos){
        this.playerPos = newRoom();
        this.movePit();
        return true;
      }
      return false;
    }
    public boolean encouterWumpus(){
      if(this.playerPos == this.wumpusPos){
        this.moveWumpus();
        return true;
      }
      return false;
    }
  
    //precondition: player has arrows. returns true if the wumpus is shot
    public boolean shootArrow(int shotRoom){
      this.player.shootArrow();
      if(shotRoom == this.wumpusPos){
        return true;
      }
      else return false;    
    }




  //precondition: newRoom is a valid move
  public void movePlayer(int newRoom){
    this.playerPos = newRoom;
  }

    
  // hints/warnings (not done)
    public String giveHint(){
        return "hint";
    }
    public ArrayList<String> giveWarning(){
      ArrayList<Integer> rooms = map.getAdjacentRooms(this.playerPos);
      ArrayList<String> warnings = new ArrayList<String>();
      for(int adjacent : rooms){
        if(adjacent == this.batPos){
          warnings.add("I hear flapping");
        }
        if(adjacent == this.pitPos){
          warnings.add("I feel a breeze");
        }
        if(adjacent == this.wumpusPos){
          warnings.add("I smell a wumpus");
        }
      }
    
      return warnings;
    }
}