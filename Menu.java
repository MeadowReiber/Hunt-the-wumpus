import javax.swing.JButton;

public class Menu extends JPanel{
    private JButton quitGame;
    private JButton x;
    private JLabel Menu;
    private GameControl gameControl;
    
    public Menu(GameControl gc){
        this.quitGame = new JButton("Quit game");
        this.x = new JButton("X");
        this.menu = new JLabel("Menu");
        this.gameControl = gc;

        quitGame.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                gameControl.quitGame();
            }
        });
        x.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                gameControl.getGUI().remove(this);
            }
        });

    }
}
