// Casandra Reyes
// Test - editting at home 2

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;import java.awt.FlowLayout;
import java.awt.*;
import java.awt.Color;
import java.awt.TextField

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame{

    //---------PROPERTIES

    private JPanel startScreen;
    private JPanel room;
    private JPanel actions;
    private JPanel topBar;
    private JPanel inventory;
    private JPanel message;

    private JLabel score;
    private JLabel arrows;
    private GridBagConstraints gbc;
    private Font mainFont;
    private Color lightBeige;
    private Color medGreen;
    
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
        
        this.mainFont = new Font("SansSerif", Font.BOLD, 20);
        this.lightBeige = new Color(252, 244, 189);
        this.medGreen = new Color(168, 214, 124);
        //this.gc = new GameControl();
    
        this.room = new JPanel();
        add(room, BorderLayout.CENTER); //might need to change size of the room panel to not fill up entire screen cuz of layout
        displayRoom(room);
        
        this.actions = new JPanel();
        add(initializePanel(actions), BorderLayout.EAST);
        displayActions(actions);
        
        this.inventory = new JPanel();
        add(initializePanel(inventory), BorderLayout.SOUTH);
        displayInventory(new Player());
        
        this.message = new JPanel();
        add(initializePanel(message), BorderLayout.WEST);
        namePanel(message, "CONSOLE"); //if components don't show up, try doing add(displayMessage(message)) and have the method return the panel editted
        
        this.startScreen = new JPanel();
        add(initializePanel(startScreen), BorderLayout.CENTER);
        displayStartScreen(startScreen);
        this.room.setLayout(null);

        this.topBar = new JPanel();
        add(initializePanel(topBar), BorderLayout.NORTH);
        displayTopBar(topBar);
        
        
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
        p.setVisible(true);
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        p.setBackground(medGreen);
        return p;
    }

    public void displayStartScreen(JPanel jp){
        JButton start = new JButton("HUNT THE WUMPUS CLICK TO PLAY"); //add image later
        start.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player arrows decrease and display on south panel named inventory
                //gc.startGame();
            }
        });
    }

    public void displayRoom(JPanel jp){

        ArrayList<String> imagePaths= new ArrayList<String>();
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "grassHex.png");
        imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "caveHex.png");
        
        JButton button = new JButton;
        int size = 100;
        int offsetX = 0;
        int offsetY = 0;
        button.setSize(size, size);

        for(int x = 0; x < 6; x++){
            offsetX += size;
            offsetY = 0;
            if(x % 2 = 0){
                offsetY -= size;
            }
            for(int y = 0; y < 5; y++){
                button.setBounds(offsetY, offsetX, size, size);
                add(button);
                offsetY -= size;
            }
        }
    }
    
    public void displayRoomXXXX(JPanel jp){
        
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

    public void typeName(Player p){
        JTextField textField = new JTextField;
        textField.setBounds(5, 5, 280, 50);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                p.setName() = textField.getText();
            }
        });
        add(textField);
    }

    public void namePanel(JPanel jp, String panelTitle){
        JLabel messagePanelTitle = new JLabel(panelTitle);
        messagePanelTitle.setFont(mainFont);
        jp.add(messagePanelTitle);
    }

    public void announcement(String message){ //if wumpus is shot, player dies, player is carried by bats or falls into a bottomless pit, etc, game control calls this method and passes in the string to display on player screen
        this.message.removeAll();
        displayMessage(message);
        this.message.add(new JLabel(String message).setFont(mainFont));
    }

    public void askTriviaQuestion(String question, String optionA, String optionB, String optionC){
        JPanel jp = new JPanel();
        initializePanel(jp);
        namePanel(jp, "TRIVIA");
        //addText(question); make a method that takes a string and a panel and just adds the text into it
        //make jbuttons titled with the options and the actionlistener when clicked will call the game control checkAnswer(takes in the title of jButton to see if it matches the correct trivia question answer); 
        //after the game control checks if it is correct, game control should use the announcement method to announce whether the user is correct or not and reward them with the hint or the coins or something; also the game control is the one displaying the hints by using the announcement method and passing in the string or whatever message user needs to see.
    }
    
    public void displayActions(JPanel jp){
        JButton shootArrow = new JButton("Shoot");
        shootArrow.addActionListener(new ActionListener() {
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

    public void displayTopBar(JPanel jp){ //maybe change the layout of the top bar panel to grid layout or border layout
        JLabel score = new JLabel("EXAMPLE SCORE: 1050");
        jp.add(score);
        
        JButton gameOps = new JButton("GameOptions");
        gameOps.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                JPanel menu = new JPanel();
                menu.setLayout(new GridLayout(2,1));
                
                JButton closeMenu = new JButton("Close Menu");
                closeMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e){
                        //player coins decrease and display on screen on south panel named inventory
                        remove(menu);
                    }
                });
                menu.add(closeMenu);
                
                JButton saveAndQuit = new JButton("Save and Quit");
                saveAndQuit.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e){
                        //quit and show high score
                        //gc.endGame();
                    }
                });
                menu.add(saveAndQuit);
                add(menu, BorderLayout.CENTER);
            }
        });
        jp.add(gameOps);
        return jp;
    }
    

    public void displayInventory(Player p){
        this.inventory.removeAll();
        
        JLabel arrows = new JLabel("ARROWS: " + 3); //change later to p.getArrows() etc.
        JLabel coins = new JLabel("COINS: " + 3);
        JButton b = new JButton("test");
        b.setSize(400, 400);
        this.inventory.add(b);

        arrows.setFont(this.mainFont);
        coins.setFont(this.mainFont);

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
                //displayRoom();
                //displayButtons();
                //remove(start);
                //remove(htw);
                //revalidate();
                //repaint();
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
        //Player p = new Player("cas", 2);
        //GameControl gc = new GameControl();
        GUI gui = new GUI("HUNT THE WUMPUS");
        //gui.displayActions(p);
    }
  }

