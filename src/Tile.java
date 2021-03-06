
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
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Tile extends JButton
{
    private boolean drawn;
    private boolean inGrid;
    private MazeIcon icon;
    private boolean isClicked;
    
    // The actual tileNumber the tile is currently on
    private int tileNumber;
    
    // The tiles current rotation
    private int rotation;

    private static final long serialVersionUID = 1;

    // Added font "styles" to constructor for all tiles instead of
    // individually in SideButtons and GridButtons
    // This way when a change is needed, it can be done in
    // one place instead of three different areas. DK 3-23-2016
    public Tile(GameWindow window, int number)
    {
        super();
        Dimension d = new Dimension(75, 75);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.tileNumber = number;

        // Right click listener that rotates each tile
        // -Jay 4/19/2016
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    rotate(90);
                    // Checks if they just found the solution.
                    if(window.getGrid().isSolution())
                    {
                        window.gameWon();
                    }
                }
                if(window.startTime == 0)
                {
                    window.startTimer();
                }
            }
        });
    }

    /**
     * Rotates the tile 90 degrees clockwise when the user right clicks.
     * parameter changed from degreesRotated to degrees to avoid confusing with
     * degreesRotated used in MazeIcon. - DK 4/22/2016
     * 
     * @param degrees
     *            -Evan 4/17/2016 -Jay 4/19/2016
     */
    public void rotate(int degrees)
    {
        if (icon != null)
        {
            icon = new MazeIcon(icon.getLineCoords(),
                    (degrees + icon.getDegreesRotated()) % 360);
            setIcon(icon.getImageIcon());
            setRotation(((int) (icon.getDegreesRotated() % 360))/90);
        }
        invalidate();
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
     * Gets the click state of tile
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

    /**
     * Sets the icon of the tile
     * @param maze icon
     */
    public void setMazeIcon(MazeIcon icon)
    {
        this.icon = icon;
        if (icon != null)
        {
            setIcon(icon.getImageIcon());
            setIsDrawn(true);
            if (inGrid)
            {
                setBorder(null);
            }
        }
        else
        {
            setIcon(null);
            setIsDrawn(false);
        }

        invalidate();
        revalidate();
        repaint();
    }

    /**
     * Returns the maze icon
     * @return
     */
    public MazeIcon getMazeIcon()
    {
        return icon;
    }

    /**
     * Returns the number of the tile (where it is in the game)
     * @return
     */
    public int getTileNumber()
    {
        return tileNumber;
    }
    
    /**
     * Sets the rotation of the tile
     * @param myRotation
     * @author jaycg
     */
    public void setRotation(int myRotation)
    {
        rotation = myRotation;
    }
    
    /**
     * Gets the tiles current rotation
     * @return
     * @author jaycg
     */
    public int getRotation()
    {
        return rotation;
    }
}