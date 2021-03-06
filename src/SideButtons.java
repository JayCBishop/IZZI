
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
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class SideButtons extends TileArea
{
    private static final long serialVersionUID = 1;
    public JPanel leftPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    private Tile[] startTiles = new Tile[16];

    SideButtons(GameWindow window,
            HashMap<Integer, ArrayList<float[]>> allTilesLineCoords,
            HashMap<Integer, Integer> rotations, GameType gameType)
    {
        super(window, allTilesLineCoords, rotations, gameType);
        GridBagLayout gbl = new GridBagLayout();
        leftPanel.setLayout(gbl);
        rightPanel.setLayout(gbl);
        leftPanel.setBackground(Color.cyan);
        rightPanel.setBackground(Color.cyan);

        if (!(gameType == GameType.BLANK_GAME))
        {
            setUp(true);
            if (gameType == GameType.ORIGINAL_GAME)
            {
                shuffle();
            }
            if (gameType == GameType.PLAYED_GAME)
            {
                for (int i = 0; i < window.numTiles; i++)
                {
                    if (rotations.get(i) != null)
                    {
                        tiles[i].rotate(rotations.get(i) * 90);
                    }
                }
            }
            for (int i = 0; i < 16; i++)
            {
                startTiles[i] = new Tile(window, i);
                startTiles[i].setMaximumSize(tileDimen);
                startTiles[i].setMinimumSize(tileDimen);
                startTiles[i].setPreferredSize(tileDimen);
                startTiles[i].setMazeIcon(tiles[i].getMazeIcon());
                if (window.rotations.get(i) != null)
                {
                    startTiles[i].setRotation(window.rotations.get(i));
                }
            }
        }
        else
        {
            setUp(true);
        }
    }

    /**
     * Shuffle method rotates and rearranges tiles -Jay 4/21/2016
     */
    public void shuffle()
    {
        ArrayList<MazeIcon> icons = new ArrayList<MazeIcon>();
        ArrayList<Integer> rotations = new ArrayList<Integer>();
        for (int i = 0; i < tiles.length; i++)
        {
            icons.add(i, tiles[i].getMazeIcon());
            rotations.add(i, 90 * ((i % 4) + 1));
            tiles[i].setMazeIcon(null);
        }
        for (int i = 15; i >= 0; i--)
        {
            Random r = new Random();
            int result = r.nextInt(i + 1);
            tiles[i].setMazeIcon(icons.get(result));
            // Update HashMap during shuffle
            window.coordsToTile.replace(icons.get(result).getLineCoords(), i);
            icons.remove(result);
            if (rotations.get(result) != null)
            {
                tiles[i].rotate(rotations.get(result));
            }
            rotations.remove(result);
        }
    }

    /**
     * Sets up a new tile board depending on whether it's new or reset
     * 
     * @param isNewGame
     * 
     *            - Anna 4/21/2016
     */
    public void setUp(boolean isNewGame)
    {
        // Add the side button panels
        for (int index = 0; index < window.numTiles; index++)
        {
            tiles[index] = new Tile(window, index);
            tiles[index].setIsInGrid(false);
            tiles[index].setMaximumSize(tileDimen);
            tiles[index].setMinimumSize(tileDimen);
            tiles[index].setPreferredSize(tileDimen);
            Insets inset = new Insets(0, 0, 0, 0); // All insets same, removed
                                                   // method call AC 3-23-2016

            this.addActionListener(tiles[index], window);
            if (index < window.numTiles / 2)
            {
                this.addButtons(leftPanel, tiles[index], 1, index, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        inset);
            }
            else
            {
                this.addButtons(rightPanel, tiles[index], 1, index, 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        inset);
            }
            if (isNewGame)
            {
                if (gameType == GameType.BLANK_GAME
                        || allTilesLineCoords.get(index) == null
                        || allTilesLineCoords.get(index).size() == 0)
                {
                    tiles[index].setMazeIcon(null);
                }
                else
                {
                    tiles[index].setMazeIcon(
                            new MazeIcon(allTilesLineCoords.get(index)));
                }

            }
            else
            {
                tiles[index].setMazeIcon(startTiles[index].getMazeIcon());
            }
        }
    }

    public void reset()
    {
        leftPanel.removeAll();
        rightPanel.removeAll();
        setUp(false);
    }

}