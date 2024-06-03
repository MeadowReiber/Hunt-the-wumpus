//import javax.swing.JFrame;
//import java.awt.FlowLayout;
//import javax.swing.JButton;
//import java.awt.event.*;


public class GameControl{
    Player player = new Player();
    Cave cave = new Cave();
    GameLocations loc = new GameLocations(player, cave);
    GUI display = new GUI("game", this);
    //public void displayScore(int room){
        //ask meadow which class holds the boolean for wumpus dead or alive?
        //int currentScore = player.calculateScore(shoot(room));
        //return currentScore;
        //called by ui --> player to get score --> ui
    //}
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
            //call casandra's method to show it was shot
            //shotWumpus
        }
        else{
            //call casandra's method to show not shot
            //missedShot
        }
    }
    public Boolean movePlayer(int roomNumber){
        if(cave.isConnected(loc.getPlayerPos(), roomNumber)){
            loc.movePlayer(roomNumber);
            int newLoc = loc.getPlayerPos();
            for(int x = 0; x < 6; x++){
                player.addCoin();
            }
            //call casandra's method to go to new room
            //call casandras method to display loc.giveWarnings();
            if(loc.encounterBats()){
                
            }
        }
        return true;
    }


    public void triviaArrow(){
        if (player.getCoins()>0){
            int triviaCorrect = 0;
            player.loseCoin();
            //start trivia for 3 questions (for loop)
            //if trivia question correct, triviaCorrect++
            //if triviaCorrect >= 2 -> player.addArrow();
            //use casandras display to show player.getCoins();
            //use casandras display to show player.getArrows();
            //displayInventory
        }
    }

    public void triviaHint(){
        if (player.getCoins()>0){
            int triviaCorrect = 0;
            player.loseCoin();
            //start trivia for 3 questions (for loop)
            //if trivia question correct, triviaCorrect++
            //if triviaCorrect >= 2 -> loc.giveHint()
            //use casandras display to show player.getCoins()
            //announcement
        }
    }

    public void displayInventory(){
        display.displayInventory(player);
        //called by ui --> player to get inventory --> ui
    }

    




}
