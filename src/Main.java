
/**
 *Additional authors for all files are Group G.  The members
 *are listed below and here-in will be listed as Group G
 * @author- Jay Bishop, Evan Turner, Anna Carrigan, Kyle Bobak,
 *          Debbie Kretzschmar -- 3-21-2016  D.K. 
 * 
 * @author Kim Buckner
 * Date: Feb 19, 2016
 *
 * A starting point for the COSC 3011 programming assignment
 * Probably need to fix a bunch of stuff, but this compiles and runs.
 *

 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Main
{

    // Probably should declare any buttons here
    public static JButton newGameButton, resetButton, quitButton;

    public static void main(String[] args)
    {
        // This is the play area
        // Named the GameWindow after our group D.K.
        GameWindow game = new GameWindow("Group G aMaze");

        // have to override the default layout to reposition things!!!!!!!

        game.setSize(new Dimension(900, 1000));

        // So the debate here was, do I make the GameWindow object the game
        // or do I make main() the game, manipulating a window?
        // Should GameWindow methods know what they store?
        // Answer is, have the "game" do it.
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().setBackground(Color.cyan);

        // This is where we read in the tiles and draw their images.
        // Anna Carrigan and Kyle Bobak, 4/8/2016

        File file = new File("default.mze");
        ByteFileStreamReader reader;

        try
        {
            reader = new ByteFileStreamReader(file);
        } catch (FileNotFoundException e2)
        {
            System.out.println("File not found!");
            e2.printStackTrace();
            return;
        }
        int numberOfTiles = reader.readInt();
        BufferedImage[] allImages = new BufferedImage[numberOfTiles];

        for (int i = 0; i < numberOfTiles; i++)
        {
            BufferedImage buffImage = new BufferedImage(100, 100,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = buffImage.createGraphics();
            graphic.setColor(Color.WHITE);
            graphic.fillRect(0, 0, 100, 100);
            graphic.setColor(Color.BLACK);
            int tileNumber = reader.readInt();
            int numLines = reader.readInt();
            for (int j = 0; j < numLines; j++)
            {
                float[] coords = new float[4];
                for (int k = 0; k < 4; k++)
                {
                    coords[k] = reader.readFloat();
                }
                graphic.rotate(Math.toRadians(90), 50, 50);
                graphic.transform(new AffineTransform(1.0, 0, 0, 1.0, 0, 0));
                graphic.setStroke(new BasicStroke(1));
                graphic.drawLine((int) coords[0], (int) coords[1],
                        (int) coords[2], (int) coords[3]); // coords for lines
                                                           // being drawn
                //graphic.rotate(Math.toRadians(90));
            }
            allImages[tileNumber] = buffImage;
        }

        game.images = allImages;

        try
        {
            reader.close();
        } catch (IOException e1)
        {
            System.out.println("File not closed.");
            e1.printStackTrace();
        }

        game.setUp();

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
        } catch (UnsupportedLookAndFeelException e)
        {
            // handle possible exception
        } catch (ClassNotFoundException e)
        {
            // handle possible exception
        } catch (InstantiationException e)
        {
            // handle possible exception
        } catch (IllegalAccessException e)
        {
            // handle possible exception
        }
    }

};