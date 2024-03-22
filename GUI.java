
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class GUI extends JFrame{
    public GUI(String title){
    System.out.println(title);

    setTitle(title);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setSize(640, 480);
    setLayout(new FlowLayout());

    // custom code
    GraphicalInterface a = new GraphicalInterface();
    GraphicalInterface b = new GraphicalInterface();
    add( new GraphicalInterface() );
    add( new GraphicalInterface() );
    add(b);
    add(a);

    // boiler plate
    setResizable(true);
    setLocationRelativeTo(null);
    setVisible(true);
    
  }
}

