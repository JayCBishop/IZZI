/**
 * SideButtons.java
 * Software Design
 * Group G
 * 2/26/2016
 * 
 * Contains the side slots for the tiles
 */

import javax.swing.*;
import java.awt.*;

public class SideButtons extends TileArea {
    private static final long serialVersionUID = -3L;
    public JPanel leftPanel = new JPanel();
    public JPanel rightPanel = new JPanel();

    Tile[] tiles = new Tile[16];

    GridBagConstraints basic = new GridBagConstraints();

    SideButtons(JFrame frame) {
        GridBagLayout gbl = new GridBagLayout();
        leftPanel.setLayout(gbl);
        rightPanel.setLayout(gbl);

        // Add the tiles with their insets to the appropriate panel

        for (int index = 0; index < 8; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(leftPanel, tiles[index], 1, index, 1, 1,
                    basic.CENTER, basic.BOTH, inset);
        }
        for (int index = 8; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(rightPanel, tiles[index], 1, index, 1, 1,
                    basic.CENTER, basic.BOTH, inset);
        }
    }

    public void shuffle() {
        // stub
    }
}