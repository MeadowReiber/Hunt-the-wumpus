//import javax.swing.JFrame;
//import java.awt.FlowLayout;
//import javax.swing.JButton;
//import java.awt.event.*;

//talk abt on tues
//new methods i need meadow to add + alter methods
//new stuff i found from reading the game spec
//ask how the trivia works (how should i call to display it and how do ik if they got a correct answer)

public class GameControl{
    Player player = new Player();
    Cave cave = new Cave();
    GameLocations loc = new GameLocations(player, cave);
    GUI display = new GUI("game", this);
    Trivia trivia = new Trivia();
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
            display.shootButton(player);
            //displays shot + wumpus dead so game ends
        }
        else{
            if(player.getArrows() <= 0){
                //displays wumpus eats player
                //display game end
            }
        }
    }
    public Boolean movePlayer(int roomNumber){
        if(cave.isConnected(loc.getPlayerPos(), roomNumber)){
            loc.movePlayer(roomNumber);
            int newLoc = loc.getPlayerPos();
            player.addCoin();
            //call casandra's method to go to new room
            //call casandras method to display loc.giveWarnings();
            check();
            
        }
        return true;
    }

    public void check(){
        if(loc.encounterBats()){
            newLoc = player.getPlayerPos();
            //call cas method to go to new room
        }
        if(loc.encounterPit()){
            //if coins are more than 0
            //run trivia to see if won (same as triviahint)
            //if won, player pos set to 0 (need meadow to make method + change encounterPit)
            //use cas method to show new room
            //if trivia lost, game end (call method to display end game)

            //if coins are less than 0, end game
        }
        if(loc.encounterWumpus()){
            //if coins are more than 0
            //run trivia (5 times, 3 correct) and see if won
            //if won, wumpus moves rooms (need meadow to make method + change encounter wumpus)
            //use cas method to say "wumpus has been wounded... wumpus moved rooms"
            //if trivia lost, game end (call method to display end game)

            //if coins are less than 0, end game
        }
    }


    public void triviaArrow(){
        if (player.getCoins()>0){
            int triviaCorrect = 0;
            player.loseCoin();
            //start trivia for 3 questions (for loop)
            //ask how to start trivia (?) would gui have it?
            //if trivia question correct (ask where is trivia correct method), triviaCorrect++
            //if triviaCorrect >= 2 -> player.addArrow();
            //use casandras display to show player.getCoins();
            //use casandras display to show player.getArrows();
            //displayInventory
        }
        else{
            //display "you dont have enough coins!"
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
        else{
            //display you dont have enough coins
        }
    }

    public void displayInventory(){
        display.displayInventory(player);
        //called by ui --> player to get inventory --> ui
    }

    




}
