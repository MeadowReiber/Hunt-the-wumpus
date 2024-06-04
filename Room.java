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

public class Room extends JPanel{
    private JButton northwest;
    private JButton north;
    private JButton northeast;
    private JButton southwest;
    private JButton south;
    private JButton southeast;

    public Room(){
        this.northwest = new JButton("northwest");
        this.north = new JButton("north");
        this.northeast = new JButton("northeast");
        this.southwest = new JButton("southwest");
        this.south = new JButton("south");
        this.southeast = new JButton("southeast");

        initializePanel();
        displayRoom();
    }

    public void initializePanel(){
        setLayout(new GridLayout(2, 3));
        setSize(300, 280);
        setVisible(true);
    }

    public void displayRoom(){
        add(northwest);
        add(north);
        add(northeast);
        add(southwest);
        add(south);
        add(southeast);
    }
    public static void main(String[] args){
        System.out.println("this is my file");
        Room room = new Room();
    }
}
