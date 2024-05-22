// Casandra Reyes
// Test - editting at home

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

    //---------PROPERTIES
    
    private JPanel room;
    private JPanel actions;
    private JPanel inventory;
    private JPanel message;

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

    //---------CONSTRUCTOR
    
    public GUI(String title){
        initializeFrame(title);
        
        this.mainFont = new Font("SansSerif", 5, 20);
        this.gc = new GameControl();
    
        this.room = new JPanel();
        this.actions = new JPanel();
        this.inventory = new JPanel();
        this.message = new JPanel();
    
        this.room.setLayout(new GridLayout(2, 3));
        
        add(room, BorderLayout.CENTER);
        //need to change size of the room panel to not fill up entire screen cuz of layout
        add(initializePanel(actions), BorderLayout.NORTH);
        add(initializePanel(inventory), BorderLayout.SOUTH);
        displayRoom(room);
        displayActions(actions);
        
        //add(initializePanel(message), BorderLayout.SOUTH);
        //startScreen();
    
    }
    
    //---------METHODS
    
    public void initializeFrame(String t){
        setTitle(t);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize(960, 540);
    
        setLayout(new BorderLayout());
        
        /*this.gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;*/

        //readd gridbag if needed
    
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public JPanel initializePanel(JPanel p){
        p.setLayout(new FLowLayout(FlowLayout.CENTER, 10, 5));
        p.setBackground(Color.BLUE);
        return p;
    }

    
    public void displayRoom(JPanel jp){
        
        ArrayList<String> imagePaths= new ArrayList<String>();
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "1.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "2.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "3.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "4.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "5.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "6.png");
        
        int imagecount = 0;
        
        for(int y = 0; y < p.getLayout().getRows(); y++){
            for(int x = 0; x < p.getLayout().getColumns(); x++){
                //String imagePath = System.getProperty("user.dir") + "\\images\\" + "forestbackground.png";
                //System.out.println("Image path: " + imagePath);
                
                JButton button = new JButton();
                button.setIcon(loadIcon(imagePaths.get(imagecount), 200, 200));
                //button.setSize(200,200); //try removing when running
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        //g.walk(y, x);
                        ////// gameControl class add this walk method, x and y are the direction where the player is moving 
                        System.out.println("BUTTONCLICKED");
                    }
                });   

                jp.add(button);
                imagecount++;
            }
        }
        jp.invalidate();
        jp.validate();
        jp.repaint();
        //try removing and running
    }

    
    public void displayActions(JPanel jp){
        JButton shootArrow = new JButton("Shoot");
        shoot.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player arrows decrease and display on south panel named inventory
            }
        });
        JButton buyArrows = new JButton("Buy Arrows");
        buyArrows.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player arrows increases and coins decrease and display on screen on south panel named inventory
            }
        });
        JButton buyHint = new JButton("Buy Hint");
        buyArrows.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player coins decrease and display on screen on south panel named inventory
            }
        });

        jp.add(shootArrow);
        jp.add(buyArrows);
        jp.add(buyHint);
    }
    

    public void displayInventory(Player p){
        this.inventory.removeAll();
        
        JLabel arrows = new JLabel("ARROWS: " + p.getArrows());
        JLabel coins = new JLabel("COINS: " + p.getCoins());

        this.inventory.add(arrows);
        this.inventory.add(coins);
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

