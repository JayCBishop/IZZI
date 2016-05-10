
/**
 * Added authors as Group G on 3-21-2016  D.K.
 * Members of group listed in Main.java
 * Tile.java
 * Software Design, Spring 2016
 * @author-Group G
 * Created 2/23/2016
 * 
 * Is a collection of multiple tiles that form a grid
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GridButtons extends TileArea
{

    private static final long serialVersionUID = 1;

    /**
     * Constructor creates a grid from an array of 16 tiles
     * 
     * @param gameType
     *
     * @param frame
     */
    GridButtons(GameWindow window,
            HashMap<Integer, ArrayList<float[]>> allTilesLineCoords,
            HashMap<Integer, Integer> rotations, GameType gameType)
    {
        super(window, allTilesLineCoords, rotations, gameType);
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        // Create GridButtons in play area
        // DK 3-23-2016

        setUp();
    }

    /**
     * Sets up the grid buttons
     */
    private void setUp()
    {
        for (int i = 0; i < window.numTiles; i++)
        {
            tiles[i] = new Tile(window, i + window.numTiles);
            tiles[i].setMaximumSize(tileDimen);
            tiles[i].setMinimumSize(tileDimen);
            tiles[i].setPreferredSize(tileDimen);
            if (gameType == GameType.ORIGINAL_GAME)
            {
                tiles[i].setMazeIcon(null);
            }
            else
                if (gameType == GameType.BLANK_GAME
                        || allTilesLineCoords.get(i + window.numTiles) == null
                        || allTilesLineCoords.get(i + window.numTiles)
                                .size() == 0)
                {
                    tiles[i].setMazeIcon(null);
                }
                else
                {
                    tiles[i].setMazeIcon(new MazeIcon(
                            allTilesLineCoords.get(i + window.numTiles)));
                    tiles[i].setBorder(null);
                }
            // rotate the tile we created by 90 * whatever the integer
            // number is
            if (gameType == GameType.PLAYED_GAME)
            {
                if (rotations.get(i + window.numTiles) != null)
                {
                    tiles[i].rotate(rotations.get(i + window.numTiles) * 90);
                }
            }

            tiles[i].setIsInGrid(true);
            Insets inset = new Insets(0, 0, 0, 0); // All insets same,
                                                   // removed
                                                   // method call AC
                                                   // 3-23-2016
            int rowLength = (int) Math.sqrt(window.numTiles);
            this.addButtons(this, tiles[i], (i % rowLength), (i / rowLength), 1,
                    1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    inset);
            this.addActionListener(tiles[i], window);
        }
    }

    /**
     * return whether this is a solution or not
     * 
     * @return boolean
     */
    public boolean isSolution()
    {
        // Check for first solution
        for (int i = 0; i < window.numTiles; i++)
        {
            if(tiles[i].getMazeIcon()!=null)
            {
                if(window.solution.get(i) != tiles[i].getMazeIcon().getLineCoords()
                        || tiles[i].getMazeIcon().getDegreesRotated() /90 != 0)
                {
                    return false;
                }
            }
            else
            {
                return false;
            }          
        }
        return true;
    }
}