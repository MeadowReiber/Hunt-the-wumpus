//Meadow Reiber
public class GameLocations{
    //fields and properties----------------------
    private int batCave;
    private int pitCave;
    private int wumpusCave;

    private int playerPos;

    //constructor--------------------------------
    public GameLocations(){
        this.startGame();
    }
    //methods------------------------------------
    public void startGame(){
        this.batCave = 2;
    }
    public void moveHazard(){
      
    }
    public void movePlayer(){}
    public String giveHint(){
        return "hint";
    }
}