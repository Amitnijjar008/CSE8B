/**
 * Class GraphicLetter.
 * Author: Amit Nijjar A11489111 & Alex Luu A11632514
 * Date: February 11, 2014
 * Graphic Letter: 'l'
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicLetter_cs8bei2 extends GraphicLetter {
  
  /** Returns the character 'L'.
    * @return The character 'L'.
    * */
  public char whichChar()
  {
    return 'L';
  }
  
  /** Return a new instance of this GraphicLetter object.
    * @return A new instance of this GraphicLetter object.
    * */
  public GraphicLetter makeCopy()
  {
    GraphicLetter copy = new GraphicLetter_cs8bei2();
    return copy;
  }
  
  /** Return a preferred dimension of 100 by 100.
    * @return A preferred dimension of 100 by 100.
    * */
  public Dimension getPreferredSize()
  {
    Dimension preferred = new Dimension(100, 100);
    return preferred;
  }
  
  /** Draws the letter 'L'.
    * */
  protected void paintComponent( Graphics g )
  {
    // Call the paintComponent of the JPanel. 
    // Leave this line in when you add to this method.
    super.paintComponent(g);
    
    Random randInt = new Random(); //creating random integers for RGB values
    int r = randInt.nextInt(256);
    int gr = randInt.nextInt(256);
    int b = randInt.nextInt(256); 
    Color coltest = new Color(r, gr, b); //the random color
    
    g.setColor(coltest); //setting this block to that random color
    g.fillRoundRect( 0 , 0 , (this.getWidth()/5) , this.getHeight() , 25 , 25 ); 
    
    r = randInt.nextInt(256);
    gr = randInt.nextInt(256);
    b = randInt.nextInt(256); 
    coltest = new Color(r, gr, b); //another random color
    
    g.setColor(coltest);
    g.fillRoundRect( 0 , this.getHeight() - (this.getWidth()/8), this.getWidth() , (this.getWidth()/5) , 25 , 25 ); 
  } 
}
