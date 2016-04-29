/**
 * Members are listed in Main.java
 * Software Design, Spring 2016
 * @author- Group G
 * Created 4/7/2016
 * 
 * A class to organize rotation of ImageIcons for our tiles
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 *  The RotatedIcon allows you to change the orientation of an Icon by
 *  rotating the Icon before it is painted. 
 */
public class MazeIcon 
{
   
    private ImageIcon icon;
    private double degreesRotated;
    private ArrayList<float[]> lineCoords;

    /**
     *  Convenience constructor to create a RotatedIcon that is rotated DOWN.
     *
     *  @param icon the icon to rotate
     */
    public MazeIcon(ArrayList<float[]> lineCoords)
    {
        this(lineCoords, 0);
    }

    /**
     *  Create a Maze Icon
     *
     *  @param icon the Icon to rotate
     *  @param rotate  the direction of rotation
     */
    public MazeIcon(ArrayList<float[]> lineCoords, double degreesRotated)
    {
        this.degreesRotated = degreesRotated;
        this.lineCoords = lineCoords;
        drawIcon();
    }

    /**
     *  Gets the degrees of rotation
     *
     *  @return the degrees of rotation
     */
    public double getDegreesRotated()
    {
        return degreesRotated;
    }

    /**
     *  Set the degrees of rotation. 
     *
     *  @param degrees the degrees of rotation
     */
    public void setDegreesRotated(double degrees)
    {
        this.degreesRotated = degrees;
        drawIcon();
    }
    
    /**
     * sets the line coordinates
     * @param lineCoords
     */
    public void setLineCoords(ArrayList<float[]> lineCoords)
    {
        this.lineCoords = lineCoords;
    }
    
    /**
     * returns the line coordinates
     * @return an array list of line coordinates
     */
    public ArrayList<float[]> getLineCoords()
    {
        return lineCoords;
    }
    
    /**
     * returns the actual icon
     * @return the tile's icon
     */
    public ImageIcon getImageIcon()
    {
        drawIcon();
        return icon;
    }


   /**
    *  Draw the icon with the correct rotation
    */
    public void drawIcon()
    {
        
        BufferedImage buffImage = new BufferedImage(100, 100,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = buffImage.createGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0, 0, 100, 100);
        graphic.setColor(Color.BLACK);
        graphic.rotate(Math.toRadians(degreesRotated), 50, 50);

        int numLines = lineCoords.size();
        for (int j = 0; j < numLines; j++)
        {
            float[] coords = lineCoords.get(j);
            graphic.setStroke(new BasicStroke(3));
            Line2D line = new Line2D.Float(coords[0], coords[1], coords[2], coords[3]);
            graphic.draw(line); 
                                                       
        }
        
        icon = new ImageIcon(buffImage);
    }
}