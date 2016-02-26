
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

public class GameWindow extends JFrame implements ActionListener {
    /**
     * because it is a serializable object, need this or javac complains a lot
     */
    public static final long serialVersionUID = 1;

    /*
     * Here I declare some buttons and declare an array to hold the grid
     * elements. But, you can do what you want.
     */

    private int startAt = 1;

    /**
     * Constructor sets the window name using super(), changes the layout, which
     * you really need to read up on, and maybe you can see why I chose this
     * one.
     *
     * @param s
     */

    public GameWindow(String s) {
        super(s);
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
    }

    /**
     * For the buttons
     * 
     * @param e
     *            is the ActionEvent
     * 
     *            BTW can ask the event for the name of the object generating
     *            event. The odd syntax for non-java people is that "exit" for
     *            instance is converted to a String object, then that object's
     *            equals() method is called.
     */

    public void actionPerformed(ActionEvent e) {
        if ("exit".equals(e.getActionCommand()))
            System.exit(0);
        if ("reset".equals(e.getActionCommand()))
            System.out.println("reset pressed\n");
        if ("new".equals(e.getActionCommand()))
            System.out.println("new pressed\n");
    }

    /**
     * Establishes the initial board
     */

    public void setUp() {
        // actually create the array for elements, make sure it is big enough
        // Need to play around with the dimensions and the gridx/y values
        // These constraints are going to be added to the pieces/parts I
        // stuff into the "GridBag".

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        // This will hold the new, reset, and exit buttons
        JToolBar toolbar = new JToolBar();
        // toolbar can't be dragged around/out of JFrame
        toolbar.setFloatable(false);

        toolbar.setBackground(Color.YELLOW);

        // Initialize buttons
        Main.newGameButton = new JButton("new");
        Main.newGameButton.setMinimumSize(new Dimension(100, 75));
        Main.newGameButton.setMaximumSize(new Dimension(100, 75));
        Main.newGameButton.setPreferredSize(new Dimension(100, 75));
        Main.newGameButton.addActionListener(this);
        Main.newGameButton.setFont(new Font("Arial", Font.PLAIN, 40));

        Main.resetButton = new JButton("reset");
        Main.resetButton.setMinimumSize(new Dimension(100, 75));
        Main.resetButton.setMaximumSize(new Dimension(100, 75));
        Main.resetButton.setPreferredSize(new Dimension(100, 75));
        Main.resetButton.addActionListener(this);
        Main.resetButton.setFont(new Font("Arial", Font.PLAIN, 40));

        Main.quitButton = new JButton("exit");
        Main.quitButton.setMinimumSize(new Dimension(100, 75));
        Main.quitButton.setMaximumSize(new Dimension(100, 75));
        Main.quitButton.setPreferredSize(new Dimension(100, 75));        
        Main.quitButton.addActionListener(this);
        Main.quitButton.setFont(new Font("Arial", Font.PLAIN, 40));

        toolbar.add(Main.newGameButton);
        toolbar.add(Main.resetButton);
        toolbar.add(Main.quitButton);
        toolbar.setSize(200, 50);
        toolbar.setMinimumSize(new Dimension(200, 50));

        // Add toolbar to JFrame at index 0 (in the top left)
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;

        add(toolbar, gbc);

        TileArea grid = new GridButtons(this);

      
        
        TileArea sideButtons = new SideButtons(this);
       // gbc.fill = gbc.BOTH;
        gbc.gridy = 1;
      
        gbc.gridx = 0;
        gbc.insets = new Insets(50, 25, 75, 0);
        gbc.gridheight = 4;
        add(((SideButtons) sideButtons).leftPanel, gbc);
        
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(250, 75, 225, 75);
        gbc.gridwidth = 1;
        add(grid, gbc);
        
        
        gbc.gridy = 1;
        gbc.gridx = 3;
        gbc.gridheight = 4;
        gbc.insets = new Insets(50, 0, 75, 25);
        add(((SideButtons) sideButtons).rightPanel, gbc);
        
        
        return;
    }
    /**
     * Used by setUp() to configure the buttons on a button bar and add it to
     * the gameBoard
     */

};