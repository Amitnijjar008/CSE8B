/**
 * Name: Alex Luu A1162514 & Amit Nijjar A11489111
 * Date: 2/20/14
 * PSA6
 *
 * You should answer these questions BEFORE you start coding:
 * 
 *   1. To what type of object will you add an instance of a PlayListener object (as a MouseListener)?
 *      -- To an MouseEvent object.
 *   2. If your board has column w and row h, how many BoardCell objects do you need to create? 
 *      -- x = h*w and x is the number of BoardCell objects
 *      To which component will you add these BoardCell objects?
 *      -- the JPanel
 *   3. Why is the JLabel status an instance variable (as opposed to just a local variable 
 *      in the constructor)?
 *      -- so that it can be changed outside of the class
 *   4. Where is the information about what contents are stored in each cell located?  
 *      -- it stores the information in the board double int array
 *      What method must the BoardCell call in its paintComponent method to determine what 
 *      color to paint the "checker"?
 *      -- getContents
 *   5. Which method will determine when the game is over (by calling methods on the 
 *      ConnectFourBoard object theBoard)? 
 *      -- winsFor
 *      Which method will detect illegal moves (again by calling methods on the ConnectFourBoard object theBoard)?
 *      -- allowsMove
 *   6. Will you need to create a separate listener to handle clicks on the New Game button, 
 *      or will you use another instance of the PlayListener class?
 *      -- yes, we will make create a separate listener to handle clicks on the new game class
 *   7. How do you run the game?
 *      -- java ConnectFourSolution and then press new game
 *
 * 
 **/


// import all the necessary built-in java stuff
import javax.swing.*;  // For swing classes (the "J" classes)
import java.awt.*;     // For awt classes (e.g., Dimension)
import java.awt.event.*; // For events (which you will implement)

/**
 * A class that implements a graphical version of connect 4.  Don't forget to 
 * complete your header comment with your name and the date and the PSA.
 */
public class ConnectFour extends JFrame {
  
  /** The underlying board that will hold the state of the game */
  private ConnectFourBoard theBoard;

  /** Whose turn it is.  We use 'X' and 'O', but we will translate 'X's and 
    * 'O's into colors to display them (I use blue and red in my example,
    * but you can use any two colors you like).  */
  protected char turn;
  
  /** The status message at the top of the window */
  private JLabel status;
  
  /** Create a new Connect 4 game that is 7x6.  */
  public ConnectFour()
  {
    this( 7, 6 );
  }

  /** Create a new ConnectFour game with the specified row and column */
  public ConnectFour( int column, int row )
  {
    // X starts
    this.turn = 'X';
    
    // Make a new underlying board.
    this.theBoard = new ConnectFourBoard(column, row);
    
    // The reset button.  It doesn't do anything YET.
    // You'll need to define and add the appropriate listener to it.
    JButton jbtReset = new JButton( "New Game" );

     ResetListener reset = new ResetListener();
        jbtReset.addMouseListener (reset);
    
    // This message will always display the current status
    // of the game (e.g., whose turn it is, whether the game is 
    // over, who won, etc).  Feel free to change the initial message.
    this.status = new JLabel( "Welcome to Connect 4!" );
    
    // This is the panel that will hold the BoardCells. 
    // You will need to populate it.  I suggest writing a helper
    // method to create the BoardCell objects and add them to
    // the JPanel.  You will want to use a GridLayout on the displayBoard
    // to lay out the BoardCell objects.
    JPanel displayBoard = new JPanel();
    
    
    displayBoard.setLayout (new GridLayout(row, column));
    for(int i = 0; i < row; i++) {
      for(int j = 0; j < column; j++) {
        displayBoard.add ( new BoardCell(i, j));
      }
    }


    // Use a BorderLayout to lay out the game board
    setLayout( new BorderLayout() );
    add( status, BorderLayout.NORTH );
    add( displayBoard, BorderLayout.CENTER );
    add( jbtReset, BorderLayout.SOUTH );
    

    
    // Size and show the board
    pack();
    setVisible( true );
  }

  public void reset() {
  this.turn = 'X';
  }

