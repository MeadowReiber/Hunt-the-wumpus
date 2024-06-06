import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    private JButton quitGame;
    private JButton x;
    private JLabel menu;
    private GameControl gameControl;
    
    public Menu(GameControl gc){
        this.quitGame = new JButton("Quit game");
        this.x = new JButton("X");
        this.menu = new JLabel("Menu",0);
        this.gameControl = gc;

        /* *
        quitGame.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                gameControl.quitGame();
            }
        });*/
        // TODO: ActionListner is an Interface, not a full class
        // You need a class that implements it.
        /*
        x.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //gameControl.getGUI().remove(this);
                System.out.println("TODO: THIS DOES NOTHING");
            }
        });
         */

    }
}
