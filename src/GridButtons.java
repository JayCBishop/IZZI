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
    private Tile[] tiles = new Tile[16];
    private Dimension tileDimen = new Dimension(99,99);
    private ArrayList<ArrayList<float[]>> allTilesLineCoords;
    private ArrayList<Integer> rotations;
    private GameWindow window;
    private SideButtons side;
   

    /**
     * Constructor creates a grid from an array of 16 tiles
     *
     * @param frame
     */
    GridButtons(GameWindow window,ArrayList<ArrayList<float[]>> allTilesLineCoords, ArrayList<Integer> rotations )
    {
        super();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        this.setBackground(Color.PINK);

        // Create Gridbuttons in play area
        // Placed font size in Tile class since all Tiles have uniform font size
        // DK 3-23-2016
        
        //if rotations is empty, this means it is an original game
        // set everything up as previous
        if(rotations.isEmpty())
        {
            for (int index = 0; index < 16; index++)
            {
                tiles[index] = new Tile(window);
                tiles[index].setIsInGrid(true);
                tiles[index].setMaximumSize(tileDimen);
                tiles[index].setMinimumSize(tileDimen);
                tiles[index].setPreferredSize(tileDimen);
                Insets inset = new Insets(0, 0, 0, 0); // All insets same, removed
                // method call AC 3-23-2016
                this.addButtons(this, tiles[index], (index % 4), (index / 4), 1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, inset);
                this.addActionListener(tiles[index], window);
            }
        }
        else
        {
            this.allTilesLineCoords = allTilesLineCoords;
            this.window = window;
            this.rotations = rotations;

            side.setUp(false);
            //this branch does not have the shuffle method
            //started at index 16 because this is where the grid starts DK 4/28/16
            for (int i = 16; i < 32; i++)
            {
                tiles[i] = new Tile(window);
                tiles[i].setMazeIcon(tiles[i].getMazeIcon());
                //rotate the tile we created by 90 * whatever the integer number is 
                //stored in the rotations array we passed in. 
                tiles[i].rotate(rotations.get(i) * 90);
            }
                
                
           
        }
    }

    /**
     * return whether this is a solution or not
     * 
     * @return boolean
     */
    public boolean isSolution()
    {
        // stub
        return false;
    }

}