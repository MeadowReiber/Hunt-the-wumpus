// Casandra Reyes
// Test - editting at home 2

///////////////////////////////////////////
///// METHOD NAMES AND WHAT THEY DO:
///////////////////////////////////////////

// initializeFrame - sets color, layout etc. of the frame
// initializePanel - sets color, layout etc. of the panel given

// displayStartScreen - displays a Jpanel taking up the whole screen with a single start button
// displayRoom - takes a panel and on it displays the 2 x 3 current room as buttons - player clicks what direction to go and buttons call a walk method in game control
// displayActions - takes a panel and on it it displays actions for player to take - they can earn coins in rooms and spend on trivia questions to buy arrows and hints
// displayInventory - takes a player and a panel and displays their arrows and coins as jlabels on the panel

//

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

public class GUI extends JFrame{

    //---------PROPERTIES

    private ArrayList<Room> rooms;

    private JPanel startScreen;
    private JPanel room;
    private JPanel actions;
    private JPanel topBar;
    private JPanel inventory;
    private JPanel textPanel;
    private JLabel score;
    private JLabel arrows;
    private Font mainFont;
    private Color lightBeige;
    private Color medGreen;
    private ArrayList<String> imagePaths;
    private Cave cave;
    
    private GameControl gameControl;
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
        this.imagePaths = new ArrayList<String>();
        this.mainFont = new Font("SansSerif", Font.BOLD, 20);
        this.lightBeige = new Color(252, 244, 189);
        this.medGreen = new Color(168, 214, 124);
        
