package Cave;

import javax.swing.*;
import java.awt.event.ActionListener;

// This file is an example of how a test UI might work.
// It is calling the mock cave object, but does not interact at all with the mock wumpus game code.

class CaveTest {
    private static Cave myCave;

    public static void TestConstructor() {
        myCave = new Cave();
        System.out.println("Constructor called");
    }

    public static String TestCaveDoStuff(String textInput) {
        // Convert the input text to an int
        int param = Integer.parseInt(textInput);

        // Call the function in the cave object
        int result = myCave.DoStuff(param);
        System.out.println("DoStuff called with " + param + " and returned " + result);

        // Convert the result to a string to be added to the label
        return "Result: " + result;
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("My Wumpus Test GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        int yPos = 0;
        int itemSpacing = 10;
        int rowHeight = 30;

        // Add some UI to test the constructor
        {
            yPos += itemSpacing;  // Space from top of window

            JButton button = new JButton("Test Constructor");
            button.setBounds(itemSpacing, yPos, 150, rowHeight);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    TestConstructor();
                }
            });
            frame.add(button);

            yPos += rowHeight;  // Height of this row
        }

        // Add some UI to test the DoStuff method
        {
            yPos += itemSpacing;  // Space from previous row
            int xPos = 0;

            JTextField textField = new JTextField();
            xPos += itemSpacing;  // Space from edge of window
            textField.setBounds(xPos, yPos, 50, rowHeight);
            xPos += 50;  // Text field width

            JButton button = new JButton("Test DoStuff");
            xPos += itemSpacing;  // Space between items
            button.setBounds(xPos, yPos, 150, rowHeight);
            xPos += 150;  // Button width

            JLabel label = new JLabel();
            xPos += itemSpacing;  // Space between items
            label.setBounds(xPos, yPos, 100, rowHeight);
            xPos += 100;  // Label width

            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // Read the input from the text field, write the results to the label
                    String result = TestCaveDoStuff(textField.getText());
                    label.setText(result);
                }
            });

            frame.add(textField);
            frame.add(button);
            frame.add(label);

            yPos += rowHeight;  // Height of this row
        }

        frame.setVisible(true);
    }
}