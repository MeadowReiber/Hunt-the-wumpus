//Meadow Reiber
public class Player {
  //fields and properties----------------------
  private int arrows;
  private int goldCoins;

  private int turns;

  //constructor--------------------------------
  public Player(){
      
  }
  //methods------------------------------------
  public void takeTurn(){
    this.turns++;
  }
  public int getArrowInvetory(){
    return this.arrows;
  }
  public void shootArrow(){
    this.arrows -=;
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
