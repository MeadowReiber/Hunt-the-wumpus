//Casandra Reyes

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class GraphicalInterface extends JButton implements ActionListener{
    ////////////
    //Properties
    ////////////
    private JButton button;
    ////////////
    //Constructor
    ////////////
    public GraphicalInterface(){
        super("testing button");
    }

    ////////////
    //Methods
    ////////////
  @Override
  public void actionPerformed(ActionEvent e){
    //other.setText("?");
    button.setText(button.getText() + "chat");
    System.out.println( "chat" );
  }
    public void displayRoom(){
        //displays the hexagonal room in the form of 6 buttons with exits
    }
    public int displayScore(){
        //called by game control?
        return 8; //not sure if it is an int or double
    }
    public void displayInventory(){
        //calls player
        //returns an array?
    }
    public String displayHints(){
        return "I smell a wumpus";
    }
    public void displayActions(){
        //calls player?
        //shows actions player can take
    }
}


