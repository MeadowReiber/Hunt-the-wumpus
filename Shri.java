import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shri extends JFrame {

    private JLabel welcomeLabel;
    private JButton[] actionButtons = new JButton[5];
    private JPanel cavePanel;

    public Shri() {
        setTitle("Please choose a cave:");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        //creates new message
        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); 
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        add(welcomeLabel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        for (int i = 0; i < 5; i++) {
            actionButtons[i] = new JButton("Cave " + (i + 1));
            int finalI = i;
            actionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayCave(finalI + 1); 
                }
            });
            buttonPanel.add(actionButtons[i]);
        }
        add(buttonPanel, BorderLayout.SOUTH);

        cavePanel = new JPanel();
        cavePanel.setLayout(new GridLayout(5, 6)); 
        add(cavePanel, BorderLayout.CENTER);

        displayWelcomeMessage("Welcome to Hunt the Wumpus!");
    }

    private void displayWelcomeMessage(String message) {
        Timer timer = new Timer(100, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < message.length()) {
                    welcomeLabel.setText(welcomeLabel.getText() + message.charAt(index));
                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void displayCave(int caveNumber) {
        cavePanel.removeAll(); 

        for (int i = 0; i < 30; i++) {
            JPanel hexagonPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawHexagon(g);
                }

                private void drawHexagon(Graphics g) {
                    int xPoints[] = {40, 80, 120, 120, 80, 40, 20};
                    int yPoints[] = {20, 20, 40, 80, 100, 100, 20};
                    g.drawPolygon(xPoints, yPoints, 7);
                }
            };
            cavePanel.add(hexagonPanel);
        }

        cavePanel.revalidate();
        cavePanel.repaint();
    }

    public static void main(String[] args) {
        Shri gui = new Shri();
        gui.setVisible(true);
    }
}