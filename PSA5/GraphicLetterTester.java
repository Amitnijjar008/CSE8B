import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Don't forget your comments.
public class GraphicLetterTester
{
  public static void main( String[] args )
  {
    JFrame myFrame = new JFrame();
    // Continue implementing main here.  Note that main is required. 
    GraphicLetter_cs8bey1 letter1 = new GraphicLetter_cs8bey1();//declairs two letters
    GraphicLetter_cs8bei2 letter2 = new GraphicLetter_cs8bei2();
    System.out.println(letter1.whichChar());
    System.out.println(letter2.whichChar());
    
    myFrame.setLayout( new GridLayout( 1, 2 ) );//creates the frame for the letters
    myFrame.setSize( 400, 600 );
    GraphicLetter a1 = letter1.makeCopy();//makes coppies of the two letters
    GraphicLetter a2 = letter2.makeCopy();
    myFrame.add( letter1 );//adds the first letter to the frame 
    myFrame.add( a1 );
    a1.setBackground( new Color( 0, 255, 255 ) );
    
    myFrame.add( letter2 ); //adds the second letter to the frame 
    myFrame.add( a2 );
    a2.setBackground(  new Color( 100, 100, 100 ) );
    
    myFrame.setVisible( true ); //shows the new frame
  }
}