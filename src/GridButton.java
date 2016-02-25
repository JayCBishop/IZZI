/**
 * GridButton.java
 * Software Design
 * Group G
 * 2/25/2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridButton {
	public final JButton button = new JButton();
	public Insets inset;
	
	/**
	 * A JButton that contains a unique inset
	 *
	 * @param  JFrame:  The Frame to add the button to
	 * @param  i:       The index of the button relative to the rest of the grid
	 */
	GridButton(JFrame frame,int i)
	{
		int width = frame.getWidth();
	    int height = frame.getHeight();
	    
	    switch (i)
	    {
	    // Center Grid Pieces
	    case 5:
	    case 6:
	    case 9:
	    case 10: inset = new Insets(0,0,0,0);
	        break;
	    // Top Grid Pieces
	    case 1:
	    case 2: inset = new Insets(height/3,0,0,0);
	        break;
	    // Bottom Grid Pieces
	    case 13:
	    case 14: inset = new Insets(0,0,height/3,0);
	        break;
	    // Left Grid Pieces
	    case 4: 
	    case 8: inset = new Insets(0,width/3,0,0); 
	        break;
	    // Right Grid Pieces
	    case 7:
	    case 11: inset = new Insets(0,0,0,width/3);
	        break;
	    // TopLeft Piece
	    case 0: inset = new Insets(height/3,width/3,0,0);
            break;
	    // TopRight Piece
	    case 3: inset = new Insets(height/3,0,0,width/3);
	    	break;
	    // BotLeft Piece
	    case 12: inset = new Insets(0,width/3,height/3,0);
	    	break;
	    // BotRight Piece
	    case 15: inset = new Insets(0,0,height/3,width/3);
	    break;  
	    }
	}
}