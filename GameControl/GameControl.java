package GameControl;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.*;
public class GameControl{
    
    public Boolean moveForward(int roomNumber){
        //called by ui --> cave checks for open rooms --> return if can move
        return true;
    }
    public void buyArrow(){
        //called by ui --> trivia --> player to update inventory
    }
    public void displayScore(){
        //called by ui --> player to get score --> ui
    }
    public void displayInventory(){
        //called by ui --> player to get inventory --> ui
    }
    public void displayActions(){
        //called by ui --> player to display actions possible --> ui
    }


}