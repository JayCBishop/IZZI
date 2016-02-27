import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideButtons extends TileArea
{
    private static final long serialVersionUID = -2508093488142888043L;
    public JPanel leftPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    
    Tile[] tiles = new Tile[16];
    GridBagConstraints basic = new GridBagConstraints();
    
    /**
     * Constructor creates both the side panels from two arrays of 8 tiles
     *
     * @param framee
     */

    SideButtons(JFrame frame) {
        GridBagLayout gbl=new GridBagLayout();
        leftPanel.setLayout(gbl);
        rightPanel.setLayout(gbl);
        
        //Add the left SideButtons panel
        for (int index = 0; index < 8; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(leftPanel, tiles[index], 1,
                    index, 1, 1, basic.CENTER, basic.BOTH, inset);
        }
        
        //Add the right SideButtons panel
        for (int index = 8; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(rightPanel, tiles[index], 1,
                    index, 1, 1, basic.CENTER, basic.BOTH, inset);
        }
    
    }
}

