import java.awt.*;

public abstract class TileArea {
  private Tile tile;
  private boolean blank;
  /**
   * Stupid serializable stuff
   */
  private static final long serialVersionUID = 2L;
  
  public TileArea(){
    //stub for constructor
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
