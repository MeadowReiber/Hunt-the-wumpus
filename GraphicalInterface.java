//DO NOT USE THIS CLASS

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class GraphicalInterface{
    ////////////
    //Properties
    ////////////
    private JButton button;
    ////////////
    //Constructor
    ////////////
    public GraphicalInterface(){
        displayRoom();
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



}


