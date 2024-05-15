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

    // Load the icon, scaled to the given size
    private static ImageIcon loadIcon(String iconFilePath, int width, int height) {
        ImageIcon origIcon = new ImageIcon(iconFilePath);
        Image img = origIcon.getImage();  
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }

    public GUI(String title){
    System.out.println(title);

    setTitle(title);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setSize(640, 480);
    setLayout(new GridBagLayout());

    setResizable(true);
    setLocationRelativeTo(null);
    setVisible(true);

    //startScreen();
    displayRoom();

    
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
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                JButton button = new JButton();
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.gridx = j;
                gbc.gridy = i;
        
                String imagePath = System.getProperty("user.dir") + "\\images\\" + "forestbackground.png";
                System.out.println("Image path: " + imagePath);

                button.setIcon(loadIcon(imagePaths.get(imagecount), 200, 200));
                button.setSize(200,200);
            
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        // Put code here for what happens when the user clicks the button
                        //gameControl.walk(j, i);
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
        
//////change to this code below
        GridBagConstraints gbc = new GridBagConstraints();
        

        JButton nw = new JButton();
        String imagePathnw = System.getProperty("user.dir") + "\\images\\" + "1.png";
                System.out.println("Image path: " + imagePathnw);

                nw.setIcon(loadIcon(imagePathnw, 200, 200));
                nw.setSize(200,200);
                nw.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        // Put code here for what happens when the user clicks the button
                        //gameControl.walk(j, i);
                        System.out.println("-----moved north west");
                    }
                });   
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.getContentPane().add(nw, gbc);

        this.getContentPane().invalidate();
        this.getContentPane().validate();
        this.getContentPane().repaint();

/* 
        JButton n = new JButton();
        String imagePathn = System.getProperty("user.dir") + "\\images\\" + "2.png";
                System.out.println("Image path: " + imagePathn);

                nw.setIcon(loadIcon(imagePathn, 200, 200));
                nw.setSize(200,200);
                nw.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        // Put code here for what happens when the user clicks the button
                        //gameControl.walk(j, i);
                        System.out.println("-----moved north");
                    }
                });   
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.getContentPane().add(nw, gbc);

        this.getContentPane().invalidate();
        this.getContentPane().validate();
        this.getContentPane().repaint();
*/
    }
    public void startScreen(){
        
        JLabel htw = new JLabel("HUNT THE WUMPUS");
        Font font = new Font("SansSerif", 8, 80);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();

        gbc.gridx = 1;
        gbc.gridy = 2;
 

        htw.setFont(font);

        JButton start = new JButton("START");
        start.setSize(600,100);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
                //start game
                System.out.println("Game has started.");
                displayRoom();
                remove(start);
                revalidate();
                repaint();
            }
        });

        add(start, gbl);
        add(htw, gbl);


    }
    public int displayScore(){
        //called by game control?
        return 8; //not sure if it is an int or double
    }
    public void displayInventory(){
        //called by game control
        JLabel text = new JLabel("display score here");

    }
    public String displayHints(){
        return "I smell a wumpus";
    }
    public void displayActions(){
        //calls player?
        //shows actions player can take
    }
  }

