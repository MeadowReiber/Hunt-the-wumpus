//import javax.swing.JFrame;
//import java.awt.FlowLayout;
//import javax.swing.JButton;
//import java.awt.event.*;

//talk abt on tues
//new methods i need meadow to add + alter methods
//new stuff i found from reading the game spec
//ask how the trivia works (how should i call to display it and how do ik if they got a correct answer)

import java.util.ArrayList;

public class GameControl{
    Player player = new Player();
    Cave cave = new Cave();
    GameLocations loc = new GameLocations(player, cave);
    GUI display = new GUI("game");
    //public void displayScore(int room){
        //ask meadow which class holds the boolean for wumpus dead or alive?
        //int currentScore = player.calculateScore(shoot(room));
        //return currentScore;
        //called by ui --> player to get score --> ui
    //}
    public void shoot(int room){
        Boolean shot;
        if(player.getArrows() > 0){
            shot = loc.shootArrow(room);
        }
        else{
            shot =  false;
        }

        if(shot == true){
            display.shootButton(player);
            //displays shot + wumpus dead so game ends
        }
        else{
            if(player.getArrows() <= 0){
                display.showMessage("oh no... the wumpus eats the player");
                display.gameOver();
            }
        }
    }public GUI getGUI(){
        return this.display;
    }

    public Boolean movePlayer(int roomNumber){
        display.showMessage(""+roomNumber);
        if(cave.isConnected(loc.getPlayerPos(), roomNumber)){
            loc.movePlayer(roomNumber);
            int newLoc = loc.getPlayerPos();
            player.addCoin();
            //display newLoc
            //call casandras method to display loc.giveWarnings();
            ArrayList<String> warnings = loc.giveWarnings();
            String warn = "";
            if(warnings.size() > 1){
                for(int i = 0; i < warnings.size(); i++){
                    warn = warn + " " + warnings.get(i);
                }
            }
            display.showMessage(warn);
            check();
            
        }
        return true;
    }

    public void check(){
        if(loc.encounterBats()){
            int newLoc = player.getPlayerPos();
            //display player.getPlayerPos();
        }
        if(loc.encounterPit()){
            if(player.getCoins() > 0){
                if(trivia()){
                    display.showMessage("you have avoided the pit... you are being sent to the start");
                    //display player.getPlayerPos();
                }
                else{
                    display.gameOver();
                }
            }
            //if won, player pos set to 0 (need meadow to make method + change encounterPit)
            //use cas method to show new room (player.getPlayerPos)
            //if trivia lost, game end (call method to display end game)
            else{
                display.gameOver();
              
            }
            //if coins are less than 0, end game
        }
        if(loc.encouterWumpus()){
            if(player.getCoins() > 0){
                if(trivia()){
                    display.showMessage("wumpus has been wounded... wumpus moved rooms");
                    //display player.getPlayerPosition();
                }
                else{
                    display.gameOver();
                }
            }
            //if coins are more than 0
            //run trivia (5 times, 3 correct) and see if won
            //if won, wumpus moves rooms (need meadow to make method + change encounter wumpus)
            //use cas method to say "wumpus has been wounded... wumpus moved rooms"
            //if trivia lost, game end (call method to display end game)
            else{
                display.gameOver();
            }
            //if coins are less than 0, end game
        }
    }


    public void triviaArrow(){
        if (player.getCoins()>0){
            int triviaCorrect = 0;
            player.loseCoin();
            if(trivia()){
                player.addArrow();
                display.showMessage("your arrow inventory increased!");
            }
            display.displayInventory(this.player);
        }
        else{
            display.showMessage("you dont have enough coins");
            //display "you dont have enough coins!"
        }
    }

    public void triviaHint(){
        if (player.getCoins()>0){
            int triviaCorrect = 0;
            player.loseCoin();
            if(trivia()){
                String hint = loc.giveHint();
                display.showMessage(hint);
            }
            display.displayInventory(player);
        }
        else{
            display.showMessage("you don't have enough coins!");
            //display you dont have enough coins
        }
    }

    public boolean trivia(){
        //runs trivia gui
        //counts how many they get correct
        //return true if 2/3 trivia correct
        TriviaGUI triviaGUI = new TriviaGUI("trivia_question.txt", this);
        triviaGUI.setVisible(true);
        return true;

    }

    public void displayInventory(){
        display.displayInventory(player);
        //called by ui --> player to get inventory --> ui
    }

    




}
