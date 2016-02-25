/**
 * Software Design, Spring 2016
 * Group G
 * 
 * Created 2/23/2016
 */


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Tile extends JButton implements ActionListener {

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


    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Do whatever needs to be done when the tile is clicked
    }
}