        //this.gameControl = gc;
        this.room = new JPanel();
        this.actions = new JPanel();
        this.textPanel = new JPanel();
        this.inventory = new JPanel();
        this.rooms = new ArrayList<Room>();
        this.imagePaths = new ArrayList<String>();

        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "1.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "2.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "3.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "4.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "5.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "6.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "SHOOTARROW.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "GETHINT.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "BUYTRIVIA.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "VIEWSCORES.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "5.png");
        this.imagePaths.add(System.getProperty("user.dir") + "\\images\\" + "6.png");
        
        initializeFrame(title);
        displayRoom();
        add(new Room(), BorderLayout.CENTER);
    
        this.room = new JPanel();
        add(room, BorderLayout.CENTER);
        displayRoom();
        
        this.actions = new JPanel();
        add(initializePanel(actions), BorderLayout.EAST);
        displayActions();
        
        this.textPanel = new JPanel();
        this.textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        add(initializePanel(textPanel), BorderLayout.NORTH);
        showMessage("Hint example : a wumpus is near");
        
        this.inventory = new JPanel();
        add(initializePanel(inventory), BorderLayout.SOUTH);
        displayInventory(new Player());
    
    }

    public GUI(String title, GameControl gc){
      
      this.imagePaths = new ArrayList<String>();
      this.mainFont = new Font("SansSerif", Font.BOLD, 20);
      this.lightBeige = new Color(252, 244, 189);
      this.medGreen = new Color(168, 214, 124);
      
      this.gameControl = gc;
      this.room = new JPanel();
      this.actions = new JPanel();
      this.textPanel = new JPanel();
      this.inventory = new JPanel();

      displayRoom();
      add(new Room(), BorderLayout.CENTER);
      
      initializeFrame(title);
      
    }
    
    //---------METHODS
    public void shotWumpus(){

    }
    public void gameOver(){

    }
    public void initializeFrame(String t){
        setTitle(t);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize(960, 540);
        setLayout(new BorderLayout());
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel initializePanel(JPanel p){
        p.setVisible(true);
        p.setBackground(medGreen);
        return p;
    }

    public void displayStartScreen(JPanel jp){
        JButton start = new JButton("HUNT THE WUMPUS CLICK TO PLAY"); //add image later
        start.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player arrows decrease and display on south panel named inventory
                //gc.startGame(); // in the game control class start game method will first call gui displayRules(); to show the instructions.
            }
        });
    }

    public void displayRules(){
        JButton rules = new JButton("RULES");
        add(rules);
        rules.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                remove(rules);
            }
        });
    }

    public void displayRoom(){
        HexagonalLayout hexLayout = new HexagonalLayout(30);
        room.setLayout(hexLayout);
        room.setVisible(true);   
        for(int i = 0; i < 30; i++){
            HexagonButton hexButton = new HexagonButton("Hexagon Button", i);
        }
    }
    
    /*public void example(){
             
      GridLayout roomLayout = new GridLayout(2,3);
      int imagecount = 0;

      room.setLayout(roomLayout);
      room.setBackground(medGreen);
      room.setVisible(true);

        for(int y = 0; y < 2; y++){
            for(int x = 0; x < 3; x++){
                //String imagePath = System.getProperty("user.dir") + "\\images\\" + "forestbackground.png";
                //System.out.println("Image path: " + imagePath);

                JButton button = new JButton();
                button.setIcon(loadIcon(imagePaths.get(imagecount), 300, 300));
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        ///gameControl.walk(x, y);
                        ////// gameControl class add this walk method, x and y are the direction where the player is moving 
                        System.out.println("BUTTONCLICKED");
                    }
                });   

                room.add(button, x, y);
                imagecount++;
            }
        }
        room .invalidate();
        room.validate();
        room.repaint();
    }*/

    public void showMessage(String text){ //if wumpus is shot, player dies, player is carried by bats or falls into a bottomless pit, etc, game control calls this method and passes in the string to display on player screen
        textPanel.removeAll();
        JLabel message = new JLabel(text);
        message.setFont(mainFont);
        textPanel.add(message);
    }

    public void askTriviaQuestion(String question, String optionA, String optionB, String optionC){
        JPanel jp = new JPanel();
        initializePanel(jp);
        //addText(question); make a method that takes a string and a panel and just adds the text into it
        //make jbuttons titled with the options and the actionlistener when clicked will call the game control checkAnswer(takes in the title of jButton to see if it matches the correct trivia question answer); 
        //after the game control checks if it is correct, game control should use the announcement method to announce whether the user is correct or not and reward them with the hint or the coins or something; also the game control is the one displaying the hints by using the announcement method and passing in the string or whatever message user needs to see.
    }
    
    public void displayActions(){

        GridLayout GL = new GridLayout(4, 1);

        actions.setLayout(GL);        

        JButton shootArrow = new JButton("shoot");
        shootArrow.setIcon(loadIcon(this.imagePaths.get(6), 200, 200));
        shootArrow.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player arrows decrease and display on south panel named inventory
                //gameControl.shoot(ABORT); 
                System.out.println("shot an arrow");
            }
        });
        JButton buyArrows = new JButton("buy arrows");
        buyArrows.setMargin(new Insets(0, 0, 0, 0));
        buyArrows.setIcon(loadIcon(this.imagePaths.get(7), 200, 200));
        buyArrows.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player arrows increases and coins decrease and display on screen on south panel named inventory
                //gameControl.shootArrow();
                System.out.println("bought an arrow");
            }
        });
        JButton buyHints = new JButton("buy hints");
        //buyTrivia.setIcon(loadIcon(this.imagePaths.get(8), 200, 200));
        buyArrows.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player coins decrease and display on screen on south panel named inventory
                //gameControl.
                System.out.println("bought a trivia question.");
            }
        });

        JButton viewScore = new JButton("view scores");
        viewScore.setIcon(loadIcon(this.imagePaths.get(9), 200, 100));
        viewScore.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //player coins decrease and display on screen on south panel named inventory
                //gameControl.
                System.out.println("viewed score.");
            }
        });

        actions.add(shootArrow);
        actions.add(buyArrows);
        actions.add(buyHints);
        actions.add(viewScore);
    }

    public JPanel displayTopBar(){ //maybe change the layout of the top bar panel to grid layout or border layout
        topBar.setLayout(new GridLayout(1,2));
        JLabel score = new JLabel("EXAMPLE SCORE: 1050");
        score.setFont(mainFont);
        topBar.add(score);
        
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
        room.add(gameOps);
        return room;
    }
    

    public void displayInventory(Player p){
        
        this.inventory.removeAll();

        JLabel arrows = new JLabel("ARROWS: " + p.getArrows());
        JLabel coins = new JLabel("COINS: " + p.getCoins());

        arrows.setFont(this.mainFont);
        coins.setFont(this.mainFont);

        this.inventory.add(arrows);
        this.inventory.add(coins);
        System.out.println("inventory displayed");
    }

    public int displayScore(){
       // HighScorePanel highscores = new HighScorePanel();
       return 1;
    }
    
    public static void main(String[] args){
        System.out.println("this is my file");
        GUI display = new GUI("game");
    }
  
}

