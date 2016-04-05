
/**
 * Added authors as Group G on 3-21-2016  D.K.
 * Members are listed in Main.java
 * Tile.java
 * Software Design, Spring 2016
 * @author- Group G
 * Created 2/23/2016
 * 
 * Contains functionality used by SideButtons and
 * GridButtons, such as an addButton method.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class TileArea extends JPanel {
	private boolean blank;
	/**
	 * Program needs serialVersion UID D.K.
	 */
	private static final long serialVersionUID = 1;
	private static final Border DEFAULT_BORDER = new JButton().getBorder();
	private static final Border SELECTED_BORDER = new LineBorder(Color.ORANGE, 4);

	protected TileArea() {
		super();
		// stub for constructor
	}

	/**
	 * Adds the button to a container
	 * 
	 * @param container:
	 *            container to place components in
	 * @param component:
	 *            what we are placing in the container
	 * @param gridx:
	 *            the x position relative to the container for the component
	 * @param gridy:
	 *            the y position relative to the container for the component
	 * @param gridWidth:
	 *            the width of the grid
	 * @param gridHeight:
	 *            the height of the grid
	 * @param anchor
	 * @param fill
	 * @param in:
	 *            the inset of the component on the container
	 */
	public void addButtons(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, int anchor, int fill, Insets in) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0, 0, anchor, fill, in, 0,
				0);
		container.add(component, gbc);
		return;
	}

	/**
	 * Adds an actionListener to a tile that controls the clicking on each of
	 * the tiles. If two grid tiles are clicked, or a grid and a side tile is
	 * clicked, the images of the two tiles are swapped
	 * 
	 * @param tile:
	 *            the tile to add an actionListener to
	 * @param window:
	 *            the GameWindow object the tile is a piece of
	 * @param type:
	 *            the type of button that is clicked true is grid, false is side
	 *            -Jay 3/18/2016 (last updated: 3/23/2016) -Evan 3/21/2016
	 */
	public void addActionListener(Tile tile, GameWindow window) {
		tile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Tile is already selected
				if (window.getFirstClicked() != null && tile.isClicked()) {
					deselectTile(window, tile);
				}
				// Second Tile Clicked
				else if (window.getFirstClicked() != null) {
					// Two sideTile selected
					if (!tile.isInGrid() && !window.getFirstClicked().isInGrid()) {
						deselectTile(window, window.getFirstClicked());
						tileInfractionOccured(tile);
					}
					// Both tiles have text
					else if (window.getFirstClicked().getText() != "" && tile.getText() != "") {
						deselectTile(window, window.getFirstClicked());
						tileInfractionOccured(tile);
					}
					// Swap tiles
					else {
						window.setSecondClicked(tile);
						switchTiles(window.getFirstClicked(), window.getSecondClicked());
						deselectTile(window, window.getFirstClicked());
						deselectTile(window, tile);
					}
				}
				// First Tile Clicked
				else {
					if (tile.getText() != "") {
						selectTile(window, tile);
					} else {
						tileInfractionOccured(tile);
					}
				}
			}
		});
	}

	/**
	 * Lights a tile momentarily if an illegal move happens
	 * 
	 * @param tile:
	 *            the tile an infraction occured upon -Jay 3/24/2016
	 */
	public void tileInfractionOccured(Tile tile) {
		tile.setBackground(Color.RED);
		tile.setForeground(Color.WHITE);
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				tile.setBackground(new JButton().getBackground());
				tile.setForeground(Color.BLACK);
			}
		}, 1000);
	}

	/**
	 * Selects a tile
	 * 
	 * @param window
	 * @param tile
	 * @param type
	 *            -Jay 3/22/2016
	 */
	public void selectTile(GameWindow window, Tile tile) {
		window.setFirstClicked(tile);
		tile.setIsClicked(true);
		tile.setBorder(SELECTED_BORDER);
	}

	/**
	 * De-selects a tile
	 * 
	 * @param window
	 * @param tile
	 *            -Jay 3/22/2016
	 */
	public void deselectTile(GameWindow window, Tile tile) {
		window.setFirstClicked(null);
		tile.setBorder(DEFAULT_BORDER);
		tile.setIsClicked(false);
	}

	/**
	 * returns blank if the tile has no image
	 */
	public boolean isSpaceBlank() {
		return blank;
	}

	/**
	 * Swaps attributes of tiles
	 * 
	 * @param firstClicked
	 * @param secondClicked
	 *            -Kyle 3/22/2016
	 */
	private void switchTiles(Tile firstClicked, Tile secondClicked) {
		String temp = firstClicked.getText();
		firstClicked.setText(secondClicked.getText());
		secondClicked.setText(temp);
	}

    
	
	
}
