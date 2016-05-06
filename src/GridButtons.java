
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

public class GridButtons extends TileArea
{

    private static final long serialVersionUID = 1;
    Tile[] myTiles = new Tile[16];

    /**
     * Constructor creates a grid from an array of 16 tiles
     * 
     * @param gameType
     *
     * @param frame
     */
    GridButtons(GameWindow window,
            ArrayList<ArrayList<float[]>> allTilesLineCoords,
            ArrayList<Integer> rotations, GameType gameType)
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
               tiles[i].rotate(rotations.get(i + window.numTiles) * 90);
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
            myTiles[i] = tiles[i];
        }
    }

    /**
     * return whether this is a solution or not
     * 
     * @return boolean
     */
    public boolean isSolution()
    {
        // Second solution array
        int[] solutionTwo = {12,8,4,0,13,9,5,1,14,10,6,2,15,11,7,3};
        // Third solution array
        int[] solutionThree = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
        // Fourth solution array
        int[] solutionFour = {3,7,11,15,2,6,10,14,1,5,9,13,0,4,8,12};
        
        for(int i = 0; i < window.numTiles; i++)
        {
            if(myTiles[i].getMazeIcon() != null)
            {
                // Solution 1
                if((myTiles[i].getTileNumber() - 16 != 
                        window.coordsToTile.get(
                                myTiles[i].getMazeIcon().getLineCoords())
                        || ((int)myTiles[i].getMazeIcon().getDegreesRotated()/90 != 0))
                    // Solution 2
                    && (solutionTwo[i] != window.coordsToTile.get(
                            myTiles[i].getMazeIcon().getLineCoords())
                        || ((int)myTiles[i].getMazeIcon().getDegreesRotated()/90 != 1))
                    // Solution 3
                    && (solutionThree[i] != window.coordsToTile.get(
                            myTiles[i].getMazeIcon().getLineCoords())
                        || ((int)myTiles[i].getMazeIcon().getDegreesRotated()/90 != 2))
                    // Solution 4
                    && (solutionFour[i] != window.coordsToTile.get(
                            myTiles[i].getMazeIcon().getLineCoords())
                        || ((int)myTiles[i].getMazeIcon().getDegreesRotated()/90 != 3)))

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