//Meadow Reiber
import java.util.Random;
import java.util.ArrayList;
public class GameLocations{
    //fields and properties----------------------
    private Cave map;
    
    private int batPos;
    private int pitPos;
    private int wumpusPos;

    private int playerPos;
    private Player player;

    //constructor--------------------------------
    public GameLocations(Player player, Cave theCave){
      this.startGame();
      this.player = player;
      this.map = theCave;
    }
    //methods------------------------------------
    //giving the hazards new/original locations
    private void startGame(){
      Random rnd = new Random();  
      this.batPos = rnd.nextInt(30);
      this.pitPos = rnd.nextInt(30);
      this.wumpusPos = rnd.nextInt(30);

      //set the player to a position
      this.playerPos = 0;
    }
    private void movePit(){
      Random rnd = new Random();
      this.pitPos = rnd.nextInt(30);
    }
    private void moveBat(){
      Random rnd = new Random();  
      this.batPos = rnd.nextInt(30);
    }
    private void moveWumpus(){
       Random rnd = new Random();
       this.wumpusPos = rnd.nextInt(30); 
    }
    
    
    public boolean encounterBats(){
      if(this.playerPos == this.batPos){
        this.playerPos = 0;
        this.moveBat();
        return true;
      }
      return false;
    }
    public boolean encounterPit(){
      this.movePit();
      return false;
    }
    public boolean encouterWumpus(){
      this.moveWumpus();
      return false;
    }
  

    public boolean shootArrow(int shotRoom){
      if(this.player.getArrows() > 0){
        this.player.shootArrow();
        if(shotRoom == this.wumpusPos){
          return true;
        }
        else return false;
      } 
      else System.out.println("out of arrows");
      return false;
  }




  //precondition: newRoom is a valid move
  public void movePlayer(int newRoom){
    if(newRoom == this.batPos) this.encounterBats();
    else if(newRoom == this.pitPos) this.encounterPit();
    else if(newRoom == this.wumpusPos){
      this.encouterWumpus();
    };
    this.playerPos = newRoom;
  }

    
  // hints/warnings (not done)
    public String giveHint(){
        return "hint";
    }
    public ArrayList<String> giveWarning(){
      ArrayList<int> rooms = map.getAdjacentRooms(this.playerPos);
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