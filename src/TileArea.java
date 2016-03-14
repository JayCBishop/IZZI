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
import javax.swing.JPanel;

public abstract class TileArea extends JPanel{
  private Tile tile;
  private boolean blank;
  /**
   * Stupid serializable stuff
   */
  private static final long serialVersionUID = 2L;
  
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
   */
  public void addActionListener(Tile tile)
  {
	  tile.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent evt) {
			  System.out.println("click");
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
