// Casandra Reyes

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;

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
    setLayout(new FlowLayout());

    setResizable(true);
    setLocationRelativeTo(null);
    setVisible(true);

    displayRoom();
    
  }

    public void displayRoom(){
        int xcord = 0;
        int ycord = 0;
        int i = 0; int j = 0;
//        for(int i = 0; i < 2; i++){
//            for(int j = 0; j < 3; j++){
                JButton button = new JButton();
                button.setBounds(xcord, ycord, 100, 100);
        
                String imagePath = System.getProperty("user.dir") + "\\images\\" + "forestbackground.png";
                System.out.println("Image path: " + imagePath);

                button.setIcon(loadIcon(imagePath, 100, 100));
            
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        // Put code here for what happens when the user clicks the button
                    }
                });   

                this.getContentPane().add(button);
                xcord += 100;
//            }
            ycord +=100;
            xcord = 0;
//        }

    }
    public int displayScore(){
        //called by game control?
        return 8; //not sure if it is an int or double
    }
    public void displayInventory(){
        //calls player
        //returns an array?
    }
    public String displayHints(){
        return "I smell a wumpus";
    }
    public void displayActions(){
        //calls player?
        //shows actions player can take
    }
  }

