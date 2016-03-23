/**
 * Added authors as Group G on 3-21-2016  D.K.
 * Tile.java
 * Software Design, Spring 2016
 * @author - Group G -- see Main.Java for 
 *           members of Group G
 * Created 2/23/2016
 * 
 * A Tile extends JButton but may also contain an
 * image and be rotated. Each tile has a unique inset,
 * index, and button.
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
    
    public boolean isClicked;

    private static final long serialVersionUID = 1;

    // default constructor -- right now this just creates a button
    public Tile() {
      super();
    }

    /**
     * Establishes the initial board
     */
    public Tile(JPanel panel, int i) {
        super();
        this.panel = panel;
        index = i;
        Dimension d = new Dimension(75, 75);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
    }
  

    /**
     * Rotates the tile 90 degrees clockwise
     * 
     * @param numberOfRotations controls
     * how many times the tile is rotated
     */
    public void rotate(int numberOfRotations) {
        // stub
    }
 
    /**
     * Sets the image displayed on the tile
     * 
     * @param i 
     */
    public void setImage(Image i) {
        image = i;
    }

    /**
     * Returns the image on the tile
     */
    public Image getImage() {
        return image;
    }

    /**
     * sets the drawn variable to true or false
     * 
     * @param isDrawn
     */
    public void setIsDrawn(boolean isDrawn) {
        drawn = isDrawn;
    }

    /**
     * returns the boolean value drawn
     */
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

    /**
     * Gets the inset of the tile
     */
    public Insets getTileInsets() {
        int width = panel.getWidth();
        int height = panel.getHeight();     
        inset = new Insets(0, 0, 0, 0);
        return inset;
    }
}