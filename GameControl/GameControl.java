package GameControl;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.*;
public class GameControl{
    Player player = new Player();
    Cave cave = new Cave();
    GameLocations loc = new GameLocations(player, cave);
    public int displayScore(){
        //ask meadow which class holds the boolean for wumpus dead or alive?
        int currentScore = player.calculateScore();
        return currentScore;
        //called by ui --> player to get score --> ui
    }
    public Boolean shoot(int room){
        if(player.getArrows() > 0){
            //checks if there r arrows
            loc.shootArrow(room);
            //shootarrow will return true for shot, false for not shot
        }
        else{
            return false;
            //if no arrows, then did not shoot
        }
    }
    public Boolean moveForward(int roomNumber){
        //called by ui --> cave checks for open rooms --> return if can move
        return true;
    }
    public void buyArrow(){
        //called by ui --> trivia --> player to update inventory
    }
    public void displayInventory(){
        //called by ui --> player to get inventory --> ui
    }
    public void displayActions(){
        //called by ui --> player to display actions possible --> ui
    }


}
