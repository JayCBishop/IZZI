/**
 * Tile.java
 * Software Design, Spring 2016
 * Group G
 * Created 2/23/2016
 * 
 * Two collections of tiles that form two side panels
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideButtons extends TileArea
{
    private static final long serialVersionUID = 1;
    public JPanel leftPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    
    Tile[] tiles = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();
    
    /**
     * Constructor creates both the side panels from two arrays of 8 tiles
     *
     * @param framee
     */
    SideButtons(GameWindow window) {
        GridBagLayout gbl=new GridBagLayout();
        leftPanel.setLayout(gbl);
        rightPanel.setLayout(gbl);
        leftPanel.setBackground(Color.cyan);
        rightPanel.setBackground(Color.cyan);
        
        //Add the left SideButtons panel
        for (int index = 0; index < 8; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(leftPanel, tiles[index], 1,
                    index, 1, 1, basic.CENTER, basic.BOTH, inset);
            this.addActionListener(tiles[index], window, 1);
        }
        
        //Add the right SideButtons panel
        for (int index = 8; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(rightPanel, tiles[index], 1,
                    index, 1, 1, basic.CENTER, basic.BOTH, inset);
            this.addActionListener(tiles[index], window, 1);
        }
    
    }
}

