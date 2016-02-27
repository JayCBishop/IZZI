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
   * @param container: the 
   */
  public void addButtons(Container container, Component component, int gridx,
          int gridy, int gridwidth, int gridheight, int anchor, int fill,
          Insets in) {
      GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
              gridheight, 0, 0, anchor, fill, in, 0, 0);
      container.add(component, gbc);
      return;
  }

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
