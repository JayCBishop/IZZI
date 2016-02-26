import java.awt.*;

import javax.swing.JPanel;

public abstract class TileArea extends JPanel{
  private Tile tile;
  private boolean blank;
  /**
   * Stupid serializable stuff
   */
  private static final long serialVersionUID = 2L;
  
  public TileArea(){
	  
    //stub for constructor
  }
  
  public void addButtons(Container container, Component component, int gridx,
          int gridy, int gridwidth, int gridheight, int anchor, int fill,
          Insets in) {
      GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
              gridheight, 1.0, 1.0, anchor, fill, in, 0, 0);
      add(component, gbc);
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
