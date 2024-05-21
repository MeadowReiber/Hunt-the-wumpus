// Casandra Reyes

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame{

    private JLabel score;
    private JLabel arrows;
    private GridBagConstraints gbc;
    private Font mainFont;
    private GameControl gc;
    private Player player;

    // Load the icon, scaled to the given size
    private static ImageIcon loadIcon(String iconFilePath, int width, int height) {
        ImageIcon origIcon = new ImageIcon(iconFilePath);
        Image img = origIcon.getImage();  
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }

    public GUI(String title){
    System.out.println(title);
    mainFont = new Font("SansSerif", 5, 20);
    this.gc = new GameControl();

    setTitle(title);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setSize(640, 480);

    setLayout(new GridBagLayout());
    this.gbc = new GridBagConstraints();
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;

    setResizable(true);
    setLocationRelativeTo(null);
    setVisible(true);

    //setMargins(new Insets(0,0,0,0));

    startScreen();
    
  }

    public void displayRoom(){
        ArrayList<String> imagePaths= new ArrayList<String>();
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "1.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "2.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "3.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "4.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "5.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "6.png");
        
        int imagecount = 0;
        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 3; x++){

                JButton button = new JButton();
                this.gbc.gridx = x;
                this.gbc.gridy = y;
        
                String imagePath = System.getProperty("user.dir") + "\\images\\" + "forestbackground.png";
                System.out.println("Image path: " + imagePath);

                button.setIcon(loadIcon(imagePaths.get(imagecount), 200, 200));
                button.setSize(200,200);
            
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        //g.walk(y, x);
                        ////// gameControl class add this walk method, x and y are the direction where the player is moving 
                        System.out.println("BUTTONCLICKED");
                    }
                });   

                this.getContentPane().add(button,gbc);
                imagecount++;
            }
        }
        this.getContentPane().invalidate();
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }


    public void displayButtons(){
        JButton inventory = new JButton("Inventory");
        inventory.setSize(300,300);
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //start game
                System.out.println("inventory opened");
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(inventory,gbc);

        JButton hints = new JButton("Get Hint");
        hints.setSize(300,300);
        hints.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //start game
                System.out.println("get a hint");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(hints,gbc);

        JButton takeAction = new JButton("Take Action");
        takeAction.setSize(300,300);
        takeAction.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //start game
                System.out.println("get a hint");
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(takeAction,gbc);
        

        
    }

    public void displayInventory(){
        int[] items = this.gc.getInventory();
    }


    public void startScreen(){

        JLabel htw = new JLabel("HUNT THE WUMPUS");
        Font font = new Font("SansSerif", 5, 50);
 
        htw.setFont(font);

        JButton start = new JButton("START");
        start.setSize(600,100);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //start game
                System.out.println("Game has started.");
                displayRoom();
                displayButtons();
                remove(start);
                remove(htw);
                revalidate();
                repaint();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(htw, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(start, gbc);

    }

    public int displayScore(){
        //called by game control?
        return 8; //not sure if it is an int or double
    }
    public void updateInventory(int arrows){
        //called by game control
        JLabel numArrows = new JLabel("Arrows: " + arrows);
        numArrows.setFont(mainFont);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(numArrows, gbc);
    }
    public String displayHints(){
        return "I smell a wumpus";
    }
    public JButton shootButton(Player p){
        JButton shoot = new JButton("SHOOT ARROW");
        shoot.setSize(600,100);
        shoot.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //start game
                p.shootArrow();
                updateInventory(p.getArrows());
            }
        });
        gbc.gridx = 4;
        gbc.gridy = 5;
        return shoot;
    }
    public void gameOver(){
        System.out.println("GAME OVER!");
    }
    public static void main(String[] args){
        System.out.println("this is my file");
        Player p = new Player("cas", 2);
        GUI gui = new GUI("HUNT THE WUMPUS");
        gui.displayActions(p);
    }
  }

