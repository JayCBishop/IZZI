
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton implements ActionListener
{

    private boolean drawn;
    private boolean inGrid;
    private Image image;
    private boolean isClicked;

    private static final long serialVersionUID = 1;

    /**
     * Establishes the initial board
     */
    // Added font "styles" to constructor for all tiles instead of
    // individually in SideButtons and GridButtons
    // This way when a change is needed, it can be done in
    // one place instead of three different areas. DK 3-23-2016
    public Tile()
    {
        super();
        Dimension d = new Dimension(75, 75);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               if (e.getButton() == MouseEvent.BUTTON3) {
                  rotate(1);
               }
            }
         });
    }

    /**
     * Rotates the tile 90 degrees clockwise
     * 
     * @param numberOfRotations
     *            controls how many times the tile is rotated
     */
    public void rotate(int numberOfRotations)
    {
        System.out.println("Rotated");
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(Color.BLACK);
        this.getIcon().paintIcon(this, g2d, 0, 0);
        g2d.drawLine(1, 1, 99, 99);
        //    g2d.rotate(Math.toRadians(90)); // Broken

        g2d.transform(AffineTransform.getQuadrantRotateInstance(1));
        g2d.drawImage(bi, null, null);
        g2d.dispose();
        try {
            ImageIO.write(bi, "png", new File("test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIcon(new ImageIcon(bi));
    }

    /**
     * Sets the image displayed on the tile
     * 
     * @param i
     */
    public void setImage(Image i)
    {
        image = i;
    }

    /**
     * Returns the image on the tile
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * sets the drawn variable to true or false
     * 
     * @param isDrawn
     */
    public void setIsDrawn(boolean isDrawn)
    {
        drawn = isDrawn;
    }

    /**
     * returns the boolean value drawn
     */
    public boolean isDrawn()
    {
        return drawn;
    }

    /**
     * sets whether the tile is in the grid or not
     * 
     * @param isInGrid
     * 
     *            - Anna 3/25/2016
     */
    public void setIsInGrid(boolean isInGrid)
    {
        inGrid = isInGrid;
    }

    /**
     * returns whether the tile is in the grid or not
     * 
     * @return isInGrid
     * 
     *         - Anna 3/25/2016
     */
    public boolean isInGrid()
    {
        return inGrid;
    }

    /**
     * Stub for double click and other events that happen just to single tile
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // stub
    }

    /**
     * Sets the click state of tile
     * 
     * @param clicked
     * 
     *            - Anna 3/25/16
     */
    public void setIsClicked(boolean clicked)
    {
        isClicked = clicked;
    }

    /**
     * Gets the click state of tiel
     * 
     *
     * @return isClicked
     * 
     *         - Anna 3/25/16
     */
    public boolean isClicked()
    {
        return isClicked;
    }
}