  // This is the method that is called when a BoardCell is clicked on.
  // Refer to the PSA instructions for more details.
  private void makeMove( int col )
  {
    
    if(theBoard.winsFor('X') != true && theBoard.winsFor('O') != true){
      if(theBoard.allowsMove(col) == true) {
        theBoard.addMove(col, this.turn);
        if(this.turn == 'X')
          this.turn = 'O';
        else
          this.turn = 'X';
        if(theBoard.winsFor('X') == true || theBoard.winsFor('O') == true) {
          if(this.turn == 'X')
            this.turn = 'O';
          else
            this.turn = 'X';
        }
      }
    this.status.setText( this.turn + "'s turn.");
    }
    
    if(theBoard.winsFor('X') == true || theBoard.winsFor('O') == true){
      this.status.setText(this.turn + " Won!");   
    }
    
    repaint();
  }
  

  /** An inner class that represents one graphical cell in the connect 4 board.
    * Each cell keeps track of what row and column it is in.
    * These are the objects that will listen for mouse clicks.
    * Because they are an inner class, they have access to all of
    * the methods in the ConnectFour outer class.  
    * 
    * Notice that a BoardCell object IS A JPanel, so it can be added directly
    * to a ConnectFour object (which IS A JFrame).  You can also add listeners
    * to JPanels (and BoardCells, since they are JPanels), which will be useful
    * to detect where the user wants to play.  */
  class BoardCell extends JPanel
  {
    /** The row in which this BoardCell appears in the board. *
      * */
    private int row;
    
    /** The column in which this BoardCell appears in the board. 
      * */
    private int column;
    
    /** Create a new BoardCell object at row r and column c. 
      * The constructor is the right place to add the PlayListener too. */
    BoardCell( int r, int c )
    {
        this.row = r;
        this.column = c;
        PlayListener play = new PlayListener();
        this.addMouseListener (play);
    }
  
    /** Return the preferred size for this BoardCell */
    public Dimension getPreferredSize()
    {
      // Just a suggestion. Feel free to change it if you want.  
      return new Dimension( 50, 50 );
    }
  
    // Paint the BoardCell.  Note that this method should paint empty cells 
    // in one color, cells filled with and 'X' with another color, and cells 
    // filled with an 'O' in a third color.  
    //
    // IMPORTANT: You will need to refer to the 
    // theBoard object in the ConnectFour class to get the contents of the 
    // underlying cell that this visual cell represents.  If this does not make
    // sense to you, seek help now.
    //
    // My suggestion here is to paint the whole background of the cell with a 
    // solid rectangle, and then paint a circle (oval) on top to represent the space
    // or the checker.  You'll want to make your circle slightly smaller than
    // your rectangle.  Of course, feel free to get creative, add shadow, etc.
    protected void paintComponent( Graphics g )
    { 
      super.paintComponent( g );
      setBackground (Color.black);
      g.setColor (Color.white);
      g.fillOval (0,0, getWidth()-5,getHeight()-5);
      
      char turn = theBoard.getContents(row, column);
      if (turn == 'X') {
        g.setColor(Color.red);
        g.fillOval (0,0, getWidth()-5,getHeight()-5);
      }
      if (turn == 'O'){
        g.setColor(Color.blue);
        g.fillOval (0,0, getWidth()-5,getHeight()-5);
      }
    }
    
    /** This is the listener that will handle clicks on the board cell.
      * You will not need to change this code at all, but you should understand 
      * what is going on.
      * */
    class PlayListener implements MouseListener
    {
      /** Inform the ConnectFour object that the corresponding column has been
        * clicked */
      public void mouseClicked( MouseEvent e ) 
      {
        // We just need to tell the board to play a checker in the appropriate
        // column.  column refers to the instance variable in the BoardCell
        // object.  This method calls the makeMove method in the ConnectFour class. 
        makeMove( column );
      }
      
      public void mousePressed( MouseEvent e ) {}
      public void mouseReleased( MouseEvent e ) {}
      public void mouseEntered( MouseEvent e ) {}
      public void mouseExited( MouseEvent e ) {}
    }
}
  
  class ResetListener implements MouseListener {
    
    public void mouseClicked( MouseEvent e ) 
    {
      theBoard.clear();
      repaint();
      ConnectFour.this.turn = 'X';
      ConnectFour.this.status.setText( "Welcome to Connect 4!" );
    }
  
      public void mousePressed( MouseEvent e ) {}
      public void mouseReleased( MouseEvent e ) {}
      public void mouseEntered( MouseEvent e ) {}
      public void mouseExited( MouseEvent e ) {}
  }
  
  public static void main(String[] args) {
   ConnectFour test = new ConnectFour();
  }
  
}
