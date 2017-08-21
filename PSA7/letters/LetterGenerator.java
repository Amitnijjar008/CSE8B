/* Name: Amit Nijjar A11489111
 * PSA 7
 * Date: 2/27/14
 */

import javax.swing.*;
import javax.swing.event.*;
import java.*;
import java.awt.*;
import java.awt.event.*;


public class LetterGenerator extends JApplet {
  // instance variables to reflect what is typed
  private JLabel message;
  private JTextField inputText;    
  private String text = new String();
  /** The panel that will hold the graphical letters */
  private JPanel canvas; 
  private JPanel input;
  /** The letter factory that generates the graphical letters */
  private GraphicLetterFactory factory;
  
  /**
   * Initialize the applet. Instantiate all the class variables.
   * This is like the constructor for a normal class.
   * You will want to add to this method.
   */
  public void init() {
    
    message = new JLabel("Type what you want in the text field below!");
    message.setFont(new Font("SansSerif",Font.PLAIN,42));
    
    inputText = new JTextField(); //makes a text field for the user to use
    inputText.addKeyListener(new MessageListener() );//makes a key listener to see the user's inputs
    
    JButton clearButton = new JButton( "Clear" );//sets up clear button for the program
    ClearListener clearCanvas = new ClearListener();
    clearButton.addMouseListener(clearCanvas);//clears the JPanel
    
    input = new JPanel(); //makes a JPanel for the Button and the Text Field
    input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
    input.add( inputText, BorderLayout.SOUTH );
    input.add( clearButton, BorderLayout.NORTH );    
    
    canvas = new JPanel(); //makes a JPanel for the graphic letters to add onto
    
    // Initialize the letter factory.
    factory = new GraphicLetterFactory();
    
    canvas.setLayout(new FlowLayout(FlowLayout.LEFT));
        
    // add everything, set the starting size.  This size will change when we set
    // the size in the index.html file.
    setLayout( new BorderLayout() );
    this.setSize(new Dimension(800,500));
    add( message, BorderLayout.CENTER ); //adds the message to the applet
    add( input, BorderLayout.SOUTH); //adds the button and the Text field to applet
    
    setVisible( true );
    
  }
  
  public class MessageListener implements KeyListener{
    public void keyPressed(KeyEvent a){}//sets up the listener class
    public void keyReleased(KeyEvent a){}
    public void keyTyped(KeyEvent a){
      remove( message);
      add( canvas, BorderLayout.CENTER); //adds the canvas to the applet
      GraphicLetterFactory c = new GraphicLetterFactory();
      JPanel tempPanel = new JPanel(); //creates a temporary JPanel to recieve the returned JPanel
      JPanel letterFigure = new JPanel();
      tempPanel = factory.getRandomCharacter(a.getKeyChar()); //returns a JPanel
      letterFigure.add(tempPanel, BorderLayout.CENTER);
      canvas.add( letterFigure, BorderLayout.CENTER );//adds the JPanel to the canvas
      canvas.revalidate();
      
    }
    
  }
  
  class ClearListener implements MouseListener
  {
    //created to clear the object
    public void mouseClicked( MouseEvent e ) 
    {
      canvas.removeAll(); //removes everything from the JPanel canvas
      canvas.repaint();
      inputText.setText(""); //clears the text field
      canvas.revalidate();
    }
    
    public void mousePressed( MouseEvent e ) {}
    public void mouseReleased( MouseEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
    public void mouseExited( MouseEvent e ) {}
  }
  
}
