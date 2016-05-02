
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

public class GameWindow extends JFrame implements ActionListener
{
    /**
     * because it is a serializable object, need this or javac complains a lot
     */
    public static final long serialVersionUID = 1;

    // Boolean that is set true if any changes have been made to the board
    // Will need to be set back to false if a save method is invoked
    private TileArea grid;
    private SideButtons sideButtons;

    private Tile firstClicked, secondClicked;
    private JPanel panel;
    public ArrayList<ArrayList<float[]>> allTilesLineCoords;
    // added rotations array in for the stored rotations of the played games DK
    // 4/28/2016
    public ArrayList<Integer> rotations;
    private GameType gameType;
    private MazeIcon[] savedIcons = new MazeIcon[32];

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
        {
            if (checkChangesMade())
            {
                popUpAlert();
            }
            else
            {
                System.exit(0);
            }
        }
        if ("Reset".equals(e.getActionCommand()))
        {
            reset();
        }
        if ("File".equals(e.getActionCommand()))
        {
            menu();
        }
    }

    private boolean checkChangesMade()
    {
        Tile[] sideTiles = sideButtons.getTiles();
        Tile[] gridTiles = grid.getTiles();
        for (int i = 0; i < 16; i++)
        {
            if (sideTiles[i].getMazeIcon() == null)
            {
                if (savedIcons[i] != null)
                {
                    return true;
                }
            }
            else
            {
                if (!(sideTiles[i].getMazeIcon().getLineCoords()
                        .equals(savedIcons[i].getLineCoords())))
                {
                    return true;
                }
                if (!(sideTiles[i].getMazeIcon()
                        .getDegreesRotated() == savedIcons[i]
                                .getDegreesRotated()))
                {
                    return true;
                }
            }
        }
        for (int i = 16; i < 32; i++)
        {
            if (gridTiles[i - 16].getMazeIcon() == null)
            {
                if (savedIcons[i] != null)
                {
                    return true;
                }
            }
            else
            {
                if (!(gridTiles[i - 16].getMazeIcon().getLineCoords()
                        .equals(savedIcons[i].getLineCoords())))
                {
                    return true;
                }
                if (!(gridTiles[i - 16].getMazeIcon()
                        .getDegreesRotated() == savedIcons[i]
                                .getDegreesRotated()))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Handles the pop-up window that runs when the user decides to quit -Jay
     * 4/26/2016
     */
    private void popUpAlert()
    {
        Object[] options =
        { "Yes", "No" };

        int n = JOptionPane.showOptionDialog(this,
                "Changes have been made to the game board. Would"
                        + " you like to save those changes?",
                "Warning", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, // do not use a custom Icon
                options, // the titles of buttons
                options[0]); // default button title

        // Yes option was selected
        if (n == 0)
        {
            save();
        }
        else
            if (n == 1)
            {
                System.exit(0);
            }
    }

    // This method alerts the player that the file
    // they used has an invalid file format DK 4/29/16
    public void alertInvalFileFormat()
    {
        JOptionPane.showMessageDialog(panel,
                "The first four bytes of the file have an error."
                        + " The game will start with no maze loaded.",
                "Invalid File Format", JOptionPane.ERROR_MESSAGE);
    }

    public void invalFileName(String name)
    {
        JOptionPane.showMessageDialog(panel,
                "File: " + name + " could not be found.", "Invalid File Name",
                JOptionPane.ERROR_MESSAGE);
        load();
    }

    public void fileAlreadyExists()
    {
        int n = JOptionPane.showConfirmDialog(panel,
                "This file already exists. Would you like to overwrite it?");
        // Yes was not selected
        if (n != 0)
        {
            // reopen fileChooser for save
            save();
        }
    }

    /**
     * Resets the game to its original state
     */
    // DK 4/5/2016
    private void reset()
    {
        if (gameType != GameType.BLANK_GAME)
        {
            this.remove(sideButtons.leftPanel);
            this.remove(sideButtons.rightPanel);
            ((SideButtons) sideButtons).reset();
            createSidePanels();
            this.remove(grid);
            createGrid();
            this.revalidate();
        }
    }

    /**
     * Setup establishes the initial board The boolean decides whether or not
     * the game board will be blank i.e. not contain a maze or a maze will be
     * presented. DK 4/29/16
     */

    public void setUp(GameType gameType)
    {
        // actually create the array for elements, make sure it is big enough
        // Need to play around with the dimensions and the gridx/y values
        // These constraints are going to be added to the pieces/parts I
        // stuff into the "GridBag".

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        this.gameType = gameType;

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
        Main.fileButton = new JButton("File");
        Main.fileButton.setMinimumSize(buttonDimen);
        Main.fileButton.setMaximumSize(buttonDimen);
        Main.fileButton.setPreferredSize(buttonDimen);
        Main.fileButton.addActionListener(this);
        Main.fileButton.setFont(new Font("Arial", Font.PLAIN, buttonWidth / 6));

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

        toolbar.add(Main.fileButton);
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
            sideButtons = new SideButtons(this, allTilesLineCoords, rotations,
                    gameType);
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

        for (int i = 0; i < 16; i++)
        {
            savedIcons[i] = sideButtons.getTiles()[i].getMazeIcon();
        }
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
        grid = new GridButtons(this, allTilesLineCoords, rotations, gameType);
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(grid, gbc);

        Tile[] gridTiles = grid.getTiles();
        for (int i = 0; i < 16; i++)
        {
            savedIcons[i + 16] = gridTiles[i].getMazeIcon();
        }
        invalidate();
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

    /**
     * When invoked, brings up a menu with a load and save item that allows the
     * user to load and save the current state of the maze
     * 
     * -Jay 4/27/2016
     */
    public void menu()
    {
        // The Font for the MenuItems
        Font f = new Font("sans-serif", Font.PLAIN, 24);

        // The PopupMenu
        final JPopupMenu popup = new JPopupMenu();

        // Load MenuItem
        JMenuItem load = new JMenuItem("     Load");
        load.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                load();
            }
        });

        // Save MenuItem
        JMenuItem save = new JMenuItem("     Save");
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                save();
            }
        });

        // Set the MenuItem fonts
        load.setFont(f);
        save.setFont(f);

        // Add the MenuItems
        popup.add(load);
        popup.add(save);

        popup.setPopupSize(Main.fileButton.getWidth(),
                Main.fileButton.getHeight() * 2);
        popup.show(Main.fileButton, 0, Main.fileButton.getHeight());
    }

    /**
     * Gets a fileName from the user via a file chooser and restarts the program
     * with that fileName as the new file to be loaded.
     */
    public void load()
    {
        String newFileName = "default.mze";

        if(checkChangesMade())
        {
            popUpAlert();
        }
        // Start in directory program is run from
        final JFileChooser chooser = new JFileChooser(
                new File(System.getProperty("user.dir")));
        chooser.showOpenDialog(GameWindow.this);

        if (chooser.getSelectedFile() != null)
        {
            newFileName = chooser.getSelectedFile().getName();
            Main.fileName = newFileName;
            Main.main(null);
        }
    }

    /**
     * Gets a filename from the user via a file chooser and saves the current
     * game to that file.
     */
    public void save()
    {
        // Start in directory program is run from
        final JFileChooser chooser = new JFileChooser(
                new File(System.getProperty("user.dir")));

        int returnVal = chooser.showSaveDialog(GameWindow.this);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            // save file
            File file = chooser.getSelectedFile();
            if (file.exists())
            {
                // give a warning
                fileAlreadyExists();
            }
            ByteFileStreamWriter writer;
            try
            {
                writer = new ByteFileStreamWriter(file);
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
                return;
            }

            try
            {
                // Assuming file has been played
                writer.write(new byte[]
                { (byte) 0xca, (byte) 0xfe, (byte) 0xde, (byte) 0xed });
                // Number of Tiles (assuming 32 for now)
                writer.writeInt(32);
                // Tile settings

                // Sidebuttons
                for (Tile tile : sideButtons.getTiles())
                {
                    int tileNum = tile.getTileNumber();
                    // tile number/placement
                    writer.writeInt(tileNum);
                    if (tile.isDrawn())
                    {
                        //System.out.println("Tile " + tileNum + " is drawn");
                        MazeIcon icon = tile.getMazeIcon();
                        int rotation = (int) (icon.getDegreesRotated() / 90)
                                % 4;
                        //System.out.println("rotated " + rotation + " times: "
                        //        + icon.getDegreesRotated());
                        // tile rotation
                        writer.writeInt(rotation);
                        ArrayList<float[]> lineCoords = icon.getLineCoords();
                        //System.out.println(lineCoords);
                        // number of lines on the tile
                        writer.writeInt(lineCoords.size());
                        for (int i = 0; i < lineCoords.size(); i++)
                        {
                            // get coords out of array
                            float[] coordList = lineCoords.get(i);
                            for (int j = 0; j < coordList.length; j++)
                            {
                                writer.writeFloat(coordList[j]);
                            }
                        }
                        savedIcons[tileNum] = icon;
                    }
                    else
                    {
                        // Tile has no image use default values
                        // rotation
                        writer.writeInt(0);
                        // number of lines
                        writer.writeInt(0);
                        savedIcons[tileNum] = null;
                    }

                }
                // Grid
                for (Tile tile : grid.getTiles())
                {
                    int tileNum = tile.getTileNumber();
                    writer.writeInt(tileNum);

                    // tile number/placement
                    if (tile.isDrawn())
                    {
                        //System.out.println("Tile " + tileNum + " is drawn");
                        MazeIcon icon = tile.getMazeIcon();
                        int rotation = (int) (icon.getDegreesRotated() / 90)
                                % 4;
                        //System.out.println("rotated " + rotation + " times: "
                        //        + icon.getDegreesRotated());
                        // tile rotation
                        writer.writeInt(rotation);
                        ArrayList<float[]> lineCoords = icon.getLineCoords();
                        //System.out.println(lineCoords);
                        // number of lines on the tile
                        int boop = lineCoords.size();
                        //System.out.println("BOOP " + boop);
                        writer.writeInt(lineCoords.size());
                        for (int i = 0; i < lineCoords.size(); i++)
                        {
                            // get coords out of array
                            float[] coordList = lineCoords.get(i);
                            for (int j = 0; j < coordList.length; j++)
                            {
                                writer.writeFloat(coordList[j]);
                            }
                        }
                        savedIcons[tileNum] = icon;
                    }
                    else
                    {
                        // Tile has no image, use default values
                        // rotation
                        writer.writeInt(0);
                        // number of lines
                        writer.writeInt(0);
                        savedIcons[tileNum] = null;

                    }

                }
                writer.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            ;
        }
    }
};