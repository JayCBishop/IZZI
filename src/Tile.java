/**
 * Software Design, Spring 2016
 * Group G
 * 
 * Created 2/23/2016
 */

package prog01repo.IZZI.src;

import java.awt.Image;

import javax.swing.JButton;

public class Tile extends JButton {

    private boolean drawn;
    private boolean inGrid;
    private Image image;
    
    
    /**
     * Stupid serializable stuff
     */
    private static final long serialVersionUID = 2L;
    
    // default constructor -- right now this just creates a button
    public Tile()
    {
        super();
    }
    
    public void rotate(int numberOfRotations)
    {
        // stub
    }
    
    public void setImage(Image i)
    {
        image = i;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public void setIsDrawn(boolean isDrawn)
    {
        drawn = isDrawn;
    }
    
    public boolean isDrawn()
    {
        return drawn;
    }
    
    public void setInGrid(boolean isInGrid)
    {
        inGrid = isInGrid;
    }
    
    public boolean isInGrid()
    {
        return inGrid;
    }

}
