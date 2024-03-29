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
    public void movePit(){
      Random rnd = new Random();
      this.pitPos = rnd.nextInt(30);
    }
    public void moveBat(){
      Random rnd = new Random();  
      this.batPos = rnd.nextInt(30);
    }
    public void moveWumpus(){
       Random rnd = new Random();
       this.wumpusPos = rnd.nextInt(30);
    }
    
    
    
    public void movePlayer(int direction){
      // this.playerPos = this.map.getRoom(this.playerPos, direction);
      // returns the room in the direction:   original room, direction of new room
    }
    public boolean shootArrow(int direction){
    if(this.player.getArrows() > 0){
      this.player.shootArrow();
      // method from cave that returns the room in the correct direction
      //if(/*the room*/ == this.wumpusRoom) return true;
    }
    else System.out.println("out of arrows");
    return false;
  }

    public String giveHint(){
        return "hint";
    }
    public String giveWarning(){
    if(this.playerPos == 4){}
      
    return "warning";
    }
    //intearcting with hazards
}