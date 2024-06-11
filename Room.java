import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.Arrays;

public class Room extends HexagonButton implements ActionListener{

  private GameControl gc;
  private boolean[] walls;
  //private Hazard h;
  private Player p;

  public Room(){
    super("HexButton");
    this.addActionListener(this);

  }

  public Room setController(GameControl controller){  
    this.gc = controller;
    return this;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(gc);
    //gc.move(this);
  }

}