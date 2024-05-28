//Meadow Reiber
import java.util.ArrayList;
public class GameLocations{
    //fields and properties----------------------
    private ArrayList<String> hints;
    private int coinsLeft;

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
      this.wumpusPos = this.newRoom();
      this.coinsLeft = 100;

      this.hints = new ArrayList<String>();
      this.hints.add("look at the beautiful BLUE sky");
      this.hints.add("visiting the capital city: OLYMPIA");
      this.hints.add("RED roses are my favorite");
      this.hints.add("TOUCHDOWN!!! another 6 points");
      this.hints.add("people with AB- blood are my favorite");
      this.hints.add("I'm so excited to visit London for the olympics (2012)");
    }


    //methods------------------------------------
    public int getPlayerPos(){
      return this.playerPos;
    }
    
    //moves the hazards to random room determined by new room
    private void movePit(){
      this.batPos = this.newRoom();
    }
    private void moveBat(){
      this.batPos = this.newRoom();
    }
    private void moveWumpus(int roomsLeft){
      if(roomsLeft > 0){
        ArrayList<Integer> adjacentRooms = map.getAdjacentRooms(this.wumpusPos);
        for(int i = 0; i < adjacentRooms.size(); i++){
          if(map.isConnected(i, this.wumpusPos)){
            this.wumpusPos = i;
            moveWumpus(roomsLeft-1);
          }
        }
      }
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
        int roomsAway = (int)(Math.random()*3) + 2;
        this.moveWumpus(roomsAway);
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

    //precondition: newRoom is a valid move. moves the player to the new room and adds coin
    public void movePlayer(int newRoom){
      this.playerPos = newRoom;
      if(coinsLeft > 0){
        this.player.addCoin();
        this.coinsLeft--;
      }
    }

    //returns a String that is the seceret/hint
    public String giveHint(){
      int hint = (int)(Math.random()*this.hints.size()) + 4;
      ArrayList<String> secerets = this.hints;
      secerets.add("You are in room number " + this.playerPos);
      secerets.add("The bats are in room number " + this.batPos);
      secerets.add("The pit is in room number " + this.pitPos);
      if(hint < this.hints.size()){
        this.hints.remove(hint);
      }
      
      return secerets.get(hint);
    }
    //to be called when the player moves and returns the warnings about surrounding hazards
    public ArrayList<String> giveWarnings(){
      ArrayList<Integer> rooms = map.getAdjacentRooms(this.playerPos);
      ArrayList<String> warnings = new ArrayList<String>();
      for(int adjacent : rooms){
        if(adjacent == this.batPos){
          warnings.add("You hear flapping");
        }
        if(adjacent == this.pitPos){
          warnings.add("You feel a breeze");
        }
        if(adjacent == this.wumpusPos){
          warnings.add("You smell a wumpus");
        }
      }
    
      return warnings;
    }
}