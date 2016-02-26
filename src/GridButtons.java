
/**
 * GridButton.java
 * Software Design
 * Group G
 * 2/25/2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridButtons extends TileArea {

    /**
     * 
     */
    private static final long serialVersionUID = -2508093488142888044L;
    Tile[] buttons = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();

    GridButtons(JFrame frame) {
        GridBagLayout gbl=new GridBagLayout();
        setLayout(gbl);
        for (int index = 0; index < 16; index++) {
            buttons[index] = new Tile(this, index);
            Insets inset = buttons[index].inset;
            this.addButtons(this, buttons[index].button, (index % 4),
                    (index / 4), 1, 1, basic.CENTER, basic.BOTH, inset);
        }

    }

    public void addButtons(Container container, Component component, int gridx,
            int gridy, int gridwidth, int gridheight, int anchor, int fill,
            Insets in) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
                gridheight, 1.0, 1.0, anchor, fill, in, 0, 0);
        add(component, gbc);
        return;
    }

}