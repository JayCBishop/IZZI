import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideButtons extends TileArea
{
	private static final long serialVersionUID = -2508093488142888043L;
    Tile[] leftPanel = new Tile[8];
    Tile[] rightPanel = new Tile[8];
    GridBagConstraints basic = new GridBagConstraints();

    SideButtons(JFrame frame) {
        GridBagLayout gbl=new GridBagLayout();
        setLayout(gbl);
        
        //Left Side Panel
        for (int index = 16; index < 24; index++) {
            leftPanel[index - 16] = new Tile(this, index);
            Insets inset = leftPanel[index - 16].inset;
            this.addButtons(this, leftPanel[index - 16].button, (index % 1),
                    (index / 8), 1, 1, basic.CENTER, basic.BOTH, inset);
        }
        
        // Right Side Panel
        for (int index = 24; index < 32; index++) {
            rightPanel[index - 24] = new Tile(this, index);
            Insets inset = rightPanel[index - 24].inset;
            this.addButtons(this, rightPanel[index - 24].button, (index % 1),
                    (index / 8), 1, 1, basic.CENTER, basic.BOTH, inset);
        }
    }


}

