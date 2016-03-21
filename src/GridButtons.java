
/**
 * Tile.java
 * Software Design, Spring 2016
 * Group G
 * Created 2/23/2016
 * 
 * Is a collection of multiple tiles that form a grid
 */

import javax.swing.*;
import java.awt.*;

public class GridButtons extends TileArea {

    private static final long serialVersionUID = 1;
    Tile[] tiles = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();

    /**
     * Constructor creates a grid from an array of 16 tiles
     *
     * @param framee
     */
    GridButtons(GameWindow window) {
        super();
        GridBagLayout gbl=new GridBagLayout();
        setLayout(gbl);
        
        this.setBackground(Color.PINK);
        
        for (int index = 0; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            Insets inset = tiles[index].getTileInsets();
            this.addButtons(this, tiles[index], (index % 4),
                    (index / 4), 1, 1, basic.CENTER, basic.BOTH, inset);
            this.addActionListener(tiles[index], window, 0);
        }
    }
}