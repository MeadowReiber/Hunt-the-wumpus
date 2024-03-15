//Meadow Reiber
import java.util.Scanner;
public class Player {
  //fields and properties----------------------
  private int arrows;
  private int goldCoins;

  private int turns;

  private String name;
  private Scanner scanMan = new Scanner(System.in);
  //constructor--------------------------------
  public Player(){
      this.name = scanMan.next();
  }
  //methods------------------------------------
  public int getArrows(){
    return this.arrows;
  }
  public void shootArrow(){
    this.arrows --;
  }
  public String getName(){
    return this.name;
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
