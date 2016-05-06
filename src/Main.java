
/**
  *Additional authors for all files are Group G.  The members
 *are listed below and here-in will be listed as Group G
 * @author- Jay Bishop, Evan Turner, Anna Carrigan, Kyle Bobak,
 *          Debbie Kretzschmar -- 3-21-2016  D.K. 
 * 
 * @author Kim Buckner
 * Date: Feb 19, 2016
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;

public class Main
{

    // Button declarations
    public static JButton fileButton, resetButton, quitButton;

    // Hard-coded file to load
    public static String fileName = "default.mze";

    public static void main(String[] args)
    {
        // This is the play area
        // Named the GameWindow after our group D.K.
        GameWindow game = new GameWindow("Group G aMaze");
        GameType gameType = GameType.ORIGINAL_GAME;

        game.setSize(new Dimension(900, 1000));

        // So the debate here was, do I make the GameWindow object the game
        // or do I make main() the game, manipulating a window?
        // Should GameWindow methods know what they store?
        // Answer is, have the "game" do it.
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().setBackground(Color.cyan);

        // This is where we read in the tiles and draw their images.
        // Anna Carrigan and Kyle Bobak, 4/8/2016

        /**
         * The next section of code opens an input file. The default file must
         * be found just within the project file, not within a sub-folder such
         * as the src folder. For example, if I am using Windows as the OS and a
         * workspace within Eclipse and my project is titled Program5, then my
         * path to the default file would be:
         * C:\Users\DEBBIE\workspace\Program5. The direction of the slashes
         * (forward or backward) may vary based upon the OS you are using. DK
         * 5/2/2016
         */
        File file = new File(fileName);

        ByteFileStreamReader reader;
        try
        {
            reader = new ByteFileStreamReader(file);
        }
        catch (FileNotFoundException e2)
        {
            game.invalFileName(fileName);
            return;
        }

        /**
         * read the first four bytes and determine if it is a played game or not
         * cafebeef means that it is original cafedeed means that it is a saved
         * or played game DK 4/28/16
         */

        int val = reader.readInt();

        if (val == 0xcafebeef)
        {
            gameType = GameType.ORIGINAL_GAME;
            System.out.println("This is an original game");
        }
        else
            if (val == 0xcafedeed)
            {
                gameType = GameType.PLAYED_GAME;
                System.out.println("This is a played game");
            }
            else
            {
                // this notifies the user that the first 4 bytes are bad
                game.alertInvalFileFormat();
                // changing the game type to blank means a totally blank board
                // will
                // be set up
                gameType = GameType.BLANK_GAME;
            }

        if (!(gameType == GameType.BLANK_GAME))
        {
            // Now that we know if the file is original or a played game, we
            // check
            // the number of tiles. DK 4/29/16

            int numberOfTiles = reader.readInt();
            game.numTiles = numberOfTiles;

            // we create an arraylist to store the tile line coordinates for
            // either version
            ArrayList<ArrayList<float[]>> allTilesLineCoords = new ArrayList<ArrayList<float[]>>(
                    numberOfTiles * 2);
            ArrayList<ArrayList<float[]>>solution = new ArrayList <ArrayList<float[]>>(numberOfTiles);
            // HashMap doesn't support primitive types, so use Integer
            HashMap<ArrayList<float[]>, Integer> coordsToTile = new HashMap(numberOfTiles);

            // we also create an arraylist to store the rotation for tiles.
            ArrayList<Integer> rotations = new ArrayList<Integer>(
                    numberOfTiles * 2);
            
            //This is the time in seconds
            long time = reader.readLong();
            System.out.println("The time in seconds is: " + time);
            game.time = time;

            for (int i = 0; i < numberOfTiles * 2; i++)
            {
                allTilesLineCoords.add(null);
                rotations.add(0);
            }
            /**
             * next 4 bytes: an integer tile number, range 0-31....ignored if
             * original next 4 bytes: an integer tile rotation, range
             * 0-3...ignore if original next four bytes: an integer number of
             * lines, M...same for original next 16 bytes, the float coordinate
             * endpoints for the lines...same for original DK 4/29/16
             */

            for (int i = 0; i < numberOfTiles; i++)
            {
                ArrayList<float[]> lineCoords = new ArrayList<float[]>();
                int tileNumber = reader.readInt();
                int tileRotation = reader.readInt();
                // if not original, then we add to ArrayList rotations
                // otherwise rotations will stay null
                if (gameType == GameType.PLAYED_GAME)
                {
                    rotations.add(tileNumber, tileRotation);
                }
                int numLines = reader.readInt();
                for (int j = 0; j < numLines; j++)
                {
                    float[] coords = new float[4];
                    for (int k = 0; k < 4; k++)
                    {
                        coords[k] = reader.readFloat();
                    }
                    lineCoords.add(coords);
                }
                allTilesLineCoords.add(tileNumber, lineCoords);
                solution.add(lineCoords);
                coordsToTile.put(lineCoords, i);
            }

            game.allTilesLineCoords = allTilesLineCoords;
            game.rotations = rotations;
            game.coordsToTile = coordsToTile;
            try
            {
                reader.close();
            }
            catch (IOException e1)
            {
                System.out.println("File not closed.");
                e1.printStackTrace();
            }
        }

        game.setUp(gameType);
        game.setVisible(true);

        try
        {
            // The 4 that installed on Linux here
            // May have to test on Windows boxes to see what is there.
            // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // This is the "Java" or CrossPlatform version and the default
            // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            // Linux only
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException e)
        {
            // handle possible exception
        }
        catch (ClassNotFoundException e)
        {
            // handle possible exception
        }
        catch (InstantiationException e)
        {
            // handle possible exception
        }
        catch (IllegalAccessException e)
        {
            // handle possible exception
        }
    }

};