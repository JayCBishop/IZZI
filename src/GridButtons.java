
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

public class GridButtons extends TileArea
{

    private static final long serialVersionUID = 1;
    private Tile[] tiles = new Tile[16];
    private Dimension tileDimen = new Dimension(100,100);

    /**
     * Constructor creates a grid from an array of 16 tiles
     *
     * @param frame
     */
    GridButtons(GameWindow window)
    {
        super();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        this.setBackground(Color.PINK);

        // Create Gridbuttons in play area
        // Placed font size in Tile class since all Tiles have uniform font size
        // DK 3-23-2016
        for (int index = 0; index < 16; index++)
        {
            tiles[index] = new Tile();
            tiles[index].setIsInGrid(true);
            tiles[index].setMaximumSize(tileDimen);
            tiles[index].setMinimumSize(tileDimen);
            tiles[index].setPreferredSize(tileDimen);
            tiles[index].setBorder(null);
            Insets inset = new Insets(0, 0, 0, 0); // All insets same, removed
            // method call AC 3-23-2016
            this.addButtons(this, tiles[index], (index % 4), (index / 4), 1, 1,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, inset);
            this.addActionListener(tiles[index], window);
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