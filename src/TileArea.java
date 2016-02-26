
/**
 * TileArea.java
 * Software Design
 * Group G
 * 2/26/2016
 * 
 * Contains shared functionality between SideButtons and GridButtons
 */

import java.awt.*;

import javax.swing.JPanel;

public abstract class TileArea extends JPanel {
    private Tile tile;
    private boolean blank;

    private static final long serialVersionUID = 2L;

    protected TileArea() {
        super();
    }

    public void addButtons(Container container, Component component, int gridx,
            int gridy, int gridwidth, int gridheight, int anchor, int fill,
            Insets in) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
                gridheight, 0, 0, anchor, fill, in, 0, 0);
        container.add(component, gbc);
        return;
    }

    public boolean isSpaceBlank() {
        return blank;

    }

    public void getTile() {
        // stub
    }

    public void placeTile() {
        // stub
    }
}