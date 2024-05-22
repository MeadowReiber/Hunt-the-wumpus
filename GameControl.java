//import javax.swing.JFrame;
//import java.awt.FlowLayout;
//import javax.swing.JButton;
//import java.awt.event.*;


public class GameControl{
    Player player = new Player();
    Cave cave = new Cave();
    GameLocations loc = new GameLocations(player, cave);
    GUI display = new GUI("game");
    public void displayScore(int room){
        //ask meadow which class holds the boolean for wumpus dead or alive?
        //int currentScore = player.calculateScore(shoot(room));
        //return currentScore;
        //called by ui --> player to get score --> ui
    }
    public void shoot(int room){
        Boolean shot;
        if(player.getArrows() > 0){
            //checks if there r arrows
            shot = loc.shootArrow(room);
            //shootarrow will return true for shot, false for not shot
        }
        else{
            shot =  false;
            //if no arrows, then did not shoot
        }

        if(shot == true){
            //call casandra's method
        }
        else{
            //call casasndra's method
        }
    }
    public Boolean moveForward(int roomNumber){
        //called by ui --> cave checks for open rooms --> return if can move
        return true;
    }
    public void buyArrow(){
        int currentCoins = player.getCoins();
        int currentArrows = player.getArrows();
        if (currentCoins > 0){
            currentCoins--;
            currentArrows = currentArrows+1;
            //call casandras method to display coins
            //call casandras method to display arrows
        }
        //called by ui --> trivia --> player to update inventory
    }
    public void displayInventory(){
        GUI.displayInventory(player);
        //called by ui --> player to get inventory --> ui
    }
    public void displayActions(){
        //called by ui --> player to display actions possible --> ui
    }


}
