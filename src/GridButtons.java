
/**
 * GridButton.java
 * Software Design
 * Group G
 * 2/25/2016
 */

import javax.swing.*;
import java.awt.*;

public class GridButtons extends TileArea {

    /**
     * 
     */
    private static final long serialVersionUID = -2508093488142888044L;
    Tile[] buttons = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();

    /**
     * Constructor creates a grid from an array of 16 tiles
     *
     * @param framee
     */

    
    GridButtons(JFrame frame) {
        super();
        GridBagLayout gbl=new GridBagLayout();
        setLayout(gbl);
        
        for (int index = 0; index < 16; index++) {
            buttons[index] = new Tile(this, index);
            Insets inset = buttons[index].getInsets();
            this.addButtons(this, buttons[index], (index % 4),
                    (index / 4), 1, 1, basic.CENTER, basic.BOTH, inset);
        }
    }
}