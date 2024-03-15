//Meadow Reiber
import java.util.Random;
public class GameLocations{
    //fields and properties----------------------
    private int batRoom;
    private int pitRoom;
    private int wumpusRoom;

    private int playerPos;
    private Player player;

    //constructor--------------------------------
    public GameLocations(Player player){
      this.startGame();
      this.player = player;
    }
    //methods------------------------------------
    public void startGame(){
      Random rnd = new Random();  
      this.batRoom = rnd.nextInt(30);
      this.batRoom = rnd.nextInt(30);
      this.batRoom = rnd.nextInt(30);
    }

    public void moveHazard(){
      
    }
    public void movePlayer(){}
    public String giveHint(){
        return "hint";
    }
  public String giveWarning(){
    if(this.playerPos == 4){}
    
    return "warning";
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
}