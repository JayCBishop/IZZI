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

    SideButtons(JFrame frame) {
        GridBagLayout gbl=new GridBagLayout();
        leftPanel.setLayout(gbl);
        rightPanel.setLayout(gbl);
        
        for (int index = 0; index < 8; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(leftPanel, tiles[index], 1,
                    index, 1, 1, basic.CENTER, basic.BOTH, inset);
        }
        for (int index = 8; index < 16; index++) {
            tiles[index] = new Tile(this, index);
            tiles[index].setInGrid(false);
            Insets inset = tiles[index].getInsets();
            this.addButtons(rightPanel, tiles[index], 1,
                    index, 1, 1, basic.CENTER, basic.BOTH, inset);
        }
    }

    public void addButtons(Container container, Component component, int gridx,
            int gridy, int gridwidth, int gridheight, int anchor, int fill,
            Insets in) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
                gridheight, 0, 0, anchor, fill, in, 0, 0);
        container.add(component, gbc);
        return;
    }
}

