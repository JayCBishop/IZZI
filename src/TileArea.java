/**
 * Tile.java
 * Software Design, Spring 2016
 * Group G
 * Created 2/23/2016
 * 
 * Contains functionality used by SideButtons and
 * GridButtons, such as an addButton method.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public abstract class TileArea extends JPanel{
  private Tile tile;
  private boolean blank;
  /**
   * Stupid serializable stuff
   */
  private static final long serialVersionUID = 1;
  private static final Border DEFAULT_BORDER = new JButton().getBorder();
  private static final Border SELECTED_BORDER = new LineBorder(Color.ORANGE, 4);
  
  protected TileArea(){
	  super();
    //stub for constructor
  }
  
  /**
   * Adds the button to a container
   * 
   * @param container: container to place components in
   * @param component: what we are placing in the container
   * @param gridx: the x position relative to the container
   *               for the component
   * @param gridy: the y position relative to the container
   *               for the component
   * @param gridWidth: the width of the grid
   * @param gridHeight: the height of the grid
   * @param anchor
   * @param fill
   * @param in: the inset of the component on the container
   */
  public void addButtons(Container container, Component component, int gridx,
          int gridy, int gridwidth, int gridheight, int anchor, int fill,
          Insets in) {
      GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
              gridheight, 0, 0, anchor, fill, in, 0, 0);
      container.add(component, gbc);
      return;
  }
  
  /**
   * Adds an actionListener to a tile
   * 
   * @param tile: the tile to add an actionListener to
   * @param window: the GameWindow object the tile is a piece of
   * @param type: the type of button that is clicked
   */
  public void addActionListener(Tile tile, GameWindow window, int type)
  {
	  tile.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent evt) {
			  if(type == 0)
			  {
				  if(window.gridClicked != null)
				  {
					  System.out.println("A Grid Tile is already selected");
				  }
				  else if(window.sideClicked != null)
				  {
					  System.out.println("Move images of the selected tiles");
					  
					  window.sideClicked.isClicked = false;
					  tile.isClicked = false;
					  window.sideClicked.setBorder(DEFAULT_BORDER);
					  window.sideClicked = null;
					  window.gridClicked = null;
				  }
				  else
				  {
					  window.gridClicked = tile;
					  tile.isClicked = true;
					  tile.setBorder(SELECTED_BORDER);
					  System.out.println("Grid Tile Clicked");
				  }
			  }
			  else
			  {
				  if(window.sideClicked != null)
				  {
					  System.out.println("A Side Tile is already selected");
				  }
				  else if(window.gridClicked != null)
				  {
					  System.out.println("Move images of the selected tiles");
					  
					  window.gridClicked.isClicked = false;
					  tile.isClicked = false;
					  window.gridClicked.setBorder(DEFAULT_BORDER);
					  window.sideClicked = null;
					  window.gridClicked = null;
				  }
				  else
				  {
					  window.sideClicked = tile;
					  tile.isClicked = true;
					  tile.setBorder(SELECTED_BORDER);
					  System.out.println("Side Tile Clicked");
				  }
			  }
		  }
	  });
  }

  /**
   * returns blank if the tile has no image
   */
  public boolean isSpaceBlank(){
    return blank;
    
  }
  
  public void getTile(){
    //stub
  }
  
  public void placeTile(){
    //stub
  }
}
