/**
 * Class GraphicLetter.
 * Author: Amit Nijjar A11489111 & Alex Luu A11632514
 * Date: February 11, 2014
 * Graphic Letter: 'A'
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicLetter_cs8bey1 extends GraphicLetter {
  
  /** Returns the character 'A'.
    * @return The character 'A'.
    * */
  public char whichChar()
  {
    return 'A';
  }
  
  /** Return a new instance of this GraphicLetter object.
    * @return A new instance of this GraphicLetter object.
    * */
  public GraphicLetter makeCopy()
  {
    GraphicLetter copy = new GraphicLetter_cs8bei1();
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
  
  /** Draws the letter 'A'.
    * */
  protected void paintComponent( Graphics g )
  {
    // Call the paintComponent of the JPanel. 
    // Leave this line in when you add to this method.
    
    
    
    super.paintComponent(g);
    
    int[] color1 = new int[3]; //makes int array for 3 random values
    
    for(int x = 0; x < 3; x++){ 
      Random randInt = new Random(); //creating random integers for RGB values
      int r = randInt.nextInt(256);
      color1[x] = r; //sets three random values
    }
    
    Color coltest = new Color(color1[0],color1[1],color1[2]);
    g.setColor(coltest); //setting color block to a random color
    g.fillRect(5,5,this.getWidth()/3, (this.getHeight()*9)/10 ); //makes the 'A' shape
    g.fillRect(5,5,(this.getWidth()*2)/3, (this.getHeight()*2)/10 );
    g.fillRect(5,(this.getHeight()*3)/10,(this.getWidth()*2)/3, (this.getHeight()*1)/10 );
    g.fillRect((this.getWidth()/3)*2,5,this.getWidth()/3, (this.getHeight()*9)/10 );  
  }
}
