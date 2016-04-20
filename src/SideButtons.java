
/**
 * added authors as Group G on 3-21-2016  D.K.
 * Members listed in Main.java
 * Tile.java
 * Software Design, Spring 2016
 * @author- Group G
 * Created 2/23/2016
 * 
 * Two collections of tiles that form two side panels
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class SideButtons extends TileArea
{
    private static final long serialVersionUID = 1;
    public JPanel leftPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    private Dimension tileDimen = new Dimension(100, 100);

    private Tile[] tiles = new Tile[16];

    /**
     * Constructor creates both the side panels from two arrays of 8 tiles
     * 
     * @param images
     *
     * @param frame
     */
    SideButtons(GameWindow window, Vector<Vector<float[]>> allTilesLineCoords)
   {
        GridBagLayout gbl = new GridBagLayout();
        leftPanel.setLayout(gbl);
        rightPanel.setLayout(gbl);
        leftPanel.setBackground(Color.cyan);
        rightPanel.setBackground(Color.cyan);

        // Add the left SideButton panel
        // Add the numbers 0 thru 7 to the tiles DK 3-22-2016

        for (int index = 0; index < 16; index++)
        {
            tiles[index] = new Tile();
            tiles[index].setIsInGrid(false);
            tiles[index].setMaximumSize(tileDimen);
            tiles[index].setMinimumSize(tileDimen);
            tiles[index].setPreferredSize(tileDimen);
            Insets inset = new Insets(0, 0, 0, 0); // All insets same, removed
                                                   // method call AC 3-23-2016
            this.addActionListener(tiles[index], window);
            if (index < 8)
            {
                this.addButtons(leftPanel, tiles[index], 1, index, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        inset);
            } else
            {
                this.addButtons(rightPanel, tiles[index], 1, index, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        inset);
            }
            tiles[index].setMazeIcon(new MazeIcon(allTilesLineCoords.get(index)));
        }
        shuffle();
    }

    /**
     * stub for shuffle method to rearrange tiles
     */
    public void shuffle()
    {
        ArrayList<MazeIcon> icons = new ArrayList<MazeIcon>();
        ArrayList<Integer> rotations = new ArrayList<Integer>();
        for(int i = 0; i < tiles.length; i++)
        {
            icons.add(i,tiles[i].getMazeIcon());
            rotations.add(i,90*((i%4)+1));
            tiles[i].setMazeIcon(null);
        }
        for(int i = 15; i >= 0; i--)
        {
            Random r = new Random();
            int Result = r.nextInt(i+1);
            tiles[i].setMazeIcon(icons.get(Result));
            icons.remove(Result);
            tiles[i].rotate(rotations.get(Result));
            rotations.remove(Result);
        }
    }
}