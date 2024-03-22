//Casandra Reyes

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class GraphicalInterface extends JButton implements ActionListener{
    ////////////
    //Properties
    ////////////
    private JButton button;
    ////////////
    //Constructor
    ////////////
    public GraphicalInterface(){
        super("testing button");
    }

    public ShowJS()
    {
        // Saved in case it's useful
        /*
        ScriptEngine js = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bindings = js.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("stdout", System.out);
        js.eval("stdout.println(Math.cos(Math.PI));");        
        */

        Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);

        JFrame frame = new JFrame("JxBrowser");
        frame.add(browserView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);

        browser.loadURL("file:///C:/Hunt-the-wumpus-1/Animation.html");//"http://www.google.com");
    }

    ////////////
    //Methods
    ////////////
    public void displayRoom(){
        //displays the hexagonal room in the form of 6 buttons with exits
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


