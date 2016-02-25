/**
 * @author Kim Buckner
 * Date: Feb 19, 2016
 *
 * This is the actual "game". May/will have to make some major changes.
 * This is just a "hollow" shell.
 *
 * When you get done, I should see the buttons at the top in the "play" area
 * (not a pull-down menu). The only one that should do anything is Quit.
 *
 * Should also see something that shows where the 4x4 board and the "spare"
 * tiles will be when we get them stuffed in.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener
  {
    /**
     * because it is a serializable object, need this or javac
     * complains a lot
     */
    public static final long serialVersionUID=1;

    /*
     * Here I declare some buttons and declare an array to hold the grid elements. 
     * But, you can do what you want.
     */
    GridButton grid[] = new GridButton[16];
    
    private int startAt=1;
    
    /**
     * Constructor sets the window name using super(), changes the layout,
     * which you really need to read up on, and maybe you can see why I chose
     * this one.
     *
     * @param s
     */

    public GameWindow(String s)
    {
      super(s);
      GridBagLayout gbl=new GridBagLayout();
      setLayout(gbl);
    }

    /**
     * For the buttons
     * @param e is the ActionEvent
     * 
     * BTW can ask the event for the name of the object generating event.
     * The odd syntax for non-java people is that "exit" for instance is
     * converted to a String object, then that object's equals() method is
     * called.
     */

    public void actionPerformed(ActionEvent e) {
      if("exit".equals(e.getActionCommand()))
        System.exit(0);
      if("reset".equals(e.getActionCommand()))
        System.out.println("reset pressed\n");
      if("new".equals(e.getActionCommand()))
        System.out.println("new pressed\n");
    } 

    /**
     *  Establishes the initial board
     */

    public void setUp()
    {
      //actually create the array for elements, make sure it is big enough
      // Need to play around with the dimensions and the gridx/y values
      // These constraints are going to be added to the pieces/parts I 
      // stuff into the "GridBag".
   
      GridBagConstraints basic = new GridBagConstraints();

      //Here I create 16 elements to put into my gameBoard and add them
      for(int i = 0; i < 16; i++)
      {
    	  grid[i] = new GridButton(this, i);
    	  Insets inset = grid[i].inset;
    	  this.addButtons(this,grid[i].button,(i%4),(i/4),1,1,basic.CENTER,basic.BOTH,inset);
      }
      
      //This will hold the new, reset, and exit buttons
      JToolBar toolbar = new JToolBar();
      //toolbar can't be dragged around/out of JFrame
      toolbar.setFloatable(false);
      
      toolbar.setBackground(Color.YELLOW);
      
      //Initialize buttons
      Main.newGameButton = new JButton("new");
      Main.newGameButton.addActionListener(this);
      
      Main.resetButton = new JButton("reset");
      Main.resetButton.addActionListener(this);
      
      Main.quitButton = new JButton("exit");
      Main.quitButton.addActionListener(this);
      
      toolbar.add(Main.newGameButton);
      toolbar.add(Main.resetButton);
      toolbar.add(Main.quitButton);
      
      //Add toolbar to JFrame at index 0 (in the top left)
      add(toolbar, 0);

      return;
    }
    /**
     * Used by setUp() to configure the buttons on a button bar and
     * add it to the gameBoard
     */

    public void addButtons(Container container, Component component, int gridx, int gridy,
    	      int gridwidth, int gridheight, int anchor, int fill, Insets in){
    	GridBagConstraints gbc = new GridBagConstraints
    			(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
    	        anchor, fill, in, 0, 0);
    	    container.add(component, gbc);
        return;
    }

  };