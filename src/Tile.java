/**
 * Software Design, Spring 2016
 * Group G
 * 
 * Created 2/23/2016
 */

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

    public final JButton button = new JButton();
    public Insets inset;

    /**
     * Stupid serializable stuff
     */
    private static final long serialVersionUID = 2L;

    // default constructor -- right now this just creates a button
    public Tile() {
        super();
    }

    public Tile(JPanel frame, int i) {
        super();
        int width = frame.getWidth();
        int height = frame.getHeight();

        switch (i) {
        // Center Grid Pieces
        case 5:
        case 6:
        case 9:
        case 10:
            inset = new Insets(0, 0, 0, 0);
            break;
        // Top Grid Pieces
        case 1:
        case 2:
            inset = new Insets(height / 3, 0, 0, 0);
            break;
        // Bottom Grid Pieces
        case 13:
        case 14:
            inset = new Insets(0, 0, height / 3, 0);
            break;
        // Left Grid Pieces
        case 4:
        case 8:
            inset = new Insets(0, width / 3, 0, 0);
            break;
        // Right Grid Pieces
        case 7:
        case 11:
            inset = new Insets(0, 0, 0, width / 3);
            break;
        // TopLeft Piece
        case 0:
            inset = new Insets(height / 3, width / 3, 0, 0);
            break;
        // TopRight Piece
        case 3:
            inset = new Insets(height / 3, 0, 0, width / 3);
            break;
        // BotLeft Piece
        case 12:
            inset = new Insets(0, width / 3, height / 3, 0);
            break;
        // BotRight Piece
        case 15:
            inset = new Insets(0, 0, height / 3, width / 3);
            break;
        }
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
}
