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

public class GridButtons extends TileArea {

    private static final long serialVersionUID = 1;
    Tile[] tiles = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();

    /**
     * Constructor creates a grid from an array of 16 tiles
     *
     * @param frame
     */
    GridButtons(GameWindow window) {
        super();
        GridBagLayout gbl=new GridBagLayout();
        setLayout(gbl);
        
        this.setBackground(Color.PINK);
        
        //Create Gridbuttons in play area
        //Made sure font same size as sideButtons  DK 3-23-2016
        for (int index = 0; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            Insets inset = new Insets(0, 0, 0, 0); // All insets same, removed method call AC 3-23-2016
            tiles[index].setFont(new Font("Arial", Font.PLAIN, 20));
            this.addButtons(this, tiles[index], (index % 4),
                    (index / 4), 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, inset);
            this.addActionListener(tiles[index], window, 0);
        }
    }
}