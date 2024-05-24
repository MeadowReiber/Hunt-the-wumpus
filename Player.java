//Meadow Reiber
import java.util.Scanner;
public class Player {
  //fields and properties----------------------
  private int arrows;
  private int goldCoins;

  private int turns;

  private String name;
  private Scanner scanMan = new Scanner(System.in);
  
  //for filewriting purposes
  private int highScore;
  //constructor--------------------------------
  public Player(){
      this.name = scanMan.next();
  }
  //recreating player for file writing
  public Player(String name, int highScore){
    this.name = name;
    this.highScore = highScore;
  }
  //methods------------------------------------
  public int getArrows(){
    return this.arrows;
  }
  public int getCoins(){
    return this.goldCoins;
  }
  public void shootArrow(){
    this.arrows --;
  }
  public int getHighScore(){
    return this.highScore;
  }
  public String getName(){
    return this.name;
  }
  public void loseCoin(){
    this.goldCoins--;
  }
  public void addCoin(){
    this.goldCoins++;
  }
  public void addArrow(){
    this.arrows++;
  }

  public void takeTurn(){
    this.turns++;
  }
  public int calculateScore(boolean wumpusDead){
    int score = 100 - this.turns + this.goldCoins + (5 * this.arrows);
    if(wumpusDead){
      score += 50;
      return score;
    }
    return score;
  }
}
//maybe add a method to calculate high score
