
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
import javax.swing.*;
import java.awt.*;

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


		int[] array = new int[100];
		File file = new File("default.mze");
		ByteFileStreamReader reader;
		try {
			reader = new ByteFileStreamReader(file);
			int nextInt = reader.readInt();
			int index = 0;
			while(nextInt != -1 && index < 100)
			{
				array[index] = nextInt;
				index++;
				nextInt = reader.readInt();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(0);
			
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
            // really old style Motif
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
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