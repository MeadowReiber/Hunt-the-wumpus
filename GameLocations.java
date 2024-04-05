//Meadow Reiber
import java.util.Random;
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
    private void encounterBats(){
      this.moveBat();
    }
    private void encounterPit(){
      this.movePit();
    }
    private void encouterWumpus(){
      this.moveWumpus();
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





  public void movePlayer(int newRoom){
    if(newRoom == this.batPos) this.encounterBats();
    this.playerPos = newRoom;
    }
  // hints/warnings (not done)
    public String giveHint(){
        return "hint";
    }
    public String giveWarning(){
    if(this.playerPos == 4){}
    
    return "warning";
    }
}