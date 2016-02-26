/**
 * GridButtons.java
 * Software Design
 * Group G
 * 2/26/2016
 * 
 * Contains the tiles that populate the grid
 */

import javax.swing.*;
import java.awt.*;

public class GridButtons extends TileArea {

    private boolean isSolution = false;

    private static final long serialVersionUID = -25L;
    Tile[] tiles = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();

    GridButtons(JFrame frame) {
        super();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        // Add our tiles to the grid
        for (int index = 0; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            Insets inset = tiles[index].getInsets();
            this.addButtons(this, tiles[index], (index % 4), (index / 4), 1, 1,
                    basic.CENTER, basic.BOTH, inset);
        }
    }

    public boolean checkSolution() {
        return isSolution;
    }
}