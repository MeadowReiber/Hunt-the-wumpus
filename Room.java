import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;

public class Room extends HexagonButton implements ActionListener{
    // properties
    private int roomNumber;
    private GameControl gameControl; 
    private GUI gui;

    // constructer
    public Room(){
        super("Button");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        gameControl.movePlayer(2);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
