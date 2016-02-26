import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SideButton 
{

			public final JButton button = new JButton();
			public Insets inset;
			
			SideButton(JFrame frame,int i)
			{
				int width = frame.getWidth();
			    int height = frame.getHeight();
			    
			    switch (i)
			    {
			    	//Top tile of left side
			    case 0: inset = new Insets((int)(height/1.5),(int)(width/1.5),0,0);
		            break;
		            //Bottom tile of left side
			    case 7: inset = new Insets(0,(int)(width/1.5),(int)(height/1.5),0);
			    	break;
			    	//Top tile of right side
			    case 8: inset = new Insets(0,0,0,0);
			    	break;
			    	// Bottom ties of right side
			    case 15: inset = new Insets(0,0,0,0);
			    break;
			    
			    //Middle tiles of left side
			    case 1:
			    case 2: 
			    case 3: 
			    case 4: 
			    case 5:
			    case 6: inset = new Insets(50,(int)(width/1.5),50,0);
			    	break;
			    
			    //Middle tiles of right side
			    case 9:
			    case 10: 
			    case 11: 
			    case 12: 
			    case 13:
			    case 14: inset = new Insets(0,0,0,0);
		        break;
			       
			    }
			}
		}

