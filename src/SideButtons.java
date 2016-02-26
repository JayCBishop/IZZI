import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideButtons extends JPanel
{
	private static final long serialVersionUID = -2508093488142888043L;
    Tile[] leftPanel = new Tile[8];
    Tile[] rightPanel = new Tile[8];
    GridBagConstraints basic = new GridBagConstraints();

    SideButtons(JFrame frame) {
        GridBagLayout gbl=new GridBagLayout();
        setLayout(gbl);
        for (int index = 0; index < 8; index++) {
            leftPanel[index] = new Tile(this, index);
            Insets inset = leftPanel[index].inset;
            this.addButtons(this, leftPanel[index].button, (index % 1),
                    (index / 8), 1, 1, basic.CENTER, basic.BOTH, inset);
        }
        for (int index = 0; index < 8; index++) {
            rightPanel[index] = new Tile(this, index);
            Insets inset = rightPanel[index].inset;
            this.addButtons(this, rightPanel[index].button, (index % 1),
                    (index / 8), 1, 1, basic.CENTER, basic.BOTH, inset);
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

