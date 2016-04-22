
/**
 * Added Group G as additional authors on 3-21-2016  D.K.
 * See Main for a list of Group G members
 * @author- Group G
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
import java.util.ArrayList;

public class GameWindow extends JFrame implements ActionListener
{
    /**
     * because it is a serializable object, need this or javac complains a lot
     */
    public static final long serialVersionUID = 1;

    private TileArea grid;
    private SideButtons sideButtons;

    private Tile firstClicked, secondClicked;

    public ArrayList<ArrayList<float[]>> allTilesLineCoords;

    /**
     * Constructor sets the window name using super(), changes the layout, which
     * you really need to read up on, and maybe you can see why I chose this
     * one.
     *
     * @param s
     */

    public GameWindow(String s)
    {
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

    public void actionPerformed(ActionEvent e)
    {
        if ("Quit".equals(e.getActionCommand()))
            System.exit(0);
        if ("Reset".equals(e.getActionCommand()))
        {
            reset();
        }
        if ("New Game".equals(e.getActionCommand()))
            newGame();
    }

    // method to reset the side panels and grid area to original state
    // DK 4/5/2016
    private void reset()
    {
        this.remove(sideButtons.leftPanel);
        this.remove(sideButtons.rightPanel);
        ((SideButtons) sideButtons).reset();
        createSidePanels();
        this.remove(grid);
        createGrid();
        this.revalidate();
    }
    
    private void newGame()
    { 
        this.getContentPane().removeAll();
        sideButtons = null;
        this.getContentPane().invalidate();
        this.getContentPane().validate();
        this.getContentPane().repaint();
        this.setUp();
        this.setVisible(true);
    }

    /**
     * Setup Establishes the initial board
     */

    public void setUp()
    {
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

        // Set up the button dimensions for the menu bar
        int buttonWidth = 150;
        int buttonHeight = 75;
        Dimension buttonDimen = new Dimension(buttonWidth, buttonHeight);

        // Initialize buttons
        // Font size is 1/3 of button width so it will be more consistent
        // between different machines. D.K.
        Main.newGameButton = new JButton("New Game");
        Main.newGameButton.setMinimumSize(buttonDimen);
        Main.newGameButton.setMaximumSize(buttonDimen);
        Main.newGameButton.setPreferredSize(buttonDimen);
        Main.newGameButton.addActionListener(this);
        Main.newGameButton
                .setFont(new Font("Arial", Font.PLAIN, buttonWidth / 6));

        Main.resetButton = new JButton("Reset");
        Main.resetButton.setMinimumSize(buttonDimen);
        Main.resetButton.setMaximumSize(buttonDimen);
        Main.resetButton.setPreferredSize(buttonDimen);
        Main.resetButton.addActionListener(this);
        Main.resetButton
                .setFont(new Font("Arial", Font.PLAIN, buttonWidth / 6));

        Main.quitButton = new JButton("Quit");
        Main.quitButton.setMinimumSize(buttonDimen);
        Main.quitButton.setMaximumSize(buttonDimen);
        Main.quitButton.setPreferredSize(buttonDimen);
        Main.quitButton.addActionListener(this);
        Main.quitButton.setFont(new Font("Arial", Font.PLAIN, buttonWidth / 6));

        toolbar.add(Main.newGameButton);
        toolbar.add(Main.resetButton);
        toolbar.add(Main.quitButton);
        toolbar.setSize(buttonWidth * 3, buttonHeight);
        toolbar.setMinimumSize(new Dimension(buttonWidth * 3, buttonHeight));

        // Add toolbar to JFrame at index 0 (in the top left)
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;

        add(toolbar, gbc);

        createGrid();

        createSidePanels();

        return;
    }

    // create both the left and right side panels
    private void createSidePanels()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        if (sideButtons == null)
        {
            sideButtons = new SideButtons(this, allTilesLineCoords);
        }
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridheight = 8;
        add(((SideButtons) sideButtons).leftPanel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(((SideButtons) sideButtons).rightPanel, gbc);
        invalidate();
    }

    // create the grid playing area
    private void createGrid()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        gbc.weightx = 1;
        gbc.weighty = 1;
        grid = new GridButtons(this);
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(grid, gbc);
    }

    /**
     * Getter for the first tile clicked on
     * 
     * @return the first tile clicked on -Jay 3/21/2016
     */
    public Tile getFirstClicked()
    {
        return firstClicked;
    }

    /**
     * Setter for the first tile clicked on
     * 
     * @param the
     *            value to set the tile as -Jay 3/21/2016
     */
    public void setFirstClicked(Tile firClick)
    {
        firstClicked = firClick;
    }

    /**
     * Getter for the second tile clicked on
     * 
     * @return the second tile clicked on -Jay 3/21/2016
     */
    public Tile getSecondClicked()
    {
        return secondClicked;
    }

    /**
     * Setter for the second tile clicked on
     * 
     * @param the
     *            value to set the tile as -Jay 3/21/2016
     */
    public void setSecondClicked(Tile secClick)
    {
        secondClicked = secClick;
    }
};