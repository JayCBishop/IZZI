
/**
 * Software Design, Spring 2016
 * Group G
 * 
 * Created 2/23/2016
 */

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tile extends JButton implements ActionListener {

    private boolean drawn;
    private boolean inGrid;
    private Image image;
    private JPanel panel;

    public final JButton button = new JButton();
    public Insets inset;
    private int index;

    /**
     * Stupid serializable stuff
     */
    private static final long serialVersionUID = 2L;

    // default constructor -- right now this just creates a button
    public Tile() {
        super();
    }

    public Tile(JPanel panel, int i) {
        super();
        this.panel = panel;
        index = i;
        Dimension d = new Dimension(75, 75);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
    }

    public void rotate(int numberOfRotations) {
        // stub
    }

    public void setImage(Image i) {
        image = i;
    }

    public Image getImage() {
        return image;
    }

    public void setIsDrawn(boolean isDrawn) {
        drawn = isDrawn;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setInGrid(boolean isInGrid) {
        inGrid = isInGrid;
    }

    public boolean isInGrid() {
        return inGrid;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Do whatever needs to be done when the tile is clicked
    }

    public Insets getTileInsets() {
        int width = panel.getWidth();
        int height = panel.getHeight();     
        inset = new Insets(0, 0, 0, 0);
        return inset;
    }
}
