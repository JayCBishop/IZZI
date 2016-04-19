import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;

/**
 *  The RotatedIcon allows you to change the orientation of an Icon by
 *  rotating the Icon before it is painted. 
 */
public class MazeIcon 
{
   
    private ImageIcon icon;
    private double degreesRotated;
    private Vector<float[]> lineCoords;

    /**
     *  Convenience constructor to create a RotatedIcon that is rotated DOWN.
     *
     *  @param icon  the Icon to rotate
     */
    public MazeIcon(Vector<float[]> lineCoords)
    {
        this(lineCoords, 0);
    }

    /**
     *  Create a Maze Icon
     *
     *  @param icon the Icon to rotate
     *  @param rotate  the direction of rotation
     */
    public MazeIcon(Vector<float[]> lineCoords, double degreesRotated)
    {
        this.degreesRotated = degreesRotated;
        this.lineCoords = lineCoords;
        drawIcon();
    }

    /**
     *  Gets the degrees of rotation. Only used for Rotate.ABOUT_CENTER.
     *
     *  @return the degrees of rotation
     */
    public double getDegreesRotated()
    {
        return degreesRotated;
    }

    /**
     *  Set the degrees of rotation. Only used for Rotate.ABOUT_CENTER.
     *  This method only sets the degrees of rotation, it will not cause
     *  the Icon to be repainted. You must invoke repaint() on any
     *  component using this icon for it to be repainted.
     *
     *  @param degrees the degrees of rotation
     */
    public void setDegreesRotated(double degrees)
    {
        this.degreesRotated = degrees;
        drawIcon();
    }
    
    public void setLineCoords(Vector<float[]> lineCoords)
    {
        this.lineCoords = lineCoords;
    }
    
    public Vector<float[]> getLineCoords()
    {
        return lineCoords;
    }
    
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
            graphic.setStroke(new BasicStroke(1));
            graphic.drawLine((int) coords[0], (int) coords[1],
                    (int) coords[2], (int) coords[3]); // coords for lines
                                                       // being drawn
        }
        
        icon = new ImageIcon(buffImage);
    }
}